# Protein Sequence Visualiser

This java program visualises a FASTA file with its peptide sequences from an OMSSA file.

## Requirements

This program requires `Java vesrion 8` or later.
Datasets required for visualisation is attached as `P02787.fasta` and `omsssaresults.csv`.

The distribution `.jar` file is found in the peptideVisualiser/dist folder. Running this `.jar` file will open up the GUI window where user can load both `FASTA` and `OMSSA` files using the file menu.


## Expected Results

![alt text](https://github.com/eerollapramod/Protein_Sequence_Visualiser/blob/master/peptide_viewer.png)

In the image above:
* The protein is parsed from the `FASTA` file and shown in white color in **Protein View** section, and the sequence is shown in **Protein Sequence** section.
* the identified peptides retrieved by OMSSA are shown in **yellow** color in **Protein View** section.
* These peptides are highlighted in **yellow** color in **Protein Sequence** section.
* The table shows the identified peptide sequences along with their e-values' start & stop position, and the Definitions.












 
