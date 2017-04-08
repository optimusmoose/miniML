package workflow;

public class Keys {

    public static final String App = "MML";

    public static final String DatasetConfig = "MML_DS_CFG";
    public static final String DatasetFile = "MML_DS_CFG_FILE";

    public static final String ModelConfig = "MML_ALGS_CFG";

    public static final String PreprocessConfig = "MML_PP_CFG";

    /**
     The caller references the constants using <tt>Keys.EMPTY_STRING</tt>,
     and so on. Thus, the caller should be prevented from constructing objects of
     this class, by declaring this private constructor.
     */
    Keys(){
        //this prevents even the native class from
        //calling this ctor as well :
        throw new AssertionError();
    }
}
