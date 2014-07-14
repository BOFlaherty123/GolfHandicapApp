package main.java.co.uk.myhandicap.model.user;

import javax.persistence.*;
import java.util.Date;

/**
 * UserRole
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 03/07/14
 * @project MyHandicapApp
 */
@Embeddable
public class UserRole {

    @Temporal(TemporalType.DATE)
    @Column(name="ASSIGNED_DATE")
    private Date assignedDate;
    @Column(name="ROLE")
    private String role;

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "assignedDate=" + assignedDate +
                ", role='" + role + '\'' +
                '}';
    }
}
