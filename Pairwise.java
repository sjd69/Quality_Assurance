
public class Pairwise {
	public static void main(String[] args) {
		Pairwise pairwise = new Pairwise();
		
		if (pairwise.checkArgLength(args) && pairwise.checkParamTypes(args)) {
			
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
}


