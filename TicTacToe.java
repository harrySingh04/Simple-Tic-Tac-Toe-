import java.util.Scanner;
public class TicTacToe {
	enum Player { X, O }
	public static void main(String[] args) {
		char playerTurn=' ';//Blank value
	  char[][] board = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};//two Dimensional array for Tic Tac Toe board
	  Player turn= Player.X;
	  boolean winner,noDraw = false;
	  System.out.println("Welcome to Tic Tac Toe Game!!!!");
	  System.out.println("The game will be Played between you and the Computer");
	  do
	  {
	  System.out.println("Do you want your turn to be first(Y/N):");
	  Scanner input = new Scanner(System.in);
	  if(input.hasNext())
	  playerTurn = input.next().charAt(0);
	  else		  
		  input.next();
	  
	  }while(!(playerTurn=='Y' || playerTurn=='N'));
	  for(int counter=0;counter<9;counter++)
	  {
	  if (playerTurn == 'Y')	  
	  {
		System.out.println("\nIt is your turn"); 
		turn=move(board,true,turn);
		display(board);
		winner = victoryCheck(board);
		if(winner)
		{
			System.out.print("\n\nYou are the Winner!Comgratulations");
			noDraw = true;
			break;
		}
			
		playerTurn = 'N';
		  
	  }
	  else
	  {
		  System.out.println("\nIt is  Computer's turn");
		  turn=move(board,false,turn);
		  display(board);
		  winner = victoryCheck(board);
			if(winner)
			{
				System.out.print("\n\nComputer has won..Sorry you have losed the game");
				noDraw = true;
				break;
			}
			
			playerTurn = 'Y';  
		  
	  }
	  
	  
	  } 
	  
	  if (!noDraw)
		  System.out.println("\nGame Result in a Draw!!!");
	}
	
	public static Player move(char[][] board, boolean playTurn,Player turn)
	{	
		Scanner input = new Scanner(System.in);
		boolean repeat=false,boardCheck;
		int rowNo=-1,columnNo=-1;
		if (playTurn)
		{
			do
			{
				do{
					
				
					System.out.println("\nPlease enter the row you want to occupy");	
						if (input.hasNextInt())
							rowNo = input.nextInt();		
						else
							input.next();
						
				  }while(rowNo<0|| rowNo>2);
			   
				do{
						
					System.out.println("\nPlease enter the Column you want to occupy");	
						if (input.hasNextInt())
							columnNo = input.nextInt();		
						else
							input.next();
						
				 }while(columnNo<0|| columnNo>2);
			
				
			boardCheck = check(board,rowNo,columnNo);
			if (boardCheck)
			{
				repeat = false;
			if (turn==Player.X)
			{
			board[rowNo][columnNo] = 'X';
			turn = Player.O;
			
			}
			else
			{
				board[rowNo][columnNo] = 'O';
				turn = Player.X;
			}
			}
			else
			{
				System.out.println("This row and column is already occupied."
								+ "Please tell the different row and column.");
				
				
				repeat = true;
				
			}
						
			}while(repeat);
			
		}
		else
		{	
			
			do
			{
			rowNo = (int)(Math.random()*10);
			columnNo = (int)(Math.random()*10);
			
			while (!(rowNo==0 || rowNo ==1 || rowNo==2))
			{
				rowNo = (int)(Math.random()*10);
				
			}
			while (!(columnNo==0 || columnNo ==1 || columnNo==2))
			{
				columnNo = (int)(Math.random()*10);
				
			}
			
			boardCheck = check(board,rowNo,columnNo);
			if(boardCheck)
			{
				repeat = false;
				if (turn==Player.X)
				{
				board[rowNo][columnNo] = 'X';
				turn = Player.O;
				
				}
				else
				{
					board[rowNo][columnNo] = 'O';
					turn = Player.X;
				}	
				
			}
			else
				repeat = true;
			
			}while(repeat);
}
		return turn;	
		}
		
	
	
	public static boolean check(char[][] board,int row ,int column)
	{
		if (board[row][column]== ' ')
		{
			return true;
		}
		else
			return false;
		
		
	}
	
	public static void display(char[][] board)
	{
		System.out.println("\n\nThe Board Displays now as:");
		for (int counter=0;counter<board.length;counter++)
		{
			System.out.println();
			for(int next = 0;next<board.length;next++)
			{
				
				System.out.printf("%c\t",board[counter][next]);
			}
		}
	}
	public static boolean victoryCheck(char[][] board)
	{
		boolean victory=false;
		int row,column;
		
		while(!victory)
		{
		
//Checking if any single row cotaining the same set of elements.	
		for(int counter=0;counter<board.length;counter++)
		{
			
			victory = rowCheck(board,counter,0);
			if (victory)			
				break;		
		}
		
	if(victory)
		continue;
//Checking if any single column containing the same set of elements.	
	for(int counter=0;counter<board.length;counter++)
	{
		
		victory = columnCheck(board,0,counter);
		if (victory)			
			break;		
	}
	if(victory)
		continue;
	
	row=0;
	column=0;
//Check for the Right hand side Diagonal combination	
	if((board[row][column]=='X' && board[row+1][column+1]=='X' && board[row+2][column+2]=='X')
	|| (board[row][column]=='O' && board[row+1][column+1]=='O' && board[row+2][column+2]=='O'))
	{
		victory=true;
		continue;
		
	}
	
 column=2;
//Check for the Left hand side Diagonal Combination	
	if((board[row][column]=='X' && board[row+1][column-1]=='X' && board[row+2][column-2]=='X')
			|| (board[row][column]=='O' && board[row+1][column-1]=='O' && board[row+2][column-2]=='O'))
			{
				victory=true;
				continue;
				
			}
	break;
		}
		
		
	return victory;	
		}
	
	public static boolean rowCheck(char[][] board,int row,int column)
	{
		
		if((board[row][column]== 'X' && board[row][column+1]=='X' && board[row][column+2]=='X' )|| 
		   (board[row][column]== 'O' && board[row][column+1]=='O' && board[row][column+2]=='O' ))
		{
			return true;
			
		}
		else
			
		return false;
	}
	public static boolean columnCheck(char[][] board,int row,int column)
	{
		
		if((board[row][column]== 'X' && board[row+1][column]=='X' && board[row+2][column]=='X' )|| 
		   (board[row][column]== 'O' && board[row+1][column]=='O' && board[row+2][column]=='O' ))
		{
			return true;
			
		}
		else
			
		return false;
	}	
	}
	
	
