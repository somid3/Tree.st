package com.questy.domain;

import com.questy.utils.StringUtils;

import java.util.Date;

public class User extends Parent {
    private Date createdOn;
    private String email;
    private String passwordHash;
    private String passwordSalt;
    private String firstName;
    private String lastName;
    private String checksum;
    private String faceUrl;
    private Date faceOn;
    private Integer faceRef;

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getSaltChecksum() {
        return passwordSalt.substring(0, 15);
    }

    public String getName() {
        return getFirstName() + " " + getLastName();
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getFirstName() {
        return StringUtils.capitalize(firstName.trim());
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return StringUtils.capitalize(lastName.trim());
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean hasFace() {
        return faceUrl != null;
    }

    public String getFaceUrl() {

        if (hasFace())
            return faceUrl;
        else {
            return "/resources/fruits/" + (getId() % 19) + ".jpg";
//            return "/resources/users/0/faces/0_face_none.jpg";
        }

    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public Date getFaceOn() {
        return faceOn;
    }

    public void setFaceOn(Date currentFaceOn) {
        this.faceOn = currentFaceOn;
    }

    public Integer getFaceRef() {
        return faceRef;
    }

    public void setFaceRef(Integer faceRef) {
        this.faceRef = faceRef;
    }

}
