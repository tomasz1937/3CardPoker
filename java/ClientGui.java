// Tomasz Kmiotek poker client

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ClientGui extends Application{
	
	ListView<String> listItems = new ListView<String>();
	Client clientConnection;
	boolean pairWager = false;
	boolean isWin = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Display welcome scene
		buildWelcomeScene(primaryStage); 
	}
	
	// Build and show welcome scene for client GUI
	void buildWelcomeScene(Stage stage){
		
		
		// Create the background image and set the size of the background
        Image backgroundImage = new Image(getClass().getResourceAsStream("clientWelcome.jpg"));
        BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);

        // Create a BackgroundImage with the loaded image
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, size);

        // Create a Background with the BackgroundImage
        Background background = new Background(backgroundImg);

        // Create border pane 
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(55.0));

        // Create Vbox
        VBox vbox = new VBox();
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        vbox.setSpacing(10.0);

        // Create welcome test
        Text text = new Text("Welcome!");
        text.setFill(Color.valueOf("#d70e0e"));
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setFont(new Font(82.0));

        // Create Hbox
        HBox hbox = new HBox();
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        hbox.setSpacing(40.0);

        // Set textfield for IP address
        TextField textField1 = new TextField();
        textField1.setPrefHeight(25.0);
        textField1.setPromptText("Please Enter IP Address");

        // Set textfield for port 
        TextField textField2 = new TextField();
        textField2.setPrefHeight(25.0);
        textField2.setPromptText("Please Enter Port");
        
        // Label for status of connection
        Label status = new Label("Invalid IP address, port, or server is not running");
        status.setTextFill(Color.valueOf("#d70e0e"));
        status.setVisible(false);
       
        // Set button for connecting to server, calls buildPlayScene
        Button connectButton = new Button("Connect to Server");
        connectButton.setOnAction(e -> {
        
        	// IP and Port are the text in the textFields
            String IP = textField1.getText();
            int Port = Integer.parseInt(textField2.getText());
            
            // Run the client and move to play scene, if failed display status
            clientConnection = new Client(data->{
        		listItems.getItems().add(data.getMessage());
        				
        		});
        		
        		clientConnection.Ip = IP;
        		clientConnection.port = Port;
        		clientConnection.start();
        		
        		// Wait for thread to be started
        		try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		// Check if thread was started 
        		if(clientConnection.isRunning()) {
        			buildPlayScene(stage,listItems, clientConnection);
        		}
        		else {
        			status.setVisible(true);
        			return;
        		}
        	
        });

        // Add all assets to their respective containers 
        hbox.getChildren().addAll(textField1, textField2);
        vbox.getChildren().addAll(text, hbox, connectButton, status);
        borderPane.setCenter(vbox);
        borderPane.setBackground(background);

        // Create Scene and display
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setWidth(600); // Set the width of the stage
        stage.setHeight(600); // Set the height of the stage
        stage.show();       
        
        // Close the client if user presses "X"
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });   
	}
	
	// Build and show the scene for playing the actual game 
	void buildPlayScene(Stage stage, ListView<String> listItems, Client client) {	
		
		isWin = false;
		
		// Create all images for game play
		Image image = new Image("back.png");
		
		ImageView image1 = new ImageView();
		image1.setFitWidth(100);
	    image1.setFitHeight(100);
	    image1.setImage(image);
	    
	    ImageView image2 = new ImageView();
		image2.setFitWidth(100);
	    image2.setFitHeight(100);
	    image2.setImage(image);
	    
	    ImageView image3 = new ImageView();
		image3.setFitWidth(100);
	    image3.setFitHeight(100); 
	    image3.setImage(image);
	    
	    ImageView image4 = new ImageView();
		image4.setFitWidth(100);
	    image4.setFitHeight(100);
	    image4.setImage(image);
	    
	    ImageView image5 = new ImageView();
		image5.setFitWidth(100);
	    image5.setFitHeight(100);
	    image5.setImage(image);
	    
	    ImageView image6 = new ImageView();
		image6.setFitWidth(100);
	    image6.setFitHeight(100);
	    image6.setImage(image);
	    
		
		// Create the background image and set the size of the background
        Image backgroundImage = new Image(getClass().getResourceAsStream("PokerTable.jpg"));
        BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);

        // Create a BackgroundImage with the loaded image
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, size);

        // Create a Background with the BackgroundImage
        Background background = new Background(backgroundImg);
        
        // New BorderPane
        BorderPane pane = new BorderPane();

        // Top MenuBar
        MenuBar menuBar = new MenuBar();
        Menu optionsMenu = new Menu("Options");
        MenuItem exitMenuItem = new MenuItem("Exit");
    
        MenuItem freshStartMenuItem = new MenuItem("Fresh Start");
        MenuItem newLookMenuItem = new MenuItem("NewLook");

        optionsMenu.getItems().addAll(exitMenuItem, freshStartMenuItem, newLookMenuItem);
        menuBar.getMenus().add(optionsMenu);  

        // Center VBox with Label
        VBox topVBox = new VBox(20);
        topVBox.setAlignment(javafx.geometry.Pos.CENTER);
        
        // HBox for cards
        HBox imageHboxBottom = new HBox(25);
        imageHboxBottom.getChildren().addAll(image1, image2, image3);
        imageHboxBottom.setAlignment(javafx.geometry.Pos.CENTER);
        
        // HBox for cards
        HBox imageHboxTop = new HBox(25);
        imageHboxTop.getChildren().addAll(image4, image5, image6);
        imageHboxTop.setAlignment(javafx.geometry.Pos.CENTER);
        
        // Label for dealer
        Label label = new Label("Dealer");
        label.setPrefSize(600, 15);
        label.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label.setTextFill(javafx.scene.paint.Color.valueOf("#ffff00"));
        label.setFont(Font.font("Serif", FontWeight.BOLD, 48)); 
        
        // Bottom VBox with Label
        VBox bottomVBox = new VBox();
        bottomVBox.setPrefSize(100, 200);
        bottomVBox.setAlignment(javafx.geometry.Pos.CENTER);
        
        // Label for player
        Label bottomLabel = new Label("Player");
        bottomLabel.setPrefSize(600, 200);
        bottomLabel.setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);
        bottomLabel.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        bottomLabel.setTextAlignment(javafx.scene.text.TextAlignment.JUSTIFY);
        bottomLabel.setTextFill(javafx.scene.paint.Color.valueOf("#ffff00"));
        bottomLabel.setFont(Font.font("Serif", FontWeight.BOLD, 48)); 
       
        // TextFields for wagers
        TextField anteWager = new TextField();
        anteWager.setPrefHeight(15.0);
        anteWager.setPromptText("Ante Wager ($5-$25)");
        
        TextField pairPlus = new TextField();
        pairPlus.setPrefHeight(20.0);
        pairPlus.setPromptText("Pair Plus Wager ($5-$25)");
        
        TextField playWager = new TextField();
        playWager.setDisable(true);
        playWager.setPrefHeight(25.0);
        
        // Buttons for playing or folding
        Button play = new Button("Play");
        play.setDisable(true);
        play.setFont(Font.font("Serif", FontWeight.BOLD, 18));
              
        Button fold = new Button("Fold");
        fold.setDisable(true); 
        fold.setFont(Font.font("Serif", FontWeight.BOLD, 18));
        
        // HBox for game buttons
        HBox buttonBox = new HBox(20);
        buttonBox.getChildren().addAll(play,fold);
        buttonBox.setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);
        
        // Label for telling the user what to do 
        Label directions = new Label("Start by placing an Ante Wager and an optional pair plus wager");
        directions.setTextFill(javafx.scene.paint.Color.valueOf("#ffff00"));
        directions.setFont(Font.font("Serif", FontWeight.BOLD, 28));
        
        // Button to continue game
        Button cont = new Button("Continue");
        cont.setFont(Font.font("Serif", FontWeight.BOLD, 28));
        
        // Area for winnings
        Label wins = new Label("Total Winnings");
        wins.setTextFill(javafx.scene.paint.Color.valueOf("#ffff00"));
        wins.setFont(Font.font("Serif", FontWeight.BOLD, 18));
        
        TextField winnings = new TextField();
        winnings.setText("0");
        
        // HBox for all wagers TextFields
        VBox wagersBox = new VBox(20);
        wagersBox.getChildren().addAll(playWager,anteWager,pairPlus,wins, winnings, cont);
        wagersBox.setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);       
        
        // Add assets to VBoxs
        bottomVBox.getChildren().addAll(imageHboxBottom, buttonBox, bottomLabel);
        topVBox.getChildren().addAll(menuBar, label, imageHboxTop);
        
        anteWager.setEditable(true);
    	pairPlus.setEditable(true);
			
        // Set locations 
        pane.setRight(wagersBox);
        pane.setCenter(directions);
        pane.setTop(topVBox);
        pane.setBottom(bottomVBox);
        pane.setLeft(listItems);
        pane.setBackground(background);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setWidth(1000); // Set the width of the stage
        stage.setHeight(1000); // Set the height of the stage
        stage.show();
        
        
        // Continue event handler 
        cont.setOnAction(e-> {  
        	
        	// Get text from input and see if it is correct 
        	if(pairPlus.getText().equals("")) {
        		System.out.println(pairPlus.getText());
        		pairWager = false;
        	}
        	else {
        		pairWager = true;
        	}
        	try {
        		Integer.parseInt(anteWager.getText());
        	}
        	catch(Exception e2){
        		directions.setText("Enter only numerical characters");
        		return;
        	}
        	if(pairWager) {
        		try {
        			Integer.parseInt(pairPlus.getText());
        		}
        		catch(Exception e2){
        			directions.setText("Enter only numerical characters");
        			return;
        		}
        	}
        	
        	if(pairWager) {
        		int aW = Integer.parseInt(anteWager.getText());
        		int pW = Integer.parseInt(pairPlus.getText());
        		
        		if(aW > 25 || aW < 5) {
        			directions.setText("Enter an ante wager between 5 and 25 dollars");
        			return;
        		}
        		if(pW > 25 || pW < 5) {
        			directions.setText("Enter a pair wager between 5 and 25 dollars");
        			return;
        		}
        		else {
        			clientConnection.send("Dealer Cards"); 
        			cont.setDisable(true);
        			directions.setText("Would you like to play or fold?");
        			play.setDisable(false);
        			fold.setDisable(false);
        		}
        	}
        	else {
        		int aW = Integer.parseInt(anteWager.getText());
            		
            	if(aW > 25 || aW < 5) {
            		directions.setText("Enter a ante wager between 5 and 25 dollars");
            		return;
            	}
            	else { 
            		clientConnection.send("Dealer Cards");
            		cont.setDisable(true);
            		directions.setText("Would you like to play or fold?");
            		play.setDisable(false);
            		fold.setDisable(false);
            		anteWager.setDisable(true);
            		pairPlus.setDisable(true);
            		}
            	}
        	
        	
        	
        	ArrayList<String> list = new ArrayList<String>();
        	
        	// wait 
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	for(String i : listItems.getItems()) {
        		
        		System.out.println(i);
        	
        		if(Character.isDigit(i.charAt(0)) || i.charAt(0) == 'a' || i.charAt(0) == 'j' || i.charAt(0) == 'k' || i.charAt(0) == 'q') {
        			
        			list.add(i);		
        		}		
        	}
        			
        	imageHboxTop.getChildren().removeAll(image4, image5, image6);
        		
        	Image imageCi1 = new Image("/PNG-cards-1.3/"+list.get(0));
        	Image imageCi2 = new Image("/PNG-cards-1.3/"+list.get(1));
        	Image imageCi3 = new Image("/PNG-cards-1.3/"+list.get(2));
        	
        	ImageView imageC1 = new ImageView();
    		imageC1.setFitWidth(100);
    		imageC1.setFitHeight(100);
    		imageC1.setImage(imageCi1);
    	    
    	    ImageView imageC2 = new ImageView();
    	    imageC2.setFitWidth(100);
    	    imageC2.setFitHeight(100);
    	    imageC2.setImage(imageCi2);
    	    
    	    ImageView imageC3 = new ImageView();
    	    imageC3.setFitWidth(100);
    	    imageC3.setFitHeight(100); 
    	    imageC3.setImage(imageCi3);
        		
    	    imageHboxTop.getChildren().addAll(imageC1, imageC2, imageC3);
            imageHboxTop.setAlignment(javafx.geometry.Pos.CENTER);
        		
            pane.setRight(wagersBox);
            pane.setCenter(directions);
            pane.setTop(topVBox);
            pane.setBottom(bottomVBox);
            pane.setLeft(listItems);
            pane.setBackground(background);
            
            stage.setScene(scene);
            stage.setWidth(1000); // Set the width of the stage
            stage.setHeight(1000); // Set the height of the stage
          
        });
        
        
        // Play button for game
        play.setOnAction(e->{
        	      	
        	play.setDisable(true);
        	playWager.setText(anteWager.getText());
        	anteWager.setEditable(false);
        	pairPlus.setEditable(false);
        	
        	if(pairWager) {
        		clientConnection.send("ante " + anteWager.getText()); 
    			clientConnection.send("pair " + pairPlus.getText());
        	}
        	else {
        		clientConnection.send(anteWager.getText() + " ante"); 
        	}
        	
        	ArrayList<String> list = new ArrayList<String>();
        	
        	// wait 
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	for(String i : listItems.getItems()) {
        		
        		System.out.println(i);
        	
        		if(Character.isDigit(i.charAt(0)) || i.charAt(0) == 'a' || i.charAt(0) == 'j' || i.charAt(0) == 'k' || i.charAt(0) == 'q') {
        			
        			list.add(i);		
        		}		
        	}
        	
        	imageHboxBottom.getChildren().removeAll(image1, image2, image3);
    		
        	Image imageCi1 = new Image("/PNG-cards-1.3/"+list.get(0));
        	Image imageCi2 = new Image("/PNG-cards-1.3/"+list.get(1));
        	Image imageCi3 = new Image("/PNG-cards-1.3/"+list.get(2));
        	
        	ImageView imageC1 = new ImageView();
    		imageC1.setFitWidth(100);
    		imageC1.setFitHeight(100);
    		imageC1.setImage(imageCi1);
    	    
    	    ImageView imageC2 = new ImageView();
    	    imageC2.setFitWidth(100);
    	    imageC2.setFitHeight(100);
    	    imageC2.setImage(imageCi2);
    	    
    	    ImageView imageC3 = new ImageView();
    	    imageC3.setFitWidth(100);
    	    imageC3.setFitHeight(100); 
    	    imageC3.setImage(imageCi3);
        		
    	    imageHboxBottom.getChildren().addAll(imageC1, imageC2, imageC3);
            imageHboxBottom.setAlignment(javafx.geometry.Pos.CENTER);
        		
            pane.setRight(wagersBox);
            pane.setCenter(directions);
            pane.setTop(topVBox);
            pane.setBottom(bottomVBox);
            pane.setLeft(listItems);
            pane.setBackground(background);
            
            stage.setScene(scene);
            stage.setWidth(1000); // Set the width of the stage
            stage.setHeight(1000); // Set the height of the stage
      
        	
        	
        });
        
        // Play button for game
        fold.setOnAction(e->{
        	
        	isWin = false;       	
        	endGameScreen(stage, winnings.getText(), isWin, clientConnection,listItems);	
        });
                        
        // Exit game if user presses exit
        exitMenuItem.setOnAction(e ->{
        	Platform.exit();
            System.exit(0);
        });
        
        // New look for the game 
        newLookMenuItem.setOnAction(e ->{
        	
        	// Create the new background image and set the size of the background
            Image backgroundImage2 = new Image(getClass().getResourceAsStream("RedTable.jpg"));
            BackgroundSize size2 = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);

            // Create a new BackgroundImage with the loaded image
            BackgroundImage backgroundImg2 = new BackgroundImage(backgroundImage2,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, size2);

            // Create a Background with the BackgroundImage
            Background background2 = new Background(backgroundImg2);
        	
            // Set new background
        	pane.setBackground(background2);
        	
        	// Set new label colors
        	bottomLabel.setTextFill(javafx.scene.paint.Color.valueOf("#2eff23"));
        	label.setTextFill(javafx.scene.paint.Color.valueOf("#2eff23"));

        });
       

	}
	// Function for end game screen
	void endGameScreen(Stage stage, String winnings, boolean isWin, Client client, ListView<String> listItems) {
		// Create Labels
        Label resultLabel = new Label();
        Label totalMoneyLabel = new Label();

        // Set initial text for labels
        resultLabel.setText("You " + (isWin ? "won!" : "lost."));
        resultLabel.setTextFill(javafx.scene.paint.Color.valueOf("#2eff23"));
        resultLabel.setFont(Font.font("Serif", FontWeight.BOLD, 24));
        totalMoneyLabel.setText("Total money won: $" + winnings);

        // Create Buttons
        Button exitButton = new Button("Quit");
        exitButton.setOnAction(e -> stage.close()); // Exit button event handler

        Button restartButton = new Button("Another Round");
        restartButton.setOnAction(e -> {
            // Reset game variables
            buildPlayScene(stage,listItems, client);
        });

        // Create VBox layout
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(resultLabel, totalMoneyLabel, restartButton, exitButton);

        // Create Scene
        Scene scene = new Scene(vBox, 1000, 1000);

        // Set Stage properties
        stage.setTitle("Game Result");
        stage.setScene(scene);
        stage.show();
        	
        }
    	
    }

	

