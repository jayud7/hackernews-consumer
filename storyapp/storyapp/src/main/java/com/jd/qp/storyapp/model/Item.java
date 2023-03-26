package com.jd.qp.storyapp.model;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item implements Comparable<Item> {
    @Id
    @Column
    String id ;
    //item's unique id.

    @Column
    String title;

    @Column(length = 1023)
    String url;

    @Column
    int score;

    @Column
    long submissionTime;

    @Column
    String by;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(long submissionTime) {
        this.submissionTime = submissionTime;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public int compareTo(Item item) {
        int compareScore
                = item.getScore();
        return compareScore - this.score;
    }

    public Item(String id, String title, String url, int score, long submissionTime, String by) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.score = score;
        this.submissionTime = submissionTime;
        this.by = by;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", score=" + score +
                ", submissionTime=" + submissionTime +
                ", by='" + by + '\'' +
                '}'+"\n";
    }
    public Item(){}
}
