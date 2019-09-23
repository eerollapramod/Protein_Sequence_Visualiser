
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * 
 * @author eerollapramod
 */
public class fastaFileClass extends mainFrame {    
    // Method for reading user input and to displaying it into Text area
    public void fastaMethod(JTextPane textPane) {
        FileDialog nameBox;
        nameBox = new FileDialog(this, "Open Fasta File", FileDialog.LOAD);
        nameBox.setVisible(true);//Making nameBox visible
        fileDir = nameBox.getDirectory();//Gets Directory list for file sepection      
        fileName = nameBox.getFile();//Allows the user to choose files
        fileName = fileDir.concat(fileName);
        ////////// Displaying/printing out file       
        StyledDocument document = (StyledDocument) textPane.getDocument();

        try {
            FileReader frs = new FileReader(fileName);// gets the file reader
            //Buffer reader reads file and puts in infile
            try (BufferedReader inFile = new BufferedReader(frs)) {
                //Reading and splitting first of fasta file to get ID
                String proteinIDline = inFile.readLine();
                proteinID = proteinIDline.substring(4, 10);
                System.out.println("\nProtein ID is: " + proteinID + "\n");//Printing  protein ID               
                // reading first line after seperating information line
                String line = inFile.readLine();                
                textPane.setText(null);//Stops JTextpane from rereading file if user selects the same file twice
                // WHile loop to read through the file and populating on jTextPane
                while (line != null) {
                    document.insertString(document.getLength(), line + "\n", null);
                    System.out.println(line);
                    line = inFile.readLine();//read next line
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");//Cathces if the requested file is not found
        } catch (IOException | BadLocationException ex) {
            Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
