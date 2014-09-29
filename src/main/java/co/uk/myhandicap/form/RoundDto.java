package main.java.co.uk.myhandicap.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import java.util.List;

/**
 * Round Data Transfer Object.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/07/14
 * @project MyHandicapApp
 */
public class RoundDto {

    @NotBlank(message = "Date of Visit is required.")
    private String playDate;
    @NotBlank(message = "Course Name is required.")
    private String courseName;
    @NotBlank(message = "Course Par is required.")
    private String coursePar;
    @NotBlank(message = "Course SSS is required.")
    private String courseSSS;
    @Valid
    private List<HoleDto> holes;

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
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

    public String getCourseSSS() {
        return courseSSS;
    }

    public void setCourseSSS(String courseSSS) {
        this.courseSSS = courseSSS;
    }

    public List<HoleDto> getHoles() {
        return holes;
    }

    public void setHoles(List<HoleDto> holes) {
        this.holes = holes;
    }

    @Override
    public String toString() {
        return "RoundDto{" +
                "playDate='" + playDate + '\'' +
                ", courseName='" + courseName + '\'' +
                ", coursePar='" + coursePar + '\'' +
                ", courseSSS='" + courseSSS + '\'' +
                ", holes=" + holes +
                '}';
    }
}
