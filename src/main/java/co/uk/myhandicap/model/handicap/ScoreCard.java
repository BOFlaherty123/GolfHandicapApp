package main.java.co.uk.myhandicap.model.handicap;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User Score Card Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
@Entity
@Table(name="SCORE_CARDS")
public class ScoreCard {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="PLAYER")
    private Long playerId;
    @Column(name="SUBMITTED_DATE")
    private String submittedDate;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(joinColumns = @JoinColumn(name="SCORE_CARD_ID"), inverseJoinColumns = @JoinColumn(name="ROUND_ID"))
    private List<Round> golfRounds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    public List<Round> getGolfRounds() {
        return golfRounds;
    }

    public void setGolfRounds(List<Round> golfRounds) {
        this.golfRounds = golfRounds;
    }

    @Override
    public String toString() {
        return "ScoreCard{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", submittedDate=" + submittedDate +
                ", golfRounds=" + golfRounds +
                '}';
    }

}
