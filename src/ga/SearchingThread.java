package ga;



import java.awt.Point;
import java.util.ArrayList;
import java.util.Calendar;

public class SearchingThread extends Thread {

	public static boolean search;
	public static DrawingPanel drawingPanel;
	public static int[][] activeNodes;
	public static ArrayList<ArrayList<Point>> populationTree, chosenPopulation;
	public static int rows, columns, maxChildren, randomPositionA,
			randomPositionB, randomPositionC, randomPositionD, size,
			bestPosition, securityCounter;
	public static double time, timeO, currentDistance, currentDistance2,
			currentDistance3, trialDistance, distance, timeRetarder,
			randomOption, minimumP, minimumPP, minimumPPP;
	public static Point startPoint, goalPoint, pointA, pointB, pointC, pointD;
	public static ArrayList<Point> pointsList, pointsList2, pointsList3,
			auxiliarPointsList, auxiliarPointsList2, auxiliarPointsList3,
			childrenPointsList;

	public SearchingThread(int r, int c, int[][] an, int mc, double tr) {
		search = false;
		activeNodes = an;
		rows = r;
		columns = c;

		pointsList = new ArrayList<Point>();
		pointsList2 = new ArrayList<Point>();
		pointsList3 = new ArrayList<Point>();
		auxiliarPointsList = new ArrayList<Point>();
		childrenPointsList = new ArrayList<Point>();
		populationTree = new ArrayList<ArrayList<Point>>();

		
		for (int rr = 0; rr < rows; rr++) {
			for (int cc = 0; cc < columns; cc++) {
				if (activeNodes[cc][rr] == 2)
					startPoint = new Point(cc, rr);
				if (activeNodes[cc][rr] == 3)
					goalPoint = new Point(cc, rr);
				if (activeNodes[cc][rr] == 1)
					pointsList.add(new Point(cc, rr));
			}
		}
		size = pointsList.size();
		pointsList2.clear();
		pointsList3.clear();
		for (int i = 0; i < size; i++) {
			pointsList2.add(pointsList.get(i));
			pointsList3.add(pointsList.get(i));
		}
		// CREATE RANDOM INITIAL VALUES For the three initial parents
		for (int i = 0; i < 100; i++) {
			randomPositionA = (int) (size * Math.random());
			randomPositionB = (int) (size * Math.random());
			pointA = pointsList.get(randomPositionA);
			pointB = pointsList.get(randomPositionB);
			pointsList.set(randomPositionA, pointB);
			pointsList.set(randomPositionB, pointA);
		}
		for (int i = 0; i < 100; i++) {
			randomPositionA = (int) (size * Math.random());
			randomPositionB = (int) (size * Math.random());
			pointA = pointsList2.get(randomPositionA);
			pointB = pointsList2.get(randomPositionB);
			pointsList2.set(randomPositionA, pointB);
			pointsList2.set(randomPositionB, pointA);
		}
		for (int i = 0; i < 100; i++) {
			randomPositionA = (int) (size * Math.random());
			randomPositionB = (int) (size * Math.random());
			pointA = pointsList3.get(randomPositionA);
			pointB = pointsList3.get(randomPositionB);
			pointsList3.set(randomPositionA, pointB);
			pointsList3.set(randomPositionB, pointA);
		}
		populationTree.clear();
		populationTree.add(pointsList);
		populationTree.add(pointsList2);
		populationTree.add(pointsList3);

		maxChildren = mc;
		timeRetarder = tr;
		minimumP = 0.1;
		minimumPP = 0.2;
		minimumPPP = 0.3;
	}

	public void setMaxChildren(int a) {
		maxChildren = a;
	}

	public void setTimeRetarder(double b) {
		timeRetarder = b;
	}

	public void setDrawingPanel(DrawingPanel d) {
		drawingPanel = d;
	}

	public void setRowsColumns(int r, int c) {
		rows = r;
		columns = c;
		activeNodes = new int[columns][rows];
	}

	public void setActiveNodes(int[][] an) {
		activeNodes = an;

		pointsList.clear();

		for (int rr = 0; rr < rows; rr++) {
			for (int cc = 0; cc < columns; cc++) {
				if (activeNodes[cc][rr] == 2)
					startPoint = new Point(cc, rr);
				if (activeNodes[cc][rr] == 3)
					goalPoint = new Point(cc, rr);
				if (activeNodes[cc][rr] == 1)
					pointsList.add(new Point(cc, rr));
			}
		}
		size = pointsList.size();
		pointsList2.clear();
		pointsList3.clear();
		for (int i = 0; i < size; i++) {
			pointsList2.add(pointsList.get(i));
			pointsList3.add(pointsList.get(i));
		}
		// CREATE RANDOM INITIAL VALUES
		for (int i = 0; i < 100; i++) {
			randomPositionA = (int) (size * Math.random());
			randomPositionB = (int) (size * Math.random());
			pointA = pointsList.get(randomPositionA);
			pointB = pointsList.get(randomPositionB);
			pointsList.set(randomPositionA, pointB);
			pointsList.set(randomPositionB, pointA);
		}
		for (int i = 0; i < 100; i++) {
			randomPositionA = (int) (size * Math.random());
			randomPositionB = (int) (size * Math.random());
			pointA = pointsList2.get(randomPositionA);
			pointB = pointsList2.get(randomPositionB);
			pointsList2.set(randomPositionA, pointB);
			pointsList2.set(randomPositionB, pointA);
		}
		for (int i = 0; i < 100; i++) {
			randomPositionA = (int) (size * Math.random());
			randomPositionB = (int) (size * Math.random());
			pointA = pointsList3.get(randomPositionA);
			pointB = pointsList3.get(randomPositionB);
			pointsList3.set(randomPositionA, pointB);
			pointsList3.set(randomPositionB, pointA);
		}
		populationTree.clear();
		populationTree.add(pointsList);
		populationTree.add(pointsList2);
		populationTree.add(pointsList3);
	}

	public void search(boolean b) {
		search = b;
	}

	public void run() {
		while (true) {
			if (search) {try{
				time = Calendar.getInstance().getTimeInMillis() * 0.001 - timeO;

				// CREATE CHILDREN FOR EACH OF THE THREE PARENTS
				auxiliarPointsList = new ArrayList<Point>();
				for (int j = 0; j < populationTree.get(0).size(); j++) {
					auxiliarPointsList.add(populationTree.get(0).get(j));
				}
				auxiliarPointsList2 = new ArrayList<Point>();
				for (int j = 0; j < populationTree.get(1).size(); j++) {
					auxiliarPointsList2.add(populationTree.get(1).get(j));
				}
				auxiliarPointsList3 = new ArrayList<Point>();
				for (int j = 0; j < populationTree.get(2).size(); j++) {
					auxiliarPointsList3.add(populationTree.get(2).get(j));
				}
				populationTree = new ArrayList<ArrayList<Point>>();
				populationTree.add(auxiliarPointsList);
				populationTree.add(auxiliarPointsList2);
				populationTree.add(auxiliarPointsList3);
				
				for (int i = 1; i <= maxChildren; i++) {
					childrenPointsList = new ArrayList<Point>();
					for (int j = 0; j < auxiliarPointsList.size(); j++) {
						childrenPointsList.add(auxiliarPointsList.get(j));
					}
					randomOption = Math.random();
					if (randomOption <= minimumP + (1 - minimumP)
							* Math.exp(-timeRetarder * time)) {
						randomPositionA = (int) (size * Math.random());
						randomPositionB = (int) (size * Math.random());
						while (randomPositionB == randomPositionA) {
							randomPositionB = (int) (size * Math.random());
						}
						pointA = auxiliarPointsList.get(randomPositionA);
						pointB = auxiliarPointsList.get(randomPositionB);
						childrenPointsList.set(randomPositionA, pointB);
						childrenPointsList.set(randomPositionB, pointA);
					} else {
						if (randomOption <= minimumPP + (1 - minimumPP)
								*0.5*Math.exp(-timeRetarder * time)) {
							randomPositionA = (int) (size * Math.random());
							randomPositionB = (int) (size * Math.random());
							while (randomPositionB == randomPositionA) {
								randomPositionB = (int) (size * Math.random());
							}
							randomPositionC = (int) (size * Math.random());
							while ((randomPositionC == randomPositionA)
									|| (randomPositionC == randomPositionB)) {
								randomPositionC = (int) (size * Math.random());
							}
							pointA = auxiliarPointsList.get(randomPositionA);
							pointB = auxiliarPointsList.get(randomPositionB);
							pointC = auxiliarPointsList.get(randomPositionC);
							childrenPointsList.set(randomPositionA, pointB);
							childrenPointsList.set(randomPositionB, pointC);
							childrenPointsList.set(randomPositionC, pointA);
						} else {
							if (randomOption <= minimumPPP + (1 - minimumPPP)
									* 0.25*Math.exp(-timeRetarder * time)) {
								randomPositionA = (int) (size * Math.random());
								randomPositionB = (int) (size * Math.random());
								while (randomPositionB == randomPositionA) {
									randomPositionB = (int) (size * Math
											.random());
								}
								randomPositionC = (int) (size * Math.random());
								while ((randomPositionC == randomPositionA)
										|| (randomPositionC == randomPositionB)) {
									randomPositionC = (int) (size * Math
											.random());
								}
								randomPositionD = (int) (size * Math.random());
								while ((randomPositionD == randomPositionA)
										|| (randomPositionD == randomPositionB)
										|| (randomPositionD == randomPositionC)) {
									randomPositionD = (int) (size * Math
											.random());
								}
								pointA = auxiliarPointsList
										.get(randomPositionA);
								pointB = auxiliarPointsList
										.get(randomPositionB);
								pointC = auxiliarPointsList
										.get(randomPositionC);
								pointD = auxiliarPointsList
										.get(randomPositionD);
								childrenPointsList.set(randomPositionA, pointB);
								childrenPointsList.set(randomPositionB, pointC);
								childrenPointsList.set(randomPositionC, pointD);
								childrenPointsList.set(randomPositionD, pointA);
							} else {
								// EVITAR CRUCES
								randomPositionA = (int) ((size - 2) * Math
										.random());
								randomPositionB = 1 + (int) ((size - 1) * Math
										.random());
								securityCounter = 0;
								while ((randomPositionB <= randomPositionA)
										&& (securityCounter <= 10)) {
									randomPositionB = 1 + (int) ((size - 1) * Math
											.random());
									securityCounter = securityCounter + 1;
								}
								if (securityCounter < 11) {
									if ((randomPositionB - randomPositionA) % 2 != 0) {
										for (int j = 1; j <= ((1 + randomPositionB - randomPositionA) / 2); j++) {
											pointA = auxiliarPointsList
													.get(randomPositionA + j
															- 1);
											pointB = auxiliarPointsList
													.get(randomPositionB - j
															+ 1);
											childrenPointsList.set(
													randomPositionA + j - 1,
													pointB);
											childrenPointsList.set(
													randomPositionB - j + 1,
													pointA);
										}
									} else {
										for (int j = 1; j <= ((randomPositionB - randomPositionA) / 2); j++) {
											pointA = auxiliarPointsList
													.get(randomPositionA + j
															- 1);
											pointB = auxiliarPointsList
													.get(randomPositionB - j
															+ 1);
											childrenPointsList.set(
													randomPositionA + j - 1,
													pointB);
											childrenPointsList.set(
													randomPositionB - j + 1,
													pointA);
										}
									}
								}
							}
						}
					}
					populationTree.add(childrenPointsList);
				}
				// CHILDREN OF SECOND PARENT
				for (int i = 1; i <= maxChildren; i++) {
					childrenPointsList = new ArrayList<Point>();
					for (int j = 0; j < auxiliarPointsList2.size(); j++) {
						childrenPointsList.add(auxiliarPointsList2.get(j));
					}
					randomOption = Math.random();
					if (randomOption <= minimumP + (1 - minimumP)
							* Math.exp(-timeRetarder * time)) {
						randomPositionA = (int) (size * Math.random());
						randomPositionB = (int) (size * Math.random());
						while (randomPositionB == randomPositionA) {
							randomPositionB = (int) (size * Math.random());
						}
						pointA = auxiliarPointsList2.get(randomPositionA);
						pointB = auxiliarPointsList2.get(randomPositionB);
						childrenPointsList.set(randomPositionA, pointB);
						childrenPointsList.set(randomPositionB, pointA);
					} else {
						if (randomOption <= minimumPP + (1 - minimumPP) * 0.5
								* (1 + Math.exp(-timeRetarder * time))) {
							randomPositionA = (int) (size * Math.random());
							randomPositionB = (int) (size * Math.random());
							while (randomPositionB == randomPositionA) {
								randomPositionB = (int) (size * Math.random());
							}
							randomPositionC = (int) (size * Math.random());
							while ((randomPositionC == randomPositionA)
									|| (randomPositionC == randomPositionB)) {
								randomPositionC = (int) (size * Math.random());
							}
							pointA = auxiliarPointsList2.get(randomPositionA);
							pointB = auxiliarPointsList2.get(randomPositionB);
							pointC = auxiliarPointsList2.get(randomPositionC);
							childrenPointsList.set(randomPositionA, pointB);
							childrenPointsList.set(randomPositionB, pointC);
							childrenPointsList.set(randomPositionC, pointA);
						} else {
							if (randomOption <= minimumPPP + (1 - minimumPPP)
									* Math.exp(-timeRetarder * time)) {
								randomPositionA = (int) (size * Math.random());
								randomPositionB = (int) (size * Math.random());
								while (randomPositionB == randomPositionA) {
									randomPositionB = (int) (size * Math
											.random());
								}
								randomPositionC = (int) (size * Math.random());
								while ((randomPositionC == randomPositionA)
										|| (randomPositionC == randomPositionB)) {
									randomPositionC = (int) (size * Math
											.random());
								}
								randomPositionD = (int) (size * Math.random());
								while ((randomPositionD == randomPositionA)
										|| (randomPositionD == randomPositionB)
										|| (randomPositionD == randomPositionC)) {
									randomPositionD = (int) (size * Math
											.random());
								}
								pointA = auxiliarPointsList2
										.get(randomPositionA);
								pointB = auxiliarPointsList2
										.get(randomPositionB);
								pointC = auxiliarPointsList2
										.get(randomPositionC);
								pointD = auxiliarPointsList2
										.get(randomPositionD);
								childrenPointsList.set(randomPositionA, pointB);
								childrenPointsList.set(randomPositionB, pointC);
								childrenPointsList.set(randomPositionC, pointD);
								childrenPointsList.set(randomPositionD, pointA);
							} else {
								// EVITAR CRUCES
								randomPositionA = (int) ((size - 2) * Math
										.random());
								randomPositionB = 1 + (int) ((size - 1) * Math
										.random());
								securityCounter = 0;
								while ((randomPositionB <= randomPositionA)
										&& (securityCounter <= 10)) {
									randomPositionB = 1 + (int) ((size - 1) * Math
											.random());
									securityCounter = securityCounter + 1;
								}
								if (securityCounter < 11) {
									if ((randomPositionB - randomPositionA) % 2 != 0) {
										for (int j = 1; j <= ((1 + randomPositionB - randomPositionA) / 2); j++) {
											pointA = auxiliarPointsList2
													.get(randomPositionA + j
															- 1);
											pointB = auxiliarPointsList2
													.get(randomPositionB - j
															+ 1);
											childrenPointsList.set(
													randomPositionA + j - 1,
													pointB);
											childrenPointsList.set(
													randomPositionB - j + 1,
													pointA);
										}
									} else {
										for (int j = 1; j <= ((randomPositionB - randomPositionA) / 2); j++) {
											pointA = auxiliarPointsList2
													.get(randomPositionA + j
															- 1);
											pointB = auxiliarPointsList2
													.get(randomPositionB - j
															+ 1);
											childrenPointsList.set(
													randomPositionA + j - 1,
													pointB);
											childrenPointsList.set(
													randomPositionB - j + 1,
													pointA);
										}
									}
								}
							}
						}
					}
					populationTree.add(childrenPointsList);
				}
				// CHILDREN OF THIRD PARENT
				for (int i = 1; i <= maxChildren; i++) {
					childrenPointsList = new ArrayList<Point>();
					for (int j = 0; j < auxiliarPointsList3.size(); j++) {
						childrenPointsList.add(auxiliarPointsList3.get(j));
					}
					randomOption = Math.random();
					if (randomOption <= minimumP + (1 - minimumP)
							* Math.exp(-timeRetarder * time)) {
						randomPositionA = (int) (size * Math.random());
						randomPositionB = (int) (size * Math.random());
						while (randomPositionB == randomPositionA) {
							randomPositionB = (int) (size * Math.random());
						}
						pointA = auxiliarPointsList3.get(randomPositionA);
						pointB = auxiliarPointsList3.get(randomPositionB);
						childrenPointsList.set(randomPositionA, pointB);
						childrenPointsList.set(randomPositionB, pointA);
					} else {
						if (randomOption <= minimumPP + (1 - minimumPP) * 0.5
								* (1 + Math.exp(-timeRetarder * time))) {
							randomPositionA = (int) (size * Math.random());
							randomPositionB = (int) (size * Math.random());
							while (randomPositionB == randomPositionA) {
								randomPositionB = (int) (size * Math.random());
							}
							randomPositionC = (int) (size * Math.random());
							while ((randomPositionC == randomPositionA)
									|| (randomPositionC == randomPositionB)) {
								randomPositionC = (int) (size * Math.random());
							}
							pointA = auxiliarPointsList3.get(randomPositionA);
							pointB = auxiliarPointsList3.get(randomPositionB);
							pointC = auxiliarPointsList3.get(randomPositionC);
							childrenPointsList.set(randomPositionA, pointB);
							childrenPointsList.set(randomPositionB, pointC);
							childrenPointsList.set(randomPositionC, pointA);
						} else {
							if (randomOption <= minimumPPP + (1 - minimumPPP)
									* Math.exp(-timeRetarder * time)) {
								randomPositionA = (int) (size * Math.random());
								randomPositionB = (int) (size * Math.random());
								while (randomPositionB == randomPositionA) {
									randomPositionB = (int) (size * Math
											.random());
								}
								randomPositionC = (int) (size * Math.random());
								while ((randomPositionC == randomPositionA)
										|| (randomPositionC == randomPositionB)) {
									randomPositionC = (int) (size * Math
											.random());
								}
								randomPositionD = (int) (size * Math.random());
								while ((randomPositionD == randomPositionA)
										|| (randomPositionD == randomPositionB)
										|| (randomPositionD == randomPositionC)) {
									randomPositionD = (int) (size * Math
											.random());
								}
								pointA = auxiliarPointsList3
										.get(randomPositionA);
								pointB = auxiliarPointsList3
										.get(randomPositionB);
								pointC = auxiliarPointsList3
										.get(randomPositionC);
								pointD = auxiliarPointsList3
										.get(randomPositionD);
								childrenPointsList.set(randomPositionA, pointB);
								childrenPointsList.set(randomPositionB, pointC);
								childrenPointsList.set(randomPositionC, pointD);
								childrenPointsList.set(randomPositionD, pointA);
							} else {
								// EVITAR CRUCES
								randomPositionA = (int) ((size - 2) * Math
										.random());
								randomPositionB = 1 + (int) ((size - 1) * Math
										.random());
								securityCounter = 0;
								while ((randomPositionB <= randomPositionA)
										&& (securityCounter <= 10)) {
									randomPositionB = 1 + (int) ((size - 1) * Math
											.random());
									securityCounter = securityCounter + 1;
								}
								if (securityCounter < 11) {
									if ((randomPositionB - randomPositionA) % 2 != 0) {
										for (int j = 1; j <= ((1 + randomPositionB - randomPositionA) / 2); j++) {
											pointA = auxiliarPointsList3
													.get(randomPositionA + j
															- 1);
											pointB = auxiliarPointsList3
													.get(randomPositionB - j
															+ 1);
											childrenPointsList.set(
													randomPositionA + j - 1,
													pointB);
											childrenPointsList.set(
													randomPositionB - j + 1,
													pointA);
										}
									} else {
										for (int j = 1; j <= ((randomPositionB - randomPositionA) / 2); j++) {
											pointA = auxiliarPointsList3
													.get(randomPositionA + j
															- 1);
											pointB = auxiliarPointsList3
													.get(randomPositionB - j
															+ 1);
											childrenPointsList.set(
													randomPositionA + j - 1,
													pointB);
											childrenPointsList.set(
													randomPositionB - j + 1,
													pointA);
										}
									}
								}
							}
						}
					}
					populationTree.add(childrenPointsList);
				}

				chosenPopulation = new ArrayList<ArrayList<Point>>();
				// SET PARENTS AS BEST CHOICES (IN GROWING ORDER)

				currentDistance = Distance(populationTree.get(0));
				currentDistance2 = Distance(populationTree.get(1));
				currentDistance3 = Distance(populationTree.get(2));
				chosenPopulation.add(populationTree.get(0));
				chosenPopulation.add(populationTree.get(1));
				chosenPopulation.add(populationTree.get(2));

				// COMPARE CHILDREN FROM PARENT 1
				for (int i = 1; i <= maxChildren; i++) {
					childrenPointsList = new ArrayList<Point>();
					for (int j = 0; j < populationTree.get(i).size(); j++) {
						childrenPointsList.add(populationTree.get(i).get(j));
					}
					trialDistance = Distance(childrenPointsList);
					if (trialDistance <= currentDistance) {
						chosenPopulation.set(0, childrenPointsList);
						currentDistance = Distance(childrenPointsList);
					}
				}
				// COMPARE CHILDREN FROM PARENT 2
				for (int i = 1 + maxChildren; i <= 2 * maxChildren; i++) {
					childrenPointsList = new ArrayList<Point>();
					for (int j = 0; j < populationTree.get(i).size(); j++) {
						childrenPointsList.add(populationTree.get(i).get(j));
					}
					trialDistance = Distance(childrenPointsList);
					if (trialDistance <= currentDistance2) {
						chosenPopulation.set(1, childrenPointsList);
						currentDistance2 = Distance(childrenPointsList);
					}
				}
				// COMPARE CHILDREN FROM PARENT 3
				for (int i = 1 + 2 * maxChildren; i <= 3 * maxChildren; i++) {
					childrenPointsList = new ArrayList<Point>();
					for (int j = 0; j < populationTree.get(i).size(); j++) {
						childrenPointsList.add(populationTree.get(i).get(j));
					}
					trialDistance = Distance(childrenPointsList);
					if (trialDistance <= currentDistance3) {
						chosenPopulation.set(2, childrenPointsList);
						currentDistance3 = Distance(childrenPointsList);
					}
				}
				currentDistance = Distance(chosenPopulation.get(0));
				currentDistance2 = Distance(chosenPopulation.get(1));
				currentDistance3 = Distance(chosenPopulation.get(2));
				if (currentDistance <= currentDistance2) {
					if (currentDistance <= currentDistance3) {
						if (currentDistance2 <= currentDistance3) {
							pointsList = chosenPopulation.get(0);
							pointsList2 = chosenPopulation.get(1);
							pointsList3 = chosenPopulation.get(2);
						} else {
							pointsList = chosenPopulation.get(0);
							pointsList2 = chosenPopulation.get(2);
							pointsList3 = chosenPopulation.get(1);
						}
					} else {
						pointsList = chosenPopulation.get(1);
						pointsList2 = chosenPopulation.get(2);
						pointsList3 = chosenPopulation.get(0);
					}
				} else {
					if (currentDistance <= currentDistance3) {
						pointsList = chosenPopulation.get(1);
						pointsList2 = chosenPopulation.get(0);
						pointsList3 = chosenPopulation.get(2);
					} else {
						if (currentDistance2 <= currentDistance3) {
							pointsList = chosenPopulation.get(2);
							pointsList2 = chosenPopulation.get(0);
							pointsList3 = chosenPopulation.get(1);
						} else {
							pointsList = chosenPopulation.get(2);
							pointsList2 = chosenPopulation.get(1);
							pointsList3 = chosenPopulation.get(0);
						}
					}
				}
				populationTree = new ArrayList<ArrayList<Point>>();
				populationTree.add(pointsList);
				populationTree.add(pointsList2);
				populationTree.add(pointsList3);
				drawingPanel.setLinks(pointsList, pointsList2, pointsList3);
				drawingPanel.drawLinks(true);
			}catch(Exception ex){}
			} else {
				timeO = Calendar.getInstance().getTimeInMillis() * 0.001;
			}
		}
	}

	public double Distance(ArrayList<Point> a) {
		distance = 0;
		distance = distance
				+ Math.sqrt(Math.pow(startPoint.getX() - a.get(0).getX(), 2)
						+ Math.pow(startPoint.getY() - a.get(0).getY(), 2));
		for (int i = 1; i < a.size(); i++) {
			distance = distance
					+ Math.sqrt(Math.pow(a.get(i - 1).getX() - a.get(i).getX(),
							2)
							+ Math
									.pow(a.get(i - 1).getY() - a.get(i).getY(),
											2));
		}
		distance = distance
				+ Math.sqrt(Math.pow(goalPoint.getX()
						- a.get(a.size() - 1).getX(), 2)
						+ Math.pow(goalPoint.getY()
								- a.get(a.size() - 1).getY(), 2));
		return (distance);
	}
}
