/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import com.sun.glass.events.KeyEvent;
import java.io.PrintStream;
import java.sql.*;
import javax.swing.*;
/**
/**
 *
 * @author Mudabir Ahmad
 */
public class Login extends javax.swing.JFrame {
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        usertext = new javax.swing.JTextField();
        passwordtext = new javax.swing.JPasswordField();
        submitbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.pink);
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(480, 300));
        setResizable(false);

        jPanel1.setLayout(null);

        jLabel2.setText("WELCOME ");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(40, 20, 110, 20);

        jLabel3.setText("USERNAME");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(90, 80, 60, 14);

        jLabel4.setText("Password");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(90, 110, 60, 20);
        jPanel1.add(usertext);
        usertext.setBounds(230, 70, 180, 30);

        passwordtext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordtextActionPerformed(evt);
            }
        });
        jPanel1.add(passwordtext);
        passwordtext.setBounds(230, 110, 180, 30);

        submitbtn.setText("Submit");
        submitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitbtnActionPerformed(evt);
            }
        });
        submitbtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                submitbtnKeyPressed(evt);
            }
        });
        jPanel1.add(submitbtn);
        submitbtn.setBounds(230, 150, 80, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/image.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 480, 300);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void login_Func()
    {
          conn=mysqlconnection.ConnectDb();
        String sql= "select * from login where username=? and password=?";
       
        try
        {
           pst=conn.prepareStatement(sql);
           pst.setString(1, usertext.getText());
           pst.setString(2,passwordtext.getText());
           rs=pst.executeQuery();
           
           if (rs.next())
           {
            String role = rs.getString("role");
            role=role.toLowerCase();
            System.out.println(role);
            if (role.equals("admin"))
            {
             System.out.println("corect");
            Admin a=new Admin();
            a.setVisible(true);
            }
            else 
            {
             Reception rp=new Reception();
             rp.setVisible(true);
            }
           
           }
           else{
              System.out.println("Incorect");
              JOptionPane.showMessageDialog(null, "Incorrect password or username ");
           }
        }
        catch(Exception ex){
        JOptionPane.showMessageDialog(null, "sss");
        
        System.out.println(ex.getMessage());
        }
    }
    private void submitbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitbtnActionPerformed
        // TODO add your handling code here:
     login_Func();
    }//GEN-LAST:event_submitbtnActionPerformed

    private void passwordtextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordtextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordtextActionPerformed

    private void submitbtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_submitbtnKeyPressed
        // TODO add your handling code here:
      login_Func();
    }//GEN-LAST:event_submitbtnKeyPressed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
                                                                                                                                                            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField passwordtext;
    private javax.swing.JButton submitbtn;
    private javax.swing.JTextField usertext;
    // End of variables declaration//GEN-END:variables
}
