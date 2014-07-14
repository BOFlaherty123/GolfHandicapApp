package main.java.co.uk.myhandicap.dto;

import main.java.co.uk.myhandicap.validation.annotation.HoleValid;

/**
 * Hole Data Transfer Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/07/14
 * @project MyHandicapApp
 */
@HoleValid.List({
        @HoleValid(holePar = "holePar", holeScore = "holeScore", holeSSI = "holeSSI", holeYards = "holeYards",
        message = "Please ensure that all fields have been completed.")
})
public class HoleDto {

    private String holePar;
    private String holeScore;
    private String holeSSI;
    private String holeYards;

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

    public String getHoleSSI() {
        return holeSSI;
    }

    public void setHoleSSI(String holeSSI) {
        this.holeSSI = holeSSI;
    }

    public String getHoleYards() {
        return holeYards;
    }

    public void setHoleYards(String holeYards) {
        this.holeYards = holeYards;
    }

    @Override
    public String toString() {
        return "HoleDto{" +
                "holePar='" + holePar + '\'' +
                ", holeScore='" + holeScore + '\'' +
                ", holeSSI='" + holeSSI + '\'' +
                ", holeYards='" + holeYards + '\'' +
                '}';
    }

}
