package chathura.mathula.phonebook.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contacts")
public class Contact {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String mobileNumber;
    private String homeNumber;

    public Contact(String firstname, String lastname, String mobileNumber, String homeNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobileNumber = mobileNumber;
        this.homeNumber = homeNumber;
        this.id = buildId(firstname, lastname);
    }

    private String buildId(String firstname, String lastname) {
        String lowerCaseFirstname = firstname.toLowerCase();
        String lowerCaseLastname = lastname.toLowerCase();

        StringBuilder idString = new StringBuilder();

        idString.append(lowerCaseFirstname).append(lowerCaseLastname);
        return idString.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }
}
