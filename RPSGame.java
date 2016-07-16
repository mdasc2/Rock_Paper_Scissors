import java.util.Random;
/*
 * Andrew Chow
 * CS 110B
 * 7/14/2016
 * This class is a simple rock-paper-scissors game that finds out who wins the game, 
 * tallies wins and losses and allows the user to update as they choose.
 */

public class RPSGame
{

	private int cpuWins;
	private int userWins;
	private int tieCounter;
	private double betMoney = 0;
	private double betAmount = 0;
	
	static final int Rock = 0;
	static final int Paper = 1;
	static final int Scissors = 2;
	Random rand = new Random();
	
	public RPSGame ()
	{
		cpuWins = 0;
		userWins = 0;
		tieCounter = 0;	
	}
	
	public RPSGame (int betMoney, int betAmount)
	{
		cpuWins = 0;
		userWins = 0;
		tieCounter = 0;
		
		if(betMoney > 0)
			this.betMoney = betMoney;
		else
			betMoney = 0;
		
		if (betAmount > 0)
			this.betAmount = betAmount;
		else 
			betAmount = 0;
	}
	public double getBetMoney()
	{
		return betMoney;
	}
	public void setBetMoney(double betMoney)
	{
		this.betMoney = betMoney;
	}
	
	public double getBetAmount()
	{
		return betAmount;
	}
	public void setBetAmount(double betAmount)
	{
		this.betAmount = betAmount;
	}	
	
	public int getCWins()
	{
		return cpuWins;
	}
	
	public void setCWins(int cpuWins)
	{
		this.cpuWins = cpuWins;
	}
	
	public int getUWins()
	{
		return userWins;
	}
	
	public void setUWins(int userWins)
	{
		this.userWins = userWins;
	}
	
	public int getTies()
	{
		return tieCounter;
	}
	public void setTies(int tieCounter)
	{
		this.tieCounter = tieCounter;
	}
	
	// Creates a random integer from 0-2, equalling the constants of rock paper scissors above
	public int generateComputerPlay()
	{
		return rand.nextInt(3);		
	}
	
	// Subtracts or adds betamount to betmoney
	public void betResult(String winner)
	{
		if( winner.equals("Computer Wins!"))
		{
			betMoney -= betAmount;
		}
			
		else if (winner.equals("User Wins!"))
		{
			betMoney += betAmount;
		}
		else
		{
			
		}
	}
	
	// Takes in two int values and finds the winner of rockpaper scissors, returns a string detailing the winner of a round 
	public String findWinner(int userMove, int cpuMove)
	{
		String winner = "";
		if(userMove == Rock)
		{
			if(cpuMove == Rock)
			{
				tieCounter++;
				winner = "Tie!";
			}
			if(cpuMove == Paper)
			{
				cpuWins++;
				winner = "Computer Wins!";
			}
			if(cpuMove == Scissors)
			{
				userWins++;
				winner = "User Wins!";
			}
		}				
		
		if(userMove == Paper)
		{
			if(cpuMove == Rock)
			{
				userWins++;
				winner = "User Wins!";
			}
			if(cpuMove == Paper)
			{
				tieCounter++;
				winner = "Tie!";
			}
			if(cpuMove == Scissors)
			{
				cpuWins++;	
				winner = "Computer Wins!";
			}
		}
			
		if(userMove == Scissors)
		{
			if(cpuMove == Rock)
			{	
				cpuWins++;
				winner = "Computer Wins!";
			}
			if(cpuMove == Paper)
			{
				userWins++;
				winner = "User Wins!";
			}
			if(cpuMove == Scissors)
			{
				tieCounter++;
				winner = "Tie!";
			}
		}
		return winner;
	}
	
}


