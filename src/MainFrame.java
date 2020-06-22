import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public MainFrame()
	{
		this.setTitle(AppConstants.WINDOW_TITLE);
		this.setSize(AppConstants.WINDOW_WIDTH, AppConstants.WINDOW_HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		Container contentArea = this.getContentPane();
		contentArea.setPreferredSize(new Dimension(AppConstants.WINDOW_WIDTH, AppConstants.WINDOW_HEIGHT));
		MainPanel mainPanel = new MainPanel(AppConstants.WINDOW_WIDTH, AppConstants.WINDOW_HEIGHT);
		mainPanel.setFocusable(true);
		contentArea.add(mainPanel);
		mainPanel.requestFocusInWindow();
		
		
		this.setVisible(true);
		this.pack();
	}
}
