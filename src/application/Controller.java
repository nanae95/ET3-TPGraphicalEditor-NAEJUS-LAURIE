package application;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.MotionBlur;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class Controller {

	GraphicalEditor ge;
	private ToggleGroup group;
	
	/*On recupere les layouts et widgets nécessaires du fichier fxml*/
	
	@FXML
	private RadioButton radioButtonSM;
	
	@FXML
	private RadioButton radioButtonE;
	
	@FXML
	private RadioButton radioButtonR;
	
	@FXML
	private RadioButton radioButtonL;
	
	@FXML
	private ColorPicker colorPicker;
	
	@FXML
	private Button buttonC;
	
	@FXML
	private Button buttonD;
	
	@FXML
	private Pane pane;
	
	
	@FXML
	private void initialize() {
		
		/************** INITIALISATION ***************/
		
		ge = new GraphicalEditor();
		double[] coord = {0, 0};
		
		/*On met dans un meme groupe les boutton Ellipse, Rectangle et Ligne*/
		group = new ToggleGroup();
		radioButtonE.setToggleGroup(group);
		radioButtonL.setToggleGroup(group);
		radioButtonR.setToggleGroup(group);
		
		/*Tentative d'ajout d'effet lors de la selection d'une forme*/
		MotionBlur b = new MotionBlur();
		
		/************** 1- CREATION DES FORMES ***************/
		
		pane.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e2) {
				
				if((radioButtonR.isSelected()) && !(radioButtonSM.isSelected())){
					Rectangle rec = new Rectangle();
					rec.setX(e2.getSceneX());
					rec.setY(e2.getSceneY());
					rec.setWidth(e2.getSceneX());
					rec.setHeight(e2.getSceneY());
					
					/* 3- On recupere la couleur de ColorPicker*/
					
					rec.setStroke(colorPicker.getValue());
					rec.setFill(colorPicker.getValue());
					coord[0] = e2.getSceneX();
					coord[1] = e2.getSceneY();
					
					/* 4- Gestion du Select/Move */
					
					/*	RECTANGLE	*/
					
					rec.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e3) {
							if(radioButtonSM.isSelected()) {
								//rec.setEffect(b);
								rec.setX(e3.getSceneX());
								rec.setY(e3.getSceneY());
								rec.setStroke(colorPicker.getValue());
								rec.setFill(colorPicker.getValue());
							}
						}
					});
					ge.getRectangles().add(rec);
					pane.getChildren().add(rec);
				}
				
				/*	LINE	*/
				
				if((radioButtonL.isSelected()) && !(radioButtonSM.isSelected())){
					Line line = new Line();
					line.setStartX(e2.getSceneX());
					line.setStartY(e2.getSceneY());
					line.setEndX(e2.getSceneX());;
					line.setEndY(e2.getSceneY());
					line.setStrokeWidth(6);
					line.setStroke(colorPicker.getValue());
					coord[0] = e2.getSceneX();
					coord[1] = e2.getSceneY();
					line.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e4) {
							if(radioButtonSM.isSelected()) {
								//line.setEffect(b);
								line.setTranslateX(e4.getSceneX());
								line.setTranslateY(e4.getSceneY());
								line.setStroke(colorPicker.getValue());
							}
						}
					});
					ge.getLines().add(line);
					pane.getChildren().add(line);
				}
				
				/*	ELLIPSE	*/
				
				if((radioButtonE.isSelected()) && !(radioButtonSM.isSelected())){
					Ellipse ellipse = new Ellipse();
					ellipse.setCenterX(e2.getSceneX());
					ellipse.setCenterY(e2.getSceneY());
					ellipse.setRadiusX(0);
					ellipse.setRadiusY(0);
					ellipse.setStroke(colorPicker.getValue());
					ellipse.setFill(colorPicker.getValue());
					coord[0] = ellipse.getRadiusX();
					coord[1] = ellipse.getRadiusY();
					ellipse.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e5) {
							if(radioButtonSM.isSelected()) {
								ellipse.setEffect(b);
								ellipse.setCenterX(e5.getSceneX());
								ellipse.setCenterY(e5.getSceneY());
								ellipse.setStroke(colorPicker.getValue());
								ellipse.setFill(colorPicker.getValue());
								ellipse.setEffect(null);
							}
						}
					});
					ge.getEllipses().add(ellipse);
					pane.getChildren().add(ellipse);
				}
			}
		});

		/************** 2- PARTIE DRAG ***************/
		
		pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e3) {
				if((radioButtonR.isSelected()) && !(radioButtonSM.isSelected())){
					ge.getRectangles().get(ge.getRectangles().size()-1).setWidth(e3.getSceneX() - coord[0]);
					ge.getRectangles().get(ge.getRectangles().size()-1).setHeight(e3.getSceneY() - coord[1]);
				}
				if((radioButtonL.isSelected()) && !(radioButtonSM.isSelected())){
					ge.getLines().get(ge.getLines().size()-1).setStartX(e3.getSceneX() + coord[0]);
					ge.getLines().get(ge.getLines().size()-1).setStartY(e3.getSceneY() + coord[1]);
				}
				if((radioButtonE.isSelected()) && !(radioButtonSM.isSelected())){
					ge.getEllipses().get(ge.getEllipses().size()-1).setRadiusX(e3.getSceneX() + coord[0]);
					ge.getEllipses().get(ge.getEllipses().size()-1).setRadiusY(e3.getSceneY() + coord[1]);
				}
			}
		});
		
	}
	
	
	
}
