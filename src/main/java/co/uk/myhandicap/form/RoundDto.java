package main.java.co.uk.myhandicap.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Round Data Transfer Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/07/14
 * @project MyHandicapApp
 */
class RoundDto {

    @NotNull(message = "Date of Visit is required.")
    private Date playDate;
    @NotBlank(message = "Course Name is required.")
    private String courseName;
    @NotBlank(message = "Course Par is required.")
    private String coursePar;
    @Valid
    private List<HoleDto> holes;

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

    public List<HoleDto> getHoles() {
        return holes;
    }

    public void setHoles(List<HoleDto> holes) {
        this.holes = holes;
    }

    @Override
    public String toString() {
        return "RoundDto{" +
                "playDate=" + playDate +
                ", courseName='" + courseName + '\'' +
                ", coursePar='" + coursePar + '\'' +
                ", holes=" + holes +
                '}';
    }
}
