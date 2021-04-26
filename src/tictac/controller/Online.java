/*+.
 * To change this license header, choose License Headers in Project Propersss.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictac.controller;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import tictac.Database;
import tictac.model.onlineModel;
import tictac.services.Dialogue;
import tictac.services.GameService;

public class Online extends Thread {

    private onlineModel model = new onlineModel();

    private Queue<String> gameReplay = new LinkedList<String>();

    GameService gameService;

    private final int player_X = 1;
    private final int player_O = 2;
    int clicks = 0;

    File file;
    String replyMsg1;
    String firstMsg;
    boolean yourTurn;
    LoginController l = new LoginController();
    String userName = l.getUserName();
    Queue<String> qS = new LinkedList<>();
    Queue<String> players = new LinkedList<>();
    String player = "";
    String playerStr = "";
    String steps = "";
    String steps1 = "";
    String winner = "";
    String opponent;
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
    private Text tx;
     @FXML
    private Label label;
    @FXML
    void initialize(){
        
    label.setText(userName);
    
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    private Sounds sounds = new Sounds();

    @Override
    public void run() {
        close();
        model.setWhoWin(gameService.playerNumber);

        try {
            if (gameService.yourTurn && model.isPlay()) {
                tx.setText("Your Turn");
            }
            String player = gameService.playerNumber + "-" + userName;
            System.out.println(player);
            gameService.ps = new PrintStream(gameService.mySocket.getOutputStream());
            gameService.ps.println(player);
            gameService.dis = new DataInputStream(gameService.mySocket.getInputStream());
            opponent = gameService.dis.readLine();
            System.out.println(player);
            players.add(player);
            players.add(opponent);
            System.out.println(opponent);

        } catch (IOException ex) {
            Logger.getLogger(Online.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error Here");
        }

        while (true) {
            try {
                gameService.ps = new PrintStream(gameService.mySocket.getOutputStream());

                try {

                    replyMsg1 = gameService.dis.readLine();
                    System.err.println(replyMsg1);

//                String steps="";
                } catch (IOException IO) {
                    try {
                        System.err.println("Closed");
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Stage window = (Stage) B00.getScene().getWindow();
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.getButtonTypes().clear();
                                alert.setTitle("Your opponent has left");
                                alert.setHeaderText("Your opponent has left the session Please Try again Later");
                                alert.setX(window.getX() + 70);
                                alert.setY(window.getY() + 100);
                                ButtonType OK = new ButtonType("OK");
                                alert.getButtonTypes().add(OK);
                                alert.showAndWait().ifPresent((ButtonType response) -> {
                                    if (response == OK) {

                                        try {

                                            Parent root = FXMLLoader.load(getClass().getResource("../view/startGUI.fxml"));

                                            window.setScene(new Scene(root, 530, 400));

                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }

                                    }
                                });
                            }
                        });

                        gameService.mySocket.close();
                        gameService.ps.close();
                        gameService.dis.close();
                    } catch (IOException socket) {
                        socket.printStackTrace();
                    }

                    break;

                }

                if (!gameService.yourTurn && model.isPlay()) {

                    // String replyMsg []=replyMsg1.split("-");
                    System.out.println("Server reply is: " + replyMsg1);
                    setTheImage(replyMsg1);
                    gameReplay.add(replyMsg1);
                    qS.add(replyMsg1);
                    gameService.yourTurn = true;
                    tx.setText("Your Turn");

                    //setTheImage(replyMsg[0]);
                    //  gameReplay.add(replyMsg[0]);
                    //qP.add(replyMsg[1]);
                } else {
                    System.err.println("Not you turn");
                }
                if (replyMsg1.equalsIgnoreCase("reset")) {
                    model.setPlay(true);
                }

            } catch (IOException ex) {
                ex.printStackTrace();
                try {
                    gameService.mySocket.close();
                    gameService.ps.close();
                    gameService.dis.close();
                } catch (IOException socket) {
                    socket.printStackTrace();
                }

                break;
            }

        }
    }

    //KeyEvent event
    @FXML
    public void clicked(MouseEvent e) throws FileNotFoundException {

        //  if ((firstMsg.equals("x") && (clicks % 2 == 0)) || (firstMsg.equals("o") && (clicks % 2 != 0))) {
        if (gameService.yourTurn && model.isPlay()) {
            Image image_O = new Image(this.getClass().getResourceAsStream("../images/Oimg.PNG"));
            Image image_X = new Image(this.getClass().getResourceAsStream("../images/Ximg.PNG"));
            String move = ((ImageView) e.getSource()).getId();
            switch (move) {

                case "B00":
                    //        System.out.println("Id"+ ((ImageView)e.getSource()).getId());

                    if (model.allow(0, 0)) {
                        switch (model.executeClick(0, 0)) {
                            case "X":
                                B00.setImage(image_X);
                                //   playSound1();
                                break;
                            case "O":
                                B00.setImage(image_O);
                                //playSound2();
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
                                //playSound1();
                                break;
                            case "O":
                                B01.setImage(image_O);
                                // playSound2();
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
                                //playSound1();
                                break;
                            case "O":
                                B02.setImage(image_O);
                                //playSound2();
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
                                //playSound1();
                                break;
                            case "O":
                                B10.setImage(image_O);
                                //playSound2();
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
                                //playSound1();
                                break;
                            case "O":
                                B11.setImage(image_O);
                                //playSound2();
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
                                //playSound1();
                                break;
                            case "O":
                                B12.setImage(image_O);
                                //playSound2();
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
                                //playSound1();
                                break;
                            case "O":
                                B20.setImage(image_O);
                                //playSound2();
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
                                //playSound1();
                                break;
                            case "O":
                                B21.setImage(image_O);
                                //playSound2();
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
                                //playSound1();
                                break;
                            case "O":
                                B22.setImage(image_O);
                                //playSound2();
                                break;
                        }

                        model.checkWinner();
                    }
                    break;
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        gameService.ps = new PrintStream(gameService.mySocket.getOutputStream());
                        gameService.ps.println(move);
                        gameReplay.add(move);
                        qS.add(move);
                        System.err.println("I sent the message");
                        gameService.ps.println(((ImageView) e.getSource()).getId() + "-" + userName);
                    } catch (IOException image) {
                        image.printStackTrace();
                    }
                }
            });

            // gameService.ps.println(userName);
            gameService.yourTurn = false;
            tx.setText("Opponent Turn..");

        } else {
            tx.setText("wait for your oppnent");
            System.out.println("Opponent turn!!");
        }

//                    gameReplay.add(((ImageView)e.getSource()).getId());
//            } else {
//                System.out.println("Opponent turn!!");
//            }
        Dialog();
    }

    public void setTheImage(String str) {

        try {

            Image image_O = new Image(this.getClass().getResourceAsStream("../images/Oimg.PNG"));
            Image image_X = new Image(this.getClass().getResourceAsStream("../images/Ximg.PNG"));

            switch (str) {
                case "B00":
                    if (model.allow(0, 0)) {
                        switch (model.executeClick(0, 0)) {
                            case "X":
                                B00.setImage(image_X);
                                sounds.playSound1();
                                System.out.println("X pressed");
                                break;
                            case "O":
                                B00.setImage(image_O);
                                sounds.playSound2();
                                break;
                        }
                    }
                    break;
                case "B01":
                    if (model.allow(0, 1)) {
                        switch (model.executeClick(0, 1)) {
                            case "X":
                                B01.setImage(image_X);
                                sounds.playSound1();
                                System.out.println("X pressed");
                                break;
                            case "O":
                                B01.setImage(image_O);
                                sounds.playSound2();
                                break;
                        }
                    }
                    break;
                case "B02":
                    if (model.allow(0, 2)) {
                        switch (model.executeClick(0, 2)) {
                            case "X":
                                B02.setImage(image_X);
                                sounds.playSound1();
                                System.out.println("X pressed");
                                break;
                            case "O":
                                B02.setImage(image_O);
                                sounds.playSound2();
                                break;
                        }
                    }
                    break;
                case "B10":
                    if (model.allow(1, 0)) {
                        switch (model.executeClick(1, 0)) {
                            case "X":
                                B10.setImage(image_X);
                                sounds.playSound1();
                                break;
                            case "O":
                                B10.setImage(image_O);
                                sounds.playSound2();
                                break;
                        }
                    }
                    break;
                case "B11":
                    if (model.allow(1, 1)) {
                        switch (model.executeClick(1, 1)) {
                            case "X":
                                B11.setImage(image_X);
                                sounds.playSound1();
                                break;
                            case "O":
                                B11.setImage(image_O);
                                sounds.playSound2();
                                break;
                        }
                    }
                    break;
                case "B12":
                    if (model.allow(1, 2)) {
                        switch (model.executeClick(1, 2)) {
                            case "X":
                                B12.setImage(image_X);
                                sounds.playSound1();
                                break;
                            case "O":
                                B12.setImage(image_O);
                                sounds.playSound2();
                                break;
                        }
                    }
                    break;
                case "B20":
                    if (model.allow(2, 0)) {
                        switch (model.executeClick(2, 0)) {
                            case "X":
                                B20.setImage(image_X);
                                sounds.playSound1();
                                break;
                            case "O":
                                B20.setImage(image_O);
                                sounds.playSound2();
                                break;
                        }
                    }
                    break;
                case "B21":
                    if (model.allow(2, 1)) {
                        switch (model.executeClick(2, 1)) {
                            case "X":
                                B21.setImage(image_X);
                                sounds.playSound1();
                                break;
                            case "O":
                                B21.setImage(image_O);
                                sounds.playSound2();
                                break;
                        }
                    }
                    break;
                case "B22":
                    if (model.allow(2, 2)) {
                        switch (model.executeClick(2, 2)) {
                            case "X":
                                B22.setImage(image_X);
                                sounds.playSound1();
                                break;
                            case "O":
                                B22.setImage(image_O);
                                sounds.playSound2();
                                break;
                        }
                    }
                    break;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        model.checkWinner();

        Dialog();
//        if (clicks % 2 == 0) {
//            if (firstMsg.equals("o")) {
//                tx.setText("your turn");
//            } else {
//                tx.setText("Opponent turn");
//            }
//        } else {
//            if (firstMsg.equals("x")) {
//                tx.setText("your turn");
//            } else {
//                tx.setText("Opponent turn");
//            }
//        }

        clicks++;
    }

    @FXML

    public void reset() {
        model.resetNumbClicked();
        steps = "";
        steps1 = "";
        model.resetClicks();
        model.restBoard();
        model.setWinner(false);
        model.setWinnerNumber("No Winner");
        gameReplay.clear();
        qS.clear();
        clicks = 0;
        playerStr = "";
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

        if (gameService.playerNumber.equalsIgnoreCase("x")) {
            gameService.yourTurn = true;
            tx.setText("your turn");
        } else {
            gameService.yourTurn = false;
            tx.setText("Opponent turn");
        }

        gameService.ps.println("reset");
        System.out.println("sent reset");
//        model.setWhoWin(firstMsg);
//        if (firstMsg.compareToIgnoreCase("x") == 0) {
//            tx.setText("Your Turn");
//        } else {
//            tx.setText("Opponent Turn");
//        }

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
            String player[] = new String[2];
            String[] playerArr = players.toArray(player);
            Image image_X = new Image(this.getClass().getResourceAsStream("../images/Ximg.PNG"));
            Image image_O = new Image(this.getClass().getResourceAsStream("../images/Oimg.PNG"));
            Sounds sound = new Sounds();
            int count = 0;

            public void run() {
                System.out.println(steps);
                System.out.println(gameReplay);
                String polled = steps.poll();
                Image temp;
                while (polled != null) {
                    System.out.println(polled);
                    if (polled.charAt(0) == 'B') {
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

                    } else {
//                        if (polled.charAt(0) == 't') {

                            Window window = B00.getScene().getWindow();

                            Platform.runLater(() -> {
                                Dialogue dialog = new Dialogue(window );
                                if (model.getClicks() == 0 && model.playable()) {
                                    dialog.setlabeltext("");
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
//                        }

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

    /**
     * ********************************************done****************************************************************************
     */
    public void Dialog() {

        if (model.getClicks() == 0 && model.playable() || (!model.playable())) {
            model.setPlay(false);
          
            Window window = B00.getScene().getWindow();
            Platform.runLater(() -> {
                Dialogue dialog = new Dialogue(window , model.getWinnerNumber());
                  Database d1 = new Database();
                if (model.getClicks() == 0 && model.playable()) {
                    dialog.setlabeltext("");
                    gameReplay.add("No Winner");
                    for (String i : qS) {
                        if (i.charAt(0) != 't') {
                            steps1 += i + ",";
                        }

                    }
                    for (String i : players) {
                        playerStr += i + ",";
                        System.out.println(i);
                    }

                    winner = "It's a Tie";
                    if (model.getWhoWin().compareToIgnoreCase("x") == 0) {
                        d1.insert(steps1, playerStr, winner, "online");
                    }

                } else {

                    dialog.setlabeltext(model.getWinnerNumber());
//                    gameReplay.add(model.getWinnerNumber());
                    if (model.getWinnerNumber().compareToIgnoreCase("You Won") == 0) {
                        firstMsg = "x";
                        gameReplay.add(userName);
                        for (String i : qS) {
                            if (i.charAt(0) != 't') {
                                steps1 += i + ",";
                            }

                        }
                        for (String i : players) {
                            playerStr += i + ",";
                            System.out.println(i);
                        }
                        winner = userName;
                        d1.insert(steps1, playerStr, winner, "online");

                    } else {
                        firstMsg = "o";
                        gameReplay.add("t-" + userName);
                    }
                }
                try {
                    gameService.ps = new PrintStream(gameService.mySocket.getOutputStream());

                    gameService.ps.println("t" + "-" + userName);

                    for (String i : gameReplay) {
                        if (i.charAt(0) != 't') {
                            steps += i + ",";
                        }

                        // player+=userName+",";
                    }
                    System.out.println(steps);
                    System.out.println(steps1);
                    for (String i : players) {
                        playerStr += i + ",";
                        System.out.println(i);
                    }
                    System.out.println(playerStr);

//                    for (String j : qP) {
//                        //steps+=i+",";
//                        player += j + ",";
                    //
//                    }
//                    steps += model.getWhoWin();
////                    gameService.ps.println(steps + "-" + player);
                } catch (Exception ex) {
                    System.out.println("Output error");
                }
                /* Database d1=new Database();
                    //connect to database
                    if (model.getWinnerNumber().compareToIgnoreCase("You Won") == 0) {
                          d1.insert(steps, playerStr, "winner", "friend");
                        //firstMsg = "x";
                        //gameReplay.add(userName);
                        
                        

                    }*/
 /* else  if (model.getClicks() == 0 && model.playable()) {
                         if(model.getWhoWin().compareToIgnoreCase("x") == 0)
                         {
                             d1.insert(steps, playerStr, "no winner", "friend");
                         }
                           // dialog.setlabeltext("No Winner");
                            // gameReplay.add("No Winner");

                }*/
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

    public void close() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                B00.getScene().getWindow().setOnCloseRequest((WindowEvent event) -> {
                    gameService.ps.println("closed");
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                    ButtonType NO = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.setContentText("Are You Sure You Want to leave");
                    alert.getButtonTypes().add(yes);
                    alert.getButtonTypes().add(NO);
//                    stage window =  B00.getScene().getWindow();
//                    alert.setX( B00.getScene().getWindow().getX()+100);
//                    alert.setY( B00.getScene().getWindow().getY()+100);
                    
                    alert.showAndWait().ifPresent((response) -> {
                        if (response == yes) {

                            System.exit(0);
                        } else {
                            if (response == NO) {

                                event.consume();
                            }
                        }
                    });

                });
            }
        });

    }
}
