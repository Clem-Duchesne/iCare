import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("9b7066eb-e6a3-4dda-b4d0-6dab1cdb1ff9")
public class Document {
    @objid ("255fe036-7a49-407b-bcce-d8725ccc7c04")
    private String dateEcriture;

    @objid ("f7094901-c273-42b7-8f4f-628b4a8d2880")
    private List<Observation> observation = new ArrayList<Observation> ();

    @objid ("d2f84c0f-e839-439f-a728-d05dbf8f8879")
    private List<Prescription> prescription = new ArrayList<Prescription> ();

    @objid ("de3723ac-8a5e-4213-8fdb-80129d7323da")
    private List<LettreDeSortie> lettreDeSortie = new ArrayList<LettreDeSortie> ();

    @objid ("9bac8711-b969-4b25-b15c-cd390057381f")
    private ExamenMedicoTechnique examenMedicoTechnique;

}
