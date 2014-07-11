package main.java.co.uk.myhandicap.dto.handicap;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Round of Golf Object
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
    @Temporal(TemporalType.DATE)
    private Date playDate;
    @Column(name="COURSE_NAME")
    private String courseName;
    @Column(name="COURSE_PAR")
    private String coursePar;
    @Column(name="COURSE_HOLES")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name="ROUND_ID"), inverseJoinColumns = @JoinColumn(name="HOLE_ID"))
    private List<Hole> holes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
                "id=" + id +
                ", playDate=" + playDate +
                ", courseName='" + courseName + '\'' +
                ", coursePar='" + coursePar + '\'' +
                ", holes=" + holes +
                '}';
    }
}
