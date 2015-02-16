/*
 * Creator Jimmy Yue and Rama Gosula
 * Controls:
 * Forward = w
 * back = s
 * left = a
 * right = d
 * up = e
 * down = q
 * 
 * It glitches out when you rotate, but if you want:
 * clockwise = ->
 * counterclockwise = <-
 * 
 * Feel free to mess around with adding objects to the scene do this below
 * ctrl-f "#" to find where to do that
 * 
 * It will take to to
 * -here
 * -where you add objects
 * -where you can create new methods for adding objects
 * 
 */
package miniGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*; //NEW
import java.io.*;
import javax.sound.sampled.Line;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameDisplay extends JPanel implements KeyListener {
	private static boolean LEFTDOWN;
	private static boolean RIGHTDOWN;
	private static boolean BACKDOWN;
	private static boolean FORWARDDOWN;
	private static boolean UPDOWN;
	private static boolean DOWNDOWN;
	private static boolean CLOCKWISEDOWNX;
	private static boolean COUNTERCLOCKWISEDOWNX;
	private static boolean CLOCKWISEDOWNY;
	private static boolean COUNTERCLOCKWISEDOWNY;
	private static Looker look = new Looker(300, 600, 0);
	private static Looker look2 = new Looker(250, 600, 0);
	private static Camera3D camera = new Camera3D(look);
	private static Camera3D camera2 = new Camera3D(look2);
	private static int pointValue, isLine, j, k, l, j1, k1, l1; //NEW

	private Random rand = new Random();
	private ArrayList<Point> pointList = new ArrayList<Point>();
	private ArrayList<Point3D> pointList3D = new ArrayList<Point3D>();
	private ArrayList<Point> pointDispList = new ArrayList<Point>();
	private ArrayList<Line3D> lineList3D = new ArrayList<Line3D>();
	private ArrayList<Line3D> axes = new ArrayList<Line3D>();
	private Form3D form = new Form3D();
	private FormLine3D formLine = new FormLine3D();

	public GameDisplay() {// Initialization of panel
		super();    
		LEFTDOWN = false;
		RIGHTDOWN = false;
		BACKDOWN = false;
		FORWARDDOWN = false;
		UPDOWN = false;
		DOWNDOWN = false;
		CLOCKWISEDOWNX = false;
		CLOCKWISEDOWNY = false;
		COUNTERCLOCKWISEDOWNX = false;
		COUNTERCLOCKWISEDOWNY = false;

		setBackground(Color.WHITE);

		/*
		 * Here is where you insert the shapes#
		 */


//		addSquare3D(100,400,0);
//		addBuilding(400, 100, 0, 5);
//		addRandomBox(0, 0, 0, 10,50);
//		addRandomBox(0, 0, 0, 5,10);
//		addRandomBox(-1000, 0, 0, 1q0,50);


		//pointList3D.addAll(box(-700, 0, 0, 10,50));
//		pointList3D.addAll(sphere(500, 0, 0, 50, 100));
//		pointList3D.addAll(randomBox(-700, 0, 0, 15,50));
//		lineList3D.addAll(lineSphere(500,0,0,50,100));
//		lineList3D.addAll(lineGrid(0, 0, 0, 50, 50, 10));
//		pointList3D.addAll(grid(0, 0, 0, 50, 50, 10));
		
		addAxes(0, 0, 0) ;
//		pointList3D.add(new Point3D());
		
		//form.setPointList3D(cone(0, 0, 0));
//		form.setPointList3D(torus(0, 0, 0, 500, 200, 100));
//		formLine.setLineList3D(lineBox(-700, 0, 0, 15,50));
		form.setPointList3D(building(0, 0, 0, 5, 5, 25, 10));
		pointList3D.addAll(building(0, 100, 0, 5, 5, 10, 10));
		pointList3D.addAll(cone(0, 0, 0));
		pointList3D.addAll(torus(100, -500, 300, 500, 200, 100));
		pointList3D.addAll(form.getPointList3D());
		pointList3D.addAll(lineDot(new Point3D (0, 0, 0), new Point3D (200, 200, 200), 10)); // In progress
		//pointList3D.addAll(lineDot(new Point3D (10, 10, 10), new Point3D (10, 10, 10), 5));
		
//		lineList3D.addAll(formLine.getLineList3D());
		try { //NEW, maybe go back to trying to get XML to work one day?
			//File file = new File ("/users/jimmy/desktop/object.txt"); //I'm not quite sure how to designate this to ANY windows desktop, I'll mess around
			//File file = new File (System.getProperty("user.home"), "Desktop"); //Gets the desktop destination but....
			File file = new File ("/Users/Jimmy/Desktop/object.txt");
			//reader = new BufferedReader (new FileReader (file));
			if (!file.exists()){ //Creates a file (but does not overwrite), helpful for checking if file location is correct
				file.createNewFile();
			}
			List<Integer> objectValues = new ArrayList<Integer>();    
			/**Scanner fileScanner = new Scanner(file); //efficient way that I stopped using
			while (fileScanner.hasNextInt()){
				integers.add(fileScanner.nextInt());
			}*/
			BufferedReader reader; //How did I do this again???
			reader = new BufferedReader (new FileReader (file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\s");
				for (String part : parts) {
					Integer value = Integer.valueOf(part);
					objectValues.add(value);
				}
			}
			reader.close();
			//addPoint (0,0,0);
			for(int placeCounter = 0; placeCounter < objectValues.size(); placeCounter++) {
		            //System.out.println(integers.get(i));
		            //for (int u = 0; u < 3; u++){
            	if (pointValue == 0){
            		//j = integers.get(i);
            		if (objectValues.get(placeCounter) == 0){
            			isLine = 0;
            		}
            		if (objectValues.get(placeCounter) == 1){
            			isLine = 1;
            		}
            		pointValue++;
            		//addPoint (0,0,0);
            	}
            	else if (pointValue == 1){
            		j = objectValues.get(placeCounter);
            		pointValue++;
            		//addPoint (0,0,1);
            	}
            	else if (pointValue == 2){
            		k = objectValues.get(placeCounter);
            		//addPoint(j, k, l);
            		pointValue++;
            	}
            	else if (pointValue == 3 && isLine == 0){
            		l = objectValues.get(placeCounter);
            		addPoint(j, k, l);
            		pointValue = 0;
            	}
            	else if (pointValue == 3 && isLine == 1){
            		l = objectValues.get(placeCounter);
            		pointValue++;
            	}
            	else if (pointValue == 4 && isLine == 1){
            		j1 = objectValues.get(placeCounter);
            		pointValue++;
            	}
            	else if (pointValue == 5 && isLine == 1){
            		k1 = objectValues.get(placeCounter);
            		pointValue++;
            	}
            	else if (pointValue == 6 && isLine == 1){
            		l1 = objectValues.get(placeCounter);
            		lineList3D.add(new Line3D(new Point3D(j,k,l),new Point3D(j1,k1,l1)));
            		pointValue = 0;
            	}
            }
			while ((line = reader.readLine()) != null) {
		        System.out.println(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
				
		}
	}

	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Camera camera = new Camera(look);
		look.update();
		form.TranslateX(5);
		if(form.getX() > 500){
			form.setX(0);
		}
//		form.pitch(.01);
//		form.yaw(.01);
//		form.roll(.01);
	//virus ping trojan worm.exe //script.bash terminal LINUX ubuntu honeypot UCP/TCP port6969 192.168.0.1 127.0.0.1 
		look2.setxDirection(look.getxDirection());
		look2.setyDirection(look.getyDirection());
		look2.setzDirection(look.getzDirection());
		look2.setxRotDirection(look.getxRotDirection());
		look2.setyRotDirection(look.getyRotDirection());
		look2.setCameraLocked(look.isCameraLocked());
		look2.update();

		//look.paintArrow(g);

		pointDispList.clear();

		//2D
//		for (int i = 0; i < pointList.size(); i++) {
//			pointList.get(i).display(g);
//			if (camera.pointVisibleX(pointList.get(i))){
//				pointDispList.add(new Point(camera.calcScreenX(pointList.get(i)),50));
//				
//			}
//		}	
//		flyMovements();
		//3D
		for (int i = 0; i < pointList3D.size(); i++) {
			//pointList3D.get(i).display(g);
			if (camera.pointVisibleX(pointList3D.get(i)) && camera.pointVisibleY(pointList3D.get(i))){
				pointDispList.add(new Point(camera.calcScreenX(pointList3D.get(i)),camera.calcScreenY(pointList3D.get(i))));
			}
		}	

		//second view
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, GameRunner.WIDTH, GameRunner.HEIGHT);
		g.fillRect(0, 0, GameRunner.WIDTH, 100);
		for (int i = 0; i < pointDispList.size(); i++){
			pointDispList.get(i).display(g);
		}

		Line3D.setCamera(camera);

		for (int i = 0; i < lineList3D.size(); i++){
			lineList3D.get(i).display(g,Color.blue);
		}
		drawAxes(g); 

//		drawAxes(300, 300, 20, camera, g);//This draws the coordinate axes


		/*
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(GameRunner.WIDTH, 0, GameRunner.WIDTH, GameRunner.HEIGHT);
		g.fillRect(GameRunner.WIDTH, 0, GameRunner.WIDTH, 100);

		pointDispList.clear();

		for (int i = 0; i < pointList3D.size(); i++) {
			//pointList3D.get(i).display(g);
			if(i != 0){
				if (camera2.pointVisibleX(pointList3D.get(i)) && camera2.pointVisibleY(pointList3D.get(i))){
					pointDispList.add(new Point(camera2.calcScreenX(pointList3D.get(i))+ GameRunner.WIDTH,camera2.calcScreenY(pointList3D.get(i))));
				}
			}else{
				if (camera2.pointVisibleX(pointList3D.get(i)) && camera2.pointVisibleY(pointList3D.get(i))){
					pointDispList.add(new Point(camera2.calcScreenX(pointList3D.get(i))+ GameRunner.WIDTH,camera2.calcScreenY(pointList3D.get(i))));
				}
			}
		}	
		for (int i = 0; i < pointDispList.size(); i++){
			pointDispList.get(i).display(g);
		}

		Line3D.setCamera(camera2);

		for (int i = 0; i < lineList3D.size(); i++){
			lineList3D.get(i).display(g,Color.blue,GameRunner.WIDTH);
		}
		drawAxes(g,GameRunner.WIDTH); 



//		drawLines(g);
 * 
 */

	} // end method paintComponent


	//#
	public void addPoint (int x, int y, int z){ //NEW
		pointList3D.add(new Point3D(x, y, z));
	}
	public ArrayList<Point3D> grid(int x, int y, int z, int length, int width, int spacing){
		ArrayList<Point3D> pointList = new ArrayList<Point3D>();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				pointList.add(new Point3D(x + i * (GameRunner.WIDTH / spacing), y + j* (GameRunner.HEIGHT / spacing),z));
			}
		}
		return pointList;
	}
	public ArrayList<Point3D> box(int x, int y, int z, int size, int spacing){
		ArrayList<Point3D> pointList = new ArrayList<Point3D>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++){
					pointList.add(new Point3D(x + i*spacing , y + j*spacing,z + k*spacing));
				}
			}
		}
		return pointList;
	}
	public ArrayList<Line3D> lineBox(int x, int y, int z, int size, int spacing){
		ArrayList<Line3D> lineList = new ArrayList<Line3D>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++){
					Point3D point = new Point3D(x + i*spacing , y + j*spacing,z + k*spacing);
					lineList.add(new Line3D(point,point));

				}
			}
		}
		return lineList;
	}
	public ArrayList<Point3D> randomBox(int x, int y, int z, int size, int spacing){
		ArrayList<Point3D> pointList = new ArrayList<Point3D>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++){
					pointList.add(new Point3D(x + i*spacing + rand.nextInt(spacing/2), y + j*spacing +rand.nextInt(spacing/2),z + k*spacing + rand.nextInt(spacing/2)));
				}
			}
		}
		return pointList;
	}
	public ArrayList<Point3D> sphere(int x, int y, int z, int radius, int approximation){
		ArrayList<Point3D> pointList = new ArrayList<Point3D>();
		for (double i = 0; i < Math.PI; i += approximation*.001 ){
			for (double j = 0; j < 2*Math.PI; j += approximation*.001){
				int xNew = (int) (x + radius*Math.sin(i)*Math.sin(j));
				int yNew = (int) (y + radius*Math.sin(i)*Math.cos(j));
				int zNew = (int) (z + radius*Math.cos(i));
				pointList.add(new Point3D(xNew,yNew,zNew));
			}
		}
		return pointList;
	}
	public ArrayList<Point3D> spiral(int x, int y, int z, double density){
		ArrayList<Point3D> pointList = new ArrayList<Point3D>();
		for(double t = 0; t < 100; t += density){
			int xNew = (int) (x + 100*Math.cos(t));
			int yNew = (int) (y + 100*Math.sin(t));
			int zNew = (int) (z + 10*t);
			pointList.add(new Point3D(xNew,yNew,zNew));
		}
		return pointList;
	}
	public ArrayList<Point3D> spiral(int x, int y, int z){
		return spiral(x, y, z, .2);
	}
	
	public ArrayList<Point3D> cone(int x, int y, int z){
		ArrayList<Point3D> pointList = new ArrayList<Point3D>();
		for(double t = -100; t < 100; t += .2){
			int xNew = (int) (x + t*Math.cos(t));
			int yNew = (int) (y + t*Math.sin(t));
			int zNew = (int) (z + t);
			pointList.add(new Point3D(xNew,yNew,zNew));
		}
		return pointList;
	}
	public ArrayList<Point3D> torus(int x, int y, int z, int radius, int width, int approximation){
		ArrayList<Point3D> pointList = new ArrayList<Point3D>();
		for (double i = 0; i < Math.PI; i += approximation*.001 ){
			for (double j = 0; j < 2*Math.PI; j += approximation*.001){
				int xNew = (int) (x + (radius + width*Math.cos(j))*Math.cos(i));
				int yNew = (int) (y + (radius + width*Math.cos(j))*Math.sin(i));
				int yNew2 = (int) (y - (radius + width*Math.cos(j))*Math.sin(i));
				int zNew = (int) (z + width*Math.sin(j));
				pointList.add(new Point3D(xNew,yNew,zNew));
				pointList.add(new Point3D(xNew,yNew2,zNew));
			}
		}
		return pointList;
	}
	public ArrayList<Point3D> lineDot(Point3D startPoint, Point3D endPoint, int spacing){
		ArrayList<Point3D> pointList = new ArrayList<Point3D>();
		double length = Math.sqrt(Math.pow(endPoint.getX() -startPoint.getX(), 2) +Math.pow(endPoint.getY() -startPoint.getY(), 2) +Math.pow(endPoint.getZ() -startPoint.getZ(), 2) );
		for (int i = 0; i <= length; i += spacing) {
			/*
			int xNew = (int) (startPoint.getX() + i);
			int yNew = (int) (startPoint.getY() + i);
			int zNew = (int) (startPoint.getZ() + i);
			*/		
			int xNew = (int) (startPoint.getX() + (i/length) * (endPoint.getX() - startPoint.getX()));
			int yNew = (int) (startPoint.getY() + (i/length) * (endPoint.getY() - startPoint.getY()));
			int zNew = (int) (startPoint.getZ() + (i/length) * (endPoint.getZ() - startPoint.getZ()));
			
			pointList.add(new Point3D(xNew,yNew,zNew));
		}
		return pointList;
	}
		
	
	public ArrayList<Line3D> lineGrid(int x, int y, int z, int length, int width, int spacing){
		ArrayList<Line3D> lineList = new ArrayList<Line3D>();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				Point3D startPoint = new Point3D(x + i * (GameRunner.WIDTH / spacing), y + j* (GameRunner.HEIGHT / spacing),z);
				Point3D endPoint = new Point3D(x + i * (GameRunner.WIDTH / spacing), y + j* (GameRunner.HEIGHT / spacing),z + 0);
				lineList.add(new Line3D(startPoint,endPoint));
			}
		}
		return lineList;
	}
	public ArrayList<Line3D> lineGridVertical1(int x, int y, int z, int length, int width, int spacing){
		ArrayList<Line3D> lineList = new ArrayList<Line3D>();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				Point3D startPoint = new Point3D(x, y + j* (GameRunner.HEIGHT / spacing),z + i*(GameRunner.WIDTH / spacing));
				lineList.add(new Line3D(startPoint,startPoint));
			}
		}
		return lineList;
	}
	public ArrayList<Line3D> lineGridRandom(int x, int y, int z, int length, int width, int spacing){
		ArrayList<Line3D> lineList = new ArrayList<Line3D>();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				Point3D startPoint = new Point3D(x + i * (GameRunner.WIDTH / spacing), y + j* (GameRunner.HEIGHT / spacing),z);
				Point3D endPoint = new Point3D(x + i * (GameRunner.WIDTH / spacing), y + j* (GameRunner.HEIGHT / spacing),(int)(z + 100*rand.nextDouble()));
				lineList.add(new Line3D(startPoint,endPoint));
			}
		}
		return lineList;
	}
	public ArrayList<Line3D> lineCone(int x, int y, int z){
		ArrayList<Line3D> lineList = new ArrayList<Line3D>();
		double increment = .2;
		for(double t = -100; t < 100 - increment; t += increment){
			int xNew = (int) (x + t*Math.cos(t));
			int yNew = (int) (y + t*Math.sin(t));
			int zNew = (int) (z + t);
			
			Point3D startPoint = new Point3D(xNew,yNew,zNew);
			double t2 = t + increment;
			xNew = (int) (x + (t2)*Math.cos(t2));
			yNew = (int) (y + (t2)*Math.sin(t2));
			zNew = (int) (z + t2);
			
			Point3D endPoint = new Point3D(xNew,yNew,zNew);
			
			lineList.add(new Line3D(startPoint, endPoint));
		}
		return lineList;
	}
	public ArrayList<Line3D> lineSpiral(int x, int y, int z){
		ArrayList<Line3D> lineList = new ArrayList<Line3D>();
		double increment = .2;
		for(double t = 0; t < 100 - increment; t += increment){
			int xNew = (int) (x + 100*Math.cos(t));
			int yNew = (int) (y + 100*Math.sin(t));
			int zNew = (int) (z + 10*t);
			
			Point3D startPoint = new Point3D(xNew,yNew,zNew);
			
			double t2 = t + increment;
			xNew = (int) (x + 100*Math.cos(t2));
			yNew = (int) (y + 100*Math.sin(t2));
			zNew = (int) (z + 10*t2);
			Point3D endPoint = new Point3D(xNew,yNew,zNew);
			lineList.add(new Line3D(startPoint, endPoint));
		}
		return lineList;
	}
	public ArrayList<Line3D> lineSphere(int x, int y, int z, int radius, int approximation){
		ArrayList<Line3D> lineList = new ArrayList<Line3D>();
		double increment = approximation*.001;
		for (double i = 0; i < Math.PI; i += approximation*.001 ){
			for (double j = 0; j < 2*Math.PI ; j += increment){
				int xNew = (int) (x + radius*Math.sin(i)*Math.sin(j));
				int yNew = (int) (y + radius*Math.sin(i)*Math.cos(j));
				int zNew = (int) (z + radius*Math.cos(i));
				
				Point3D startPoint = new Point3D(xNew,yNew,zNew);
				double t = j + increment;
				
				xNew = (int) (x + radius*Math.sin(i)*Math.sin(t));
				yNew = (int) (y + radius*Math.sin(i)*Math.cos(t));
				zNew = (int) (z + radius*Math.cos(i));
				Point3D endPoint = new Point3D(xNew,yNew,zNew);

				lineList.add(new Line3D(startPoint, endPoint));
			}
		}
		return lineList;
	}
	public void drawAxes(int x, int y, int z, Camera3D camera, Graphics g){
		Point3D center = new Point3D(x, y, z);
		Point3D line2 = new Point3D(x,y,z+50);
		g.setColor(Color.BLUE);
		g.drawLine(camera.calcScreenX(center),camera.calcScreenY(center),camera.calcScreenX(line2),camera.calcScreenY(line2));
		g.setColor(Color.RED);
		line2.setPosition(x+50, y, z);
		g.drawLine(camera.calcScreenX(center),camera.calcScreenY(center),camera.calcScreenX(line2),camera.calcScreenY(line2));
		g.setColor(Color.GREEN);
		line2.setPosition(x, y+50, z);
		g.drawLine(camera.calcScreenX(center),camera.calcScreenY(center),camera.calcScreenX(line2),camera.calcScreenY(line2));

	}
	public void drawAxes(Graphics g){
		drawAxes(g,0);
	}
	public void drawAxes(Graphics g,int offset){
		axes.get(0).display(g, Color.BLUE,offset);
		axes.get(1).display(g,Color.RED,offset);
		axes.get(2).display(g,Color.GREEN,offset);
	}
	public void addAxes(int x, int y, int z){
		Point3D center = new Point3D(x, y, z);
		axes.add(new Line3D(center, new Point3D(x,y,z+50)));
		axes.add(new Line3D(center, new Point3D(x+ 50,y,z)));
		axes.add(new Line3D(center, new Point3D(x,y+50,z)));
	}
	public ArrayList<Point3D> building(int x, int y, int z,int width,int length,int height,int spacing){
		ArrayList<Point3D> pointList = new ArrayList<Point3D>();
		for (int i = 0; i < width; i++){
			for (int j = 0; j < length; j++){
				for (int k = 0; k < height; k++){
					int xNew = x + i*spacing;
					int yNew = y + j*spacing;
					int zNew = z + k*spacing;
					pointList.add(new Point3D(xNew,yNew,zNew));
				}
			}
		}
		return pointList;
	}
	
	public void drawPlane(int x, int y, int z,int size, Camera3D camera, Graphics g, Color c){
		Point3D leftTop = new Point3D(x,y,z);
		Point3D leftBottom = new Point3D(x,y + size,z);
		Point3D rightTop = new Point3D(x+ size,y,z);
		Point3D rightBottom = new Point3D(x+size,y+size,z);
		Point3D points[] = {leftTop,leftBottom,rightBottom,rightTop};
		int xPoints[] = new int[4];
		int yPoints[] = new int[4];
		for (int i = 0; i < 4; i++){
			xPoints[i] = camera.calcScreenX(points[i]);
			yPoints[i] = camera.calcScreenY(points[i]);
		}
		g.setColor(c);
		g.fillPolygon(xPoints, yPoints, 4);
		
	}

	public void drawVPlane(int x, int y, int z,int size, Camera3D camera, Graphics g, Color c){
		Point3D leftTop = new Point3D(x,y,z);
		Point3D leftBottom = new Point3D(x,y + size,z);
		Point3D rightTop = new Point3D(x,y,z +size);
		Point3D rightBottom = new Point3D(x,y+size,z+size);
		Point3D points[] = {leftTop,leftBottom,rightBottom,rightTop};
		int xPoints[] = new int[4];
		int yPoints[] = new int[4];
		for (int i = 0; i < 4; i++){
			xPoints[i] = camera.calcScreenX(points[i]);
			yPoints[i] = camera.calcScreenY(points[i]);
		}
		g.setColor(c);
		g.fillPolygon(xPoints, yPoints, 4);
		
	}
	public void flyMovements(){
		for (int i = 0; i < pointList3D.size(); i++) {
			pointList3D.get(i).changeX(rand.nextInt(5)-2);
			pointList3D.get(i).changeY(rand.nextInt(5)-2);
			pointList3D.get(i).changeZ(rand.nextInt(5)-2);
		}	
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			look.setCameraLocked(!look.isCameraLocked());
			break;
		case KeyEvent.VK_W:
			//forward
			FORWARDDOWN = true;
			if (BACKDOWN){
				look.setyDirection(Looker.STOP);
			}else{
				look.setyDirection(Looker.FORWARD);
			}
			//System.out.println("W - forward");
			break;

		case KeyEvent.VK_S:
			//back
			BACKDOWN = true;
			if (FORWARDDOWN){
				look.setyDirection(Looker.STOP);
			}else{
				look.setyDirection(Looker.BACK);
			}
			//System.out.println("S - back");
			break;
		case KeyEvent.VK_A:
			//Left
			LEFTDOWN = true;
			if (RIGHTDOWN){
				look.setxDirection(Looker.STOP);
			}else{
				look.setxDirection(Looker.LEFT);
			}
			//System.out.println("A - left");
			break;
		case KeyEvent.VK_D:
			RIGHTDOWN = true;
			if (LEFTDOWN){
				look.setxDirection(Looker.STOP);
			}else{
				look.setxDirection(Looker.RIGHT);
			}
			//System.out.println("D - right");
			break;
		case KeyEvent.VK_E:
			UPDOWN = true;
			if (DOWNDOWN){
				look.setzDirection(Looker.STOP);
			}else{
				look.setzDirection(Looker.UP);
			}
			//System.out.println("E - up");
			break;
		case KeyEvent.VK_Q:
			DOWNDOWN = true;
			if (UPDOWN){
				look.setzDirection(Looker.STOP);
			}else{
				look.setzDirection(Looker.DOWN);
			}
			//System.out.println("Q - down");
			break;
//		case KeyEvent.VK_UP:
//			COUNTERCLOCKWISEDOWNY = true;
//			if (CLOCKWISEDOWNY){
//				look.setyRotDirection(Looker.STOP);
//			}else{
//				look.setyRotDirection(Looker.CLOCKWISE);
//			}
//			//System.out.println("UP - pitch forward");
//			break;
//		case KeyEvent.VK_DOWN:
//			CLOCKWISEDOWNY = true;
//			if (COUNTERCLOCKWISEDOWNY){
//				look.setyRotDirection(Looker.STOP);
//			}else{
//				look.setyRotDirection(Looker.CCLOCKWISE);
//			}
//			//System.out.println("DOWN - pitch back");
//			break;
		case KeyEvent.VK_LEFT:
			COUNTERCLOCKWISEDOWNX = true;
			if (CLOCKWISEDOWNX){
				look.setxRotDirection(Looker.STOP);
			}else{
				look.setxRotDirection(Looker.CLOCKWISE);
			}
			//System.out.println("LEFT - counterclockwise");
			break;
		case KeyEvent.VK_RIGHT:
			CLOCKWISEDOWNX = true;
			if (COUNTERCLOCKWISEDOWNX){
				look.setxRotDirection(Looker.STOP);
			}else{
				look.setxRotDirection(Looker.CCLOCKWISE);
			}
			//System.out.println("RIGHT - clockwise");
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			FORWARDDOWN = false;
			if (BACKDOWN){
				look.setyDirection(Looker.BACK);
			}else{
				look.setyDirection(Looker.STOP);
			}
			//System.out.println("W - forward");
			break;
		case KeyEvent.VK_S:
			BACKDOWN = false;
			if (FORWARDDOWN){
				look.setyDirection(Looker.FORWARD);
			}else{
				look.setyDirection(Looker.STOP);
			}
			//System.out.println("S - back");
			break;
		case KeyEvent.VK_A:
			LEFTDOWN = false;
			if (RIGHTDOWN){
				look.setxDirection(Looker.RIGHT);
			}else{
				look.setxDirection(Looker.STOP);
			}
			//System.out.println("A - left");
			break;
		case KeyEvent.VK_D:
			RIGHTDOWN = false;
			if (LEFTDOWN){
				look.setxDirection(Looker.LEFT);
			}else{
				look.setxDirection(Looker.STOP);
			}
			//System.out.println("D - right");
			break;
		case KeyEvent.VK_E:
			UPDOWN = false;
			if (DOWNDOWN){
				look.setzDirection(Looker.DOWN);
			}else{
				look.setzDirection(Looker.STOP);
			}
			//System.out.println("E - up");
			break;
		case KeyEvent.VK_Q:
			DOWNDOWN = false;
			if (UPDOWN){
				look.setzDirection(Looker.UP);
			}else{
				look.setzDirection(Looker.STOP);
			}
			//System.out.println("Q - down");
			break;
//		case KeyEvent.VK_UP:
//			COUNTERCLOCKWISEDOWNY = false;
//			if (CLOCKWISEDOWNY){
//				look.setyRotDirection(Looker.CLOCKWISE);
//			}else{
//				look.setyRotDirection(Looker.STOP);
//			}
//			//System.out.println("UP - pitch forward");
//			break;
//		case KeyEvent.VK_DOWN:
//			CLOCKWISEDOWNY = false;
//			if (COUNTERCLOCKWISEDOWNY){
//				look.setyRotDirection(Looker.CCLOCKWISE);
//			}else{
//				look.setyRotDirection(Looker.STOP);
//			}
//			//System.out.println("DOWN - pitch back");
//			break;
		case KeyEvent.VK_LEFT:
			COUNTERCLOCKWISEDOWNX = false;
			if (CLOCKWISEDOWNX){
				look.setxRotDirection(Looker.CLOCKWISE);
			}else{
				look.setxRotDirection(Looker.STOP);
			}
			//System.out.println("LEFT - counterclockwise");
			break;
		case KeyEvent.VK_RIGHT:
			CLOCKWISEDOWNX = false;
			if (COUNTERCLOCKWISEDOWNX){
				look.setxRotDirection(Looker.CCLOCKWISE);
			}else{
				look.setxRotDirection(Looker.STOP);
			}
			//System.out.println("RIGHT - clockwise");
			break;
		}
	}

}