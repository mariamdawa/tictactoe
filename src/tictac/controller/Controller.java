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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.Window;
import tictac.Database;
import tictac.model.FriendModel;
import tictac.services.Dialogue;

public class Controller {

    private FriendModel model = new FriendModel();

    private final int player_X = 1;
    private final int player_O = 2;
    
    private Queue<String> gameReplay=new LinkedList<String>();
     Queue<String> qS = new LinkedList<>();
   
    //Queue<String> qP = new LinkedList<>();
    
    LoginController l=new LoginController();
    StartController s=new StartController();
    String userName=l.getUserName();
    String friendName=s.FriendName;
    String playerStr = "x-"+userName+",o"+friendName;
    String steps="";
    String winner="";

    File file;
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
    private AnchorPane Anchor;
@FXML
    private Label labelo ;
    @FXML
    private Label labelx ;

    @FXML
    void initialize(){
        labelo.setText(friendName+ " - O");
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
        model.setWinnerNumber("No Winner");
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
    
          public void replay(){
            B00.setImage(null);
            B01.setImage(null);
            B02.setImage(null);
            B10.setImage(null);
            B11.setImage(null);
            B12.setImage(null);
            B20.setImage(null);
            B21.setImage(null);
            B22.setImage(null);
            
            Thread th=new Thread(new Runnable(){
                Queue<String>steps=new LinkedList<>(gameReplay);
                Image image_X = new Image(this.getClass().getResourceAsStream("../images/Ximg.PNG"));
                Image image_O = new Image(this.getClass().getResourceAsStream("../images/Oimg.PNG"));
                Sounds sound=new Sounds();
                int count=0;
                public void run(){
                    System.out.println(steps);
                    System.out.println(gameReplay);
                    String polled=steps.poll();
                    Image temp;
                    while(polled!=null){
                        System.out.println(polled);
                        if(polled.compareToIgnoreCase("Player 1")==0||
                            polled.compareToIgnoreCase("Player 2")==0||
                            polled.compareToIgnoreCase("It's A Tie")==0){
                                Window window = B00.getScene().getWindow();
                            
                                Platform.runLater(() -> {
                                    Dialogue  dialog = new Dialogue(window);
                                    if(model.getClicks()==0 && model.playable())
                                    {
                                    dialog.setlabeltext("");
                                    }
                                    else
                                    {

                                         dialog.setlabeltext(model.getWinnerNumber()+" Won");
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
                                     dialog.replay().setOnAction((ev)->{
                                         replay();
                                         
                                         dialog.getStage().close();
                                     });

                                });
                        }
                        else{
                            if(count%2==0){
                                temp=image_X;
                                sound.playSound1();
                            }
                            else{
                                temp=image_O;
                                sound.playSound2();
                            }
                            switch(polled){
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
                        polled=steps.poll();
                        count++;
                        try{
                        Thread.sleep(750);
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
                        
                    }
                    
                }
            });
            th.start();
     }
   

//KeyEvent event
    @FXML
    public void clicked(MouseEvent e) throws FileNotFoundException {

        //Add the full path of 2 images
        // String Path ="E:\\iti\\java\\final project\\TicTac\\src\\tictac\\images\\Oimg.PNG";
        // InputStream stream_O = new FileInputStream(Path);
        //InputStream stream_X = new FileInputStream("E:\\iti\\java\\final project\\TicTac\\src\\tictac\\images\\Ximg.PNG");
        //Image image_O = new Image(stream_O);
        //Image image_X = new Image(stream_X);
        Image image_O = new Image(this.getClass().getResourceAsStream("../images/Oimg.PNG"));
        Image image_X = new Image(this.getClass().getResourceAsStream("../images/Ximg.PNG"));
        if (model.playable()) {
            String move=((ImageView) e.getSource()).getId();

            switch (move) {

                case "B00":
                    //        System.out.println("Id"+ ((ImageView)e.getSource()).getId());

                    if (model.allow(0, 0)) {
                        switch (model.executeClick(0, 0)) {
                            case "X":
                                B00.setImage(image_X);
                                playSound1();
                                break;
                            case "O":
                                B00.setImage(image_O);
                                playSound2();
                                break;
                        }

                        model.checkWinner();
                    } else {
                        System.out.println("Don' t allow clicked here");
                    }
                    B00.setStyle("-fx-font: 30 arial ;");
                    break;
                case "B01":

                    if (model.allow(0, 1)) {
                        switch (model.executeClick(0, 1)) {
                            case "X":
                                B01.setImage(image_X);
                                playSound1();
                                break;
                            case "O":
                                B01.setImage(image_O);
                                playSound2();
                                break;

                        }

                        model.checkWinner();
                    }
                    break;
                case "B02":

                    if (model.allow(0, 2)) {
                        switch (model.executeClick(0, 2)) {
                            case "X":
                                B02.setImage(image_X);
                                playSound1();
                                break;
                            case "O":
                                B02.setImage(image_O);
                                playSound2();
                                break;
                        }

                        model.checkWinner();
                    }
                    break;
                case "B10":
                    if (model.allow(1, 0)) {
                        switch (model.executeClick(1, 0)) {
                            case "X":
                                B10.setImage(image_X);
                                playSound1();
                                break;
                            case "O":
                                B10.setImage(image_O);
                                playSound2();
                                break;
                        }

                        model.checkWinner();
                    }
                    break;
                case "B11":
                    if (model.allow(1, 1)) {
                        switch (model.executeClick(1, 1)) {
                            case "X":
                                B11.setImage(image_X);
                                playSound1();
                                break;
                            case "O":
                                B11.setImage(image_O);
                                playSound2();
                                break;
                        }

                        model.checkWinner();
                    }
                    break;
                case "B12":
                    if (model.allow(1, 2)) {
                        switch (model.executeClick(1, 2)) {
                            case "X":
                                B12.setImage(image_X);
                                playSound1();
                                break;
                            case "O":
                                B12.setImage(image_O);
                                playSound2();
                                break;
                        }

                        model.checkWinner();
                    }
                    break;
                case "B20":
                    if (model.allow(2, 0)) {
                        switch (model.executeClick(2, 0)) {
                            case "X":
                                B20.setImage(image_X);
                                playSound1();
                                break;
                            case "O":
                                B20.setImage(image_O);
                                playSound2();
                                break;
                        }

                        model.checkWinner();
                    }
                    break;
                case "B21":
                    if (model.allow(2, 1)) {
                        switch (model.executeClick(2, 1)) {
                            case "X":
                                B21.setImage(image_X);
                                playSound1();
                                break;
                            case "O":
                                B21.setImage(image_O);
                                playSound2();
                                break;
                        }

                        model.checkWinner();
                    }
                    break;
                case "B22":
                    if (model.allow(2, 2)) {
                        switch (model.executeClick(2, 2)) {
                            case "X":
                                B22.setImage(image_X);
                                playSound1();
                                break;
                            case "O":
                                B22.setImage(image_O);
                                playSound2();
                                break;
                        }

                        model.checkWinner();
                    }
                    break;
            }
            gameReplay.add(move);
            qS.add(move);
        }
        Dialoge();
        //winnerdialog();

    }

    public void Dialoge() {
Window window = B00.getScene().getWindow();
        if ((model.getClicks() == 0 && model.playable()) || (!model.playable())) {
         
            Platform.runLater(() -> {
                Dialogue dialog = new Dialogue(window ,model.getWinnerNumber());
                 Database d1=new Database();
                if ((model.getClicks() == 0 && model.playable())) {
                    dialog.setlabeltext("");
                    gameReplay.add("It's a Tie");
                    gameReplay.add("No Winner");
                      for (String i : qS) {
                         {
                            steps += i + ",";
                        }

                }   
                    winner="It's a Tie";
                    d1.insert(steps,playerStr,winner,"friend");
                } else {
                    if (!model.playable()) {

                        dialog.setlabeltext(model.getWinnerNumber() + " is the Winner");
                        gameReplay.add(model.getWinnerNumber());
                        if(model.getWinnerNumber().compareToIgnoreCase("Player 1")==0)
                        {
                            winner=userName;
                        }
                        else{
                            winner="friend";
                        }
                         for (String i : qS) {
                         {
                            steps += i + ",";
                        }

                }   
                    }
                    System.out.println("steps"+steps);
                    
                     d1.insert(steps, playerStr, winner, "friend");

                }

                dialog.reset().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        reset();
                        dialog.getMediaPlayer().stop();
                        dialog.getStage().close();
                    }
                });
                dialog.replay().setOnAction((ev)->{
                    replay();
                    dialog.getMediaPlayer().stop();
                    dialog.getStage().close();
                });
            }
            );

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

    public void playSound1() {

        //Instantiating Media class  
        Media media = new Media(getClass().getResource("../sounds/player1.mp3").toExternalForm());

        //Instantiating MediaPlayer class   
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //by setting this property to true, the audio will be played   
        mediaPlayer.setAutoPlay(true);
    }

    public void playSound2() {

        Media media = new Media(getClass().getResource("../sounds/player2.mp3").toExternalForm());

        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //by setting this property to true, the audio will be played   
        mediaPlayer.setAutoPlay(true);
    }

}
