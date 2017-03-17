package utils;

/**
 * a utility to return type objects
 * enables abstract construction of config contexts
 * can be further extended for custom types such as ranges
 */
public enum TypeFactory {
    INSTANCE;

    /**
     * Return an Object equivalent of primitive types
     * @param type String
     * @param value String
     * @return Object
     */
    public Object get(String type, String value) {
        switch (type) {
            //if the value is a string return it
            case "str":
                return value;
            //if it is a char, it is the first of the string
            case "char":
                return value.charAt(0);
            //the remainder of the primatives parse to their objects
            case "byte":
                return Byte.parseByte(value);
            case "short":
                return Short.parseShort(value);
            case "int":
                return Integer.parseInt(value);
            case "long":
                return Long.parseLong(value);
            case "float":
                return Float.parseFloat(value);
            case "double":
                return Double.parseDouble(value);
            case "boolean":
                return Boolean.parseBoolean(value);
            //if the type is not defined return null
            default:
                return null;
        }
    }
}