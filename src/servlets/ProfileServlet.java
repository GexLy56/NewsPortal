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

@WebServlet (value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if(currentUser!=null) {
            request.getRequestDispatcher("/profile.jsp").forward(request, response);
        }else{
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String full_Name = request.getParameter("full_name");
        String password = request.getParameter("password");
        int id = Integer.parseInt(request.getParameter("id"));

        User user = DBManager.getUserById(id);
        if (user != null) {
            user.setFull_name(full_Name);
            user.setPassword(password);

            DBManager.updateUser(user);

            response.sendRedirect("/profile_details?id=" + id);

        } else {
            response.sendRedirect("/");
        }
    }
}
