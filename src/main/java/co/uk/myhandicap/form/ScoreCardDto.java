package main.java.co.uk.myhandicap.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ScoreCard Data Transfer Object.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/07/14
 * @project MyHandicapApp
 */
public class ScoreCardDto {

    @NotNull(message = "User/Player ID is required.")
    private Long playerId;
    @NotBlank(message = "Submission Date is required.")
    private String submittedDate;
    @Valid
    private List<RoundDto> golfRounds;

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

    public List<RoundDto> getGolfRounds() {
        return golfRounds;
    }

    public void setGolfRounds(List<RoundDto> golfRounds) {
        this.golfRounds = golfRounds;
    }

    @Override
    public String toString() {
        return "ScoreCardDto{" +
                "playerId=" + playerId +
                ", submittedDate=" + submittedDate +
                ", golfRounds=" + golfRounds +
                '}';
    }

}
