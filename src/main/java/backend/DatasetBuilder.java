package backend;

import weka.core.Instance;

/**
 * Hold the master dataset Instance key and give out carefully prepared sections as needed.
 *
 * This should not only reinforce user expectations but also implement random feature search.
 *
 * THIS OBJECT HANDLES ITS OWN CONTEXT KEYS.
 */
public class DatasetBuilder {
    private Instance master;
    protected int[] userAttributeSelectionIndices;
    protected int classIndex;

    public DatasetBuilder(){
    }

    public void collect(){

    }
}
