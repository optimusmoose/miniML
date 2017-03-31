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
<<<<<<< c56581d8d0c066db10d60ceb7594e48722be5b30
    String flag;

    public WrappedParam(String type, String name, String flag){
        this.type = type;
        this.name = name;
        this.flag = flag;
    }

    /**
     * Every parameter has a flag.
     * @return
     */
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
=======
>>>>>>> stubs made and gradually getting populated. more shortly.

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

<<<<<<< c56581d8d0c066db10d60ceb7594e48722be5b30
    public WrappedParamFinal(String type, String name, String flag, String value){
        super(type,name,flag);
        this.value = value;
    }

=======
>>>>>>> stubs made and gradually getting populated. more shortly.
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

<<<<<<< c56581d8d0c066db10d60ceb7594e48722be5b30
    public WrappedParamFloat(String type, String name, String flag, float minValue, float maxValue){
        super(type,name,flag);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

=======
>>>>>>> stubs made and gradually getting populated. more shortly.
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
<<<<<<< c56581d8d0c066db10d60ceb7594e48722be5b30
    int maxValue;

    public WrappedParamInt(String type, String name, String flag, int minValue, int maxValue) {
        super(type, name, flag);
        this.minValue = minValue;
        this.maxValue = maxValue;

    }
=======
>>>>>>> stubs made and gradually getting populated. more shortly.

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

<<<<<<< c56581d8d0c066db10d60ceb7594e48722be5b30
=======
    int maxValue;
>>>>>>> stubs made and gradually getting populated. more shortly.
}

class WrappedParamBoolean extends WrappedParam {
    boolean value;

<<<<<<< c56581d8d0c066db10d60ceb7594e48722be5b30
    public WrappedParamBoolean(String type, String name, String flag, boolean value) {
        super(type, name, flag);
        this.value = value;
    }

=======
>>>>>>> stubs made and gradually getting populated. more shortly.
    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
<<<<<<< c56581d8d0c066db10d60ceb7594e48722be5b30

class WrappedParamString extends WrappedParam {
    String value;

    public WrappedParamString(String type, String name, String flag, String value) {
        super(type, name, flag);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

/**
 * Some parameters are ONLY a flag, no value at all.
 */
class WrappedParamEmpty extends WrappedParam {

    public WrappedParamEmpty(String type, String name, String flag) {
        super(type, name, flag);
    }
}
=======
>>>>>>> stubs made and gradually getting populated. more shortly.
