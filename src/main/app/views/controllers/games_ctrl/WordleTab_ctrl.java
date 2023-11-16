package views.controllers.games_ctrl;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import views.animations.GameAnimations;
import views.controllers.mainPanel_ctrl;
import views.games.GameNotification;
import views.games.MainWordle;

import java.util.ArrayList;

public class WordleTab_ctrl extends Game_ctrl {
    public static mainPanel_ctrl mainPanelCtrl = null;

    private final MainWordle mainWordle = MainWordle.getInstance();

    @FXML
    public AnchorPane game_sc;
    @FXML
    public GridPane gridPane;
    @FXML
    public GridPane keyboardRow1;
    public GridPane keyboardRow2;
    public GridPane keyboardRow3;
    public GridPane[] keyboardRows;
    @FXML
    public ImageView helpButton;
    @FXML
    public HBox titleHBox;
    @FXML
    public ImageView restartButton;
    @FXML
    public ImageView exitButton;
    @FXML
    public BorderPane notificationPane;

    // Word lists
    public static final ArrayList<String> winningWords = new ArrayList<>();

    @FXML
    void initialize() {
        initializeWordLists();
        createUI();
        mainWordle.wordleTab_ctrl = this;
        GameNotification.wordleTab_ctrl = this;
        setTooltip(helpButton, "Instructions");
        setTooltip(restartButton, "Restart");
        setTooltip(exitButton, "Exit");
        game_sc.setOnMouseClicked(e -> {
            if (notificationPane.isVisible() &&
                    inHierarchy(e.getPickResult().getIntersectedNode(), notificationPane) &&
                    inHierarchy(e.getPickResult().getIntersectedNode(), helpButton)) {
                notificationPane.setVisible(false);
                notificationPane.setTranslateX(0);
                notificationPane.setTranslateY(0);
                notificationPane.setOnMousePressed(null);
                notificationPane.setOnMouseDragged(null);
                gridRequestFocus();
            }
        });
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        mainWordle.onKeyPressed(gridPane, keyboardRows, keyEvent);
    }

    @FXML
    public void showHelp() {
        if (notificationPane.isVisible()) {
            notificationPane.setVisible(false);
            gridRequestFocus();
        } else {
            GameNotification.instructionWordle(notificationPane);
            notificationPane.setVisible(true);
        }
    }

    @FXML
    public void restart() {
        RotateTransition rotateTransition = GameAnimations.rotateTrans(restartButton, 0, 360 * 3);
        rotateTransition.setOnFinished(ae -> {
            showStartGame();
            mainWordle.resetGame(gridPane, keyboardRows);
        });
        rotateTransition.play();
    }

    @Override
    public void showStartGame() {
        SequentialTransition sequentialTransition = new SequentialTransition();
        ParallelTransition parallelTransition1 = new ParallelTransition();
        for (Node node : gridPane.getChildren()) {
            if (node != null) {
                parallelTransition1.getChildren().add(GameAnimations.scaleTrans(node, 0, 1, 1000));
            }
        }
        sequentialTransition.getChildren().add(parallelTransition1);
        sequentialTransition.play();
    }

    @FXML
    public void exitGame() {
        mainPanelCtrl.mainPane.setCenter(mainPanelCtrl.game_tab);
    }

    private void createUI() {
        // Create Grid
        mainWordle.createGrid(gridPane);

        // Create Keyboard
        keyboardRows = new GridPane[3];
        keyboardRows[0] = keyboardRow1;
        keyboardRows[1] = keyboardRow2;
        keyboardRows[2] = keyboardRow3;
        mainWordle.createKeyBoard(keyboardRows, gridPane);

        // Create Game Title
        mainWordle.createGameTitle(titleHBox);
    }

    public void gridRequestFocus() {
        gridPane.requestFocus();
    }

    public static void showWordNotFound() {
        GameNotification.showNotification(MainWordle.getInstance().wordleTab_ctrl.notificationPane, "INVALID WORD!");
    }

    private void initializeWordLists() {
        initWords("/game/winning-words.txt", winningWords);
        mainWordle.getRandomWord();
    }

    public void showEndGameWindow(boolean guessed, String winningWord) {
        GameNotification.endGameNotification(guessed, winningWord, notificationPane, this,
                new String[]{"YOU WON!", "THE WINNING WORD WAS:"});
    }
}