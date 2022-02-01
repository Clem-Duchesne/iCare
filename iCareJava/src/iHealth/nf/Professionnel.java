import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("860c3fee-3413-4487-9490-202ff8c5fb72")
public class Professionnel {
    @objid ("87cf7090-666f-4737-b9b0-0d4a91b52840")
    private String numeroProfessionnel;

    @objid ("2d39a742-18c1-4075-80eb-0d0a5051f060")
    private String identifiant;

    @objid ("2ae1e08b-2f93-46cc-ade1-e9d75804cbc3")
    private String motDePasse;

    @objid ("9efc7cd3-68dc-473e-b746-e7c46025222d")
    private String nom;

    @objid ("d1a0e00c-9621-454c-ab9b-e5049dce06d9")
    private String prenom;

    @objid ("d71f924c-c657-4bc2-a519-d2f6128df138")
    private Specialite specialite;

    @objid ("ab348ccc-68d6-4ff3-9930-a1781cc66b32")
    private Infirmiere infirmiere;

    @objid ("08038d38-3b24-473f-b6db-b5d680314679")
    private PH pH;

    @objid ("49bf7ef3-938f-4c35-b17f-59f31b4efd64")
    public SecretaireMedicale secretaireMedicale;

    @objid ("f2ddf14d-06cf-436e-a4c4-5cb00180a269")
    private SecretaireAdministrative secretaireAdministrative;

}
