package state;

/**
 * The Context of the algorithm models configuration
 */
public class PreprocessContext extends AbstractContext {

    /**
     * no pre-processing functionality yet, state is ready
     */
    PreprocessContext() {
        super(StateFactory.INSTANCE.ready());
    }
}
