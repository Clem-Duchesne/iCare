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
import iHealth.nf.Document;
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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

/**
 *
 * @author cleme
 */
public class Visualisation_DM_SM_View extends javax.swing.JFrame {

    
    private Connection conn = null;
    private DM mydm;
    private Patient patient;
    private DMA dma;
    private String identite;
    
    /**
     * Creates new form Creation_DMA
     */
    public Visualisation_DM_SM_View(Connection conn, String identite, Document document, Patient patient, DMA dma, DM dm) throws SQLException, ParseException {
        this.conn = conn;
        initComponents();
        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = tailleEcran.height;
        int largeur = tailleEcran.width;
        this.setSize(longueur, largeur);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Image icon = Toolkit.getDefaultToolkit().getImage("src/iHealth/img/icare.png");  
        this.setIconImage(icon); 
        this.mydm=dm;
        this.patient = patient;
        this.dma = dma;
        this.identite = identite;
        
        ImageIcon icone = new ImageIcon("src/iHealth/img/hospital.png");
        java.awt.Image img = icone.getImage();
        java.awt.Image newImg = img.getScaledInstance(106,80,100);
        icone=new ImageIcon(newImg);
        logo.setIcon(icone);
        
        ImageIcon icone2 = new ImageIcon("src/iHealth/img/plus (1).png");
        java.awt.Image img2 = icone2.getImage();
        java.awt.Image newImg2 = img2.getScaledInstance(25,25,100);
        icone2=new ImageIcon(newImg2);
        addDMAIcon.setIcon(icone2);
        
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

        
        ImageIcon icone7 = new ImageIcon("src/iHealth/img/fleche-gauche.png");
        java.awt.Image img7 = icone7.getImage();
        java.awt.Image newImg7 = img7.getScaledInstance(25,25,100);
        icone7=new ImageIcon(newImg7);
        retourIcon.setIcon(icone7);
        

        // Récupérer le nom et prénom de la personne connectée
        professionnelLabel.setText(identite);
       
        
        patientLabel.setText("Patient : " + patient.getNom() + " " + patient.getPrenom() + " - IPP : " + patient.getIPP());
        
        String ph = new requetes().getPersonnel(conn, document.getNumP());
        redactionLabel.setText("Rédigé par : " + ph);
        dateLabel.setText("Date : " + document.getDateEcriture());
        titreLabel.setText("Intitulé : " + document.getTitre());
        String doc = document.getDescription();
        
        descriptionLabel.setText(doc);
        descriptionLabel.setEditable(false);
        
     
        
        
        
        
    }
    public Visualisation_DM_SM_View() {
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
        addDMAIcon.setIcon(icone2);
        
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

        
        ImageIcon icone7 = new ImageIcon("src/iHealth/img/fleche-gauche.png");
        java.awt.Image img7 = icone7.getImage();
        java.awt.Image newImg7 = img7.getScaledInstance(25,25,100);
        icone7=new ImageIcon(newImg7);
        retourIcon.setIcon(icone7);
        
        jPanel3.setFocusable(true);
        
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
        addDMAIcon = new javax.swing.JLabel();
        addDMALabel = new javax.swing.JLabel();
        seeDMAIcon = new javax.swing.JLabel();
        seeDMALabel = new javax.swing.JLabel();
        deconnexionIconButton = new javax.swing.JLabel();
        deconnexionLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        deconnexionLabel2 = new javax.swing.JLabel();
        deconnexionIcon2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        professionnelLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        numberOne = new javax.swing.JLabel();
        retourIcon = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        redactionLabel = new javax.swing.JLabel();
        titreLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        patientLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionLabel = new javax.swing.JTextPane();

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
        setTitle("iHealth - Visualisation Document");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setBackground(new java.awt.Color(247, 247, 247));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        logo.setBackground(new java.awt.Color(255, 255, 255));

        addDMAIcon.setBackground(new java.awt.Color(255, 255, 255));
        addDMAIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addDMAIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addDMAIconMouseClicked(evt);
            }
        });

        addDMALabel.setBackground(new java.awt.Color(255, 255, 255));
        addDMALabel.setFont(new java.awt.Font("Quicksand", 0, 14)); // NOI18N
        addDMALabel.setText("Nouveau DM");
        addDMALabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addDMALabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addDMALabelMouseClicked(evt);
            }
        });

        seeDMAIcon.setBackground(new java.awt.Color(255, 255, 255));
        seeDMAIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seeDMAIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeDMAIconMouseClicked(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(seeDMALabel)
                    .addComponent(seeDMAIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addDMALabel)
                    .addComponent(addDMAIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(deconnexionIconButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deconnexionLabel))
                .addGap(10, 10, 10))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addDMAIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addDMALabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(seeDMAIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(seeDMALabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deconnexionIconButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deconnexionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 36)); // NOI18N
        jLabel1.setText("Visualisation Document");

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
        jLabel3.setText("Secrétaire Médicale : ");

        professionnelLabel.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(professionnelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(37, 158, 185));
        jLabel6.setText(" Retour");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel10.setText("Description :");

        redactionLabel.setBackground(new java.awt.Color(255, 255, 255));
        redactionLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        redactionLabel.setText("Rédigé par :");

        titreLabel.setBackground(new java.awt.Color(255, 255, 255));
        titreLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        titreLabel.setText("Intitulé :");

        dateLabel.setBackground(new java.awt.Color(255, 255, 255));
        dateLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        dateLabel.setText("Date:");

        patientLabel.setFont(new java.awt.Font("Quicksand", 0, 24)); // NOI18N
        patientLabel.setForeground(new java.awt.Color(217, 21, 21));
        patientLabel.setText("Patient :          ");

        descriptionLabel.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        descriptionLabel.setFocusable(false);
        jScrollPane1.setViewportView(descriptionLabel);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numberOne)
                .addGap(9, 9, 9)
                .addComponent(retourIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(patientLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10)
                            .addComponent(titreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(redactionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                        .addGap(509, 509, 509)
                        .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                        .addContainerGap(210, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1094, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(numberOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(250, 250, 250))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(retourIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(patientLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(redactionLabel)
                            .addComponent(dateLabel))
                        .addGap(41, 41, 41)
                        .addComponent(titreLabel)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 21, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(96, 96, 96)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deconnexionIconButtonMouseClicked

    private void addDMAIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addDMAIconMouseClicked
    addDMALabelMouseClicked(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_addDMAIconMouseClicked

    private void addDMALabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addDMALabelMouseClicked
        try {
            Creation_DM interfaceDM = new Creation_DM(this.conn, identite);
            this.setVisible(false);
            interfaceDM.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(Level.SEVERE, null, ex);
            message.setText("Erreur serveur");
                dialogue.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(Level.SEVERE, null, ex);
            message.setText("Erreur serveur");
                dialogue.setVisible(true);
        }
    }//GEN-LAST:event_addDMALabelMouseClicked

    private void seeDMAIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMAIconMouseClicked
    seeDMALabelMouseClicked(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_seeDMAIconMouseClicked

    private void seeDMALabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMALabelMouseClicked
        try {
            Visualisation_DM_SM interfaceDMSM = new Visualisation_DM_SM(this.conn, identite, patient, dma, mydm);
            this.setVisible(false);
            interfaceDMSM.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(Level.SEVERE, null, ex);
            message.setText("Erreur serveur");
                dialogue.setVisible(true);
        }
    }//GEN-LAST:event_seeDMALabelMouseClicked

    private void deconnexionLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deconnexionLabel2MouseClicked
        SQLWarningsExceptions.printWarnings(conn);
        try {
            conn.close();
            Connexion connexion = new Connexion();
            this.setVisible(false);
            connexion.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deconnexionIcon2MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        
        try {
            Visualisation_DM_SM interfaceDMSM = new Visualisation_DM_SM(this.conn, identite, patient, dma, mydm);
            this.setVisible(false);
            interfaceDMSM.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        dialogue.setVisible(false);
    }//GEN-LAST:event_jButton2MouseClicked

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
            java.util.logging.Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new Visualisation_DM_SM_View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addDMAIcon;
    private javax.swing.JLabel addDMALabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel deconnexionIcon2;
    private javax.swing.JLabel deconnexionIconButton;
    private javax.swing.JLabel deconnexionLabel;
    private javax.swing.JLabel deconnexionLabel2;
    private javax.swing.JTextPane descriptionLabel;
    private javax.swing.JDialog dialogue;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JLabel patientLabel;
    private javax.swing.JLabel professionnelLabel;
    private javax.swing.JLabel redactionLabel;
    private javax.swing.JLabel retourIcon;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel seeDMAIcon;
    private javax.swing.JLabel seeDMALabel;
    private javax.swing.JLabel titreLabel;
    // End of variables declaration//GEN-END:variables
}
