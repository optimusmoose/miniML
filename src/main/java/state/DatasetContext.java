package state;

class DatasetContext extends AbstractContext {

    private ConfigContext fileName;

    DatasetContext() {
        //dataset has not been set by user at start, state will be error
        super(StateFactory.INSTANCE.error());
        fileName = new ConfigContext();

        this.childContexts.add(fileName);
    }

    public boolean fileNameValid()
    {
        //TODO: write finename validation utility
        return false;
    }

    public boolean fileIsValid()
    {
        //TODO: write lint utility for file
        return false;
    }
}
