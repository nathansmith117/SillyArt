package art.controller;

import javax.swing.JOptionPane;
import art.view.ArtFrame;

public class Controller
{
	private ArtFrame frame;
	
	public Controller()
	{
		this.frame = new ArtFrame(this);
	}
	
	public void start()
	{
		JOptionPane.showMessageDialog(frame, "Welcome to silly art!");
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(frame, error.getMessage(), "We have ittle tiny issue!",
				JOptionPane.ERROR_MESSAGE);
	}
}
