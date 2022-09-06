import java.awt.Container;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawPolyPanel extends JPanel {
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Polygon geo = new Polygon();
	geo.addPoint(0, 0);
	geo.addPoint(100, 0);
	geo.addPoint(100,50);
	geo.addPoint(0, 50);

	int x = 20;
	int y = 10;
	int w = 5;
	int h = 5;
	g.drawOval(x, y, w, h);
    g.drawPolygon(geo);
    
  }
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setTitle("DrawPoly");
    frame.setSize(350, 250);
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    Container contentPane = frame.getContentPane();
    contentPane.add(new DrawPolyPanel());

    frame.show();
  }
}
           