package state;

import javax.swing.plaf.nimbus.State;

/**
 * The Context of the algorithm models configuration
 */
class ModelContext extends AbstractContext {

    /**
     * dataset has not been set by user at start, state will be error
     */
    ModelContext() {
        super(StateFactory.INSTANCE.error());
    }

}
