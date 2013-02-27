import java.util.*;
import java.io.*;
class Main
{
	static class Item
	{
		public int[] Pos = new int[3];
		public int[] Ban = new int[3];
		public Item(int[] P, int [] B)
		{
			for(int i = 0; i < 3; i++)
			{
				Pos[i] = P[i];
				Ban[i] = B[i];
			}
		}
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Item> States = new LinkedList<Item>();
		Map<Item, Integer> Depth = new HashMap<Item, Integer>();
		String inputString = br.readLine();
		while(inputString != null)
		{
			String[] tok = inputString.split(" ");
			String a = tok[0];
			String b = tok[1];
			
			
			int[] beginning = new int[3]; //Beginning state of the knight.
			int[] end = new int[3]; //End position.
			beginning[1] = parseString(a.charAt(0));
			beginning[2] = parseString(a.charAt(1));
			end[1] = parseString(b.charAt(0));
			end[2] = parseString(b.charAt(1));

			Item root = new Item(beginning, new int[]{0,0,0});
			States.add(root);
			Depth.put(root,0);
			while(!States.isEmpty())
			{
				Item curr = States.remove();
				if(curr.Pos[1] == end[1] && curr.Pos[2] == end[2])
				{
					System.out.println("To get from " + a + " to " + b + " takes " + Depth.get(curr) + " knight moves.");
					break;
				}
				process(States, Depth, curr);
			}
			States.clear();
			Depth.clear();
			inputString = br.readLine();
		}
		System.exit(0);
	}
	static void process(Queue<Item> States, Map<Item, Integer> Depth, Item curr)
	{
		int parentDepth = Depth.get(curr);
		int currentDepth = parentDepth + 1;
		int[] pos = new int[3];
		int[] dx = {0, -2, 2, -2, 2, -1, 1, -1, 1};
		int[] dy = {0, 1, -1, -1, 1, 2, -2, -2, 2};
		int px = 0, py = 0;
		int[] newPos = new int[3];
		for(int i = 1; i <= 8; i++)
		{
			if(curr.Ban[1] != dx[i] && curr.Ban[2] != dy[i]) // Safe move doesn't result in infintie loop
			{
				px = curr.Pos[1] + dx[i];
				py = curr.Pos[2] + dy[i];
				if(valid(px, py))
				{
					newPos[1] = px;
					newPos[2] = py;
					Item f = new Item(newPos, new int[]{0,-px,-py});
					States.add(f);
					Depth.put(f, currentDepth);
				}
			}
		}
	}
	static boolean valid(int x, int y)
	{
		if(x < 1 || x > 8 || y < 1 || y > 8)
			return false;
		return true;
	}
	static int parseString(char input)
	{
		if(input >= 'a' && input <= 'z')
			return (int)(input - 'a' + 1);
		return (int)(input - '1' + 1);
	}
}
/*
@BUG: Will go into infinite loop. Fix it so that if moved to (x+b)(y+c) where a E (1,2,-1,-2) and b E (1,2,-1,-2) the next move can't be (x-b)(y-c).
*/