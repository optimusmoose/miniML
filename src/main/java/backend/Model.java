package backend;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import weka.classifiers.AbstractClassifier;
import weka.core.Instances;
import weka.core.Option;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.Evaluation;
import java.io.Serializable;

/**
 * A general Weka function wrapped in a way that allows us to run it and get results from it.
 *
 * Ideally, we will have the entirety of the Model available for inspection after running
 * and keep it around if it outperforms other solutions. We may also want an approach to
 * exporting the Model so that it can be reused on other data in the future.
 */
public abstract class Model implements Serializable {
    protected String[] pars;
    protected Instances data;
    protected Evaluation eval;
    protected AbstractClassifier classifier;
    /**
    * construct and run the Model. Keep as a functioning unit that can be modified as needed.
     *
     * TODO implement parameter search and generation; add logging once ready
    *
    * @param   d         A Weka Instance of data. The Model assumes that the data will have a class assigned
    *                    before it gets here.
    * @param   params    A String[] of parameters understood by the Model. If provided, these will be used
    *                    to provide the Model with constraints.
    * @return  void      Other software will be able to get the Evaluation of the Model, but it will not return
    *                    anything by default.
    */
    public Model(Instances d, String[] params) throws Exception {
        data = d;
        pars = params;
        eval = new Evaluation(data);
        classifier = null;
    }

    /**
     * Once classifier is known, we can prepare for a run.
     * More may go here later.
     * @throws Exception    as required by setOptions()
     */
    public void prepare() throws Exception {
        if(classifier != null){
            if(pars != null) {
                classifier.setOptions(pars);
            }
        }
    }

    /**
     * Run the model.
     *
     * This will 'fill in' the Model's eval, making results available to the user.
     * Once run, a Model should be savable. This lets a user use a good Model again.
     *
     * @throws Exception    a general-purpose catch-all
     */
    abstract void run() throws Exception;

    /**
     * get information from the classifier about what options (parameters) it accepts
     * so that we can build our parameter search space dynamically.
     * TODO this should be used in future iterations if possible; weka conventions do
     * TODO not make this economical at the moment, but it should be kept in mind!
     */
    public String[] get_info(){
        Enumeration<Option> info = classifier.listOptions();
        ArrayList<String> parameter_descriptions = new ArrayList<String>();
        while(info.hasMoreElements()){
            Option elt = info.nextElement();
            parameter_descriptions.add(elt.synopsis());
            parameter_descriptions.add(elt.description());
        }
        String[] arr = parameter_descriptions.toArray(new String[parameter_descriptions.size()]);
        return(arr);
    }
}

class LR_Model extends Model {
    /**
    * Generates a Weka LinearRegression function Model acting on our data instance with our parameters.
    */
    public LR_Model(Instances d, String[] params) throws Exception {
        super(d,params);
        classifier = new LinearRegression();
        prepare();
        run();
    }

    /**
     * Configure crossfold validation and run the linear regression model
     * @throws Exception
     */
    protected void run() throws Exception {
        //invoke crossfold validation (Classifier obj, Instance, #folds, RNG)
        eval.crossValidateModel(classifier, data, 10, new Random(1));
        //System.out.println(eval.toSummaryString("\nResults\n======\n", false));
    }
}

class NN_Model extends Model {
    /**
    * Generates a Weka MultlayerPerceptron function Model acting on our data instance with our parameters.
    */
    public NN_Model(Instances d, String[] params) throws Exception {
        super(d,params);
        classifier = new MultilayerPerceptron();
        prepare();
        run();
    }

    /**
     * Configure crossfold validation and run the neural network model
     * @throws Exception
     */
    protected void run() throws Exception {
        //invoke crossfold validation (Classifier obj, Instance, #folds, RNG)
        eval.crossValidateModel(classifier, data, 10, new Random(1));
        //System.out.println(eval.toSummaryString("\nResults\n======\n", false));
    }
}
