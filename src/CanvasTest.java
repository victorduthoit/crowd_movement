import java.awt.*;
import java.awt.event.*;

public class CanvasTest extends Canvas {
	
	final int wC = 1000;
	final int hC = 600;
	
	private Color color;
	private int wCanvas;
	private int hCanvas;
	private int w;
	private int h;
	
	// Constructors :
	public CanvasTest(Color color, int w, int h) {
		super();
		this.color = color;
		this.wCanvas = wC;
		this.hCanvas = hC;
		this.w = w;
		this.h = h;
	}
	
	

	
	//Methods :
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getwCanvas() {
		return wCanvas;
	}
	public void setwCanvas(int wCanvas) {
		this.wCanvas = wCanvas;
	}
	public int gethCanvas() {
		return hCanvas;
	}
	public void sethCanvas(int hCanvas) {
		this.hCanvas = hCanvas;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}

	public void increaseRect(int delta) {
		this.w+=delta;
		this.h+=delta;
	}
	public void decreaseRect(int delta) {
		this.w-=delta;
		this.h-=delta;
	}

	public void paint(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0,200, wC, hC);
		g.setColor(color);
		g.fillRect(wCanvas/2, hCanvas/2 , w, h);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}




}
