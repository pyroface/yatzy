import java.util.ArrayList;
import java.util.Collections;

public class Management {
  private static ArrayList<Player> playerList = new ArrayList<>();

  public void addNewPlayer(Player player) {
    playerList.add(player);
  }

  public static void showPlayers(){
    boolean ans = playerList.isEmpty();
    if (ans)
      System.out.println("The player list is empty");

    for (int i = 0; i < playerList.size(); i++) {
      System.out.println(
              " Name: " + playerList.get(i).getName() +
              " ID: " + playerList.get(i).getId() +
              " Score: " + playerList.get(i).getScore());
    }

  }

  public static void highestScore(){
    System.out.println("Highest score\n" + Collections.max(playerList));
  }

  public static void scoreSort(){
    /*
    System.out.println("\nSorted by price Ascending");

    playerList.sort(null);

    for (Player p : playerList) {
      System.out.println(p);
    }
    */

    //System.out.println("\nSorted by price Descending");
    Collections.reverse(playerList);

    for (Player p : playerList) {
      System.out.println(p);
    }

  }

  public static int playerListLength(){
    return playerList.size();
  }

  public static Double playerGetScore(int id){
    return playerList.get(id).getScore();
  }
  public static void playerSetScore( int id, Double newScore){
      playerList.get(id).setScore(newScore);
  }

  public static String playerGetName( int id){
    return playerList.get(id).getName();
  }

}
