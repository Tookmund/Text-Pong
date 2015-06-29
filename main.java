import java.io.*;
import java.util.*;

public class main 
{
   public static void main (String[] args) 
   {
      Scanner input = new Scanner(System.in);
      // Windows console is 22x78
      game Game = new game(22,78);
      Game.update();   
   }
}