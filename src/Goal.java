import java.awt.*;

public class Goal {

    int x;
    int y;

    public Goal(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(250,20,30,30);
    }

    public boolean hasCollided(Dot dot) {

        if(Math.abs(dot.getX() - x) < 15 && Math.abs(dot.getY() - y) < 15) {
            return true;
        } else {
            return false;
        }
    }

}
