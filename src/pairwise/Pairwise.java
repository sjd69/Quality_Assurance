package pairwise;

import java.io.IOException;

public class Pairwise {

    StringBuilder coveringArray = new StringBuilder();
    StringBuilder exhaustiveTests = new StringBuilder();
    int len = 2;
    int lineCount = 0;

    /**
     *  Main program driver
     * @param args  Arugments entered at the command line when running the program.
     */
    public static void main(String[] args) {
        Pairwise pairwise = new Pairwise();
        if (pairwise.checkArgLength(args)) {
            pairwise.len = args.length;

            pairwise.buildTests(args.length, new StringBuilder());
            pairwise.coveringArray = pairwise.buildCoveringArray(pairwise.lineCount);
            pairwise.outputCoveringArray();
        }

    }

    /**
     *  Checks the argument lengths
     * @param args  The args entered at the command prompt
     * @return  Trut if there are two or more arguments. Else false.
     */
    public boolean checkArgLength(String[] args) {
        if (args.length < 2) {
            System.out.println("Please enter at least two parameters!");
            return false;
        } else {
            return true;
        }
    }

    /**
     *  Prints out the covering array
     */
    public void outputCoveringArray() {
        System.out.println(coveringArray.toString());
    }


    /**
     *  Creates a stringbuilder to hold the exhaustive test array
     * @param depth The number of arguments entered
     * @param output    The stringbuilder we are building
     */
    public void buildTests(int depth, StringBuilder output) {
        String input = "10";

        if (depth == 0) {
            //System.out.println(output);
            String str = output.toString();
            exhaustiveTests.append(str);
            exhaustiveTests.append(System.getProperty("line.separator"));
            lineCount++;
        } else {
            for (int i = 0; i < input.length(); i++) {
                output.append(input.charAt(i));
                buildTests(depth - 1, output);
                output.deleteCharAt(output.length() - 1);
            }
        }

    }

    /**
     * Returns the covering array in a stringbuilder
     * @param lineCount The current Line
     * @return  Stringbuilder holding the covering Array
     */
    public StringBuilder buildCoveringArray(int lineCount) {

        if (len == 2) {
            return exhaustiveTests;
        } else {
            String[] splitTests = exhaustiveTests.toString().split("\\n");
            StringBuilder ca = new StringBuilder();
            int i = 0;
            int currLine = 1;
            while (currLine <= ((lineCount / 2) + 1)) {
                ca.append(splitTests[i]);
                i++;
                currLine++;
            }
            int z = 0;
            for (int j = 0; j < len; j++) {
                ca.append(0);
            }
            ca.append(System.getProperty("line.separator"));

            return ca;
        }

    }
}
