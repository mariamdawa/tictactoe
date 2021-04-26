/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictac.model;

/**
 *
 * @author M
 */

public class onlineModel {
    
    
    private int[][] numbClicked = new int[3][3];
    private int clicks = 9;
    private boolean winner = false;
    // to call rest method
    private boolean Rest = false;
    String WhoWin;
    boolean play = true;
    //to check winner:
    //X is 1
    // O is 2
    private int[][] board = new int[3][3];
    String WinnerNumber= "No Winner";
    
    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }
    public void setWinner(boolean winnerCase)
    {
    winner = winnerCase;
    }
    public String getWinnerNumber(){
    
    return WinnerNumber;
    }

    public void setWinnerNumber(String WinnerNumber) {
        this.WinnerNumber = WinnerNumber;
    }
    
    public int getClicks()
    {
    return clicks;
    
    }
public void setWhoWin(String winner)
{
WhoWin = winner;

}

    public String getWhoWin() {
        return WhoWin;
    }

    public void resetNumbClicked() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                numbClicked[i][j] = 0;
            }
        }
    }
public void restBoard()
{
 for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
}
    public void resetClicks() {
        clicks = 9;
    }

    public boolean allow(int row, int colomn) {
        if (numbClicked[row][colomn] == 0) {
            numbClicked[row][colomn] = 1;
            return true;
        } else {
            return false;
        }
    }

    public String executeClick(int row, int colomn) {
        if (clicks % 2 == 0) {
            //means player o
            board[row][colomn] = 2;
            clicks--;

            return ("O");
        } else {
            //means player x
            clicks--;
            board[row][colomn] = 1;
            return ("X");

        }

    }


    public void checkWinner() 
    {
        //horizontal
        System.err.println(WhoWin);
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 1) 
            {
                winner = true;
                play=false; 
                if(WhoWin.equalsIgnoreCase("x"))
                {
                WinnerNumber ="You Won";
                }
                else
                {
                 WinnerNumber ="You lost";
                }
               
                
              System.out.println("Player1 is the winner");
              
             // winnerdialog("palyer 1") ;
        
            }
            else if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 2) {
                winner = true;
                play = false;
                    if(WhoWin.equalsIgnoreCase("x"))
                {
                WinnerNumber ="You lost";
                }
                else
                {
                 WinnerNumber ="You won";
                }
              System.out.println("Player2 is the winner");
             // winnerdialog("Player 2") ;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.err.println(board[i][j]);
            }
        }

        
             
       //vertical
            for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 1) {
                winner = true;
                play=false;
                if(WhoWin.equalsIgnoreCase("x"))
                {
                WinnerNumber ="You Won";
                }
                else
                {
                 WinnerNumber ="You lost";
                }
            }
            else if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 2) {
                winner = true;
                play = false;
                    if(WhoWin.equalsIgnoreCase("x"))
                {
                WinnerNumber ="You Lost";
                }
                else
                {
                 WinnerNumber ="You Won";
                }
              System.out.println("Player2 is the winner");
             // winnerdialog("Player 2 ") ;
            }
        }
       //diagnol
       if(board[0][0]== board[1][1] && board[0][0] == board[2][2] && board[0][0]==1)
       {
           winner = true;
           play = false;
               if(WhoWin.equalsIgnoreCase("x"))
                {
                WinnerNumber ="You Won";
                }
                else
                {
                 WinnerNumber ="You lost";
                }
        System.out.println("Player1 is the winner");

       // winnerdialog("Player 1") ;
       }
        if(board[0][2]== board[1][1] && board[0][2] == board[2][0] && board[0][2]==1)
       {
           winner = true;
           play = false;
               if(WhoWin.equalsIgnoreCase("x"))
                {
                WinnerNumber ="You Won";
                }
                else
                {
                 WinnerNumber ="You lost";
                }
        System.out.println("Player1 is the winner");
        //winnerdialog("Player 1") ;
       }
        if(board[0][0]== board[1][1] && board[0][0] == board[2][2] && board[0][0]==2)
       {
           winner = true;
           play = false;
               if(WhoWin.equalsIgnoreCase("x"))
                {
                WinnerNumber ="You lost";
                }
                else
                {
                 WinnerNumber ="You won";
                }
        System.out.println("Player2 is the winner");
       // winnerdialog("Player 2") ;
       }
          if(board[0][2]== board[1][1] && board[0][2] == board[2][0] && board[0][2]==2)
       {
           winner = true;
         
           play = false;
               if(WhoWin.equalsIgnoreCase("x"))
                {
                WinnerNumber ="You lost";
                }
                else
                {
                 WinnerNumber ="You won ";
                };
        System.out.println("Player2 is the winner");
        //winnerdialog("Player 2") ;
       }
            System.out.println(WinnerNumber);
    }

    public boolean playable ()
    {
    if( winner)
    {
    return false;
    }
    return true;
    }
  
}

