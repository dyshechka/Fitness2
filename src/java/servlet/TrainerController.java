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
import models.Group;
import models.User;

/**
 *
 * @author Marta
 */
public class TrainerController extends HttpServlet {

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

        String requestUrl = request.getRequestURI();
        switch (requestUrl) {
            case "/Fitness/Trainer/changeGroup.jsp":
                request.getRequestDispatcher("/trainerView/changeGroup.jsp").forward(request, response);
                break;
            case "/Fitness/Trainer/createNewGroup.jsp":
                request.getRequestDispatcher("/trainerView/createNewGroup.jsp").forward(request, response);
                break;
            case "/Fitness/Trainer/submitCreateNewGroup.jsp":
                Group createdGroup = new Group();
                
                createdGroup.setNameGroup(request.getParameter("nameGroup"));
                createdGroup.setTypeTraining(request.getParameter("nameTraining"));
                
                if (dao.createGroup(createdGroup) == -1){
                    request.setAttribute("message", "Произошла ошибка");
                } else {
                    request.setAttribute("message", "Группа добавлена");
                    request.setAttribute("userName", user.getFullName());
                    request.setAttribute("listGroups", dao.readAllGroups());
                }
                request.getRequestDispatcher("/trainerView/mainPagesOfTrainer.jsp").forward(request, response);
                break;
            case "/Fitness/Trainer/deleteGroup.jsp":
                int idGroup = Integer.parseInt(request.getParameter("id_group"));
                request.setAttribute("group", dao.readGroup(idGroup));
                request.getRequestDispatcher("/trainerView/deleteGroup.jsp").forward(request, response);
                break;
            case "/Fitness/Trainer/submitDeleteGroup.jsp":
                String nameGroup = request.getParameter("nameGroup");
                idGroup = Integer.parseInt(request.getParameter("id_group"));

                Group group = dao.readGroup(idGroup);
                group.setNameGroup(nameGroup);
                
                if (!dao.deleteGroup(group.getIdGroup())) {
                    request.setAttribute("message", "Произошла ошибка");
                } else {
                    request.setAttribute("message", "Данные обновлены");
                    request.setAttribute("userName", user.getFullName());
                    request.setAttribute("listGroups", dao.readAllGroups());
                }
                request.getRequestDispatcher("/trainerView/mainPagesOfTrainer.jsp").forward(request, response);
                break;
            case "/Fitness/Trainer/detailsGroup.jsp":
                request.getRequestDispatcher("/trainerView/detailsGroup.jsp").forward(request, response);
                break;
            case "/Fitness/Trainer/editGroup.jsp":
                idGroup = Integer.parseInt(request.getParameter("id_group"));
                request.setAttribute("group", dao.readGroup(idGroup));
                request.getRequestDispatcher("/trainerView/editGroup.jsp").forward(request, response);
                break;
            case "/Fitness/Trainer/submitEditGroup.jsp":
                nameGroup = request.getParameter("nameGroup");
                idGroup = Integer.parseInt(request.getParameter("id_group"));

                group = dao.readGroup(idGroup);
                group.setNameGroup(nameGroup);

                if (!dao.updateGroup(group.getIdGroup(), group)) {
                    request.setAttribute("message", "Произошла ошибка");
                } else {
                    request.setAttribute("message", "Группа удалена");
                    request.setAttribute("userName", user.getFullName());
                    request.setAttribute("listGroups", dao.readAllGroups());
                }
                request.getRequestDispatcher("/trainerView/mainPagesOfTrainer.jsp").forward(request, response);
                break;
            case "/Fitness/Trainer/listFrozenClient.jsp":
                request.getRequestDispatcher("/trainerView/listFrozenClient.jsp").forward(request, response);
                break;
            case "/Fitness/Trainer/showFramedSubscription.jsp":
                request.getRequestDispatcher("/trainerView/showFramedSubscription.jsp").forward(request, response);
                break;
            case "/Fitness/Trainer/index.jsp":
                request.getRequestDispatcher("/trainerView/index.jsp").forward(request, response);
                break;
        }
        if (user == null || !user.getNameRole().equals("Trainer")) {
            response.sendRedirect("/Fitness/");
        } else {
            request.setAttribute("userName", user.getFullName());
            request.setAttribute("listGroups", dao.readAllGroups());
            request.getRequestDispatcher("/trainerView/mainPagesOfTrainer.jsp").forward(request, response);
        }
    }

}
