package main.java.co.uk.myhandicap.model.handicap;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Round of Golf Object.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Round {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="DATE_OF_PLAY")
    private String playDate;
    @Column(name="COURSE_NAME")
    private String courseName;
    @Column(name="COURSE_PAR")
    private String coursePar;
    @Column(name="COURSE_SSS")
    private String courseSSS;
    @Column(name="COURSE_HOLES")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(joinColumns = @JoinColumn(name="ROUND_ID"), inverseJoinColumns = @JoinColumn(name="HOLE_ID"))
    private List<Hole> holes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Hole> getHoles() {
        return holes;
    }

    public void setHoles(List<Hole> holes) {
        this.holes = holes;
    }

    @Override
    public String toString() {
        return "Round{" +
                "id=" + id +
                ", playDate='" + playDate + '\'' +
                ", courseName='" + courseName + '\'' +
                ", coursePar='" + coursePar + '\'' +
                ", courseSSS='" + courseSSS + '\'' +
                ", holes=" + holes +
                '}';
    }
}
