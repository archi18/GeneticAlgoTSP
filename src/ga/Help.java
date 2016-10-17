/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ga;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author manish
 */
public class Help {
    private JFrame frame;
    private JPanel panel;
    public Help() {
        
        
        frame=new JFrame("Genetic Algorithm");
        panel=new JPanel();
        frame.getContentPane().add(panel);
        
        
          panel.setBackground(Color.black);
          frame.setBounds(300,200,400,400);
          frame.setVisible(true);
    }

    
}
