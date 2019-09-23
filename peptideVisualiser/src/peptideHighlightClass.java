import java.awt.Color;
import java.util.Arrays;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eerollapramod
 */
public class peptideHighlightClass extends mainFrame {
        // Method for highlighting peptide sequence ion jTextPane
    public void peptideHighlighter(JTextPane highlightPane, JTable highlightTable) {
        //An instance of the private subclass of the default highlight painter
        Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.yellow);

        try {
            Highlighter hilight = highlightPane.getHighlighter();//Gets new hghlighter
            Document doc = highlightPane.getDocument();//hilightpane(jTextPane) gets the given documents
            String textLength = doc.getText(0, doc.getLength());//Gets peptides' lengths at column 0(peptide sequence)
            int pos = 0;
            // Reading through table to isolate peptides and populating them into an array
            int i;
            String[] peptideSequences = new String[(int) highlightTable.getRowCount()];
            for (i = 1; i < highlightTable.getRowCount(); i++) {
                String peptideMatches = (String) highlightTable.getModel().getValueAt(i, 0);//gets the peptides from column 0
                peptideSequences[i] = peptideMatches;
            }
                System.out.println(Arrays.toString(peptideSequences));
                //This generates a pattern of reading peptides to use it in highlighting them on text
            for (int j = 1; j < peptideSequences.length; j++) {
                String peptidePattern = peptideSequences[j];//Pattern is stored in peptidePattern variable

                //Search for pattern and Highlight them
                while ((pos = textLength.indexOf(peptidePattern, pos)) >= 0) {
                    //Create highlighter using private painter and apply around pattern
                    hilight.addHighlight(pos, pos + peptidePattern.length(), myHighlightPainter);
                    pos += peptidePattern.length();
                }
            }
        } catch (BadLocationException e) {
            System.out.println("bad location");
        }

    }

    
}
 //A private subclass of the default highlight painter
class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
    public MyHighlightPainter(Color color) {
        super(color);
    }
}

