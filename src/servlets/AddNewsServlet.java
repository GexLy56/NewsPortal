package servlets;

import db.DBManager;
import entity.News;
import entity.News_Category;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(value = "/add_news")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        if(user!=null) {

            request.getRequestDispatcher("/addnews.jsp").forward(request, response);

        }else{
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        if(user!=null) {

            String title = request.getParameter("title");
            String content = request.getParameter("content");
            News_Category newsCategory = DBManager.getNewsCategorybyName(request.getParameter("category"));

            News news = new News();
            news.setTitle(title);
            news.setContent(content);
            news.setNewsCategory(newsCategory);

            DBManager.addNews(news);

            response.sendRedirect("/");

        }else{
            response.sendRedirect("/login");
        }
    }
}
