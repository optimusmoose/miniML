package state;

import junit.framework.TestCase;

public class TypeFactoryTest extends TestCase {

    public void testString() {
        String type = "str";
        String value = "somestring";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertSame(result, value);
    }

    public void testChar() {
        String type = "char";
        String value = "c";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertEquals(result, 'c');
    }

    public void testShort() {
        String type = "short";
        String value = "1";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertTrue(result.equals((short) 1));
    }

    public void testInt() {
        String type = "int";
        String value = "214783647";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertTrue(result.equals(214783647));
    }

    public void testLong() {
        String type = "long";
        String value = "9223372036854775807";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertTrue(result.equals(9223372036854775807L));
    }


    public void testFloat() {
        String type = "float";
        String value = "3.1415926";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertTrue(result.equals(3.1415926f));
    }

    public void testDouble() {
        String type = "double";
        String value = "3.1415926535897932";

        Object result = TypeFactory.INSTANCE.get(type, value);
        System.out.println(value);
        System.out.println(result);

        assertTrue(result.equals(3.1415926535897932d));
    }

    public void testBoolean() {
        String type = "boolean";
        String value = "true";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertTrue(result.equals(true));
    }

    public void testUnknownType() {
        String type = "somenonexistenttype";
        String value ="itdoesntevenmatter";

        Object result = TypeFactory.INSTANCE.get(type, value);
        assertNull(result);
    }
}
