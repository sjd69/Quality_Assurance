import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.*;

public class PairwiseTest {

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testBelowTwoCommandLineArgLength() {
        Pairwise pairwise = new Pairwise();

        String[] array = {"1"};

        assertFalse(pairwise.checkArgLength(array));
    }

    @Test
    public void testZeroCommandLineArgLength() {
        Pairwise pairwise = new Pairwise();

        String[] array = {};

        assertFalse(pairwise.checkArgLength(array));
    }

    @Test
    public void testTwoCommandLineArgLength() {
        Pairwise pairwise = new Pairwise();

        String[] array = {"1", "2"};

        assertTrue(pairwise.checkArgLength(array));
    }

    @Test
    public void testAboveTwoCommandLineArgLength() {
        Pairwise pairwise = new Pairwise();

        String[] array = {"1", "2", "3", "4", "5"};

        assertTrue(pairwise.checkArgLength(array));
    }

    @Test
    public void testLowLineArgoutput() {
        Pairwise pairwise = new Pairwise();

        String[] array = {"1"};
        pairwise.checkArgLength(array);

        assertEquals("Please enter at least two parameters!" + System.getProperty("line.separator"),
                outContent.toString());
    }

    @Test
    public void testBooleanOutput() {
        Pairwise pairwise = new Pairwise();
        pairwise.buildTests(4, new StringBuilder());

        StringBuilder sb = pairwise.buildCoveringArray(Pairwise.lineCount);
        assertTrue(sb.toString().matches("[01" + System.getProperty("line.separator") + "]+"));
    }

    @Test
    public void testZeroBooleanOutput() {
        Pairwise pairwise = new Pairwise();
        pairwise.buildTests(0, new StringBuilder());

        StringBuilder sb = pairwise.buildCoveringArray(Pairwise.lineCount);
        assertTrue(sb.toString().matches("[01" + System.getProperty("line.separator") + "]+"));
    }

    @Test
    public void testTwoExhaustive() {
        Pairwise pairwise = new Pairwise();
        pairwise.buildTests(2, new StringBuilder());
        pairwise.len = 2;

        StringBuilder sb = pairwise.buildCoveringArray(Pairwise.lineCount);
        assertEquals(pairwise.exhaustiveTests.toString(), sb.toString());
    }
    @Test
    public void testNoExhaustive() {
        Pairwise pairwise = new Pairwise();
        pairwise.buildTests(3, new StringBuilder());
        pairwise.len = 3;

        StringBuilder sb = pairwise.buildCoveringArray(Pairwise.lineCount);
        assertNotEquals(pairwise.exhaustiveTests.toString(), sb.toString());
    }

    @Test
    public void testLargeNoExhaustive() {
        Pairwise pairwise = new Pairwise();
        pairwise.buildTests(10, new StringBuilder());
        pairwise.len = 10;

        StringBuilder sb = pairwise.buildCoveringArray(Pairwise.lineCount);
        assertNotEquals(pairwise.exhaustiveTests.toString(), sb.toString());
    }

    @Test
    public void testOutput() {
        Pairwise pairwise = new Pairwise();
        pairwise.buildTests(2, new StringBuilder());
        pairwise.len = 2;

        pairwise.coveringArray = pairwise.buildCoveringArray(Pairwise.lineCount);

        pairwise.outputCoveringArray();

        assertEquals(outContent.toString(), pairwise.coveringArray.toString()
                + System.getProperty("line.separator"));
    }

    @Test
    public void testExhaustive() {
        Pairwise pairwise = new Pairwise();
        pairwise.buildTests(10, new StringBuilder());
        pairwise.len = 10;

        String[] exhaustiveSplit = pairwise.exhaustiveTests.toString().split(System.getProperty("line.separator"));

        assertEquals(exhaustiveSplit.length, Math.pow(2, pairwise.len), .001);
    }
}
