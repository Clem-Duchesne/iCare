package iHealth.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author catherineberrut
 */
public class requetesbd {

    /**
     * Afficher toutes les informations sur tous les spectacles.
     *     
* @param conn connexion à la base de données
     * @throws SQLException en cas d'erreur d'accès à la base de données
     */
    public static void nbemp(Connection conn) throws SQLException {
// Get a statement from the connection
        Statement stmt = conn.createStatement();
// Execute the query
        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM LesSpectacles");
        while (rs.next()) {
            System.out.println("Nombre de spectacles : " + rs.getInt(1));
        }
// Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }

    public static void employes(Connection conn) throws SQLException {
// Get a statement from the connection
        Statement stmt = conn.createStatement();
// Execute the query

        ResultSet rs = stmt.executeQuery("SELECT * FROM LesSpectacles");
        while (rs.next()) {
            System.out.print("numS : " + rs.getString(1) + " ----> ");
            System.out.print("nomS : " + rs.getString(2) + "\n");
        }

// Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }

//2. Etant donné un numéro de spectacle, afficher le nom de ce spectacle. Si le 
//   numéro indiqué n'est pas dans la base de données, un message spécial sera affiché.
    public static void numSpec(Connection conn) throws SQLException {
// Get a statement from the connection
        Statement stmt = conn.createStatement();

        String num;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un numéro de spectacle :");
        num = sc.nextLine();

// Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM LesSpectacles where numS=" + num);

        if (rs.next()) {
            if (rs.getString(1).matches(num) && rs.getString(2) != null) {
                System.out.println("Le spectacle n°" + num + " est : " + rs.getString(2));
            }
        } else {
            System.out.println("Aucun spectacle ne correspond à ce numéro.");
        }

// Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }

// 3. Etant donné un nom de spectacle, afficher les numéros de spectacles 
//    associés. Si le nom indiqué n'est pas dans la base de données, un message spécial sera affiché.
    public static void nomSpec(Connection conn) throws SQLException {
// Get a statement from the connection
        Statement stmt = conn.createStatement();

        String nom;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un nom de spectacle :");
        nom = sc.nextLine();

// Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM LesSpectacles where nomS='" + nom + "'");

        if (rs.next()) {
            if (rs.getString(2).matches(nom) && rs.getString(1) != null) {
                System.out.println("Le numéro du spectacle " + nom + " est : " + rs.getString(1));
            }
        } else {
            System.out.println("Ce spectacle n'existe pas.");
        }

// Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }

//4.  Etant donné un numéro de spectacle, afficher le nom du spectacle et l’ensemble des
//    représentations de ce spectacle. Le nom du programme n'apparaitra qu'une seule
//    fois, même s'il y a plusieurs représentations du spectacle. Si le numéro indiqué n'est
//    pas dans la base de données, un message spécial sera affiché. De même si le
//    spectacle n'a pas de représentation prévue, son nom s'affichera et un message
//    spécial indiquera l'absence de représentations.
    public static void repres(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();

        String num;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un numéro de spectacle :");
        num = sc.nextLine();

// Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM LesSpectacles LEFT OUTER JOIN LesRepresentations USING (numS) WHERE numS=" + num);
        
        if (rs.next()) {
            if (rs.getString(3) != null) {
                System.out.print("Le numéro de spectacle " + rs.getString(1));
                System.out.print(", représente le spectacle " + rs.getString(2) + ". \n");
                System.out.print("Les dates de représentation de ce spectacle sont les suivantes : \n" + rs.getString(3) + "\n");
                while (rs.next()) {
                    System.out.print(rs.getString(3) + "\n");
                }
            } else {
                System.out.println("Aucune représentation n'est prévue pour ce spectacle.");
            }
        } else {
            System.out.println("Ce spectacle n'existe pas.");
        }

// Close the result set, statement and the connection
        rs.close();
        stmt.close();

    }
    
    public static void ajoutR(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();

        String num, date_j, date_m, date_a, date_java, date_sql;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un spectacle : ");
        num = sc.nextLine();
        
        
// Détaille de la date
        System.out.println("Veuillez saisir une date de représentation du spectacle n°" + num + " : ");
        System.out.println("Le jour : ");
        date_j = sc.nextLine();
        System.out.println("Le mois : ");
        date_m = sc.nextLine();
        System.out.println("L'année : ");
        date_a = sc.nextLine();
        date_java = date_j +"-"+ date_m +"-"+ date_a;
        date_sql = date_a +"-"+ date_m +"-"+ date_j;
        
// Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM LesSpectacles LEFT OUTER JOIN LesRepresentations USING (numS) WHERE numS=" + num);
        if (rs.next() && rs.getString(3)!=date_sql) {
                stmt.executeUpdate("INSERT INTO LesRepresentations VALUES (" + num + ",'" + date_java + "')");
                System.out.println("La représentation a été ajoutée avec succès !");
        }else{
            System.out.println("Erreur, la représentation n'a pas été insérée. Cette représentation existe déjà."); // Le else ne fonctionne pas
        }
// Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }
    
    public static void suppR(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();

        String num, date_j, date_m, date_a, date_java, date_sql;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un spectacle : ");
        num = sc.nextLine();

// Détaille de la date
        System.out.println("Veuillez saisir une date de représentation du spectacle n°" + num + " : ");
        System.out.println("Le jour : ");
        date_j = sc.nextLine();
        System.out.println("Le mois : ");
        date_m = sc.nextLine();
        System.out.println("L'année : ");
        date_a = sc.nextLine();
        date_java = date_j +"-"+ date_m +"-"+ date_a;
        date_sql = date_a +"-"+ date_m +"-"+ date_j;
        
// Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM LesSpectacles LEFT OUTER JOIN LesRepresentations USING (numS) WHERE numS=" + num);
        if (rs.next() && rs.getString(3)==date_sql) {
                stmt.executeUpdate("DELETE FROM LesRepresentations WHERE numS=" + num + " AND dateRep='" + date_java + "'");
                System.out.println("La représentation a été supprimée avec succès !");
        }else{
            System.out.println("Erreur, la représentation n'a pas été supprimée. Cette représentation n'existe."); // Le else ne fonctionne pas
        }
// Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }
}