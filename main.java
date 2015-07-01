import java.io.*;
import java.util.*;

public class main 
{
   public static void main (String[] args) 
   {
      if (System.console() == null)
      {
         System.err.println("Warning: This game was was designed to run in cmd.exe.\nYour play experience may be suboptimal.");
      }
      System.out.println("Player 1 uses W and S to move the paddle\nPlayer 2 uses I and K to move the paddle\nUnfortunately I cannot uncook a terminal in Java so you must press enter for the game to continue\nReady? ");
      try 
      {
         System.in.read();
      }
      catch(Exception e)
      {
      
      }
      Scanner input = new Scanner(System.in);
      // Windows console is 22x78
      game Game = new game(22,78);
      Game.update();
      Game.loop();   
   }
}