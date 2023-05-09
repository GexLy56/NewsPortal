package servlets;

import db.DBManager;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet (value = "/register")
public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("re_password");
        String full_Name = req.getParameter("full_name");

        User user = DBManager.getUser(email);

        if(user==null){
            if(password.equals(rePassword)){

                User createUser = new User();
                createUser.setEmail(email);
                createUser.setPassword(password);
                createUser.setFull_name(full_Name);
                createUser.setRole_id(2);

                DBManager.addUser(createUser);
                resp.sendRedirect("/register?success");

            }else{
                resp.sendRedirect("/register?passworderror");
            }

        }else{
            resp.sendRedirect("/register?emailerror");
        }
    }
}
