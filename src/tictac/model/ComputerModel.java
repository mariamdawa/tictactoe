/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictac.model;

import java.util.Random;

/**
 *
 * @author M
 */
public class ComputerModel {

    //Which string will draw according to id
    public int[][] numbClicked = new int[3][3];
    public int clicks = 9;
    private int result=11;
    //to check winner:
    //X is 1
    // O is 2
    //the entire board: 
    private int[][] board = new int[3][3];
    //Remaining:
    private int[][] remainingBoard = new int[3][3];
    private boolean winner = false;
    // to call rest method
    private boolean Rest = false;
      private boolean turn = true;
    //to check winner:
    //X is 1
    // O is 2
    String WinnerNumber = "No Winner";

    public void setWinnerNumber(String WinnerNumber) {
        this.WinnerNumber = WinnerNumber;
    }
    

    public void setWinner(boolean winnerCase) {
        winner = winnerCase;
    }

    public boolean getWinner() {
        return winner;
    }

    public String getWinnerNumber() {

        return WinnerNumber;
    }

    public int getClicks() {
        return clicks;

    }
    public void decClicks(){
        clicks--;
    }
     public void setTurn(boolean setturn)
    {
    turn = setturn;
    
    }
      public boolean getTurn()
    {
    return turn ;
    
    }
      

    public void restBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
    }

    Random rand = new Random();

    public boolean canPlay() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (numbClicked[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void resetNumbClicked() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                numbClicked[i][j] = 0;
            }
        }
    }

    public void resetClicks() {
        clicks = 9;
    }

    public boolean allow(int row, int colomn) {
        if (numbClicked[row][colomn] == 0) {
            numbClicked[row][colomn] = 1;
            clicks--;
            return true;
        } else {
            return false;
        }
    }

    /*   public String executeClick(int row, int colomn){
            if(clicks %2 == 0){
                  //means player o
                board[row][colomn]=2;  
                clicks --;
                
            return("O");
            }
          
            else{
                //means player x
                    clicks --;
                    board[row][colomn]=1; 
                      return("X");
                      
                    }
                
              
        }*/
    public void availablePlaces() {

        //First we declare all the array with 1 's as a no places availabl
        for (int i1 = 0; i1 < 3; i1++) {
            for (int j1 = 0; j1 < 3; j1++) {
                remainingBoard[i1][j1] = 1;
            }
        }
        //Second we modify it by available places
        for (int i2 = 0; i2 < 3; i2++) {
            for (int j2 = 0; j2 < 3; j2++) {
                if (numbClicked[i2][j2] == 0) {
                    remainingBoard[i2][j2] = 0;
                }
            }
        }
    }
    //Random playing, No use of AI:

    public String computerPlays() {
        availablePlaces();
        boolean assign = false;
        int randomRow;
        int randomColmn;
        String result = "B";
        while (!assign) {
            //board[0-2][0-2]
            randomRow = rand.nextInt(3);//1
            randomColmn = rand.nextInt(3);//0

            for (int i3 = 0; i3 < 3; i3++) {
                for (int j3 = 0; j3 < 3; j3++) {
                    if (remainingBoard[i3][j3] == 0 && i3 == randomRow && j3 == randomColmn) {
                        numbClicked[i3][j3] = 2;
                        clicks--;
                        assign = true;
                        String row = String.valueOf(randomRow);
                        String col = String.valueOf(randomColmn);
                        result += row + col;
                        break;
                    }
                }
            }

        }
        System.out.println("Result" + result);
        return result;
    }

    public void checkWinner() {
        //horizontal
        for (int i = 0; i < 3; i++) {
            if (numbClicked[i][0] == numbClicked[i][1] && numbClicked[i][0] == numbClicked[i][2] && numbClicked[i][0] == 1) {
                result=1;
                winner = true;
                WinnerNumber = "You Won";
                //System.out.println("You Won");

                // winnerdialog("palyer 1") ;
            } else if (numbClicked[i][0] == numbClicked[i][1] && numbClicked[i][0] == numbClicked[i][2] && numbClicked[i][0] == 2) {
                result=2;
                winner = true;
                WinnerNumber = "You Lost";
                //System.out.println("You Lost");
                // winnerdialog("Player 2") ;
            }
        }

        //vertical
        for (int i = 0; i < 3; i++) {
            if (numbClicked[0][i] == numbClicked[1][i] && numbClicked[0][i] == numbClicked[2][i] && numbClicked[0][i] == 1) {
                result=1;
                winner = true;
                WinnerNumber = "You Won";
                //System.out.println("You Won");
                // winnerdialog("player 1") ;
            } else if (numbClicked[0][i] == numbClicked[1][i] && numbClicked[0][i] == numbClicked[2][i] && numbClicked[0][i] == 2) {
                result=2;
                winner = true;
                WinnerNumber = "You Lost";
                //System.out.println("You Lost");
                // winnerdialog("Player 2 ") ;
            }
        }
        //diagnol
        if (numbClicked[0][0] == numbClicked[1][1] && numbClicked[0][0] == numbClicked[2][2] && numbClicked[0][0] == 1) {
            result=1;
            winner = true;
            WinnerNumber = "You Won";
            //System.out.println("You Won");

            // winnerdialog("Player 1") ;
        }
        if (numbClicked[0][2] == numbClicked[1][1] && numbClicked[0][2] == numbClicked[2][0] && numbClicked[0][2] == 1) {
            result=1;
            winner = true;
            WinnerNumber = "You Won";
            //System.out.println("You Won");
            //winnerdialog("Player 1") ;
        }
        if (numbClicked[0][0] == numbClicked[1][1] && numbClicked[0][0] == numbClicked[2][2] && numbClicked[0][0] == 2) {
            result=2;
            winner = true;
            WinnerNumber = "You Lost";
            //System.out.println("You Lost");
            // winnerdialog("Player 2") ;
        }
        if (numbClicked[0][2] == numbClicked[1][1] && numbClicked[0][2] == numbClicked[2][0] && numbClicked[0][2] == 2) {
            result=2;
            winner = true;
            WinnerNumber = "You Lost";
            //System.out.println("You Lost");
            //winnerdialog("Player 2") ;
        }
       // System.out.println(WinnerNumber);
    }

    
    public boolean playable() {
        if (winner) {
            return false;
        }
        return true;
    }
    public int getResult(){
        if(canPlay()){
            checkWinner();
            return result;
        }
        //that means tie:
        return 0;
    }

}
