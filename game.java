public class game 
{
   private char[][] board;
   private static int x;
   private static int y;
   public game(int nx, int ny) 
   {
      x = nx;
      y = ny;
      board = new char[x][y];
      clearBoard('0');
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
         // Google says this might clear the terminal (http://lists.bluej.org/pipermail/bluej-discuss/2003-April/002171.html)
         System.out.println("\f");
      }
   }
   public void update() {
      clearConsole();
      for(int i = 0; i < x; i++) {
           System.out.println(String.valueOf(board[i]));
      } 
   }
}