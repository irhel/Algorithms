import java.util.*;
import java.io.*;
public class BFSDepth
{
	static int maxDepth = 3;
	public static void main(String[] args)
	{
		Queue<String> States = new LinkedList<String>();
		Map<String, Integer> M = new HashMap<String, Integer>();
		String root = "";
		States.add(root);
		while(!States.isEmpty())
		{
			String curr = States.remove();
			System.out.println(curr);
			M.put(root, 0);
			process(States, M, curr);
		}
	}
	static void process(Queue<String> States, Map<String, Integer> M, String curr)
	{
		int parentDepth = M.get(curr);
		int currentDepth = parentDepth + 1;
		for(int i = 0; i < 3; i++)
		{
			String f = curr + (char)(i + 'a');
			if(currentDepth > maxDepth)
				break;
			States.add(f);
			M.put(f, currentDepth);
		}
	}
}