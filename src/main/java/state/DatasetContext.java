package state;

import java.io.File;

class DatasetContext extends AbstractContext {

    private AbstractParameterContext fileName;

    DatasetContext() {
        //dataset has not been set by user at start, state will be error
        super(StateFactory.INSTANCE.error());
//        fileName = new AbstractParameterContext();

//        this.childContexts.add(fileName);
    }

    //TODO: move these verifications into config Context? extend, to FileConfigContext
    public boolean fileNameValid()
    {
        return new File((String) fileName.getValue()).isFile();
    }

    public boolean fileIsValid()
    {
        //TODO: write lint utility for file
        return true;
    }
}
