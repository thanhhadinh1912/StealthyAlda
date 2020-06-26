package com.stealthyalda.ai.model.dtos;
import java.util.Date;


public class StepstoneDTO implements AnwendungDTOs {


    private int stellenid;
    private String jobTitle;
    private String Vorname;
    private String Nachname;
    private String email;
    private String applicationText;


    @Override
    public String toString() {
        return " { \"Stepstone\": { \n "
                + " \"stellenid\": \"" + stellenid + "\", \n "
                + " \"jobTitle\": \"" + jobTitle + "\", \n "
                + " \"Vorname\": \"" + Vorname + "\", \n "
                + " \"Nachname\": \"" + Nachname + "\", \n "
                + "+ } }";
    }

    @Override
    public boolean gleich(AnwendungDTOs application) {
        return (application instanceof StepstoneDTO
                && this.getApplicationText().equals(((StepstoneDTO) application).getApplicationText()));
    }


    public int getstellenid() {
        return stellenid;
    }

    public void setstellenid(int stellenid) {
        this.stellenid = stellenid;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }



    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String vorname) {
        this.Vorname = vorname;
    }

    public String getNachname() {
        return Nachname;
    }

    public void setNachname(String nachname) {
        this.Nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getApplicationText() {
        return applicationText;
    }

    public void setApplicationText(String applicationText) {
        this.applicationText = applicationText;
    }

}


