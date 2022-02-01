import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d5ab9ef8-4803-42a1-9ed0-fa38ebecdaf4")
public class Observation {
    @objid ("f2416e4c-4c34-4233-8c5f-5c3ca8c750a5")
    private String texte;

    @objid ("4d083387-c3ed-43e3-9301-07b7bee9992f")
    private PH pH;

    @objid ("2952322e-726c-40f0-b8ef-4f770b22b4b1")
    String getTexte() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.texte;
    }

    @objid ("38220f1e-2935-46f9-b609-f03d8d5fb532")
    void setTexte(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.texte = value;
    }

}
