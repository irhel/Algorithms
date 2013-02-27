import java.util.*;
import java.io.*;
public class Main
{
	static class Graph
	{
		public int vertices;
		public ArrayList<String>[] Adj;
		public boolean[] visited;
		public Map<String, Integer> T;
		public Graph(int Vertices)
		{
			this.vertices = Vertices;
			Adj = new ArrayList[Vertices + 1];
			visited = new boolean[Vertices + 1];
			for(int i = 1; i <= Vertices; i++)
				Adj[i] = new ArrayList<String>();
			T = new HashMap<String, Integer>();
		}
		public void addEdge(String A, String B)
		{
			int i = getIndex(A);
			int j = getIndex(B);
			Adj[i].add(B);
			Adj[j].add(A);
			T.put(A, i);
			T.put(B, i);
		}
		public int getIndex(String A)
		{
			for(String X : T.keySet())
			{
				if(X.compareTo(A) == 0)
					return T.get(A);
			}
			return T.size() + 1; 
		}
		public ArrayList<String> getAdjecant(String A) 
		{
			int x = getIndex(A);
			return Adj[x];
		}
		public void setVisited(String A, boolean x)
		{
			visited[getIndex(A)] = x;
		}
		public boolean getVisited(String A)
		{
			return visited[getIndex(A)];
		}
	}
	public static void main(String[] args) throws IOException
	{
		Graph G = new Graph(1000);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<String> Nodes = new LinkedHashSet<String>();
		String inputNumber = br.readLine();
		int N = Integer.parseInt(inputNumber);
		for(int i = 0; i < N; i++)
		{
			String blankLine = br.readLine();
			String inputString =  br.readLine();
			inputString = br.readLine();
			while(inputString != null)
			{
				String A = inputString.substring(0, 1);
				String B = inputString.substring(1, 2);
				G.addEdge(A, B);
				Nodes.add(A);
				Nodes.add(B);
				inputString = br.readLine();
			}
			System.out.println(numberOfComponents(G, Nodes));
			Nodes = new LinkedHashSet<String>();
			G = new Graph(1000);
		}
		System.exit(0);
	}
	static int numberOfComponents(Graph G, Set<String> Nodes)
	{
		int answer = 0;
		for(String N  : Nodes)
		{
			if(!G.getVisited(N))
			{
				++answer;
				dfs(G, N);
			}
		}
		return answer;
	}
	static void dfs(Graph G, String V)
	{
		if(G.getVisited(V))
			return;
		G.setVisited(V, true);
		ArrayList<String> Neighbors = G.getAdjecant(V);
		for(String Neighbor : Neighbors)
		{
			if(!G.getVisited(Neighbor))
				dfs(G, Neighbor);
		}
	}
}