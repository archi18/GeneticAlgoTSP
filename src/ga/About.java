package ga;


 import java.awt.*;
 import javax.swing.*;
 /**
  * Summary description for label
  *
  */
 public class About extends JFrame
 {
 	// Variables declaration
 	private JLabel jLabel1;
 	private JLabel jLabel2;
 	private JLabel jLabel3;
 	private JLabel jLabel4;
 	private JLabel jLabel5;
 	private JLabel jLabel6;
 	private JLabel jLabel7;
 	private JPanel contentPane;
 	// End of variables declaration


 	public About()
 	{
 		super();
 		initializeComponent();
 		//
 		// TODO: Add any constructor code after initializeComponent call
 		//
                    this.setBounds(300,200,400,400);
 		this.setVisible(true);
 	}

 	
 	private void initializeComponent()
 	{
 		jLabel1 = new JLabel();
 		jLabel2 = new JLabel();
 		jLabel3 = new JLabel();
 		jLabel4 = new JLabel();
 		jLabel5 = new JLabel();
 		jLabel6 = new JLabel();
 		jLabel7 = new JLabel();
 		contentPane = (JPanel)this.getContentPane();

 		//
 		// jLabel1
 		//
 		jLabel1.setBackground(new Color(255, 51, 102));
 		jLabel1.setForeground(new Color(47, 241, 14));
 		jLabel1.setText("  College Of Engineering");
 		jLabel1.setVerticalAlignment(SwingConstants.TOP);
                jLabel1.setFont(new Font("Arial Bold",Font.BOLD,20));
 		//
 		// jLabel2
 		//
 		jLabel2.setBackground(new Color(255, 0, 102));
 		jLabel2.setForeground(new Color(23, 245, 9));
 		jLabel2.setText("                ***Group Guide***");
                jLabel2.setFont(new Font("Arial Bold",Font.BOLD,20));
 		//
 		// jLabel3
 		//
 		jLabel3.setBackground(new Color(255, 102, 102));
 		jLabel3.setForeground(new Color(153, 244, 51));
 		jLabel3.setText("                   Prof. XYXZ");
                jLabel3.setFont(new Font("Arial Bold",Font.BOLD,20));
 		//
 		// jLabel4
 		//
 		jLabel4.setBackground(new Color(255, 51, 102));
 		jLabel4.setForeground(new Color(102, 255, 0));
 		jLabel4.setText("               ***Group Members***");
                jLabel4.setFont(new Font("Arial Bold",Font.BOLD,20));
 		//
 		// jLabel5
 		//
 		jLabel5.setBackground(new Color(255, 0, 51));
 		jLabel5.setForeground(new Color(102, 255, 0));
 		jLabel5.setText("                  Member One");
                jLabel5.setFont(new Font("Arial Bold",Font.BOLD,20));
 		//
 		// jLabel6
 		//
 		jLabel6.setBackground(new Color(255, 0, 51));
 		jLabel6.setForeground(new Color(153, 255, 0));
 		jLabel6.setText("                  Memeber Two");
                jLabel6.setFont(new Font("Arial Bold",Font.BOLD,20));
 		//
 		// jLabel7
 		//
 		jLabel7.setBackground(new Color(255, 51, 102));
 		jLabel7.setForeground(new Color(102, 255, 0));
 		jLabel7.setText("                 Member Three");
                jLabel7.setFont(new Font("Arial Bold",Font.BOLD,20));
 		//
 		// contentPane
 		//
 		contentPane.setLayout(null);
 		contentPane.setBackground(new Color(0, 0, 0));
 		contentPane.setForeground(new Color(78, 249, 17));
 		addComponent(contentPane, jLabel1, 0,10,384,68);
 		addComponent(contentPane, jLabel2, 3,82,384,50);
 		addComponent(contentPane, jLabel3, 2,104,384,50);
 		addComponent(contentPane, jLabel4, 0,129,384,50);
 		addComponent(contentPane, jLabel5, -4,154,386,50);
 		addComponent(contentPane, jLabel6, 1,180,384,50);
 		addComponent(contentPane, jLabel7, 2,208,384,50);
 		//
 		// label
 		//
 		this.setTitle("label - extends JFrame");
 		this.setLocation(new Point(0, 0));
 		this.setSize(new Dimension(390, 300));
 	}

 	/** Add Component Without a Layout Manager (Absolute Positioning) */
 	private void addComponent(Container container,Component c,int x,int y,int width,int height)
 	{
 		c.setBounds(x,y,width,height);
 		container.add(c);
 	}


 }

