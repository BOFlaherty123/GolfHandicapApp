package main.java.co.uk.myhandicap.dto.handicap;

import java.util.Date;
import java.util.List;

/**
 * Round of Golf Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
public class Round {

    private Date playDate;
    private String courseName;
    private String coursePar;
    private List<Hole> holes;

    public Date getPlayDate() {
        return playDate;
    }

    public void setPlayDate(Date playDate) {
        this.playDate = playDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoursePar() {
        return coursePar;
    }

    public void setCoursePar(String coursePar) {
        this.coursePar = coursePar;
    }

    public List<Hole> getHoles() {
        return holes;
    }

    public void setHoles(List<Hole> holes) {
        this.holes = holes;
    }

    @Override
    public String toString() {
        return "Round{" +
                "playDate=" + playDate +
                ", courseName='" + courseName + '\'' +
                ", coursePar='" + coursePar + '\'' +
                ", holes=" + holes +
                '}';
    }

}
