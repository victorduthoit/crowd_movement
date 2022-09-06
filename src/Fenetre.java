import java.awt.*;
import javax.swing.*;
 
public class Fenetre extends JFrame {
  private JPanel container = new JPanel();
  private JTextField jtf = new JTextField("Valeur par défaut");
  private JLabel label = new JLabel("Un JTextField");

  public Fenetre(){
    this.setTitle("Animation");
    this.setSize(300, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
    JPanel top = new JPanel();
    Font police = new Font("Arial", Font.BOLD, 14);
    jtf.setFont(police);
    jtf.setPreferredSize(new Dimension(150, 30));
    jtf.setForeground(Color.BLUE);
    top.add(label);
    top.add(jtf);
    container.add(top, BorderLayout.NORTH);
    this.setContentPane(container);
    this.setVisible(true);            
  }
	
	//Main : 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Fenetre fen =new Fenetre();
	}
}