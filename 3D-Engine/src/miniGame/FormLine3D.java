package miniGame;

import java.util.ArrayList;

public class FormLine3D {
	private int x;
	private int y;
	private int z;
	private ArrayList<Line3D> lineList3D = new ArrayList<Line3D>();
	public FormLine3D(int x, int y, int z, Line3D[] lineList){
		this.x = x;
		this.y = y;
		this.z = z;
		
		for(int i = 0; i < lineList.length; i++){
			this.lineList3D.add(lineList[i]);
		}
	}
	public FormLine3D(int x, int y, int z){
		this(x,y,z,new Line3D[] {new Line3D(new Point3D(0,0,0),new Point3D(1,0,0))});
	}
	public FormLine3D(){
		this(0,0,0,new Line3D[] {new Line3D(new Point3D(0,0,0),new Point3D(1,0,0))});
	}
	public FormLine3D(Line3D[] pointList){
		this(0,0,0,pointList);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
		for (int i = 0; i < lineList3D.size(); i++){
			lineList3D.get(i).getStartPoint().changeX(x - this.x) ;
			lineList3D.get(i).getEndPoint().changeX(x - this.x) ;
		}
		this.x = x;	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
		for (int i = 0; i < lineList3D.size(); i++){
			lineList3D.get(i).getStartPoint().changeY(y - this.y) ;
			lineList3D.get(i).getEndPoint().changeY(y - this.y) ;
		}
		this.y = y;	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
		for (int i = 0; i < lineList3D.size(); i++){
			lineList3D.get(i).getStartPoint().changeZ(z - this.z) ;
			lineList3D.get(i).getEndPoint().changeZ(z - this.z) ;
		}
		this.z = z;
	}
	public ArrayList<Line3D> getLineList3D() {
		return lineList3D;
	}
	public void setLineList3D(ArrayList<Line3D> lineList) {
		this.lineList3D.clear();
		this.lineList3D = lineList;
	}
	public void setLineList3D(Line3D[] lineList) {
		this.lineList3D.clear();
		for(int i = 0; i < lineList.length; i++){
			this.lineList3D.add(lineList[i]);
		}
	}
	
	public void Translate(int x, int y, int z){
		TranslateX(x);
		TranslateY(y);
		TranslateZ(z);
	}
	public void TranslateX(int translation){
		x+= translation;
		for (int i = 0; i < lineList3D.size(); i++){
			lineList3D.get(i).getEndPoint().changeX(translation);
			lineList3D.get(i).getStartPoint().changeX(translation);
		}
	}
	public void TranslateY(int translation){
		y+= translation;
		for (int i = 0; i < lineList3D.size(); i++){
			lineList3D.get(i).getEndPoint().changeY(translation);
			lineList3D.get(i).getStartPoint().changeY(translation);
		}
	}
	public void TranslateZ(int translation){
		z+= translation;
		for (int i = 0; i < lineList3D.size(); i++){
			lineList3D.get(i).getEndPoint().changeZ(translation);
			lineList3D.get(i).getStartPoint().changeZ(translation);
		}
	}
	public void Rotate(double yaw, double pitch, double roll){
		Yaw(yaw);
		//noPitch
		//noRoll
	}
	public void Yaw(double angle){//very glitchy
		for (int i = 0; i < lineList3D.size(); i++){
			//Translation
			double distance = Math.sqrt(Math.pow((lineList3D.get(i).getEndPoint().getX() - x), 2) + Math.pow((lineList3D.get(i).getEndPoint().getY() - y), 2)) + .6;
			double anglePre = Math.atan2((lineList3D.get(i).getEndPoint().getY() - y), (lineList3D.get(i).getEndPoint().getX() - x));
			double angleFinal = (anglePre + angle);//%(Math.PI*2);
			double xT = x + distance*Math.cos(angleFinal);
			double yT = y + distance*Math.sin(angleFinal);
//			System.out.printf("d: %f\nanglePre: %f\nxT: %f\nyT: %f\n",d,anglePre,xT,yT);
			lineList3D.get(i).getEndPoint().setPosition((int)xT, (int)yT);
			
			distance = Math.sqrt(Math.pow((lineList3D.get(i).getStartPoint().getX() - x), 2) + Math.pow((lineList3D.get(i).getStartPoint().getY() - y), 2)) + .6;
			anglePre = Math.atan2((lineList3D.get(i).getStartPoint().getY() - y), (lineList3D.get(i).getStartPoint().getX() - x));
			angleFinal = (anglePre + angle);//%(Math.PI*2);
			xT = x + distance*Math.cos(angleFinal);
			yT = y + distance*Math.sin(angleFinal);
//			System.out.printf("d: %f\nanglePre: %f\nxT: %f\nyT: %f\n",d,anglePre,xT,yT);
			lineList3D.get(i).getStartPoint().setPosition((int)xT, (int)yT);
		}
	}

}
