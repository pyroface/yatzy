
import java.util.*;
import java.util.Arrays;

class Die {
    private int value;
    private Random rand;

    public Die() {
        value = 0;
        rand = new Random();
    }

    public void roll() {
        value = 1 + rand.nextInt(6);
    }

    public int get() {
        return (value);
    }
}

/*
* Use die class to maybe have a reroll function
* */

// maybe use something like this in main class
/*
public static void rolling(){
    int[] aDice = new int[] { 0, 0, 0, 0, 0 };
    Die die = new Die();

    for (int i = 0; i < 5; i++){
        die.roll();
        aDice[i] = die.get();
        System.out.print(aDice[i] + " ");
    }
}
*/