package main.java.co.uk.myhandicap.dto.handicap;

/**
 * Hole Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
public class Hole {

    private String holePar;
    private String holeScore;
    private String SSI;
    private String yards;

    public String getHolePar() {
        return holePar;
    }

    public void setHolePar(String holePar) {
        this.holePar = holePar;
    }

    public String getHoleScore() {
        return holeScore;
    }

    public void setHoleScore(String holeScore) {
        this.holeScore = holeScore;
    }

    public String getSSI() {
        return SSI;
    }

    public void setSSI(String SSI) {
        this.SSI = SSI;
    }

    public String getYards() {
        return yards;
    }

    public void setYards(String yards) {
        this.yards = yards;
    }

    @Override
    public String toString() {
        return "Hole{" +
                "holePar='" + holePar + '\'' +
                ", holeScore='" + holeScore + '\'' +
                ", SSI='" + SSI + '\'' +
                ", yards='" + yards + '\'' +
                '}';
    }
}
