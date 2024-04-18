package art.view;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JSlider;
import javax.swing.JLabel;

import java.util.Hashtable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import java.awt.GridLayout;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

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
	
	private JButton triangleButton;
	private JButton ellipseButton;
	private JButton rectangleButton;
	private JButton polygonButton;
	private JButton clearButton;
	private JButton colorButton;
	
	private ShapeCanvas canvas;
	
	// Things for sliders.
	private final int MINIMUM_EDGE = 5;
	private final int MAXIMUM_EDGE = 20;
	private final int MINIMUM_SCALE = 20;
	private final int MAXIMUM_SCALE = 100;
	
	private int currentScale;
	private int currentEdgeCount;
	
	private JSlider scaleSlider;
	private JSlider edgeSlider;
	
	private JPanel sliderPanel;
	
	public ArtPanel(Controller app)
	{
		super();
		this.app = app;
		
		this.canvas = new ShapeCanvas(app);
		
		this.layout = new SpringLayout();
		this.infoArea = new JTextArea();
		this.infoPane = new JScrollPane();
		this.menuPanel = new JPanel(new GridLayout(0, 1));
		
		this.saveButton = new JButton("save");
		this.loadButton = new JButton("load");
		
		this.triangleButton = new JButton("triangle");
		this.ellipseButton = new JButton("ellipse");
		this.rectangleButton = new JButton("rectangle");
		this.polygonButton = new JButton("polygon");
		this.clearButton = new JButton("clear");
		this.colorButton = new JButton("color");
		
		this.currentScale = 0;
		this.currentEdgeCount = 0;
		this.scaleSlider = new JSlider(MINIMUM_SCALE, MAXIMUM_SCALE);
		this.edgeSlider = new JSlider(MINIMUM_EDGE, MAXIMUM_EDGE);
		this.sliderPanel = new JPanel(new GridLayout(1, 0));
		
		setupSliders();
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupSliders()
	{
		Hashtable<Integer, JLabel> scaleLabels = new Hashtable<Integer, JLabel>();
		Hashtable<Integer, JLabel> edgeLabels = new Hashtable<Integer, JLabel>();
		
		scaleLabels.put((MAXIMUM_SCALE + MINIMUM_SCALE) / 2, new JLabel("<HTML>Medium<BR>Shape</HTML>"));
		scaleLabels.put(MINIMUM_SCALE, new JLabel("<HTML>Small<BR>Shape</HTML>"));
		scaleLabels.put(MAXIMUM_SCALE, new JLabel("<HTML>Large<BR>Shape</HTML>"));
		
		edgeLabels.put(MINIMUM_EDGE, new JLabel("Edges: " + MINIMUM_EDGE));
		edgeLabels.put(MAXIMUM_EDGE, new JLabel("Edges: " + MAXIMUM_EDGE));
		
		scaleSlider.setLabelTable(scaleLabels);
		scaleSlider.setOrientation(JSlider.HORIZONTAL);
		scaleSlider.setMajorTickSpacing(10);
		scaleSlider.setMinorTickSpacing(2);
		scaleSlider.setPaintLabels(true);
		scaleSlider.setPaintTicks(true);
		
		edgeSlider.setLabelTable(edgeLabels);
		edgeSlider.setOrientation(JSlider.HORIZONTAL);
		edgeSlider.setMajorTickSpacing(5);
		edgeSlider.setMinorTickSpacing(1);
		edgeSlider.setPaintLabels(true);
		edgeSlider.setPaintTicks(true);
	}
	
	private void setupPanel()
	{	
		this.setBackground(Color.CYAN);
		this.setLayout(layout);
		this.add(menuPanel);
		this.add(canvas);
		
		menuPanel.setPreferredSize(new Dimension(200, 450));
		menuPanel.add(infoPane);
		menuPanel.add(saveButton);
		menuPanel.add(loadButton);
		menuPanel.add(triangleButton);
		menuPanel.add(ellipseButton);
		menuPanel.add(rectangleButton);
		menuPanel.add(polygonButton);
		menuPanel.add(clearButton);
		menuPanel.add(colorButton);
		
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
