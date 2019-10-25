package task2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<PersonPair> pairs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pairs.add(new PersonPair(i, "First " + i, "Second " + i));
        }

        for(int i = 0; i < 5; i++){
            new Thread(pairs.get(i).getP1()).start();
            new Thread(pairs.get(i).getP2()).start();
        }
    }
}
