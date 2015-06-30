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
      Scanner input = new Scanner(System.in);
      // Windows console is 22x78
      game Game = new game(22,78);
      Game.update();
      Game.loop();   
   }
}