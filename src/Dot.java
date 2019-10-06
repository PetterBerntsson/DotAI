import java.awt.*;
import java.util.ArrayDeque;


// --------------------------- Handles drawing of self and moving of self, gets its moves from its brain which is given in the Mutation Chamber

public class Dot {

    private int x;
    private int y;
    private Brain brain;
    private double fitnessScore;
    private boolean dead;
    private Goal goal;
    private ArrayDeque<Movement> moves;

    public Dot(int x, int y, Goal goal) {
        this.x = x;
        this.y = y;
        dead = false;
        this.goal = goal;
        moves = new ArrayDeque<>();

    }


    public void move() {
        if(!hasCollided() && !moves.isEmpty()) {
            x = x + moves.peek().getX();
            y = y + moves.poll().getY();

        } else {
            moves.clear();
            dead = true;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x,y,10,10);

    }

    public void update() {
        move();
    }

    public void initializeMoveSet() {
        moves = brain.getMoveSet();
    }

    public void setBrain(Brain brain) {
        this.brain = brain;
    }

    public Brain getBrain() {
        return brain;
    }

    public double getFitnessScore() {
        return fitnessScore;
    }

    public void setFitnessScore(double fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private boolean hasCollided() {
        return goal.hasCollided(this);
    }

    public boolean isDead() {
        return dead;
    }

    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
        dead = false;
    }
}
