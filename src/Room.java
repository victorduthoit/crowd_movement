import java.util.Iterator;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Room {
	
	/**
	 * Cette classe modélise la salle dans laquelle évoluent les différents individus. Elle est caractériser par:
	 * -les couleurs du sol, des portes
	 * -la geométrie de la salle : geo. On se contente de faire une salle à géométrie rectangulaire
	 * -les sortie : exits
	 * -les obstacles : obstacles.
	 * Un objet de type Room n'est pas dynamique (pas ammener être modifier pendant l'animation,
	 * les méthodes se limiteront aux ajouts/retraits d'obstacles/sorties et au dessin.
	 */
	
	// Attributes :
	final Color CR = Color.lightGray;
	final Color CD = Color.GRAY;
	private Color colorRoom;
	private Color colorDoor;
	private Polygon geo ;
	private LinkedList<Exit> exits ;
	private LinkedList<Obstacle> obstacles;
	
	// Constructors :
	
	public Room(){
		geo = new Polygon();
		geo.addPoint(0, 0);
		geo.addPoint(0, 1000);
		geo.addPoint(1000, 0);
		geo.addPoint(1000,1000);
		exits = new LinkedList<Exit>();
		obstacles = new LinkedList<Obstacle>();
		colorRoom = CR;
		colorDoor = CD;
	}
	public Room(int w, int h){
		geo = new Polygon();
		geo.addPoint(0, 0);
		geo.addPoint(w, 0);
		geo.addPoint(w,h);
		geo.addPoint(0, h);
		exits = new LinkedList<Exit>();
		obstacles = new LinkedList<Obstacle>();
		colorRoom = CR;
		colorDoor = CD;
	}
	public Room(int w, int h, Color colorRoom, Color colorDoor ){
		this.colorRoom = colorRoom;
		this.colorDoor = colorDoor;
		geo = new Polygon();
		geo.addPoint(0, 0);
		geo.addPoint(w, 0);
		geo.addPoint(w,h);
		geo.addPoint(0, h);
		exits = new LinkedList<Exit>();
		obstacles = new LinkedList<Obstacle>();
	}

	// Methods :
		// Getters & Setters :
		public Color getColorDoor() {
			return colorDoor;
		}
		public void setColorDoor(Color colorDoor) {
			this.colorDoor = colorDoor;
		}
		public Color getColorRoom() {
			return colorRoom;
		}
		public void setColorRoom(Color colorRoom) {
			this.colorRoom = colorRoom;
		}
		public Color getCR() {
			return CR;
		}
		public Polygon getGeo() {
			return geo;
		}
		public void setGeo(Polygon geo) {
			this.geo = geo;
		}
		public LinkedList<Exit> getExits() {
			return exits;
		}
		public void setExits(LinkedList<Exit> exits) {
			this.exits = exits;
		}
		public LinkedList<Obstacle> getObstacles() {
			return obstacles;
		}
		public void setObstacles(LinkedList<Obstacle> obstacles) {
			this.obstacles = obstacles;
		}
		
		//Others :

		public void addObstacle(Obstacle obs) {
			obstacles.add(obs);
		}
		public void addExit(Exit ex) {
			exits.add(ex);
		}
		public void removeObstacle(Obstacle obs){
			obstacles.remove(obs);
		}
		public void removeExit(Exit ex){
			exits.remove(ex);
		}
		public void setDoorsColor(Color colorDoor) {
			Iterator<Exit> exitIt = exits.iterator();
			while (exitIt.hasNext()) {
				Exit exit = exitIt.next();
				exit.setColorDoor(colorDoor);
			}
		}

		//Draw :
		public void draw(Graphics g,int xp,int yp) {
			g.setColor(colorRoom);
			Rectangle rect = geo.getBounds();
			int hR = rect.height;
			int wR = rect.width;
			g.drawRect(xp, yp, wR, hR);
			Iterator<Exit> exitIt = exits.iterator();
			while (exitIt.hasNext()) {
				Exit exit = exitIt.next();
				exit.draw(g,xp,yp);
			}
			Iterator<Obstacle> obsIt = obstacles.iterator();
			while (obsIt.hasNext()) {
				Obstacle obs = obsIt.next();
				obs.draw(g,xp,yp);
			}
		}
		
		//Main :
		public static void main(String[] args) {
		// TODO Auto-generated method stub
			Room room =  new Room(1000,500);
			Exit exit = new Exit(0,0,200,room);
			room.addExit(exit);
			System.out.println("c'est bon");
	}
			

}
