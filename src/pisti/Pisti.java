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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Pisti extends Application {
    
    private Player user;
    private Player bot;
    private Deck deck;
    private Pane botCardPane = new Pane();
    private Pane userCardPane = new Pane();
    private Group mainPane = new Group();
    private Pane playAreaPane = new Pane();
    private Pane userCapturedPane = new Pane();
    private Pane botCapturedPane = new Pane();
    private Pane userRegularCardsPane = new Pane();
    private Pane userPistisPane = new Pane();
    private Pane botRegularCardsPane = new Pane();
    private Pane botPistisPane = new Pane();
    private Text txtStartGame = new Text("Select Difficulty to Start Game");
    private Button btEasy = new Button("Easy");
    private Button btHard = new Button("Hard");
    private Button btRules = new Button("Game Rules");
    private Image image = new Image("PlayingTable.jpg");
    private ImageView imageView = new ImageView(image);
    private String gameLevel = "";
    private ArrayList<Card> cardsInCenter = new ArrayList<>();
    private int centerCardsXOffset, botRegCaptureXOffset, botPistiXOffset, 
            userRegCaptureXOffset, userPistiXOffset;
    private Image deckImage = new Image("deckimg.jpg");
    private ImageView deckImageView = new ImageView(deckImage);
    private Label lbCardsInDeck = new Label();
    private String cardsLeftText;
    private ImageView clickedImage;
    private boolean botCardPlacement[] = new boolean[4]; // for use with bot's placement of cards in hand in GUI
    private ArrayList<Card> botInitialCardsInHand = new ArrayList<Card>(); // copy of bot's hand at beginning of deal
    private Label lbUserRegCapture = new Label("Regular");
    private Label lbUserPistiCapture = new Label("Pistis");
    private Label lbBotRegCapture = new Label("Regular");
    private Label lbBotPistiCapture = new Label("Pistis");
    private Label lbYourScore, lbBotScore;
    private Label lbWinner = new Label("");
    
    @Override
    public void start(Stage primaryStage) {
        
        // set up initial game screen
        imageView.setFitWidth(800);
        imageView.setFitHeight(500);
        mainPane.prefWidth(800);
        mainPane.prefHeight(500);
        //playAreaPane.setStyle("-fx-border-color: black");
        playAreaPane.setPrefSize(410, 210);
        playAreaPane.setLayoutX(65);
        playAreaPane.setLayoutY(145);
        txtStartGame.setStyle("-fx-font: 18 arial;");
        txtStartGame.setLayoutX(70);
        txtStartGame.setLayoutY(55);
        btEasy.setPrefSize(75, 50);
        btEasy.setLayoutX(120);
        btEasy.setLayoutY(85);
        btEasy.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent");
        btHard.setPrefSize(75, 50);
        btHard.setLayoutX(220);
        btHard.setLayoutY(85);
        btHard.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent");
        btRules.setLayoutX(160);
        btRules.setLayoutY(155);
        btRules.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent");
        playAreaPane.getChildren().addAll(txtStartGame, btEasy, btHard, btRules);
        mainPane.getChildren().addAll(imageView, playAreaPane);

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
                   + "If there is only one card in the pile when a player matches the rank, this is called a Pisti.\n"
                   + "Pistis are placed separately from the players' other captured cards because they are worth more points.\n\n"
                   + "Scoring:\n"
                   + "each non-Jack Pisti: 10 points\n"
                   + "each Jack Pisti: 20 points\n"
                   + "each (non-Pisti) 10 J, Q, K, A: 1 point\n"
                   + "2 of Clubs: 2 points\n"
                   + "10 of Diamonds: 3 points\n"
                   + "Player with most cards captured: 3 point bonus\n\n"
                   + "The player with the most points at the end of the game wins.";
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
        playAreaPane.getChildren().clear();
        // user's side
        userCardPane.setPadding(new Insets(10, 10, 10, 10));
        userCardPane.setLayoutX(30);
        userCardPane.setLayoutY(363);
        lbUserRegCapture.setStyle("-fx-font: 10 arial;");
        lbUserRegCapture.setLayoutY(100);
        userRegularCardsPane.setPrefSize(125, 130);
        userRegularCardsPane.setLayoutX(125);
        userRegularCardsPane.getChildren().add(lbUserRegCapture); 
        lbUserPistiCapture.setStyle("-fx-font: 10 arial;");
        lbUserPistiCapture.setLayoutY(100);
        userPistisPane.setPrefSize(125, 130);
        userPistisPane.getChildren().add(lbUserPistiCapture);
        userCapturedPane.setPrefSize(250, 130);
        userCapturedPane.setLayoutX(505);
        userCapturedPane.setLayoutY(310); 
        userCapturedPane.getChildren().addAll(userPistisPane, userRegularCardsPane);
        //bot's side
        botCardPane.setLayoutX(30);
        botCardPane.setLayoutY(38);
        botCardPane.setPadding(new Insets(10, 10, 10, 10));
        lbBotRegCapture.setStyle("-fx-font: 10 arial;");
        lbBotRegCapture.setLayoutY(100);
        botRegularCardsPane.setPrefSize(125, 130);
        botRegularCardsPane.setLayoutX(125);
        botRegularCardsPane.getChildren().add(lbBotRegCapture);
        lbBotPistiCapture.setStyle("-fx-font: 10 arial;");
        lbBotPistiCapture.setLayoutY(100);
        botPistisPane.setPrefSize(125, 130);
        botPistisPane.getChildren().add(lbBotPistiCapture);
        botCapturedPane.setPrefSize(250, 130);
        botCapturedPane.setLayoutX(505);
        botCapturedPane.setLayoutY(55);
        botCapturedPane.getChildren().addAll(botPistisPane, botRegularCardsPane);
        // center playing area
        deckImageView.setFitWidth(80);
        deckImageView.setFitHeight(100);
        deckImageView.setLayoutX(310);
        deckImageView.setLayoutY(60);
        lbCardsInDeck.setLayoutX(320);
        lbCardsInDeck.setLayoutY(175);
        lbCardsInDeck.setStyle("-fx-font: 10 arial;");
        mainPane.getChildren().addAll(botCardPane, userCardPane, userCapturedPane, botCapturedPane);
        playAreaPane.getChildren().addAll(deckImageView, lbCardsInDeck);
        
        
        // create objects to start the game
        user = new Player();
        bot = new Player();
        deck = new Deck();
        deck.shuffleCards();
        
        // initial X offsets for captured cards
        botRegCaptureXOffset = 0;
        botPistiXOffset = 0;
        userRegCaptureXOffset = 0; 
        userPistiXOffset = 0;
        
        // deal 4 cards to center
        centerCardsXOffset = 15;
        for (int i = 0; i < 4; i++) {
            cardsInCenter.add(deck.dealCard());
            if (i < 3) {
                ImageView backOfCard = new ImageView("cards/b1fv.png");
                backOfCard.setX(centerCardsXOffset);
                backOfCard.setY(60);
                playAreaPane.getChildren().add(backOfCard);
                centerCardsXOffset += 6;
            }
            else {
                deck.getCardInDeck(i).getCardImage().setX(centerCardsXOffset);
                deck.getCardInDeck(i).getCardImage().setY(60);
                playAreaPane.getChildren().add(deck.getCardInDeck(i).getCardImage());
                centerCardsXOffset += 6;
            }
        }
        
        //deal cards to players
        dealCards();
        
        
    }
    
    public void dealCards() {
        int userCardXOffset = 0, botCardXOffset = 0;
        for (int i = 0; i < 4; i++)
            botCardPlacement[i] = true;
        for (int i = 0; i < 4; i++) {
            user.addCardToHand(deck.dealCard());
            user.getCardInHand(i).getCardImage().setX(userCardXOffset);
            userCardXOffset += 80;
            userCardPane.getChildren().add(user.getCardInHand(i).getCardImage());
            Card currentCard = user.getCardInHand(i);
            user.getCardInHand(i).getCardImage().setOnMouseClicked(e -> handleCardPlayed(currentCard));
            bot.addCardToHand(deck.dealCard());
            ImageView botCardImage = new ImageView("cards/b1fv.png");
            botCardImage.setX(botCardXOffset);
            botCardXOffset += 80;
            botCardPane.getChildren().add(botCardImage);
        }
        botInitialCardsInHand = new ArrayList<>(bot.getCardsInHand());
        cardsLeftText = "Cards Still in\nDeck: " + deck.getNumCardsLeft();
        lbCardsInDeck.setText(cardsLeftText);
        if (deck.getNumCardsLeft() == 0)
            playAreaPane.getChildren().remove(deckImageView);
    }
    
    public void handleCardPlayed (Card clickedCard) {
        // ***user plays card***
        ImageView userTempCardImage = new ImageView();
        userTempCardImage = user.getCardInHand(0).getCardImage();
        for (int i = 0; i < user.getCardsInHandSize(); i++) {
            if (user.getCardInHand(i) == clickedCard) {
                userTempCardImage = user.getCardInHand(i).getCardImage();
                user.removeCardFromHand(clickedCard);
                cardsInCenter.add(clickedCard);
            }      
        }
        userTempCardImage.setX(centerCardsXOffset);
        centerCardsXOffset += 6;
        userTempCardImage.setY(60);
        playAreaPane.getChildren().add(userTempCardImage);
        // if match, remove cards from center and add to user's captured piles
        if (cardsInCenter.size() > 1) {
            if (cardsInCenter.get(cardsInCenter.size() - 1).getRankOfCard() == cardsInCenter.get(cardsInCenter.size() - 2).getRankOfCard()) {
                if (cardsInCenter.size() == 2) {
                    user.addAllToPistiCaptured(cardsInCenter);
                    for (int i = 0; i < 2; i++) {
                        cardsInCenter.get(i).getCardImage().setX(userPistiXOffset);
                        cardsInCenter.get(i).getCardImage().setY(0);
                        userPistisPane.getChildren().add(cardsInCenter.get(i).getCardImage());
                        if (userPistiXOffset < 12)
                            userPistiXOffset += 2;
                    }
                }
                else {
                    user.addAllToCardsCaptured(cardsInCenter);
                    for (int i = 0; i < cardsInCenter.size(); i++) {
                        cardsInCenter.get(i).getCardImage().setX(userRegCaptureXOffset);
                        cardsInCenter.get(i).getCardImage().setY(0);
                        userRegularCardsPane.getChildren().add(cardsInCenter.get(i).getCardImage());
                        if (userRegCaptureXOffset < 12)
                            userRegCaptureXOffset += 2;
                    }
                }
                cardsInCenter.clear();
                playAreaPane.getChildren().clear();
                if (deck.getNumCardsLeft() > 0)
                    playAreaPane.getChildren().add(deckImageView);
                playAreaPane.getChildren().add(lbCardsInDeck);
                centerCardsXOffset = 15;
            }
        }
        
        
        // ***bot plays card***
        int cardIndexToPlay = 0;
        if (cardsInCenter.size() > 0) {
            for (int i = 1; i < bot.getCardsInHandSize(); i++) 
                if (bot.getCardInHand(i).getRankOfCard() == cardsInCenter.get(cardsInCenter.size() - 1).getRankOfCard()) {
                    cardIndexToPlay = i;
            }
        }
        //find match between card being played and card in original hand
        for (int i = 0; i < 4; i++) {
            if (bot.getCardInHand(cardIndexToPlay) == botInitialCardsInHand.get(i))
                botCardPlacement[i] = false;
        }
        
        Card botPlayedCard = bot.getCardInHand(cardIndexToPlay);
        ImageView botTempCardImage = new ImageView();
        botTempCardImage = botPlayedCard.getCardImage();
        cardsInCenter.add(botPlayedCard);
        bot.removeCardFromHand(botPlayedCard);
        botTempCardImage.setX(centerCardsXOffset);
        botTempCardImage.setY(60);
        centerCardsXOffset += 6;
        playAreaPane.getChildren().add(botTempCardImage);
        // redo placement of cards in bot's card pane
        int botCardXOffset = 0;
        botCardPane.getChildren().clear();
        for (int i = 0; i < 4; i++) {
            ImageView botCardImage = new ImageView("cards/b1fv.png");
            botCardImage.setX(botCardXOffset);
            botCardXOffset += 80;
            if (botCardPlacement[i])
                botCardPane.getChildren().add(botCardImage);
        }
        // if match, remove cards from center and add to bot's captured piles
        if (cardsInCenter.size() > 1) {
            if (cardsInCenter.get(cardsInCenter.size() - 1).getRankOfCard() == 
                    cardsInCenter.get(cardsInCenter.size() - 2).getRankOfCard()) {
                if (cardsInCenter.size() == 2) {
                    bot.addAllToPistiCaptured(cardsInCenter);
                    for (int i = 0; i < 2; i++) {
                        cardsInCenter.get(i).getCardImage().setX(botPistiXOffset);
                        cardsInCenter.get(i).getCardImage().setY(0);
                        botPistisPane.getChildren().add(cardsInCenter.get(i).getCardImage());
                        if (botPistiXOffset < 12)
                            botPistiXOffset += 2;
                    }
                }
                else {
                    bot.addAllToCardsCaptured(cardsInCenter);
                    for (int i = 0; i < cardsInCenter.size(); i++) {
                        cardsInCenter.get(i).getCardImage().setX(botRegCaptureXOffset);
                        cardsInCenter.get(i).getCardImage().setY(0);
                        botRegularCardsPane.getChildren().add(cardsInCenter.get(i).getCardImage());
                        if (botRegCaptureXOffset < 12)
                            botRegCaptureXOffset += 2;
                    }
                }
                cardsInCenter.clear();
                playAreaPane.getChildren().clear();
                if (deck.getNumCardsLeft() > 0)
                    playAreaPane.getChildren().add(deckImageView);
                playAreaPane.getChildren().add(lbCardsInDeck);
                centerCardsXOffset = 15;
            }
        }
        
        // deal if players are out of cards
        if (bot.getCardsInHand().isEmpty()) {
            if (deck.getNumCardsLeft() > 0)
                dealCards();
        }
        
        // end game if no cards remain to play
        if (bot.getCardsInHand().isEmpty() && deck.getNumCardsLeft() == 0) {
            calculateScore(bot);
            calculateScore(user);
            // 3 bonus points for most cards captured
            if ((user.getPistiCapturedSize() + user.getCardsCapturedSize()) > 
                    (bot.getPistiCapturedSize() + bot.getCardsCapturedSize()))
                user.setScore(user.getScore() + 3);
            else
                bot.setScore(bot.getScore() + 3);
            endGame();
        }
    }
    
    public void calculateScore(Player player) {
        // add user's Pistis to their score
        for (int i = 0; i < player.getPistiCapturedSize(); i = i + 2) {
            if (player.getPistiCaptured().get(i).getRankOfCard() == 11)
                player.setScore(player.getScore() + 20);
            else
                player.setScore(player.getScore() + 10);
        }

        // add other captured cards to user's score
        for (int i = 0; i < player.getCardsCapturedSize(); i++) {
            // 1 point for A
            if (player.getCardsCaptured().get(i).getRankOfCard() == 1)
                player.setScore(player.getScore() + 1);
            // 1 point for 10, J, Q, or K
            if (player.getCardsCaptured().get(i).getRankOfCard() >= 10 &&
                    player.getCardsCaptured().get(i).getRankOfCard() <= 13)
                 player.setScore(player.getScore() + 1);
            // 2 additional points for 10 of diamonds
            if (player.getCardsCaptured().get(i).getRankOfCard() == 10 &&
                    player.getCardsCaptured().get(i).getSuitOfCard() == 2)
                player.setScore(player.getScore() + 2);
            // 2 points for 2 of clubs
            if (player.getCardsCaptured().get(i).getRankOfCard() == 2 &&
                    player.getCardsCaptured().get(i).getSuitOfCard() == 3)
                player.setScore(player.getScore() + 2);
        }
    }

    public void endGame() {
        System.out.println("your score: " + user.getScore());
        System.out.println("Opponent's score: " + bot.getScore());
        String yourScore = "Your Score: " + user.getScore();
        lbYourScore = new Label(yourScore);
        String botScore = "Opponent's score: " + bot.getScore();
        lbBotScore = new Label(botScore);
        lbYourScore.setStyle("-fx-font: 18 arial");
        lbBotScore.setStyle("-fx-font: 18 arial");
        if (user.getScore() > bot.getScore()) {
            lbWinner.setText("YOU WIN!");
            lbWinner.setLayoutX(70);
        }
        else {
            lbWinner.setText("YOU LOSE");
            lbWinner.setLayoutX(60);
        }
        lbYourScore.setStyle("-fx-font: 20 arial");
        lbYourScore.setLayoutX(70);
        lbYourScore.setLayoutY(160);
        lbBotScore.setStyle("-fx-font: 20 arial");
        lbBotScore.setLayoutX(70);
        lbBotScore.setLayoutY(30);
        lbWinner.setStyle("-fx-font: 60 arial");
        lbWinner.setTextFill(Color.GREEN);
        lbWinner.setLayoutY(75);
        playAreaPane.getChildren().clear();
        playAreaPane.getChildren().addAll(lbWinner, lbYourScore, lbBotScore);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
