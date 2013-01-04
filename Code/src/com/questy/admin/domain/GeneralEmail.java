package com.questy.admin.domain;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Shlomo
 * Date: 1/3/13
 * Time: 4:35 PM

 * To change this template use File | Settings | File Templates.
 */

public class GeneralEmail {
    private Integer id;
    private String first_name, organization, email, url;

    public Date getSentOn() {
        return sentOn;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

    private Date sentOn;

    public GeneralEmail(String firstn, String organizationn, String emailn, String urln) {
        first_name = firstn;
        organization = organizationn;
        email = emailn;
        url = urln;
    }

    public GeneralEmail(String emailn, String urln) {
        first_name = "";
        url = urln;
        email = emailn;
        organization = "";

    }

    public GeneralEmail() {
        first_name = "";
        organization = "";
        email = "";
        url = "";
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
