package application;

import java.util.ArrayList;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class GraphicalEditor {

	/************** LISTES DES FORMES DE L'EDITEUR ***************/
	
	private ArrayList<Rectangle> rectangles;
	private ArrayList<Line> lines;
	private ArrayList<Ellipse> ellipses;
	
	public GraphicalEditor() {
		super();
		rectangles = new ArrayList<Rectangle>();
		lines = new ArrayList<Line>();
		ellipses = new ArrayList<Ellipse>();
	}
	
	public ArrayList<Rectangle> getRectangles() {
		return rectangles;
	}
	
	public ArrayList<Line> getLines() {
		return lines;
	}
	
	public ArrayList<Ellipse> getEllipses() {
		return ellipses;
	}
	
}
