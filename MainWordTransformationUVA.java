/* Word Transformation UVA , AC. */

import java.util.*;
import java.io.*;
class Main
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<String> dict = new ArrayList<String>();
		int N = Integer.parseInt(br.readLine());
		String newLine = br.readLine();
		while(N-- != 0)
		{
			String inputString = br.readLine();
			while(inputString.compareTo("*") != 0 && inputString!=null)
			{ 
				dict.add(inputString);
				inputString = br.readLine();
			}
			String pair = br.readLine();
			while(pair != null && !pair.equals(""))
			{
				List<String> T = new ArrayList<String>(dict);
				String[] pairs = pair.split(" ");
				String word = new String(pairs[0]);
				String end = new String(pairs[1]);
				Queue<String> States = new LinkedList<String>();
				Map<String, Integer> Depth = new HashMap<String, Integer>();
				States.add(word);
				Depth.put(word, 0);
				while(!States.isEmpty())
				{
					String curr = States.remove();
					if(curr.compareTo(end) == 0)
					{
						System.out.println(pair +" " + Depth.get(curr));
						break;
					}
					process(States, Depth, T, curr);
				}
				pair = br.readLine();
			}
			if(N != 0)
				System.out.println();
		}
		System.exit(0);
	}

	static void process(Queue<String> States, Map<String, Integer> Depth, List<String> dict, String curr)
	{
		int parentDepth = Depth.get(curr);
		int currentDepth= parentDepth + 1;
		for(int i = 0; i < dict.size(); i++)
		{
			String f = new String(dict.get(i));
			if(oneDifference(curr, f))
			{
				dict.remove(i);
				i--;
				States.add(f);
				Depth.put(f, currentDepth);
			}
		}
		dict.remove(curr);
	}
	static boolean oneDifference(String word1, String word2) //If word2 can be obtained by changing a single character in word1 then true.
	{
		if(word1.length() != word2.length())
			return false;
		if(word1.compareTo(word2) == 0)
			return false;
		char[] C1 = word1.toCharArray();
		char[] C2 = word2.toCharArray();
		int len = C1.length;
		for(int i = 0; i < len; i++)
			if(C1[i]!=C2[i])
			{
				C1[i] = C2[i];
				break;
			}
		String S1 = new String(C1);
		String S2 = new String(C2);
		int d = S1.compareTo(S2);
		if(d == 0)
			return true;
		return false;
	}
}