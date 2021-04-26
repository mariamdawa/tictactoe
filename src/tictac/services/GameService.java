/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictac.services;

import java.awt.TextField;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import tictac.model.onlineModel;

/**
 *
 * @author ADMIN
 */
public class GameService {

    public Socket mySocket;
    public DataInputStream dis;
    public PrintStream ps;
    int port;
    String ip;
    public boolean connect = false;
    public boolean yourTurn = false;
    public String playerNumber = "";
    private onlineModel model = new onlineModel();

    public GameService(Stage Window) {
        while (true) {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("TestName");

            // Set the button types.
            ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
            dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);

            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            javafx.scene.control.TextField ipNo = new javafx.scene.control.TextField();
            //from.setPromptText("IP");
            ipNo.setText("127.0.0.1");
            javafx.scene.control.TextField portNo = new javafx.scene.control.TextField();
            //to.setPromptText("Port");

            gridPane.add(new Label("IP:"), 0, 0);
            gridPane.add(ipNo, 1, 0);
            gridPane.add(new Label("Port:"), 2, 0);
            gridPane.add(portNo, 3, 0);

            dialog.getDialogPane().setContent(gridPane);
            dialog.setX(Window.getX() + 100);
            dialog.setY(Window.getY() + 100);
            dialog.showAndWait();

            //Validation
            String zeroTo255 = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";
            String IP_REGEXP = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255;
            Pattern p = Pattern.compile(IP_REGEXP);
            Matcher m = p.matcher(ipNo.getText());
            if (!m.matches()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Enter a valid Ip");
                alert.setContentText("example for a valid ip is 127.0.0.1 ,\n  please try again");
                alert.showAndWait();
                continue;
            }
            ip = ipNo.getText();

            String portNumber = portNo.getText();
            if (portNumber.equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Enter Port number");
                alert.setContentText("a valid port number should be in range [1024,49151] ,\n  please try again");
                alert.showAndWait();
                continue;
            } else {
                port = Integer.parseInt(portNumber);
                if ((port > 1024) && (port < 49151)) {
                    break;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Incorrect Port Number");
                    alert.setContentText("a valid port number should be in range [1024,49151] ,\n please try again");
                    alert.showAndWait();
                }
            }

        }

        try {
            mySocket = new Socket(ip, port);

            playerNumber = "o";
            connect = true;
            System.out.println(model.getWhoWin());

        } catch (Exception ex) {
            server(Window);
        }
    }

//    public void sendMsg(String message) {
//
//        try {
//            ps = new PrintStream(mySocket.getOutputStream());
//            ps.println(message);
//        } catch (IOException ex) {
//            Logger.getLogger(GameService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void server(Stage window) {
        try {
           
            ServerSocket server = new ServerSocket(port);
 Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Waiting for Your opponent to join,\n you will redirected to the game automatically");
           alert.setX(window.getX()+100);
           alert.setY(window.getY()+100);
            alert.show();
            playerNumber = "x";
            System.out.println(model.getWhoWin());
            //Socket socket;
            mySocket = server.accept();
            System.err.println("Connected");
            alert.close();
            connect = true;
            yourTurn = true;
        } catch (IOException server) {
            server.printStackTrace();

        }

    }

}
