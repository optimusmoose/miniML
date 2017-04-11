package utils;

/**
 * a utility to return type objects
 * enables abstract construction of config contexts
 * can be further extended for custom types such as ranges
 */


public enum TypeFactory {
    STRING, CHARACTER, BYTE, SHORT, INT, LONG, FLOAT, DOUBLE, BOOLEAN, NULL,
    INSTANCE;

    /**
     * Return an Object equivalent of primitive types
     * @param type String
     * @param value String
     * @return Object
     */
    public Object get(TypeFactory type, String value) {
        switch (type) {
            //if the value is a typeString return it
            case STRING:
                return value;
            //if it is a char, it is the first of the typeString
            case CHARACTER:
                return value.charAt(0);
            //the remainder of the primitives parse to their objects
            case BYTE:
                return Byte.parseByte(value);
            case SHORT:
                return Short.parseShort(value);
            case INT:
                return Integer.parseInt(value);
            case LONG:
                return Long.parseLong(value);
            case FLOAT:
                return Float.parseFloat(value);
            case DOUBLE:
                return Double.parseDouble(value);
            case BOOLEAN:
                return Boolean.parseBoolean(value);
            //if the type is not defined return null
            case NULL:
                return null;
            default:
                return null;
        }
    }
}
