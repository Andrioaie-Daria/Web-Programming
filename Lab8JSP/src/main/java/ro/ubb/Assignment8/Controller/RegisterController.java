package ro.ubb.Assignment8.Controller;

import ro.ubb.Assignment8.Domain.User;
import ro.ubb.Assignment8.Model.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class RegisterController extends HttpServlet {
    public RegisterController(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        RequestDispatcher dispatcher = null;

        if(!Objects.equals(password1, password2)){
            dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        DBManager dbManager = new DBManager();
        User user = dbManager.register(username, name, password1);

        if(user != null){
            dispatcher = request.getRequestDispatcher("/home.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }
        else{
            dispatcher = request.getRequestDispatcher("/error.jsp");
        }
        dispatcher.forward(request, response);
    }
}
