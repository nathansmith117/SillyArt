package art.view;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SpringLayout;

import java.awt.Color;
import java.awt.GridLayout;

import art.controller.Controller;


public class ArtPanel extends JPanel
{
	private Controller app;
	private SpringLayout layout;
	private JTextArea infoArea;
	private JScrollPane infoPane;
	private JPanel menuPanel;
	private JButton demoButton;
	
	private CanvasPanel canvas;
	
	public ArtPanel(Controller app)
	{
		super();
		this.app = app;
		
		this.canvas = new CanvasPanel(app);
		
		this.layout = new SpringLayout();
		this.infoArea = new JTextArea();
		this.infoPane = new JScrollPane();
		this.menuPanel = new JPanel(new GridLayout(0, 1));
		this.demoButton = new JButton("Click");
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		this.setBackground(Color.CYAN);
		this.setLayout(layout);
		this.add(menuPanel);
		this.add(canvas);
		
		menuPanel.add(infoPane);
		menuPanel.add(demoButton);
		
		infoPane.setViewportView(infoArea);
		infoArea.setLineWrap(true);
		infoArea.setWrapStyleWord(true);
		infoArea.setEditable(false);
		
		infoPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		infoPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, menuPanel, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, menuPanel, 125, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, menuPanel, -25, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, menuPanel, 25, SpringLayout.WEST, this);
		
		layout.putConstraint(SpringLayout.NORTH, canvas, 0, SpringLayout.NORTH, menuPanel);
		layout.putConstraint(SpringLayout.EAST, canvas, -25, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, canvas, 0, SpringLayout.SOUTH, menuPanel);
		layout.putConstraint(SpringLayout.WEST, canvas, 25, SpringLayout.EAST, menuPanel);
	}
}
