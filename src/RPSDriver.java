import java.util.Scanner;

public class RPSDriver
{
	public static void main(String args[])
	{
		RPSGame game = new RPSGame();
		char userContinue = 'y';		
		char userMove;
		Scanner scan = new Scanner(System.in);
		
		String winner = "";
		boolean success;		
		
		System.out.println("Welcome to a game of Rock-Paper-Scissors");				
		do
		{
			int compMove = game.generateComputerPlay();
			System.out.println("To play enter r for Rock , p for Paper, and s for scissors");
			
			do
			{
				userMove = scan.next().trim().toLowerCase().charAt(0);
				System.out.print("User Move: ");
				
				success = false;
				if (userMove == 'r')				
				{
					System.out.println("Rock");
					winner = game.findWinner(0, compMove);
				}				
				else if (userMove == 'p')
				{
					System.out.println("Paper");
					winner = game.findWinner(1, compMove);
				}
				else if (userMove == 's')
				{
					System.out.println("Scissors");
					winner = game.findWinner(2, compMove);
				}
			
				else
				{
					System.out.println("Invalid character input, enter a proper character");
					success = true;
				}			
			}while(success);
			
			if(compMove == 0)
				System.out.println("Cpu Move : Rock\n" + winner);
			else if (compMove == 1)
				System.out.println("Cpu Move : Paper\n" + winner);			
			else if (compMove == 2)
				System.out.println("Cpu Move : Scissors\n" + winner);
			
			System.out.println(game.toString());
			System.out.println("Do you want to play another round? (Enter y to confirm)");	
			userContinue = scan.next().trim().toLowerCase().charAt(0);
			
		}while (userContinue == 'y');
		
		System.out.println("Alright, See ya!");
		
	}
	

}
