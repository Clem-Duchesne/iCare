import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("965f49b4-48a6-41e4-a9c1-d1177613a9a0")
public class ExamenAnesthesiste {
    @objid ("5e5ef062-24be-4f96-859c-a801110442e6")
    private String resultat;

    @objid ("b459d527-0a51-4732-8aef-7f122911d47d")
    private Prescription prescription;

    @objid ("a3a49f60-9904-416d-9819-0f82ee841077")
    public ServicesMedicoTechniques servicesMedicoTechniques;

    @objid ("d857e8e2-8b32-4819-b0db-0c665bd9a9f5")
    private Observation observation;

    @objid ("bd73d248-3a45-4280-bde7-3c7d7a68582f")
    void setPrescription(Prescription value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.prescription = value;
    }

}
