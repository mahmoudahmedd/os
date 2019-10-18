/**
 *  @file    CLI.java
 *  @author  Mahmoud Ahmed Tawfik - 20160227
 *  @date    11/10/2019
 *  @version 1.0
 */
package commandlineiinterface;
import java.util.*; 
import java.io.IOException;

public class CommandLineIinterface
{

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception
    {
        User user       = new User("Mahmoud", "12345");
        Terminal t      = new Terminal();
        System.out.println("Terminal [Version 1.0.0]");

        while(true)
        {
            Parser p        = new Parser();
            Scanner scanner = new Scanner(System.in);
                    
            System.out.print(user.getUserName() + "@" + System.getProperty("os.name") + ":");
            String input = scanner.nextLine();
            
            String[] instructions = input.split("\\|");
            
            if(input.indexOf("more") > 0) 
            {
                t.flag = true;
            }
            
            for (String instruction : instructions) 
            {
                instruction = instruction.trim();  
                
                if(p.parse(instruction))
                {
                    if(instruction.indexOf(">>") > 0) 
                    {
                        t.toFile = 1;
                    } 
                    else if(instruction.indexOf(">") > 0) 
                    {
                        t.toFile = 2;
                    }
                    
                    if (t.toFile > 0) 
                        t.currentPathToFile = p.getArguments().get(p.getArguments().size() - 1) ;
                    
                    t.run(p.getCmd(), p.getArguments());
                }
            }
        }
    }
}
