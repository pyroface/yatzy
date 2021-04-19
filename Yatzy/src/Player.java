
import java.util.Comparator;

public class Player implements Comparable<Player>{

    int id;
    String name;
    Double score;

    //tried with an autoincrement counter
    //private static int IdCounter = 1;

    public Player(int id, String name, Double score) {
        //this.id = IdCounter++;
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }


    @Override
    public int compareTo(Player p) {
        return this.score.compareTo(p.score);
    }

    @Override
    public String toString() {
        return "name: " + name + ", score: " + score;
    }
}
