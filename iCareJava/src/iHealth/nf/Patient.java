import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("0586bfac-b2d1-46ee-8437-a2fb660db899")
public class Patient {
    @objid ("d7484a6f-dd9d-4c9f-a024-32e88a2d4500")
    private String IPP;

    @objid ("17d8314f-f557-44ef-97a7-fc7c1ae9f1d8")
    private String nom;

    @objid ("4f07c856-9fc6-46ee-a2db-6515177a6a9c")
    private String prenom;

    @objid ("186ef78b-17be-44f2-a02a-9700cc78342f")
    private Date dateNaissance;

    @objid ("38007cc9-ba99-4354-8c5d-05cb7b5aad54")
    private String sexe;

    @objid ("972f4398-5700-40f8-9a21-bbb2372fe718")
    private String adresse;

    @objid ("2a76be0b-ceec-46e6-95e4-409cd575fa54")
    private DPI dPI;

}
