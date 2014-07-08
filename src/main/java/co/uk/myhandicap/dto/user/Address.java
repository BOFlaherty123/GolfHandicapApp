package main.java.co.uk.myhandicap.dto.user;

import javax.persistence.*;

/**
 * Address Entity
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 03/07/14
 * @project MyHandicapApp
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// Only required for a SINGLE_TABLE inheritance strategy
//@DiscriminatorColumn(
//        name="ADDRESS_TYPE",
//        discriminatorType = DiscriminatorType.STRING
//)
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long addressId;
    @Column(name="ADDRESS_1")
    private String address1;
    @Column(name="POST_CODE")
    private String postCode;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", address1='" + address1 + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }

}
