package main.java.co.uk.myhandicap.dto.user;

import main.java.co.uk.myhandicap.dto.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Home Address Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 07/07/2014
 * @project MyHandicapApp
 */
@Entity(name = "HOME_ADDRESS")
//@DiscriminatorValue("HOME")
public class HomeAddress extends Address {

    @Column(name="HOME_TELEPHONE")
    private String homeTelephone;

    public String getHomeTelephone() {
        return homeTelephone;
    }

    public void setHomeTelephone(String homeTelephone) {
        this.homeTelephone = homeTelephone;
    }

}
