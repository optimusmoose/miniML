package state;

import javax.swing.plaf.nimbus.State;

class ModelContext extends AbstractContext {

    ModelContext() {
        //dataset has not been set by user at start, state will be error
        super(StateFactory.INSTANCE.error());
    }

}
