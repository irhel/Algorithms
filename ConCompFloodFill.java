public class ConCompFloodFill
{
	public static void main(String[] args)
	{
		//Given a matrix of characters find how many maximal sized subgraphs exist.
		//Or, identify the number of same colored regions in a matrix.
		String[] M = {"ACCCCACB", "AAAACACB", "BCAACCCA", "BBBBACAA", "BCCBAACC", "BBBBBAAC"};
		int R = M.length;
		int C = M[0].length();
		boolean[][] seen = new boolean[R][C];
		System.out.println(numberOfComponents(M, seen));
	}
	
	static int numberOfComponents(String[] M, boolean[][] seen)
	{
		int R = M.length;
		int C = M[0].length();
		int res = 0;
		for(int i = 0; i < R; i++)
		{
			for(int j = 0; j < C; j++)
			{
				if(!seen[i][j])
				{
					res++;
					char c = M[i].charAt(j);
					floodFill(M, seen, i, j, c);
				}
			}
		}
		return res;
	}
	static void floodFill(String[] M, boolean[][] seen, int i, int j, char c)
	{
		int R = M.length;
		int C = M[0].length();
		if(i < 0 || i >= R || j < 0 || j >= C || seen[i][j] || M[i].charAt(j)!=c)
			return;
		seen[i][j] = true;
		floodFill(M, seen, i + 1, j, c);
		floodFill(M, seen, i, j + 1, c);
		floodFill(M, seen, i - 1, j, c);
		floodFill(M, seen, i, j - 1, c);
	}
}