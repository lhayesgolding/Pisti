package pisti;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Pisti extends Application {
    
    private Player user = new Player();
    private Player bot = new Player();
    private Deck deck = new Deck();
    private GridPane mainPane = new GridPane();
    private HBox botCardPane = new HBox();
    private HBox userCardPane = new HBox();
    private Pane playAreaPane = new Pane();
    private Pane botCapturedPane = new Pane();
    private Pane userCapturedPane = new Pane();
    private Label lbBotCards = new Label("Opponent's Cards");
    private Label lbUserCards = new Label("Your Cards"); 
    private Button btRules = new Button("Game Rules");
    private Text txtStartGame = new Text("Select Difficulty to Start Game");
    private Button btEasy = new Button("Easy");
    private Button btHard = new Button("Hard");
    
    @Override
    public void start(Stage primaryStage) {
        
        // set up panes and dimensions
        botCardPane.setPadding(new Insets(10, 10, 10, 10));
        userCardPane.setPadding(new Insets(10, 10, 10, 10));
        mainPane.setPadding(new Insets(10, 10, 10, 10));
        playAreaPane.setStyle("-fx-background-color: azure; -fx-border-color: black");
        //playAreaPane.setStyle("-fx-border-color: black");
        botCapturedPane.setStyle("-fx-background-color: azure");
        userCapturedPane.setStyle("-fx-background-color: azure");
        mainPane.setStyle("-fx-background-color: green");
        mainPane.getColumnConstraints().add(new ColumnConstraints(50)); // column 0 is 50 wide
        mainPane.getColumnConstraints().add(new ColumnConstraints(290)); // column 1 is 290 wide
        mainPane.getColumnConstraints().add(new ColumnConstraints(20)); // column 2 is 20 wide
        mainPane.getColumnConstraints().add(new ColumnConstraints(290)); // column 3 is 290 wide
        mainPane.getColumnConstraints().add(new ColumnConstraints(50)); // column 4 is 50 wider
        mainPane.getRowConstraints().add(new RowConstraints(10)); // row 0 is 10 high
        mainPane.getRowConstraints().add(new RowConstraints(120)); // row 1 is 120 high
        mainPane.getRowConstraints().add(new RowConstraints(180)); // row 2 is 180 high
        mainPane.getRowConstraints().add(new RowConstraints(120)); // row 3 is 120 high
        mainPane.getRowConstraints().add(new RowConstraints(10)); // row 4 is 10 high
        
        btRules.setLayoutX(95);
        btRules.setLayoutY(100);
        btEasy.setLayoutX(85);
        btEasy.setLayoutY(60);
        btHard.setLayoutX(150);
        btHard.setLayoutY(60);
        txtStartGame.setLayoutX(18);
        txtStartGame.setLayoutY(45);
        txtStartGame.setStyle("-fx-font: 18 arial;");
        playAreaPane.getChildren().addAll(btRules, btEasy, btHard,txtStartGame);
     
        
        mainPane.add(lbBotCards, 1, 0);
        mainPane.add(botCardPane, 1, 1);
        mainPane.add(playAreaPane, 1, 2);
        mainPane.add(userCardPane, 1, 3);
        mainPane.add(lbUserCards, 1, 4);
        
        
        Scene scene = new Scene(mainPane);
        primaryStage.setTitle("Pisti");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
