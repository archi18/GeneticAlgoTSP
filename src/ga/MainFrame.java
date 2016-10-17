package ga;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
public class MainFrame extends JPanel implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JMenuBar mbr;
    private JMenu filemenu,helpmenu,aboutmenu;;
    private JMenuItem file,help,about,exit;

    
    public MainFrame() {
        frame=new JFrame("Genetic Algorithm");
        panel=new JPanel();
        panel.setBackground(new Color(0,0,0));
//        panel=new JPanel();
        frame.getContentPane().add(panel);

      // JLabel label1 = new JLabel(new ImageIcon("DNA_animation.gif"));
       //add(label, BorderLayout.CENTER);
        ImageIcon icon= new ImageIcon("MYTEXT5.gif");
        ImageIcon icon1= new ImageIcon("images3.jpg");
        ImageIcon icon2= new ImageIcon("images2.jpg");
        
       //ImageIcon icon2= new ImageIcon("gif1.gif");
     JLabel label = new JLabel(icon);
     JLabel label1 = new JLabel(icon1);
     JLabel label2 = new JLabel(icon2,JLabel.CENTER);
    
               mbr=new JMenuBar();
        
        file=new JMenuItem("Genetic Algo");
        file.setMnemonic('f');
        file.addActionListener(this);
        
        help=new JMenuItem("Help");
        help.setMnemonic('h');
        help.addActionListener(this);
        
        exit=new JMenuItem("Exit");
        exit.setMnemonic('e');
        exit.addActionListener(this);
        
        about=new JMenuItem("About-Us");
        about.setMnemonic('a');
        about.addActionListener(this);
        
        
        filemenu=new JMenu("File");
        filemenu.setMnemonic('F');
        filemenu.add(file);
        filemenu.add(exit);
        
        helpmenu=new JMenu("Help");
        helpmenu.add(help);
        
        aboutmenu=new JMenu("About us");
        aboutmenu.add(about);
        
        
        mbr.add(filemenu);
        
        mbr.add(helpmenu);
        mbr.add(aboutmenu);
        frame.setJMenuBar(mbr);
        //panel.add(label1,BorderLayout.WEST);
        panel.add(label);
        //panel.add(label2);
   //   panel.add(label1);
      panel.add(label2);
//       frame.setBounds(100,100,800,550);
       frame.setBounds(0,0,1025,740);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
  
      
    }
    


    public void actionPerformed(ActionEvent e) {
        Object str=e.getSource();
        if(str.equals(file))
        {
            new MainGA();
            frame.setVisible(false);
        }
        if(str.equals(exit))
        {
            System.exit(0);
        }
        
        if(str.equals(help))
        {
            new Help();
        }
        if(str.equals(about))
        {
            new About();
        }
        
        
    }
    


}

