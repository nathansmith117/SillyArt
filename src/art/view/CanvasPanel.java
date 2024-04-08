package art.view;

import javax.swing.JPanel;
import art.controller.Controller;

public class CanvasPanel extends JPanel
{
	private Controller app;
	
	public CanvasPanel(Controller app)
	{
		super();
		
		this.app = app;
	}
}
