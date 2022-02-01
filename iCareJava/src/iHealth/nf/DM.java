import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("0966ea8b-2f14-45bc-b0dd-be217dd55fa4")
public class DM {
    @objid ("72df4faf-7acd-41a6-bb49-86f5d6a00bc8")
    private String dateCreation;

    @objid ("37e08cbf-1214-405b-8d7e-4a17bbde5c0d")
    private String dateDerniereModification;

    @objid ("d5219224-24e0-46cb-a840-82c43473c027")
    private List<Document> document = new ArrayList<Document> ();

    @objid ("9697551a-fea5-4431-a9fa-f8ca665f5e1d")
    public Chambre chambre;

}
