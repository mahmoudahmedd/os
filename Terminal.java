/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineiinterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Mahmoud Ahmed
 */
public class Terminal
{
    public void run(String _cmd, ArrayList<String> _args) throws IOException 
    {
        switch(_cmd) 
        {
            
        }
    }
    
     //************************* create directory (rmdir) *********************************//


    public static void rmdir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                rmdir(f);
            }
        }
        file.delete();
    }



    //************************* list items  (ls) *********************************//

    public static void ls(File f){
        File[] list= f.listFiles();
        for(File fi:list){
            System.out.println(fi.getName());
        }
    }




    //************************* Copy  (CP) *********************************//

    public static void cp(File sourceLocation , File targetLocation)
            throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }

            String[] children = sourceLocation.list();
            for (int i=0; i<children.length; i++) {
                cp(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {

            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            System.out.println("copied correctly");
            in.close();
            out.close();
        }
    }




    //************************* Remove  (rm) *********************************//

    public static FileVisitResult rm(File file) throws IOException {

        System.out.println("Deleting file: " + file);
        file.delete();
        return CONTINUE;
    }



    //************************* arguments  (args) *********************************//


    public static void args()
    {
        System.out.println("Commands:   Arguments\n---------------------" +
                "\nclear:  ------- \ncd  :   1.new directory\nls:     1.-(letter): a flag\n" + "rmdir:  1.directory\n" +
                "cat:      1.path1 2.path2\n"+"cp:     1.sourcePath  2.destinationPath\n" +
                "mv:     1.oldPath     2.newPath" + "\nrm:     1.directory\n"
                + "mkdir:  1.directory" + "\nmore:   1.directory\npwd:    -------\nhelp:   -------\ndate:   -------\n");
    }



    //************************* Exit  (exit) *********************************//


    public static void exit(){
        System.exit(0);
    }


    //************************* print current path  (pwd) *********************************//

    public static void pwd() throws IOException {
        String current = new java.io.File(".").getCanonicalPath();
        System.out.println("Current directory:" + current);
    }


    //************************* content at text file  (cat) *********************************//

    public static void cat(File f) throws FileNotFoundException {

        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            System.out.println(str);
        }

    }



    //************************* clear console  (clear) *********************************//
    public static void clear() {
        int numRowsInConsole = 60;
        for (int ii = 0; ii < numRowsInConsole; ii++) {
            // scroll down one line
            System.out.println("");
        }
    }

    //************************* get date  (date) *********************************//
    public static void date() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
    }


    //************************* create directory  (mkdir) *********************************//
    public static void mkdir(String strDir) {
        try {


            boolean success = (
                    new File(strDir)).mkdir();
            if (success) {
                System.out.println("directory " + strDir + " created");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //************************* commands description   (help) *********************************//
    public static void help() {

        System.out.println("clear : This command clears the screen.");
        System.out.println("cd    : This command changed the current directory.");
        System.out.println("ls    : This command lists the files in the current working directory.");
        System.out.println("cp    : This command copies the contents of the first file to the second file");
        System.out.println("mv    : This command moves one or more files or directories from one place to another");
        System.out.println("rm    : This command removes a specified file.");
        System.out.println("mkdir : This command creates a directory.");
        System.out.println("rmdir : This command removes any empty directory.");
        System.out.println("cat   : This command concatenates files and print on the standard output.");
        System.out.println("more  : This command view the contents of a text file one screen at a time.");
        System.out.println("pwd   : This command shows current user directory.");
        System.out.println("args  : This command lists all parameters on the command line, numbers or strings specific command.");
        System.out.println("date  : This command shows the current date.");

    }

    public static void cd(String directory_name) {

        File directory = new File(directory_name).getAbsoluteFile();
        if (directory.exists() || directory.mkdirs()) {
            System.out.println(directory);
        }
    }

    public static void more(String file_name)throws  Exception
    {
        BufferedReader reader_obj = new BufferedReader(new FileReader(file_name));
        ArrayList<String> mystring = new ArrayList <String>();
        String new_line = "";

        while ((new_line = reader_obj.readLine()) != null)
        {
            mystring.add(new_line);
        }
        int count = 0;
        for(int i=0 ; i<mystring.size();i++)
        {
            String x = mystring.get(i);
            if(count==5)
            {
                Scanner scn=new Scanner(System.in);
                scn.nextLine();
                count -= 1;
            }
            System.out.println(x);
            count++;
        }
    }
} 
