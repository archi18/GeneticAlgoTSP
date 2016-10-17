package ga;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainGA extends Thread{
        private Image img;
	public static JFrame mainFrame;//Main Frame

	public static JPanel mainPanel, buttonsPanel, spinnerPanel;// 3 panel(component) added on JFrame

	public static DrawingPanel drawingPanel;// This is Java Class Created in Project...for Drawing Link...
       
        public static ImageIcon map;
	public static JButton randomButton, clearButton, startButton,back,exit;// 3 buttons(component) created..

	public static JSpinner rowsSpinner, columnsSpinner;// 2 Spinner created... for select  rows and columns...

	public static JTextField Atextfield, timeRetardertextfield;

	public static SpinnerModel spinnerModelRows, spinnerModelColumns;// it is used to create spinner...

	public static SearchingThread thread;// This is java class created in project ...

	public static Color backcolor,clearButtonColor, randomButtonColor, startButtonColor,
			mainBackgroundColor, tablebackgroundColor, ActiveNodeColor,
			InctiveNodeColor, StartNodeColor, GoalNodeColor, ActiveLinkColor,ActiveLinkColor2,ActiveLinkColor3;

        // Color object is created.....



	public static Border drawingPanelBorder;

	public static Dimension screenDimension, mainFrameDimension,
			buttonDimension, spinnerPanelDimension, mainPanelDimension,
			buttonsPanelDimension, drawingpanelDimension,mapDimension ;


	public static Point mainFrameLocation, startingPoint, goalPoint,
			currentPoint, nextPoint;


	public static Toolkit kit;

	public static int minRows, maxRows, minColumns, maxColumns, rows, columns,maxChildren;

	public static double  timeRetarder;

	public static int[][] activeNodes;

    public MainGA() {
//                Thread thread=new Thread();
                initializeValues();// This method is used to intialize GUI and Other values...

		createObjects();

		addObjects();
		mainFrame.setVisible(true);
		thread.start();
        
    }
        

//        // Ececution of java programes start from main method.......
//
//	public static void main(String[] args) {
//
//		
//	}

	public static void initializeValues() {





		minRows = 3;
		maxRows = 30;
		minColumns = 3;
		maxColumns = 30;
		rows = 10;
		columns = 10;
		activeNodes = new int[columns][rows];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				activeNodes[c][r] = 0;
			}
		}
		activeNodes[0][1] = 1;
		activeNodes[0][2] = 1;
		activeNodes[1][0] = 1;
		activeNodes[2][0] = 1;
		activeNodes[0][0] = 2;//start node
		activeNodes[columns - 1][rows - 1] = 3;//goal node

		kit = Toolkit.getDefaultToolkit();

		screenDimension = kit.getScreenSize();
		mainFrameDimension = new Dimension((int) (200 + screenDimension
				.getWidth() *0.7), (int) (200+ screenDimension.getWidth()
				* 0.7 / (0.7 * (0.2 + Math.sqrt(3)))));
		mainPanelDimension = new Dimension(
				(int) (screenDimension.getWidth() * 0.8),
				(int) (screenDimension.getWidth() * 0.8 / (0.8 * (1 + Math
						.sqrt(4)))));
		buttonsPanelDimension = new Dimension(
				(int) (screenDimension.getWidth() * 0.85), 100);

		drawingpanelDimension = new Dimension(
				(int) (screenDimension.getWidth() * 0.85),
				(int) (screenDimension.getWidth() * 0.85/ (0.85 * (0.9 + Math
						.sqrt(0.5)))) - 100);
		
//               
                
                buttonDimension = new Dimension(
				(int) ((screenDimension.getWidth()*1.2 - 50) / 9.0),100);
		spinnerPanelDimension = new Dimension((int) ((screenDimension
				.getWidth() * 0.9 - 550) * 2.0 / 5.0), 80);
		mainFrameLocation = new Point(
				(int) (screenDimension.getWidth() * 0.07),
				(int) (0.5 * (screenDimension.getHeight() - (int) (screenDimension
						.getWidth() * 0.07 / (0.07 * (0.35+ Math.sqrt(1)))))));

		drawingPanelBorder = BorderFactory.createMatteBorder(1, 1, 1, 1,
				Color.MAGENTA);

		clearButtonColor = new Color(0, 155, 255);
		randomButtonColor = new Color(255, 255, 0);
		startButtonColor = new Color(0, 255, 0);
                backcolor=new Color(0,0,0);
		mainBackgroundColor = new Color(255, 255, 255);
		tablebackgroundColor = new Color(255, 255, 255);
		ActiveNodeColor = new Color(0, 0, 0);
		InctiveNodeColor = new Color(200, 200, 200);
		StartNodeColor = new Color(0, 255, 0);
		GoalNodeColor = new Color(255, 0, 0);
//		
                ActiveLinkColor = new Color(255,255,0);
		ActiveLinkColor2 = new Color(255, 0, 0,50);
		ActiveLinkColor3 = new Color(0, 0, 255,50);

		maxChildren =10;
		timeRetarder =1.0;
	}

	public static void createObjects() {
		mainFrame = new JFrame("Genetic Algorithm");
//		
                mainFrame.setBounds(0,0,1025,740);

		mainPanel = new JPanel();
		mainPanel.setSize(mainPanelDimension);
		mainPanel.setMinimumSize(mainPanelDimension);
		mainPanel.setPreferredSize(mainPanelDimension);
		mainPanel.setMaximumSize(mainPanelDimension);
		
                mainPanel.setBackground(Color.BLACK);

		buttonsPanel = new JPanel();
		buttonsPanel.setSize(buttonsPanelDimension);
		buttonsPanel.setMinimumSize(buttonsPanelDimension);
		buttonsPanel.setPreferredSize(buttonsPanelDimension);
		
		buttonsPanel.setBackground(new Color(0,0,0) );//a

		spinnerPanel = new JPanel();
                
		spinnerPanel.setSize(spinnerPanelDimension);
		spinnerPanel.setMinimumSize(spinnerPanelDimension);
		spinnerPanel.setPreferredSize(spinnerPanelDimension);
		spinnerPanel.setMaximumSize(spinnerPanelDimension);
                spinnerPanel.setBackground(Color.red);//******//
		spinnerPanel.setLayout(new GridLayout(2, 2));

		thread = new SearchingThread(rows, columns, activeNodes,maxChildren, timeRetarder);
		thread.setPriority(thread.NORM_PRIORITY);

		drawingPanel = new DrawingPanel(drawingpanelDimension,
				tablebackgroundColor, ActiveNodeColor, InctiveNodeColor,
				ActiveLinkColor,ActiveLinkColor2,ActiveLinkColor3, StartNodeColor, GoalNodeColor, thread);
		drawingPanel.setSize(drawingpanelDimension);
		drawingPanel.setMinimumSize(drawingpanelDimension);
		drawingPanel.setPreferredSize(drawingpanelDimension);
		drawingPanel.setMaximumSize(drawingpanelDimension);
		///drawingPanel.setBackground(tablebackgroundColor);//******
                drawingPanel.setBackground(new Color(255,255,255));
		drawingPanel.setBorder(drawingPanelBorder);
		drawingPanel.setRowsColumns(rows, columns);
		drawingPanel.setActiveNodes(activeNodes);
                
             
                 
		thread.setDrawingPanel(drawingPanel);
		thread.setMaxChildren(maxChildren);
		thread.setTimeRetarder(timeRetarder);
                
                back=new JButton("Back");
                back.setSize(10,10);
//                back.setMinimumSize(buttonDimension);
//		back.setPreferredSize(buttonDimension);
//		back.setMaximumSize(buttonDimension);
                back.setBackground(new Color(0,255,0));
                back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

                            new MainFrame();
                            mainFrame.setVisible(false);
			}
		});
                  exit=new JButton("Exit");
                exit.setSize(10,10);
//                back.setMinimumSize(buttonDimension);
//		back.setPreferredSize(buttonDimension);
//		back.setMaximumSize(buttonDimension);
                exit.setBackground(new Color(255,0,0));
                exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

                          System.exit(0);
			}
		});
		randomButton = new JButton("Random");
		randomButton.setSize(buttonDimension);
		randomButton.setMinimumSize(buttonDimension);
		randomButton.setPreferredSize(buttonDimension);
		randomButton.setMaximumSize(buttonDimension);
		
                randomButton.setBackground(new Color(184, 102, 219));
               
		randomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int activePoints = 0;
				while (activePoints < 4) {

					for (int r = 0; r < rows; r++) {
						for (int c = 0; c < columns; c++) {
							activeNodes[c][r] = (int) (2 * Math.random() * 0.8);
						}
					}
					int r1 = (int) (rows * Math.random());
					int c1 = (int) (columns * Math.random());
					int r2 = r1;
					while (r2 == r1)
						r2 = (int) (rows * Math.random());
					int c2 = c1;
					while (c2 == c1)
						c2 = (int) (columns * Math.random());
					activeNodes[c1][r1] = 2;
					activeNodes[c2][r2] = 3;

					activePoints = 0;
					for (int r = 0; r < rows; r++) {
						for (int c = 0; c < columns; c++) {
							if (activeNodes[c][r] == 1)
								activePoints = activePoints + 1;
						}
					}
				}

				thread.search(false);
				thread.setRowsColumns(rows, columns);
				thread.setActiveNodes(activeNodes);
				drawingPanel.drawLinks(false);
				drawingPanel.setRowsColumns(rows, columns);
				drawingPanel.setActiveNodes(activeNodes);
			}
		});

		clearButton = new JButton("Clear");
		clearButton.setSize(buttonDimension);
		clearButton.setMinimumSize(buttonDimension);
		clearButton.setPreferredSize(buttonDimension);
		clearButton.setMaximumSize(buttonDimension);
		//clearButton.setBackground(clearButtonColor);//*****
                clearButton.setBackground(new Color(176, 230, 244));
                //clearButton.setForeground(Color.blue);//*****
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int r = 0; r < rows; r++) {
					for (int c = 0; c < columns; c++) {
						activeNodes[c][r] = 0;
					}
				}
				activeNodes[0][1] = 1;
				activeNodes[0][2] = 1;
				activeNodes[1][0] = 1;
				activeNodes[2][0] = 1;
				activeNodes[0][0] = 2;
				activeNodes[columns - 1][rows - 1] = 3;
				thread.search(false);
				thread.setRowsColumns(rows, columns);
				thread.setActiveNodes(activeNodes);
				drawingPanel.drawLinks(false);
				drawingPanel.setRowsColumns(rows, columns);
				drawingPanel.setActiveNodes(activeNodes);
			}
		});

		startButton = new JButton("Start");
		startButton.setSize(buttonDimension);
		startButton.setMinimumSize(buttonDimension);
		startButton.setPreferredSize(buttonDimension);
		startButton.setMaximumSize(buttonDimension);
		//startButton.setBackground(startButtonColor);//***
                startButton.setBackground(new Color(204, 248, 73) );//***
                //startButton.setForeground(Color.blue);//******
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int activePoints = 0;
				for (int r = 0; r < rows; r++) {
					for (int c = 0; c < columns; c++) {
						if (activeNodes[c][r] == 1)
							activePoints = activePoints + 1;
					}
				}
				if (activePoints >= 4)
					drawingPanel.drawLinks(true);
				thread.search(true);
			}
		});

		Atextfield = new JTextField("MaxChildren = " +maxChildren);
                Atextfield.setBackground(mainBackgroundColor);
		Atextfield.setBorder(BorderFactory
				.createTitledBorder("Children"));
		
		Atextfield.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = Atextfield.getText();
				s = s.replace("MaxChildren", "");
				s = s.replace("=", "");
				s = s.replace(" ", "");
				maxChildren = Integer.parseInt(s);
				drawingPanel.drawLinks(false);
				thread.search(false);
				thread.setMaxChildren(maxChildren);
				Atextfield.setText("MaxChildren = " + maxChildren);
			}
		});
		timeRetardertextfield = new JTextField("Time Retarder = " + timeRetarder);
		timeRetardertextfield.setBorder(BorderFactory
				.createTitledBorder("time=time*timeRetarder"));
		timeRetardertextfield.setBackground(mainBackgroundColor);
		timeRetardertextfield.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = timeRetardertextfield.getText();
				s = s.replace("B", "");
				s = s.replace("b", "");
				s = s.replace("=", "");
				s = s.replace(" ", "");
				timeRetarder = Double.parseDouble(s);
				drawingPanel.drawLinks(false);
				thread.search(false);
				thread.setTimeRetarder(timeRetarder);
				timeRetardertextfield.setText("Time Retarder = " + timeRetarder);
			}
		});

		spinnerModelRows = new SpinnerNumberModel(rows, minRows, maxRows, 1);

		rowsSpinner = new JSpinner(spinnerModelRows);

		rowsSpinner.setBorder(BorderFactory.createTitledBorder("Rows"));
		rowsSpinner.setBackground(mainBackgroundColor);
		rowsSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				rows = Integer.parseInt(rowsSpinner.getValue().toString());
				activeNodes = new int[columns][rows];
				for (int r = 0; r < rows; r++) {
					for (int c = 0; c < columns; c++) {
						activeNodes[c][r] = 0;
					}
				}
				activeNodes[0][1] = 1;
				activeNodes[0][2] = 1;
				activeNodes[1][0] = 1;
				activeNodes[2][0] = 1;
				activeNodes[0][0] = 2;
				activeNodes[columns - 1][rows - 1] = 3;
				drawingPanel.drawLinks(false);
				thread.search(false);
				thread.setRowsColumns(rows, columns);
				drawingPanel.setRowsColumns(rows, columns);
				drawingPanel.setActiveNodes(activeNodes);
			}
		});

		spinnerModelColumns = new SpinnerNumberModel(columns, minColumns,
				maxColumns, 1);
		columnsSpinner = new JSpinner(spinnerModelColumns);
		columnsSpinner.setBorder(BorderFactory.createTitledBorder("Columns"));
		columnsSpinner.setBackground(mainBackgroundColor);
		columnsSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				columns = Integer
						.parseInt(columnsSpinner.getValue().toString());
				activeNodes = new int[columns][rows];
				for (int r = 0; r < rows; r++) {
					for (int c = 0; c < columns; c++) {
						activeNodes[c][r] = 0;
					}
				}
				activeNodes[0][1] = 1;
				activeNodes[0][2] = 1;
				activeNodes[1][0] = 1;
				activeNodes[2][0] = 1;
				activeNodes[0][0] = 2;
				activeNodes[columns - 1][rows - 1] = 3;
				drawingPanel.drawLinks(false);
				thread.search(false);
				thread.setRowsColumns(rows, columns);
				drawingPanel.setRowsColumns(rows, columns);
				drawingPanel.setActiveNodes(activeNodes);
			}
		});
	}

	public static void addObjects() {
		spinnerPanel.add(rowsSpinner);
//		spinnerPanel.add(Atextfield);
		spinnerPanel.add(columnsSpinner);
//		spinnerPanel.add(timeRetardertextfield);
                buttonsPanel.add(back);
		buttonsPanel.add(randomButton);
		buttonsPanel.add(clearButton);
		buttonsPanel.add(startButton);
                  buttonsPanel.add(exit);
		buttonsPanel.add(spinnerPanel);
               
		mainPanel.add(buttonsPanel);
		mainPanel.add(drawingPanel);
               //drawingPanel.add(map);
              
                 mainFrame.add(mainPanel);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}