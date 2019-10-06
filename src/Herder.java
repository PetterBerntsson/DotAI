import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class Herder {

    ArrayList<Dot> herd;

    int x;
    int y;

    Goal goal;
    Random r;

    public Herder(int startX, int startY, Goal goal) {

        herd = new ArrayList<>();
        this.x = startX;
        this.y = startY;
        this.goal = goal;
        r = new Random();
    }


    public void update(Graphics g) {
        for(Dot dot: herd) {
            dot.move();
            dot.draw(g);
        }
    }


    public void createHerd(int population) {
        for(int i = 0; i < population; i++) {
            herd.add(new Dot(x, y, goal));
        }
    }

    public ArrayList<Dot> getHerd() {
        return herd;
    }

    public void initializeMoveSets() {
        for(Dot dot: herd) {
            dot.initializeMoveSet();
        }
    }

    public boolean allDead() {

        boolean allDead = true;

        for(Dot dot: herd) {
            if(!dot.isDead()) {
                allDead = false;
            }
        }

        return allDead;
    }

    public ArrayList<Dot> getNewHerd(int population, double harshness) {
        int missing = (int)(population*harshness);
        ArrayList<Dot> newHerd = new ArrayList<>();

        for(int i = 0; i < missing; i++) {
            newHerd.add(new Dot(x, y, goal));
        }

        return newHerd;
    }

    public void setNewHerd(ArrayList<Dot> newHerd) {
        this.herd = newHerd;
        resetToStart();
    }

    private void resetToStart() {
        for(Dot dot: herd) {
            dot.reset(x, y);
        }
    }
}
