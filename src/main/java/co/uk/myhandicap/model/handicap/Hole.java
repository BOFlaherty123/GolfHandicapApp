package main.java.co.uk.myhandicap.model.handicap;

import javax.persistence.*;

/**
 * Hole Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Hole {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="HOLE_PAR")
    private String holePar;
    @Column(name="HOLE_SCORE")
    private String holeScore;
    @Column(name="HOLE_SSI")
    private String SSI;
    @Column(name="HOLE_YARDS")
    private String yards;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
                "id=" + id +
                ", holePar='" + holePar + '\'' +
                ", holeScore='" + holeScore + '\'' +
                ", SSI='" + SSI + '\'' +
                ", yards='" + yards + '\'' +
                '}';
    }
}
