package pisti;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Pisti extends Application {
    
    private Player user;
    private Player bot;
    private Deck deck;
    private HBox botCardPane = new HBox();
    private HBox userCardPane = new HBox();
    private Group mainPane = new Group();
    private Text txtStartGame = new Text("Select Difficulty to Start Game");
    private Button btEasy = new Button("Easy");
    private Button btHard = new Button("Hard");
    private Button btRules = new Button("Game Rules");
    private Image image = new Image("PlayingTable.jpg");
    private ImageView imageView = new ImageView(image);
    private String gameLevel = "";
    private ArrayList<Card> cardsInCenter = new ArrayList<>();
    private int centerCardsXOffset;
    private Image deckImage = new Image("deckimg.jpg");
    private ImageView deckImageView = new ImageView(deckImage);
    private Label lbCardsInDeck = new Label();
    
    @Override
    public void start(Stage primaryStage) {
        
        // set up initial game screen
        imageView.setFitWidth(800);
        imageView.setFitHeight(500);
        mainPane.prefWidth(800);
        mainPane.prefHeight(500);
        txtStartGame.setStyle("-fx-font: 18 arial;");
        txtStartGame.setLayoutX(135);
        txtStartGame.setLayoutY(190);
        btEasy.setPrefSize(75, 50);
        btEasy.setLayoutX(180);
        btEasy.setLayoutY(220);
        btEasy.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent");
        btHard.setPrefSize(75, 50);
        btHard.setLayoutX(280);
        btHard.setLayoutY(220);
        btHard.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent");
        btRules.setLayoutX(220);
        btRules.setLayoutY(290);
        btRules.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent");
        mainPane.getChildren().addAll(imageView, txtStartGame, btEasy, btHard, btRules);

        // rules button handler
        btRules.setOnAction(e -> {
           Pane rulesPane = new Pane();
           String words = 
                   "Gameplay:\n"
                   + "Four cards are dealt on the table, three face down and one face up.\n"
                   + "Four cards are dealt to each player.\n"
                   + "Players take turns playing cards to the center\n"
                   + "When players run out of cards, four more cards are dealt to each.\n\n"
                   + "Capturing cards:\n"
                   + "The player takes the pile if they play a card that matches the rank of the card on top.\n"
                   + "If there is only one card in the pile and a player matches the rank, this is called a Pisti.\n"
                   + "Pistis are placed separately from the players' other captured cards because they are worth more points.\n\n"
                   + "Scoring:\n"
                   + "each non-J Pisti: 10 points\n"
                   + "each J Pisti: 20 points\n"
                   + "each (non-Pisti) 10 J, Q, K, A: 1 point\n"
                   + "2 of Clubs: 2 points\n"
                   + "10 of Diamonds: 3 points\n"
                   + "Player with most cards captured: 3 point bonus";
           Label lbRules = new Label(words);
           rulesPane.getChildren().add(lbRules);
           
           Scene rulesScene = new Scene(rulesPane);
           Stage rulesStage = new Stage();
           rulesStage.setTitle("Pisti Rules");
           rulesStage.setScene(rulesScene);
           rulesStage.show();
        });
        
        // easy button handler
        btEasy.setOnAction(e -> {
           startGame("easy");
           
        });
        
        // hard button handler
        btHard.setOnAction(e -> {
           startGame("hard");
           
        });
        
        Scene scene = new Scene(mainPane);
        primaryStage.setTitle("Pisti");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void startGame(String gameLevel) {
        
        // set up board
        mainPane.getChildren().clear();
        mainPane.getChildren().add(imageView);
        botCardPane.setPadding(new Insets(10, 10, 10, 10));
        userCardPane.setPadding(new Insets(10, 10, 10, 10));
        botCardPane.setLayoutX(50);
        botCardPane.setLayoutY(60);
        deckImageView.setFitWidth(80);
        deckImageView.setFitHeight(100);
        deckImageView.setLayoutX(370);
        deckImageView.setLayoutY(200);
        lbCardsInDeck.setLayoutX(380);
        lbCardsInDeck.setLayoutY(315);
        lbCardsInDeck.setStyle("-fx-font: 10 arial;");
        mainPane.getChildren().addAll(deckImageView, botCardPane, lbCardsInDeck);
        
        
        // create objects to start the game
        user = new Player();
        bot = new Player();
        deck = new Deck();
        deck.shuffleCards();
        
        // deal 4 cards to center
        centerCardsXOffset = 80;
        for (int i = 0; i < 4; i++) {
            cardsInCenter.add(deck.dealCard());
            if (i < 3) {
                ImageView backOfCard = new ImageView("cards/b1fv.png");
                backOfCard.setX(centerCardsXOffset);
                backOfCard.setY(200);
                mainPane.getChildren().add(backOfCard);
                centerCardsXOffset += 15;
            }
            else {
                deck.getCardInDeck(i).getCardImage().setX(centerCardsXOffset);
                deck.getCardInDeck(i).getCardImage().setY(200);
                mainPane.getChildren().add(deck.getCardInDeck(i).getCardImage());
            }
        }
        String cardsLeftText = "Cards Still in\nDeck: " + deck.getNumCardsLeft();
        lbCardsInDeck.setText(cardsLeftText);
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
