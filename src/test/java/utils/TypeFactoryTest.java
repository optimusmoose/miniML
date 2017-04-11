package utils;

import junit.framework.TestCase;

public class TypeFactoryTest extends TestCase {

    public void testString() {
        TypeFactory type = TypeFactory.STRING;
        String value = "somestring";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertSame(result, value);
    }

    public void testChar() {
        TypeFactory type = TypeFactory.CHARACTER;
        String value = "c";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertEquals(result, 'c');
    }

    public void testShort() {
        TypeFactory type = TypeFactory.SHORT;
        String value = "1";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertTrue(result.equals((short) 1));
    }

    public void testInt() {
        TypeFactory type = TypeFactory.INT;
        String value = "214783647";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertTrue(result.equals(214783647));
    }

    public void testLong() {
        TypeFactory type = TypeFactory.LONG;
        String value = "9223372036854775807";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertTrue(result.equals(9223372036854775807L));
    }


    public void testFloat() {
        TypeFactory type = TypeFactory.FLOAT;
        String value = "3.1415926";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertTrue(result.equals(3.1415926f));
    }

    public void testDouble() {
        TypeFactory type = TypeFactory.DOUBLE;
        String value = "3.1415926535897932";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertTrue(result.equals(3.1415926535897932d));
    }

    public void testBoolean() {
        TypeFactory type = TypeFactory.BOOLEAN;
        String value = "true";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertTrue(result.equals(true));
    }

    public void testUnknownType() {
        TypeFactory type = TypeFactory.NULL;
        String value ="itdoesntevenmatter";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertNull(result);
    }
}
