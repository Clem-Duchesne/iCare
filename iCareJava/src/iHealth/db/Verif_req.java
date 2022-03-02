package iHealth.db;

import iHealth.db.DatabaseAccessProperties;
import iHealth.db.SQLWarningsExceptions;
import iHealth.db.requetes;
import iHealth.nf.Patient;
import static iHealth.nf.Sexe.FEMME;
import static iHealth.nf.Sexe.HOMME;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


/**
 *
 * @author MZ
 */
public class Verif_req {

private static final String configurationFile = "src/database.properties";

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
            Patient patient = new Patient("210000258","GUNNESS","Belle","01/01/2001",FEMME,"adresse1");
            //DMA dma = new requetes().getDMA_IPP(conn,patient);
            
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
    }    }
   
