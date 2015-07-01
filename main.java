import java.io.*;
import java.util.*;

public class main 
{
   public static final String os = System.getProperty("os.name");
   public static void main (String[] args) 
   {
      if (System.console() == null)
      {
         System.err.println("Warning: This game was was designed to run in cmd.exe.\nYour play experience may be suboptimal.");
      }
      rawMode(true);
      System.out.println("Player 1 uses W and S to move the paddle\nPlayer 2 uses I and K to move the paddle\nReady? ");
      try 
      {
         System.in.read();
      }
      catch(Exception e)
      {
      
      }
      Scanner input = new Scanner(System.in);
      game Game;
      if (System.console() == null)
      {
         // JGrasp Terminal is 5x47
         Game = new game(5,47);
      }
      else 
      {
         // Windows console is 22x78
         Game = new game(22,78);
      }
      Game.update();
      Game.loop();
      rawMode(false);
   }
   public final static void rawMode(boolean on)
   {
      if (os.contains("Windows"))
      {
         System.err.println("You will have to press enter to continue the game");
      }
      else
      {
         try
         {
            if(on)
            {
               Runtime.getRuntime().exec("stty raw");
            }
            else
            {
               Runtime.getRuntime().exec("stty cooked");
            }
         }
         catch(Exception e)
         {
         
         }
      }
   }
}