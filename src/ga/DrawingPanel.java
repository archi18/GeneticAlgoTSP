package ga;



import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
    
     private Image img;
    
    	public Dimension drawingPanelDimension;
	public Color tablebackgroundColor, ActiveNodeColor, InactiveNodeColor,
			StartNodeColor, GoalNodeColor, ActiveLinkColor,ActiveLinkColor2,ActiveLinkColor3;
	public int rows, columns, x, y, handRadius, nodeRadius, greenColumn,
			greenRow, redColumn, redRow;
	public double width, height;
	public int[][] activeNodes, activeLinks;
	public boolean drawLinks, draggingGreen, draggingRed;
	public Ellipse2D node;
	public Line2D link;
	public Point startPoint, goalPoint;
	public ArrayList<Point> pointsList,pointsList2,pointsList3;
	public SearchingThread thread;
        //public ImageIcon map;
       
	public DrawingPanel(Dimension dpd, Color tbc, Color anc, Color inc,
			Color alc, Color alc2,Color alc3,Color snc, Color gnc, SearchingThread t) {
	
            drawingPanelDimension = dpd;
		tablebackgroundColor = tbc;
		ActiveNodeColor = anc;
		InactiveNodeColor = inc;
		ActiveLinkColor = alc;
		ActiveLinkColor2 = alc2;
		ActiveLinkColor3 = alc2;
		StartNodeColor = snc;
		GoalNodeColor = gnc;
		thread = t;
              	pointsList = new ArrayList<Point>();
		pointsList2 = new ArrayList<Point>();
		pointsList3 = new ArrayList<Point>();

		MouseNodeListener mouseListener = new MouseNodeListener();
		addMouseListener(mouseListener);
		MouseNodeListener2 mouseListener2 = new MouseNodeListener2();
		addMouseMotionListener(mouseListener2);
              
		drawLinks = false;
		draggingGreen = false;
		draggingRed = false;
                
                 ImageIcon icon = new ImageIcon("mumbai-central.jpg");
                 img = icon.getImage();
                
	}
        
       	public void setRowsColumns(int r, int c) {
		rows = r;
		columns = c;
		if (drawingPanelDimension.getWidth() / columns < drawingPanelDimension
				.getHeight()
				/ rows) {
			handRadius = (int) (0.4 * drawingPanelDimension.getWidth() / columns);
		} else {
			handRadius = (int) (0.4 * drawingPanelDimension.getHeight() / rows);
		}
              	activeNodes = new int[columns][rows];
                         		
                repaint();
	}

	public void setActiveNodes(int[][] an) {
		activeNodes = an;

		pointsList.clear();
		pointsList2.clear();
		pointsList3.clear();

		for (int rr = 0; rr < rows; rr++) {
			for (int cc = 0; cc < columns; cc++) {
				if (activeNodes[cc][rr] == 2)
					startPoint = new Point(cc, rr);
				if (activeNodes[cc][rr] == 3)
					goalPoint = new Point(cc, rr);
				if (activeNodes[cc][rr] == 1){
					pointsList.add(new Point(cc, rr));
				pointsList2.add(new Point(cc, rr));
				pointsList3.add(new Point(cc, rr));}
			}
		}
		repaint();
	}

	public void setLinks(ArrayList<Point> pl,ArrayList<Point> pl2,ArrayList<Point> pl3) {
		pointsList = pl;
		pointsList2 = pl2;
		pointsList3 = pl3;
		repaint();
	}

	public void drawLinks(boolean b) {
		drawLinks = b;
		repaint();
	}
        

	public void paintComponent(Graphics g) {
           g.drawImage(img, 0, 0, this);
            setOpaque(false);
            
            
            super.paintComponent(g);
                setOpaque(true);
		Graphics2D g2 = (Graphics2D) g;
             
	
                      
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		width = drawingPanelDimension.getWidth() / columns;
		height = drawingPanelDimension.getHeight() / rows;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				if (activeNodes[c][r] == 1) {
					g2.setPaint(ActiveNodeColor);
					nodeRadius = 6;
				}
				if (activeNodes[c][r] == 0) {
					g2.setPaint(InactiveNodeColor);
					nodeRadius = 4;
				}
				if (activeNodes[c][r] == 2) {
					g2.setPaint(StartNodeColor);
					nodeRadius = 8;
				}
				if (activeNodes[c][r] == 3) {
					g2.setPaint(GoalNodeColor);
					nodeRadius = 8;
				}

				node = new Ellipse2D.Double(0.5 * width + width * c
						- nodeRadius * 0 - 5, 0.5 * height + height * r
						- nodeRadius * 0 - 5, nodeRadius, nodeRadius);
                               
				g2.draw(node);
				g2.fill(node);
			}
		}
		try {
			// SECOND PARENT
			if (pointsList2.size() > 0 && drawLinks) {
				g2.setPaint(ActiveLinkColor2);
				link = new Line2D.Double(0.5 * width + width
						* startPoint.getX(), 0.5 * height + height
						* startPoint.getY(), 0.5 * width + width
						* pointsList2.get(0).getX(), 0.5 * height + height
						* pointsList2.get(0).getY());
				g2.draw(link);
				link = new Line2D.Double(
						0.5 * width + width * goalPoint.getX(), 0.5 * height
								+ height * goalPoint.getY(), 0.5 * width
								+ width
								* pointsList2.get(pointsList2.size() - 1).getX(),
						0.5 * height + height
								* pointsList2.get(pointsList2.size() - 1).getY());
				g2.draw(link);
				for (int i = 1; i < pointsList2.size(); i++) {
					link = new Line2D.Double(0.5 * width + width
							* pointsList2.get(i - 1).getX(), 0.5 * height
							+ height * pointsList2.get(i - 1).getY(), 0.5
							* width + width * pointsList2.get(i).getX(), 0.5
							* height + height * pointsList2.get(i).getY());
					g2.draw(link);
				}
			}
			// THIRD PARENT
			if (pointsList3.size() > 0 && drawLinks) {
				g2.setPaint(ActiveLinkColor3);
				link = new Line2D.Double(0.5 * width + width
						* startPoint.getX(), 0.5 * height + height
						* startPoint.getY(), 0.5 * width + width
						* pointsList3.get(0).getX(), 0.5 * height + height
						* pointsList3.get(0).getY());
				g2.draw(link);
				link = new Line2D.Double(
						0.5 * width + width * goalPoint.getX(), 0.5 * height
								+ height * goalPoint.getY(), 0.5 * width
								+ width
								* pointsList3.get(pointsList3.size() - 1).getX(),
						0.5 * height + height
								* pointsList3.get(pointsList3.size() - 1).getY());
				g2.draw(link);
				for (int i = 1; i < pointsList3.size(); i++) {
					link = new Line2D.Double(0.5 * width + width
							* pointsList3.get(i - 1).getX(), 0.5 * height
							+ height * pointsList3.get(i - 1).getY(), 0.5
							* width + width * pointsList3.get(i).getX(), 0.5
							* height + height * pointsList3.get(i).getY());
					g2.draw(link);
				}
			}
			// FIRST PARENT
			if (pointsList.size() > 0 && drawLinks) {
				g2.setPaint(ActiveLinkColor);
				link = new Line2D.Double(0.5 * width + width
						* startPoint.getX(), 0.5 * height + height
						* startPoint.getY(), 0.5 * width + width
						* pointsList.get(0).getX(), 0.5 * height + height
						* pointsList.get(0).getY());
				g2.draw(link);
				link = new Line2D.Double(
						0.5 * width + width * goalPoint.getX(), 0.5 * height
								+ height * goalPoint.getY(), 0.5 * width
								+ width
								* pointsList.get(pointsList.size() - 1).getX(),
						0.5 * height + height
								* pointsList.get(pointsList.size() - 1).getY());
				g2.draw(link);
				for (int i = 1; i < pointsList.size(); i++) {
					link = new Line2D.Double(0.5 * width + width
							* pointsList.get(i - 1).getX(), 0.5 * height
							+ height * pointsList.get(i - 1).getY(), 0.5
							* width + width * pointsList.get(i).getX(), 0.5
							* height + height * pointsList.get(i).getY());
					g2.draw(link);
                                       
				}
			}
		} catch (Exception e) {
		}
	}

	public class MouseNodeListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			for (int r = 0; r < rows; r++) {
				for (int c = 0; c < columns; c++) {
					if (Math.sqrt(Math.pow(x - (0.5 * width + width * c), 2)
							+ Math.pow(y - (0.5 * height + height * r), 2)) <= handRadius) {
						if (activeNodes[c][r] == 0) {
							activeNodes[c][r] = 1;
							thread.search(false);
							drawLinks = false;
							thread.setActiveNodes(activeNodes);
						} else {
							if (activeNodes[c][r] == 1) {
								activeNodes[c][r] = 0;
								thread.search(false);
								drawLinks = false;
								thread.setActiveNodes(activeNodes);
							}
						}
					}
				}
			}
			repaint();
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			thread.search(false);
			x = e.getX();
			y = e.getY();
			for (int r = 0; r < rows; r++) {
				for (int c = 0; c < columns; c++) {
					if (Math.sqrt(Math.pow(x - (0.5 * width + width * c), 2)
							+ Math.pow(y - (0.5 * height + height * r), 2)) <= handRadius) {
						if (activeNodes[c][r] == 2) {
							greenColumn = c;
							greenRow = r;
							draggingGreen = true;
						}
						if (activeNodes[c][r] == 3) {
							redColumn = c;
							redRow = r;
							draggingRed = true;
						}
					}
				}
			}
		}

		public void mouseReleased(MouseEvent e) {
			thread.search(false);
			x = e.getX();
			y = e.getY();
			if (draggingGreen) {
				for (int r = 0; r < rows; r++) {
					for (int c = 0; c < columns; c++) {
						if (Math.sqrt(Math
								.pow(x - (0.5 * width + width * c), 2)
								+ Math.pow(y - (0.5 * height + height * r), 2)) <= handRadius) {
							if (activeNodes[c][r] == 0) {
								activeNodes[c][r] = 2;
								startPoint = new Point(c, r);
								activeNodes[greenColumn][greenRow] = 0;
							}
							if (activeNodes[c][r] == 1) {
								activeNodes[c][r] = 2;
								startPoint = new Point(c, r);
								activeNodes[greenColumn][greenRow] = 1;
							}
						}
					}
				}
			}
			if (draggingRed) {
				for (int r = 0; r < rows; r++) {
					for (int c = 0; c < columns; c++) {
						if (Math.sqrt(Math
								.pow(x - (0.5 * width + width * c), 2)
								+ Math.pow(y - (0.5 * height + height * r), 2)) <= handRadius) {
							if (activeNodes[c][r] == 0) {
								activeNodes[c][r] = 3;
								goalPoint = new Point(c, r);
								activeNodes[redColumn][redRow] = 0;
							}
							if (activeNodes[c][r] == 1) {
								activeNodes[c][r] = 3;
								goalPoint = new Point(c, r);
								activeNodes[redColumn][redRow] = 1;
							}
						}
					}
				}
			}
			drawLinks = false;
			thread.setActiveNodes(activeNodes);
			repaint();
			draggingGreen = false;
			draggingRed = false;
		}
	}

	public class MouseNodeListener2 implements MouseMotionListener {

		public void mouseMoved(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			for (int r = 0; r < rows; r++) {
				for (int c = 0; c < columns; c++) {
					if (Math.sqrt(Math.pow(x - (0.5 * width + width * c), 2)
							+ Math.pow(y - (0.5 * height + height * r), 2)) <= handRadius) {
						setCursor(Cursor
								.getPredefinedCursor(Cursor.HAND_CURSOR));
					}
				}
			}
		}

		public void mouseDragged(MouseEvent e) {
		}
	}
}
