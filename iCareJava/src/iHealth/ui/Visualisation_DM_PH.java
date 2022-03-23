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
import iHealth.nf.DocumentType;
import iHealth.nf.PH;
import iHealth.nf.Patient;
import iHealth.nf.Sexe;
import iHealth.nf.toDate;
import iHealth.nf.toDocument;
import iHealth.nf.toSexe;
import java.awt.Dimension;
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
public class Visualisation_DM_PH extends javax.swing.JFrame {

    
    private Connection conn = null;
    private String IPP =null;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private DM mydm;
    private Consultation maConsultation;
    
    /**
     * Creates new form Creation_DMA
     */
    public Visualisation_DM_PH(Connection conn, String identite, String IPP, Patient patient, DMA dma, DM dm) throws SQLException, ParseException {
        this.conn = conn;
        this.IPP = IPP;
        this.mydm = dm;
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
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
        
        
        ImageIcon icone6 = new ImageIcon("src/iHealth/img/patient.png");
        java.awt.Image img6 = icone6.getImage();
        java.awt.Image newImg6 = img6.getScaledInstance(25,25,100);
        icone6=new ImageIcon(newImg6);
        seeDMAIcon1.setIcon(icone6);
        seeDMAIcon2.setIcon(icone6); 
        
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
            maConsultation = sejour;
            PH ph = new requetes().getPH(conn, sejour.getNumP());
            PHLabel1.setText("Praticien responsable : " + ph.getNom() + " " + ph.getPrenom());
            dateDebutLabel.setText("Date de début de séjour : " + sejour.getDateDebutSejour());
            serviceRLabel.setText("Service responsable : " + sejour.getLit().getChambre().getServiceResponsable());
            serviceGLabel.setText("Service géographique : " + sejour.getLit().getChambre().getServiceGeographique());
            nChambreLabel.setText("N° chambre : " + sejour.getLit().getChambre().getNumeroChambre());
            natureLabel.setText("Nature de la prestation : " + sejour.getNaturePrestation());
            
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
                phLS = new requetes().getPH(conn, c.getNumP());
                tableModel.insertRow(index, new Object[] {"LS",c.getDateFinSejour(), "Lettre de sortie", "Dr." + phLS.getNom() + " " + phLS.getPrenom(), "Lettre de sortie" });
            }
            for(Document d : documents){
                PH ph1 = new requetes().getPH(conn, d.getNumP());
                //System.out.println(d.getDateEcriture());
                
                
                tableModel.insertRow(index, new Object[] { d.getIdDoc(), d.getDateEcriture(),new toDocument().DocToString(d.getType()),"Dr." + ph1.getNom()+ " " + ph1.getPrenom(), d.getTitre()});
                index++; 
            } 
            
            

            documentTable.setModel(tableModel);
        

    }
    public Visualisation_DM_PH() {
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
        
        ImageIcon icone7 = new ImageIcon("src/iHealth/img/patient.png");
        java.awt.Image img7 = icone7.getImage();
        java.awt.Image newImg7 = img7.getScaledInstance(25,25,100);
        icone7=new ImageIcon(newImg7);
        seeDMAIcon1.setIcon(icone7);
        seeDMAIcon2.setIcon(icone7); 
        
        ImageIcon icone8 = new ImageIcon("src/iHealth/img/numero-3.png");
        java.awt.Image img8 = icone8.getImage();
        java.awt.Image newImg8 = img8.getScaledInstance(25,25,100);
        icone8=new ImageIcon(newImg8);
        numeroTrois.setIcon(icone8);
        
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
        seeDMAIcon1 = new javax.swing.JLabel();
        seeDMAIcon = new javax.swing.JLabel();
        seeDMALabel = new javax.swing.JLabel();
        deconnexionIconButton = new javax.swing.JLabel();
        deconnexionLabel = new javax.swing.JLabel();
        seeDMALabel1 = new javax.swing.JLabel();
        seeDMALabel2 = new javax.swing.JLabel();
        seeDMAIcon2 = new javax.swing.JLabel();
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
        nomPrenomLabel1 = new javax.swing.JLabel();
        sexLabel = new javax.swing.JLabel();
        adressLabel = new javax.swing.JLabel();
        dateNLabel = new javax.swing.JLabel();
        dateOLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        documentTable = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        dateDebutLabel = new javax.swing.JLabel();
        PHLabel1 = new javax.swing.JLabel();
        serviceRLabel = new javax.swing.JLabel();
        natureLabel = new javax.swing.JLabel();
        numeroTrois = new javax.swing.JLabel();
        serviceGLabel = new javax.swing.JLabel();
        nChambreLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        prescriptionLabel = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        operationLabel = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        observationLabel = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        commentaireLabel = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        resultatLabel = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        lettreSortieLabel = new javax.swing.JLabel();

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
        setTitle("iHealth - Visualisation DM");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setBackground(new java.awt.Color(247, 247, 247));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        logo.setBackground(new java.awt.Color(255, 255, 255));

        seeDMAIcon1.setBackground(new java.awt.Color(255, 255, 255));
        seeDMAIcon1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seeDMAIcon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeDMAIcon1MouseClicked(evt);
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

        seeDMALabel1.setBackground(new java.awt.Color(255, 255, 255));
        seeDMALabel1.setFont(new java.awt.Font("Quicksand", 0, 14)); // NOI18N
        seeDMALabel1.setText("Patients du service");
        seeDMALabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seeDMALabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeDMALabel1MouseClicked(evt);
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

        seeDMAIcon2.setBackground(new java.awt.Color(255, 255, 255));
        seeDMAIcon2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seeDMAIcon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeDMAIcon2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(13, 13, 13))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(deconnexionIconButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deconnexionLabel))
                    .addComponent(seeDMAIcon1, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seeDMALabel1, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(seeDMAIcon, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seeDMALabel, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(seeDMALabel2, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(seeDMAIcon2, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(seeDMAIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(seeDMALabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seeDMAIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seeDMALabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seeDMAIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seeDMALabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deconnexionIconButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deconnexionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
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
                .addContainerGap(20, Short.MAX_VALUE))
        );

        patientLabel.setFont(new java.awt.Font("Quicksand", 0, 30)); // NOI18N
        patientLabel.setForeground(new java.awt.Color(217, 21, 21));
        patientLabel.setText("Patient :          ");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(37, 158, 185));
        jLabel5.setText("Informations sur le DM");

        jLabel6.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(37, 158, 185));
        jLabel6.setText(" Informations sur le patient");

        ippLabel.setBackground(new java.awt.Color(255, 255, 255));
        ippLabel.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        ippLabel.setText("IPP :");

        nomPrenomLabel1.setBackground(new java.awt.Color(255, 255, 255));
        nomPrenomLabel1.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        nomPrenomLabel1.setText("Nom & prénom :");
        nomPrenomLabel1.setToolTipText("");

        sexLabel.setBackground(new java.awt.Color(255, 255, 255));
        sexLabel.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        sexLabel.setText("Sexe :");

        adressLabel.setBackground(new java.awt.Color(255, 255, 255));
        adressLabel.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        adressLabel.setText("Adresse :");

        dateNLabel.setBackground(new java.awt.Color(255, 255, 255));
        dateNLabel.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        dateNLabel.setText("Date de naissance :");

        dateOLabel.setBackground(new java.awt.Color(255, 255, 255));
        dateOLabel.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        dateOLabel.setText("Date d’ouverture :");

        documentTable.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        documentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Date ajout document", "Prescription PH", "Observation PH", "Commentaire Infirmier", "Compte-rendu", "Résultats", "Lettre de sortie"
            }
        ));
        documentTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        documentTable.setGridColor(new java.awt.Color(255, 255, 255));
        documentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                documentTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(documentTable);

        jLabel8.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(37, 158, 185));
        jLabel8.setText(" Informations sur le séjour du patient");

        dateDebutLabel.setBackground(new java.awt.Color(255, 255, 255));
        dateDebutLabel.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        dateDebutLabel.setText("Date de début de séjour :");

        PHLabel1.setBackground(new java.awt.Color(255, 255, 255));
        PHLabel1.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        PHLabel1.setText("PH responsable :");

        serviceRLabel.setBackground(new java.awt.Color(255, 255, 255));
        serviceRLabel.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        serviceRLabel.setText("Service responsable : ");

        natureLabel.setBackground(new java.awt.Color(255, 255, 255));
        natureLabel.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        natureLabel.setText("Nature de la prestation : ");

        serviceGLabel.setBackground(new java.awt.Color(255, 255, 255));
        serviceGLabel.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        serviceGLabel.setText("Service géographique : ");

        nChambreLabel.setBackground(new java.awt.Color(255, 255, 255));
        nChambreLabel.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        nChambreLabel.setText("N° Chambre :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(numberOne)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(dateOLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(PHLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(natureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(266, 266, 266)))
                                        .addComponent(nChambreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(286, 286, 286)))
                                .addContainerGap())
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(dateDebutLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(serviceRLabel)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(serviceGLabel)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(numeroUn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numeroDeux, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numeroTrois, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ippLabel)
                            .addComponent(nomPrenomLabel1)
                            .addComponent(sexLabel)
                            .addComponent(adressLabel)
                            .addComponent(dateNLabel))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(numberOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(250, 250, 250))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numeroUn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ippLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomPrenomLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sexLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adressLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateNLabel)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(numeroDeux, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(serviceRLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateDebutLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(serviceGLabel)
                            .addComponent(PHLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nChambreLabel)
                            .addComponent(natureLabel))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(numeroTrois, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateOLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel7.setBackground(new java.awt.Color(204, 246, 255));

        jLabel4.setFont(new java.awt.Font("Quicksand", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Ajouter Document");

        jPanel36.setBackground(new java.awt.Color(243, 245, 255));

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));

        prescriptionLabel.setBackground(new java.awt.Color(255, 255, 255));
        prescriptionLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        prescriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prescriptionLabel.setText("Prescription");
        prescriptionLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        prescriptionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prescriptionLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prescriptionLabel)
                .addGap(89, 89, 89))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(prescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));

        operationLabel.setBackground(new java.awt.Color(255, 255, 255));
        operationLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        operationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        operationLabel.setText("Opération");
        operationLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        operationLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                operationLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(operationLabel)
                .addGap(100, 100, 100))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(operationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));

        observationLabel.setBackground(new java.awt.Color(255, 255, 255));
        observationLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        observationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        observationLabel.setText("Observation");
        observationLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        observationLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                observationLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(observationLabel)
                .addGap(86, 86, 86))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(observationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));

        commentaireLabel.setBackground(new java.awt.Color(255, 255, 255));
        commentaireLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        commentaireLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        commentaireLabel.setText("Commentaire");
        commentaireLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        commentaireLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                commentaireLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(commentaireLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(commentaireLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));

        resultatLabel.setBackground(new java.awt.Color(255, 255, 255));
        resultatLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        resultatLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resultatLabel.setText("Résultats");
        resultatLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resultatLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultatLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(resultatLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultatLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));

        lettreSortieLabel.setBackground(new java.awt.Color(255, 255, 255));
        lettreSortieLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        lettreSortieLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lettreSortieLabel.setText("Lettre de sortie");
        lettreSortieLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lettreSortieLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lettreSortieLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(lettreSortieLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lettreSortieLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel47, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel44, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(patientLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 134, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(patientLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 10, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deconnexionIconButtonMouseClicked

    private void seeDMAIcon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMAIcon1MouseClicked
seeDMALabel2MouseClicked(evt);        
// TODO add your handling code here:
    }//GEN-LAST:event_seeDMAIcon1MouseClicked

    private void seeDMAIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMAIconMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seeDMAIconMouseClicked

    private void seeDMALabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMALabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seeDMALabelMouseClicked

    private void deconnexionLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deconnexionLabel2MouseClicked
        SQLWarningsExceptions.printWarnings(conn);
        try {
            conn.close();
            Connexion connexion = new Connexion();
            this.setVisible(false);
            connexion.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deconnexionIcon2MouseClicked

    private void prescriptionLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prescriptionLabelMouseClicked
        String identite = professionnelLabel.getText();
        Ajout_Doc_PH ajoutDocInterface;
        if(maConsultation.getDateFinSejour()==null){
            try {
            DocumentType type_doc = DocumentType.PRESCRIPTION;
            
            ajoutDocInterface = new Ajout_Doc_PH(this.conn, identite, IPP, mydm, maConsultation, type_doc);
            this.setVisible(false);
            ajoutDocInterface.setVisible(true);
            } catch (SQLException ex) {
                message.setText("Ajout de document impossible");
                dialogue.setVisible(true);
                Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                message.setText("Ajout de document impossible");
                dialogue.setVisible(true);
                Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            message.setText("Il n'y a pas séjour ouvert");
            dialogue.setVisible(true);
        }
        
        
    }//GEN-LAST:event_prescriptionLabelMouseClicked

    private void operationLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_operationLabelMouseClicked
       String identite = professionnelLabel.getText();
        Ajout_Doc_PH ajoutDocInterface;
        if(maConsultation.getDateFinSejour()==null){
            try {
            DocumentType type_doc = DocumentType.OPERATION;
            
            ajoutDocInterface = new Ajout_Doc_PH(this.conn, identite, IPP, mydm, maConsultation, type_doc);
            this.setVisible(false);
            ajoutDocInterface.setVisible(true);
            } catch (SQLException ex) {
                message.setText("Ajout de document impossible");
                dialogue.setVisible(true);
                Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                message.setText("Ajout de document impossible");
                dialogue.setVisible(true);
                Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            message.setText("Il n'y a pas séjour ouvert");
            dialogue.setVisible(true);
        }
    }//GEN-LAST:event_operationLabelMouseClicked

    private void observationLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_observationLabelMouseClicked
        String identite = professionnelLabel.getText();
        Ajout_Doc_PH ajoutDocInterface;
        if(maConsultation.getDateFinSejour()==null){
            try {
            DocumentType type_doc = DocumentType.OBSERVATION;
            
            ajoutDocInterface = new Ajout_Doc_PH(this.conn, identite, IPP, mydm, maConsultation, type_doc);
            this.setVisible(false);
            ajoutDocInterface.setVisible(true);
            } catch (SQLException ex) {
                message.setText("Ajout de document impossible");
                dialogue.setVisible(true);
                Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                message.setText("Ajout de document impossible");
                dialogue.setVisible(true);
                Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            message.setText("Il n'y a pas séjour ouvert");
            dialogue.setVisible(true);
        }
    }//GEN-LAST:event_observationLabelMouseClicked

    private void commentaireLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_commentaireLabelMouseClicked
        String identite = professionnelLabel.getText();
        Ajout_Doc_PH ajoutDocInterface;
        if(maConsultation.getDateFinSejour()==null){
            try {
            DocumentType type_doc = DocumentType.COMMENTAIRE;
            
            ajoutDocInterface = new Ajout_Doc_PH(this.conn, identite, IPP, mydm, maConsultation, type_doc);
            this.setVisible(false);
            ajoutDocInterface.setVisible(true);
            } catch (SQLException ex) {
                message.setText("Ajout de document impossible");
                dialogue.setVisible(true);
                Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                message.setText("Ajout de document impossible");
                dialogue.setVisible(true);
                Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            message.setText("Il n'y a pas séjour ouvert");
            dialogue.setVisible(true);
        }
    }//GEN-LAST:event_commentaireLabelMouseClicked

    private void resultatLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultatLabelMouseClicked
        String identite = professionnelLabel.getText();
        Ajout_Doc_PH ajoutDocInterface;
        if(maConsultation.getDateFinSejour()==null){
            try {
            DocumentType type_doc = DocumentType.RESULTAT;
            
            ajoutDocInterface = new Ajout_Doc_PH(this.conn, identite, IPP, mydm, maConsultation, type_doc);
            this.setVisible(false);
            ajoutDocInterface.setVisible(true);
            } catch (SQLException ex) {
                message.setText("Ajout de document impossible");
                dialogue.setVisible(true);
                Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                message.setText("Ajout de document impossible");
                dialogue.setVisible(true);
                Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            message.setText("Il n'y a pas séjour ouvert");
            dialogue.setVisible(true);
        }
    }//GEN-LAST:event_resultatLabelMouseClicked

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

    private void seeDMALabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMALabel2MouseClicked
         String identite = professionnelLabel.getText();
        try {
            Mes_Patients_PH interfacePH = new Mes_Patients_PH(conn, identite);
            this.setVisible(false);
            interfacePH.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Mes_Patients_PH.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Mes_Patients_PH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_seeDMALabel2MouseClicked

    private void seeDMAIcon2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMAIcon2MouseClicked
     seeDMALabel1MouseClicked(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_seeDMAIcon2MouseClicked

    private void documentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_documentTableMouseClicked
        int num_doc = documentTable.getSelectedRow();
        String numS = mydm.getNumS();
        String identite = professionnelLabel.getText();
        try {
            List<Consultation> sejoursPat = new requetes().getSejours(this.conn, IPP);
            List<Document> documents = new ArrayList<>();
            documents = new requetes().getDocuments(conn, IPP, numS);
            if(sejoursPat.size() == 0){
                Document document = documents.get(num_doc);
                
                Visualisation_Doc_PH interfaceDMView = new Visualisation_Doc_PH(this.conn, identite,document,IPP);
                this.setVisible(false);
                interfaceDMView.setVisible(true);
               
            }
            else{
                Consultation consult = sejoursPat.get(num_doc);
                
                Visualisation_Doc_PH interfaceDMView = new Visualisation_Doc_PH(this.conn, identite,consult,IPP);
                this.setVisible(false);
                interfaceDMView.setVisible(true);
            }
        } catch (SQLException ex) {
            message.setText("Visualisation document impossible");
                    dialogue.setVisible(true);
            Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_documentTableMouseClicked

    private void lettreSortieLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lettreSortieLabelMouseClicked
        String identite = professionnelLabel.getText();
        Ajout_Doc_PH ajoutDocInterface;
        if(maConsultation.getDateFinSejour()==null){
            try {
            DocumentType type_doc = DocumentType.LETTRES;
            
            ajoutDocInterface = new Ajout_Doc_PH(this.conn, identite, IPP, mydm, maConsultation, type_doc);
            this.setVisible(false);
            ajoutDocInterface.setVisible(true);
            } catch (SQLException ex) {
                message.setText("Ajout de document impossible");
                dialogue.setVisible(true);
                Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                message.setText("Ajout de document impossible");
                dialogue.setVisible(true);
                Logger.getLogger(Visualisation_DM_PH.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            message.setText("Il n'y a pas séjour ouvert");
            dialogue.setVisible(true);
        }
    }//GEN-LAST:event_lettreSortieLabelMouseClicked

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
            java.util.logging.Logger.getLogger(Visualisation_DM_PH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Visualisation_DM_PH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Visualisation_DM_PH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Visualisation_DM_PH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Visualisation_DM_PH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PHLabel1;
    private javax.swing.JLabel adressLabel;
    private javax.swing.JLabel commentaireLabel;
    private javax.swing.JLabel dateDebutLabel;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lettreSortieLabel;
    private javax.swing.JLabel logo;
    private javax.swing.JTextPane message;
    private javax.swing.JLabel nChambreLabel;
    private javax.swing.JLabel natureLabel;
    private javax.swing.JLabel nomPrenomLabel1;
    private javax.swing.JLabel numberOne;
    private javax.swing.JLabel numeroDeux;
    private javax.swing.JLabel numeroTrois;
    private javax.swing.JLabel numeroUn;
    private javax.swing.JLabel observationLabel;
    private javax.swing.JLabel operationLabel;
    private javax.swing.JLabel patientLabel;
    private javax.swing.JLabel prescriptionLabel;
    private javax.swing.JLabel professionnelLabel;
    private javax.swing.JLabel resultatLabel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel seeDMAIcon;
    private javax.swing.JLabel seeDMAIcon1;
    private javax.swing.JLabel seeDMAIcon2;
    private javax.swing.JLabel seeDMALabel;
    private javax.swing.JLabel seeDMALabel1;
    private javax.swing.JLabel seeDMALabel2;
    private javax.swing.JLabel serviceGLabel;
    private javax.swing.JLabel serviceRLabel;
    private javax.swing.JLabel sexLabel;
    // End of variables declaration//GEN-END:variables
}
