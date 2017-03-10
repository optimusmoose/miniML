import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MiniMLTest extends TestCase {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDownStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testMain()
    {
        String[] args = new String[]{};
//        MiniML.main(args);
//        assertEquals("Hello World!", outContent.toString());
    }

}
