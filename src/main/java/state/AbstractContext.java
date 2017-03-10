package state;

abstract class AbstractContext implements ContextInterface {
    protected ProcessState state;

    AbstractContext(ProcessState state) {
        this.state = state;
    }

    //return the state of the context
    public ProcessState getState(){
        return this.state;
    }
}
