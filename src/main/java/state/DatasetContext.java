package state;

import java.io.File;

/**
 * The Context of the dataset configuration
 */
class DatasetContext extends AbstractContext {

    private AbstractParameterContext fileName;


    /**
     * Instantiate with error state, as no dataset is selecton on construction
     */
    DatasetContext() {
        super(StateFactory.INSTANCE.error());
        //TODO: finish implementing specific parameter context and then tie in
//        fileName = new AbstractParameterContext();

//        this.childContexts.add(fileName);
    }

    //TODO: extend abstract parameter context to fileparametercontext, move these there for isValid
    /**
     *
     * @return boolean
     */
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
