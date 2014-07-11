package main.java.co.uk.myhandicap.dto.user.address;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Work Address
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 07/07/2014
 * @project MyHandicapApp
 */
@Entity(name = "WORK_ADDRESS")
//@DiscriminatorValue("WORK")
public class WorkAddress extends Address {

    @Column(name="WORK_TELEPHONE")
    private String workTelephone;
    @Column(name="WORK_FAX_NUMBER")
    private String workFaxNumber;

    public String getWorkTelephone() {
        return workTelephone;
    }

    public void setWorkTelephone(String workTelephone) {
        this.workTelephone = workTelephone;
    }

    public String getWorkFaxNumber() {
        return workFaxNumber;
    }

    public void setWorkFaxNumber(String workFaxNumber) {
        this.workFaxNumber = workFaxNumber;
    }

}
