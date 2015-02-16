package miniGame;
import java.awt.Color;
import java.awt.Graphics;

public class Point3D extends Point{
	private int z;
	public Point3D(int x, int y, int z){
		super( x, y);
		this.z = z;
	}
	public Point3D(){
		super();
		z = 0;
	}

	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public void changeZ(int change){
		z += change;
	}
	public void setPosition(int x, int y, int z){
		super.setPosition(x, y);
		this.z = z;
	}
	public int getDistance(Looker look){
		double x1 = (double) getX();
		double y1 = (double) getY();
		double z1 = (double) getZ();
		double x2 = (double) look.getX();
		double y2 = (double) look.getY();
		double z2 = (double) look.getZ();
		double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
		int d1 = (int) distance;
		return d1;
	}
	public void display(Graphics g,Looker look){
		g.setColor(Color.BLACK);
		int size = 6 / (getDistance(look)/2);
		if (size == 0){
			size = 1;
		}
		g.fillRect(getX(), getY(), size,size);
	}
	public void display(Graphics g, Color c){
		g.setColor(c);
		g.fillRect(getX(), getY(), 1, 1);
	}

}