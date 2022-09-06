import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;


class MyDocumentListener implements DocumentListener {
  final String newline = "\n";

  public void insertUpdate(DocumentEvent e) {
      updateLog(e, "inserted into");
  }
  public void removeUpdate(DocumentEvent e) {
      updateLog(e, "removed from");
  }
  public void changedUpdate(DocumentEvent e) {
      //Plain text components don't fire these events.
  }

  public void updateLog(DocumentEvent e, String action) {
      Document doc = (Document)e.getDocument();
      int changeLength = e.getLength();
      System.out.println(
          changeLength + " character"
        + ((changeLength == 1) ? " " : "s ")
        + action + " " + doc.getProperty("name") + "."
        + newline
        + "  Text length = " + doc.getLength() + newline);
  }
}


