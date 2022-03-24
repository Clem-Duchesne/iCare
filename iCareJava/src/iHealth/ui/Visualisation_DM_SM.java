/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.ui;

import java.sql.Connection;
import iHealth.db.SQLWarningsExceptions;
import iHealth.db.requetes;
import iHealth.nf.Consultation;
import iHealth.nf.DM;
import iHealth.nf.DMA;
import iHealth.nf.Document;
import iHealth.nf.PH;
import iHealth.nf.Patient;
import iHealth.nf.Sexe;
import iHealth.nf.toDate;
import iHealth.nf.toDocument;
import iHealth.nf.toSexe;
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
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cleme
 */
public class Visualisation_DM_SM extends javax.swing.JFrame {

    
    private Connection conn = null;
    private String IPP;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private DM mydm;
    private Patient patient;
    private DMA dma;
    private String identite;
    
    /**
     * Creates new form Creation_DMA
     */
    public Visualisation_DM_SM(Connection conn, String identite, Patient patient, DMA dma, DM dm) throws SQLException, ParseException {
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
        
        ImageIcon icone5 = new ImageIcon("src/iHealth/img/rond.png");
        java.awt.Image img5 = icone5.getImage();
        java.awt.Image newImg5 = img5.getScaledInstance(25,25,100);
        icone5=new ImageIcon(newImg5);
        numeroUn.setIcon(icone5);
        
        ImageIcon icone6 = new ImageIcon("src/iHealth/img/numero-2.png");
        java.awt.Image img6 = icone6.getImage();
        java.awt.Image newImg6 = img6.getScaledInstance(25,25,100);
        icone6=new ImageIcon(newImg6);
        numeroDeux.setIcon(icone6);
        
//        ImageIcon icone7 = new ImageIcon("src/iHealth/img/loupe.png");
//        java.awt.Image img7 = icone7.getImage();
//        java.awt.Image newImg7 = img7.getScaledInstance(25,25,100);
//        icone7=new ImageIcon(newImg7);
//        searchIcon.setIcon(icone7);
        //this.setResizable(false);
        

        // Récupérer le nom et prénom de la personne connectée
        professionnelLabel.setText(identite);
       
        IPP = patient.getIPP();
        
        patientLabel.setText("Patient : " + patient.getNom() + " " + patient.getPrenom() + " - IPP : " + patient.getIPP());
        
         ippLabel.setText("IPP : " + IPP);
            nomPrenomLabel1.setText("Nom & prénom : " + patient.getNom() + " " + patient.getPrenom());
            sexLabel.setText("Sexe : " + new toSexe().sexeToString(patient.getSexe()));
            adressLabel.setText("Adresse : " + patient.getAdresse());
            dateNLabel.setText("Date de naissance : " + patient.getDateNaissance().toString());
            
            Consultation sejour = new requetes().getConsultationByNumS(conn, dm.getNumS(), IPP);
            PH ph = new requetes().getPH(conn, sejour.getNumP());
            PHLabel1.setText("Praticien responsable : " + ph.getNom() + " " + ph.getPrenom());
            
            dateOLabel.setText("Date d'ouverture : " + dm.getDateE());

            //Liste consultations et hospitalisations
            tableModel.addColumn("N°");
            tableModel.addColumn("Date d'ajout");
            tableModel.addColumn("Type");
            tableModel.addColumn("PH");
            tableModel.addColumn("Titre");


            this.IPP = IPP;
            
            List<Document> documents = new ArrayList<>();
            documents = new requetes().getDocuments(this.conn, IPP, dm.getNumS());
            
            List<Consultation> sejoursPat = new ArrayList<>();
            sejoursPat = new requetes().getSejours(this.conn, IPP);

            int index =0;
            PH phLS;
            
            for(Consultation c : sejoursPat){
                
                if(c.getLettre().getLettre_text() != "null" & c.getLettre().getDate() != null){
                    phLS = new requetes().getPH(conn, c.getNumP());
                tableModel.insertRow(index, new Object[] {"LS",c.getDateFinSejour(), "Lettre de sortie", "Dr." + phLS.getNom() + " " + phLS.getPrenom(), "Voir" });
                }
                
            }

         
            for(Document d : documents){
                PH ph1 = new requetes().getPH(conn, d.getNumP());
                
                tableModel.insertRow(index, new Object[] { d.getIdDoc(), d.getDateEcriture(),new toDocument().DocToString(d.getType()),"Dr." + ph1.getNom()+ " " + ph1.getPrenom(), d.getTitre()});
                index++; 
            } 
            

            documentTable.setModel(tableModel);
        
    }
    public Visualisation_DM_SM() {
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
        
        ImageIcon icone5 = new ImageIcon("src/iHealth/img/rond.png");
        java.awt.Image img5 = icone5.getImage();
        java.awt.Image newImg5 = img5.getScaledInstance(25,25,100);
        icone5=new ImageIcon(newImg5);
        numeroUn.setIcon(icone5);
        
        ImageIcon icone6 = new ImageIcon("src/iHealth/img/numero-2.png");
        java.awt.Image img6 = icone6.getImage();
        java.awt.Image newImg6 = img6.getScaledInstance(25,25,100);
        icone6=new ImageIcon(newImg6);
        numeroDeux.setIcon(icone6);
        
//        ImageIcon icone7 = new ImageIcon("src/iHealth/img/loupe.png");
//        java.awt.Image img7 = icone7.getImage();
//        java.awt.Image newImg7 = img7.getScaledInstance(25,25,100);
//        icone7=new ImageIcon(newImg7);
//        searchIcon.setIcon(icone7);
        //this.setResizable(false);
        
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
        patientLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        numberOne = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        numeroUn = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        numeroDeux = new javax.swing.JLabel();
        ippLabel = new javax.swing.JLabel();
        PHLabel1 = new javax.swing.JLabel();
        nomPrenomLabel1 = new javax.swing.JLabel();
        sexLabel = new javax.swing.JLabel();
        adressLabel = new javax.swing.JLabel();
        dateNLabel = new javax.swing.JLabel();
        dateOLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        documentTable = new javax.swing.JTable();
        numeroDeux1 = new javax.swing.JLabel();

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
        setTitle("iHealth - VIsualisation_DM");
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
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(seeDMALabel)
                    .addComponent(seeDMAIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addDMALabel)
                    .addComponent(addDMAIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(deconnexionIconButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deconnexionLabel))
                .addContainerGap())
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
                .addGap(77, 77, 77))
        );

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 36)); // NOI18N
        jLabel1.setText("Visualisation DM");

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

        patientLabel.setFont(new java.awt.Font("Quicksand", 0, 30)); // NOI18N
        patientLabel.setForeground(new java.awt.Color(217, 21, 21));
        patientLabel.setText("Patient :          ");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(37, 158, 185));
        jLabel5.setText(" Informations sur le séjour du patient");

        jLabel6.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(37, 158, 185));
        jLabel6.setText(" Informations sur le patient");

        ippLabel.setBackground(new java.awt.Color(255, 255, 255));
        ippLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        ippLabel.setText("IPP :");

        PHLabel1.setBackground(new java.awt.Color(255, 255, 255));
        PHLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        PHLabel1.setText("Practicien responsable : ");

        nomPrenomLabel1.setBackground(new java.awt.Color(255, 255, 255));
        nomPrenomLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        nomPrenomLabel1.setText("Nom & prénom :");

        sexLabel.setBackground(new java.awt.Color(255, 255, 255));
        sexLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        sexLabel.setText("Sexe :");

        adressLabel.setBackground(new java.awt.Color(255, 255, 255));
        adressLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        adressLabel.setText("Adresse :");

        dateNLabel.setBackground(new java.awt.Color(255, 255, 255));
        dateNLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        dateNLabel.setText("Date de naissance :");

        dateOLabel.setBackground(new java.awt.Color(255, 255, 255));
        dateOLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        dateOLabel.setText("Date d’ouverture :");

        documentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Date ajout document", "Prescription PH", "Observation PH", "Commentaire Infirmier", "Compte-rendu", "Résultats", "Lettre de sortie"
            }
        ));
        documentTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        documentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                documentTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(documentTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numberOne)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(numeroUn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(dateNLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PHLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(adressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sexLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nomPrenomLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ippLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(numeroDeux, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateOLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(310, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(numeroDeux1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1318, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numeroUn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(numberOne, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(ippLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomPrenomLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sexLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adressLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateNLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PHLabel1)
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(numeroDeux, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(dateOLabel)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 165, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(319, 319, 319)
                    .addComponent(numeroDeux1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(378, Short.MAX_VALUE)))
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(patientLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                .addComponent(patientLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
            Logger.getLogger(Visualisation_DM_SM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DM_SM.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Visualisation_DM_SM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DM_SM.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(Visualisation_DM_SM_View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addDMALabelMouseClicked

    private void seeDMAIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMAIconMouseClicked
    seeDMALabelMouseClicked(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_seeDMAIconMouseClicked

    private void seeDMALabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMALabelMouseClicked
        this.setVisible(true);
    }//GEN-LAST:event_seeDMALabelMouseClicked

    private void deconnexionLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deconnexionLabel2MouseClicked
        SQLWarningsExceptions.printWarnings(conn);
        try {
            conn.close();
            Connexion connexion = new Connexion();
            this.setVisible(false);
            connexion.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Visualisation_DM_SM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DM_SM.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Visualisation_DM_SM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DM_SM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deconnexionIcon2MouseClicked

    private void documentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_documentTableMouseClicked
       int num_doc = documentTable.getSelectedRow();
        String numS = mydm.getNumS();
        List<Document> documents = new ArrayList<>();
        try {
            documents = new requetes().getDocuments(conn, IPP, numS);
            if(documents.size() == 0 ){
                message.setText("Erreur serveur");
                dialogue.setVisible(true);
            }
            else{
                    
                Document document = documents.get(num_doc);
                if(document != null){

                    String identite = professionnelLabel.getText();
                    Visualisation_DM_SM_View interfaceDMView = new Visualisation_DM_SM_View(this.conn, identite,document,patient,dma,mydm);
                    this.setVisible(false);
                    interfaceDMView.setVisible(true);
                    
                }
                else{
                    message.setText("Il semblerait qu'il y ait un problème avec la lecture du document");
                    dialogue.setVisible(true);
                }
                
            }
            
        } catch (SQLException ex) {
            message.setText("Erreur serveur");
            dialogue.setVisible(true);
            Logger.getLogger(Visualisation_DM_SM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            message.setText("Erreur serveur");
            dialogue.setVisible(true);
            Logger.getLogger(Visualisation_DM_SM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_documentTableMouseClicked

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
            java.util.logging.Logger.getLogger(Visualisation_DM_SM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Visualisation_DM_SM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Visualisation_DM_SM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Visualisation_DM_SM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Visualisation_DM_SM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PHLabel1;
    private javax.swing.JLabel addDMAIcon;
    private javax.swing.JLabel addDMALabel;
    private javax.swing.JLabel adressLabel;
    private javax.swing.JLabel dateNLabel;
    private javax.swing.JLabel dateOLabel;
    private javax.swing.JLabel deconnexionIcon2;
    private javax.swing.JLabel deconnexionIconButton;
    private javax.swing.JLabel deconnexionLabel;
    private javax.swing.JLabel deconnexionLabel2;
    private javax.swing.JDialog dialogue;
    private javax.swing.JTable documentTable;
    private javax.swing.JLabel ippLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JLabel nomPrenomLabel1;
    private javax.swing.JLabel numberOne;
    private javax.swing.JLabel numeroDeux;
    private javax.swing.JLabel numeroDeux1;
    private javax.swing.JLabel numeroUn;
    private javax.swing.JLabel patientLabel;
    private javax.swing.JLabel professionnelLabel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel seeDMAIcon;
    private javax.swing.JLabel seeDMALabel;
    private javax.swing.JLabel sexLabel;
    // End of variables declaration//GEN-END:variables
}
