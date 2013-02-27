/* How do we know where to stop?, Why doesn't it go to infinite, like "AAAAAAAA..A"?*/
public class DFS
{
    public static void main(String[] args)
    {
        int maxDepth = 3;
        String f = "";
        for(int i = 0; i <= maxDepth; i++)
            truncatedDFSearch(f, i);
        
    }
    static void search(String f, int depth)
    {
        if(f.length() == depth)
            System.out.println(f);
        else
        {
            for(int i = 0; i < 2; i++)
            {
                f = f + (char) ('A' + i);
                search(f,depth);
                f = new String(f.substring(0, f.length() - 1));
            }
        }
    }
    static void truncatedDFSearch(String f, int depth)
    {
        if(f.length() == depth)
            System.out.println(f);
        else
        {
            for(int i = 0; i < 2; i++)
            {
                f = f + (char)('A' + i);
                truncatedDFSearch(f, depth);
                f = new String(f.substring(0, f.length() - 1));
            }
        }
    } 
}

            
