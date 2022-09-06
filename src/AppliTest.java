import java.awt.*;
import java.awt.event.*;

public class AppliTest extends Frame implements ActionListener, ItemListener {
	
	private int t;
	private Label time;
	private Button start;
	private List colorChoice;
	private CanvasTest canvas;
	
	public AppliTest() {
		super("Test of canvas");
		this.t =0;
		this.time = new Label("time ="+t);
		this.start = new Button("Start");
		this.colorChoice = new List();
		this.colorChoice.add("Blue");
		this.colorChoice.add("Red");
		this.canvas = new CanvasTest(Color.BLUE,100,80);
		
		this.start.addActionListener(this);
		this.colorChoice.addItemListener(this);
		
		this.setLayout(new FlowLayout());
		this.add(colorChoice);
		this.add(time);
		this.add(start);
		this.setLayout(new FlowLayout());
		this.add(this.canvas);
		this.addWindowListener(new ListenerToCloseWindow());
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==colorChoice) {
			String couleur = colorChoice.getSelectedItem();
			if (couleur == "Blue") {
				this.canvas.setColor(Color.BLUE);
			}
			if (couleur == "Red") {
				this.canvas.setColor(Color.RED);
			}
		}
		this.repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == start) {
			
		}
		this.repaint();
	}
	
	public void paint(Graphics g) {
		canvas.paint(g);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AppliTest app = new AppliTest();
	    app.setLocation(100, 100);
	    app.setSize(1000,1000);
		app.setVisible(true);
	}



}