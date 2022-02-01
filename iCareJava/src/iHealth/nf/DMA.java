import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("017403bb-d39b-43ef-ba54-ffbaf2276f1d")
public class DMA {
    @objid ("39dd8ed2-a68a-4691-9b4f-7b251474b11c")
    private String dateCreation;

    @objid ("aced8e0d-690c-4daf-8f89-8ac5ab87b603")
    private String dateModification;

    @objid ("9d97babf-b3e6-456d-8da3-8981d2c1cb40")
    private List<Consultation> consultation = new ArrayList<Consultation> ();

}
