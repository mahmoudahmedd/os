/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineiinterface;

import java.io.IOException;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Mahmoud Ahmed
 */
public class Parser 
{
    private ArrayList<String> args;   // Will be filled by arguments extracted by parse method
    private String cmd;      // Will be filled by the command extracted by parse method

    public Parser()
    {
        args = new ArrayList<String>();
    }
    
    /**
     * @return returns true if it was able to parse user input correctly, Otherwise false
     * @param _input the command line input
     */
    public boolean parse(String _input)
    {
        _input = _input.trim();
        _input = _input.replace('\'', '"');
        
        if(_input.length() == 0) 
        {
            System.out.println("Parser::parse(String): ERROR - Empty command");
            return false;
        }
        
        if((_input.charAt(0) == '"') && (_input.charAt(_input.length() - 1) != '"'))
        {
            System.out.println("Parser::parse(String): ERROR - Parsing the command");
            return false;
        }
        else if((_input.charAt(0) == '"') && (_input.charAt(_input.length() - 1) == '"')) 
        {
            _input = _input.substring(1, _input.length() - 1);
            _input = _input.trim();
        }


        if (_input.indexOf('"') == -1) 
        {   
            String[] parts = _input.split(" ");
            this.cmd = parts[0];

            for(int i = 1;i < parts.length;i++) 
            {
                this.args.add(parts[i]);
            }
        }
        else
        {
            String[] parts = _input.split(" ", 2);
            this.cmd = parts[0];
            
            
            if(parts.length > 1)
            {
                parts[1] = parts[1].trim();
                parts[1] = parts[1].substring(1, parts[1].length() - 1);;
                parts[1] = parts[1].trim();
                
                this.args.add(parts[1]);
            }
        }
        
        boolean f = false;
        
        if(args.contains(">>") || args.contains(">")) 
        {
            f = true;
        } 

        if(this.cmd.equals("cd"))
        {
            if(this.args.size() == 1)
            {
                return true;
            }
            else
            {
                System.out.println("Parser::parse(String): ERROR - cd requires 1 argument");
                return false;
            }
        }
        else if(this.cmd.equals("ls"))
        {
            if(this.args.size() == 0 || this.args.size() == 1 || f)
            {
                return true;
            }
            else
            {
                System.out.println("Parser::parse(String): ERROR - ls requires 0 or 1 arguments");
                return false;
            }
        }
        else if(this.cmd.equals("cp"))
        {
            if(this.args.size() == 2)
            {
                return true;
            }
            else
            {
                System.out.println("Parser::parse(String): ERROR - cp requires 2 arguments");
                return false;
            }
        }
        else if(this.cmd.equals("cat"))
        {
            if(this.args.size() == 1 || this.args.size() == 2 || f)
            {
                return true;
            }
            else
            {
                System.out.println("Parser::parse(String): ERROR - cat requires 1 or 2 arguments");
                return false;
            }
        }
        else if(this.cmd.equals("more"))
        {
            if(this.args.size() == 1)
            {
                return true;
            }
            else
            {
                System.out.println("Parser::parse(String): ERROR - more requires 1 arguments");
                return false;
            }
        }
        else if(this.cmd.equals("mkdir"))
        {
            if(this.args.size() == 1)
            {
                return true;
            }
            else
            {
                System.out.println("Parser::parse(String): ERROR - mkdir requires 1 argument");
                return false;
            }
        }
        else if(this.cmd.equals("rmdir"))
        {
            if(this.args.size() == 1)
            {
                return true;
            }
            else
            {
                System.out.println("Parser::parse(String): ERROR - rmdir requires 1 argument");
                return false;
            }
        }
        else if(this.cmd.equals("mv"))
        {
            if(this.args.size() == 2)
            {
                return true;
            }
            else
            {
                System.out.println("Parser::parse(String): ERROR - mv requires 2 arguments");
                return false;
            }
        }
        else if(this.cmd.equals("rm"))
        {
            if(this.args.size() == 1)
            {
                return true;
            }
            else
            {
                System.out.println("Parser::parse(String): ERROR - rm requires 1 argument");
                return false;
            }
        }
        else if(this.cmd.equals("args"))
        {
            if(this.args.size() == 0)
            {
                return true;
            }
            else
            {
                System.out.println("Parser::parse(String): ERROR - args requires 0 argument");
                return false;
            }
        }
        else if(this.cmd.equals("date"))
        {
            if(this.args.size() == 0)
            {
                return true;
            }
            else
            {
                System.out.println("Parser::parse(String): ERROR - date requires 0 argument");
                return false;
            }
        }
        else if(this.cmd.equals("help"))
        {
            if(this.args.size() == 0 || f)
            {
                return true;
            }
            else
            {
                System.out.println("Parser::parse(String): ERROR - help requires 0 argument");
                return false;
            }
        }
        else if(this.cmd.equals("exit"))
        {
            if(this.args.size() == 0)
            {
                return true;
            }
            else
            {
                System.out.println("Parser::parse(String): ERROR - exit requires 0 argument");
                return false;
            }
        }
        else if(this.cmd.equals("pwd"))
        {
            if(this.args.size() == 0 || f)
            {
                return true;
            }
            else
            {
                System.out.println("Parser::parse(String): ERROR - pwd requires 0 argument");
                return false;
            }
        }
        else if(this.cmd.equals("clear"))
        {
            if(this.args.size() == 0)
            {
                return true;
            }
            else
            {
                System.out.println("Parser::parse(String): ERROR - clear requires 0 argument");
                return false;
            }
        }
        else
        {
            System.out.println("Parser::parse(String): ERROR - Command not found");
            return false;
        }
    }

    public String getCmd()
    {
        return cmd;
    }
    
    public ArrayList<String> getArguments()
    {
        return args;
    }
}
