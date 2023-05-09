package entity;

import java.security.Timestamp;

public class Comment {
    private int id;
    private User user;
    private News news;
    private String comment;
    private java.sql.Timestamp postDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public java.sql.Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(java.sql.Timestamp postDate) {
        this.postDate = postDate;
    }
}
