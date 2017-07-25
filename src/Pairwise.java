import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Pairwise {

    public StringBuilder coveringArray;
    public StringBuilder exhaustiveTests = new StringBuilder();
    public int len = 2;
    public static int lineCount = 0;

    public static void main(String[] args) throws IOException {
        Pairwise pairwise = new Pairwise();
        if (pairwise.checkArgLength(args)) {
            pairwise.len = args.length;

            pairwise.buildTests(args.length, new StringBuilder());
            pairwise.coveringArray = pairwise.buildCoveringArray(lineCount);
            pairwise.outputCoveringArray();
        }

    }

    public boolean checkArgLength(String[] args) {
        if (args.length < 2) {
            System.out.println("Please enter at least two parameters!");
            return false;
        } else {
            return true;
        }
    }

    public void outputCoveringArray() {
        System.out.println(coveringArray.toString());
    }


    public void buildTests(int depth, StringBuilder output){
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