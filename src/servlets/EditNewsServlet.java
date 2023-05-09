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

@WebServlet (value = "/edit_news")
public class EditNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String title = request.getParameter("title");
            News_Category newsCategory = DBManager.getNewsCategorybyName(request.getParameter("category"));
            String content = request.getParameter("content");
            int id = Integer.parseInt(request.getParameter("id"));

            News news = DBManager.getNewsById(id);
            if (news != null) {
                news.setTitle(title);
                news.setContent(content);
                news.setNewsCategory(newsCategory);

                DBManager.updateNews(news);

                response.sendRedirect("/news_details?id=" + id);

            } else {
                response.sendRedirect("/");
            }

    }
}