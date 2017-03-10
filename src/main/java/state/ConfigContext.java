package state;

class ConfigContext extends AbstractContext {

    /*
     * A context for configuration objects, will hold information on specific settings
     */
    ConfigContext() {
        super(StateFactory.INSTANCE.empty());
    }
}
