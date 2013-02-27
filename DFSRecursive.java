import java.util.*;
public class Test
{
	static class Graph
	{
		public int vertices;
		public ArrayList<String>[] Adj;
		public boolean[] visited;
		public Map<String, Integer> T;

		public Graph(int Vertices) //Graph constructor.
		{
			this.vertices = Vertices;
			Adj = new ArrayList[Vertices + 1];
			visited = new boolean[Vertices + 1];
			for(int i = 1; i <= Vertices; i++)
				Adj[i] = new ArrayList<String>();
			T = new HashMap<String, Integer>();
		}
		public void addEdge(String A, String B) //Given the names of two nodes, set an edge between them.
		{
			int i = getIndex(A);
			Adj[i].add(B);
			T.put(A, i);
			int j = getIndex(B);
			Adj[j].add(A);
			T.put(B, j);
 		}
		public int getIndex(String A) //Find the respective index of the name of the node in the adjecancy list.
		{
			for(String X : T.keySet())
			{
				if(X.compareTo(A) == 0)
					return T.get(A);
			}
			return T.size() + 1; 
		}
		public ArrayList<String> getAdjecant(String A) //Get the adjecant nodes of a given node name.
		{
			int x = getIndex(A);
			return Adj[x];
		}
		public void setVisited(String A, boolean x) //Given the name of the node in the graph, set it as visited in order to avoid infinite loops.
		{
			visited[getIndex(A)] = x;
		}
		public boolean isVisited(String A) //Given the name of the node in the graph, find whether it is visited(via the BFS traversal).
		{
			return visited[getIndex(A)];
		}
	}
	public static void main(String[] args)
	{
		Graph G = new Graph(1000);
		G.addEdge("1", "2");
		G.addEdge("1", "3");
		G.addEdge("2", "4");
		G.addEdge("2", "5");
		G.addEdge("3", "6");
		G.addEdge("3", "7");
		G.addEdge("5", "7");
		String V = "1"; //The beginning out node, out of which we will proceed with BFS traversal of the graph.
		
		dfs(G, V);
	}
	static void dfs(Graph G, String V)
	{
		G.setVisited(V, true);
		System.out.println(V);
		ArrayList<String> Adjecant = G.getAdjecant(V);
		Iterator<String> it = Adjecant.iterator();
		while(it.hasNext())
		{
			String A = it.next();
			if(!G.isVisited(A))
				dfs(G, A);
		}
	}
	static void process(Stack<String> States, Graph G, String curr)
	{
		ArrayList<String> Adjecant = G.getAdjecant(curr);
		Collections.reverse(Adjecant); //We do this to get the result in proper terms.
		Iterator<String> it = Adjecant.iterator();
		while(it.hasNext())
		{
			String node = it.next();
			if(!G.isVisited(node))
				States.add(node);
		}
	}
	//Can be used for identifying all the nodes in a connected component.
}