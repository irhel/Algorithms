import java.util.*;
public class NQueens
{
	static int N  = 10;
	public static void main(String[] args)
	{
		int[] Q = new int[N];
		Arrays.fill(Q, -1);
		search(Q, 0);
	}
	static boolean safe(int row, int column, int[] Q)
	{
		for(int i = 0; i < row; i++)
			if(i == row || Q[i] == column || Math.abs(row - i) == Math.abs(column - Q[i]))
				return false;
		return true;
	}
	static void search(int[] Q, int R)
	{
		if(R == N)
			printBoard(Q);
		else
		{
			for(int i = 0; i < N; i++)
			{
				if(safe(R, i, Q))
				{
					Q[R] = i;
					search(Q, R + 1);
				}
				Q[R] = -1;
			}
		}
	}
	static void printBoard(int[] Q)
	{
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				if(j == Q[i] && Q[i] >= 0)
					System.out.print("Q" + " ");
				else System.out.print("*" + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}