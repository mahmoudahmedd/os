/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli;

import javax.swing.*;    // Needed for Swing classes
import java.awt.event.*; // Needed for ActionListener Interface
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mahmoud Ahmed
 */
public class Window extends JFrame {

    public final static String newline = "\n";
    public static JPanel panel;                // To reference a panel
    public static JScrollPane scrollPane;      
    public static JTextArea textArea;
    public final static int WINDOW_WIDTH = 600;  // Window width
    public final static int WINDOW_HEIGHT = 500; // Window height

    /**
     * Constructor
     */
    public Window() {
        // Set the window title.
        setTitle("Compress / Decompress");

        // Set the size of the window.
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // Specify what happens when the close button is clicked.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Build the panel and add it to the frame.
        buildPanel();

        // Add the panel to the frame's content pane.
        add(panel);

        // Display the window.
        setVisible(true);
    }

    /**
     * The buildPanel method adds a label, text field, and and a button to a
     * panel.
     */
    public static void buildPanel() 
    {
        textArea = new JTextArea(20, 50);
        scrollPane = new JScrollPane(textArea);
        

        panel = new JPanel();

        panel.add(textArea);
        panel.add(scrollPane);

    }
}
