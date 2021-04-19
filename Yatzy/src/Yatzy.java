
import java.util.Scanner;
import java.util.*;
import java.util.Arrays;

public class Yatzy {

  private static Scanner scan = new Scanner(System.in);
  private static Management playerList = new Management();

  public static void main(String[] args) {

    boolean playing = true;

    while(playing){

      System.out.println("1: add players");
      System.out.println("2: start game");
      System.out.println("3: show rules");
      System.out.println("0: quit");

      String input = scan.next();
      int userChoice = 0;
      try {
        userChoice = Integer.parseInt(input);
      } catch (NumberFormatException ne) {
        System.out.println("!! Please use a number !!");
        continue;
      }

      switch(userChoice){
        case 0:
          playing = false;
          break;
        case 1:
          addPlayer();
          break;
        case 2:
          //gameplayLoop();
          game();
          break;
        case 3:
          rules();
          break;
      }
    }
  }

  private static void rules(){
    System.out.println(
      "Each player rolls 5 die\n" +
      "the result of each die will be\n" +
      "added to the players score.\n" +
      "First player to reach 300points win\n" +
      "the player can gain additional points\n" +
      "for different combinations like:\n" +
      "-----------------------------\n" +
      "Pair: Sum of the two dice showing the same number\n" +
      "Three of a kind: Sum of those dice\n" +
      "Four of a kind: Sum of those dice\n" +
      "Yatzy: Sum of all five dice\n" +
      "Small straight(1-5): 15 points\n" +
      "Large straight(2-6): 20 points\n" +
      "Full house: any set of pair and three of a kind. 25p\n"
    );
  }

  private static void addPlayer(){

    System.out.println(" - Chose between 1-4 players - ");
    System.out.print("How many players?: ");

    String input = scan.next();
    int playerNr = 0;
    scan.nextLine();

    try {
      playerNr = Integer.parseInt(input);
    } catch (NumberFormatException ne) {
      System.out.println("!! Please use a number !!");
    }

    if( playerNr > 4 ){
      System.out.println("You can max play as four people");
    }else if ( playerNr == 0){
      System.out.println("Someone must play");
    }else{
      for (int i = 1; i <= playerNr; i++) {
        System.out.print("Write player name: ");
        String name = scan.nextLine();

        Player p = new Player(i, name, 0.0);
        playerList.addNewPlayer(p);
      }
    }

  }

  private static void game(){
    boolean playing = true;

    while(playing){
      System.out.println("1: roll dice");
      System.out.println("2: show results");
      System.out.println("0: Back to main menu");

      String input = scan.next();
      int userChoice = 0;
      try {
        userChoice = Integer.parseInt(input);
      } catch (NumberFormatException ne) {
        System.out.println("!! Please use a number !!");
        continue;
      }

      switch(userChoice){
        case 0:
          playing = false;
          break;
        case 1:
          gameplayLoop();
          break;
        case 2:
          Management.scoreSort();
          break;
      }
    }
  }

  public static void gameplayLoop(){

    if(checkWin()){
      System.out.println("We have a winner!");
      Management.highestScore();
      System.out.print("Play again(Y/N)?: ");
      String input = scan.next();
      if(input.equals("Y") || input.equals("y")){
        for (int i = 0; i < Management.playerListLength(); i++) {
          Management.playerSetScore(i,0.0);
        }
      }
    }else{
      for (int i = 0; i < Management.playerListLength(); i++) {
        double result = Management.playerGetScore(i);
        System.out.println("\nPlayer: " + Management.playerGetName(i) );
        result = result + rolls();
        Management.playerSetScore(i, result);
        System.out.println("------------------------");
      }
    }
  }


  static int[] intArray = new int[5];
  //static int[] intArray = {6,6,5,5,5};
  public static int rolls(){

    System.out.print("Rolls: ");
    for(int i = 0; i<5; i++){
      int random_int = (int)(Math.random() * 6 + 1);
      intArray[i] = random_int;
      System.out.print(intArray[i] + " ");
    }

    System.out.println("\n");
    int q = frequencyCheck();
    return q;
  }

  public static int frequencyCheck() {
    //Array fr will store frequencies of element
    int[] fr = new int[intArray.length];
    int visited = -1;
    for (int i = 0; i < intArray.length; i++) {
      int count = 1;
      for (int j = i + 1; j < intArray.length; j++) {
        if (intArray[i] == intArray[j]) {
          count++;
          //To avoid counting same element again
          fr[j] = visited;
        }
      }
      if (fr[i] != visited)
      fr[i] = count;
    }

    int result = 0;

    //Displays the frequency of each element present in array
    //Then loop to find pairs/threes/four of a kind and Yatzy
    //Arrays.sort(intArray);
    //System.out.println("\nElement | Frequency");
    for (int i = 0; i < fr.length; i++) {
      if (fr[i] != visited){
        //System.out.println(intArray[i] + "| " + fr[i]);
      }

      //result of the combined dices
      result = result + intArray[i];
      //results from pairs and full house
      result = result + checkPairs(fr[i], intArray[i]);
      result = result + checkFullHouse();
    }

    result = result + straights(result);

    System.out.println("\nRound result: " + result);
    return result;
  }

  private static int pair = 0;
  private static int threes = 0;
  public static int checkPairs(int x, int y){

    int sum = 0;

    if (x == 2) {
      System.out.println("found a pair: " + y);
      sum = x * y;
      pair++;
    } else if (x == 3) {
      System.out.println("three of a kind: " + y);
      sum = y * 3;
      threes++;
    } else if (x == 4){
      System.out.println("four of a kind: " + y);
      sum = y * 4;
    } else if (x == 5){
      System.out.println("Yatzy: " + y);
      sum = y * 5;
    }
    return sum;
  }

  public static int checkFullHouse(){
    /*
      some rules gives you the sum of the dices in full house
      other just gives you 25. I'm going with 25 because it's simpler
    */
    int sum = 0;
    if(pair == 1 && threes == 1){
      System.out.println("Full house: 25 points!");
      pair = 0;
      threes = 0;
      sum = 25;
    }
    return sum;
  }

  public static int straights(int n){
    int straightResult = n;
    Arrays.sort(intArray);
    //System.out.printf("Sorted array : %s", Arrays.toString(intArray));
    if (((intArray[0] == 1) && (intArray[1] == 2) && (intArray[2] == 3) && (intArray[3] == 4) && (intArray[4] == 5))
            || ((intArray[1] == 1) && (intArray[2] == 2) && (intArray[3] == 3) && (intArray[4] == 4) && (intArray[5] == 5))) {
      System.out.println("small straight: 15 points!");
      straightResult = 15;
    }else if(((intArray[0] == 2) && (intArray[1] == 3) && (intArray[2] == 4) && (intArray[3] == 5) && (intArray[4] == 6))
            || ((intArray[1] == 2) && (intArray[2] == 3) && (intArray[3] == 4) && (intArray[4] == 5) && (intArray[5] == 6))) {
      System.out.println("large straight: 20 points!");
      straightResult = 20;
    }

    return straightResult;
  }

  public static boolean checkWin(){

    boolean win = false;

    for (int i = 0; i < Management.playerListLength(); i++) {
      if (Management.playerGetScore(i) >= 63)
        win = true;
    }
    return win;
  }

}
