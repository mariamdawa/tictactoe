/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictac.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import tictac.Database;
import tictac.services.Dialogue;

/**
 * FXML Controller class
 *
 * @author bloodymary
 */
public class ReplayController implements Initializable {

    @FXML
    private Label player1;
    @FXML
    private Label player2;
    @FXML
    public ImageView B00;
    @FXML
    private ImageView B01;
    @FXML
    private ImageView B02;
    @FXML
    private ImageView B10;
    @FXML
    private ImageView B11;
    @FXML
    private ImageView B12;
    @FXML
    private ImageView B20;
    @FXML
    private ImageView B21;
    @FXML
    private ImageView B22;

    int id;
Database d1;
    public void initData(String id) {

//         this.id=Integer.parseInt(id.trim());
        this.id = Integer.parseInt(id.trim());
        d1 = new Database();
        String[] steps = d1.selectSteps(this.id).split(",");
        replay(steps);

    }

    public void initialize(URL location, ResourceBundle recourses) {

//         String steps=d1.selectSteps(id);//retrieve steps according to specific id
//         System.out.println(steps);
    }

    public void replay(String[] steps) {
        B00.setImage(null);
        B01.setImage(null);
        B02.setImage(null);
        B10.setImage(null);
        B11.setImage(null);
        B12.setImage(null);
        B20.setImage(null);
        B21.setImage(null);
        B22.setImage(null);

        Thread th = new Thread(new Runnable() {
            Image image_X = new Image(this.getClass().getResourceAsStream("../images/Ximg.PNG"));
            Image image_O = new Image(this.getClass().getResourceAsStream("../images/Oimg.PNG"));
            Sounds sound = new Sounds();
            int count = 0;

            public void run() {
                for (String i : steps) {

                    Image temp;

                    if (count % 2 == 0) {
                        temp = image_X;
                        sound.playSound1();
                    } else {
                        temp = image_O;
                        sound.playSound2();
                    }
                    switch (i) {
                        case "B00":
                            B00.setImage(temp);
                            break;
                        case "B01":
                            B01.setImage(temp);
                            break;
                        case "B02":
                            B02.setImage(temp);
                            break;
                        case "B10":
                            B10.setImage(temp);
                            break;
                        case "B11":
                            B11.setImage(temp);
                            break;
                        case "B12":
                            B12.setImage(temp);
                            break;
                        case "B20":
                            B20.setImage(temp);
                            break;
                        case "B21":
                            B21.setImage(temp);
                            break;
                        case "B22":
                            B22.setImage(temp);
                            break;
                    }
                    count++;

                    try {
                        Thread.sleep(750);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                Window window = B00.getScene().getWindow();
                Platform.runLater(() -> {
                            Dialogue dialog = new Dialogue(window);
                            dialog.setlabeltext("The Winner is: " + d1.selectWinner(id));
                            dialog.reset().setText("Close");
                            dialog.replay().setText("Replay Again");
                            dialog.reset().setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    
                                    dialog.getStage().close();
                                    window.hide();
                                }
                            });
                            dialog.replay().setOnAction((ev) -> {
                                replay(steps);
                                dialog.getStage().close();
                            });

                        });

            }
        });
        th.start();
    }
}
