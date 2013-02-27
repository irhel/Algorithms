import java.util.*;
public class BFS
{
    public static void main(String[] args)
    {
        Queue States = new LinkedList();
        String f = "";
        States.add(f); //Add the root node.
        int count = 0;
        int var = 0;
        int depth = 3;
        while(!States.isEmpty() && count < (int)Math.pow(9,2))
        {
            count ++;
            String current = (String) States.remove();
            System.out.println(current);
            process(States, current, var);
            
        }   
    }
    static void process(Queue States, String current, int var)
    {
        for(int i = var; i < 9; i++)
        {
            String f = current + (char)('1' + i);
            States.add(f);
        }
    }    
}
