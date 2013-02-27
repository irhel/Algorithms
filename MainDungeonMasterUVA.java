import java.util.*;
import java.io.*;
class Main
{
	static class Item 
	{
		public int layer;
		public int row;
		public int column;
		public Item(int l, int r, int c)
		{
			layer = l;
			row = r;
			column = c;
		}
	}
	public static void main(String[] args) throws IOException
	{
		int L, R, C;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputString = br.readLine();
		String[] tokens = inputString.split(" ");
		L = Integer.parseInt(tokens[0]);
		R = Integer.parseInt(tokens[1]);
		C = Integer.parseInt(tokens[2]);
		String[][] Maze = new String[L+1][R+1];
		int initialLayer, initialRow, initialColumn, endLayer, endRow, endColumn;
		initialLayer = initialRow = initialColumn= endLayer = endRow = endColumn = 0;
		String newLine;
		while(L != 0 && R != 0 && C != 0)
		{
			Maze = new String[L+1][R+1];
			for(int l = 1; l <= L; l++)
			{
				for(int r = 1; r <= R; r++)
				{
					inputString = " " + br.readLine();
					Maze[l][r] = new String(inputString);
					for(int c = 1; c <= C; c++)
					{
						if(inputString.charAt(c) == 'S')
						{
							initialLayer = l;
							initialRow = r;
							initialColumn = c;
						}
						if(inputString.charAt(c) == 'E')
						{
							endLayer = l;
							endRow = r;
							endColumn = c;
						}
					}
				}
				newLine = br.readLine();
			}
			Item Root = new Item(initialLayer, initialRow, initialColumn);
			Queue<Item> States = new LinkedList<Item>();
			Map<Item, Integer> Depth = new HashMap<Item, Integer>();
			States.add(Root);
			Depth.put(Root, 0);
			boolean[][][] Visited = new boolean[L+1][R+1][C+1];
			Visited[initialLayer][initialRow][initialColumn] = true;
			int result = 0;
			while(!States.isEmpty())
			{
				Item curr = States.remove();
				if(curr.layer == endLayer && curr.row == endRow && curr.column == endColumn)
				{
					result = Depth.get(curr);
					System.out.println("Escaped in " + result + " minute(s).");
					break;
				}
				process(Maze, Depth, Visited, States, curr);
			}
			if(result == 0)
				System.out.println("Trapped!");
			inputString = br.readLine();
			tokens = inputString.split(" ");
			L = Integer.parseInt(tokens[0]);
			R = Integer.parseInt(tokens[1]);
			C = Integer.parseInt(tokens[2]);
		}
		System.exit(0);
	}
	static void process(String[][] Maze, Map<Item, Integer> Depth, boolean[][][] Visited, Queue<Item> States, Item curr)
	{
		int L = Visited.length - 1;
		int R = Visited[0].length - 1;
		int C = Visited[0][0].length - 1;
		int parentDepth = Depth.get(curr);
		int currentDepth = 1 + parentDepth;
		int[] dl = {0, 0, 0, 0, 0, 1, -1};
		int[] dr = {0, 1, -1, 0, 0, 0, 0};
		int[] dc = {0, 0, 0, 1, -1, 0, 0};
		int pl, pr, pc;
		pl = pr = pc = 0;
		for(int i = 1; i <= 6; i++)
		{
			pl = curr.layer + dl[i];
			pr = curr.row + dr[i];
			pc = curr.column + dc[i];
			//A move to be accomplished must be within the constraints and it musn't be a '#' and visited.
			if(valid(pl, pr, pc, L, R, C))
			{
				if(!Visited[pl][pr][pc])//Not visited.
				{
					if(Maze[pl][pr].charAt(pc) != '#') //Not a rock.
					{
						Item f = new Item(pl, pr, pc);
						Visited[pl][pr][pc] = true;
						Depth.put(f, currentDepth);
						States.add(f);
					}
				}
			}
		}
	}
	static boolean valid(int pl, int pr, int pc, int L, int R, int C)
	{
		if(pl < 1 || pl > L)
			return false;
		if(pr < 1 || pr > R)
			return false;
		if(pc < 1 || pc > C)
			return false;
		return true;
	}
}