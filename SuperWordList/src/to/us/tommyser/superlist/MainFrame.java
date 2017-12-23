package to.us.tommyser.superlist;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8050406266563768063L;
	
	
	public MainFrame() {
		super("Super Word List");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 400);
		
		add(new MainPanel());
		
		setVisible(true);
	}

}
