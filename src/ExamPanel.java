
import com.mysql.jdbc.Connection;
import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class ExamPanel extends javax.swing.JPanel implements ActionListener{

      Timer clock;
   // int flag=-1;
    int counter=300;
    List<ModalClass> list=Collections.emptyList();
    //List<String> ansList=Collections.emptyList();
    public Timer timer=null;
    int Qno=0;
    ExamPanel ex;
     ScorePanel panel;
    private Object resizedImage;
    /**
     * Creates new form ExamPanel
     */
    public ExamPanel( Dimension d, ScorePanel panel)
    {
        this.panel=panel;
        this.setLayout(null);
        this.setBounds(0,0 ,d.width,d.height);
        this.setBackground(Color.BLUE);
        initComponents();
        Q1.addActionListener(this);
        Q2.addActionListener(this);
        Q3.addActionListener(this);
        Q4.addActionListener(this);
        Q5.addActionListener(this);
        Q6.addActionListener(this);
        Q7.addActionListener(this);
        Q8.addActionListener(this);
        Q9.addActionListener(this);
        Q10.addActionListener(this);
        A.addActionListener(this);
        B.addActionListener(this);
        C.addActionListener(this);
        D.addActionListener(this);
        
    
    }
    public void setTime()
    {
          
            clock=new javax.swing.Timer(1000, new ActionListener() 
            {
               public void actionPerformed(ActionEvent e) {

                   counter--;
                   add(time);
                   if(counter>=0)
                   {
                       int sec=counter%60;
                       int min=counter/60;
                       time.setText("Time Left  :"+min+" min :"+sec+"sec");
                   }
                       
                   else
                   {
                     JOptionPane.showMessageDialog(ex,"Test Finished");
                     clock.stop();
                     panel.setScore(calculateScore());
                    ex.setVisible(false);
                    panel.setVisible(true);
                     
                   }

               }
           } );
            
           clock.start();
    }
    public void fetchImage() //throws ClassNotFoundException, SQLException
    {
        try
        {
            Class.forName("java.sql.DriverManager");
            Connection con = (Connection)DriverManager.getConnection ("jdbc:mysql://localhost:3306/microsoftexam", "root", "");
            Statement stmt = (Statement)con.createStatement();
            //String query =" select * from studentlogin where id like "+id;
            String query="select img from image where sc like "+myScholar.getText();
            ResultSet rs=stmt.executeQuery(query);
            
             byte[] images = null;
             while (rs.next())
             {
                 images = rs.getBytes("img");
                 //Blob b=rs.getBlob("img");

             }
            java.awt.Image imge = Toolkit.getDefaultToolkit().createImage(images);
            ImageIcon icon;
            // use me  icon = new ImageIcon(imge);
           icon =new ImageIcon(scaledImage(images,picture.getWidth(),picture.getHeight()));
            
            
            
            //Image.getScaledInstance(...)
            
            
            //image.setIcon(new ImageIcon (Toolkit.getDefaultToolkit().createImage(b)));
            picture.setIcon(icon);
            add(picture);
            con.close();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            
        }
        
    }
    private BufferedImage scaledImage(byte [] img,int w,int h)
    {
        BufferedImage rs=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        try
        {
            Graphics2D g2=rs.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            ByteArrayInputStream in =new ByteArrayInputStream(img);
            BufferedImage bImageFromConvert=ImageIO.read(in); 
            g2.drawImage(bImageFromConvert,0,0,w,h,null);
            g2.dispose();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,e);
            
        }
    
        return rs;
    }
    public void fetchData()
    {
        list =new ArrayList();
       // ansList=new ArrayList();
        try
        {
            Class.forName("java.sql.DriverManager");
            Connection con = (Connection)DriverManager.getConnection ("jdbc:mysql://localhost:3306/microsoftexam", "root", "");
            Statement stmt = (Statement)con.createStatement();
            //String query =" select * from studentlogin where id like "+id;
            String query="select * from paper ";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
            {
                ModalClass obj=new ModalClass();
                obj.setQuestion(rs.getString("question"));
                obj.setOptionA(rs.getString("optiona"));
                obj.setOptionB(rs.getString("optionb"));
                obj.setOptionC(rs.getString("optionc"));
                obj.setOptionD(rs.getString("optiond"));
                obj.setAnswer(rs.getString("answer"));
                list.add(obj);
               // list.add(0, obj);
            }
           
            con.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Populate UI with questions 
     
        setQuestion(0);
        
        
    }
    public void setQuestion(int i)
    {
        String num="Q."+(i+1);
        qLabel.setText(num);
       // System.out.println(""+i);
        ModalClass m=new ModalClass();
        m=list.get(i);
        Question.setText(m.getQuestion());
        A.setText(m.getOptionA());
        B.setText(m.getOptionB());
        C.setText(m.getOptionC());
        D.setText(m.getOptionD());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Q1 = new javax.swing.JButton();
        Q2 = new javax.swing.JButton();
        Q3 = new javax.swing.JButton();
        Q4 = new javax.swing.JButton();
        Q5 = new javax.swing.JButton();
        Q6 = new javax.swing.JButton();
        Q7 = new javax.swing.JButton();
        Q8 = new javax.swing.JButton();
        Q9 = new javax.swing.JButton();
        Q10 = new javax.swing.JButton();
        A = new javax.swing.JRadioButton();
        B = new javax.swing.JRadioButton();
        C = new javax.swing.JRadioButton();
        D = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        qLabel = new javax.swing.JLabel();
        Question = new javax.swing.JLabel();
        myName = new javax.swing.JLabel();
        myScholar = new javax.swing.JLabel();
        picture = new javax.swing.JLabel();
        time = new javax.swing.JLabel();

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

        setBackground(new java.awt.Color(51, 0, 0));
        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        Q1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Q1.setText("Q 1.");
        Q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Q1ActionPerformed(evt);
            }
        });

        Q2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Q2.setText("Q 2.");
        Q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Q2ActionPerformed(evt);
            }
        });

        Q3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Q3.setText("Q 3.");
        Q3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Q3ActionPerformed(evt);
            }
        });

        Q4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Q4.setText("Q 4.");

        Q5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Q5.setText("Q 5.");

        Q6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Q6.setText("Q 6.");

        Q7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Q7.setText("Q 7.");

        Q8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Q8.setText("Q 8.");

        Q9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Q9.setText("Q 9.");

        Q10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Q10.setText("Q10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Q4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Q5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Q6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Q7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Q8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Q9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Q10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Q3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Q2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Q1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addComponent(Q1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Q2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Q3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Q4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Q5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Q6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Q7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Q8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Q9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Q10)
                .addContainerGap())
        );

        add(jPanel1);
        jPanel1.setBounds(0, 200, 89, 480);

        A.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        A.setForeground(new java.awt.Color(153, 153, 0));
        A.setText("A");
        add(A);
        A.setBounds(130, 330, 370, 31);

        B.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        B.setForeground(new java.awt.Color(153, 153, 0));
        B.setText("B");
        add(B);
        B.setBounds(130, 380, 370, 31);

        C.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        C.setForeground(new java.awt.Color(153, 153, 0));
        C.setText("C");
        add(C);
        C.setBounds(130, 420, 370, 31);

        D.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        D.setForeground(new java.awt.Color(153, 153, 0));
        D.setText("D");
        add(D);
        D.setBounds(130, 470, 370, 31);

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(930, 580, 240, 53);

        jPanel4.setLayout(null);

        jLabel5.setText("Time Left");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(129, 26, 76, 27);

        add(jPanel4);
        jPanel4.setBounds(940, 0, 0, 0);

        qLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        qLabel.setForeground(new java.awt.Color(153, 153, 0));
        qLabel.setText("jLabel4");
        add(qLabel);
        qLabel.setBounds(130, 170, 90, 29);

        Question.setBackground(new java.awt.Color(51, 51, 0));
        Question.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Question.setForeground(new java.awt.Color(153, 153, 0));
        Question.setText("jLabel1");
        Question.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 10));
        add(Question);
        Question.setBounds(120, 210, 900, 90);

        myName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        myName.setForeground(new java.awt.Color(255, 255, 255));
        myName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        myName.setText("Rohit Patel");
        add(myName);
        myName.setBounds(140, 10, 350, 22);

        myScholar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        myScholar.setForeground(new java.awt.Color(255, 255, 255));
        myScholar.setText("141112043");
        add(myScholar);
        myScholar.setBounds(140, 50, 99, 22);

        picture.setForeground(new java.awt.Color(255, 255, 255));
        picture.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picture.setLabelFor(picture);
        picture.setText("jLabel1");
        picture.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 0), 5));
        picture.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        add(picture);
        picture.setBounds(0, 0, 120, 140);

        time.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 10));
        add(time);
        time.setBounds(970, 0, 330, 70);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       panel.setScore(calculateScore());
       this.setVisible(false);
       panel.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Q1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Q1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Q1ActionPerformed

    private void Q2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Q2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Q2ActionPerformed

    private void Q3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Q3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Q3ActionPerformed
    private int calculateScore()
    {
        int c=0;
        for(ModalClass m:list)
        {
            if(m.getAnswer().equals(m.getOptionChosen()))
                c++;
        }
        return c*3;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton A;
    private javax.swing.JRadioButton B;
    private javax.swing.JRadioButton C;
    private javax.swing.JRadioButton D;
    private javax.swing.JButton Q1;
    private javax.swing.JButton Q10;
    private javax.swing.JButton Q2;
    private javax.swing.JButton Q3;
    private javax.swing.JButton Q4;
    private javax.swing.JButton Q5;
    private javax.swing.JButton Q6;
    private javax.swing.JButton Q7;
    private javax.swing.JButton Q8;
    private javax.swing.JButton Q9;
    private javax.swing.JLabel Question;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JLabel myName;
    public static javax.swing.JLabel myScholar;
    private javax.swing.JLabel picture;
    private javax.swing.JLabel qLabel;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
    
        
        
    if(ae.getSource()==Q1)
    {
       
        
        
       /* if(flag>=0)
        {
            flag=Qno;
        }
        else
            flag=-1;*/
        
        Qno=0;
        buttonClicked();
         setQuestion(0);
         
        
       // changeSelection();
    }
    else if(ae.getSource()==Q2)
    {
        
        //flag=Qno;
        Qno=1;
        buttonClicked();
         setQuestion(1);
    }
    else if(ae.getSource()==Q3)
    {     
        //flag=Qno;
        Qno=2;
        buttonClicked();
         setQuestion(2);
    }
    else if(ae.getSource()==Q4)
    {
       // flag=Qno;
        Qno=3;
        buttonClicked();
         setQuestion(3);
    }
    else if(ae.getSource()==Q5)
    {
         //flag=Qno;
        Qno=4;
        buttonClicked();
         setQuestion(4);
    }
    else if(ae.getSource()==Q6)
    {     
        //flag=Qno;
        Qno=5;
        buttonClicked();
         setQuestion(5);
    }
    else if(ae.getSource()==Q7)
    {
       // flag=Qno;
        Qno=6;
        buttonClicked();
         setQuestion(6);
    }
    else if(ae.getSource()==Q8)
    {
         //flag=Qno;
        Qno=7;
        buttonClicked();
         setQuestion(7);
    }
    else if(ae.getSource()==Q9)
    {     
        //flag=Qno;
        Qno=8;
        buttonClicked();
         setQuestion(8);
    }
    else if(ae.getSource()==Q10)
    {
            
        //flag=Qno;
        Qno=9;
        buttonClicked();
         setQuestion(9);
    }
    else if(ae.getSource()==A)
    {
        B.setSelected(false);
        C.setSelected(false);
        D.setSelected(false);
        insertAnsToList(A);
         
    }
    else if(ae.getSource()==B)
    { 
      A.setSelected(false);
        C.setSelected(false);
        D.setSelected(false);
        insertAnsToList(B);
    }
    else if(ae.getSource()==C)
    {
        B.setSelected(false);
        A.setSelected(false);
        D.setSelected(false);
        
         insertAnsToList(C);
    }
    else if(ae.getSource()==D)
    {
        B.setSelected(false);
        C.setSelected(false);
        A.setSelected(false);
         insertAnsToList(D);
        
    }
    
    
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        
    
    
    
    }
    public void buttonClicked()
    {
       
        /*if(flag>=0)
        {
            clearSelection();
             
        }*/
            clearSelection();
        
        if(list.get(Qno).getOptionChosen()!=null)
        {
            
            if(list.get(Qno).getOptionChosen().equals(list.get(Qno).getOptionA()))
            {
                A.setSelected(true);
                
            }
            else if(list.get(Qno).getOptionChosen().equals(list.get(Qno).getOptionB()))
            {
               
                B.setSelected(true);
            }
            else if(list.get(Qno).getOptionChosen().equals(list.get(Qno).getOptionC()))
            {
               
                C.setSelected(true);
            }
            else if(list.get(Qno).getOptionChosen().equals(list.get(Qno).getOptionD()))
            {
                
                D.setSelected(true);
                
            }
            
        }
      
    }
   
    public void clearSelection()
    {
        
        A.setSelected(false);
        B.setSelected(false);
        C.setSelected(false);
        D.setSelected(false);
    }
    
   public void insertAnsToList(JRadioButton rb)
   {
       if(rb.isSelected())
        {
            list.get(Qno).setOptionChosen(rb.getText().toString());
            
        }
        else
        {
            list.get(Qno).setOptionChosen(null);
        }
       
   }
  
    
    
    
    
    
    
}
