__author__ = 'Tim Anderson'

from subprocess import call, check_output
import re
import json
import sys
import os
import os.path
import random
from timeit import default_timer as timer

# initial seed so all random calls with same json config create similar timing sets
random_seed = 0xDEADBEEF

json_key_weka_src = "weka_executable_src"
json_key_tolerance_ratio = "tolerance_ratio"
json_key_runs_per_algorithm = "runs_per_algorithm"
json_key_attribute_selection = "attribute_selection_algorithms"
json_key_parameter_search_class = "classification_algorithms"

json_key_run_name = "run_name"
json_key_dataset_src = "dataset_src"
json_key_algorithm_scheme = "algorithm_scheme"
json_key_goal_times = "goal_times"

# for output
json_key_regression_list = "regression_list"
json_key_classification_list = "classification_list"
json_key_time = "goal_time"
json_key_flags = "flag_values"
json_key_run_success = "run_success"
json_key_output_times = "run_times"

json_key_correctly_classified = "correctly_classified"
json_key_best_classification = "best_classification_percentage"

json_key_scheme_flags = "flags"
json_key_flag_character = "flag_char"
json_key_flag_type = "flag_type"
json_key_flag_min = "min"
json_key_flag_max = "max"
attribute_selection_formattable_call_str = "java -Xmx1024m -classpath {wekaJarSrc} {filter} -i {input} -o {output}"

float_regex = "([0-9]*.[0-9]"
correctly_classified_regex_string = "Correctly Classified Instances:* *([0-9]* *[0-9]*\\.[0-9]*) *%"
correlation_coefficient_regex_string = "Correlation coefficient:* *([0-9]* *[0-9]*\\.[0-9]*"
algorithm_quality_regex_string = "({0})|({1})".format(correctly_classified_regex_string,
                                                      correlation_coefficient_regex_string)

weka_random_search_regex = "\"weka.\\S+?RandomSearch [^\"]"
flag_value_regex = lambda flag_char: "-{0} *[0-9]*.?[0-9]*".format(flag_char)
flag_value_format_str = "-{flag} \{{{flag}_value}}"
rand_search_value_regex = flag_value_regex('F')
timeTakenRegexString = "Time taken to build model:* *([0-9]*\\.[0-9]*) *seconds"
num_expected_cmd_args = 1
cmd_args = sys.argv
this_dir_path = os.path.dirname(os.path.realpath(__file__))
temp_file_src = os.path.join(this_dir_path, "temp_filter_dataset.arff")

minimum_allowable_rand_percentage = 1e-13
maximum_allowable_rand_percentage = 100.0

'''
neural net (MLP)
svn (weka SMO)
linear reg
j48

using wrapper subset eval!

maybe filter method
TODO: look for -F tag specifically in randomsearch, not just any -F tag!
TODO: create timings from randomsearch for attribute selection alone!
TODO: write own parameter randomizer, be able to create % correct from time taken

choosing attribute for linreg



choosing flag values
linspace with n points, if there's more time, linspace with 2n points, do ones between


'''


def show_help():
    print("expected arguments:")
    print(
        " 1. File src for json file detailing algorithms and datasets. all datasets are expected to be in the same directory.")
    print(" ex: ")
    print("{")
    print("\t\"weka_executable_src\": \"\\usr\\weka\\weka.jar\",")
    print("\t\"tolerance_time\": 3")
    print("\t\"num_runs\" : 100")
    print("\t\t\"goal_times\": [10,20,30,40,...],")
    print("\t\"algorithm_list\":[")
    print("\t\t{")
    print("\t\t\"run_name\": timing_run_1,")
    print("\t\t\"dataset_src\":\"iris.arff\",,")
    print("\t\t\"algorithm_args\": \"generating this from the weka GUI is the easiest way...\",")
    print("\t\t\"filtering_args\": \"generating this from the weka GUI is easiest way... leave off for no filtering")
    print("\t\t},")
    print("\t\t{...}")
    print("\t]")
    print("}")


def check_args():
    if len(cmd_args) < num_expected_cmd_args:
        print("Number of args is less than expected number " + str(num_expected_cmd_args))
        show_help()
        exit()

    if cmd_args[0].lower() == "help":
        show_help()
        exit()

    if not os.path.exists(cmd_args[0]):
        print("could not find json file at location: " + cmd_args[0])


def load_json(json_file_src):
    try:
        with open(json_file_src) as data_file:
            json_data = json.load(data_file)

            if not json_data:
                print("Error loading or parsing the json file")
                exit()
            else:
                return json_data
    except:
        print("exception created when loading json file. Is the path correct? Is it legal Json?")
        exit()


def run_classification(json_data_obj):
    for classification in json_data_obj[json_key_parameter_search_class]:
        algorithm_timings = classification_create_timings(json_data_obj[json_key_weka_src], classification)

        output_file_src = os.path.join(this_dir_path, classification[json_key_run_name])
        with open(output_file_src, "w+") as output_file:
            json.dump(algorithm_timings, output_file, indent=1)
        print("{0} written to file {1}".format(classification[json_key_run_name]), output_file_src)

    print("all attribute selection algorithm dumped to corresponding json files")


def classification_create_timings(weka_src, classification_algorithm):
    scheme_flags = classification_algorithm[json_key_scheme_flags]
    scheme_str = classification_algorithm[json_key_algorithm_scheme]

    # use regex to alter the scheme to easily allow formatting of flag values
    for flag in scheme_flags:
        scheme_str = re.sub(flag_value_regex(flag), flag_value_format_str, scheme_str)

    formattable_call_str = "java -classpath {wekaJarSrc} {algorithm} -t {datasetFileSrc}".format(
        wekaJarSrc=weka_src, algorithm=scheme_str, datasetFileSrc=classification_algorithm[json_key_dataset_src])

    output_timing_dict = {json_key_run_name: classification_algorithm[json_key_run_name], json_key_goal_times: []}

    print("beginning random parameter selection on run {0}".format(classification_algorithm[json_key_run_name]))
    goal_time_list = classification_algorithm[json_key_goal_times].sort()
    for goal_time in goal_time_list:
        classification_list = classification_randomize_until_goal_time(formattable_call_str, scheme_flags, goal_time)
        output_timing_dict[json_key_goal_times].append(
            {json_key_time: goal_time, json_key_classification_list: classification_list})

    return output_timing_dict


def find_best_classification(classification_list):
    percentages = list(map(lambda c_string: float(re.match(float_regex, c_string)), classification_list))
    return max(percentages)


def classification_randomize_until_goal_time(formattable_call_str, scheme_flags, goal_time):
    total_time_taken = 0
    classification_results = []
    while total_time_taken < goal_time:
        random_flags = randomize_flags(scheme_flags)
        call_str = formattable_call_str.format(random_flags)

        start_time = timer()
        result_str = check_output(call_str)
        end_time = timer()
        elapsed_time = end_time - start_time
        total_time_taken += elapsed_time

        algorithm_output_quality = re.match(algorithm_quality_regex_string, result_str).group(0)
        classification_results.append({json_key_time: elapsed_time,
                                       json_key_flags: random_flags,
                                       json_key_correctly_classified: algorithm_output_quality})
        print("ran classification/regression, time taken: {0}".format(total_time_taken))
    return classification_results


def randomize_flags(scheme_flags):
    flag_value_dict = {}
    for flag in scheme_flags:
        flag_value_str = "{{0}_value}".format(flag[json_key_flag_character])
        if json_key_flag_type in flag and flag[json_key_flag_type] == "int":
            flag_value_dict[flag_value_str] = random.randrange(flag[json_key_flag_min], flag[json_key_flag_max])
        else:
            flag_value_dict[flag_value_str] = random.uniform(flag[json_key_flag_min], flag[json_key_flag_max])
    return flag_value_dict


def run_attribute_selection(json_data_obj):
    '''
    looks through the json object, and runs creates timed attribute selection dataset.
    attribute selection scheme must contain a random search, with a -f parameter

    :param json_data_obj: base json object that has been checked for validity
    :return:
    '''

    for attr_sel_algorithm in json_data_obj[json_key_attribute_selection]:
        algorithm_timings = attribute_selection_create_timings(json_data_obj[json_key_weka_src], attr_sel_algorithm,
                                                               json_data_obj[json_key_tolerance_ratio],
                                                               json_data_obj[json_key_runs_per_algorithm])

        output_file_src = os.path.join(this_dir_path, attr_sel_algorithm[json_key_run_name])
        with open(output_file_src, "w+") as output_file:
            json.dump(attr_sel_algorithm, output_file, indent=1)
        print("{0} written to file {1}".format(attr_sel_algorithm[json_key_run_name]), output_file_src)

    print("all attribute selection algorithm dumped to corresponding json files")


def attribute_selection_create_timings(weka_executable_src, attr_sel_algorithm, tolerance_ratio, runs_per_goal_time):
    formattable_scheme = re.sub(rand_search_value_regex, "-F {0}", attr_sel_algorithm[json_key_algorithm_scheme])
    formattable_call_str = "java -classpath {wekaJarSrc} {scheme} -i {input} -o {output}".format(
        wekaJarSrc=weka_executable_src, scheme=formattable_scheme, input=attr_sel_algorithm[json_key_dataset_src],
        output=temp_file_src)

    output_timing_dict = {json_key_run_name: attr_sel_algorithm[json_key_run_name], json_key_goal_times: []}

    goal_time_list = attr_sel_algorithm[json_key_goal_times]
    goal_time_list.sort()

    print("beginning runs for attribute selection {0}.".format(attr_sel_algorithm[json_key_run_name]))
    for goal_time in goal_time_list:
        print("beginning timing for {0} seconds".format(goal_time))
        output_timing_dict[json_key_goal_times].append(
            attribute_selection_tweak_and_find_goal_time(formattable_call_str, goal_time, tolerance_ratio,
                                                         runs_per_goal_time, attr_sel_algorithm[json_key_run_name]))
    print("completed run {0}.".format(attr_sel_algorithm[json_key_run_name]))

    return output_timing_dict


def attribute_selection_tweak_and_find_goal_time(formattable_call_str, goal_time, tolerance_ratio,
                                                 runs_per_goal_time, run_name):
    rand_percent_step_initial = sys.float_info.epsilon
    rand_percent = rand_percent_step_initial
    time_threshold_low = goal_time - (goal_time * tolerance_ratio)
    time_threshold_high = goal_time + (goal_time * tolerance_ratio)
    num_runs_in_time_threshold = 0

    # create output dictionary
    this_goal_time_result = {json_key_time: goal_time, json_key_output_times: []}

    while (num_runs_in_time_threshold < runs_per_goal_time):
        call_str = formattable_call_str.format(rand_percent)
        start_time = timer()
        check_output(call_str)
        end_time = timer()
        total_time = end_time - start_time

        if time_threshold_low < total_time < time_threshold_high:
            print("ran attribute selection in threshold: time {0}".format(total_time))
            this_goal_time_result[json_key_output_times].append(total_time)
            num_runs_in_time_threshold += 1
        else:
            if rand_percent <= minimum_allowable_rand_percentage and total_time > time_threshold_high:
                this_goal_time_result[json_key_run_success] = "failure: goal time too high"
                print("could not create run long enough to match time threshold of {0}".format(time_threshold_low))
                return this_goal_time_result
            elif rand_percent >= maximum_allowable_rand_percentage and total_time < time_threshold_low:
                this_goal_time_result[json_key_run_success] = "failure: goal time too low"
                print("could not create run short enough to match time threshold of {0}".format(time_threshold_high))
                return this_goal_time_result

            else:
                #tweak the search percent by a factor relating to the previous time taken
                rand_percent_change_ratio = (goal_time / total_time) * .99
                rand_percent *= rand_percent_change_ratio
                #keep in maximum and minimums
                rand_percent = min(rand_percent, maximum_allowable_rand_percentage)
                rand_percent = max(rand_percent, minimum_allowable_rand_percentage)
                print("{0} not in tolerance, trying new random search percent {1}".format(total_time, rand_percent))

    print("finished run list for algorithm {0}, timing {1}.".format(run_name, goal_time))
    this_goal_time_result[json_key_run_success] = "success"
    return this_goal_time_result


def test_config_json_validity(json_obj):
    '''
    Runs simple tests on the validity of the json object, and will alert the user if expected keys are missing.
    Returns true if validity check was successful, false if an error was found.
    '''

    top_level_json_keys = [json_key_weka_src, json_key_tolerance_ratio, json_key_runs_per_algorithm,
                           json_key_attribute_selection, json_key_parameter_search_class]
    attribute_selection_subkeys = [json_key_run_name, json_key_dataset_src, json_key_algorithm_scheme,
                                   json_key_goal_times]
    parameter_search_classification_subkeys = [json_key_run_name, json_key_dataset_src, json_key_algorithm_scheme,
                                               json_key_goal_times, json_key_scheme_flags]

    for json_key in top_level_json_keys:
        if not json_key in json_obj:
            print(
                "{0} not found in top level json configuration. Please check json configuration for correctness.".format(
                    json_key))
            return False

    for attr_sel_run_instance in json_obj[json_key_attribute_selection]:
        for attr_sel_subkey in attribute_selection_subkeys:
            if not attr_sel_subkey in attr_sel_run_instance:
                print("key {0} not found in an instance of attribute selection test. "
                      "Please check configuration for correctness.".format(attr_sel_subkey))
                return False

    for param_search_instance in json_obj[json_key_parameter_search_class]:
        for param_search_subkey in parameter_search_classification_subkeys:
            if not param_search_subkey in param_search_instance:
                print("key {0} not found in an instance of attribute selection test. "
                      "Please check configuration for correctness.".format(param_search_subkey))
                return False

    return True

random.seed(random_seed)  # for future use
check_args()
json_input_file_src = cmd_args[0]
json_data_obj = load_json(json_input_file_src)
if not test_config_json_validity(json_data_obj):
    exit()

run_attribute_selection(json_data_obj)

run_classification(json_data_obj)

