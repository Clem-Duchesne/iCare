/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.ui;

import iHealth.db.DatabaseAccessProperties;
import iHealth.db.SQLWarningsExceptions;
import iHealth.db.requetes;
import iHealth.nf.Authentification;
import iHealth.nf.Poste;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cleme
 */
public class Connexion extends javax.swing.JFrame {

    private static final String configurationFile = "src/iHealth/db/database.properties";
    private Connection conn = null;
    //private Statement statement = null;
    private ResultSet resultat = null;
    private String jdbcDriver, dbUrl, username, password;

    /**
     * Creates new form Connexion
     */
    public Connexion() throws ClassNotFoundException, SQLException {

        initComponents();
        errorMessage.setVisible(false);
        nullMessage.setVisible(false);
        errorServorMessage.setVisible(false);
        //ConnectBD connexionBD = new ConnectBD();
        DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
        jdbcDriver = dap.getJdbcDriver();
        dbUrl = dap.getDatabaseUrl();
        username = dap.getUsername();
        password = dap.getPassword();

        try {

            // Load the database driver
            Class.forName(jdbcDriver);
            // Get a connection to the database
            this.conn = DriverManager.getConnection(dbUrl, username, password);
            //statement = conn.createStatement();
        } catch (SQLException se) {
            // Print information about SQL exceptions
            SQLWarningsExceptions.printExceptions(se);
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        //this.setResizable(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        motDePasseField = new javax.swing.JPasswordField();
        connectButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        identifiantField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nullMessage = new javax.swing.JLabel();
        errorMessage = new javax.swing.JLabel();
        errorServorMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("iHealth - Connexion");
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(785, 496));

        jPanel1.setBackground(new java.awt.Color(244, 244, 244));
        jPanel1.setMaximumSize(new java.awt.Dimension(785, 496));
        jPanel1.setName(""); // NOI18N

        motDePasseField.setForeground(new java.awt.Color(207, 45, 54));
        motDePasseField.setToolTipText("");
        motDePasseField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motDePasseFieldActionPerformed(evt);
            }
        });
        motDePasseField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                motDePasseFieldKeyPressed(evt);
            }
        });

        connectButton.setBackground(new java.awt.Color(207, 45, 54));
        connectButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        connectButton.setForeground(new java.awt.Color(255, 255, 255));
        connectButton.setText("Se connecter");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Montserrat Thin", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(207, 45, 54));
        jLabel1.setText("Mot de passe :");

        identifiantField.setName(""); // NOI18N
        identifiantField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identifiantFieldActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Montserrat Thin", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(207, 45, 54));
        jLabel2.setText("Identifiant :");

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(223, 91, 98));
        jLabel3.setText("iHealth.");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iHealth/img/hospital.png"))); // NOI18N
        jLabel5.setText("jLabel5");

        nullMessage.setFont(new java.awt.Font("Montserrat Thin", 0, 12)); // NOI18N
        nullMessage.setForeground(new java.awt.Color(0, 51, 51));
        nullMessage.setText("Veuillez indiquer un identifiant et un mot de passe");
        nullMessage.setEnabled(false);

        errorMessage.setFont(new java.awt.Font("Montserrat Thin", 0, 12)); // NOI18N
        errorMessage.setForeground(new java.awt.Color(253, 0, 0));
        errorMessage.setText("Identifiant ou mot de passe incorrect");
        errorMessage.setEnabled(false);

        errorServorMessage.setFont(new java.awt.Font("Montserrat Thin", 0, 12)); // NOI18N
        errorServorMessage.setForeground(new java.awt.Color(253, 0, 0));
        errorServorMessage.setText("Connexion au serveur impossible");
        errorServorMessage.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(nullMessage)
                    .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(motDePasseField, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                    .addComponent(identifiantField)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorServorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(173, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(identifiantField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(motDePasseField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectButton)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(errorMessage)
                    .addComponent(errorServorMessage)
                    .addComponent(nullMessage))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        identifiantField.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void motDePasseFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motDePasseFieldActionPerformed

    }//GEN-LAST:event_motDePasseFieldActionPerformed

    private void identifiantFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identifiantFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_identifiantFieldActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        String id = identifiantField.getText();
        String password = motDePasseField.getText();
        boolean reponse = false;

        try {
            reponse = new requetes().connection(this.conn, id, password);
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (reponse) {
            Poste poste = new Authentification().definirPoste(id);
            switch (poste) {
                case SECRETAIREA:
                    Creation_DMA interfaceSecretaireA = new Creation_DMA(this.conn);
                    this.setVisible(false);
                    interfaceSecretaireA.setVisible(true);
                    break;
                /*
                    case SECRETAIREM:
                        Creation_DM interfaceSecretaireM = new Creation_DM();
                        interfaceSecretaireM.setVisible(true);
                    break;
                    
                    //à compléter
                    default:
                        new Connexion().setVisible(true);
                    break;
                 */
            }
        }
        else{
            switch(id){
                case "":
                    nullMessage.setVisible(true);
                break;
                case " ":
                    nullMessage.setVisible(true);
                break;
                default :
                    errorMessage.setVisible(true);
                break;
                
            }
        }
    }//GEN-LAST:event_connectButtonActionPerformed

    private void motDePasseFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_motDePasseFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String id = identifiantField.getText();
            String password = motDePasseField.getText();
            boolean reponse = false;

            try {
                reponse = new requetes().connection(this.conn, id, password);
            } catch (SQLException ex) {
                Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (reponse) {
                Poste poste = new Authentification().definirPoste(id);
                switch (poste) {
                    case SECRETAIREA:
                        Creation_DMA interfaceSecretaireA = new Creation_DMA(this.conn);
                        this.setVisible(false);
                        interfaceSecretaireA.setVisible(true);
                        break;
                    /*
                        case SECRETAIREM:
                            Creation_DM interfaceSecretaireM = new Creation_DM();
                            interfaceSecretaireM.setVisible(true);
                        break;

                        //à compléter
                        default:
                            new Connexion().setVisible(true);
                        break;
                     */
                }
            }
            else{
                switch(id){
                    case "":
                        nullMessage.setVisible(true);
                    break;
                    case " ":
                        nullMessage.setVisible(true);
                    break;
                    default :
                        errorMessage.setVisible(true);
                    break;

                }
            }
        }
    }//GEN-LAST:event_motDePasseFieldKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Connexion().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    private javax.swing.JLabel errorMessage;
    private javax.swing.JLabel errorServorMessage;
    private javax.swing.JTextField identifiantField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField motDePasseField;
    private javax.swing.JLabel nullMessage;
    // End of variables declaration//GEN-END:variables

}
