import java.io.*;
import java.util.*;
public class CaptureThemAll
{
	static class Item
	{
		public int row;
		public int column;
		public Item(int r, int c)
		{
			row = r;
			column = c;
		}
	}
	public int fastKnight(String knight, String rook, String queen)
	{
		int[] knightP = {0, parse(knight.charAt(1)), parse(knight.charAt(0))};
		int[] rookP = {0, parse(rook.charAt(1)), parse(rook.charAt(0))};
		int[] queenP = {0, parse(queen.charAt(1)), parse(queen.charAt(0))};
		Item root = new Item(knightP[1], knightP[2]);
		Item R = new Item(rookP[1], rookP[2]);
		Item Q = new Item(queenP[1], queenP[2]);
		return Math.min(getShortest(root,R) + getShortest(R, Q), getShortest(root, Q) + getShortest(Q,R));
	}
	static int getShortest(Item root, Item finalPos)
	{
		Queue<Item> States = new LinkedList<Item>();
		Map<Item, Integer> Depth = new HashMap<Item, Integer>();
		States.add(root);
		Depth.put(root, 0);
		boolean[][] visited = new boolean[9][9];
		visited[root.row][root.column] = true;
		
		while(!States.isEmpty())
		{
			Item curr = States.remove();
			if(curr.row == finalPos.row && curr.column == finalPos.column)
				return Depth.get(curr);
			process(States, Depth, curr, visited);
		}
		return 0;
	}
	static void process(Queue<Item> States, Map<Item, Integer> Depth, Item curr, boolean[][] visited)
	{
		int parentDepth = Depth.get(curr);
		int currentDepth = parentDepth + 1;
		int[] dx = {0, -2, -2, -1, -1, 2, 2, 1, 1};
		int[] dy = {0, -1, 1, -2, 2, -1, 1, -2, 2};
		int px, py;
		for(int i = 1; i <= 8; i++)
		{
			px = dx[i] + curr.row;
			py = dy[i] + curr.column;
			if(valid(px,py))
			{
				if(!visited[px][py])
				{
					Item f = new Item(px, py);
					States.add(f);
					Depth.put(f, currentDepth);
				}
			}
		}
	}
	static boolean valid(int a, int b)
	{
		if(a < 1 || a > 8 || b < 1 || b > 8)
			return false;
		return true;
	}
	static int parse(char c)
	{
		if(c >= 'a' && c <= 'z')
			return (int)(c - 'a' + 1);
		return (int)(c - '1' + 1);
	}
}
// Shortest path so that knight kill both queen and rook. -> Math.min( shortest(knight, rook), (rook, queen)), Math.min((shortest(knight, queen), (queen,rook))
