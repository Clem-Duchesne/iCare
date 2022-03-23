/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.ui;

import java.sql.Connection;
import iHealth.db.SQLWarningsExceptions;
import iHealth.db.requetes;
import iHealth.nf.DM;
import iHealth.nf.DMA;
import iHealth.nf.Patient;
import iHealth.nf.Sexe;
import iHealth.nf.toDate;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;

/**
 *
 * @author cleme
 */
public class Mes_Patients_PH extends javax.swing.JFrame {

    
    private Connection conn = null;
    private DefaultListModel patientsModel= new DefaultListModel();
    private List<Patient> patientsGen;
    
    /**
     * Creates new form Creation_DMA
     */
    public Mes_Patients_PH(Connection conn, String identite) throws SQLException, ParseException {
        this.conn = conn;
        initComponents();
        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = tailleEcran.height;
        int largeur = tailleEcran.width;
        this.setSize(longueur, largeur);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Image icon = Toolkit.getDefaultToolkit().getImage("src/iHealth/img/icare.png");  
        this.setIconImage(icon); 
        
        ImageIcon icone = new ImageIcon("src/iHealth/img/hospital.png");
        java.awt.Image img = icone.getImage();
        java.awt.Image newImg = img.getScaledInstance(106,80,100);
        icone=new ImageIcon(newImg);
        logo.setIcon(icone);
        
        ImageIcon icone2 = new ImageIcon("src/iHealth/img/plus (1).png");
        java.awt.Image img2 = icone2.getImage();
        java.awt.Image newImg2 = img2.getScaledInstance(25,25,100);
        icone2=new ImageIcon(newImg2);
       
        
        ImageIcon icone3 = new ImageIcon("src/iHealth/img/vue.png");
        java.awt.Image img3 = icone3.getImage();
        java.awt.Image newImg3 = img3.getScaledInstance(25,25,100);
        icone3=new ImageIcon(newImg3);
        seeDMAIcon.setIcon(icone3);
        
        ImageIcon icone4 = new ImageIcon("src/iHealth/img/deconnexion.png");
        java.awt.Image img4 = icone4.getImage();
        java.awt.Image newImg4 = img4.getScaledInstance(25,25,100);
        icone4=new ImageIcon(newImg4);
        deconnexionIconButton.setIcon(icone4);
        deconnexionIcon2.setIcon(icone4);
        
        
        
        ImageIcon icone7 = new ImageIcon("src/iHealth/img/loupe.png");
        java.awt.Image img7 = icone7.getImage();
        java.awt.Image newImg7 = img7.getScaledInstance(25,25,100);
        icone7=new ImageIcon(newImg7);
        searchIcon.setIcon(icone7);
        this.setResizable(false);
        
        ImageIcon icone6 = new ImageIcon("src/iHealth/img/patient.png");
        java.awt.Image img6 = icone6.getImage();
        java.awt.Image newImg6 = img6.getScaledInstance(25,25,100);
        icone6=new ImageIcon(newImg6);
        seeDMAIcon1.setIcon(icone6);
        seeDMAIcon2.setIcon(icone6); 

        // Récupérer le nom et prénom de la personne connectée
        professionnelLabel.setText(identite);
        
       
        //affichage liste de patients 
        jPanel3.setFocusable(true);
        String[] identiteTable = identite.split(" ");
        String prenom = identiteTable[0];
        String nom = identiteTable[1];
        String numP = new requetes().getPHnumP(conn, nom, prenom);
        
        patientsGen = new requetes().getPatientPH(conn, numP);
 
        for(int i=0;i<patientsGen.size();i++){
            patientsModel.addElement(patientsGen.get(i).getIPP() + " - " + patientsGen.get(i).getNom() + " " + patientsGen.get(i).getPrenom() + "");
            patientListe.setModel(patientsModel);   
          
        }

        
    }
    public Mes_Patients_PH() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int height = dim.height;
        height = height - 40;
        this.setBounds(-8, 40, dim.width, height);
        
        ImageIcon icone = new ImageIcon("src/iHealth/img/hospital.png");
        java.awt.Image img = icone.getImage();
        java.awt.Image newImg = img.getScaledInstance(106,80,100);
        icone=new ImageIcon(newImg);
        logo.setIcon(icone);
        
        ImageIcon icone2 = new ImageIcon("src/iHealth/img/plus (1).png");
        java.awt.Image img2 = icone2.getImage();
        java.awt.Image newImg2 = img2.getScaledInstance(25,25,100);
        icone2=new ImageIcon(newImg2);
    
        
        ImageIcon icone3 = new ImageIcon("src/iHealth/img/vue.png");
        java.awt.Image img3 = icone3.getImage();
        java.awt.Image newImg3 = img3.getScaledInstance(25,25,100);
        icone3=new ImageIcon(newImg3);
        seeDMAIcon.setIcon(icone3);
        
        ImageIcon icone4 = new ImageIcon("src/iHealth/img/deconnexion.png");
        java.awt.Image img4 = icone4.getImage();
        java.awt.Image newImg4 = img4.getScaledInstance(25,25,100);
        icone4=new ImageIcon(newImg4);
        deconnexionIconButton.setIcon(icone4);
        deconnexionIcon2.setIcon(icone4);
        
     
        
       ImageIcon icone7 = new ImageIcon("src/iHealth/img/loupe.png");
        java.awt.Image img7 = icone7.getImage();
        java.awt.Image newImg7 = img7.getScaledInstance(25,25,100);
        icone7=new ImageIcon(newImg7);
        searchIcon.setIcon(icone7);
       
        
        
        ImageIcon icone6 = new ImageIcon("src/iHealth/img/patient.png");
        java.awt.Image img6 = icone6.getImage();
        java.awt.Image newImg6 = img6.getScaledInstance(25,25,100);
        icone6=new ImageIcon(newImg6);
        seeDMAIcon1.setIcon(icone6);
        seeDMAIcon2.setIcon(icone6); 
        
        jPanel3.setFocusable(true);
         this.setResizable(false);
         
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogue = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        message = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        deconnexionIconButton = new javax.swing.JLabel();
        deconnexionLabel = new javax.swing.JLabel();
        seeDMALabel = new javax.swing.JLabel();
        seeDMAIcon = new javax.swing.JLabel();
        seeDMALabel1 = new javax.swing.JLabel();
        seeDMAIcon2 = new javax.swing.JLabel();
        seeDMALabel2 = new javax.swing.JLabel();
        seeDMAIcon1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        deconnexionLabel2 = new javax.swing.JLabel();
        deconnexionIcon2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        professionnelLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        numberOne = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientListe = new javax.swing.JList<>();
        searchTextfield = new javax.swing.JTextField();
        searchIcon = new javax.swing.JLabel();

        dialogue.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogue.setTitle("Erreur ");
        dialogue.setAlwaysOnTop(true);
        dialogue.setBackground(new java.awt.Color(255, 255, 255));
        dialogue.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        dialogue.setModalExclusionType(null);
        dialogue.setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);
        dialogue.setSize(new java.awt.Dimension(400, 300));
        dialogue.setType(java.awt.Window.Type.POPUP);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setBackground(new java.awt.Color(237, 100, 100));
        jButton2.setFont(new java.awt.Font("Quicksand", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("OK");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        scrollPane.setBackground(new java.awt.Color(255, 255, 255));
        scrollPane.setForeground(new java.awt.Color(51, 51, 51));
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setEnabled(false);
        scrollPane.setFocusable(false);

        message.setBorder(null);
        message.setFont(new java.awt.Font("Quicksand", 0, 14)); // NOI18N
        message.setAutoscrolls(false);
        message.setCaretColor(new java.awt.Color(255, 255, 255));
        message.setFocusable(false);
        scrollPane.setViewportView(message);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton2)
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton2)
                .addGap(86, 86, 86))
        );

        javax.swing.GroupLayout dialogueLayout = new javax.swing.GroupLayout(dialogue.getContentPane());
        dialogue.getContentPane().setLayout(dialogueLayout);
        dialogueLayout.setHorizontalGroup(
            dialogueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dialogueLayout.setVerticalGroup(
            dialogueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("iHealth - Mes patients");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setBackground(new java.awt.Color(247, 247, 247));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        logo.setBackground(new java.awt.Color(255, 255, 255));

        deconnexionIconButton.setBackground(new java.awt.Color(255, 255, 255));
        deconnexionIconButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deconnexionIconButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deconnexionIconButtonMouseClicked(evt);
            }
        });

        deconnexionLabel.setBackground(new java.awt.Color(255, 255, 255));
        deconnexionLabel.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        deconnexionLabel.setText("DÉCONNEXION");
        deconnexionLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deconnexionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deconnexionLabelMouseClicked(evt);
            }
        });

        seeDMALabel.setBackground(new java.awt.Color(255, 255, 255));
        seeDMALabel.setFont(new java.awt.Font("Quicksand", 0, 14)); // NOI18N
        seeDMALabel.setText("Visualiser DM");
        seeDMALabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seeDMALabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeDMALabelMouseClicked(evt);
            }
        });

        seeDMAIcon.setBackground(new java.awt.Color(255, 255, 255));
        seeDMAIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seeDMAIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeDMAIconMouseClicked(evt);
            }
        });

        seeDMALabel1.setBackground(new java.awt.Color(255, 255, 255));
        seeDMALabel1.setFont(new java.awt.Font("Quicksand", 0, 14)); // NOI18N
        seeDMALabel1.setText("Patients du service");
        seeDMALabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seeDMALabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeDMALabel1MouseClicked(evt);
            }
        });

        seeDMAIcon2.setBackground(new java.awt.Color(255, 255, 255));
        seeDMAIcon2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seeDMAIcon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeDMAIcon2MouseClicked(evt);
            }
        });

        seeDMALabel2.setBackground(new java.awt.Color(255, 255, 255));
        seeDMALabel2.setFont(new java.awt.Font("Quicksand", 0, 14)); // NOI18N
        seeDMALabel2.setText("Mes patients");
        seeDMALabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seeDMALabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeDMALabel2MouseClicked(evt);
            }
        });

        seeDMAIcon1.setBackground(new java.awt.Color(255, 255, 255));
        seeDMAIcon1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seeDMAIcon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeDMAIcon1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(seeDMAIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seeDMAIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seeDMALabel2)
                    .addComponent(seeDMALabel1)
                    .addComponent(seeDMALabel)
                    .addComponent(seeDMAIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deconnexionLabel)
                    .addComponent(deconnexionIconButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(seeDMAIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(seeDMALabel2)
                .addGap(18, 18, 18)
                .addComponent(seeDMAIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(seeDMALabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(seeDMAIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(seeDMALabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deconnexionIconButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deconnexionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 36)); // NOI18N
        jLabel1.setText("ACCUEIL");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        deconnexionLabel2.setBackground(new java.awt.Color(255, 255, 255));
        deconnexionLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        deconnexionLabel2.setForeground(new java.awt.Color(221, 75, 75));
        deconnexionLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deconnexionLabel2.setText("Déconnexion");
        deconnexionLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deconnexionLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deconnexionLabel2MouseClicked(evt);
            }
        });

        deconnexionIcon2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deconnexionIcon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deconnexionIcon2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deconnexionIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deconnexionLabel2)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(deconnexionIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deconnexionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel3.setText("Praticien Hospitalier :");

        professionnelLabel.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(professionnelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(professionnelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Quicksand", 0, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(217, 21, 21));
        jLabel2.setText("Mes patients");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        patientListe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        patientListe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientListeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(patientListe);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numberOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1084, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(numberOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(250, 250, 250))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        searchTextfield.setFont(new java.awt.Font("Quicksand", 0, 24)); // NOI18N
        searchTextfield.setForeground(new java.awt.Color(153, 153, 153));
        searchTextfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchTextfield.setText("Rechercher");
        searchTextfield.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchTextfieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchTextfieldFocusLost(evt);
            }
        });

        searchIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchIconMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(searchTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(searchIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deconnexionLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deconnexionLabelMouseClicked
        SQLWarningsExceptions.printWarnings(conn);
        try {
            conn.close();
            Connexion connexion = new Connexion();
            this.setVisible(false);
            connexion.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Mes_Patients_PH.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mes_Patients_PH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deconnexionLabelMouseClicked

    private void deconnexionIconButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deconnexionIconButtonMouseClicked
        SQLWarningsExceptions.printWarnings(conn);
        try {
            conn.close();
            Connexion connexion = new Connexion();
            this.setVisible(false);
            connexion.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Mes_Patients_PH.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mes_Patients_PH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deconnexionIconButtonMouseClicked

    private void deconnexionLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deconnexionLabel2MouseClicked
        SQLWarningsExceptions.printWarnings(conn);
        try {
            conn.close();
            Connexion connexion = new Connexion();
            this.setVisible(false);
            connexion.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Mes_Patients_PH.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mes_Patients_PH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deconnexionLabel2MouseClicked

    private void deconnexionIcon2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deconnexionIcon2MouseClicked
        SQLWarningsExceptions.printWarnings(conn);
        try {
            conn.close();
            Connexion connexion = new Connexion();
            this.setVisible(false);
            connexion.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Mes_Patients_PH.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mes_Patients_PH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deconnexionIcon2MouseClicked

    private void searchTextfieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextfieldFocusLost
        if(searchTextfield.getText().equals("")){
            searchTextfield.setText("Rechercher");
        }
    }//GEN-LAST:event_searchTextfieldFocusLost

    private void searchTextfieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextfieldFocusGained
        if(searchTextfield.getText().equals("Rechercher")){
            searchTextfield.setText("");
        }
    }//GEN-LAST:event_searchTextfieldFocusGained

    private void searchIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchIconMouseClicked
         String searchedPatient = searchTextfield.getText();
        //String searchedType = searchedPatient.substring(0,2);
       
        
        List<Patient> patients = new ArrayList<>();
        try {
            patients = new requetes().getPatient(conn, searchedPatient);
        } catch (SQLException ex) {
            Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        }
        //recherche d'un patient dans la base
        patientsModel= new DefaultListModel();
        
        if(searchedPatient.equals("") || searchedPatient.equals(" ")){
            patientListe.setModel(patientsModel);
        } 

        else{
            try {
                patients = new requetes().getPatient(conn, searchedPatient);
                if(patients.size()== 0 ){
                    message.setText("Aucun patient à ce nom enregistré");
                    dialogue.setVisible(true);
                }
                else{
                    //recherche d'un patient dans la base
                    patientsModel= new DefaultListModel();

                    for(int i=0;i<patients.size();i++){
 
                        for(int j = 0; j<patientsGen.size();j++){
                            if(patients.get(i).getIPP().equals(patientsGen.get(j).getIPP())){
                                patientsModel.addElement(patients.get(i).getIPP() + " - " + patients.get(i).getNom() + " " + patients.get(i).getPrenom() + "");
                            }
                        }
                    }
                    patientListe.setModel(patientsModel);
                }
            } catch (SQLException ex) {
                    message.setText("Il semblerait qu'il y est un problème serveur");
                    dialogue.setVisible(true);
                Logger.getLogger(Creation_DM.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                message.setText("Il semblerait qu'il y est un problème serveur");
                    dialogue.setVisible(true);
                Logger.getLogger(Creation_DM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_searchIconMouseClicked

    private void seeDMALabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMALabelMouseClicked
         String patient = patientListe.getSelectedValue();
         if(patient == "" || patient == " " || patient == null){
             message.setText("Veuillez sélectionner un patient pour visualiser son DM");
            dialogue.setVisible(true);
         }
         else{
             String identite = professionnelLabel.getText();
            String IPP = patient.substring(0,9);

            DMA dma;
            DM dm;
            Patient patient1;

            try {
                patient1 = new requetes().getPatientIPP(conn, IPP);
                dma = new requetes().getDMA_IPP(conn, IPP);
                dm = new requetes().getDM(conn, patient1);
                Visualisation_DM_PH interfaceVuePH = new Visualisation_DM_PH(this.conn, identite, IPP,patient1, dma, dm);
                this.setVisible(false);
                interfaceVuePH.setVisible(true);
            } catch (SQLException | ParseException ex) {
                message.setText("Visualisation du DM impossible");
                dialogue.setVisible(true);
                Logger.getLogger(Mes_Patients_PH.class.getName()).log(Level.SEVERE, null, ex);
            }
         }

    }//GEN-LAST:event_seeDMALabelMouseClicked

    private void seeDMAIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMAIconMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seeDMAIconMouseClicked

    private void seeDMALabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMALabel1MouseClicked
        String identite = professionnelLabel.getText();
        try {
            Liste_Patients_PH interfacePHService = new Liste_Patients_PH(conn, identite);
            this.setVisible(false);
            interfacePHService.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Mes_Patients_PH.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Mes_Patients_PH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_seeDMALabel1MouseClicked

    private void seeDMAIcon2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMAIcon2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seeDMAIcon2MouseClicked

    private void seeDMALabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMALabel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seeDMALabel2MouseClicked

    private void seeDMAIcon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMAIcon1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seeDMAIcon1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        dialogue.setVisible(false);
    }//GEN-LAST:event_jButton2MouseClicked

    private void patientListeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientListeMouseClicked
        String patient = patientListe.getSelectedValue();
        String identite = professionnelLabel.getText();
        String IPP = patient.substring(0,9);

        DMA dma;
        DM dm;
        Patient patient1;

        try {
            patient1 = new requetes().getPatientIPP(conn, IPP);
            dma = new requetes().getDMA_IPP(conn, IPP);
            dm = new requetes().getDM(conn, patient1);
            Visualisation_DM_PH interfaceVuePH = new Visualisation_DM_PH(this.conn, identite, IPP,patient1, dma, dm);
            this.setVisible(false);
            interfaceVuePH.setVisible(true);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Mes_Patients_PH.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_patientListeMouseClicked

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
            java.util.logging.Logger.getLogger(Mes_Patients_PH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mes_Patients_PH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mes_Patients_PH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mes_Patients_PH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mes_Patients_PH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel deconnexionIcon2;
    private javax.swing.JLabel deconnexionIconButton;
    private javax.swing.JLabel deconnexionLabel;
    private javax.swing.JLabel deconnexionLabel2;
    private javax.swing.JDialog dialogue;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logo;
    private javax.swing.JTextPane message;
    private javax.swing.JLabel numberOne;
    private javax.swing.JList<String> patientListe;
    private javax.swing.JLabel professionnelLabel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel searchIcon;
    private javax.swing.JTextField searchTextfield;
    private javax.swing.JLabel seeDMAIcon;
    private javax.swing.JLabel seeDMAIcon1;
    private javax.swing.JLabel seeDMAIcon2;
    private javax.swing.JLabel seeDMALabel;
    private javax.swing.JLabel seeDMALabel1;
    private javax.swing.JLabel seeDMALabel2;
    // End of variables declaration//GEN-END:variables
}
