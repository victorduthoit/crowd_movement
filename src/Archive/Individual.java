import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.Color;

/** Cette classe permet de modéliser chaque individu de la salle, il est caractérisé par:
 * -sa position
 * -son rayon
 * -son envie de rejoindre la parte : inertia
 * -sa répulsion envers les autres individu : recoil
 * -sa répulsion envers les murs :wallRepulse
 * -un indicateur permettant de savoir si il est dans la salle
 * -la sortie qu'il envisage (plus proche) : wayOut
 * 
 * @author victorduthoit
 *
 */

public class Individual {


	// Attributes :
	final Color CI = Color.RED;
	final double unreachable = 99999999999999.9; //facilite les méthodes de choix de sortie (pour l'initialisation)
	final int R = 5;
	final double a = 3.;
	final double b = 10.;
	final double v0 = 10.;
	private Color c;
	private int id;
	private int breadth;
	private int x;
	private int y;
	private double recoil;
	private double wallphobia;
	private double inertia;
	private WayOut wayOut;
	private boolean itr; //In The Room
	
	
	// Constructors :
	/**
	 * Pour initialiser un individu, on doit prendre en compte les personnes déjà présentent dans la salle,
	 * d'où la nécessité du paramètre people dans les constructeurs
	 * @param x
	 * @param y
	 * @param room
	 * @param people
	 */
	public Individual(int x, int y, Room room, LinkedList<Individual> people) {
		super();
		this.c = CI;
		id = people.size();
		this.breadth = R;
		this.x = x;
		this.y = y;
		this.recoil = a;
		this.wallphobia = b;
		this.inertia = v0;
		this.wayOut = evaluateOut(room);
		Polygon geo = room.getGeo();
		this.itr = geo.contains(x,y);
	}
	public Individual(LinkedList<Individual> people, Room room) {
		id = people.size();
		c = CI;
		breadth = R;
		recoil = a;
		inertia = v0;
		wallphobia = b;
		Polygon geo = room.getGeo();
		Rectangle bounds = geo.getBounds();
		int w = (int) bounds.getWidth();
		int h = (int) bounds.getHeight();
		x = (int) (Math.random()*(w-2*R)+R);
		y = (int) (Math.random()*(h-2*R)+R);
		while (!notSuperImposed(people) || !geo.contains(x-R,y-R) || !geo.contains(x+R,y-R) || !geo.contains(x-R,y+R) || !geo.contains(x+R,y+R)) {
			x = (int) (Math.random()*(w-2*R)+R);
			y = (int) (Math.random()*(h-2*R)+R);
		}
		itr =true;
		wayOut = evaluateOut(room);
	}
	public Individual(LinkedList<Individual> people, Room room, Color colorInd, int r, int rec, int inert, int wallphob) {
		id = people.size();
		c = colorInd;
		breadth = r;
		recoil = rec;
		inertia = inert;
		wallphobia = wallphob;
		Polygon geo = room.getGeo();
		Rectangle bounds = geo.getBounds();
		int w = (int) bounds.getWidth();
		int h = (int) bounds.getHeight();
		x = (int) Math.random()*(w-2*R)+R;
		y = (int) Math.random()*(h-2*R)+R;
		while (!notSuperImposed(people) || !geo.contains(x-R,y-R) || !geo.contains(x+R,y-R) || !geo.contains(x-R,y+R) || !geo.contains(x+R,y+R)) {
			x = (int) Math.random()*(w-2*R)+R;
			y = (int) Math.random()*(h-2*R)+R;
		}
		itr =true;
		wayOut = evaluateOut(room);
	}


	// Methods :
		// Getters & Setters :
	
		public void setC(Color c) {
			this.c = c;
		}
		public double getWallphobia() {
			return wallphobia;
		}
		public void setWallphobia(double wallphobia) {
			this.wallphobia = wallphobia;
		}
		public Color getC() {
			return CI;
		}
		public double getB() {
			return b;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getBreadth() {
			return breadth;
		}
		public void setBreadth(int breadth) {
			this.breadth = breadth;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public boolean isItr() {
			return itr;
		}
		public void setItr(boolean itr) {
			this.itr = itr;
		}
		public int getR() {
		return R;
	}
		public double getRecoil() {
			return recoil;
		}
		public void setRecoil(double recoil) {
			this.recoil = recoil;
		}
		public double getInertia() {
			return inertia;
		}
		public void setInertia(double inertia) {
			this.inertia = inertia;
		}
		public WayOut getWayOut() {
			return wayOut;
		}
		public void setWayOut(WayOut wayOut) {
			this.wayOut = wayOut;
		}
		public double getA() {
			return a;
		}
		public double getV() {
			return v0;
		}

		
		//Draw, Print :
		public void draw(Graphics g,int xp, int yp) {
			    int r = breadth;
				g.setColor(c);
			    g.fillOval(xp+x-r, yp+y-r, 2*r, 2*r);
		}
		public void print() {
			System.out.println("id="+id);
			System.out.print("x="+x);
			System.out.print(",");
			System.out.println("y="+y);
		}
		private static void print(boolean itr) {
			// TODO Auto-generated method stub
			if (itr) {
				System.out.println("true");
			}
			else System.out.println("false");
		}
		
		// Others :
		public boolean notSuperImposed(LinkedList<Individual> people) {
			/**Permet de savoir si l'individu est superposé à une autre personne.
			 */
			boolean ok = true;
			Iterator<Individual> indIt = people.iterator();
			while (ok && indIt.hasNext()) {
				Individual ind = indIt.next();
				if (!(ind==this)) {
					int xIt = ind.getX();
					int yIt = ind.getY();
					int ds = (this.x-xIt)*(this.x-xIt) + (this.y-yIt)*(this.y-yIt);
					ok = (ds >= 4*R*R);
				}
			}
			return ok;
		}
		public void inTheRoom(Room room) {
			/**actualise l'attribut itr
			 */
			Polygon geo = room.getGeo();
			itr = geo.contains(x,y);
		}
		public boolean Out(Room room) {
			/**Permet de savoir si l'individu a quitté a salle
			 */
			boolean res = false;
			LinkedList<Exit> exits = room.getExits();
			Iterator<Exit> exitIt = exits.iterator();
			while (!(res) && exitIt.hasNext()) {
				Exit exit = exitIt.next();
				Polygon door = exit.getDoor();
				res = door.contains(this.x,this.y);
			}
			return res;
		}
		public WayOut evaluateExit(Exit exit) {
			/**Permet d'évaluer la distance à une sortie donnée et de détertminer le vecteur qui se dirige vers la porte.
			 */
			int id = exit.getId();
			double dist = 0.;
			double vx = 0;
			double vy = 0;
			int r = breadth;
			int f = exit.getFace();
			Polygon door = exit.getDoor();
			Rectangle bounds = door.getBounds();
			int mx = (int) bounds.getMinX();
			int Mx = (int) bounds.getMaxX();
			int my = (int) bounds.getMinY();
			int My = (int) bounds.getMaxY();
			if (f == 0) {
				if ( (y <= my+r) && (y >= My-r) ) {
					vx = -1;
					vy = 0;
					dist = (double) x;
				}
				if (y > My-r) {
					double vx0 = (double) -x;
					double vy0 = (double) My-r-y;
					dist = Math.sqrt(vx0*vx0+vy0*vy0);
					vx = vx0/dist;
					vy = vy0/dist;
				}
				if (y < my+r) {;
					double vx0 = -x;
					double vy0 = my+r-y;
					dist = Math.sqrt(vx0*vx0+vy0*vy0);
					vx = vx0/dist;
					vy = vy0/dist;
				}
				
			}
			if (f == 2) {
				if ( (y <= my+r) && (y >= My-r) ) {
					vx = 1;
					vy = 0;
					dist = (double) mx-x;
				}
				if (y > My-r) {
					double vx0 = (double) mx-x;
					double vy0 = (double) My-r-y;
					dist = Math.sqrt(vx0*vx0+vy0*vy0);
					vx = vx0/dist;
					vy = vy0/dist;
				}
				if (y < my+r) {
					double vx0 = mx-x;
					double vy0 = my+r-y;
					dist = Math.sqrt(vx0*vx0+vy0*vy0);
					vx = vx0/dist;
					vy = vy0/dist;
				}
				
			}
			if (f == 3) {
				if ( (x <= mx+r) && (x >= Mx-r) ) {
					vx = 0;
					vy = 1;
					dist = (double) my-y;
				}
				if (x > Mx-r) {
					double vx0 = (double) Mx-r-x;
					double vy0 = (double) my-y;
					dist = Math.sqrt(vx0*vx0+vy0*vy0);
					vx = vx0/dist;
					vy = vy0/dist;
				}
				if (x < mx+r) {
					double vx0 = mx+r-x;
					double vy0 = my-y;
					dist = Math.sqrt(vx0*vx0+vy0*vy0);
					vx = vx0/dist;
					vy = vy0/dist;
				}
				
			}
			if (f == 1) {
				if ( (x <= mx+r) && (x >= Mx-r) ) {
					vx = 0;
					vy = -1;
					dist = (double) y;
				}
				if (x > Mx-r) {
					double vx0 = (double) Mx-r-x;
					double vy0 = (double) -y;
					dist = Math.sqrt(vx0*vx0+vy0*vy0);
					vx = vx0/dist;
					vy = vy0/dist;
				}
				if (x < mx+r) {
					double vx0 = mx+r-x;
					double vy0 = -y;
					dist = Math.sqrt(vx0*vx0+vy0*vy0);
					vx = vx0/dist;
					vy = vy0/dist;
				}
				
			}
			Vector v = new Vector(vx,vy);
			WayOut wo = new WayOut(id,dist,v);
			return wo;
		}
		public WayOut evaluateOut(Room room) {
			/**Permet d'évaluer la sortie la plus proche t le vecteur permettant de s'y diriger.
			 */
			Vector v0 = new Vector(0,0);
			WayOut wo = new WayOut(-1,unreachable,v0);
			LinkedList<Exit> exits = room.getExits();
			Iterator<Exit> exitIt = exits.iterator();
			while (exitIt.hasNext()) {
				Exit exit = exitIt.next();
				WayOut wo0 = evaluateExit(exit);
				wo.shortestWayOut(wo0);
			}
		return wo;
		}
		public Vector doorAttraction(Room room) {
			/**Permet déterminer le vecteur qui va modéliser l'attraction de la porte et l'envie de sortir.
			 */
			WayOut wo = evaluateOut(room);
			Vector v = wo.getV();
			v.multi(inertia);
//			System.out.print("vDoor = ");
//			v.print();
			return v;
		}
		public Vector peopleRepulse(LinkedList<Individual> people) {
			/**Permet déterminer le vecteur qui va modéliser la répulsion des autres individus.
			 * On choisi une répulsion du type a*sum_i(   (1/||X-X_i||^2) . (X-X_i)   ).
			 */
			Vector v = new Vector(0,0);
			Iterator<Individual> indIt = people.iterator();
			while (indIt.hasNext()) {
				Individual ind = indIt.next();
				if (!(ind.getId()==this.id)) {
					int xIt = ind.getX();
					int yIt = ind.getY();
					Vector rep = new Vector(x-xIt,y-yIt);
					double n = rep.norm();
					double t = 1/(n*n);
					rep.multi(t);
//					rep.print();
					v.add(rep);
				}
			}
			v.multi(recoil);
//			System.out.print("vPeopleRepulse = ");
//			v.print();
			return v;
		}
		public Vector wallRepulse(Room room) {
			/** Détermine le vecteur qui modélise la répulsion des murs.
			 */
			Vector v = new Vector(0,0);
			Polygon geo = room.getGeo();
			Rectangle bounds = geo.getBounds();
			int w = (int) bounds.getWidth();
			int h = (int) bounds.getHeight();
			double x1 = (double) x;
			double y1 = (double) y;
			if ((x<w/2)&&(y<h/2)) {
				double vx = 1/(x1*x1);
				double vy = 1/(y1*y1);
//				System.out.print("vx,vy = ");
//				System.out.print(vx);
//				System.out.print(",");
//				System.out.println(vy);
				v.setVx(vx);
				v.setVy(vy);
			}
			if ((x<w/2)&&(y>=h/2)) {
//				System.out.println(2);
				double vx = 1/(x1*x1);
				double vy = -1/((h-y1)*(h-y1));
				v.setVx(vx);
				v.setVy(vy);
			}
			if ((x1>=w/2)&&(y1<h/2)) {
//				System.out.println(3);
				double vx = -1/((w-x1)*(w-x1));
				double vy = 1/(y1*y1);
				v.setVx(vx);
				v.setVy(vy);
			}
			if ((x1>=w/2)&&(y1>=h/2)){
//				System.out.println(4);
				double vx = -1/((w-x1)*(w-x1));
				double vy = -1/((h-y1)*(h-y1));
				v.setVx(vx);
				v.setVy(vy);
			}
			v.multi(wallphobia);
//			System.out.print("vWallRepulse = ");
//			v.print();
			return v;
		}
		
	  	public static void main(String[] args) {
			// TODO Auto-generated method stub
			Room room =  new Room(100,50);
			Exit exit = new Exit(0,0,20,room);
			room.addExit(exit);
			LinkedList<Individual> people = new LinkedList<Individual>();
			Individual ind1 = new Individual(20,10,room, people);
			people.add(ind1);
			Individual ind2 = new Individual(30,60,room, people);
			people.add(ind2);
			Individual ind3 = new Individual(70,25,room, people);
			people.add(ind3);
			ind1.print();
			ind2.print();
			ind3.print();
			boolean itr1 = ind1.isItr();
			boolean itr2 = ind2.isItr();
			boolean itr3 = ind3.isItr();
			print(itr1);
			print(itr2);
			print(itr3);
			WayOut wo1,wo2,wo3;
			wo1 = ind1.getWayOut();
			wo2 = ind2.getWayOut();
			wo3 = ind3.getWayOut();
			wo1.print();
			wo2.print();
			wo3.print();
			
		}

}




