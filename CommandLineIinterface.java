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
        Parser p        = new Parser();
        Terminal t      = new Terminal();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Terminal [Version 1.0.0]");

        while(true)
        {
            System.out.print(user.getUserName() + "@" + System.getProperty("os.name") + ":");
            String input = scanner.nextLine();

            if(p.parse(input))
            {
                //System.out.println(p.getCmd());
                //System.out.println(p.getArguments());
                
                t.run(p.getCmd(), p.getArguments());
            }
        }
    }
}
