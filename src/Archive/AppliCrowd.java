import java.awt.*;
import java.awt.event.*;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AppliCrowd extends Frame implements ActionListener,ItemListener {

	// Attributes :
	final int spaceOut = 30;
	final int R = 100;
	final int a = 3;
	final int b = 1;
	final int v = 2;
	final int wC = 800;
	final int hC = 600;
	final int positionXCanvas = 0;
	final int positionYCanvas = 150;
	
	private Crowd crowd;
	private int breadth;
	private int recoil;
	private int wallphobia;
	private int inertia;
	private int wCanvas;
	private int hCanvas;
	
	
	final Color CI = Color.RED;
	final Color CR = Color.WHITE;
	final Color CD = Color.GRAY;
	final Color CB = Color.lightGray;
	
	
	private Color colorInd;

	
	private List listColorInd;
	private int sizePopulation;
	private int time;
	private Button increaseBreadth;
	private Button decreaseBreadth;
	private Label showTime;
	private Label showSizePopulation;
	private TextField initialPopulation;
	private Button start;
	private Button initialize;
	private TextField changeRecoil;
	private TextField changeWallphobia;
	private TextField changeInertia;
	
	// Constructors :
	public AppliCrowd(Room room, int sizePopulation) {
			super("AppliCrowd"); 
			
			/*on choisit des paramètres initiaux */
		    breadth = R;
		    recoil = a;
		    wallphobia = b;
		    inertia = v;
			crowd = new Crowd(100,sizePopulation,room);
		    colorInd = CI;

		    Polygon geo = room.getGeo();
		    Rectangle rect = geo.getBounds();
			wCanvas = rect.width + 2*spaceOut;
			hCanvas = rect.height + 2*spaceOut;
		    
		    time = 0;
		    this.sizePopulation = sizePopulation;

			/*on créée les boutons */
		    initialize = new Button("Initialiser");
		    start = new Button("Commencer l'évacuation");
		    increaseBreadth = new Button("augmenter la taille");
		    decreaseBreadth = new Button("diminuer la taille");
		    initialPopulation = new TextField("population initiale");
		    showTime = new Label("Temps=" + time);
		    showSizePopulation = new Label("Population=" + sizePopulation);
		    changeRecoil = new TextField("répulsion individus : 3");
		    changeWallphobia =new TextField("répulsion murs : 1");
		    changeInertia = new TextField("vitesse : 2");
		    
		    Panel p1 = new Panel();
		    p1.setLayout(new FlowLayout());
		    p1.add(initialize);
		    p1.add(start);
		    p1.add(increaseBreadth);
		    p1.add(decreaseBreadth);
		    
		    Panel p2 = new Panel();
		    p2.setLayout(new FlowLayout());
		    p2.add(initialPopulation);
		    p2.add(showTime);
		    p2.add(showSizePopulation);
		    
		    Panel p3 = new Panel();
		    p3.setLayout(new FlowLayout());
		    p3.add(changeRecoil);
		    p3.add(changeWallphobia);
		    p3.add(changeInertia);
		    
		    listColorInd = new List();
		    listColorInd.add("black");
		    listColorInd.add("light gray");
		    listColorInd.add("gray");
		    listColorInd.add("dark gray");
		    listColorInd.add("blue");
		    listColorInd.add("pink");
		    listColorInd.add("yellow");
		    listColorInd.add("red");
		    listColorInd.add("orange");
		    
		    changeRecoil.addActionListener(this);
		    changeWallphobia.addActionListener(this);
		    changeInertia.addActionListener(this);
		    initialPopulation.addActionListener(this);
		    initialize.addActionListener(this);
		    start.addActionListener(this);
		    increaseBreadth.addActionListener(this);
		    decreaseBreadth.addActionListener(this);
		    listColorInd.addItemListener(this);
		    
		    this.setLayout(new GridLayout(3,1));
		    this.add(p1);
		    this.add(p2);
		    this.add(p3);
		    this.add(listColorInd);
		    
		    this.setLayout(new FlowLayout());
		    this.add(crowd);
		    crowd.parent = this;
		    
		    this.addWindowListener(new ListenerToCloseWindow()); 
		    this.setSize(1200,1000);
		    this.setVisible(true);
		    
	}
	
	
	// Methods :
	
		// Getters & Setters :
		public int getRecoil() {
			return recoil;
		}
		public void setRecoil(int recoil) {
			this.recoil = recoil;
		}
		public int getWallphobia() {
			return wallphobia;
		}
		public void setWallphobia(int wallphobia) {
			this.wallphobia = wallphobia;
		}
		public int getInertia() {
			return inertia;
		}
		public void setInertia(int inertia) {
			this.inertia = inertia;
		}
		public int getSizePopulation() {
			return sizePopulation;
		}
		public void setSizePopulation(int sizePopulation) {
			this.sizePopulation = sizePopulation;
		}

		//Draw :
	  	public void paint(Graphics g)  {
	  		g.setColor(Color.white);
		    g.fillRect(positionXCanvas,positionYCanvas,wCanvas,hCanvas);
		    crowd.draw(g,positionXCanvas+spaceOut,positionYCanvas+spaceOut);
		    this.time = crowd.getTime();
		    this.sizePopulation = crowd.getSizePopulation();
		    this.showTime.setText("Time =" + time);
		    this.showSizePopulation.setText("Population = " + sizePopulation);
		  }
	  	
		//Others :
	  	public void itemStateChanged(ItemEvent evt) {
		if (evt.getSource()==listColorInd) {
	    		String color = listColorInd.getSelectedItem();
	    		if (color == "black") {
	    			colorInd = Color.BLACK;
		    	}
		    	if (color == "light gray") {
		    		colorInd = Color.LIGHT_GRAY;
			}
		    if (color == "gray") {
				colorInd = Color.GRAY;
			}
		    	if (color == "dark gray") {
	        		colorInd = Color.DARK_GRAY;
		    	}
		    	if (color == "blue") {
		    		colorInd = Color.BLUE;
		    	}
		    	if (color == "pink") {
	        		colorInd = Color.PINK;
		    	}
		    	if (color == "yellow") {
		    		colorInd = Color.YELLOW;
		    	}
		    	if (color == "red") {
     			colorInd = Color.RED;
		    	}
		    	if (color == "orange") {
 				colorInd = Color.ORANGE;
		    	}
		    	crowd.setColorInd(colorInd);
		}
		repaint();
	  	}
	  	public void actionPerformed(ActionEvent evt) {
		    if (evt.getSource()==increaseBreadth) {
		         breadth += 1;
				 crowd.setBreadth(breadth);
		    }
		    if (evt.getSource()==decreaseBreadth) {
			     breadth -= 1;
			     crowd.setBreadth(breadth);
			}
		    if (evt.getSource() == start) {
		    		(new Thread(crowd)).start();
		    }
		    if (evt.getSource() == initialize) {
		    		Room room = crowd.getRoom();
		    		String pop = initialPopulation.getText();
		    		System.out.println(pop);

				/*les paramètres initiaux sont potentiellement changés, on prend les nouvelles valeurs */
		    		sizePopulation = Integer.parseInt(pop); 
		    		int a = Integer.parseInt(changeRecoil.getText());
		    		int b = Integer.parseInt(changeWallphobia.getText());
		    		int v = Integer.parseInt(changeInertia.getText());
		    		crowd.setParameters(a, b, v);
		    		this.remove(crowd);
		    		this.crowd = new Crowd(20,sizePopulation,room);
		    		this.add(crowd);
		    		this.pack();
		    		
		    }
		    if (evt.getSource() == initialPopulation) {
		    		
		    }
		    repaint();
		}

		//Main :
	  	public static void main(String[] args) {
	  		
			// TODO Auto-generated method stub
			Room room =  new Room(600,400);
			Exit exit = new Exit(0,'l',200,room);
			room.addExit(exit);
			AppliCrowd app = new AppliCrowd(room,20);
			
			
		}

		
}
