import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ListenerToCloseWindow extends WindowAdapter{
	public void windowClosing(WindowEvent w) {
	    w.getWindow().dispose() ;
	    System.exit(0);
	  }
}
