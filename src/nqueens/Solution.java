package nqueens;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Victor
 *	for my implementation
 *				 -1 represents a queen
 *				  0 empty cell
 *				  other # number of queens attacking this index
 */
public class Solution {

	private int N;
	private int [][] board;
	private List<List<String>> listOfBoards;
	private int midPoint;
	private boolean even,midFlag;
	
	public List<List<String>> solveNQueens(int n){
		N = n;
		listOfBoards = new ArrayList<List<String>>();
		if(n == 0 || n == 2 || n == 3)
			return listOfBoards;
		if(n == 1){
			List<String> list = new ArrayList<String>(1);
			list.add("Q");
			listOfBoards.add(list);
			return listOfBoards;
		}
		
		if(N%2 == 0){
			midPoint = N/2-1;
			even = true;
		}
		else {
			midPoint = N/2;
			even = false;
		}
		midFlag = false;
		//initialize board and returned list
		board = new int[n][n];
		
		//begin recursion
		if(even)
			recursiveEvenNQueens(0);
		else
			recursiveNQueens(0);
		
		return listOfBoards;
	}
	
	//solution for when N input is even number
	private void recursiveEvenNQueens(int k){
		//check out of bounds end case
		if(k >= N)
			return;
		if(k == 0){
			for(int i = 0; i <= midPoint; ++i){
				evenInnerLoopLogic(k, i);
			}
		}else {
			//traverse through each index of current row
			for(int i = 0; i < N; i++){
				evenInnerLoopLogic(k,i);
			}
		}
	}
	
	private void evenInnerLoopLogic(int k, int i){

		//can a queen be added?
		if(board[k][i] == 0){
			addQueen(k,i);
			
			//if we just added to final row then add solution to list
			if(k == N-1 ){
				addMirroredSolution();
			}
			else
			{
				//no end case reached so recurse to next row
				recursiveNQueens(k+1);
			}
			
			//backtrack remove added queen
			removeQueen(k,i);
		}
		
		
	}
	
	//k is current row
	//end cases are if k out of bounds or we add a queen to final row
	private void recursiveNQueens(int k){
		//check out of bounds end case
		if(k >= N)
			return;
		if(k == 0){
			for(int i = 0; i <= midPoint; ++i){
				if(i == midPoint)
					midFlag = true;
				innerLoopLogic(k, i);
			}
		}else {
			//traverse through each index of current row
			for(int i = 0; i < N; i++){
				innerLoopLogic(k,i);
			}
		}
	}
	
	private void innerLoopLogic(int k, int i){

		//can a queen be added?
		if(board[k][i] == 0){
			addQueen(k,i);
			
			//if we just added to final row then add solution to list
			if(k == N-1 ){
				if(!midFlag){
					addMirroredSolution();
				} else {
					addSolution();
				}
			}
			else
			{
				//no end case reached so recurse to next row
				recursiveNQueens(k+1);
			}
			
			//backtrack remove added queen
			removeQueen(k,i);
		}
		
		
	}
	
	
	private void addQueen(int i, int k){
		board[i][k] = -1;
		updateNeighbors(i,k,1);
	}
	
	
	private void removeQueen(int i, int k){
		board[i][k] = 0;
		updateNeighbors(i,k,-1);
	}
	
	private void updateNeighbors(int i, int j, int updateVal){
		
		//left top neighbors
		for(int m = i-1, n = j-1; m >=0 && n >= 0; m--,n--)
			{board[m][n] += updateVal;}
		 
		//top right neighbors
		for(int m = i-1, n=j+1; m >=0 && n<N; --m,++n)
			{board[m][n] += updateVal;}
				
		//bottom left neighbors
		for(int m = i+1, n=j-1; m<N && n >=0; m++,n--)
			{board[m][n] += updateVal;}
		
		//bottom right neighbors
		for(int m=i+1,n=j+1; m < N && n < N; m++, n++)
			{board[m][n] += updateVal;}
		
		//left neighbors
		for(int k = 0; k < j ; ++k)
			{board[i][k] += updateVal;}
		
		//right neighbors
		for(int k = j+1; k < N; ++k)
			{board[i][k] += updateVal;}
		
		//top neighbors
		for(int k = 0; k < i; ++k)
			{board[k][j] += updateVal;}
		
		//bottom neighbors
		for(int k = i+1; k < N; ++k)
			{board[k][j] += updateVal;}
	
	}
	
	private void addSolution(){
		List<String> currSolution = new ArrayList<String>(N);
		
		for(int i = 0 ; i < N; ++i){
			String row = "";
			for(int j = 0; j < N; ++j){
				if(board[i][j] == -1)
					row += 'Q';
				else 
					row += ".";
			}
			currSolution.add(row);
		}
		listOfBoards.add(currSolution);
	}
	
	private void addMirroredSolution(){
		List<String> currSolution1 = new ArrayList<String>(N);
		List<String> currSolution2 = new ArrayList<String>(N);
		
		for(int i = 0 ; i < N; ++i){
			String row = "";
			String reversedRow = "";
			for(int j = 0; j < N; ++j){
				if(board[i][j] == -1){
					reversedRow = 'Q' + reversedRow;
					row += 'Q';
				}
				else {
					reversedRow = "." + reversedRow;
					row += ".";
				}
			}
			currSolution1.add(row);
			currSolution2.add(reversedRow);
		}
		listOfBoards.add(currSolution1);
		listOfBoards.add(currSolution2);
	}
}
