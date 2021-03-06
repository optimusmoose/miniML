package backend;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;

import utils.Logging.MiniMLLogger;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.SMOreg;
import weka.core.Instances;
import weka.core.Option;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
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
    public Model(Instances d, String[] params) throws ModelConstructException,Exception {
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
    abstract void run() throws ModelRunException,Exception;

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


    /**
     * Quick-fast method to log a summary of a Model's Evaluation.
     */
    protected void summarize() {
        MiniMLLogger.INSTANCE.info(eval.toSummaryString("\nResults\n======\n", false));
    }

    /**
     * On classifiable data sets, we may determine the percentage of classes we got right
     * TODO use this method on discrete-classed data!!
     * @throws Exception
     */
    protected void getPercentCorrect() throws Exception {
        eval.evaluateModel(this.classifier, this.data);
        MiniMLLogger.INSTANCE.info(String.valueOf(eval.pctCorrect()));
    }

}

class LR_Model extends Model implements Runnable {
    /**
    * Generates a Weka LinearRegression function Model acting on our data instance with our parameters.
    */
    public LR_Model(Instances d, String[] params) throws ModelConstructException,Exception {
        super(d,params);
        classifier = new LinearRegression();
        prepare();
        run();
    }

    /**
     * Configure crossfold validation and run the linear regression model
     * @throws Exception
     */
    @Override
    public void run() {
        //invoke crossfold validation (Classifier obj, Instance, #folds, RNG)
        try {
            MiniMLLogger.INSTANCE.info("Running a LR_Model");
            eval.crossValidateModel(classifier, data, 10, new Random(1));
            summarize();
        } catch (Exception e) {
            MiniMLLogger.INSTANCE.exception(e);
        }
    }
}

class NN_Model extends Model implements Runnable {
    /**
    * Generates a Weka MultlayerPerceptron function Model acting on our data instance with our parameters.
    */
    public NN_Model(Instances d, String[] params) throws ModelConstructException,Exception {
        super(d,params);
        classifier = new MultilayerPerceptron();
        prepare();
        run();
    }

    /**
     * Configure crossfold validation and run the neural network model
     * @throws Exception
     */
    @Override
    public void run() {
        //invoke crossfold validation (Classifier obj, Instance, #folds, RNG)
        try {
            MiniMLLogger.INSTANCE.info("Running a NN_Model");
            eval.crossValidateModel(classifier, data, 10, new Random(1));
        } catch (Exception e) {
            MiniMLLogger.INSTANCE.exception(e);
        }
        summarize();
    }
}

class DT_Model extends Model implements Runnable {
    /**
     * Generates a Weka J48 Model acting on our data instance with our parameters.
     */
    public DT_Model(Instances d, String[] params) throws ModelConstructException,Exception {
        super(d,params);
        classifier = new J48();
        prepare();
        run();
    }

    /**
     * Configure crossfold validation and run the decision tree model
     * @throws Exception
     */
    @Override
    public void run() {
        try {
            MiniMLLogger.INSTANCE.info("Running a DT_Model");
            eval.crossValidateModel(classifier, data, 10, new Random(1));
        } catch (Exception e) {
            MiniMLLogger.INSTANCE.exception(e);
        }
        summarize();
    }
}

class SMO_Model extends Model implements Runnable {
    /**
     * Generates a Weka SMO Model acting on our data instance with our parameters.
     */
    public SMO_Model(Instances d, String[] params) throws ModelConstructException,Exception {
        super(d,params);
        if(this.data.classAttribute().isNumeric()){
            classifier = new SMOreg();
        } else {
            classifier = new SMO();
        }
        prepare();
        run();
    }

    /**
     * Configure crossfold validation and run the SMO model
     * @throws Exception
     */
    @Override
    public void run() {
        try {
            MiniMLLogger.INSTANCE.info("Running a SMO_Model");
            eval.crossValidateModel(classifier, data, 10, new Random(1));
        } catch (Exception e) {
            MiniMLLogger.INSTANCE.exception(e);
        }
        summarize();
    }
}

/**
 * A special exception for failed Model.run() calls.
 * TODO should the message have more information?
 */
class ModelRunException extends Exception {
    public ModelRunException(){}

    public ModelRunException(String message){
        super(message);
    }

}

/**
 * A special exception for failed Model() instantiation calls.
 * TODO should the message have more information?
 */
class ModelConstructException extends Exception {
    public ModelConstructException(){}

    public ModelConstructException(String message){
        super(message);
    }

}
