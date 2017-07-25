package ass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Pairwise {

    public List<List<Boolean>> coveringArray;
    public int len = 0;
	private static final String FILENAME = "ass.txt";
	private static BufferedWriter bw;
	private static FileWriter fw;
	private static int lineCount;
	
    public static void main(String[] args) throws IOException {
        Pairwise pairwise = new Pairwise();
        pairwise.len = args.length;

        bw = null;
		fw = null;
		fw = new FileWriter(FILENAME);
		bw = new BufferedWriter(fw);

        lineCount = 0;
		
        pairwise.generateCombination(args.length, new StringBuffer());
        
    	bw.close();
		fw.close();
		
		BufferedReader br = null;
		FileReader fr = null;
		
		fr = new FileReader(FILENAME);
		br = new BufferedReader(fr);
		
		
		String sCurrentLine;
		int currLine = 1;
		
		while ((sCurrentLine = br.readLine()) != null && currLine <= ((lineCount/2) + 1)) 
		{
			System.out.println(sCurrentLine);
			currLine++;
		}
		
		char z = '0';
		
		StringBuffer zeroLine = new StringBuffer();
		for (int i = 0; i < args.length; i++)
		{
			zeroLine.append(z);
		}
		
		System.out.println(zeroLine);
		
		br.close();	
		fr.close();

        /*if (pairwise.checkArgLength(args) && pairwise.checkParamTypes(args)) {
            pairwise.len = 0;
            pairwise.coveringArray = new ArrayList<>();
            while (!pairwise.hasAllCombinations()) {

            }
        }*/
    }

    public boolean checkArgLength(String[] args) {
        if (args.length < 2) {
            System.out.println("Please enter at least two parameters!");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkParamTypes(String[] args) {
        try {
            for (int i = 0; i < args.length; i++) {
                if ((Integer.parseInt(args[i]) != 0 && Integer.parseInt(args[i]) != 1)) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("INVALID INPUT. ENTER 1 OR 0.");
            return false;
        }

    }

    
    
    	
    
    public void generateCombination(int depth, StringBuffer output) throws IOException {
        String input = "10";
        
        
		
        if (depth == 0) {
            //System.out.println(output);
            String str = output.toString();
            bw.write(str);
            bw.newLine();
            lineCount++;
        } else {
            for (int i = 0; i < input.length(); i++) {
                output.append(input.charAt(i));
                generateCombination(depth - 1, output);
                output.deleteCharAt(output.length() - 1);
            }
        }

       /* List<Boolean[]> master = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, len); i++) {
            String bin = Integer.toBinaryString(i);
            System.out.println(bin);
            while (bin.length() < len)
                bin = "0" + bin;
            char[] chars = bin.toCharArray();
            System.out.println(Arrays.toString(chars));

            int j = 0;
            boolean exists = false;
            while (master.get(i) != null) {

            }

        }*/

    }

}