package nqueens;

import java.util.*;

//adding erroneous table to output. index checked to see if its current middle 
//is in the wrong row. maybe add a boolean flag .

public class driver {
	public static void main( String []args){
		
		Scanner kb = new Scanner(System.in);
		boolean running = true;
		String [][] results = null;
		
		while(running){
			System.out.println("Enter number of queens range 1 to 9 <q to quit>: ");
			String input = kb.nextLine();
			if(input.equals("q"))
				running = false;
			
			else{
				try{
					int n = Integer.parseInt(input);
					if(n<1 || n > 9)
						throw new NumberFormatException();
					
					List<List<String>> l = new Solution().solveNQueens(n);
					results = new String[n][l.size()];
					for(int i = 0; i < l.size(); ++i)
					{
						List<String> row = l.get(i);
						for(int j=0; j < row.size(); ++j){
							results[j][i] = row.get(j);
						}
					}
					printResults(results);
				}catch(NumberFormatException e){
					System.out.println("invalid input");
				}
				
			}
			
			
		}
		kb.close();
		
	}
	
	//print results as boards 
	public static void printResults(String [][] results){
		for(int i = 0; i < results.length; i++){
			for(int j = 0; j < results[i].length; ++j){
				System.out.print(results[i][j] + "  ");
			}
			System.out.println("");
		}
	}
}
