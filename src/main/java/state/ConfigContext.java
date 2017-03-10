package state;

/*
 * A context for configuration objects, will hold information on specific settings
 */
class ConfigContext extends AbstractContext {

    Class type; //class of the object equivalent to a primative
    Object value; //

    ConfigContext() {
        super(StateFactory.INSTANCE.empty());
    }
}
