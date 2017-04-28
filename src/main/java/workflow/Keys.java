package workflow;

/*
 * The keys file holds a a group of keys needed to register and lookup contexts.
 * This class cannot be instantiated, public static final Strings are all that is accessible.
 * If a new context is needed somewhere in teh app, a key should be added to this file.
 */
public class Keys {

    public static final String App = "MML";

    // Dataset Tab
    public static final String DatasetConfig = "MML_DS_CFG";
    public static final String DatasetFile = "MML_DS_CFG_FILE";

    // Preprocess Tab
    // TODO: Preprocess Tab

    // Model Tab
    public static final String ModelConfig = "MML_MD_CFG";

    public static final String EstimatedTimeConfig = "MML_MD_ET_CFG";

    public static final String ToggleLinReg = "MML_MD_TOG_LIN_REG";
    public static final String ToggleNeuralNet = "MML_MD_TOG_NEUR_NET";
    public static final String ToggleSuppVec = "MML_MD_TOG_SUPP_VEC";
    public static final String ToggleDecTree = "MML_MD_TOG_DEC_TREE";

    public static final String MethodSelect = "MM_MD_METH_SEL";
    public static final String Ridge = "MM_MD_RID";

    public static final String NumHiddenLayers = "MM_MD_NUM_HLAY";
    public static final String NumHiddenNodes = "MM_MD_NUM_NDS";
    public static final String LearnRate = "MM_MD_LRN_RATE";
    public static final String NumEpochs = "MM_MD_NUM_EPO";

    public static final String Gamma = "MM_MD_GAM";
    public static final String Epsilon = "MM_MD_EPS";
    public static final String Degree = "MM_MD_DEG";
    public static final String Nu = "MM_MD_NU";

    public static final String PruningConfidence = "MM_MD_PRU_CON";
    public static final String PruningFolds = "MM_MD_PRU_FLD";
    public static final String DecisionTreeInstances = "MM_MD_INST";


    public static final String PreprocessConfig = "MML_PP_CFG";

    /**
     The caller references the constants using <tt>Keys.EMPTY_STRING</tt>,
     and so on. Thus, the caller should be prevented from constructing objects of
     this class, by declaring this private constructor.
     */
    Keys(){
        //this prevents even the native class from calling this ctor as well :
        throw new AssertionError();
    }
}
