import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Dimension;

public class Crowd extends Canvas implements Runnable {

		// Attributes :
	
		private static final long serialVersionUID = 1L;
		
		AppliCrowd parent;
		
		protected int sleepingLength;     
		protected boolean continueAnimation = true;   
		static final int W_CANVAS = 1100;  
		static final int H_CANVAS = 500;  
		
		final int dt = 1;
		private int time ;
		private int sizePopulation ;
		private LinkedList<Individual> people;
		private Room room ;
		private boolean superimposed ;

	// Constructors :
		public Crowd(int d){
			sleepingLength = d; 
			setPreferredSize (new Dimension(W_CANVAS, H_CANVAS));
			sizePopulation = 0;
			people = new LinkedList<Individual>();
			this.room = new Room() ;
			superimposed = false;
		}
		public Crowd(int d, Individual ind, Room room){
			sleepingLength = d; 
			sizePopulation = 1;
			people = new LinkedList<Individual>();
			people.add(ind);
			this.room =  room;
			superimposed = false;
		}
		public Crowd(int d, int n){
			sleepingLength = d; 
			time = 0;
			sizePopulation = n ;
			people = new LinkedList<Individual>();
			this.room = new Room();
			for(int i=0 ; i<n ; i++) {
				Individual ind = new Individual(people,room);
				people.add(ind);
			}
			superimposed = false;	
		}
		public Crowd(int d,int n, Room room) {
			sleepingLength = d; 
			time = 0;
			sizePopulation = n ;
			this.room = room;
			people = new LinkedList<Individual>();
			for(int i=0 ; i<n ; i++) {
				Individual ind = new Individual(people,this.room);
				people.add(ind);
			}
			superimposed = false;	
		}

		
	// Methods :
		// Getters & Setters :
		public int getTime() {
			return time;
		}
		public void setTime(int time) {
			this.time = time;
		}
		public int getSizePopulation() {
			return sizePopulation;
		}
		public void setSizePopulation(int sizePopulation) {
			this.sizePopulation = sizePopulation;
		}
		public LinkedList<Individual> getPeople() {
			return people;
		}
		public void setPeople(LinkedList<Individual> people) {
			this.people = people;
		}
		public Room getRoom() {
			return room;
		}
		public void setRoom(Room room) {
			this.room = room;
		}
		public boolean isSuperimposed() {
			return superimposed;
		}
		public void setSuperimposed(boolean superimposed) {
			this.superimposed = superimposed;
		}
		public double getDt() {
		return dt;
		}
		public void setParameters(int a,int b, int v) {
			Iterator<Individual> indIt = people.iterator();
			while (indIt.hasNext()) {
				Individual ind = indIt.next();
				ind.setInertia(v);
				ind.setWallphobia(b);
				ind.setRecoil(a);
			}
		}

		//Others :
		public void advanceTime() {
			time+=dt;
		}
		public void move() {
			Iterator<Individual> indIt = people.iterator();
			while (indIt.hasNext()) {
				Individual ind = indIt.next();
				Vector vMove = new Vector();
				Vector vPeopleRepulse = ind.peopleRepulse(people);
				Vector vWallRepulse = ind.wallRepulse(room);
				Vector vDoorAttraction = ind.doorAttraction(room);
				vMove.add(vPeopleRepulse);
				vMove.add(vWallRepulse);
				vMove.add(vDoorAttraction);
				double vx = vMove.getVx();
				double vy = vMove.getVy();
				int x = ind.getX();
				int y = ind.getY();
				int ux = (int) (dt*vx);
				int uy = (int) (dt*vy);
				ind.setX(x+ux);
				ind.setY(y+uy);
				}
		}
		public void updateSuperimposed() {
			boolean sI = false;
			Iterator<Individual> indIt = people.iterator();
			while (indIt.hasNext() && !(sI) ) {
				Individual ind = indIt.next();	
				sI = ind.notSuperImposed(people);
			}
			superimposed = sI;
		}
		public void updatePeople() {
			LinkedList<Individual> toRemove = new LinkedList<Individual>();
			Iterator<Individual> indIt = people.iterator();
			while (indIt.hasNext()) {
				Individual ind = indIt.next();
				ind.inTheRoom(room);
				if (!(ind.isItr())){
					toRemove.add(ind);
					sizePopulation-=1;
				}
			}
			Iterator<Individual> removeIt = toRemove.iterator();
			while (removeIt.hasNext()) {
				Individual removeInd = removeIt.next();
				people.remove(removeInd);
			}
		}
		public void crowdMove() {
			move();
			updateSuperimposed();
			updatePeople();
			advanceTime();
			testEnd();
		}
		public void setColorInd(Color colorInd) {
			Iterator<Individual> indIt = people.iterator();
			while (indIt.hasNext()) {
				Individual ind = indIt.next();	
				ind.setC(colorInd);
			}
		}
		public void setBreadth(int breadth) {
			Iterator<Individual> indIt = people.iterator();
			while (indIt.hasNext()) {
				Individual ind = indIt.next();
				ind.setBreadth(breadth);
			}
		}
		public void setRecoil(int recoil) {
			Iterator<Individual> indIt = people.iterator();
			while (indIt.hasNext()) {
				Individual ind = indIt.next();
				ind.setRecoil(recoil);
			}
		}
		public void setWallphobia(int wallphobia) {
			Iterator<Individual> indIt = people.iterator();
			while (indIt.hasNext()) {
				Individual ind = indIt.next();
				ind.setWallphobia(wallphobia);
			}
		}
		public void setInertia(int inertia) {
			Iterator<Individual> indIt = people.iterator();
			while (indIt.hasNext()) {
				Individual ind = indIt.next();
				ind.setRecoil(inertia);
			}
		}
		
		//Draw :
		public void drawPeople(Graphics g,int xp, int yp) {
			Iterator<Individual> indIt = people.iterator();
			while (indIt.hasNext()) {
				Individual ind = indIt.next();
				ind.draw(g,xp,yp);
			}
		}
		
		/*public void update(Graphics g) {
			paint(g);
		}
		public void paint(Graphics g) {
			System.out.println("xx");
			room.draw(g,0,0);
			drawPeople(g,0,0);
		}*/
		
		
		// public void paint(Graphics g) {}
		
		public void draw(Graphics g, int xp, int yp) {
			room.draw(g,xp,yp);
			drawPeople(g,xp,yp);
		}
		
		//Animation :
		public void run() {
			while ( continueAnimation == true ) {
				this.crowdMove();
			    this.parent.repaint();
			    
			    try { 
		        Thread.sleep(sleepingLength);  
			    } catch (Exception e) {
			    	}
		    } 
		}
//		public void animate() {
//		    Thread t = new Thread(this);
//		    t.start();
//		}
		public void replace() {   
		}
		
		protected void testEnd() {
			    if(sizePopulation == 0) {
			        repaint(); // Pour être sur d'avoir dessiné la position finale avant d'arréter l'animation
			        continueAnimation = false ;      // Inutile de continuer la boucle d'animation
			        System.out.println("The room has been evacuated.");
			    }
		}
		
		
		//Main : 
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Room room =  new Room(1000,500);
			Exit exit = new Exit(0,0,200,room);
			room.addExit(exit);
			Crowd crowd = new Crowd(20,3,room);
			//Frame f = new Frame();
			LinkedList<Individual> people = crowd.getPeople();
			Iterator<Individual> indIt1 = people.iterator();
			while (indIt1.hasNext()) {
				Individual ind1 = indIt1.next();
				ind1.print();
			}
			crowd.crowdMove();
			Iterator<Individual> indIt2 = people.iterator();
			while (indIt2.hasNext()) {
				Individual ind2 = indIt2.next();
				ind2.print();
			}
			
		}

}
