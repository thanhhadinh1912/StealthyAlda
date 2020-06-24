package com.stealthyalda.ai.model.dtos;

import java.util.Date;
import java.util.Objects;

public class CollAtHBRSDTO implements AnwendungDTOs {


    private int stellenid;
    private String title;
    private String status;
    private String beschreibung;
    private String arbeitgeber;
    private Date datum;
    private String vorname;
    private String nachname;
    private String email;
    private String applicationText;


    @Override
    public String toString() {
        return "CollAtHBRS{" + " stellenid =" + stellenid + "," + "title=" + title + ", status=" + status + ", beschreibung=" + beschreibung + ", arbeitgeber=" + arbeitgeber + ", datum=" + datum +" + " + " vorname =" + vorname + "," + "nachname=" + nachname + ", email=" + email + ","+ " applicationText=" + applicationText + '}';
    }

    @Override
    public boolean equals(AnwendungDTOs obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CollAtHBRSDTO other = (CollAtHBRSDTO) obj;
        if (this.stellenid != other.stellenid) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.beschreibung, other.beschreibung)) {
            return false;
        }
        if (!Objects.equals(this.arbeitgeber, other.arbeitgeber)) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        if (!Objects.equals(this.applicationText, other.applicationText)) {
            return false;

        }
        return true;

    }

        public int getStellenid () {
            return stellenid;
        }

        public void setStellenid(int stellenid){
            this.stellenid = stellenid;
        }

        public String gettitle () {
            return title;
        }

        public void settitle (String title){
            this.title = title;
        }

        public String getstatus () {
            return status;
        }

        public void setstatus (String status){
            this.status = status;
        }

        public String getbeschreibung () {
            return beschreibung;
        }

        public void setbeschreibung (String beschreibung){
            this.beschreibung = beschreibung;
        }

        public String getarbeitgeber () {
            return arbeitgeber;
        }

        public void setarbeitgeber (String arbeitgeber){
            this.arbeitgeber = arbeitgeber;
        }


        public Date getDatum () {
            return datum;
        }

        public void setDatum (Date datum){
            this.datum = datum;
        }

    public String getvorname () {
        return vorname;
    }

    public void setvorname (String vorname){
        this.vorname = vorname;
    }

    public String getNachname () {
        return nachname;
    }

    public void setNachname (String nachname){
        this.nachname = nachname;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email){
        this.email = email;
    }


        public String getApplicationText () {
            return applicationText;
        }

        public void setApplicationText (String applicationText){
            this.applicationText = applicationText;
        }




}
