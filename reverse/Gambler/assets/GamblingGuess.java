import java.util.Random;
import java.util.Scanner;

public class GamblingGuess {
   private static final String ANSI_RED = "\u001b[31m";
   private static final String ANSI_YELLOW = "\u001b[33m";
   private static final String ANSI_GREEN = "\u001b[32m";
   private static final String ANSI_RESET = "\u001b[0m";
   private static final String ANSI_FLASH = "\u001b[5m";
   private static final int[] CIPHER = new int[]{216, 72, 26, 238, 199, 85, 141, 94, 159, 249, 193, 77, 46, 251, 180, 19, 191, 114, 192, 189, 193, 88, 62, 161, 242, 62, 209, 79, 172, 164, 174, 117, 4, 166, 238, 81, 149, 91, 155, 183};

   public static void main(String[] var0) throws InterruptedException {
      Scanner var1 = new Scanner(System.in);
      Random var2 = new Random(2025L);
      boolean var3 = true;
      int[] var4 = new int[10];
      System.out.println("\u001b[33m──────────▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
      System.out.println("────────█═════════════════█");
      System.out.println("──────█═════════════════════█");
      System.out.println("─────█════════▄▄▄▄▄▄▄════════█");
      System.out.println("────█════════█████████════════█");
      System.out.println("────█═══════██▀─────▀██═══════█");
      System.out.println("───███████████──█▀█──███████████");
      System.out.println("───███████████──▀▀▀──███████████");
      System.out.println("────█═══════▀█▄─────▄█▀═══════█");
      System.out.println("────█═════════▀█████▀═════════█");
      System.out.println("────█═════════════════════════█");
      System.out.println("────█══════▀▄▄▄▄▄▄▄▄▄▀════════█");
      System.out.println("───▐▓▓▌═════════════════════▐▓▓▌");
      System.out.println("───▐▐▓▓▌▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▐▓▓▌▌");
      System.out.println("───█══▐▓▄▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▄▓▌══█");
      System.out.println("──█══▌═▐▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▌═▐══█");
      System.out.println("──█══█═▐▓▓▓▓▓▓▄▄▄▄▄▄▄▓▓▓▓▓▓▌═█══█");
      System.out.println("──█══█═▐▓▓▓▓▓▓▐██▀██▌▓▓▓▓▓▓▌═█══█");
      System.out.println("──█══█═▐▓▓▓▓▓▓▓▀▀▀▀▀▓▓▓▓▓▓▓▌═█══█");
      System.out.println("──█══█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█══█");
      System.out.println("─▄█══█▐▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▌█══█▄");
      System.out.println("─█████▐▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▌─█████");
      System.out.println("─██████▐▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▌─██████");
      System.out.println("──▀█▀█──▐▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▌───█▀█▀");
      System.out.println("─────────▐▓▓▓▓▓▓▌▐▓▓▓▓▓▓▌");
      System.out.println("──────────▐▓▓▓▓▌──▐▓▓▓▓▌");
      System.out.println("─────────▄████▀────▀████▄");
      System.out.println("─────────▀▀▀▀────────▀▀▀▀\u001b[0m");
      System.out.println("\u001b[31m===================================");
      System.out.println("   W E L C O M E   T O   F I A");
      System.out.println("   N U M B E R   G A M B L E");
      System.out.println("===================================\u001b[0m");
      System.out.println("\nGuess 0-255 to beat the shiba!");
      System.out.println("Win all 10 rounds to claim the jackpot!\n");
      int var5 = 0;

      int var6;
      while(var5 < 10) {
         var6 = var2.nextInt(256);
         var4[var5] = var6;
         System.out.println("\u001b[5m⚡⚡⚡ ROLLING... ⚡⚡⚡\u001b[0m");
         Thread.sleep(800L);
         System.out.print("\n[Round " + (var5 + 1) + "/10] Enter your bet (0-255): ");

         int var7;
         try {
            var7 = var1.nextInt();
         } catch (Exception var9) {
            System.out.println("\u001b[31m\ud83d\udc80 CRASH! Invalid input!");
            System.out.println("\ud83d\udd25 GAME OVER! \ud83d\udd25\u001b[0m");
            var3 = false;
            var1.nextLine();
            break;
         }

         if (var7 >= 0 && var7 <= 255) {
            if (var7 == var6) {
               System.out.println("\u001b[32m\ud83c\udf89 WINNER! You think you can beat me again ??? ");
               System.out.println("Progress: " + (var5 + 1) + "/10\u001b[0m");
               Thread.sleep(500L);
               if (var5 < 9) {
                  System.out.println("\n\u001b[5m\ud83d\udcb8\ud83d\udcb8\ud83d\udcb8 NEXT ROUND! \ud83d\udcb8\ud83d\udcb8\ud83d\udcb8\n\u001b[0m");
               }

               ++var5;
               continue;
            }

            System.out.println("\u001b[31m\ud83d\udc80 CRASH! You suck !!! \ud83d\udc80");
            System.out.println("\ud83d\udd25 GAME OVER! \ud83d\udd25\u001b[0m");
            var3 = false;
            break;
         }

         System.out.println("\u001b[31m\ud83d\udc80 CRASH! Invalid bet!");
         System.out.println("\ud83d\udd25 GAME OVER! \ud83d\udd25\u001b[0m");
         var3 = false;
         break;
      }

      if (var3) {
         StringBuilder var10 = new StringBuilder();

         for(var6 = 0; var6 < CIPHER.length; ++var6) {
            var10.append((char)((CIPHER[var6] ^ var4[var6 % 10]) & 255));
         }

         System.out.println("\u001b[32m\n===============================================");
         System.out.println("\ud83d\udcb0\ud83d\udcb0\ud83d\udcb0 JACKPOT! YOU BEAT BORY THE SHIBA! \ud83d\udcb0\ud83d\udcb0\ud83d\udcb0");
         System.out.println("===============================================\u001b[0m");
         System.out.println("\n\ud83c\udfc6 CONGRATULATIONS! 10 STRAIGHT WINS! \ud83c\udfc6");
         System.out.println("\n\ud83d\udd11 SECRET MESSAGE: " + var10.toString());
      } else {
         System.out.println("\u001b[31m\n===================================");
         System.out.println("\ud83d\ude08 GAME OVER - THE SHIBA WINS AGAIN! \ud83d\ude08");
         System.out.println("===================================\u001b[0m");
      }

      var1.close();
   }
}
