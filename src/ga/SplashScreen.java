

package ga;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SplashScreen extends JWindow {
     final static int interval = 5000;
     int i;
    private int duration;
     JProgressBar pb=new JProgressBar();
     Timer timer;
   
     
     public SplashScreen(int d) {
        
               duration = d;
     
            }
    
    // A simple little method to show a title screen in the center
    // of the screen for the amount of time given in the constructor
    public void showSplash() {
        
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.BLACK);
        
         int width = 450;
        int height =450;
 
        
        
        
         Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);
        //setBounds(300,300,500,400);
        
        
        
         //JLabel label = new JLabel(new ImageIcon("animated_60.gif"));
         JLabel label1 = new JLabel(new ImageIcon("loading.gif"));
        // label.setOpaque(true);
        JLabel copyrt = new JLabel
                ("369 Micro Systems WELCOMES YOU!", JLabel.CENTER);
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 22));
       
        content.add(label1);
        //content.add(label);
        
        content.add(copyrt, BorderLayout.SOUTH);
        Color BLACK = new Color(0 ,0, 0);
        content.setBorder(BorderFactory.createLineBorder(BLACK, 10));
        
        // Display it
        setVisible(true);
        
        // Wait a little while, maybe while loading resources
        try { Thread.sleep(duration); } catch (Exception e) {}
        
        setVisible(false);
        
    }
          public void showSplashAndExit() {
        
        showSplash();
       // System.exit(0);
        
    }
          
          
           public static void main(String[] args) {
        
        // Throw a nice little title page up on the screen first
        SplashScreen splash = new SplashScreen(8000);

        // Normally, we'd call splash.showSplash() and get on 
        // with the program. But, since this is only a test...
        splash.showSplashAndExit();
        new MainFrame();
 
    }
}

          
          
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
          