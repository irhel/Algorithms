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
		public void setVisited(String A) //Given the name of the node in the graph, set it as visited in order to avoid infinite loops.
		{
			visited[getIndex(A)] = true;
		}
		public boolean isVisited(String A) //Given the name of the node in the graph, find whether it is visited(via the BFS traversal).
		{
			return visited[getIndex(A)];
		}
	}
	public static void main(String[] args)
	{
		Graph G = new Graph(1000);
		G.addEdge("A", "B");
		G.addEdge("A", "C");
		G.addEdge("B", "D");
		G.addEdge("B", "E");
		G.addEdge("C", "F");
		G.addEdge("C", "G");
		
		String node = "A"; //The beginning out node, out of which we will proceed with BFS traversal of the graph.
		Queue<String> States = new LinkedList<String>();
		States.add(node);
		while(!States.isEmpty())
		{
			String curr = States.remove();
			System.out.println(curr);
			G.setVisited(curr);
			process(States, G, curr);
		}
	}
	static void process(Queue<String> States, Graph G, String curr)
	{
		ArrayList<String> Adjecant = G.getAdjecant(curr);
		Iterator<String> it = Adjecant.iterator();
		while(it.hasNext())
		{
			String node = it.next();
			if(!G.isVisited(node))
			{
				States.add(node);
			}
		}
	}
}