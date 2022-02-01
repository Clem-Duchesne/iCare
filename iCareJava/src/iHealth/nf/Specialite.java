import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("e07011d4-9216-41f0-9d15-0514bfcb5132")
public class Specialite {
    @objid ("61b14b68-5f59-44ed-a0e5-b0981aa3d7a5")
    private String nomSpecialite;

    @objid ("5899919c-780f-492d-9e26-cbd5e10da384")
    private Professionnel professionnel;

    @objid ("c6c161e6-105b-4c8b-8ff1-1d60d4fc73ec")
    private List<Services> services = new ArrayList<Services> ();

}
