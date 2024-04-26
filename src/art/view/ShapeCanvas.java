package art.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import art.controller.Controller;

public class ShapeCanvas extends JPanel
{
	private ArrayList<Polygon> triangleList;
	private ArrayList<Polygon> polygonList;
	private ArrayList<Ellipse2D> ellipseList;
	private ArrayList<Rectangle> rectangleList;
	private Controller app;
	
	private BufferedImage canvasImage;
	
	private Color backgroundColor;
	
	public ShapeCanvas(Controller app)
	{
		super();
		this.app = app;
		
		this.triangleList = new ArrayList<Polygon>();
		this.polygonList = new ArrayList<Polygon>();
		this.ellipseList = new ArrayList<Ellipse2D>();
		this.rectangleList = new ArrayList<Rectangle>();
		
		canvasImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
		
		this.setMinimumSize(new Dimension(800, 800));
		this.setPreferredSize(new Dimension(800, 800));
		this.setMaximumSize(getPreferredSize());
		
		backgroundColor = Color.WHITE;
		
		updateImage();
	}
	
	public void addShape(Shape current)
	{
		if (current instanceof Polygon)
		{
			// Is triangle or other polygon.
			if (((Polygon)current).xpoints.length == 3)
			{
				triangleList.add((Polygon)current);
			}
			else
			{
				polygonList.add((Polygon)current);
			}
		}
		else if (current instanceof Ellipse2D)
		{
			ellipseList.add((Ellipse2D)current);
		}
		else
		{
			rectangleList.add((Rectangle)current);
		}
		
		updateImage();
	}
	
	public void clear()
	{
		canvasImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
		
		triangleList.clear();
		polygonList.clear();
		ellipseList.clear();
		rectangleList.clear();
		
		updateImage();
	}
	
	public void changeBackground()
	{
		backgroundColor = createRandomColor();
		updateImage();
	}
	
	private void saveArt(String path)
	{
		try
		{
			ImageIO.write(canvasImage, "png", new File(path));
		}
		catch (IOException | NullPointerException error)
		{
			app.handleError(error);
		}
	}
	
	public void save()
	{
		JFileChooser fileChooser = new JFileChooser();
		
		int result = fileChooser.showSaveDialog(this);
		
		if (result == JFileChooser.APPROVE_OPTION)
		{
			String path = fileChooser.getSelectedFile().getPath();
			
			// Add .png if not there.
			if (path.length() <= 4 || !path.substring(path.length() - 4).equals(".png"))
			{
				path += ".png";
			}
			
			saveArt(path);
		}
	}
	
	public void loadImage()
	{
		try
		{
			JFileChooser picker = new JFileChooser();
			picker.addChoosableFileFilter(new FileNameExtensionFilter("Pictures!", "png"));
			
			if (picker.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				String filepath = picker.getSelectedFile().getPath();
				canvasImage = ImageIO.read(new File(filepath));
				repaint();
			}
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
	}
	
	@Override
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.drawImage(canvasImage, 0, 0, null);
	}
	
	private void updateImage()
	{
		Graphics2D drawingTool = (Graphics2D)canvasImage.getGraphics();
		
		drawingTool.setColor(backgroundColor);
		drawingTool.fill(new Rectangle(0, 0, 800, 800));
		
		// Triangles.
		for (Polygon triangle : triangleList)
		{
			drawingTool.setColor(createRandomColor());
			drawingTool.fill(triangle);
		}
		
		// Polygons.
		for (Polygon polygon : polygonList)
		{
			drawingTool.setColor(createRandomColor());
			drawingTool.setStroke(new BasicStroke(6));
			drawingTool.draw(polygon);
		}
		
		// Ellipses.
		for (Ellipse2D ellipse : ellipseList)
		{
			drawingTool.setColor(createRandomColor());
			drawingTool.setStroke(new BasicStroke(2));
			drawingTool.fill(ellipse);
			drawingTool.setColor(createRandomColor());
			drawingTool.draw(ellipse);
		}
		
		// Rectangles.
		for (Rectangle rectangle : rectangleList)
		{
			drawingTool.setColor(createRandomColor());
			drawingTool.fill(rectangle);
		}
		
		drawingTool.dispose();
		repaint();
	}
	
	private Color createRandomColor()
	{
		int red   = (int)(Math.random() * 256);
		int green = (int)(Math.random() * 256);
		int blue  = (int)(Math.random() * 256);
		int alpha = (int)(Math.random() * 256);
		
		Color color = new Color(red, green, blue, alpha);
		
		return color;
	}
}
