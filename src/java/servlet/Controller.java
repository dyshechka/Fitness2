package servlet;

import dao.FitnessDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

public class Controller extends HttpServlet {

    private String msg;
    private FitnessDAO dao;

    @Override
    public void init() {
        dao = new FitnessDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("1".equals(request.getParameter("goout"))) {
            request.setAttribute("message", "Необходимо авторизоваться");
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, "!Post");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = dao.findUser(login, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if ("Admin".equals(user.getNameRole())) {
                response.sendRedirect("/Fitness/Admin/");
            } else if ("Client".equals(user.getNameRole())) {
                response.sendRedirect("/Fitness/Client/");
            } else if ("Trainer".equals(user.getNameRole())) {
                response.sendRedirect("/Fitness/Trainer/");
            } 
        } else {
            request.setAttribute("message", "Неверный логин или пароль");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
