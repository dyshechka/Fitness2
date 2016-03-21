package servlet;

import dao.FitnessDAO;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Subscription;
import models.User;

/**
 *
 * @author Marta
 */
public class ClientController extends HttpServlet {

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
        Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, "client page requested");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !user.getNameRole().equals("Client")) {
            response.sendRedirect("/Fitness/Controller/?goout=1");
            return;
        }
//        request.setAttribute("isSubscription", user.getIdSubscription());
        //request.setAttribute("path", request.getRequestURI()); // TODO
        String requestUrl = request.getRequestURI();
        switch (requestUrl) {
            case "/Fitness/Client/createNewSubscription.jsp":
                request.setAttribute("listTraining", dao.listTraining());
                request.getRequestDispatcher("/clientView/createNewSubscription.jsp").forward(request, response);
                break;
            case "/Fitness/Client/submitCreateNewSubscription.jsp":
                Subscription createdSubscription = new Subscription();
                createdSubscription.setTypeTraining(request.getParameter("trainingType"));
                createdSubscription.setKindTraining(request.getParameter("trainingKind"));
                createdSubscription.setDuration(Integer.valueOf(request.getParameter("duration")));
                createdSubscription.setDateOfPurchase(new Date(System.currentTimeMillis()));
                createdSubscription.setStatus("Оформлен");
                int idSubscription = dao.createSubscription(createdSubscription);

                if (idSubscription == -1) {
                    request.setAttribute("message", "Произошла ошибка");
                } else {
                    user.setIdSubscription(idSubscription);
                    dao.updateUser(user.getIdUser(), user);
                    request.setAttribute("message", "Абонемент создан");
                    request.setAttribute("userName", user.getFullName());
                    request.getSession().setAttribute("user", dao.readUser(user.getIdUser()));
                }
                request.getRequestDispatcher("/clientView/mainPagesOfClient.jsp").forward(request, response);
                break;
            case "/Fitness/Client/deleteSubscription.jsp":
                request.setAttribute("subscription", dao.readSubscription(user.getIdSubscription()));
                request.getRequestDispatcher("/clientView/deleteSubscription.jsp").forward(request, response);
                break;
            case "/Fitness/Client/submitDeleteSubscription.jsp":
                if (!dao.deleteSubscription(user.getIdSubscription())) {
                    request.setAttribute("message", "Произошла ошибка");
                } else {
                    request.setAttribute("message", "Абонемент удален");
                    request.setAttribute("userName", user.getFullName());
                    request.getSession().setAttribute("user", dao.readUser(user.getIdUser()));
                }
                request.getRequestDispatcher("/clientView/mainPagesOfClient.jsp").forward(request, response);

                break;
            case "/Fitness/Client/paySubscription.jsp":
                request.setAttribute("trainers", dao.readTrainers());
                request.getRequestDispatcher("/clientView/paySubscription.jsp").forward(request, response);
                break;
            case "/Fitness/Client/submitPaySubscription.jsp":
                Subscription subscription = dao.readSubscription(user.getIdSubscription());
                subscription.setStatus("Оплачен");
                if (!dao.updateSubscription(subscription.getIdSubscription(), subscription)) {
                    request.setAttribute("message", "Произошла ошибка");
                } else {
                    request.setAttribute("message", "Оплата прошла успешно");
                    request.setAttribute("userName", user.getFullName());
                    request.setAttribute("subscription", dao.readSubscription(user.getIdSubscription()));
                    request.getSession().setAttribute("user", dao.readUser(user.getIdUser()));
                }
                request.getRequestDispatcher("/clientView/mainPagesOfClient.jsp").forward(request, response);
                break;
            case "/Fitness/Client/viewStatusOfSubscription.jsp":
                request.setAttribute("statusSubscription", dao.readStatusSubscription(user.getIdSubscription()).getStatus());
                request.getRequestDispatcher("/clientView/viewStatusOfSubscription.jsp").forward(request, response);
                break;
            case "/Fitness/Client/editDataAboutItself.jsp":
                request.getRequestDispatcher("/clientView/editDataAboutItself.jsp").forward(request, response);
                break;
            case "/Fitness/Client/submitEditDataAboutItself.jsp":
                String fullName = request.getParameter("fullName");
                String email = request.getParameter("email");
                int telephone = Integer.valueOf(request.getParameter("telephone"));
                
                user.setFullName(fullName);
                user.setEmail(email);
                user.setTelephone(telephone);
                if (!dao.updateUser(user.getIdUser(), user)){
                    request.setAttribute("message", "Произошла ошибка");
                } else {
                    request.setAttribute("message", "Данные обновлены");
                    request.setAttribute("userName", user.getFullName());
                    request.getSession().setAttribute("user", dao.readUser(user.getIdUser()));
                }
                request.getRequestDispatcher("/clientView/mainPagesOfClient.jsp").forward(request, response);
                break;
            case "/Fitness/Client/index.jsp":
                request.getRequestDispatcher("/clientView/index.jsp").forward(request, response);
                break;
        }

        if (user == null || !user.getNameRole().equals("Client")) {
            response.sendRedirect("/Fitness/");
        } else {
            request.setAttribute("userName", user.getFullName());
            request.getRequestDispatcher("/clientView/mainPagesOfClient.jsp").forward(request, response);
        }

    }

}
