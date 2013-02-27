import java.util.*;
public class BFSPruning
{
    public static void main(String[] args)
    {
        Queue States = new LinkedList();
        String f = "";
        States.add(f);
        int count = 0;
        while(!States.isEmpty()) //GO UNTIL DEPTH 2
        {
            count ++;
            String current = (String) States.remove();
            System.out.println(current);
            process(States, current);
        }
    }
    static void process(Queue States, String current)
    {
        for(int i = 0; i < 3; i++)
        {
            String f = current + (char)('1' + i);
            States.add(f);
        }
    }    
}
