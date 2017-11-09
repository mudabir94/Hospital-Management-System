/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.Staff_info.idvalue;
import java.io.File;
import java.io.FileFilter;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;


/**
 *
 * @author Mudabir Ahmad
 */
public class PatientRegistration extends javax.swing.JFrame {
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    // patient id to be registered
    int num=0;
    // patient admit id in patient table
    int a_num=0;
    // patient info
   static String name="";String gender="";String address="";String cell="";String pcnic_no="";String age="";
    //doc info
    String doc_name="";
    int doc_id=0;
    HashMap<Integer, String> hmap = new HashMap<Integer, String>();
    //..........................................................................................................................//
    /**
     * Creates new form Login_Reception
     */
    DefaultComboBoxModel dm= new DefaultComboBoxModel();
    public PatientRegistration() {
        initComponents();
        fillcombo_docid();
        fillcombo_roombox();
        inc_p_id();
        settextField_Null();
         updateTable_med();
         updateTable_Pres();
    }
    
    
    private void fillcombo_docid()
    {
        int d_id;String d_name="";
        String status="onduty";
         conn=mysqlconnection.ConnectDb();
     String sql= "select * from doctor_info where status=?";
        try
        {
           pst=conn.prepareStatement(sql);
           pst.setString(1,status);
           rs=pst.executeQuery();
           while(rs.next())
           {
             doc_name=rs.getString("name");
             doc_id=parseInt(rs.getString("doc_id"));
            docbox.addItem(doc_name);
            docbox2.addItem(doc_name);
            d_id=doc_id;
            d_name=doc_name;
         
             hmap.put(d_id, d_name);
           }
                     
        }
        catch(SQLException ex){
        JOptionPane.showMessageDialog(null, "x");
        System.out.println(ex.getMessage());
        }
    }
    private void fillcombo_roombox()
    {
        String status="available";
         conn=mysqlconnection.ConnectDb();
     String sql= "select * from room_info where status=?";
        try
        {
           pst=conn.prepareStatement(sql);
           pst.setString(1,status);
           rs=pst.executeQuery();
           while(rs.next())
           {
              
            int rm_num=parseInt(rs.getString("r_id"));
            roombox.addItem(String.valueOf(rm_num));
           }
           
        }
        catch(SQLException ex){
        JOptionPane.showMessageDialog(null, "x");
        System.out.println(ex.getMessage());
        }
    }
 
     private void fillcombo_bedbox_ward()
    {
         String item=wtypebox.getSelectedItem().toString().toLowerCase();
         System.out.println("Ward "+item);
         String status="available";
         conn=mysqlconnection.ConnectDb();
         String sql= "select * from wards where status=? and ward_type=?";
        try
        {
           pst=conn.prepareStatement(sql);
           pst.setString(1,status);
           pst.setString(2,item);
           rs=pst.executeQuery();
           while(rs.next())
           {
             
            int b_num=parseInt(rs.getString("bed_num"));
            bedbox.addItem(String.valueOf(b_num));
           }
           
        }
        catch(SQLException ex){
        JOptionPane.showMessageDialog(null, "x");
        System.out.println(ex.getMessage());
        }
        
    }
 private void emptycombo_roombox(){
     roombox.removeAllItems();
 }
 private void emptycombo_bedbox_ward(){
       bedbox.removeAllItems();
    }
    private void inc_p_id()
    {
         conn=mysqlconnection.ConnectDb();
         String sql= "SELECT IFNULL(MAX(p_id),0) as p_id from patient_info  ";
         System.out.println("Flag (inc_p_id): id"+num+1);
        try
        {
           pst=conn.prepareStatement(sql);
           rs=pst.executeQuery();
           while(rs.next())
           {
             
            num=parseInt(rs.getString("p_id"));
            
            p_id_tfield.setText(String.valueOf(num+1));
          
           }
           pst=null;
        }
        catch(SQLException ex){
        JOptionPane.showMessageDialog(null, "x");
        System.out.println(ex.getMessage());
        }
    }
  private void settextField_Null() {
             p_nametext.setText(null);
             p_addresstext.setText(null);
             p_celltext.setText(null);
             p_agetext.setText(null);
             in_cnic_textfield.setText(null);
    }
  
  private void get_docid(String name)
    {
       Set set = hmap.entrySet();
      Iterator iterator = set.iterator();
      while(iterator.hasNext()) {
         Map.Entry mentry = (Map.Entry)iterator.next();
         if(mentry.getValue().equals(name))
         {
           doc_id=parseInt(mentry.getKey().toString());
           
         }
      }
      System.out.println("Doc id"+doc_id);
    }
  private void updateTable_med(){
        conn=mysqlconnection.ConnectDb();
        String sql= "select * from medicine  ";
        try
        {
           pst=conn.prepareStatement(sql);
             rs=pst.executeQuery();
           med_Table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception ex){
        JOptionPane.showMessageDialog(null, "sss");
        System.out.println(ex.getMessage());
        }
    }
   private void updateTable_Pres(){
        conn=mysqlconnection.ConnectDb();
        String sql= "select * from prescribtion ";
        try
        {
           pst=conn.prepareStatement(sql);
             rs=pst.executeQuery();
           pres_Table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception ex){
        JOptionPane.showMessageDialog(null, "sss");
        System.out.println(ex.getMessage());
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

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        p_id_tfield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        p_nametext = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        p_celltext = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        p_addresstext = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        docbox = new javax.swing.JComboBox<>();
        submitbtn_outpatient = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        filtertext = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        med_Table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        pres_Table = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        admitbox = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        wtypebox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        bedbox = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        in_cnic_textfield = new javax.swing.JTextField();
        submit_btn_inpatient_ward = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        docbox2 = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        rm_sbmt = new javax.swing.JButton();
        roombox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        p_genderbox = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        p_agetext = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ptypebox = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Patient_id");

        p_id_tfield.setEditable(false);
        p_id_tfield.setBackground(new java.awt.Color(153, 153, 153));

        jLabel2.setText(" Name");

        p_nametext.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                p_nametextKeyPressed(evt);
            }
        });

        jLabel3.setText("Cell No.");

        jLabel4.setText("Address");

        jPanel1.setLayout(new java.awt.CardLayout());

        jLabel6.setText("Prescribtion");

        jLabel7.setText("Doctor on Duty");

        docbox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                docboxItemStateChanged(evt);
            }
        });

        submitbtn_outpatient.setText("Submit");
        submitbtn_outpatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitbtn_outpatientActionPerformed(evt);
            }
        });

        jButton1.setText("add");

        filtertext.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtertextKeyReleased(evt);
            }
        });

        med_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "m_id", "m_name", "Qty"
            }
        ));
        jScrollPane1.setViewportView(med_Table);

        pres_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sr/no", "p_id", "m_id"
            }
        ));
        jScrollPane2.setViewportView(pres_Table);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(filtertext, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filtertext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(submitbtn_outpatient)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(docbox, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(docbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(submitbtn_outpatient)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, "card3");

        jLabel8.setText("Admit in ");

        admitbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ward", "Room" }));
        admitbox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                admitboxItemStateChanged(evt);
            }
        });
        admitbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admitboxActionPerformed(evt);
            }
        });

        jPanel3.setLayout(new java.awt.CardLayout());

        jLabel9.setText("Ward Type");

        wtypebox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male Ward", "Female Ward", "Childern Ward" }));
        wtypebox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                wtypeboxItemStateChanged(evt);
            }
        });
        wtypebox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wtypeboxActionPerformed(evt);
            }
        });

        jLabel10.setText("Bed ");

        jLabel11.setText("Cnic No.");

        submit_btn_inpatient_ward.setText("Submit");
        submit_btn_inpatient_ward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_btn_inpatient_wardActionPerformed(evt);
            }
        });

        jLabel15.setText("doctor on duty");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(submit_btn_inpatient_ward)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel15))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(in_cnic_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(docbox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(wtypebox, javax.swing.GroupLayout.Alignment.LEADING, 0, 102, Short.MAX_VALUE)
                                .addComponent(bedbox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(617, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(wtypebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(bedbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel15))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(docbox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(in_cnic_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(submit_btn_inpatient_ward))
        );

        jPanel3.add(jPanel5, "card2");

        rm_sbmt.setText("Submit");
        rm_sbmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rm_sbmtActionPerformed(evt);
            }
        });

        jLabel12.setText("Room No");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(rm_sbmt))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addGap(39, 39, 39)
                        .addComponent(roombox, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(695, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roombox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addComponent(rm_sbmt)
                .addContainerGap())
        );

        jPanel3.add(jPanel6, "card3");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addGap(35, 35, 35)
                        .addComponent(admitbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(admitbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel4, "card2");

        jLabel13.setText("Gender");

        p_genderbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male ", "Female", "Other" }));

        jLabel14.setText("Age");

        jLabel5.setText("Patient type");

        ptypebox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OUT PATIENT", "IN PATIENT" }));
        ptypebox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ptypeboxItemStateChanged(evt);
            }
        });
        ptypebox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ptypeboxActionPerformed(evt);
            }
        });

        jPanel11.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel10, "card2");

        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("*");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 47, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel8, "card3");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(49, 49, 49)
                        .addComponent(p_genderbox, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(p_agetext, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(p_id_tfield, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(p_celltext, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(p_addresstext, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(p_nametext, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(65, 65, 65)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(ptypebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(p_id_tfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel5)))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(p_nametext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(p_celltext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(p_addresstext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(p_agetext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(p_genderbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(ptypebox, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ptypeboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ptypeboxItemStateChanged
        // TODO add your handling code here:
        String tbox= ptypebox.getSelectedItem().toString();
        if(tbox.equals("OUT PATIENT"))
        {
           jPanel1.removeAll();
           jPanel1.repaint();
           jPanel1.revalidate();
           // add panel 
           jPanel1.add(jPanel2);
           jPanel1.repaint();
           jPanel1.revalidate();
        }
        else if (tbox.equals("IN PATIENT"))
        {
           jPanel1.removeAll();
           jPanel1.repaint();
           jPanel1.revalidate();
           // add panel 
           jPanel1.add(jPanel4);
           jPanel1.repaint();
           jPanel1.revalidate();
        }
        
    }//GEN-LAST:event_ptypeboxItemStateChanged

    private void ptypeboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ptypeboxActionPerformed
        // TODO add your handling code here:
    if(ptypebox.getSelectedItem().toString().equals("IN PATIENT"))
    {
        emptycombo_bedbox_ward();
        fillcombo_bedbox_ward();
    }
        
    }//GEN-LAST:event_ptypeboxActionPerformed

    private void wtypeboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wtypeboxActionPerformed

    }//GEN-LAST:event_wtypeboxActionPerformed

    private void admitboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_admitboxItemStateChanged
        // TODO add your handling code here:
        String admbx= admitbox.getSelectedItem().toString();
        if(admbx.equals("Ward"))
        {
           jPanel3.removeAll();
           jPanel3.repaint();
           jPanel3.revalidate();
           // add panel 
           jPanel3.add(jPanel5);
           jPanel3.repaint();
           jPanel3.revalidate();
        }
        else if (admbx.equals("Room"))
        {
           jPanel3.removeAll();
           jPanel3.repaint();
           jPanel3.revalidate();
           // add panel 
           jPanel3.add(jPanel6);
           jPanel3.repaint();
           jPanel3.revalidate();
        }
        
        
    }//GEN-LAST:event_admitboxItemStateChanged

    private void admitboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admitboxActionPerformed
        // TODO add your handling code here:
          System.out.println("Ward type when action performed on admit combo"+wtypebox.getItemAt(0));  
    }//GEN-LAST:event_admitboxActionPerformed

    private void wtypeboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_wtypeboxItemStateChanged
     
           emptycombo_bedbox_ward();
           fillcombo_bedbox_ward();
       
    }//GEN-LAST:event_wtypeboxItemStateChanged

    private void submitbtn_outpatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitbtn_outpatientActionPerformed
        // TODO add your handling code here:
        name =p_nametext.getText();
       
        gender=p_genderbox.getSelectedItem().toString();
        age=p_agetext.getText();
        address=p_addresstext.getText();
        cell=p_celltext.getText();
        
        conn=mysqlconnection.ConnectDb();
        String sql= "insert into patient_info ( `name`, `gender`, `age`, `address`, `cell`, `date_created`, `date_modified`) VALUES(?,?,?,?,?,?,?)";
        String sql1="insert into patient_history (p_id, p_status, doc_id, date_created, date_modified) VALUES (?,?,?,?,?)";
        try
        {
           
           java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        
           pst=conn.prepareStatement(sql);
           pst.setString(1,name.toLowerCase());
           pst.setString(2,gender.toLowerCase());
           System.out.println("age : "+age);
           if(age==null || age.isEmpty())
           {
               System.out.println("age : "+age);
               pst.setNull(3,Types.INTEGER);
               
           }else{
               pst.setInt(3,parseInt(age));
           }
           
           pst.setString(4,address.toLowerCase());
           pst.setString(5,cell.toLowerCase());
           pst.setTimestamp(6, date);
           pst.setTimestamp(7, date);
           
         
           pst.executeUpdate();
           
           
           pst=null;
           
           java.sql.Timestamp date1 = new java.sql.Timestamp(new java.util.Date().getTime());
           pst=conn.prepareStatement(sql1);
           System.out.println("number "+num);
           pst.setInt(1,num+1);
           pst.setString(2,ptypebox.getSelectedItem().toString().toLowerCase());
           
           pst.setInt(3, doc_id);
           pst.setTimestamp(4,date1);
           pst.setTimestamp(5,date1);
           pst.execute();
           
           pst=null;
           p_nametext.setText(null);
           p_addresstext.setText(null);
           p_celltext.setText(null);
           p_agetext.setText(null);
           
           inc_p_id();
        
          
        }
        catch(NumberFormatException | SQLException ex){
        JOptionPane.showMessageDialog(null, "sss");
        System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_submitbtn_outpatientActionPerformed

    private void docboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_docboxItemStateChanged
        // TODO add your handling code here:
        
        get_docid(docbox.getSelectedItem().toString());
        
    }//GEN-LAST:event_docboxItemStateChanged
    private void p_nametext_val(){
        
       
           jPanel11.removeAll();
           jPanel11.repaint();
           jPanel11.revalidate();
           // add panel 
           jPanel11.add(jPanel8);
           jPanel11.repaint();
           jPanel11.revalidate();
       
       
    }
    private void submit_btn_inpatient_wardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_btn_inpatient_wardActionPerformed
       int wardid=0;
        // TODO add your handling code here:
        if(p_nametext.getText()==null || p_nametext.getText().isEmpty() ){
        //name =p_nametext.getText();
            p_nametext_val();
        
         JOptionPane.showMessageDialog(null, "enter name ");
        }
        
        gender=p_genderbox.getSelectedItem().toString();
        age=p_agetext.getText();
        address=p_addresstext.getText();
        cell=p_celltext.getText();
        pcnic_no=in_cnic_textfield.getText();
        ///////////////////////////----------------QUERRIES--------------------/////////////////////////////////////////////////////////
        String sql= "insert into patient_info ( name, gender, age, address, cell, date_created, date_modified) VALUES(?,?,?,?,?,?,?)";
        String sql1="select ward_id from wards where ward_type=? and bed_num=?";
        String sql2="INSERT INTO patient_admit( ward_id, date_created, date_modified) VALUES (?,?,?)";
        String sql3="UPDATE `wards` SET `status`=? WHERE ward_id=?";
        String sql4="select MAX(p_admit_id)as p_admit_id from patient_admit ";
        String sql5="insert into patient_history (p_id,p_admit_id, p_status,p_cnic, doc_id, date_created, date_modified) VALUES (?,?,?,?,?,?,?)";
        //////////////////////////////////////----------------------------------------////////////////////////////////////////
        try
        {
           java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        ////////////////////////////----------ONE------------//////////////////////////////////////
           pst=conn.prepareStatement(sql);
           pst.setString(1,name.toLowerCase());
           pst.setString(2,gender.toLowerCase());
          
           if(age==null || age.isEmpty())
           {
               System.out.println("age : "+age);
               pst.setNull(3,Types.INTEGER);
               
           }else{
               pst.setInt(3,parseInt(age));
           }
           
           pst.setString(4,address.toLowerCase());
           pst.setString(5,cell.toLowerCase());
           pst.setTimestamp(6, date);
           pst.setTimestamp(7, date);
           pst.execute();
        }
         catch(NumberFormatException | SQLException ex){
        JOptionPane.showMessageDialog(null, "rrrrrrrrrrrr");
        System.out.println("First"+ex.getMessage());
        }
   /////////////////////////////////////----------TWO------------////////////////////////////////
        try{
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1, wtypebox.getSelectedItem().toString().toLowerCase());
            pst.setInt(2, parseInt(bedbox.getSelectedItem().toString()));
            rs= pst.executeQuery();
            if(rs.next())
            {
               System.out.println("ward id "+rs.getInt("ward_id"));
              wardid= rs.getInt("ward_id");
             
            }
            pst=null;
        }
        catch(NumberFormatException | SQLException ex){
            JOptionPane.showMessageDialog(null, "sss");
            System.out.println("Second"+ex.getMessage());
         }  
     //////////////////////////////////----------THREE------------//////////////////////////////////////////
        try{
              String state="occupied";
              pst=conn.prepareStatement(sql3);
              pst.setString(1,state);
              pst.setInt(2,wardid);
              pst.execute();
              
              
              emptycombo_bedbox_ward();
              fillcombo_bedbox_ward();
             
        }
        catch(NumberFormatException | SQLException ex){
            JOptionPane.showMessageDialog(null, "sss");
            System.out.println("third"+ex.getMessage());
            }  
     //////////////////////////////////----------FOURTH------------//////////////////////////////////////////
        try{
            java.sql.Timestamp date1 = new java.sql.Timestamp(new java.util.Date().getTime());
            pst=conn.prepareStatement(sql2);
            pst.setInt(1,wardid);
            pst.setTimestamp(2,date1);
            pst.setTimestamp(3, date1);
            pst.execute();
            pst=null;
           }
            catch(NumberFormatException | SQLException ex){
            JOptionPane.showMessageDialog(null, "sss");
            System.out.println("Fourth"+ex.getMessage());
         }   
     /////////////////////////////////////----------FIFTH------------////////////////////////////////////////
         try{
            
            pst=conn.prepareStatement(sql4);
            rs=pst.executeQuery();
            if(rs.next())
             {
                a_num=parseInt(rs.getString("p_admit_id"));
                System.out.println("a_num : "+a_num);
              }
                    pst=null;
            }
            catch(NumberFormatException | SQLException ex){
            JOptionPane.showMessageDialog(null, "sss");
            System.out.println("fifth "+ex.getMessage());
         }   
        /////////////////////////////---------------SIXTH-----------------////////////////////////////////////
        try{
           
                java.sql.Timestamp date1 = new java.sql.Timestamp(new java.util.Date().getTime());
                pst=conn.prepareStatement(sql5);
                System.out.println("number "+num);
                pst.setInt(1,num+1);
                pst.setInt(2,a_num);
                pst.setString(3,ptypebox.getSelectedItem().toString().toLowerCase());
                pst.setString(4,pcnic_no);
               
                pst.setInt(5, doc_id);
                pst.setTimestamp(6,date1);
                pst.setTimestamp(7,date1);
                pst.execute();

                pst=null;
                p_nametext.setText(null);
                p_addresstext.setText(null);
                p_celltext.setText(null);
                p_agetext.setText(null);
                in_cnic_textfield.setText(null);
                
                inc_p_id();
          
        }
         catch(NumberFormatException | SQLException ex){
            JOptionPane.showMessageDialog(null, "sixth");
            System.out.println("sixth"+ex.getMessage());
         }   
    }//GEN-LAST:event_submit_btn_inpatient_wardActionPerformed

    private void p_nametextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_nametextKeyPressed
        // TODO add your handling code here:
       if( p_nametext.getText().length()>=1){
             submit_btn_inpatient_ward.setEnabled(true);
       }
       else{
            submit_btn_inpatient_ward.setEnabled(false);
        }
    }//GEN-LAST:event_p_nametextKeyPressed

    private void filtertextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtertextKeyReleased
        // TODO add your handling code here:
         String querry=filtertext.getText().toLowerCase();
       DefaultTableModel sm=(DefaultTableModel) med_Table.getModel();
       TableRowSorter<DefaultTableModel> tr=new  TableRowSorter<DefaultTableModel>(sm);
      med_Table.setRowSorter(tr);
        try{
    
            tr.setRowFilter(RowFilter.regexFilter(querry));
       }
       catch(Exception ex)
       {
          System.out.println(ex.getMessage());
       }
    }//GEN-LAST:event_filtertextKeyReleased

    private void rm_sbmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rm_sbmtActionPerformed
        // TODO add your handling code here:
        int r_id=0;
        name =p_nametext.getText();
        gender=p_genderbox.getSelectedItem().toString();
        age=p_agetext.getText();
        address=p_addresstext.getText();
        cell=p_celltext.getText();
        
        conn=mysqlconnection.ConnectDb();
        String sql= "insert into patient_info ( `name`, `gender`, `age`, `address`, `cell`, `date_created`, `date_modified`) VALUES(?,?,?,?,?,?,?)";
        String sql1="INSERT INTO patient_admit( r_id, date_created, date_modified) VALUES (?,?,?)";
        String sql2="select MAX(p_admit_id)as p_admit_id from patient_admit ";
        String sql3="UPDATE `room_info` SET `status`=? WHERE r_id=?";
        String sql4="insert into patient_history (`p_id`,p_admit_id, `p_status`, `date_created`, `date_modified`) VALUES (?,?,?,?,?)";
     try
        {
           java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        ////////////////////////////----------ONE------------//////////////////////////////////////
           pst=conn.prepareStatement(sql);
           pst.setString(1,name.toLowerCase());
           pst.setString(2,gender.toLowerCase());
          
           if(age==null || age.isEmpty())
           {
               System.out.println("age : "+age);
               pst.setNull(3,Types.INTEGER);
               
           }else{
               pst.setInt(3,parseInt(age));
           }
           
           pst.setString(4,address.toLowerCase());
           pst.setString(5,cell.toLowerCase());
           pst.setTimestamp(6, date);
           pst.setTimestamp(7, date);
           pst.execute();
        }
         catch(NumberFormatException | SQLException ex){
        JOptionPane.showMessageDialog(null, "ROOM");
        System.out.println("First ROOM "+ex.getMessage());
        }
      //-//--/-/*/-//-//-///-//-//-///--///-//*/-///*-/-//-//-//-//-//--///-//-/-/-/*/-//-/-//-//-//-/-//-//-
         try{
            java.sql.Timestamp date1 = new java.sql.Timestamp(new java.util.Date().getTime());
            pst=conn.prepareStatement(sql1);
            r_id=parseInt(roombox.getSelectedItem().toString());
            pst.setInt(1,r_id);
            pst.setTimestamp(2,date1);
            pst.setTimestamp(3, date1);
            pst.execute();
            pst=null;
           }
            catch(NumberFormatException | SQLException ex){
            JOptionPane.showMessageDialog(null, "tesk");
            System.out.println("Second"+ex.getMessage());
         }   
          try{
            
            pst=conn.prepareStatement(sql2);
            rs=pst.executeQuery();
            if(rs.next())
             {
                a_num=parseInt(rs.getString("p_admit_id"));
                
              }
                    pst=null;
                    
            }
            catch(NumberFormatException | SQLException ex){
            JOptionPane.showMessageDialog(null, "sss");
            System.out.println("fifth "+ex.getMessage());
         }   
          //////////////////////////////////////////////////////////////////////////////////////////////////
           try{
              String state="occupied";
              pst=conn.prepareStatement(sql3);
              pst.setString(1,state);
              pst.setInt(2,r_id);
              pst.execute();
              
              
              fillcombo_roombox();
              emptycombo_roombox();
             
        }
        catch(NumberFormatException | SQLException ex){
            JOptionPane.showMessageDialog(null, "sss");
            System.out.println("third"+ex.getMessage());
            }  
         /////////////////////////////////////////////////////////////////////////////////////////////////////
          try{
           
                java.sql.Timestamp date1 = new java.sql.Timestamp(new java.util.Date().getTime());
                pst=conn.prepareStatement(sql4);
                System.out.println("number "+num);
                System.out.println("admit nym"+a_num);
                pst.setInt(1,num+1);
                pst.setInt(2,a_num);
                pst.setString(3,ptypebox.getSelectedItem().toString().toLowerCase());
               
               
             
                pst.setTimestamp(4,date1);
                pst.setTimestamp(5,date1);
                pst.execute();

                pst=null;
                p_nametext.setText(null);
                p_addresstext.setText(null);
                p_celltext.setText(null);
                p_agetext.setText(null);
                in_cnic_textfield.setText(null);
                
                inc_p_id();
          
        }
         catch(NumberFormatException | SQLException ex){
            JOptionPane.showMessageDialog(null, "jnsdasdasdasdasd");
            System.out.println("third"+ex.getMessage());
         }  
    }//GEN-LAST:event_rm_sbmtActionPerformed
     
             
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
            java.util.logging.Logger.getLogger(PatientRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientRegistration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> admitbox;
    private javax.swing.JComboBox<String> bedbox;
    private javax.swing.JComboBox<String> docbox;
    private javax.swing.JComboBox<String> docbox2;
    private javax.swing.JTextField filtertext;
    private javax.swing.JTextField in_cnic_textfield;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable med_Table;
    private javax.swing.JTextField p_addresstext;
    private javax.swing.JTextField p_agetext;
    private javax.swing.JTextField p_celltext;
    private javax.swing.JComboBox<String> p_genderbox;
    private javax.swing.JTextField p_id_tfield;
    private javax.swing.JTextField p_nametext;
    private javax.swing.JTable pres_Table;
    private javax.swing.JComboBox<String> ptypebox;
    private javax.swing.JButton rm_sbmt;
    private javax.swing.JComboBox<String> roombox;
    private javax.swing.JButton submit_btn_inpatient_ward;
    private javax.swing.JButton submitbtn_outpatient;
    private javax.swing.JComboBox<String> wtypebox;
    // End of variables declaration//GEN-END:variables

   
}
