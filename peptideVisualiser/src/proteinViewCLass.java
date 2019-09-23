import java.awt.Color;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eerollapramod
 */
public class proteinViewCLass extends mainFrame {
        //Method For Protein View
    public void proteinViewMwthod(JLayeredPane jlayeredpane, JTable jtable, JTextPane jtextpane) {
        
    
        int panelWidth = jlayeredpane.getWidth();//Getting panel width for further use in graphics design
        System.out.println("Panel Width is:\n" + panelWidth);
        // Getting Fasta sequence text length to match it with panel length
        String fastaSequenceString = jtextpane.getText();
        fastaSequenceString = fastaSequenceString.replace("\n", "");//Replaces new line with nothis to make it once string
        int fastaSequenceStringLength = fastaSequenceString.length();// gets fasta sequence length
        System.out.println(fastaSequenceStringLength);
        /* Reading start values populating them into an array
        to make use them in calculating peptide graphics relative to panel length*/
        int i;
        double[] peptideSequenceStartValues = new double[jtable.getRowCount()];//Creating an array fro start values
        
        for (i = 0; i < jtable.getRowCount(); i++) {
            //gets values from column index 2(Start values)
            String newStartValues = (String) jtable.getModel().getValueAt(i, 2);
            peptideSequenceStartValues[i] = Double.parseDouble(newStartValues);
        }
            System.out.println("\nYour Start points of the peptides are:\n" + Arrays.toString(peptideSequenceStartValues));
        
        // reading stop values and populating them into an array   
        double[] peptideSequenceStopValues = new double[jtable.getRowCount()];// Creating an array for stop values
        
        for (i = 0; i < jtable.getRowCount(); i++) {
            //gets values from column index 3(Stop values)
            String newStopValues = (String) jtable.getModel().getValueAt(i, 3);
            peptideSequenceStopValues[i] = Double.parseDouble(newStopValues);
        }
            System.out.println("\nYour Stop points of the peptides are:\n" + Arrays.toString(peptideSequenceStopValues));

            /*For loop to make Start and stop values relative to the panel width*/
            for (i = 0; i < jtable.getRowCount(); i++) {
            peptideSequenceStartValues[i] = (panelWidth * (peptideSequenceStartValues[i] / fastaSequenceStringLength));
            peptideSequenceStopValues[i] = (panelWidth * (peptideSequenceStopValues[i] / fastaSequenceStringLength));
        }   /*Printing Start and Stop values relative to teh panel length - Difference can be seen in valuse' output  */
            System.out.println("Start points relative to the panel width:\n\n" + Arrays.toString(peptideSequenceStartValues) + "\n");
            System.out.println("Stop points relative to the panel width:\n\n" + Arrays.toString(peptideSequenceStopValues) + "\n");
            /* For loop to caluculating and alligning peptide sequences on panel to get the graphic view */
        for (i = 0; i < peptideSequenceStartValues.length; i++) {
            JPanel peptideSequencePanels = new JPanel();//Generating new panels to sit on top of jLayered pane
            //Setiing up and dispalying panels of peptide sequences on top of jLayered pane
            peptideSequencePanels.setBounds((int) peptideSequenceStartValues[i], 0, ((int) peptideSequenceStopValues[i] - (int) peptideSequenceStartValues[i]), jlayeredpane.getHeight());
            peptideSequencePanels.setOpaque(true);
            peptideSequencePanels.setBorder(BorderFactory.createLineBorder(Color.black));//Creating border for peptide panels
            peptideSequencePanels.setBackground(Color.yellow);
            peptideSequencePanels.setVisible(true);
            jlayeredpane.add(peptideSequencePanels);// Adds panels as per peptide sequences Start and Stop values on jLayeredPane
        }
    
    }
}
