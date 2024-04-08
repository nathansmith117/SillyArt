package art.view;

import art.controller.Controller;
import javax.swing.JFrame;

public class ArtFrame extends JFrame
{
	private Controller app;
	private ArtPanel panel;
	
	public ArtFrame(Controller app)
	{
		super();
		this.app = app;
		this.panel = new ArtPanel(this.app);
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		setContentPane(panel);
		setSize(1200, 900);
		setTitle("Silly art");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
