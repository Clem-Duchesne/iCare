/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.ui;

import java.sql.Connection;
import iHealth.db.SQLWarningsExceptions;
import iHealth.db.requetes;
import iHealth.nf.Chambre;
import iHealth.nf.Consultation;
import iHealth.nf.DMA;
import iHealth.nf.Lit;
import iHealth.nf.Localisation;
import iHealth.nf.NumeroSejour;
import iHealth.nf.PH;
import iHealth.nf.Patient;
import iHealth.nf.Service;
import iHealth.nf.Sexe;
import iHealth.nf.toDate;
import iHealth.nf.toSexe;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cleme
 */
public class Visualisation_DMA extends javax.swing.JFrame {

    
    private Connection conn = null;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private List<Consultation> consultations;
    private DefaultListModel patientsModel= new DefaultListModel();
    private DefaultComboBoxModel PHsModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel ServiceModel = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel ServiceModel2 = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel localisation = new DefaultComboBoxModel<>();
    private String IPP;
    
    /**
     * Creates new form Creation_DMA
     */
    public Visualisation_DMA(Connection conn, String identite, String IPP) throws SQLException, ParseException {
        this.conn = conn;
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //Mise en place des images
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

        //this.setResizable(false);

        // Récupérer le nom et prénom de la personne connectée
        professionnelLabel.setText(identite);

        //affichage liste de patients 
        jPanel3.setFocusable(true);   
        
        //Identité du patient
        Patient mypatient = new requetes().getPatientIPP(conn, IPP);
        patientLabel.setText("Patient : " + mypatient.getNom() + " " + mypatient.getPrenom() + " - IPP : " + mypatient.getIPP());
        sexeLabel.setText("Sexe : " + new toSexe().sexeToString(mypatient.getSexe()));
        adresseLabel.setText("Adresse : " + mypatient.getAdresse());
        dateNaissanceLabel.setText("Date de naissance : " + mypatient.getDateNaissance());
        
        //Liste consultations et hospitalisations
        tableModel.addColumn("Numéro de séjour");
        tableModel.addColumn("Date début séjour");
        tableModel.addColumn("Date fin séjour");
        tableModel.addColumn("PH responsable");
        tableModel.addColumn("Localisation");
        tableModel.addColumn("Nature prestation");
        tableModel.addColumn("Lettre de sortie");
        
        this.IPP = IPP;
        
        consultations = new requetes().getSejours(this.conn, IPP);
        
        int index =0;
        for(Consultation c : consultations){
            PH ph = new requetes().getPH(conn, c.getNumP());
            String lettre_check = "";
            if(c.getLettre().getLettre_text() != ""){
                lettre_check = "Voir lettre de sortie";
            }
            
            tableModel.insertRow(index, new Object[] { c.getNumeroSejour().getNumero(),c.getDateDebutSejour(), c.getDateFinSejour(), ph.getNom() + " " + ph.getPrenom(),"N° : " + c.getLit().getChambre().getNumeroChambre() + " - " + c.getLit().getChambre().getServiceGeographique() , c.getNaturePrestation(), lettre_check});
            index++; 
        }
        
        consultationTable.setModel(tableModel);
        
        //list comboBox PH
        List<PH> PHs = new requetes().getPHs(conn);
        List<Service> services = new ArrayList<>();
        List<Localisation> localisations = new ArrayList<>();
        
        for (Service s : Service.values()) { 
             services.add(s);
        } 
        for (Localisation l : Localisation.values()){
            localisations.add(l);
        }

        ServiceModel.addElement("Service Responsable");
        ServiceModel2.addElement("Service Géographique");
       for(int i=0;i<services.size();i++){
           ServiceModel.addElement("" + services.get(i) + "");
           ServiceModel2.addElement("" + services.get(i) + ""); 
       }
       for(int i=0;i<localisations.size();i++){
           localisation.addElement(localisations.get(i) +"");
       }
            
        for(int i=0;i<PHs.size();i++){
            PHsModel.addElement(PHs.get(i).getNumP() + " - " + PHs.get(i).getNom() + " " + PHs.get(i).getPrenom() +  " - " + PHs.get(i).getService());
        }
        praticienComboBox.setModel(PHsModel); 
        serviceGeographiqueComboBox.setModel(ServiceModel2);
        serviceResponsableComboBox.setModel(ServiceModel);
        locComboBox.setModel(localisation);
    }
    public Visualisation_DMA() {
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
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        numeroUn = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        numeroDeux = new javax.swing.JLabel();
        sexeLabel = new javax.swing.JLabel();
        dateNaissanceLabel = new javax.swing.JLabel();
        adresseLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        consultationTable = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        naturePrestationTextField = new javax.swing.JTextField();
        addPatientButton1 = new javax.swing.JButton();
        praticienComboBox = new javax.swing.JComboBox<>();
        serviceResponsableComboBox = new javax.swing.JComboBox<>();
        serviceGeographiqueComboBox = new javax.swing.JComboBox<>();
        locComboBox = new javax.swing.JComboBox<>();
        chambreLabel = new javax.swing.JTextField();
        patientLabel = new javax.swing.JLabel();

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
        setTitle("iHealth - Visualisation DMA");
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
        addDMALabel.setText("Nouveau DMA");
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
        seeDMALabel.setText("Visualiser DMA");
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
                    .addComponent(deconnexionLabel)
                    .addComponent(deconnexionIconButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addDMAIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addDMALabel)
                    .addComponent(seeDMAIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seeDMALabel))
                .addGap(13, 13, 13))
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
                .addGap(98, 98, 98))
        );

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 36)); // NOI18N
        jLabel1.setText("Visualisation DMA");

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
        jLabel3.setText("Secrétaire Administrative : ");

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
        jLabel2.setText("Informations du DMA");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(37, 158, 185));
        jLabel5.setText("Liste des consultations et hospitalisation");

        jLabel6.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(37, 158, 185));
        jLabel6.setText("Informations sur le patient");

        sexeLabel.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        sexeLabel.setText("Sexe : ");

        dateNaissanceLabel.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        dateNaissanceLabel.setText("Date de naissance :");

        adresseLabel.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        adresseLabel.setText("Adresse : ");

        consultationTable.setFont(new java.awt.Font("Quicksand", 0, 11)); // NOI18N
        consultationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        consultationTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                consultationTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(consultationTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(numeroUn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sexeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateNaissanceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(adresseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numeroDeux, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(75, 75, 75))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(numeroUn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sexeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateNaissanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adresseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(numeroDeux, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 246, 255));

        jLabel4.setFont(new java.awt.Font("Quicksand", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Ajout d’une Consultation");

        jLabel7.setFont(new java.awt.Font("Quicksand", 0, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Hospitalisation");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
                .addGap(64, 64, 64))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(243, 245, 255));

        naturePrestationTextField.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        naturePrestationTextField.setForeground(new java.awt.Color(102, 102, 102));
        naturePrestationTextField.setText("Nature de la prestation");
        naturePrestationTextField.setToolTipText("");
        naturePrestationTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                naturePrestationTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                naturePrestationTextFieldFocusLost(evt);
            }
        });
        naturePrestationTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                naturePrestationTextFieldMouseClicked(evt);
            }
        });

        addPatientButton1.setBackground(new java.awt.Color(237, 100, 100));
        addPatientButton1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        addPatientButton1.setForeground(new java.awt.Color(255, 255, 255));
        addPatientButton1.setText("Ajout séjour");
        addPatientButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addPatientButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPatientButton1MouseClicked(evt);
            }
        });
        addPatientButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPatientButton1ActionPerformed(evt);
            }
        });

        praticienComboBox.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N

        serviceResponsableComboBox.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N

        serviceGeographiqueComboBox.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N

        locComboBox.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N

        chambreLabel.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        chambreLabel.setText("Numéro de la chambre");
        chambreLabel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                chambreLabelFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                chambreLabelFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 117, Short.MAX_VALUE)
                .addComponent(addPatientButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(locComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chambreLabel))
                    .addComponent(serviceGeographiqueComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(praticienComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(naturePrestationTextField)
                    .addComponent(serviceResponsableComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(praticienComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(naturePrestationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(serviceResponsableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serviceGeographiqueComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(locComboBox)
                    .addComponent(chambreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addGap(95, 95, 95)
                .addComponent(addPatientButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        patientLabel.setFont(new java.awt.Font("Quicksand", 0, 24)); // NOI18N
        patientLabel.setForeground(new java.awt.Color(217, 21, 21));
        patientLabel.setText("Patient ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(patientLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(patientLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(30, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            Logger.getLogger(Visualisation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DMA.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Visualisation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deconnexionIconButtonMouseClicked

    private void addDMAIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addDMAIconMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addDMAIconMouseClicked

    private void addDMALabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addDMALabelMouseClicked
        String identite = professionnelLabel.getText();
        try {
            Creation_DMA createDMA = new Creation_DMA(this.conn, identite);
            this.setVisible(false);
            createDMA.setVisible(true);
        } catch (SQLException ex) {
            message.setText("Retour à l'accueil impossible");
            dialogue.setVisible(true);
            Logger.getLogger(Visualisation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            message.setText("Retour à l'accueil impossible");
            dialogue.setVisible(true);
            Logger.getLogger(Visualisation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addDMALabelMouseClicked

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
            Logger.getLogger(Visualisation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DMA.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Visualisation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visualisation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deconnexionIcon2MouseClicked

    private void naturePrestationTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_naturePrestationTextFieldFocusGained
       if(naturePrestationTextField.getText().equals("Nature de la prestation")){
           naturePrestationTextField.setText("");
       }
    }//GEN-LAST:event_naturePrestationTextFieldFocusGained

    private void naturePrestationTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_naturePrestationTextFieldFocusLost
        if(naturePrestationTextField.getText().equals("")){
           naturePrestationTextField.setText("Nature de la prestation");
       }
    }//GEN-LAST:event_naturePrestationTextFieldFocusLost

    private void naturePrestationTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_naturePrestationTextFieldMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_naturePrestationTextFieldMouseClicked

    private void addPatientButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPatientButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addPatientButton1ActionPerformed

    private void consultationTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultationTableMouseClicked
        int num_sejour = consultationTable.getSelectedRow();
        Consultation consultation_clicked = consultations.get(num_sejour);
        String identite = professionnelLabel.getText();
        
        try {
            Visualisation_DMA_View interfaceDMAView = new Visualisation_DMA_View(this.conn, identite,consultation_clicked);
            this.setVisible(false);
            interfaceDMAView.setVisible(true);
        } catch (SQLException ex) {
            message.setText("Impossible de visualiser la lettre de sortie");
            dialogue.setVisible(true);
            Logger.getLogger(Visualisation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            message.setText("Impossible de visualiser la lettre de sortie");
            dialogue.setVisible(true);
            Logger.getLogger(Visualisation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }//GEN-LAST:event_consultationTableMouseClicked

    private void addPatientButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPatientButton1MouseClicked
        


        String praticien = (String) praticienComboBox.getSelectedItem();
        String numP = praticien.substring(0,7);
        //System.out.print(numP);
        String naturePrestation = naturePrestationTextField.getText();
        String num_chambre =chambreLabel.getText();
        String service_geo = serviceGeographiqueComboBox.getSelectedItem().toString();
        String service_responsable = serviceResponsableComboBox.getSelectedItem().toString();
        String loc = (String) locComboBox.getSelectedItem();
       
        if(praticien!=null & naturePrestation!=null & !naturePrestation.equals(" ") & !naturePrestation.equals("Nature de la prestation") & (service_responsable!="Service responsable") & service_geo != "Service géographique" & num_chambre != "Numéro chambre" & num_chambre != " " & num_chambre != null){

            java.sql.Date current_date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
            DMA dma = null;    
            NumeroSejour numeroSejour = new NumeroSejour(current_date);    
            Chambre chambre = new Chambre(Service.valueOf(service_geo),Service.valueOf(service_responsable), num_chambre);
            Lit lit = new Lit(Localisation.valueOf(loc), chambre);
            Consultation consultation = new Consultation(numeroSejour, IPP, current_date, numP, naturePrestation, lit);
            dma = new DMA(IPP,current_date);
            try {
                    new requetes().addSejour(this.conn, IPP, consultation);
                    new requetes().addLocalisation(conn, IPP, lit, consultation);
                    message.setText("Le nouveau séjour a bien été créé");
                    dialogue.setVisible(true);
                     consultations = new requetes().getSejours(this.conn, IPP);
                    int index =0;
                    for(Consultation c : consultations){
                        PH ph = new requetes().getPH(conn, c.getNumP());
                        String lettre_check = "";
                        if(c.getLettre().getLettre_text() != null){
                            lettre_check = "Voir lettre de sortie";
                        }
                        tableModel.insertRow(index, new Object[] { c.getNumeroSejour().getNumero(),c.getDateDebutSejour(), c.getDateFinSejour(), ph.getNom() + " " + ph.getPrenom(),"N° : " + c.getLit().getChambre().getNumeroChambre() + " - " + c.getLit().getChambre().getServiceGeographique() , c.getNaturePrestation(), lettre_check});
                        index++; 
                    }

                    consultationTable.setModel(tableModel);

            } catch (SQLException ex) {
                message.setText("Il semblerait qu'il y ait eu un problème lors de l'ajout du séjour");
                dialogue.setVisible(true);
                Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                message.setText("Il semblerait qu'il y ait eu un problème lors de l'ajout du séjour");
                dialogue.setVisible(true);
                Logger.getLogger(Visualisation_DMA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            message.setText("Veuillez remplir la totalité des champs avant de valider");
            dialogue.setVisible(true);
        }
    }//GEN-LAST:event_addPatientButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        dialogue.setVisible(false);
    }//GEN-LAST:event_jButton2MouseClicked

    private void chambreLabelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chambreLabelFocusGained
        if(chambreLabel.getText().equals("Numéro de la chambre")){
           chambreLabel.setText("");
       }
    }//GEN-LAST:event_chambreLabelFocusGained

    private void chambreLabelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chambreLabelFocusLost
        if(chambreLabel.getText().equals("")){
           chambreLabel.setText("Numéro de la chambre");
       }
    }//GEN-LAST:event_chambreLabelFocusLost

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
            java.util.logging.Logger.getLogger(Visualisation_DMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Visualisation_DMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Visualisation_DMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Visualisation_DMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Visualisation_DMA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addDMAIcon;
    private javax.swing.JLabel addDMALabel;
    private javax.swing.JButton addPatientButton1;
    private javax.swing.JLabel adresseLabel;
    private javax.swing.JTextField chambreLabel;
    private javax.swing.JTable consultationTable;
    private javax.swing.JLabel dateNaissanceLabel;
    private javax.swing.JLabel deconnexionIcon2;
    private javax.swing.JLabel deconnexionIconButton;
    private javax.swing.JLabel deconnexionLabel;
    private javax.swing.JLabel deconnexionLabel2;
    private javax.swing.JDialog dialogue;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> locComboBox;
    private javax.swing.JLabel logo;
    private javax.swing.JTextPane message;
    private javax.swing.JTextField naturePrestationTextField;
    private javax.swing.JLabel numeroDeux;
    private javax.swing.JLabel numeroUn;
    private javax.swing.JLabel patientLabel;
    private javax.swing.JComboBox<String> praticienComboBox;
    private javax.swing.JLabel professionnelLabel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel seeDMAIcon;
    private javax.swing.JLabel seeDMALabel;
    private javax.swing.JComboBox<String> serviceGeographiqueComboBox;
    private javax.swing.JComboBox<String> serviceResponsableComboBox;
    private javax.swing.JLabel sexeLabel;
    // End of variables declaration//GEN-END:variables
}
