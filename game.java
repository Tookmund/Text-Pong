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
   private int ballX;
   private int ballY;
   private char paddleChar = '|';
   private int paddleSize = 1;
   // Left Paddle Top
   private int ltop = 1;
   // Right Paddle Top
   private int rtop = 1;
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
      addWalls();
      setupPaddles();
      setupBall();
   }
   public void loop()
   {
      while(true)
      {
         updateBall();
         movePaddles();
         update();
      }
   }
   public void setupPaddles()
   {
      if (x <= 3)
      {
         paddleSize = 1;
      }
      else if (x <= 5)
      {
         paddleSize = 2;
      }
      else if (x >= 10)
      {
         paddleSize = 5;
      }
      updatePaddle('L',1);
      updatePaddle('R',1);
   }
   public void movePaddles()
   {
      int inp = 0;
      try
      {
         inp = System.in.read();
      }
      catch(Exception e)
      {
         return;
      }
      int olr = rtop;
      int oll = ltop;
      switch(inp)
      {
         case 'w':
            ltop -=1;
            break;
         case 's':
            ltop += 1;
            break;
         case 'i':
            rtop -= 1;
            break;
         case 'k':
            rtop += 1;
            break;
      }
      updatePaddle('L',oll);
      updatePaddle('R',olr);
   }
   public void updatePaddle(char side,int oldtop)
   {
      int edge = 0;
      int top = 0;
      // Leave 1 char of space
      if (side == 'L')
      {
         edge = 1;
         top = ltop;
      }
      else if (side == 'R')
      {
         edge = y-2;
         top = rtop;
      }
      for (int i = oldtop;i < paddleSize+oldtop;i++)
      {
            try
            {
               board[i][edge] = ' ';
            }
            catch(Exception e)
            {

            }
      }
      for (int i = top;i < paddleSize+top;i++)
      {
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
      if(ballY == 1)
      {
        // Player 2 scores!
        score2 += 1;
        System.out.println("+1 Player 2");

      }
      else if (ballY == y-2)
      {
         // Player 1 scores!
         score1 += 1;
         System.out.println("+1 Player 1");
      }
      checkWinner();
      if (ballX == 0 || ballX == y-1)
      {
         // Yay recursion!
         setupBall();
      }
      int nx = ballX;
      int ny = ballY;
      switch(ballDir)
      {
         case U:
            nx += 1;
            break;
         case D:
            nx -= 1;
            break;
         case L:
            ny -= 1;
            break;
         case R:
            ny += 1;
            break;
         case UL:
            nx += 1;
            ny -= 1;
            break;
         case UR:
            nx += 1;
            ny += 1;
            break;
         case DL:
            nx -= 1;
            ny -= 1;
            break;
         case DR:
            nx -= 1;
            ny += 1;
            break;
      }
      int rd = genrn(0,1);
      if (board[nx][ny] == '|')
      {
      // Hit a paddle or a wall or a ceiling
          switch(ballDir)
          {
            case U:
               ballDir = L;
               break;
            case D:
               ballDir = R;
               break;
            case L:
               if( rd <= .5)
               {
                  ballDir = UR;
               }
               else
               {
                  ballDir = DR;
               }
               break;
            case R:
               if( rd <= .5)
               {
                  ballDir = DL;
               }
               else
               {
                  ballDir = UL;
               }
               break;
            case UL:
               if (rd <=.5)
               {
                  ballDir = R;
               }
               else
               {
                  ballDir = U;
               }
               break;
            case UR:
               if (rd <= .5)
               {
                  ballDir = L;
               
               }
               else
               {
                  ballDir = U;
               }
               break;
            case DL:
               if (rd <= .5)
               {
                  ballDir = R;
               }
               else
               {
                  ballDir = D;
               }
               break;
            case DR:
               if (rd <= .5)
               {
                  ballDir = L;
               }
               else
               {
                  ballDir = D;
               }
               break;
          }

      }

      else if (board[nx][ny] == '-')
      {
      // Hit the ceiling or floor
         switch(ballDir)
         {
            case U:
               if( rd <= .5)
               {
                  ballDir = DL;
               }
               else
               {
                  ballDir = DR;
               }
               break;
            case D:
               if( rd <= .5)
               {
                  ballDir = UL;
               }
               else
               {
                  ballDir = UR;
               }
               break;
            case L:
               break;
            case R:
               break;
            case UL:
               ballDir = DL;
               break;
            case UR:
               ballDir = DR;
               break;
            case DL:
               ballDir = UL;
               break;
            case DR:
               ballDir = UR;
               break;
         }

      }

      else
      {
         board[ballX][ballY] = ' ';
         ballX = nx;
         ballY = ny;
         board[ballX][ballY] = ballChar;
      }
   }
   public void checkWinner()
   {
      String wintxt = "";
      if (score1 >= 10)
      {
         wintxt = "Player 1 Wins!";
      }
      else if (score2 >= 10)
      {
         wintxt = "Player 2 Wins!";
      }
      if (score1 >= 10 || score2 >= 10)
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
        if (main.os.contains("Windows"))
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
   public void update()
   {
      clearConsole();
      for(int i = 0; i < x; i++) {
           System.out.println(String.valueOf(board[i]));
      }
   }
   public void addWalls()
   {
      int i;
      for (i = 0; i < y;i++)
      {
         board[0][i] = '-';
         board[x-1][i] = '-';
      }
      for (i = 0;i < x;i++)
      {
         board[i][0] = '|';
         board[i][y-1] = '|';
      }
   }
}
