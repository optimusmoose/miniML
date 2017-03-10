package state;


class DatasetContext extends AbstractContext {

    //TODO: move into config context, that is there purpose
    private String fileName;

    DatasetContext() {
        //dataset has not been set by user at start, state will be error
        super(StateFactory.INSTANCE.error());
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
