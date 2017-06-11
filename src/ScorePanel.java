
import java.awt.Color;
import java.awt.Dimension;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class ScorePanel extends javax.swing.JPanel {

    /**
     * Creates new form ScorePanel
     */
    public void setScore(int i)
    {
        score.setText(i+"");
        
    }
    public ScorePanel(Dimension d) {
        this.setLayout(null);
        this.setBounds(0,0 ,d.width,d.height);
        this.setBackground(Color.BLUE);
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

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        score = new javax.swing.JLabel();

        jFormattedTextField1.setText("jFormattedTextField1");

        setBackground(new java.awt.Color(0, 0, 0));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 0), 100));
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Your Score  :");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 10));
        add(jLabel1);
        jLabel1.setBounds(430, 150, 260, 71);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("          Thanks For Participating ");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 10));
        add(jLabel2);
        jLabel2.setBounds(370, 230, 600, 75);

        exit.setBackground(new java.awt.Color(0, 0, 0));
        exit.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setText("LogOut");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        add(exit);
        exit.setBounds(560, 370, 180, 53);

        score.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        score.setForeground(new java.awt.Color(255, 255, 255));
        score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        score.setText("jLabel3");
        score.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 10));
        add(score);
        score.setBounds(700, 150, 140, 70);
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exit;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel score;
    // End of variables declaration//GEN-END:variables
}
