package entity;

import java.sql.Timestamp;

public class News {
    private int id;
    private String title;
    private String content;
    private News_Category newsCategory;
    private Timestamp postDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public News_Category getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(News_Category newsCategory) {
        this.newsCategory = newsCategory;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

}
