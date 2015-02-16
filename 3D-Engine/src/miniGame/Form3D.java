package miniGame;


import java.util.ArrayList;

public class Form3D {
	private int x;
	private int y; 
	private int z;
	private ArrayList<Point3D> pointList3D = new ArrayList<Point3D>();
	public Form3D(int x, int y, int z, Point3D[] pointList){
		this.x = x;
		this.y = y;
		this.z = z;
		
		for(int i = 0; i < pointList.length; i++){
			this.pointList3D.add(pointList[i]);
		}
	}
	public Form3D(int x, int y, int z){
		this(x,y,z,new Point3D[] {new Point3D(0,0,0)});
	}
	public Form3D(){
		this(0,0,0,new Point3D[] {new Point3D(0,0,0)});
	}
	public Form3D(Point3D[] pointList){
		this(0,0,0,pointList);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		for (int i = 0; i < pointList3D.size(); i++){
			pointList3D.get(i).changeX(x- this.x) ;
		}
		this.x = x;
		
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		for (int i = 0; i < pointList3D.size(); i++){
			pointList3D.get(i).changeY(y - this.y) ;
		}
		this.y = y;	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		for (int i = 0; i < pointList3D.size(); i++){
			pointList3D.get(i).changeZ(z - this.z) ;
		}
		this.z = z;
	}
	public ArrayList<Point3D> getPointList3D() {
		return pointList3D;
	}
	public void setPointList3D(ArrayList<Point3D> pointList3D) {
		this.pointList3D.clear();
		this.pointList3D = pointList3D;
	}
	public void setPointList3D(Point3D[] pointList) {
		this.pointList3D.clear();
		for(int i = 0; i < pointList.length; i++){
			this.pointList3D.add(pointList[i]);
		}
	}
	
	public void Translate(int x, int y, int z){
		TranslateX(x);
		TranslateY(y);
		TranslateZ(z);
	}
	public void TranslateX(int translation){
		x+= translation;
		for (int i = 0; i < pointList3D.size(); i++){
			pointList3D.get(i).changeX(translation) ;
		}
	}
	public void TranslateY(int translation){
		y+= translation;
		for (int i = 0; i < pointList3D.size(); i++){
			pointList3D.get(i).changeY(translation);
		}
	}
	public void TranslateZ(int translation){
		z+= translation;
		for (int i = 0; i < pointList3D.size(); i++){
			pointList3D.get(i).changeZ(translation);
		}
	}
	public void Rotate(double yaw, double pitch, double roll){
		yaw(yaw);
		//noPitch
		//noRoll
	}
	public void yaw(double angle){//very glitchy
		for (int i = 0; i < pointList3D.size(); i++){
			//Translation
			double distance = Math.sqrt(Math.pow((pointList3D.get(i).getX() - x), 2) + Math.pow((pointList3D.get(i).getY() - y), 2)) + .6;
			double anglePre = Math.atan2((pointList3D.get(i).getY() - y), (pointList3D.get(i).getX() - x));
			double angleFinal = (anglePre + angle);//%(Math.PI*2);
			double xT = x + distance*Math.cos(angleFinal);
			double yT = y + distance*Math.sin(angleFinal);
//			System.out.printf("d: %f\nanglePre: %f\nxT: %f\nyT: %f\n",d,anglePre,xT,yT);
			pointList3D.get(i).setPosition((int)xT, (int)yT);
		}
	}
	

}
