/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineiinterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Mahmoud Ahmed
 */
public class Terminal
{
    public String currentPath;
    public String currentPathToFile;
    public File dir;
    public boolean flag;
    public int toFile;

    public Terminal()
    {
        this.currentPath       = System.getProperty("user.dir") + '\\';
        this.currentPathToFile = new String();
        this.dir               = new File(currentPath);
        this.flag              = false;
        this.toFile            = 0;
    }

    public void run(String _cmd, ArrayList<String> _args) throws IOException 
    {
        try
        {
            switch(_cmd) 
            {
                case "cd":
                {
                    this.cd(_args.get(0));
                    break;
                }
                case "ls":
                {
                    this.ls(_args);
                    break;
                }
                case "cp":
                {
                    this.cp(_args.get(0), _args.get(1));
                    break;
                }
                case "cat":
                {
                    this.cat(_args);
                    break;
                }
                case "more":
                {
                    //this.cd(_args.get(0));
                    break;
                }
                case "mkdir":
                {
                    this.mkdir(_args.get(0));
                    break;
                }
                case "rmdir":
                {
                    this.rmdir(_args.get(0));
                    break;
                }
                case "mv":
                {
                    this.mv(_args.get(0), _args.get(1));
                    break;
                }
                case "rm":
                {
                    this.rm(_args.get(0));
                    break;
                }
                case "args":
                {
                    this.args();
                    break;
                }
                case "date":
                {
                    this.date();
                    break;
                }
                case "help":
                {
                    this.help();
                    break;
                }
                case "exit":
                {
                    this.exit();
                    break;
                }
                case "pwd":
                {
                    this.pwd();
                    break;
                }
                case "clear":
                {
                    this.clear();
                    break;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Terminal::run(String, ArrayList<String>): ERROR - " + _cmd + ": Cannot access args");
        }
    }
    
    public void printToFile(String _text) throws IOException 
    {
        _text += System.lineSeparator();

        if(this.toFile == 1) 
        {
            File file = new File(this.currentPathToFile);
            
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(_text);

            br.close();
            fr.close();
        } 
        else if(this.toFile == 2) 
        {
            Path filePath = Paths.get(this.currentPathToFile);

            if(!Files.exists(filePath)) 
            {
                Files.createFile(filePath);
            }
            
            PrintWriter writer = new PrintWriter(this.currentPathToFile, "UTF-8");
            writer.println(_text);
            writer.close();
        }

        this.toFile = 0;
    }
    
    //************************* cd *********************************//
    public void cd(String _directoryPath) 
    {      
        if(_directoryPath.length() == 0 || _directoryPath == null || _directoryPath.equals("."))
            return;
        
        int index = _directoryPath.indexOf(":/");

        if(index == -1 && !_directoryPath.equals(".."))
        {
            _directoryPath = this.currentPath + _directoryPath + '\\';
            
            File f = new File(_directoryPath);
           
            if(f.isDirectory() && f.exists())
            {
                this.dir         = new File(_directoryPath);
                this.currentPath = this.dir.getAbsolutePath();
            }
            else
            {
                System.out.println("Terminal::rmdir(String): ERROR - No suchss directory");
            }
        }
        else if(index > -1)
        {
           File f = new File(_directoryPath);
           
            if(f.isDirectory() && f.exists())
            {
                this.dir         = new File(_directoryPath);
                this.currentPath = this.dir.getAbsolutePath();
            }
            else
            {
                System.out.println("Terminal::rmdir(String): ERROR - No suchss directory");
            }
        }
    }
    
    //---************************* ls *********************************//
    public void ls(ArrayList<String> _directoryPath) throws IOException 
    {
        if(_directoryPath.size() == 0)
        {
            File listOfFiles[] = this.dir.listFiles();
            String text = "";
            
            for (int i = 0; i < listOfFiles.length; i++) 
            {
                if (listOfFiles[i].isFile()) 
                {
                    text += "File " + listOfFiles[i].getName();
                    System.out.println("File " + listOfFiles[i].getName());
                } 
                else if (listOfFiles[i].isDirectory()) 
                { 
                    text += "Directory " + listOfFiles[i].getName();
                    System.out.println("Directory " + listOfFiles[i].getName());
                }
            }
            
            if(this.toFile > 0)
                printToFile(text);
        }
        else
        {
            if(_directoryPath.get(0).length() == 0 || _directoryPath == null || _directoryPath.get(0).equals("."))
                return;
        
            int index = _directoryPath.get(0).indexOf(":/");

            if(index == -1)
            {
                _directoryPath.set(0, this.currentPath + _directoryPath.get(0) + '\\');
            }
           
           
           File f = new File(_directoryPath.get(0));
           
           if(f.isDirectory() && f.exists())
           {
               this.dir = f;
               
               File listOfFiles[] = this.dir.listFiles();
            
                for (int i = 0; i < listOfFiles.length; i++) 
                {
                    if (listOfFiles[i].isFile()) 
                    {
                        System.out.println("File " + listOfFiles[i].getName());
                    } 
                    else if (listOfFiles[i].isDirectory()) 
                    {
                        System.out.println("Directory " + listOfFiles[i].getName());
                    }
                }
           }
           else
           {
               System.out.println("Terminal::ls(ArrayList<String>): ERROR - No suchss directory");
           }
        }
        
    }
    
    //************************* cp *********************************//
    public void cp(String sourcePath, String destinationPath) throws IOException 
    {
        if (sourcePath == "" || destinationPath == "") 
        {
            System.out.println("Terminal::cp(String, String): ERROR - Empty arguments");
            return;
        }
        
        int ind = sourcePath.indexOf(":/");
        if(ind == -1)
        {
            sourcePath = this.currentPath + sourcePath;
        }
        
        File src = new File(sourcePath);
        File des = new File(destinationPath);
        
        if (src.exists()) 
        {
            if (src.isFile()) 
            {
                if (des.exists()) 
                {
                    if (des.isFile()) 
                    {
                        Files.copy(Paths.get(sourcePath), new FileOutputStream(destinationPath));
                    } 
                    else if (des.isDirectory()) 
                    {
                        String name = "";
                        for (int i = sourcePath.length() - 1; i >= 0; i--) 
                        {
                            if (sourcePath.charAt(i) == '\\') 
                            {
                                name = sourcePath.substring(i);
                                break;
                            }
                        }
                        
                        destinationPath += name;
                        Files.copy(Paths.get(sourcePath), new FileOutputStream(destinationPath));
                    }
                } 
                else 
                {
                    String name = "";
                    for (int i = destinationPath.length() - 1; i >= 0; i--) 
                    {

                        if (destinationPath.charAt(i) == '\\') 
                        {
                            name = destinationPath.substring(0, i - 1);
                            break;
                        }
                    }
                    File check = new File(name);

                    if (check.exists()) 
                    {
                        Files.copy(Paths.get(sourcePath), new FileOutputStream(destinationPath));
                    } 
                    else 
                    {
                        System.out.println("Terminal::cp(String, String): ERROR - can not create regular file `" + destinationPath + "` : No such file or directory");
                    }
                }
            } 
            else 
            {
                System.out.println("Terminal::cp(String, String): ERROR - omitting directory `" + sourcePath + "`");
            }
        } 
        else 
        {
            System.out.println("Terminal::cp(String, String): ERROR - Can not stat `" + sourcePath + "` : No such file or directory");
        }
        
    }
    
    //---************************* cat *********************************//
    public void cat(ArrayList<String> _filenames) throws FileNotFoundException, IOException 
    {
        Scanner scan, scan2;
        String text = new String("");

        int index1 = _filenames.get(0).indexOf(":/");      
        if(index1 == -1)
        {
            _filenames.set(0, this.currentPath + _filenames.get(0));
        }
        
        scan = new Scanner(new java.io.File(_filenames.get(0)));
        while (scan.hasNext()) 
        {
            text += scan.next();
        }

        text += " ";
        
        if(_filenames.size() > 1)
        {
            int index2 = _filenames.get(1).indexOf(":/");
            if(index2 == -1)
            {
                _filenames.set(1, this.currentPath + _filenames.get(1));
            }

            scan2 = new Scanner(new java.io.File(_filenames.get(1)));
            while (scan2.hasNext()) 
            {
                text += scan2.next();
            }
        }
        System.out.println(text);
        
        if(this.toFile > 0)
            printToFile(text);
    }
    
    //************************* more *********************************//
    public void more(String _directoryPath) 
    {
        
    }
    
    //---************************* mkdir *********************************//
    public void mkdir(String _directoryPath) 
    {
        int index = _directoryPath.indexOf(":/");
        
        if(index == -1)
        {
           _directoryPath = this.currentPath + _directoryPath;
        }
        
        File f = new File(_directoryPath);

        if(!f.mkdir()) 
        {
            System.out.println("Terminal::rmdir(String): ERROR - Failed to create the directory");
        }
    }
    //---************************* rmdir *********************************//
    public void rmdir(String _directoryPath) 
    {
        int index = _directoryPath.indexOf(":/");
        
        if(index == -1)
        {
           _directoryPath = this.currentPath + _directoryPath;
        }
        
        File f = new File(_directoryPath);
        
        if(!f.exists())
        {
            System.out.println("Terminal::rmdir(String): ERROR - Failed to remove `" + _directoryPath + "` : No such file or directory");
        }
        
        if(f.isFile())
        {
            System.out.println("Terminal::rmdir(String): ERROR - Failed to remove `" + _directoryPath + "` : Not a directory");
        }
        
        if(!f.delete()) 
        {
            System.out.println("Terminal::rmdir(String): ERROR - Failed to remove `" + _directoryPath + "` : Directory not empty");
        }
    }
    
    //---************************* mv *********************************//
    public void mv(String sourcePath, String destinationPath) throws IOException 
    {
        if (sourcePath == "" || destinationPath == "") 
        {
            System.out.println("Terminal::mv(String, String): ERROR - Empty arguments");
            return;
        }
        
        int ind = sourcePath.indexOf(":/");
        if(ind == -1)
        {
            sourcePath = this.currentPath + sourcePath;
        }
        
        File src = new File(sourcePath);
        File des = new File(destinationPath);
        
        if (src.exists()) 
        {
            if (src.isFile()) 
            {
                if (des.exists()) 
                {
                    if (des.isFile()) 
                    {
                        Files.copy(Paths.get(sourcePath), new FileOutputStream(destinationPath));
                    } 
                    else if (des.isDirectory()) 
                    {
                        String name = "";
                        for (int i = sourcePath.length() - 1; i >= 0; i--) 
                        {
                            if (sourcePath.charAt(i) == '\\') 
                            {
                                name = sourcePath.substring(i);
                                break;
                            }
                        }
                        
                        destinationPath += name;
                        Files.copy(Paths.get(sourcePath), new FileOutputStream(destinationPath));
                    }
                } 
                else 
                {
                    String name = "";
                    for (int i = destinationPath.length() - 1; i >= 0; i--) 
                    {

                        if (destinationPath.charAt(i) == '\\') 
                        {
                            name = destinationPath.substring(0, i - 1);
                            break;
                        }
                    }
                    File check = new File(name);

                    if (check.exists()) 
                    {
                        Files.copy(Paths.get(sourcePath), new FileOutputStream(destinationPath));
                    } 
                    else 
                    {
                        System.out.println("Terminal::mv(String, String): ERROR - can not create regular file `" + destinationPath + "` : No such file or directory");
                    }
                }
            } 
            else 
            {
                System.out.println("Terminal::mv(String, String): ERROR - omitting directory `" + sourcePath + "`");
            }
        } 
        else 
        {
            System.out.println("Terminal::mv(String, String): ERROR - Can not stat `" + sourcePath + "` : No such file or directory");
        }
        
        File f = new File(sourcePath);

        if (f.exists()) 
        {
            if (f.isFile()) 
            {
                f.delete();
            }
        }

    }
    
    //----************************* rm *********************************//
    public void rm(String _filePath) 
    {
        int index = _filePath.indexOf(":/"); 
        
        if(index == -1)
        {
           _filePath = this.currentPath + _filePath;
        }
        
        File file = new File(_filePath);
        
        if(!file.delete()) 
        {
            System.out.println("Terminal::rm(String): ERROR - Failed to delete file");
        }
    }
    
    //----************************* args *********************************//
    public void args() 
    {
        String[] commands = new String[15];
        String[] args     = new String[15];

        commands[0] = "cd";
        commands[1] = "ls";
        commands[2] = "cp";
        commands[3] = "cat";
        commands[4] = "more";
        commands[5] = "mkdir";
        commands[6] = "rmdir";
        commands[7] = "mv";
        commands[8] = "rm";
        commands[9] = "args";
        commands[10] = "date";
        commands[11] = "help";
        commands[12] = "exit";
        commands[13] = "pwd";
        commands[14] = "clear";
        
        
        args[0]  = "one string the path of the new directory.";
        args[1]  = "(the arguments are optional) one or more string the directory that would be listed.";
        args[2]  = "two strings the path of the files.";
        args[3]  = "one string or more the path of the file.";
        args[4]  = "one string the path of the file.";
        args[5]  = "one string the path of the directory.";
        args[6]  = "one string the path of the directory.";
        args[7]  = "two strings the path of the files.";
        args[8]  = "one string the path of the file.";
        args[9]  = "one string the name of the commands.";
        args[10] = "it take no arguments.";
        args[11] = "it takes no arguments.";
        args[12] = "it takes no arguments.";
        args[13] = "it takes no arguments.";
        args[14] = "it takes no arguments.";
        
        for (int i = 0;i < 15;i++) 
        {
            System.out.println(commands[i] + " : " + args[i]);
        }
    }
    
    //----************************* date *********************************//
    public void date() 
    {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
    }
    
    //---************************* help *********************************//
    public void help() throws IOException 
    {
        String[] commands     = new String[15];
        String[] descriptions = new String[15];

        commands[0]  = "cd";
        commands[1]  = "ls";
        commands[2]  = "cp";
        commands[3]  = "cat";
        commands[4]  = "more";
        commands[5]  = "mkdir";
        commands[6]  = "rmdir";
        commands[7]  = "mv";
        commands[8]  = "rm";
        commands[9]  = "args";
        commands[10] = "date";
        commands[11] = "help";
        commands[12] = "exit";
        commands[13] = "pwd";
        commands[14] = "clear";
        
        descriptions[0]  = "Change the shell working directory, Change the current directory to DIR. The default DIR is the value of the HOME shell variable.";
        descriptions[1]  = "list directory contents.";
        descriptions[2]  = "copy files and directories.";
        descriptions[3]  = "concatenate files and print on the standard output.";
        descriptions[4]  = "file perusal filter for crt viewing.";
        descriptions[5]  = "make directories.";
        descriptions[6]  = "remove empty directories.";
        descriptions[7]  = "move files.";
        descriptions[8]  = "remove files or directories.";
        descriptions[9]  = "List all command arguments.";
        descriptions[10] = "Current date/time.";
        descriptions[11] = "display information about builtin commands.";
        descriptions[12] = "Stop all.";
        descriptions[13] = "Print the name of the current working directory.";
        descriptions[14] = "clear the terminal screen.";
        
        String text = "";
        
        for (int i = 0;i < 15;i++) 
        {
            text += commands[i] + " : " + descriptions[i];
            System.out.println(commands[i] + " : " + descriptions[i]);
        }
        
        if(this.toFile > 0)
            printToFile(text);
    }
    
    //----************************* exit *********************************//
    public void exit() 
    {
        //Terminate JVM 
        System.exit(0); 
    }
    
    //----************************* pwd *********************************//
    public void pwd() throws IOException 
    {
        System.out.println(this.currentPath);
        
        if(this.toFile > 0)
            printToFile(this.currentPath);
    }
    
    //---************************* clear *********************************//
    public void clear() 
    {
        for (int i = 0;i < 100; i++) 
        {
            System.out.println();
        } 
    }

} 
