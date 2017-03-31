package backend;

/**
 * We have several types of parameters and we need them to have uniform behavior.
 * Make sure they have some predictability.
 *
 * Derived classes may have more values (all are described below)
 */
abstract class WrappedParam {
    String name;
    String type;

    /**
     * Every parameter has a type.
     * @return
     */
    public String getType(){
        return type;
    }


    public void setType(String t){
        type = t;
    }

    /**
     * Every parameter, regardless of type, has a name.
     * @return
     */
    public String getName(){
        return name;
    }

    public void setName(String n){
        name = n;
    }
}

/**
 * This represents a SELECTED VALUE of a parameter for our algorithm!
 * Only use it to feed an algorithm!
 */
class WrappedParamFinal extends WrappedParam{
    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

class WrappedParamFloat extends WrappedParam {
    float minValue;
    float maxValue;

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }
}

class WrappedParamInt extends WrappedParam {
    int minValue;

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    int maxValue;
}

class WrappedParamBoolean extends WrappedParam {
    boolean value;

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
