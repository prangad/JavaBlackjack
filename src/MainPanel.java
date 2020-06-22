import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	//Instance Variables
	Table table = new Table();
	
	BufferedImage background;
	
	JButton btnHit;
	JButton btnStand;
	
	//Timers
	Timer animationTimer = new Timer((int)(1000/AppConstants.FRAME_RATE), new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			
			if (table.getStatus() != AppConstants.RUNNING)
			{
				btnHit.setEnabled(false);
				btnStand.setEnabled(false);
			}
			else
			{
				btnHit.setEnabled(true);
				btnStand.setEnabled(true);
			}
			
			repaint();
		}
	});
	
	//Constructors
	public MainPanel(int width, int height)
	{
		this.setBackground(Color.DARK_GRAY);
		
		try
		{
			background = ImageIO.read(new File("src/background.jpg"));
		}
		catch (Exception ex)
		{
			
		}
		
		btnHit = new JButton("Hit");
		btnStand = new JButton("Stand");
		this.add(btnHit);
		this.add(btnStand);
		
		btnHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.playerHit();
			}
		});
		
		btnStand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.playerStand();
			}
		});
		
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 2)
				{
					reset();
				}
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			
		});
		
		table.start();
		animationTimer.start();
	}
	
	//Methods
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		
		g.setColor(Color.WHITE);
		//g.drawLine(AppConstants.WINDOW_WIDTH/2, 0, AppConstants.WINDOW_WIDTH/2, AppConstants.WINDOW_HEIGHT);
		//g.drawLine(0, AppConstants.WINDOW_HEIGHT/2, AppConstants.WINDOW_WIDTH, AppConstants.WINDOW_HEIGHT/2);
		
		table.paint(g);
	}
	
	private void reset()
	{
		AppConstants.deck.reset();
		table.reset();
	}
}
