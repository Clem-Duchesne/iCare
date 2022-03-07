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
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

/**
 *
 * @author cleme
 */
public class Creation_DMA extends javax.swing.JFrame {

    
    private Connection conn = null;
    private String selectedPatient = null;
    private DefaultListModel patientsModel= new DefaultListModel();
    private DefaultComboBoxModel PHsModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel ServiceModel = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel ServiceModel2 = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel localisation = new DefaultComboBoxModel<>();
    
    /**
     * Creates new form Creation_DMA
     */
    public Creation_DMA(Connection conn, String[] identite ) throws SQLException, ParseException {
        this.conn = conn;
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
        
        ImageIcon icone7 = new ImageIcon("src/iHealth/img/loupe.png");
        java.awt.Image img7 = icone7.getImage();
        java.awt.Image newImg7 = img7.getScaledInstance(25,25,100);
        icone7=new ImageIcon(newImg7);
        searchIcon.setIcon(icone7);
        //this.setResizable(false);
        

        // Récupérer le nom et prénom de la personne connectée
        professionnelLabel.setText(identite[0] + " " + identite[1]);
        
        
        //affichage liste de patients 
        jPanel3.setFocusable(true);
      

        //affichage liste de patients 
        List<Patient> patients = new requetes().getPatients(conn);
 
        for(int i=0;i<patients.size();i++){
            patientsModel.addElement(patients.get(i).getIPP() + " - " + patients.get(i).getNom() + " " + patients.get(i).getPrenom() + "");
            patientList.setModel(patientsModel);   
          
        }
        
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
    public Creation_DMA() throws ParseException {
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
        
        ImageIcon icone7 = new ImageIcon("src/iHealth/img/loupe.png");
        java.awt.Image img7 = icone7.getImage();
        java.awt.Image newImg7 = img7.getScaledInstance(25,25,100);
        icone7=new ImageIcon(newImg7);
        searchIcon.setIcon(icone7);
        //this.setResizable(false);
        
        jPanel3.setFocusable(true);
        
        List <Patient> patients = new ArrayList<>();
        
        Patient patient = new Patient("0012345", "HUGO", "Victor", "27/10/2000", Sexe.HOMME, "14 rue du Piquet POTEAU");
        Patient patient1 = new Patient("0012355", "IOV", "Cyprien", "29/10/2000", Sexe.HOMME, "18 rue du Piquet POTEAU");
        Patient patient2 = new Patient("0012345", "MONT", "Yves", "27/02/2000", Sexe.HOMME, "30 rue Gabriel Péri GRENOBLE");
        
        patients.add(patient);
        patients.add(patient1);
        patients.add(patient2);
        
        for(int i=0;i<patients.size();i++){
            patientsModel.addElement("" + patients.get(i).getNom() + " " + patients.get(i).getPrenom() + "");
            patientList.setModel(patientsModel);
        }
        
       
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel2 = new javax.swing.JPanel();
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
        nameTextfield = new javax.swing.JTextField();
        numeroUn = new javax.swing.JLabel();
        prenomTextfield = new javax.swing.JTextField();
        dateNiassanceTextField = new javax.swing.JTextField();
        adresseTextfield = new javax.swing.JTextField();
        addConsultation = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        numeroDeux = new javax.swing.JLabel();
        naturePrestationTextfield = new javax.swing.JTextField();
        praticienComboBox = new javax.swing.JComboBox<>();
        addPatientButton = new javax.swing.JButton();
        sexeTextfield = new javax.swing.JComboBox<>();
        dateErrorMessage = new javax.swing.JLabel();
        serviceGeographiqueComboBox = new javax.swing.JComboBox<>();
        serviceResponsableComboBox = new javax.swing.JComboBox<>();
        locComboBox = new javax.swing.JComboBox<>();
        chambreLabel = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        searchTextfield = new javax.swing.JTextField();
        searchIcon = new javax.swing.JLabel();
        patientTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientList = new javax.swing.JList<>();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
                    .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addDMAIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addDMALabel)
                    .addComponent(seeDMALabel)
                    .addComponent(seeDMAIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deconnexionLabel)
                    .addComponent(deconnexionIconButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(100, 100, 100))
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
        jLabel3.setText("Secrétaire Administrative : ");

        professionnelLabel.setFont(new java.awt.Font("Quicksand", 0, 24)); // NOI18N

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
        jLabel2.setText("Création d'un nouveau DMA");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(37, 158, 185));
        jLabel5.setText("Ajout d'une consultation ou hospitalisation");

        nameTextfield.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        nameTextfield.setForeground(new java.awt.Color(102, 102, 102));
        nameTextfield.setText("Nom");
        nameTextfield.setToolTipText("");
        nameTextfield.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nameTextfieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameTextfieldFocusLost(evt);
            }
        });
        nameTextfield.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameTextfieldMouseClicked(evt);
            }
        });

        prenomTextfield.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        prenomTextfield.setForeground(new java.awt.Color(102, 102, 102));
        prenomTextfield.setText("Prénom");
        prenomTextfield.setToolTipText("");
        prenomTextfield.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                prenomTextfieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                prenomTextfieldFocusLost(evt);
            }
        });
        prenomTextfield.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prenomTextfieldMouseClicked(evt);
            }
        });

        dateNiassanceTextField.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        dateNiassanceTextField.setForeground(new java.awt.Color(102, 102, 102));
        dateNiassanceTextField.setText("Date de naissance");
        dateNiassanceTextField.setToolTipText("");
        dateNiassanceTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dateNiassanceTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateNiassanceTextFieldFocusLost(evt);
            }
        });
        dateNiassanceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateNiassanceTextFieldActionPerformed(evt);
            }
        });

        adresseTextfield.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        adresseTextfield.setForeground(new java.awt.Color(102, 102, 102));
        adresseTextfield.setText("Adresse");
        adresseTextfield.setToolTipText("");
        adresseTextfield.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adresseTextfieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                adresseTextfieldFocusLost(evt);
            }
        });
        adresseTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adresseTextfieldActionPerformed(evt);
            }
        });

        addConsultation.setBackground(new java.awt.Color(237, 100, 100));
        addConsultation.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        addConsultation.setForeground(new java.awt.Color(255, 255, 255));
        addConsultation.setText("Ajout consultation");
        addConsultation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addConsultation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addConsultationMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(37, 158, 185));
        jLabel6.setText("Informations sur le patient");

        naturePrestationTextfield.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        naturePrestationTextfield.setForeground(new java.awt.Color(102, 102, 102));
        naturePrestationTextfield.setText("Nature de la prestation");
        naturePrestationTextfield.setToolTipText("");
        naturePrestationTextfield.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                naturePrestationTextfieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                naturePrestationTextfieldFocusLost(evt);
            }
        });
        naturePrestationTextfield.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                naturePrestationTextfieldMouseClicked(evt);
            }
        });
        naturePrestationTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                naturePrestationTextfieldActionPerformed(evt);
            }
        });

        praticienComboBox.setToolTipText("");

        addPatientButton.setBackground(new java.awt.Color(237, 100, 100));
        addPatientButton.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        addPatientButton.setForeground(new java.awt.Color(255, 255, 255));
        addPatientButton.setText("Ajouter un patient");
        addPatientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addPatientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPatientButtonActionPerformed(evt);
            }
        });

        sexeTextfield.setModel(new DefaultComboBoxModel(Sexe.values()));
        sexeTextfield.setSelectedItem("Sexe");

        dateErrorMessage.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        dateErrorMessage.setText("Format : jour/mois/annee (JJ/MM/AAAA)");

        serviceGeographiqueComboBox.setToolTipText("");
        serviceGeographiqueComboBox.setName("Service Géographique"); // NOI18N

        serviceResponsableComboBox.setToolTipText("");
        serviceResponsableComboBox.setName("Service Géographique"); // NOI18N

        locComboBox.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N

        chambreLabel.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        chambreLabel.setForeground(new java.awt.Color(102, 102, 102));
        chambreLabel.setText("Numéro Chambre");
        chambreLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chambreLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(229, 229, 229)
                                .addComponent(addPatientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(167, 167, 167)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(naturePrestationTextfield)
                                        .addComponent(praticienComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(serviceResponsableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(numeroDeux, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(adresseTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sexeTextfield, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dateNiassanceTextField)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(dateErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 30, Short.MAX_VALUE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(numeroUn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(nameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prenomTextfield))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(addConsultation, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(serviceGeographiqueComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chambreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(locComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numeroUn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prenomTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(sexeTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateNiassanceTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateErrorMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                        .addGap(47, 47, 47))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(adresseTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(addPatientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numeroDeux, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(praticienComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(naturePrestationTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(serviceResponsableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(serviceGeographiqueComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chambreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(locComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(addConsultation, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(204, 246, 255));

        jLabel4.setFont(new java.awt.Font("Quicksand", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Liste de patients");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(206, 206, 206))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(243, 245, 255));

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

        patientTable.setBackground(new java.awt.Color(255, 255, 255));

        patientList.setFont(new java.awt.Font("Quicksand", 0, 14)); // NOI18N
        patientList.setForeground(new java.awt.Color(51, 51, 51));
        patientList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        patientList.setAlignmentX(80.0F);
        patientList.setSelectionBackground(new java.awt.Color(204, 204, 204));
        patientList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(patientList);

        javax.swing.GroupLayout patientTableLayout = new javax.swing.GroupLayout(patientTable);
        patientTable.setLayout(patientTableLayout);
        patientTableLayout.setHorizontalGroup(
            patientTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        patientTableLayout.setVerticalGroup(
            patientTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(patientTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(searchTextfield)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(searchIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(82, 82, 82))
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
            Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deconnexionIconButtonMouseClicked

    private void addDMAIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addDMAIconMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addDMAIconMouseClicked

    private void addDMALabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addDMALabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addDMALabelMouseClicked

    private void seeDMAIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMAIconMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seeDMAIconMouseClicked

    private void seeDMALabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeDMALabelMouseClicked
        
        String patient = patientList.getSelectedValue();
        String IPP = patient.substring(0, 9);
        
        Visualisation_DMA interfaceSecretaireA = null;
        String identite = professionnelLabel.getText();
        try {
            interfaceSecretaireA = new Visualisation_DMA(this.conn, identite, IPP);
        } catch (SQLException ex) {
            Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setVisible(false);
        interfaceSecretaireA.setVisible(true);
        
    }//GEN-LAST:event_seeDMALabelMouseClicked

    private void nameTextfieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameTextfieldFocusGained
       if(nameTextfield.getText().equals("Nom")){
            nameTextfield.setText("");
        }
    }//GEN-LAST:event_nameTextfieldFocusGained

    private void nameTextfieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameTextfieldMouseClicked
        
    }//GEN-LAST:event_nameTextfieldMouseClicked

    private void prenomTextfieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prenomTextfieldFocusGained
        if(prenomTextfield.getText().equals("Prénom")){
            prenomTextfield.setText("");
        }
    }//GEN-LAST:event_prenomTextfieldFocusGained

    private void prenomTextfieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prenomTextfieldMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomTextfieldMouseClicked

    private void dateNiassanceTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateNiassanceTextFieldFocusGained
        if(dateNiassanceTextField.getText().equals("Date de naissance")){
            dateNiassanceTextField.setText("");
        }
    }//GEN-LAST:event_dateNiassanceTextFieldFocusGained

    private void dateNiassanceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateNiassanceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateNiassanceTextFieldActionPerformed

    private void adresseTextfieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adresseTextfieldFocusGained
        if(adresseTextfield.getText().equals("Adresse")){
            adresseTextfield.setText("");
        }
    }//GEN-LAST:event_adresseTextfieldFocusGained

    private void adresseTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adresseTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adresseTextfieldActionPerformed

    private void nameTextfieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameTextfieldFocusLost
        if(nameTextfield.getText().equals("")){
            nameTextfield.setText("Nom");
        }
    }//GEN-LAST:event_nameTextfieldFocusLost

    private void prenomTextfieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prenomTextfieldFocusLost
        if(prenomTextfield.getText().equals("")){
            prenomTextfield.setText("Prénom");
        }
    }//GEN-LAST:event_prenomTextfieldFocusLost

    private void dateNiassanceTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateNiassanceTextFieldFocusLost
        if(dateNiassanceTextField.getText().equals("")){
            dateNiassanceTextField.setText("Date de naissance");
        }
    }//GEN-LAST:event_dateNiassanceTextFieldFocusLost

    private void adresseTextfieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adresseTextfieldFocusLost
        if(adresseTextfield.getText().equals("")){
            adresseTextfield.setText("Adresse");
        }
    }//GEN-LAST:event_adresseTextfieldFocusLost

    private void naturePrestationTextfieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_naturePrestationTextfieldFocusGained
        
    }//GEN-LAST:event_naturePrestationTextfieldFocusGained

    private void naturePrestationTextfieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_naturePrestationTextfieldFocusLost
        if(naturePrestationTextfield.getText().equals("")){
            naturePrestationTextfield.setText("Nature prestation");
        }
    }//GEN-LAST:event_naturePrestationTextfieldFocusLost

    private void naturePrestationTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naturePrestationTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_naturePrestationTextfieldActionPerformed

    private void deconnexionLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deconnexionLabel2MouseClicked
        SQLWarningsExceptions.printWarnings(conn);
        try {
            conn.close();
            Connexion connexion = new Connexion();
            this.setVisible(false);
            connexion.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deconnexionIcon2MouseClicked

    private void searchTextfieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextfieldFocusGained
        if(searchTextfield.getText().equals("Rechercher")){
            searchTextfield.setText("");
        }
    }//GEN-LAST:event_searchTextfieldFocusGained

    private void searchTextfieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextfieldFocusLost
        if(searchTextfield.getText().equals("")){
            searchTextfield.setText("Rechercher");
        }
    }//GEN-LAST:event_searchTextfieldFocusLost

    private void addPatientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPatientButtonActionPerformed
        //ajout d'un patient
        //test champ vide
        
        String nom = nameTextfield.getText();
        String prenom = prenomTextfield.getText();
        String dateNaissance = dateNiassanceTextField.getText();
        Sexe sexe = (Sexe) sexeTextfield.getSelectedItem();
        String adresse = adresseTextfield.getText();
         
        if((nom!=null & !nom.equals(" ") & !nom.equals("Nom")) & (prenom!=null & !prenom.equals(" ") & !prenom.equals("Prénom")) & (dateNaissance !=null & !dateNaissance.equals(" ") & dateNaissance.length() <=10 & !dateNaissance.equals("Date de naissance")) & (adresse!=null & !adresse.equals(" ") & !adresse.equals("Adresse") )){
            java.sql.Date date_sql = null;
            try {
                date_sql = new toDate().stringToDate(dateNaissance);
            } catch (ParseException ex) {
                Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(date_sql != null ){
                java.sql.Date premiere_venue = java.sql.Date.valueOf(LocalDate.now());
                LocalDate dateNaissance2 = new toDate().sqlDateToLocalDate(date_sql);
                java.sql.Date dateNaissance_sql = java.sql.Date.valueOf(dateNaissance2);
                System.out.println(dateNaissance_sql);
        
                Patient patient = null;    
                try {
                    patient = new Patient(premiere_venue,nom.toUpperCase(),prenom,dateNaissance_sql,sexe,adresse);
                    patientsModel.addElement( "" + patient.getNom() + " " + patient.getPrenom() + "");
                    patientList.setModel(patientsModel);
                    new requetes().createPatient(this.conn, patient);
                    dateErrorMessage.setText("Un nouveau patient a été créé");
                    
                } catch (ParseException ex) {
                    Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
                    dateErrorMessage.setText("Il semblerait qu'il y ait eu un problème...");
                } catch (SQLException ex) {
                    Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
                    dateErrorMessage.setText("Il semblerait qu'il y ait eu un problème...");
                }
               
            }
            else{
                dateErrorMessage.setText("Veuillez respecter le format de date : JJ/MM/AAAA");
            }
            
        }
        else{
                dateErrorMessage.setText("<html><p>Veuillez respecter le format de date : JJ/MM/AAAA </p><p>et renseigner la totalité des champs</p></html>");
        }
        
        

    }//GEN-LAST:event_addPatientButtonActionPerformed

    private void patientListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientListMouseClicked
        //récupérer le patient sélectionner
        selectedPatient = patientList.getSelectedValue();
      
        
        
       
    }//GEN-LAST:event_patientListMouseClicked

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
        
        for(int i=0;i<patients.size();i++){
            patientsModel.addElement(patients.get(i).getIPP() + " - " + patients.get(i).getNom() + " " + patients.get(i).getPrenom() + "");
            patientList.setModel(patientsModel);
          
        } 
        
    }//GEN-LAST:event_searchIconMouseClicked

    private void addConsultationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addConsultationMouseClicked
        String patient = patientList.getSelectedValue();
        String IPP = patient.substring(0, 9);


        String praticien = (String) praticienComboBox.getSelectedItem();
        String numP = praticien.substring(0,7);
        //System.out.print(numP);
        String naturePrestation = naturePrestationTextfield.getText();
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
                if(new requetes().getDMA_IPP(this.conn, IPP)!=null){
                    new requetes().addSejour(this.conn, IPP, consultation);
                    new requetes().addLocalisation(conn, IPP, lit);
                }
                else{
                    new requetes().createDMA(this.conn, dma);
                    new requetes().addSejour(this.conn, IPP, consultation);
                    new requetes().addLocalisation(conn, IPP, lit);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_addConsultationMouseClicked

    private void naturePrestationTextfieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_naturePrestationTextfieldMouseClicked
        if(naturePrestationTextfield.getText().equals("Nature de la prestation")){
            naturePrestationTextfield.setText("");
        }
    }//GEN-LAST:event_naturePrestationTextfieldMouseClicked

    private void chambreLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chambreLabelMouseClicked
        if(chambreLabel.getText().equals("Numéro Chambre")){
            chambreLabel.setText("");
        }
    }//GEN-LAST:event_chambreLabelMouseClicked

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
            java.util.logging.Logger.getLogger(Creation_DMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Creation_DMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Creation_DMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Creation_DMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Creation_DMA().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(Creation_DMA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addConsultation;
    private javax.swing.JLabel addDMAIcon;
    private javax.swing.JLabel addDMALabel;
    private javax.swing.JButton addPatientButton;
    private javax.swing.JTextField adresseTextfield;
    private javax.swing.JTextField chambreLabel;
    private javax.swing.JLabel dateErrorMessage;
    private javax.swing.JTextField dateNiassanceTextField;
    private javax.swing.JLabel deconnexionIcon2;
    private javax.swing.JLabel deconnexionIconButton;
    private javax.swing.JLabel deconnexionLabel;
    private javax.swing.JLabel deconnexionLabel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
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
    private javax.swing.JTextField nameTextfield;
    private javax.swing.JTextField naturePrestationTextfield;
    private javax.swing.JLabel numeroDeux;
    private javax.swing.JLabel numeroUn;
    private javax.swing.JList<String> patientList;
    private javax.swing.JPanel patientTable;
    private javax.swing.JComboBox<String> praticienComboBox;
    private javax.swing.JTextField prenomTextfield;
    private javax.swing.JLabel professionnelLabel;
    private javax.swing.JLabel searchIcon;
    private javax.swing.JTextField searchTextfield;
    private javax.swing.JLabel seeDMAIcon;
    private javax.swing.JLabel seeDMALabel;
    private javax.swing.JComboBox<String> serviceGeographiqueComboBox;
    private javax.swing.JComboBox<String> serviceResponsableComboBox;
    private javax.swing.JComboBox<String> sexeTextfield;
    // End of variables declaration//GEN-END:variables

}
