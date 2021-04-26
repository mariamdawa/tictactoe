/*+.
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictac.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import tictac.Database;
import tictac.model.ComputerModel;
import tictac.model.EasyModel;
import tictac.services.Dialogue;

public class EasyComputer {

    private EasyModel model = new EasyModel();
    private Sounds sounds = new Sounds();
    private int player_X = 1;
    private int player_O = 2;
    private Queue<String> gameReplay = new LinkedList<String>();//feha el steps w anta kasabt wala la2
    File file;
   Queue<String> qS = new LinkedList<>();
   
    //Queue<String> qP = new LinkedList<>();
    
    LoginController l=new LoginController();
    String userName=l.getUserName();
    String playerStr = "x-"+userName+",o-computer";
    String steps="";
    String winner="";
//ids of my buttons:
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
@FXML
    private Label labelo ;
    @FXML
    private Label labelx ;
 /*
  @FXML
 private Label turn;
 @FXML 
 private Button R1;
*/
 @FXML
    void initialize(){
        labelo.setText("Computer - O");
    labelx.setText(userName+ " - X");
    
    }


    @FXML
    public void reset() {
        gameReplay.clear();
        steps="";
        qS.clear();
        model.resetNumbClicked();
        model.resetClicks();
        model.restBoard();
        model.setWinner(false);
        model.setTurn(true);
        model.setWinnerNumber("No Winner");
        // zeros();

        System.err.println("reset");
        B00.setImage(null);
        B01.setImage(null);
        B02.setImage(null);
        B10.setImage(null);
        B11.setImage(null);
        B12.setImage(null);
        B20.setImage(null);
        B21.setImage(null);
        B22.setImage(null);

    }

    public void replay() {
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
            Queue<String> steps = new LinkedList<>(gameReplay);
            Image image_X = new Image(this.getClass().getResourceAsStream("../images/Ximg.PNG"));
            Image image_O = new Image(this.getClass().getResourceAsStream("../images/Oimg.PNG"));
            Sounds sound = new Sounds();
            int count = 0;

            public void run() {
                System.out.println(steps);
                System.out.println(gameReplay);
                String polled = steps.poll();
                Image temp;
                while (polled != null) { //hena by3mel el replay
                    System.out.println(polled);
                    if (polled.compareToIgnoreCase("You Won") == 0
                            || polled.compareToIgnoreCase("You Lost") == 0
                            || polled.compareToIgnoreCase("It's A Tie") == 0) {
                        Window window = B00.getScene().getWindow();

                        Platform.runLater(() -> {
                            Dialogue dialog = new Dialogue(window );
                            if (model.getClicks() == 0 && model.playable()) {
                                dialog.setlabeltext(" ");
                            } else {

                                dialog.setlabeltext(model.getWinnerNumber());
                            }
                            dialog.reset().setText("New Game");
                            dialog.replay().setText("Replay Again");
                            dialog.reset().setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    reset();
                                    dialog.getStage().close();
                                }
                            });
                            dialog.replay().setOnAction((ev) -> {
                                replay();
                                dialog.getStage().close();
                            });

                        });
                    } else {
                        if (count % 2 == 0) {
                            temp = image_X;
                            sound.playSound1();
                        } else {
                            temp = image_O;
                            sound.playSound2();
                        }
                        switch (polled) {
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

                    }
                    polled = steps.poll();
                    count++;
                    try {
                        Thread.sleep(750);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }

            }
        });
        th.start();
    }

    private int counts = 9;

//KeyEvent event
    @FXML
    public void clicked(MouseEvent e) throws FileNotFoundException, InterruptedException {
        //  model.getTurn() 
        if (model.canPlay() && model.getTurn()) {
            //That is mean this is turn of x:
            Xplay(((ImageView) e.getSource()).getId());
            //    noOnewinner();
            //   winnerdialog();
            if (model.canPlay() && !model.getTurn()) {
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                    @Override
                    public void run() {
                        try {
                            computerTurn();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(ComputerController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }, 1500);

            }

        } else {
            System.out.println("It's Not Your turn");
        }
       
    }
    //B00

    public void Xplay(String idClicked) throws FileNotFoundException {
        Image image_X = new Image(this.getClass().getResourceAsStream("../images/Ximg.PNG"));
        int index1 = Character.getNumericValue(idClicked.charAt(1));
        int index2 = Character.getNumericValue(idClicked.charAt(2));

        if (model.allow(index1, index2)) {
            sounds.playSound1();
            switch (idClicked) {
                case "B00":
                    B00.setImage(image_X);
                    model.setTurn(false);
                    break;
                case "B01":
                    B01.setImage(image_X);
                    
                    
                    model.setTurn(false);
                    break;
                case "B02":
                    B02.setImage(image_X);
                    
                    
                    model.setTurn(false);
                    break;
                case "B10":
                    B10.setImage(image_X);
                    
                    
                    model.setTurn(false);
                    break;
                case "B11":
                    B11.setImage(image_X);
                   
                     
                    model.setTurn(false);
                    break;
                case "B12":
                    B12.setImage(image_X);
                    
                     
                    model.setTurn(false);
                    break;
                case "B20":
                    B20.setImage(image_X);
                    
                    
                    model.setTurn(false);
                    break;
                case "B21":
                    B21.setImage(image_X);
                    
                    
                    model.setTurn(false);
                    break;
                case "B22":
                    B22.setImage(image_X);
                   
                    
                    model.setTurn(false);
                    break;
            }

            gameReplay.add(idClicked);
            qS.add(idClicked);
           
            
           
            model.checkWinner();
            Dialoge();

            System.out.println("player Winner Dailog");
        }
    }

    public void computerTurn() throws FileNotFoundException {
        if (model.playable()) {
            Image image_O = new Image(this.getClass().getResourceAsStream("../images/Oimg.PNG"));
            String computerMove = model.computerPlays();
            switch (computerMove) {
                case "B00":
                    B00.setImage(image_O);
                    
                    
                    break;
                case "B01":
                    B01.setImage(image_O);
                    
                   
                    break;
                case "B02":
                    B02.setImage(image_O);
                   
                    
                    break;
                case "B10":
                    B10.setImage(image_O);
                    
                   
                    break;
                case "B11":
                    B11.setImage(image_O);
                    
                    
                    break;
                case "B12":
                    B12.setImage(image_O);
                    
                   
                    break;
                case "B20":
                    B20.setImage(image_O);
                   
                    
                    break;
                case "B21":
                    B21.setImage(image_O);
                    
                    
                    break;
                case "B22":
                    B22.setImage(image_O);
                    
                    
                    break;
                default:
                    System.out.println("Can not play");
                    break;
            }
            gameReplay.add(computerMove);//hena 3amalna add ll steps
            qS.add(computerMove);
           
            
            sounds.playSound2();
            model.checkWinner();
            
            Dialoge();
            model.setTurn(true);

            System.out.println("Computer Winner Dailog");

        }
    // System.out.println("stepsss"+step);

    }

    public void Dialoge() {

        if (model.getClicks() == 0 && model.playable() || (!model.playable())) {
            
              
            Window window = B00.getScene().getWindow();
            Platform.runLater(() -> {
                Dialogue dialog = new Dialogue(window , model.getWinnerNumber());
                Database d1= new Database();
                if (model.getClicks() == 0 && model.playable()) {//hena bet check men ele kasab w 7atetoh fe gamereplay queue
                    gameReplay.add("It's a Tie");
                     for (String i : qS) {
                        
                            steps += i + ",";
                        
                         } 
                        
                         
                          dialog.setlabeltext("");
                           winner="It's a Tie";
                          d1.insert(steps,playerStr,winner,"Computer");
               
                }
                else {
     
                    gameReplay.add(model.getWinnerNumber());
                    if (model.getWinnerNumber().compareToIgnoreCase("You Won") == 0) {
                        winner=userName;
                    }
                    else
                    {
                        winner="computer";
                    }
                     for (String i : qS) {
                       
                            steps += i + ",";
                        
                         } 
                    
                    dialog.setlabeltext(model.getWinnerNumber());
                    d1.insert(steps,playerStr,winner,"Computer");
                    
                }
               
                dialog.reset().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        reset();
                        dialog.getMediaPlayer().stop();
                        dialog.getStage().close();
                    
                    }
                });
                dialog.replay().setOnAction((ev) -> {
                    replay();
                    dialog.getMediaPlayer().stop();
                    dialog.getStage().close();
                });

            });
        }

    }

    @FXML
    public void returnToStart(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/startGUI.fxml"));
        Button btn = (Button) e.getSource();
        String id = btn.getId();
        Stage window = (Stage) btn.getScene().getWindow();
        window.setScene(new Scene(root, 530, 400));
    }

}
