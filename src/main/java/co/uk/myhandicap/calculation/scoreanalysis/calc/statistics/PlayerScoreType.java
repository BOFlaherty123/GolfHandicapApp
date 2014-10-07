package main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics;

/**
 * Player Score Type Overall Totals
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 07/10/2014
 * @project MyHandicapApp
 */
public class PlayerScoreType {

    private int total_eagle;
    private int total_birdie;
    private int total_par;
    private int total_bogey;
    private int total_double_bogey;
    private int total_triple_bogey;
    private int total_other;

    public PlayerScoreType(int total_eagle, int total_birdie, int total_par, int total_bogey, int total_double_bogey, int total_triple_bogey, int total_other) {
        this.total_eagle = total_eagle;
        this.total_birdie = total_birdie;
        this.total_par = total_par;
        this.total_bogey = total_bogey;
        this.total_double_bogey = total_double_bogey;
        this.total_triple_bogey = total_triple_bogey;
        this.total_other = total_other;
    }

    public int getTotal_eagle() {
        return total_eagle;
    }

    public int getTotal_birdie() {
        return total_birdie;
    }

    public int getTotal_par() {
        return total_par;
    }

    public int getTotal_bogey() {
        return total_bogey;
    }

    public int getTotal_double_bogey() {
        return total_double_bogey;
    }

    public int getTotal_triple_bogey() {
        return total_triple_bogey;
    }

    public int getTotal_other() {
        return total_other;
    }

    @Override
    public String toString() {
        return "PlayerScoreType{" +
                "total_eagle=" + total_eagle +
                ", total_birdie=" + total_birdie +
                ", total_par=" + total_par +
                ", total_bogey=" + total_bogey +
                ", total_double_bogey=" + total_double_bogey +
                ", total_triple_bogey=" + total_triple_bogey +
                ", total_other=" + total_other +
                '}';
    }

}
