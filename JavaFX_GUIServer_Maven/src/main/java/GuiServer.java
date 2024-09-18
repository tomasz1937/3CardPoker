import java.io.IOException;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GuiServer extends Application{
	
	String IP;
	int port;
	
	Server serverConnection;
	ListView<String> listItems;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		// Display welcome scene
		buildFirstScene(primaryStage); 
		
	}
	
	// Build and show welcome scene for client GUI
	void buildFirstScene(Stage stage){
		
        // Create border pane 
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(55.0));
        borderPane.setStyle("-fx-background-color: gray;");

        // Create Vbox
        VBox vbox = new VBox();
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        vbox.setSpacing(10.0);

        // Create welcome test
        Text text = new Text("Poker Server");
        text.setFill(Color.valueOf("#d70e0e"));
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setFont(new Font(64.0));

        // Create Hbox
        HBox hbox = new HBox();
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        hbox.setSpacing(40.0);
        
        // Set textfield for port 
        TextField portTextField = new TextField();
        portTextField.setPrefHeight(25.0);
        portTextField.setPromptText("Please Enter Port");
        
        // Label for status of server
        Label statusLabel = new Label();
        
        Button stopButton = new Button("Stop Server");
        stopButton.setDisable(true); // Disable stop button initially
          
        stopButton.setOnAction(event -> {
        	buildFirstScene(stage);
        });
     
        
        Button startButton = new Button("Start Server");
        // Event handler for start button 
        startButton.setOnAction(event -> {
                
        	int port = Integer.parseInt(portTextField.getText());
            startButton.setDisable(true);
            stopButton.setDisable(false); 
            serverConnection = new Server(data -> {
    			Platform.runLater(()->{
				listItems.getItems().add(data.getMessage());
    			});
    		});
          
    		serverConnection.port = port;
    		
            // Wait for thread to be started
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    		// Check if thread was started 
    		if(serverConnection.isRunning()) {
    			 buildMainServer(stage, stopButton, serverConnection);
    		}
    		else {
    			statusLabel.setText("Please enter a valid port number");
    			return;
    		}
                
        });
        
        listItems = new ListView<String>();
        
        // Add all assets to their respective containers 
        hbox.getChildren().addAll(portTextField);
        vbox.getChildren().addAll(text, hbox, startButton, stopButton, statusLabel);
        borderPane.setCenter(vbox);

        // Create Scene and display
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setWidth(600); // Set the width of the stage
        stage.setHeight(600); // Set the height of the stage
        stage.show();       
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
	}
	
	void buildMainServer(Stage stage, Button stopButton, Server server) {	
		
		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane);
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: coral");
		pane.setTop(stopButton);
		pane.setCenter(listItems);
		
	
		stage.setScene(scene);
		stage.setWidth(600); // Set the width of the stage
        stage.setHeight(600); // Set the height of the stage
        stage.show();
        
        
        
 
	}
	
	
	
	
}