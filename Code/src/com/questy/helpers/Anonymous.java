package com.questy.helpers;

import com.questy.domain.User;
import com.questy.utils.Vars;

import java.util.Date;

public class Anonymous extends User {

    private User user;

    public Anonymous(User user) {
        this.user = user;
    }

    public Integer getId () {
        return -1;
    }

    public String getName () {
        return "Anonymous";
    }

    public String getFirstName () {
        return "Anonymous";
    }

    public String getLastName () {
        return "Anonymous";
    }

    public String getSaltChecksum () {
        return user.getSaltChecksum();
    }

    public Date getFaceOn() {
        return new Date();
    }

    public Boolean hasFace() {
        return true;
    }

    public String getFaceUrl() {

        return Vars.resourcesUrl + "users/-1/faces/face.jpg";

    }
}
