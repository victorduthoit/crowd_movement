import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Obstacle {
	
	/**
	 * Cette classe modélise un obstacle de la salle, elle est définie par :
	 * -une couleur
	 * -un identifiant
	 * -un polygone
	 * 
	 * @author victorduthoit
	 *
	 */

	// Attributes :
	final Color CO = Color.DARK_GRAY;
	private Color colorObstacle;
	private int id;
	private Polygon poly;


	
	// Constructors :
	public Obstacle(int id, Polygon poly) {
		super();
		this.colorObstacle = CO;
		this.id = id;
		this.poly = poly;
	}
	
	// Methods :
		// Getters & Setters :
		public int getId() {
				return id;
			}
		public Color getColorObstacle() {
				return colorObstacle;
			}
		public void setColorObstacle(Color colorObstacle) {
				this.colorObstacle = colorObstacle;
			}
		public Color getCO() {
				return CO;
			}
		public void setId(int id) {
				this.id = id;
			}
		public Polygon getPoly() {
				return poly;
			}
		public void setPoly(Polygon poly) {
				this.poly = poly;
			}

		//Draw :
		public void draw(Graphics g,int xp,int yp) {
				g.setColor(colorObstacle);
				g.fillPolygon(poly);
			}
}
