import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 * Cette classe modélise une sortie de la salle, elle est définie par :
 * -une couleur
 * -un identifiant
 * -un polygone
 * -une face (face de la salle sur laquelle se trouve la porte)
 * @author victorduthoit
 *
 */

public class Exit {
	// Attributes :
	final Color CD = Color.GREEN;
	final int dW = 20; //door width
	final int dT = 10; //door thickness
	private int id;
	private Color colorDoor;
	private Polygon door;
	private int face;
	
	// Constructors :
	public Exit(int id, Polygon door, int face, int closest, int furthest) {
		this.colorDoor = CD;
		this.id = id;
		this.face = face;
		this.door = door;
	}
	public Exit(int id, int face, int position, Room room) {
		this.id = id;
		Polygon geo = room.getGeo();
		Rectangle bounds = geo.getBounds();
		int h = (int) bounds.getHeight();
		int w = (int) bounds.getWidth();
		this.door = new Polygon();
		if (face == 2) {
			door.addPoint(w,position);
			door.addPoint(w+dT,position);
			door.addPoint(w,position+dW);
			door.addPoint(w+dT,position+dW);
		}
		if (face == 0) {
			door.addPoint(0,position);
			door.addPoint(-dT,position);
			door.addPoint(0,position+dW);
			door.addPoint(-dT,position+dW);
		}
		if (face == 3) {
			door.addPoint(position,h);
			door.addPoint(position,h+dT);
			door.addPoint(position+dW,h);
			door.addPoint(position+dW,h+dT);
		}
		if (face == 1) {
			door.addPoint(position,0);
			door.addPoint(position,-dT);
			door.addPoint(position+dW,0);
			door.addPoint(position+dW,-dT);
		}
		
	}

	// Methods :
	
		// Getters & Setters :
		public void setColorDoor(Color colorDoor) {
			this.colorDoor = colorDoor;
		}
		public Color getC() {
			return CD;
		}
		public int getId() {
			return id;
		}
		public int getFace() {
			return face;
		}
		public Polygon getDoor() {
			return door;
		}
		
		//Draw :
		public void draw(Graphics g,int xp,int yp) {
			g.setColor(colorDoor);
			Rectangle rect = door.getBounds();
			int hD = rect.height;
			int wD = rect.width;
			g.fillRect(xp, yp, wD, hD);
		}
		// Others :
}
