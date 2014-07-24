package com.questy.domain;

import com.questy.utils.StringUtils;
import com.questy.utils.Vars;

import java.util.Date;

public class User extends Parent {

    public final static int ANY_USER_ID = 0;

    private Date createdOn;
    private String email;
    private String passwordHash;
    private String passwordSalt;
    private String firstName;
    private String lastName;
    private String faceUrl;
    private Date faceOn;
    private Integer faceRef;
    private String stripeId;

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
            return "/resources/defaults/profile.png";
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

    public String getStripeId() {
        return stripeId;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }
}
