/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictac;

//import tictac.controller.Controller;
import java.awt.event.ActionEvent;
import tictac.controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;

/**
 *
 * @author M
 */
public class TicTac extends Application {

    Stage window;
    Scene startScene, twoPlayersScene, computerScene;

    @FXML
    public void login(javafx.event.ActionEvent e) throws Exception {
        Button btn = (Button) e.getSource();
        String id = btn.getId();
        Parent root = FXMLLoader.load(getClass().getResource("view/startGUI.fxml"));
        //Stage primaryStage = (Stage)((root)e.getSource()).getScene().getWindow();
        // primaryStage.setScene(firstScene);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //view/View.fxml
        // Parent root= FXMLLoader.load(getClass().getResource("view/GUI.fxml"));
        Parent start = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        //Controller root=new Controller();
        // twoPlayersScene = new Scene(root, 530, 400);

        window = primaryStage;
        // startScene= new Scene(start,530,400);
        startScene = new Scene(start, 523, 390);
        window.setResizable(false);
        //twoPlayersScene= new Scene(root,530,400);
        // window.show();
        // primaryStage.setTitle("Tic Tac Toe");
        /*
        Label label1=new Label("Welcome to first scene");
        Button button1= new Button("Two players:");
        Button button2= new Button(" plays With computer:");
       start.getChildrenUnmodifiable().addAll(label1, button1);
        startScene= new Scene(start,200,200);
     
        button1.setOnAction(e -> {
                window.setScene(twoPlayersScene);
               // window.show();
        });
         */
        //twoPlayersScene.
        window.setScene(startScene);
        window.show();

        window.setOnCloseRequest((WindowEvent event) -> {
            Alert alert = new Alert(Alert.AlertType.NONE);
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);

          ButtonType NO = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.setContentText("Are You Sure You Want to leave");
             alert.setX(window.getX()+100);
             alert.setY(window.getY()+100);
            alert.getButtonTypes().add(yes);
            alert.getButtonTypes().add(NO);
            alert.showAndWait().ifPresent((response) -> {
                if (response == yes) {

                    System.exit(0);
                }
              
                else
                {
                if (response == NO) {

                    event.consume();
                }
                }
            });

        });
        // primaryStage.setTitle("Tic Tac Toe");
        //primaryStage.setScene(scene);
        //  primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Database d1=new Database();
        System.out.println(d1.selectSteps(13));
        System.out.println(d1.selectPlayers(13));
        System.out.println(d1.selectWinner(13));
        System.out.println(d1.selectPlayWithWho(13));
        System.out.println(d1.selectGamesId("computer"));
        System.out.println(d1.selectAllGamesId());
        System.out.println(d1.selectGamesPlayers("computer"));
        System.out.println(d1.selectAllGamesPlayers());*/
        launch(args);
    }

}
