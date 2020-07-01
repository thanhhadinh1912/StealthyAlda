package com.stealthyalda.ai.model.dtos;

public class StepstoneDTO implements AnwendungDTOs {


    private static final String NEW_LINE = "\", \n ";
    private int stellenid;
    private String jobTitle;
    private String vorname;
    private String email;
    private String applicationText;
    private String nachname;

    @Override
    public String toString() {
        return " { \"Stepstone\": { \n "
                + " \"stellenid\": \"" + stellenid + NEW_LINE
                + " \"jobTitle\": \"" + jobTitle + NEW_LINE
                + " \"Vorname\": \"" + vorname + NEW_LINE
                + " \"Nachname\": \"" + nachname + NEW_LINE
                + "+ } }";
    }

    @Override
    public boolean equals(Object application) {
        if (application == this)
            return true;
        if (application == null)
            return true;

        if (getClass() != application.getClass()) {
            return false;
        }
        return (application instanceof StepstoneDTO
                && this.getApplicationText().equals(((StepstoneDTO) application).getApplicationText()));
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
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
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
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


