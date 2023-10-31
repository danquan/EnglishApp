package views;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.ImageCursor;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class DictionaryApplication extends Application {
    public static void main(String[] args) throws Exception {
        TestAPI.SetupDict();

        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Parent root = File_loader.getInstance().fxml_mainPanel();

        if (root == null)
            System.out.println("null");

        setIcon(stage);

        Scene scene = new Scene(root);
        // scene.getStylesheets().add(File_loader.getInstance().get_css("main_dictionary_tab.css"));
        setCursor(scene);

        stage.setTitle("hi");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // Set app icon
    private void setIcon(Stage stage) {
        stage.getIcons().add(new Image("front_end/graphic/icons/icon.png"));
    }

    // Load your custom cursor image
    private void setCursor(Scene scene) {
        Image cursorImage = new Image("front_end/graphic/icons/download.gif");
        ImageCursor cursor = new ImageCursor(cursorImage,
                cursorImage.getWidth() / 2,
                cursorImage.getHeight() / 2);
        ImageCursor.getBestSize(cursorImage.getWidth() * 2, cursorImage.getHeight() * 2);
        scene.setCursor(cursor);
    }
}
