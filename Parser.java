/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineiinterface;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mahmoud Ahmed
 */
public class Parser 
{
    private ArrayList<String> args;   // Will be filled by arguments extracted by parse method
    private String cmd;      // Will be filled by the command extracted by parse method
    private ArrayList<String> commands;
    private boolean flag;
    private int toFile;
    
    public Parser()
    {
        commands = new ArrayList<String>();
        
        commands.add("cd");
        commands.add("ls");
        commands.add("cp");
        commands.add("cat");
        commands.add("more");
        commands.add("mkdir");
        commands.add("rmdir");
        commands.add("mv");
        commands.add("rm");
        commands.add("args");
        commands.add("date");
        commands.add("help");
        commands.add("pwd"); 
        commands.add("clear");
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

        if (_input.indexOf("more") > 0) 
        {
            this.flag = true;
        } 

        String[] instructions = _input.split("\\|");
        for (String instruction : instructions) 
        {
            instruction = instruction.trim();

            if (instruction.indexOf(">>") > 0) 
            {
                this.toFile = 1;
            } 
            else if (instruction.indexOf(">") > 0) 
            {
                this.toFile = 2;
            }

            //to remove white spaces of entire commands
            String[] parts = instruction.split(" ");
            this.cmd = parts[0];
            
            for(int i = 1;i < parts.length;i++) 
            {
                this.args.add(parts[i]);
            }

            if(commands.indexOf(cmd) == -1)
            {
                System.out.println("Parser::parse(String): ERROR - Command not found");
                return false;
            }
        }
        return true;
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
