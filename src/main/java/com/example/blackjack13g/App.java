package com.example.blackjack13g;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("StartScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());


        stage.setTitle("Blackjack by 13G");
        stage.setScene(scene);
        stage.setFullScreen(true);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}