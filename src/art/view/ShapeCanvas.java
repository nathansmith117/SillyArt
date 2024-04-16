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

import art.controller.Controller;

public class ShapeCanvas extends JPanel
{
	private ArrayList<Polygon> triangleList;
	private ArrayList<Polygon> polygonList;
	private ArrayList<Ellipse2D> ellipseList;
	private ArrayList<Rectangle> rectangleList;
	private Controller app;
	
	private BufferedImage canvasImage;
	
	public ShapeCanvas(Controller app)
	{
		super();
		this.app = app;
		
		this.triangleList = new ArrayList<Polygon>();
		this.polygonList = new ArrayList<Polygon>();
		this.ellipseList = new ArrayList<Ellipse2D>();
		this.rectangleList = new ArrayList<Rectangle>();
		
		canvasImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
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
