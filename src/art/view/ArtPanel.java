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
	private JButton normalPolygonButton;
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
		this.normalPolygonButton = new JButton("Normal polygon");
		this.clearButton = new JButton("clear");
		this.colorButton = new JButton("color");
		
		this.currentScale = 0;
		this.currentEdgeCount = 0;
		this.scaleSlider = new JSlider(MINIMUM_SCALE, MAXIMUM_SCALE);
		this.edgeSlider = new JSlider(MINIMUM_EDGE, MAXIMUM_EDGE);
		this.sliderPanel = new JPanel(new GridLayout(0, 1));
		
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
		scaleSlider.setOrientation(JSlider.VERTICAL);
		scaleSlider.setMajorTickSpacing(10);
		scaleSlider.setMinorTickSpacing(2);
		scaleSlider.setPaintLabels(true);
		scaleSlider.setPaintTicks(true);
		currentScale = scaleSlider.getValue();
		
		edgeSlider.setLabelTable(edgeLabels);
		edgeSlider.setOrientation(JSlider.VERTICAL);
		edgeSlider.setMajorTickSpacing(5);
		edgeSlider.setMinorTickSpacing(1);
		edgeSlider.setPaintLabels(true);
		edgeSlider.setPaintTicks(true);
		currentEdgeCount = edgeSlider.getValue();
	}
	
	private void setupPanel()
	{	
		this.setBackground(Color.CYAN);
		this.setLayout(layout);
		this.add(menuPanel);
		this.add(sliderPanel);
		this.add(canvas);
		
		menuPanel.setPreferredSize(new Dimension(200, 450));
		menuPanel.add(infoPane);
		menuPanel.add(saveButton);
		menuPanel.add(loadButton);
		menuPanel.add(triangleButton);
		menuPanel.add(ellipseButton);
		menuPanel.add(rectangleButton);
		menuPanel.add(normalPolygonButton);
		menuPanel.add(polygonButton);
		menuPanel.add(clearButton);
		menuPanel.add(colorButton);
		
		sliderPanel.setPreferredSize(new Dimension(250, 450));
		sliderPanel.add(scaleSlider);
		sliderPanel.add(edgeSlider);
		
		infoPane.setViewportView(infoArea);
		infoArea.setLineWrap(true);
		infoArea.setWrapStyleWord(true);
		infoArea.setEditable(false);
		
		infoPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		infoPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}
	
	private void setupListeners()
	{
		scaleSlider.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent event)
			{
				if (!scaleSlider.getValueIsAdjusting())
				{
					currentScale = scaleSlider.getValue();
				}
			}
		});
		
		edgeSlider.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent event)
			{
				if (!edgeSlider.getValueIsAdjusting())
				{
					currentEdgeCount = edgeSlider.getValue();
				}
			}
		});
		
		saveButton.addActionListener(click -> canvas.save());
		loadButton.addActionListener(click -> canvas.loadImage());
		
		triangleButton.addActionListener(click -> canvas.addShape(createPolygon(3)));
		ellipseButton.addActionListener(click -> canvas.addShape(createEllipse()));
		rectangleButton.addActionListener(click -> canvas.addShape(createRectangle()));
		polygonButton.addActionListener(click -> canvas.addShape(createPolygon(currentEdgeCount)));
		normalPolygonButton.addActionListener(click -> canvas.addShape(createNormalPolygon(currentEdgeCount)));
		
		clearButton.addActionListener(click -> canvas.clear());
		colorButton.addActionListener(click -> canvas.changeBackground());
	}
	
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, menuPanel, 5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, menuPanel, 150, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, menuPanel, -5, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, menuPanel, 5, SpringLayout.WEST, this);
		
		layout.putConstraint(SpringLayout.NORTH, sliderPanel, 5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, sliderPanel, 95, SpringLayout.EAST, menuPanel);
		layout.putConstraint(SpringLayout.SOUTH, sliderPanel, -5, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, sliderPanel, 5, SpringLayout.EAST, menuPanel);
		
		layout.putConstraint(SpringLayout.NORTH, canvas, 0, SpringLayout.NORTH, sliderPanel);
		layout.putConstraint(SpringLayout.EAST, canvas, -5, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, canvas, 0, SpringLayout.SOUTH, sliderPanel);
		layout.putConstraint(SpringLayout.WEST, canvas, 5, SpringLayout.EAST, sliderPanel);
	}
	
	private boolean coinFlip()
	{
		return (int)(Math.random() * 2) == 0;
	}
	
	private Polygon createPolygon(int sides)
	{
		Polygon currentShape = new Polygon();
		
		int originX = (int)(Math.random() * 800);
		int originY = (int)(Math.random() * 800);
		
		for (int index = 0; index < sides; index++)
		{
			int minus = coinFlip() ? -1 : 1;
			int shiftX = (int)(Math.random() * currentScale) * minus;
			minus = coinFlip() ? -1 : 1;
			int shiftY = (int)(Math.random() * currentScale) * minus;
			currentShape.addPoint(originX + shiftX, originY + shiftY);
		}
		
		return currentShape;
	}
	
	private Polygon createNormalPolygon(int sides)
	{
		Polygon polygon = new Polygon();
		
		int originX = (int)(Math.random() * 800);
		int originY = (int)(Math.random() * 800);
		
		for (int lineNum = 0; lineNum < sides; lineNum++)
		{
			double angle = (double)(lineNum) * ((Math.PI * 2.0) / (double)(sides));
			
			int xPoint = (int)(currentScale * Math.sin(angle) + originX);
			int yPoint = (int)(currentScale * Math.cos(angle) + originY);
			
			polygon.addPoint(xPoint, yPoint);
		}
		
		return polygon;
	}
	
	private Rectangle createRectangle()
	{
		Rectangle currentRectangle;
		
		int cornerX = (int)(Math.random() * 800);
		int cornerY = (int)(Math.random() * 800);
		int width = (int)(Math.random() * currentScale) + 1;
		
		if (coinFlip())
		{
			currentRectangle = new Rectangle(cornerX, cornerY, width, width);
		}
		else
		{
			int height = (int)(Math.random() * currentScale) + 1;
			currentRectangle = new Rectangle(cornerX, cornerY, width, height);
		}
		
		return currentRectangle;
	}
	
	private Ellipse2D createEllipse()
	{
		Ellipse2D ellipse = new Ellipse2D.Double();
		
		int cornerX = (int)(Math.random() * 800);
		int cornerY = (int)(Math.random() * 800);
		double width = Math.random() * currentScale + 1;
		
		if (coinFlip())
		{
			ellipse.setFrame(cornerX, cornerY, width, width);
		}
		else
		{
			double height = Math.random() * currentScale + 1;
			ellipse.setFrame(cornerX, cornerY, width, height);
		}
		
		return ellipse;
	}
}
