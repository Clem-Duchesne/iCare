import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("89c2bc86-9953-4a24-9616-d8e8c74e2e06")
public class ExamenMedicoTechnique {
    @objid ("115363a1-f157-4396-9e23-0f0ec4bbe17e")
    private String resultat;

    @objid ("d3eb9af2-d785-4739-b79a-082b1aaa5f37")
    private List<Observation> observation = new ArrayList<Observation> ();

    @objid ("efbb0075-527d-4129-a1b6-4a2530013e5c")
    private ServicesMedicoTechniques servicesMedicoTechniques;

}
