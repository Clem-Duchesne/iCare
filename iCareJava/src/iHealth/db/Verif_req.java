package iHealth.db;

import iHealth.db.DatabaseAccessProperties;
import iHealth.db.SQLWarningsExceptions;
import iHealth.db.requetes;
import iHealth.nf.Patient;
import static iHealth.nf.Sexe.FEMME;
import static iHealth.nf.Sexe.HOMME;
import iHealth.nf.DM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author MZ
 */
public class Verif_req {

    private static final String configurationFile = "src/iHealth/db/database.properties";

    public static void main(String args[]) {
        try {
            String jdbcDriver, dbUrl, username, password;
            DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
            jdbcDriver = dap.getJdbcDriver();
            dbUrl = dap.getDatabaseUrl();
            username = dap.getUsername();
            password = dap.getPassword();
// Load the database driver
            Class.forName(jdbcDriver);
// Get a connection to the database
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            Patient patient = new Patient("210000258", "GUNNESS", "Belle", "01/01/2001", FEMME, "adresse1");
            Patient patient_new = new Patient("220200999", "ALPHA", "Beta", "02/02/2002", FEMME, "1, rue des mimosas");

            //DMA dma = new requetes().getDMA_IPP(conn,patient);
            //DM dm = new DM(patient_new, "operation_essai", "observation_essai", "prescription_essai", "resultat_essai", "lettreS_essai", "correspondance_essai");
//            requetes.createPatient(conn,patient_new);
//            requetes.getPatient(conn,"ALPHA");
//            requetes.createDM(conn,dm);
//            requetes.getDM(conn, patient_new);
//            requetes.setDM(conn, patient_new, "nouvelle operation", "nouvelle observation", "nouvelle prescription", "nouveau resultat", "nouvelle lettre de sortie", "nouvelle correspondance");
//            requetes.getDM(conn, patient_new);
//            requetes.get_date_entree(conn, "210000258");
//            requetes.get_PH_sejour(conn, "210000258");
//            requetes.get_service_sejour(conn, "210000258");
//            requetes.getPatientPH(conn, "0200008");
//            requetes.getPHnumP(conn, "CACHIN", "Francoise");

// Print information about connection warnings
            SQLWarningsExceptions.printWarnings(conn);
            conn.close();

        } catch (SQLException se) {
// Print information about SQL exceptions
            SQLWarningsExceptions.printExceptions(se);
            return;
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
            return;
        }
    }
}
   
