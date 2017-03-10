package state;

/*
 * A context for configuration objects, will hold information on specific settings
 */
class ConfigContext extends AbstractContext {

    Object value; //object form of a primative or other type such as range

    ConfigContext() {
        super(StateFactory.INSTANCE.empty());
    }

    void setContext(String type, String value) {
        TypeFactory.INSTANCE.get(type, value);
    }
}
