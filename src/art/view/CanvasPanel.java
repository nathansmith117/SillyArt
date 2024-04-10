package art.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import art.controller.Controller;

public class CanvasPanel extends JPanel
{
	private Controller app;
	private BufferedImage canvasImage;
	
	public CanvasPanel(Controller app)
	{
		super();
		
		this.app = app;
		
		this.canvasImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
		updateCanvas();
	}
	
	private void updateCanvas()
	{
		Graphics2D drawingTool = (Graphics2D)canvasImage.getGraphics();
		
		drawingTool.setColor(Color.ORANGE);
		drawingTool.fill(new Rectangle(0, 0, 800, 800));
		
		drawingTool.setStroke(new BasicStroke(3));
		drawingTool.setColor(Color.DARK_GRAY);
		drawingTool.draw(drawUfo());
		drawingTool.setStroke(new BasicStroke(1));
		drawingTool.draw(drawWeirdThing());
		drawingTool.setStroke(new BasicStroke(5));
		drawingTool.draw(drawWack());
		
		drawingTool.dispose();
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.drawImage(canvasImage, 0, 0, null);
	}
	
	// A little helper for scaling and transforming my points.
	private void scaleAndTransformPoints(Polygon polygon, int scale, int xTransform, int yTransform)
	{
		for (int index = 0; index < polygon.npoints; index++)
		{
			polygon.xpoints[index] *= scale;
			polygon.xpoints[index] += xTransform;
			
			polygon.ypoints[index] *= scale;
			polygon.ypoints[index] += yTransform;
		}
	}
	
	private Polygon drawUfo()
	{
		Polygon ufo = new Polygon();
		
		int[] xPoints = {2, 4, 4, 6, 5, 1, 0, 2};
		int[] yPoints = {0, 0, 1, 1, 2, 2, 1, 1};
		
		ufo.xpoints = xPoints;
		ufo.ypoints = yPoints;
		ufo.npoints = xPoints.length;
		scaleAndTransformPoints(ufo, 30, 20, 20);
		
		return ufo;
	}
	
	private Polygon drawWeirdThing()
	{
		Polygon weirdThing = new Polygon();
		
		weirdThing.addPoint(1, 0);
		weirdThing.addPoint(3, 0);
		weirdThing.addPoint(3, 1);
		weirdThing.addPoint(2, 2);
		weirdThing.addPoint(2, 3);
		weirdThing.addPoint(0, 3);
		weirdThing.addPoint(0, 1);
		weirdThing.addPoint(1, 2);
		
		scaleAndTransformPoints(weirdThing, 30, 210, 20);
		
		return weirdThing;
	}
	
	private Polygon drawWack()
	{
		Polygon wack = new Polygon();
		
		int[] xPoints = {1, 2, 2, 3, 4, 0, 1};
		int[] yPoints = {0, 0, 2, 2, 3, 3, 1};
		
		wack.xpoints = xPoints;
		wack.ypoints = yPoints;
		wack.npoints = xPoints.length;
		scaleAndTransformPoints(wack, 30, 20, 120);
		
		return wack;
	}
}
