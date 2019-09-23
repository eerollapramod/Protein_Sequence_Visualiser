import au.com.bytecode.opencsv.CSVReader;
import java.awt.FileDialog;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eerollapramod
 */
public class csvReaderClass extends mainFrame {    
    // Method for Opening and Parse CSV file
    public void CsvMethod(JTable csvTable) {
        FileDialog nameBox;
        nameBox = new FileDialog(this, "Open", FileDialog.LOAD);
        nameBox.setVisible(true);//Making nameBox visible
        CsvFileDir = nameBox.getDirectory();//Gets Directory list for file sepection
        CsvFileName = nameBox.getFile();//Allows the user to choose files
        CsvFileName = CsvFileDir.concat(CsvFileName);
        //Getting t a defult table model before doing anything with it
        DefaultTableModel table = (DefaultTableModel) csvTable.getModel();
        //try and catch for error trapping
        try {
            // Setting up reader to read csv file
            CSVReader reader = new CSVReader(new FileReader(CsvFileName));
            String[] nextLine;
            nextLine = reader.readNext();// Reads next line
            //Reading specific collumns of Peptides, start,stop and Definition columns as they are required            
            csvTable.getColumnModel().getColumn(0).setHeaderValue(nextLine[2]);//Peptide column
            csvTable.getColumnModel().getColumn(1).setHeaderValue(nextLine[3]);//E-value column
            csvTable.getColumnModel().getColumn(2).setHeaderValue(nextLine[7]);//Start point column
            csvTable.getColumnModel().getColumn(3).setHeaderValue(nextLine[8]);//Stop point column
            csvTable.getColumnModel().getColumn(4).setHeaderValue(nextLine[9]);//Define column
            //While loop to read through lines and populating them into the table(as per the column headers)
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                if (Arrays.toString(nextLine).contains(proteinID)) {
                    //populates data into respective columns as long as they contain matched protein ID
                    table.addRow(new Object[]{nextLine[2], nextLine[3], nextLine[7], nextLine[8], nextLine[9]});
                }
            }
        } catch (IOException e) {
            System.out.println("file not found" + e);// Catches is the file is not found
        }
    }   
}
