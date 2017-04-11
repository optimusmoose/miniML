"""
Requires mdp, numpy, arff, and matplotlib. Run pip install for each and the script should run fine
"""

import mdp, numpy as np, arff,  matplotlib.pyplot as plt, argparse

"""
Returns a list of lists by attribute in the arff file. ex/ if attributes are eye color, height, weight, return[0] = eye
color list, return[1] = height list, return[2] = weight list.
"""
def arff_parse(file_path):
    data_array = []
    row_count = 0
    for row in arff.load(file_path):
        att_counter = 0
        for attribute in row:
            if row_count == 1:
                data_array.append([attribute])
            elif row_count > 1:
                data_array[att_counter].append(attribute)
            att_counter += 1
        row_count += 1
    return data_array

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="Create a PCA plot given a data file.")

def graph_data(data_array)


# print (usable_data)
