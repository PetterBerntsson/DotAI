import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Evaluator {

    double harshness;
    Goal goal;

    public Evaluator(double harshness, Goal goal) {
        this.harshness = harshness;
        this.goal = goal;
    }

    public ArrayList<Dot> getFittest(ArrayList<Dot> dots) {
        //crucial this is done first
        setFitnessScores(dots);

        dots.sort(Comparator.comparing(Dot::getFitnessScore).reversed());
        ArrayList<Dot> fittest = new ArrayList<>();

        int limit = ((int)(harshness*dots.size()));

        int i = 0;
        for(Dot dot: dots) {
            fittest.add(dot);
            i++;
            if(i >= limit) { break; }
        }



        return fittest;
    }

    private void setFitnessScores(ArrayList<Dot> dots) {
        for(Dot dot: dots) {
            dot.setFitnessScore(1.0/(Math.sqrt(distanceTo(goal, dot))));
        }
    }


    private double distanceTo(Goal goal, Dot dot) {
        return Math.sqrt(Math.pow((double)(Math.abs(goal.x - dot.getX())), 2) + Math.pow((double)(Math.abs(goal.y - dot.getY())), 2));
    }
}
