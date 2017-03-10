package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.SelectedTag;
import weka.classifiers.functions.LinearRegression;
//import weka.classifiers.functions.LibSVM;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.M5P;
import weka.classifiers.Evaluation;

public abstract class Model {
    /*
    * A general Weka function wrapped in a way that allows us to run it and get results from it.
    *
    * Ideally, we will have the entirety of the Model available for inspection after running
    * and keep it around if it outperforms other solutions. We may also want an approach to
    * exporting the Model so that it can be reused on other data in the future.
    */
}

class LR_Model extends Model {
    /*
    * Generates a Weka LinearRegression function Model acting on our data instance with our parameters.
    */
    private String[] pars;
    private Instances data;
    public LR_Model(Instances d, String[] params) throws Exception {
        /*
        * construct and run the Model. Keep as a functioning unit that can be modified as needed.
        *
        * @param   d         A Weka Instance of data. The Model assumes that the data will have a class assigned
        *                    before it gets here.
        * @param   params    A String[] of parameters understood by the Model. If provided, these will be used
        *                    to provide the Model with constraints.
        * @return  void      Other software will be able to get the Evaluation of the Model, but it will not return
        *                    anything by default.
        */
        data = d;
        pars = params;
        run();
    }

    private void run() throws Exception {
        LinearRegression my_lr = new LinearRegression();
        //set our parameters to the object if applicable
        if(pars != null) {
            my_lr.setOptions(pars);
        }
        Evaluation eval = new Evaluation(data);
        //invoke crossfold validation (Classifier obj, Instance, #folds, RNG)
        eval.crossValidateModel(my_lr, data, 10, new Random(1));
        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
    }
}

class NN_Model extends Model {
    /*
    * Generates a Weka LinearRegression function Model acting on our data instance with our parameters.
    */
    private String[] pars;
    private Instances data;
    public NN_Model(Instances d, String[] params) throws Exception {
        data = d;
        pars = params;
        run();
    }

    private void run() throws Exception {
        MultilayerPerceptron mlp = new MultilayerPerceptron();
        //set our parameters to the object if applicable
        if(pars != null) {
            mlp.setOptions(pars);
        }
        Evaluation eval = new Evaluation(data);
        //invoke crossfold validation (Classifier obj, Instance, #folds, RNG)
        eval.crossValidateModel(mlp, data, 10, new Random(1));
        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
    }

}
