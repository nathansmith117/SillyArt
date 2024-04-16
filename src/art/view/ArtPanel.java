package art.view;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SpringLayout;

import java.awt.Color;
import java.awt.GridLayout;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import art.controller.Controller;

public class ArtPanel extends JPanel
{
	private Controller app;
	private SpringLayout layout;
	private JTextArea infoArea;
	private JScrollPane infoPane;
	private JPanel menuPanel;
	private JButton saveButton;
	private JButton loadButton;
	
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
		this.saveButton = new JButton("save");
		this.loadButton = new JButton("load");
		
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
		menuPanel.add(saveButton);
		menuPanel.add(loadButton);
		
		infoPane.setViewportView(infoArea);
		infoArea.setLineWrap(true);
		infoArea.setWrapStyleWord(true);
		infoArea.setEditable(false);
		
		infoPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		infoPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}
	
	private void setupListeners()
	{
		canvas.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent click)
			{
				String info = "Mouse was clicked at: ";
				
				int mouseX = click.getX();
				int mouseY = click.getY();
				
				info += "x: " + mouseX + " y: " + mouseY + "\n";
				
				infoArea.append(info);
			}
			
			public void mousePressed(MouseEvent press)
			{
				String info = "Mouse was pressed at: ";
				
				int mouseX = press.getX();
				int mouseY = press.getY();
				
				info += "x: " + mouseX + " y: " + mouseY + "\n";
				
				infoArea.append(info);
			}
			
			public void mouseReleased(MouseEvent release)
			{
				String info = "Mouse was released at: ";
				
				int mouseX = release.getX();
				int mouseY = release.getY();
				
				info += "x: " + mouseX + " y: " + mouseY + "\n";
				
				infoArea.append(info);
			}
			
			public void mouseEntered(MouseEvent enter)
			{
				String info = "Mouse was entered at: ";
				
				int mouseX = enter.getX();
				int mouseY = enter.getY();
				
				info += "x: " + mouseX + " y: " + mouseY + "\n";
				
				infoArea.append(info);
			}
			
			public void mouseExited(MouseEvent exit)
			{
				String info = "Mouse was exited at: ";
				
				int mouseX = exit.getX();
				int mouseY = exit.getY();
				
				info += "x: " + mouseX + " y: " + mouseY + "\n";
				
				infoArea.append(info);
			}
		});
		
		canvas.addMouseMotionListener(new MouseMotionListener()
		{
			public void mouseMoved(MouseEvent move)
			{
				String info = "Mouse moved at: ";
				
				int mouseX = move.getX();
				int mouseY = move.getY();
				
				info += "x: " + mouseX + "y: " + mouseY + "\n";
				
				infoArea.setText(info);
			}
			
			public void mouseDragged(MouseEvent drag)
			{
				String info = "Mouse dragged at: ";
				
				int mouseX = drag.getX();
				int mouseY = drag.getY();
				
				info += "x: " + mouseX + "y: " + mouseY + "\n";
				
				infoArea.setText(info);
			}
		});
		
		saveButton.addActionListener(click -> canvas.save());
		loadButton.addActionListener(click -> canvas.loadImage());
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
