package workflow.context;

import utils.Logging.MiniMLLogger;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class InstanceContext extends ParameterContext {
    /**
     * Instantiate context with a null state
     *
     * @param parentContext
     * @param key
     */
    public InstanceContext(ContextInterface parentContext, String key) {
        super(parentContext, key);
    }

    public void setValue(Instance data) {
        this.value = data;
    }

    public void loadInstanceFromPath(String path) throws Exception {
        DataSource source = new DataSource(path);
        Instances data = source.getDataSet();
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }
        this.value = data;
        MiniMLLogger.INSTANCE.info("Dataset loaded with these attributes");
        for (int i = 0; i < data.numAttributes(); i++)
        {
            MiniMLLogger.INSTANCE.info(data.attribute(i));
        }
    }
}
