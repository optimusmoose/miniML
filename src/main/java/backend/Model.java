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
}

class LR_Model extends Model {
    private String[] pars;
    private Instances data;
    public LR_Model(Instances d, String[] params) throws Exception {
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
