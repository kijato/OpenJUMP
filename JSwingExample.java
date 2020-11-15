/*

javac JSwingExample.java && jar cfe JSwingExample.jar JSwingExample JSwingExample.class && java -jar JSwingExample.jar 

https://docs.oracle.com/javase/8/docs/api/javax/swing/JMenuItem.html

*/

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class JSwingExample extends JFrame implements ActionListener {

   JMenuBar jmb;
   JMenu jmF;
   JMenuItem jmiO;
   JMenuItem jmiS;
   JMenuItem jmiQ;
   JMenuBar sb;
   JLabel statusLabel;
   JTextArea ta;

   public static void main(String args[]){
      new JSwingExample();
   }

   JSwingExample(){
      jmb = new JMenuBar();
      jmF = new JMenu("File");
      jmiO = new JMenuItem("Open"); jmiO.addActionListener(this); jmF.add(jmiO);
      jmiS = new JMenuItem("Save"); jmiS.addActionListener(this); jmF.add(jmiS);
      jmiS.setEnabled(false);
      jmF.addSeparator();
      jmiQ = new JMenuItem("Quit"); jmiQ.addActionListener(this); jmF.add(jmiQ); 
      jmb.add(jmF);
      jmb.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.lightGray));
      
      sb = new JMenuBar();
      sb.setBorder(new BevelBorder(BevelBorder.LOWERED));
      add(sb,BorderLayout.SOUTH);
      
      statusLabel=new JLabel(" ",null,JLabel.LEFT);
      statusLabel.setFont(new Font("sansserif", Font.PLAIN, 12));
      sb.add(statusLabel);

      ta=new JTextArea("");
      ta.setLineWrap(true);
      add(ta,BorderLayout.CENTER);
      
      ta.append(System.getenv("PATH"));

      setJMenuBar(jmb);
      setTitle(System.getProperty("user.name"));
      setSize(400,300);
      setLocationRelativeTo(null);
      setVisible(true);
      setDefaultCloseOperation(this.EXIT_ON_CLOSE);
      revalidate();
   }
   
   public void actionPerformed(ActionEvent e){
      ta.append(e.getSource().toString()+"\n\n");
      statusLabel.setText(e.getActionCommand());
      
      if ( e.getActionCommand() == "Open" ) {
         jmiS.setEnabled(true);
         JFileChooser fileChooser = new JFileChooser();
         //fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
         fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
         if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
             File selectedFile = fileChooser.getSelectedFile();
             ta.append("Selected file: " + selectedFile.getAbsolutePath());
         }
      }

      if ( e.getActionCommand() == "Quit" ) System.exit(0);
      
   }

}