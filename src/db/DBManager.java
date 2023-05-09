package db;

import entity.Comment;
import entity.News;
import entity.News_Category;
import entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bitlab",
                    "postgres",
                    "FATIBU56");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static User getUser(String email) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFull_name(resultSet.getString("full_name"));
                user.setRole_id(resultSet.getInt("role_id"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO users (email, password, full_name, role_id) " +
                    "VALUES (?, ?, ?, ?)");

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFull_name());
            statement.setInt(4, user.getRole_id());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addNews(News news) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO news (title, content, post_date, category_id) " +
                    "VALUES (?, ?, NOW(), ?)");

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.setInt(3, news.getNewsCategory().getId());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static News_Category getNewsCategorybyName(String name) {
        News_Category category = null;
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM news_categories WHERE name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                category = new News_Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("last_name"));
            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public static ArrayList<News> getNews() {
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT n.id, n.title, n.content, n.category_id, n.post_date, u.name " +
                    "FROM news n " +
                    "INNER JOIN news_categories u ON u.id = n.category_id " +
                    "ORDER BY n.post_date DESC ");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                News n = new News();
                n.setId(resultSet.getInt("id"));
                n.setTitle(resultSet.getString("title"));
                n.setContent(resultSet.getString("content"));
                n.setPostDate(resultSet.getTimestamp("post_date"));

                News_Category newsCategory = new News_Category();
                newsCategory.setId(resultSet.getInt("id"));
                newsCategory.setName(resultSet.getString("name"));
                n.setNewsCategory(newsCategory);

                news.add(n);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static News getNewsById(int id) {
        News news = null;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT n.id, n.title, n.content, n.category_id, n.post_date " +
                    "FROM news n " +
                    "INNER JOIN news_categories u ON u.id = n.category_id " +
                    "WHERE n.id = ? ");

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                news = new News();
                news.setId(resultSet.getInt("id"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setPostDate(resultSet.getTimestamp("post_date"));

                News_Category newsCategory = new News_Category();
                newsCategory.setId(resultSet.getInt("id"));
                newsCategory.setName(resultSet.getString("name"));
                news.setNewsCategory(newsCategory);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static void updateNews(News news) {
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE news SET title = ?, content = ? " +
                    "WHERE id = ?");

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.setLong(3, news.getId());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addComment(Comment comment) {
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO comments (comment, user_id, news_id, post_date) " +
                    "VALUES (?, ?, ?, NOW())");

            statement.setString(1, comment.getComment());
            statement.setInt(2, comment.getUser().getId());
            statement.setInt(3, comment.getNews().getId());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Comment> getComments(int newsId) {

        ArrayList<Comment> comments = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT co.id, co.comment, co.post_date, co.news_id, co.user_id, u.full_name " +
                    "FROM comments co " +
                    "INNER JOIN users u ON u.id = co.user_id " +
                    "INNER JOIN news n on co.news_id = n.id " +
                    "WHERE co.news_id = ? " +
                    "ORDER BY co.post_date DESC");

            statement.setLong(1, newsId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("post_date"));

                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setFull_name(resultSet.getString("full_name"));
                comment.setUser(user);

                News news = new News();
                news.setId(resultSet.getInt("news_id"));
                comment.setNews(news);

                comments.add(comment);
            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static User getUserById(int id) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFull_name(resultSet.getString("full_name"));
                user.setRole_id(resultSet.getInt("role_id"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void updateUser(User user) {
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users SET full_name = ?, password = ? " +
                    "WHERE id = ?");

            statement.setString(1, user.getFull_name());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getId());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteNewsById(int id) {
        try {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM news WHERE id = ?");
            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
