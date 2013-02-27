import java.util.*;
import java.io.*;
public class ANodeTooFar 
{
	static class InputReader
	{
		private BufferedReader reader;
		private StringTokenizer tokenizer;
		public InputReader()
		{
			reader = new BufferedReader(new InputStreamReader(System.in));
			tokenizer = null;
		}
		public String next()
		{
			while(tokenizer == null || !tokenizer.hasMoreElements())
			{
				try
				{
					tokenizer = new StringTokenizer(reader.readLine());
				}
				catch(IOException e)
				{
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}
		public int nextInt()
		{
			return Integer.parseInt(next());
		}
	}
	public static void main(String[] args)
	{
		Set<Integer> S = new LinkedHashSet<Integer>(); //This babe, doesn't allow duplicates.
		int caseX = 1;
		boolean[][] G = new boolean[31][31];
		InputReader in = new InputReader();
		int N = in.nextInt();
		while(N != 0)
		{
			
			for(boolean[] X : G)
				Arrays.fill(X, false);
			for(int i = 0; i < N; i++)
			{
				int x = in.nextInt();
				int y = in.nextInt();
				if(x <= 1 || x >= 30)
					x%=31;
				if(y <= 1 || y >= 30)
					y%=31;
				S.add(x);
				S.add(y);
				G[x][y] = true;
				G[y][x] = true;
			
			}
			
			int root;
			int TTL, troot = 0;
			root = in.nextInt();
			troot = root;
			if(root <= 1 || root >= 30)
			{
				root%=31;
			}
			TTL = in.nextInt();
			
			while(root != 0 || TTL != 0)
			{
				Set<Integer> X = new LinkedHashSet<Integer>(S);
				int size = getResult(root, TTL, G, X);
				System.out.println("Case " + caseX + ": " + size + " nodes not reachable from node " + troot + " with TTL = " + TTL + ".");
				caseX++;
				root = in.nextInt();
				TTL = in.nextInt();
				troot = root;
				if(root <= 1 || root >= 30)
				{
					root%=31;
				}
			}
			N = in.nextInt();
			S.clear();
		}
		System.exit(0);
		
	}
	static int getResult(int root, int TTL, boolean[][] G, Set<Integer> S)
	{
		
		Map<Integer, Integer> M = new HashMap<Integer, Integer>();
		Queue<Integer> States = new LinkedList<Integer>();
		States.add(root);
		M.put(root, 0);
		while(!States.isEmpty())
		{
			int curr = (Integer) States.remove();
			S.remove(curr);
			process(S, M, States, TTL, G, curr);
		}
		return S.size();
	}
	static void process(Set<Integer> S, Map<Integer, Integer> M, Queue<Integer> States, int TTL, boolean[][] G, int curr) //Will generate states until depth TTL.
	{
		int parentDepth = (Integer) M.get(curr);
		int currentDepth = parentDepth + 1;
		for(int i = 1; i <= 30; i++)
		{
			if(G[curr][i])
			{
				int newState = i;
				if(currentDepth > TTL)
					break;
				States.add(newState);
				M.put(newState, currentDepth);
			}
		}
	}
}