package state;

public class PreprocessContext extends AbstractContext {

    private ProcessState state;

    PreprocessContext() {
        //no pre-processing functionality yet, state is ready
        super(StateFactory.INSTANCE.ready());
    }
}
