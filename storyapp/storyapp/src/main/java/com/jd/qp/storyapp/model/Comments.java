package com.jd.qp.storyapp.model;

import com.google.gson.JsonArray;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class Comments implements Comparable<Comments>{



    String text;

    String by;

    JsonArray kids;

    public JsonArray getKids() {
        return kids;
    }

    public void setKids(JsonArray kids) {
        this.kids = kids;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }


    public Comments(String text, String by, JsonArray kids) {
        this.text = text;
        this.by = by;
        this.kids = kids;
    }

/*    @Override
    public String toString() {
        return "Comments{" +
                ", text='" + text + '\'' +
                ", by='" + by + '\'' +
                '}';
    }*/

    @Override
    public String toString() {
        return "Comments{" +
                "text='" + text + '\'' +
                ", by='" + by + '\'' +
                ", kids=" + kids.toString() +
                '}';
    }

    public Comments(){}

    @Override
    public int compareTo(Comments comment) {
        int compareKids
                = comment.getKids().size();
        return compareKids - this.kids.size();
    }
}
