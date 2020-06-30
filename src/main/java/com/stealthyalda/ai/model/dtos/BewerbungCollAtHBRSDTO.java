package com.stealthyalda.ai.model.dtos;

import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Student;

import java.time.LocalDate;
import java.util.Objects;

public class BewerbungCollAtHBRSDTO implements AnwendungDTOs, Cloneable {

    private int id;
    private Student student;
    private StellenanzeigeDTO stellenanzeige;
    private String status;
    private String anschreiben;
    private String erfahrung;
    private String zertifikat;
    private Arbeitgeber arbeitgeber;
    private LocalDate datum;


    @Override
    public String toString() {
        return "CollAtHBRS{" + " stellenid =" + stellenanzeige.getStellenanzeigeID() + "," + "title=" + stellenanzeige.getTitel() +
                ", ort=" + stellenanzeige.getOrt() + ", status=" + status + ", arbeitgeber=" + arbeitgeber +
                " vorname =" + student.getVorname() + "," + "nachname=" + student.getNachname() + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BewerbungCollAtHBRSDTO other = (BewerbungCollAtHBRSDTO) obj;
        if (this.stellenanzeige != other.stellenanzeige) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.arbeitgeber, other.arbeitgeber)) {
            return false;
        }
        if (!Objects.equals(this.anschreiben, other.anschreiben)) {
            return false;
        }
        if (!Objects.equals(this.erfahrung, other.erfahrung)) {
            return false;
        }
        return Objects.equals(this.zertifikat, other.zertifikat);

    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getAnschreiben() {
        return anschreiben;
    }

    public void setAnschreiben(String anschreiben) {
        this.anschreiben = anschreiben;
    }

    public String getErfahrung() {
        return erfahrung;
    }

    public void setErfahrung(String erfahrung) {
        this.erfahrung = erfahrung;
    }

    public String getZertifikat() {
        return zertifikat;
    }

    public void setZertifikat(String zertifikat) {
        this.zertifikat = zertifikat;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StellenanzeigeDTO getStellenanzeige() {
        return stellenanzeige;
    }

    public void setStellenanzeige(StellenanzeigeDTO stellenanzeige) {
        this.stellenanzeige = stellenanzeige;
    }

    public Arbeitgeber getArbeitgeber() {
        return arbeitgeber;
    }

    public void setArbeitgeber(Arbeitgeber arbeitgeber) {
        this.arbeitgeber = arbeitgeber;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }


}
