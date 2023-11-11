package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import views.TestAPI;

public class GoogleTab_ctrl {
    @FXML
    private Label lang_label1;

    @FXML
    private Label lang_label2;

    @FXML
    private Button speak_button1;

    @FXML
    private Button speak_button2;

    @FXML
    private Button swap_lang_button;

    @FXML
    private TextArea text1;

    @FXML
    private TextArea text2;

    @FXML
    private Button translate_button;

    private String lang1 = "en";
    private String lang2 = "vi";

    @FXML
    void initialize() {
        //assert text1 != null : "fx:id=\"text1\" was not injected: check your FXML file 'google_tab.fxml'.";
        //assert text2 != null : "fx:id=\"text2\" was not injected: check your FXML file 'google_tab.fxml'.";
        //assert translate_button != null : "fx:id=\"translate_button\" was not injected: check your FXML file 'google_tab.fxml'.";

        translate_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                doTranslate();
            }
        });
        text2.setEditable(false);
        setLang_label();

        swap_lang_button.setOnAction(e -> {
            String temp = lang1;
            lang1 = lang2;
            lang2 = temp;
            setLang_label();
        });
        speak_button1.setOnAction(e -> {
            speak_button1.setDisable(true);
            TestAPI.SpeakAPI(text1.getText(), lang1);
            speak_button1.setDisable(false);
        });
        speak_button2.setOnAction(e -> {
            speak_button2.setDisable(true);
            TestAPI.SpeakAPI(text2.getText(), lang2);
            speak_button2.setDisable(false);
        });
    }

    private void doTranslate() {
        String transed = TestAPI.TranslateAPI(text1.getText(), lang1, lang2);
        transed.replaceAll("\n", System.getProperty("line.separator"));
        text2.setText(transed);
    }

    private void setLang_label() {
        if (lang1.equals("vi")) lang_label1.setText("Tiếng Việt");
        if (lang1.equals("en")) lang_label1.setText("English");
        if (lang2.equals("vi")) lang_label2.setText("Tiếng Việt");
        if (lang2.equals("en")) lang_label2.setText("English");
    }
}
