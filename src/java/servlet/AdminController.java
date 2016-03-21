package servlet;

import dao.FitnessDAO;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Marta
 */
public class AdminController extends HttpServlet {

    private FitnessDAO dao;

    @Override
    public void init() {
        dao = new FitnessDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, "!Post");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null || !user.getNameRole().equals("Admin")) {
            response.sendRedirect("/Fitness/");
            return;
        }

//   request.setAttribute("path", request.getRequestURI()); 

        String requestUrl = request.getRequestURI();
        switch (requestUrl) {
            case "/Fitness/Admin/createNewUser.jsp":
                request.setAttribute("listRoles", dao.listRoles());
               // request.setAttribute("listNameGroups", dao.listNameGroups());
                request.getRequestDispatcher("/adminView/createNewUser.jsp").forward(request, response);
                return;
            case "/Fitness/Admin/submitCreateNewUser.jsp":
                User createdUser = new User();

                createdUser.setFullName(request.getParameter("fullName"));
                createdUser.setEmail(request.getParameter("email"));
                createdUser.setTelephone(Integer.valueOf(request.getParameter("telephone")));
                createdUser.setLogin(request.getParameter("login"));
                createdUser.setNameRole(request.getParameter("roleName"));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOfBirth = null;
                try {
                    dateOfBirth = dateFormat.parse(request.getParameter("dateOfBirth"));
                } catch (ParseException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                createdUser.setDateOfBirth(new java.sql.Date(dateOfBirth.getTime()));

                if (dao.createUser(createdUser, request.getParameter("password")) == -1) {
                    request.setAttribute("message", "Произошла ошибка");
                } else {
                    request.setAttribute("message", "Пользователь добавлен");
                    request.setAttribute("userName", user.getFullName());
                    request.setAttribute("listUsers", dao.readAllUsers());
                    request.getRequestDispatcher("/adminView/mainPagesOfAdmin.jsp").forward(request, response);
                }
                return;
            case "/Fitness/Admin/deleteUser.jsp":
                int id = Integer.parseInt(request.getParameter("id_user"));
                request.setAttribute("User", dao.readUser(id));
                request.getRequestDispatcher("/adminView/deleteUser.jsp").forward(request, response);
                return;
            case "/Fitness/Admin/submitDeleteUser.jsp":
                id = Integer.parseInt(request.getParameter("id_user"));
                if (!dao.deleteUser(id)) {
                    request.setAttribute("message", "Произошла ошибка");
                } else {
                    response.sendRedirect("/Fitness/Admin/?successremove=1");
                    return;
                }
            case "/Fitness/Admin/editRole.jsp":
                request.setAttribute("listRoles", dao.listRoles());
                id = Integer.parseInt(request.getParameter("id_user"));
                request.setAttribute("User", dao.readUser(id));
                request.getRequestDispatcher("/adminView/editRole.jsp").forward(request, response);
                return;
            case "/Fitness/Admin/submitEditRole":
                id = Integer.parseInt(request.getParameter("id_user"));
                String nameRole = request.getParameter("roleName");

                User userEdit;
                userEdit = dao.readUser(id);
                userEdit.setNameRole(nameRole);
                String nameUser = userEdit.getFullName();
                if (!dao.updateUser(id, userEdit)) {
                    request.setAttribute("message", "Произошла ошибка");
                } else {
                    request.setAttribute("message", "Роль пользователя " + nameUser + " обновлена на " + nameRole);
                    request.setAttribute("userName", user.getFullName());
                    request.setAttribute("listUsers", dao.readAllUsers());
                    request.getRequestDispatcher("/adminView/mainPagesOfAdmin.jsp").forward(request, response);
                }
                return;
            case "/Fitness/Admin/index.jsp":
                request.getRequestDispatcher("/adminView/index.jsp").forward(request, response);
                return;
        }

        if ("1".equals(request.getParameter("successremove"))) {
            request.setAttribute("message", "Пользователь удален");
        }
        request.setAttribute("userName", user.getFullName());
        request.setAttribute("listUsers", dao.readAllUsers());
        request.getRequestDispatcher("/adminView/mainPagesOfAdmin.jsp").forward(request, response);
    }
}
