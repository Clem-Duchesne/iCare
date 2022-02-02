package iHealth.nf;

/**
 *
 * @author cleme
 */
import java.util.ArrayList;
import java.util.List;

public class Document {
    private String dateEcriture;
    private List<Observation> observation = new ArrayList<Observation> ();
    private List<Prescription> prescription = new ArrayList<Prescription> ();
    private List<LettreDeSortie> lettreDeSortie = new ArrayList<LettreDeSortie> ();
    private ExamenMedicoTechnique examenMedicoTechnique;

}
