/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author HELM
 */
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class DatePickerSample {
 
    private Stage stage;
    private DatePicker checkInDatePicker;
    private DatePicker inPicker;
//    public static void main(String[] args) {
//        Locale.setDefault(Locale.US);
//        launch(args);
//    }
 
    	public JPanel initAndShowGUI(JTextField jtf) {
		JPanel panel = new JPanel();
		JFXPanel fxPanel = new JFXPanel();
                Platform.runLater(() -> {

			fxPanel.setScene(createScene());
		});
                fxPanel.setBackground(Color.red);
		panel.add(fxPanel);
                panel.setSize(161, 300);
                panel.setLocation(jtf.getLocation().x,jtf.getLocation().y-20);
                jtf.setSize(0, 0);
                panel.setBackground(Color.white);

                return panel;
	}
    
    
    private Scene createScene() {
	
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		
		// Title
		Label title1 = new Label("Enter and check if the input date is");
		Label title2 = new Label("within the selected date range.");
		VBox titleVb = new VBox();
		titleVb.setAlignment(Pos.CENTER);
//		titleVb.getChildren().addAll(title1, title2);
		
		// Input date picker
//		Label inPickLabel = new Label("Input date:");
		inPicker = new DatePicker();
//		grid.add(inPickLabel, 0, 0);
		grid.add(inPicker, 1, 0);
		
		
		// Vbox and scene
		VBox vbox = new VBox(20);
		vbox.setPadding(new Insets(-20, 0, 8, -10));
		vbox.getChildren().addAll(titleVb, grid);

		return new Scene(vbox);
	}
    
    
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("DatePickerSample ");
        initUI();
        stage.show();
    }
 
    private void initUI() {
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);

        checkInDatePicker = new DatePicker();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label checkInlabel = new Label("Check-In Date:");
        gridPane.add(checkInlabel, 0, 0);

        GridPane.setHalignment(checkInlabel, HPos.LEFT);
        gridPane.add(checkInDatePicker, 0, 1);
        vbox.getChildren().add(gridPane);
    }
}
