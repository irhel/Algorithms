/* WordIndex AC*/
import java.util.*;
import java.io.*;
class Main
{
	static int maxDepth = 5;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputString =  br.readLine();
		while(inputString != null)
		{
			if(!isValid(inputString))
				System.out.println(0);
			
			else
			{
				Queue <String> States = new LinkedList<String>();
				Map<String, Integer> Depth = new HashMap <String, Integer>();
				String root = "";
				States.add(root);
				Depth.put(root, 0);
				int count = 0;
				while(!States.isEmpty())
				{
					
					String curr = States.remove();
					if(curr.equalsIgnoreCase(inputString))
					{
						System.out.println(count);
						break;
					}
					process(States, Depth, curr);
					count++;
				}
			}
			inputString =  br.readLine();
		}
	}
	static void process(Queue<String> States, Map<String,Integer> Depth, String curr) //Maximum depth is 5.
	{
		int parentDepth = Depth.get(curr);
		int currentDepth = parentDepth + 1;
		int x = 0;
		int len = curr.length();
		if(len != 0)
			x = curr.charAt(len - 1) - 'a' + 1;
		else x = 0;
		for(int i = x; i < 26; i++)
		{
			String f = curr + (char)('a' + i);
			if(currentDepth > maxDepth)
				break;
			States.add(f);
			Depth.put(f, currentDepth);
		}
	}
	static boolean isValid(String word)
	{
		int n = word.length();
		for(int i = 0; i < n - 1; i++)
			if(word.charAt(i) >= word.charAt(i+1))
				return false;
		return true;
	}
}