public class game 
{
   private char[][] board;
   /*
   x
   x
   x
   x
   x
   x is vertical
   */
   private static int x;
   /*
   yyyyy
   y is horizontal
   */
   private static int y;
   private char ballChar = '@';
   private char ballX;
   private char ballY;
   private char paddleChar = '|';
   private int paddleSize = 3;
   public int score1 = 0;
   public int score2 = 0;
   // Ball Direction
   
   /* Up
   */
   static final int U = 0;
   // Down
   static final int D = 1;
   // Left
   static final int L = 2;
   // Right
   static final int R = 3;
   /* Up Left
   <
    \
     \
      \
   */
   static final int UL = 4;
   /* Up Right
       >
      /
     /
    /
   */
   static final int UR = 5;
   /* Down Left
       /
      /
     /
    <
   */
   static final int DL = 6;
   /* Down Right
   \
    \
     \
      >
   */
   static final int DR = 7;
   int ballDir;
   
   public game(int nx, int ny) 
   {
      x = nx;
      y = ny;
      board = new char[x][y];
      clearBoard(' ');
      setupPaddles();
      //setupBall();
      //begin();
   }
   public void setupPaddles()
   {
      if (x > 10)
      {
         paddleSize = 5;
      }
      updatePaddle('L',0);
      updatePaddle('R',0);
   }
   public void updatePaddle(char side, int top)
   {
      int edge = 0;
      // Leave 1 char of space
      if (side == 'L')
      {
         edge = 1;
      }
      else if (side == 'R')
      {
         edge = y-2;
      }
      for (int i = top;i < paddleSize+1;i++)
      {
        // You can move your paddle off screen...if you really want to...
        try 
        {
            board[i][edge] = paddleChar;
        }
        catch(Exception e)
        {
        }
      }
   }
   public int genrn(int min, int max)
   {
      // Biases RNG toward higher numbers, but oh well...
      return (int)(Math.random() * (max - min + 1)) + min;
   }
   public void setupBall() 
   {
      // Ensure RNG doesn't score on you :)
      ballX = genrn(1,x-3);
      ballY = genrn(1,y-3);
      ballDir = genrn(U,DR);
      updateBall();
   }
   public void updateBall()
   {
      switch(ballDir)
      {
         case U:
            ballDir = D;
            break;
         case D:
            ballDir = U;
            break;
         case L:
            ballDir = R;
            break;
         case R:
            ballDir = L;
            break;
         case UL:
            ballDir = DL;
            break;
         case UR:
            ballDir = DR;
            break;
         case DL:
            ballDir = 
            break;
         case DR:
            ballX -= 1;
            ballY += 1;
            break;
      }
      if(ballX == 0)
      {
        // Player 2 scores!
        score2 += 1;
         
      }
      else if (ballX == y-1)
      {
         // Player 1 scores!
         score1 += 1;
      }
      checkWinner();
      if (ballX == 0 || ballX == y-1)
      {
         // Yay recursion!
         setupBall();
      }
      
      try 
      {
         board[ballX][ballY] = ballChar;
      }
      catch(Exception e)
      {
         switch(ballDir)
      {
         case U:
            ballX += 1;
            break;
         case D:
            ballX -= 1;
            break;
         case L:
            ballY -= 1;
            break;
         case R:
            ballY += 1;
            break;
         case UL:
            ballX += 1;
            ballY -= 1;
            break;
         case UR:
            ballX += 1;
            ballY += 1;
            break;
         case DL:
            ballX -= 1;
            ballY -= 1;
            break;
         case DR:
            ballX -= 1;
            ballY += 1;
            break;
      }

      }
   }
   public void checkWinner()
   {
      String wintxt = "";
      if (score1 >= 5)
      {
         wintxt = "Player 1 Wins!";
      }
      else if (score2 >= 5)
      {
         wintxt = "Player 2 Wins!";
      }
      if (score1 >= 5 || score2 >= 5)
      {
         clearBoard(' ');
         update();
         System.out.println(wintxt);
         System.exit(0);
      }
   }
   public void clearBoard(char inp)
   {
      for(int i = 0; i < x; i++) 
      {
         for (int j = 0; j < y; j++) 
         {
            board[i][j] = inp;
         }
      }
   }
   // Thanks Stack Overflow! (http://stackoverflow.com/a/17015039/4237781)
   public final static void clearConsole()
   {
      try
      {
         final String os = System.getProperty("os.name");
        if (os.contains("Windows"))
        {
            Runtime.getRuntime().exec("cls");
        }
        else
        {
            Runtime.getRuntime().exec("clear");
        }
      }
      catch (final Exception e)
      {
         // Hope for the best...
      }
   }
   public void update() {
      clearConsole();
      for(int i = 0; i < x; i++) {
           System.out.println(String.valueOf(board[i]));
      } 
   }
}