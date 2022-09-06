import javax.swing.JFrame;
import javax.swing.JTextField;

public class UsingDocumentListener {

  public static void main(String[] a){
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JTextField textField = new JTextField();
    
    textField.getDocument().addDocumentListener(new MyDocumentListener());
    textField.getDocument().putProperty("name", "Text Field");

    frame.add(textField);

    frame.setSize(300, 200);
    frame.setVisible(true);
  }


}