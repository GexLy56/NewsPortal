package servlets;

import db.DBManager;
import entity.Comment;
import entity.News;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet (value = "/add_comment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");

        if (user != null) {

            String commentText = request.getParameter("comment");
            int newsId = Integer.parseInt(request.getParameter("news_id"));

            News news = DBManager.getNewsById(newsId);

            if (news != null) {
                Comment comment = new Comment();
                comment.setNews(news);
                comment.setUser(user);
                comment.setComment(commentText);

                DBManager.addComment(comment);

                response.sendRedirect("/news_details?id="+newsId);

            }else{
                response.sendRedirect("/");
            }
        } else {
            response.sendRedirect("/login");
        }
    }
}
