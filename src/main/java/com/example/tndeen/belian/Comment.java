package com.example.tndeen.belian;

/**
 * Created by TnDeen on 15/03/2016.
 */
public class Comment {

    private long id;
    private String comment;
    private String detail;
    private String kg;
    private String multiply;
    private String rm;
    private String date;

    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }

    public String getMultiply() {
        return multiply;
    }

    public void setMultiply(String multiply) {
        this.multiply = multiply;
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return id + ". Nama: "+ comment + "\n" + "KG: " + kg + " * "  + multiply + "=" + rm + "\n"
                + "Pada: " + date + "\n" +"Nota: " + detail;
    }
}
