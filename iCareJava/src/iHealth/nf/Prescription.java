import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7a1abf38-c185-42bb-ab96-99952aa2f62f")
public class Prescription {
    @objid ("17f65021-5bc6-4e7e-b5f7-87c9234adce9")
    private String prescription;

    @objid ("8e0ae315-69fd-4bec-a2ef-1967fb13b4ee")
    private List<Commentaire> commentaire = new ArrayList<Commentaire> ();

    @objid ("0ce2a4bf-9f07-4e61-b866-0435b37435e9")
    private PH pH;

}
