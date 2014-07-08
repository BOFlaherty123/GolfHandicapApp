package main.java.co.uk.myhandicap.dto.handicap;

import main.java.co.uk.myhandicap.dto.user.User;

import java.util.Date;
import java.util.List;

/**
 * User Score Card Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
public class ScoreCard {

    private User player;
    private Date submittedDate;
    private List<Round> golfRounds;

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
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
                "player=" + player +
                ", submittedDate=" + submittedDate +
                ", golfRounds=" + golfRounds +
                '}';
    }
}
