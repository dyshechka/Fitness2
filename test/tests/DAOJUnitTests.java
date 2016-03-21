package tests;

import dao.FitnessDAO;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import junit.framework.Assert;
import models.Group;
import models.Subscription;
import models.User;
import models.UserVisits;
import models.Visit;
import org.junit.Test;

public class DAOJUnitTests {

    private FitnessDAO dao;
    private SimpleDateFormat format;

    public DAOJUnitTests() {
        dao = new FitnessDAO(true);
        format = new SimpleDateFormat("yyyy-MM-dd");
    }

    // <editor-fold desc="/* Тестовые CRUD для Пользователя */">
    @Test
    public void createUser() {
        int id = createDummyUser();
        Assert.assertFalse(-1 == id);
    }

    @Test
    public void readAllUsers() {
        int id = createDummyUser();
        ArrayList<User> users = dao.readAllUsers();
        int newId = createDummyUser();
        ArrayList<User> newUsers = dao.readAllUsers();
        dao.deleteUser(id);
        dao.deleteUser(newId);
        Assert.assertNotSame(newUsers, users);
    }

    @Test
    public void readUser() {
        int id = createDummyUser();
        User user = dao.readUser(id);
       // dao.deleteUser(id);
       // Assert.assertNotNull(user);
    }

    @Test
    public void updateUser() {
        int id = createDummyUser();
        User user = dao.readUser(id);
        User newUser = dao.readUser(id);
        newUser.setLogin("qwerty");
        dao.updateUser(id, newUser);
        newUser = dao.readUser(id);
        dao.deleteUser(id);
        Assert.assertFalse(newUser.equals(user));
    }

    @Test
    public void deleteUser() {
        int id = createDummyUser();
        Assert.assertTrue(dao.deleteUser(id));
    }
    // </editor-fold>*/

    // <editor-fold desc="/* Тестовые CRUD для Группы */">
    @Test
    public void createGroup() {
        int id = createDummyGroup();
        dao.deleteGroup(id);
        Assert.assertFalse(-1 == id);
    }

    @Test
    public void readAllGroups() {
        int id = createDummyGroup();
        ArrayList<Group> groups = dao.readAllGroups();
        int newId = createDummyGroup();
        ArrayList<Group> newGroups = dao.readAllGroups();
        dao.deleteGroup(id);
        dao.deleteGroup(newId);
        Assert.assertNotSame(newGroups, groups);
    }

    @Test
    public void readGroup() {
        int id = createDummyGroup();
        Group group = dao.readGroup(id);
        dao.deleteGroup(id);
        Assert.assertNotNull(group);
    }

    @Test
    public void updateGroup() {
        int id = createDummyGroup();
        Group group = dao.readGroup(id);
        Group newGroup = dao.readGroup(id);
        newGroup.setNameGroup("Штаны на лямках");
        dao.updateGroup(id, newGroup);
        newGroup = dao.readGroup(id);
        dao.deleteUser(id);
        Assert.assertFalse(newGroup.equals(group));
    }

    @Test
    public void deleteGroup() {
        int id = createDummyGroup();
        Assert.assertTrue(dao.deleteGroup(id));
    }
    // </editor-fold>

    // <editor-fold desc="/* Тестовые CRUD для Абонемента */">
    @Test
    public void createSubscription() {
        int id = createDummySubscription();
        dao.deleteSubscription(id);
        Assert.assertFalse(-1 == id);
    }

    @Test
    public void readAllSubscription() {
        int id = createDummySubscription();
        ArrayList<Subscription> subscriptions = dao.readAllSubscriptions();
        int newId = createDummySubscription();
        ArrayList<Subscription> newSubscription = dao.readAllSubscriptions();
        dao.deleteGroup(id);
        dao.deleteGroup(newId);
        Assert.assertNotSame(newSubscription, subscriptions);
    }

    @Test
    public void readSubscription() {
        int id = createDummySubscription();
        Subscription subscription = dao.readSubscription(id);
        dao.deleteSubscription(id);
        Assert.assertNotNull(subscription);
    }

    @Test
    public void updateSubscription() {
        int id = createDummySubscription();
        Subscription subscription = dao.readSubscription(id);
        Subscription newSubscription = dao.readSubscription(id);
        newSubscription.setDuration(99);
        dao.updateSubscription(id, newSubscription);
        newSubscription = dao.readSubscription(id);
        dao.deleteUser(id);
        Assert.assertFalse(newSubscription.equals(subscription));
    }

    @Test
    public void deleteSubscription() {
        int id = createDummySubscription();
        Assert.assertTrue(dao.deleteSubscription(id));
    }
    // </editor-fold>

    // <editor-fold desc="/* Тестовые CRUD для фейкового Пользователя */">
    @Test
    public void createFakeUser() {
        int id = createFakeUserTest();
        dao.deleteUser(id);
        Assert.assertFalse(-1 == id);
    }

    @Test
    public void readAllFakeUsers() {
        int id = createFakeUserTest();
        ArrayList<User> users = dao.readAllUsers();
        int newId = createFakeUserTest();
        ArrayList<User> newUsers = dao.readAllUsers();
        dao.deleteUser(id);
        dao.deleteUser(newId);
        Assert.assertNotSame(newUsers, users);
    }

    @Test
    public void readFakeUser() {
        int id = createFakeUserTest();
        User user = dao.readUser(id);
        dao.deleteUser(id);
        Assert.assertNotNull(user);
    }

    @Test
    public void updateFakeUser() {
        int id = createFakeUserTest();
        User user = dao.readUser(id);
        User newUser = dao.readUser(id);
        newUser.setLogin("qwerty");
        dao.updateUser(id, newUser);
        newUser = dao.readUser(id);
        dao.deleteUser(id);
        Assert.assertFalse(newUser.equals(user));
    }

    @Test
    public void deleteFakeUser() {
        int id = createFakeUserTest();
        Assert.assertTrue(dao.deleteUser(id));
    }
    // </editor-fold>

    // <editor-fold desc="/* Тестовые CRUD для фейковой Группы */">
    @Test
    public void createFakeGroup() {
        int id = createFakeGroupTest();
        dao.deleteGroup(id);
        Assert.assertFalse(-1 == id);
    }

    @Test
    public void readAllFakeGroups() {
        int id = createFakeGroupTest();
        ArrayList<Group> groups = dao.readAllGroups();
        int newId = createFakeGroupTest();
        ArrayList<Group> newGroups = dao.readAllGroups();
        dao.deleteGroup(id);
        dao.deleteGroup(newId);
        Assert.assertNotSame(newGroups, groups);
    }

    @Test
    public void readFakeGroup() {
        int id = createFakeGroupTest();
        Group group = dao.readGroup(id);
        dao.deleteGroup(id);
        Assert.assertNotNull(group);
    }

    @Test
    public void updateFakeGroup() {
        int id = createFakeGroupTest();
        Group group = dao.readGroup(id);
        Group newGroup = dao.readGroup(id);
        newGroup.setNameGroup("Штаны на лямках");
        dao.updateGroup(id, newGroup);
        newGroup = dao.readGroup(id);
        dao.deleteUser(id);
        Assert.assertFalse(newGroup.equals(group));
    }

    @Test
    public void deleteFakeGroup() {
        int id = createFakeGroupTest();
        Assert.assertTrue(dao.deleteGroup(id));
    }
    // </editor-fold>

    // <editor-fold desc="/* Тестовые CRUD для фейкового Абонемента */">
    @Test
    public void createFakeSubscription() {
        int id = createFakeSubscriptionTest();
        dao.deleteSubscription(id);
        Assert.assertFalse(-1 == id);
    }

    @Test
    public void readAllFakeSubscription() {
        int id = createFakeSubscriptionTest();
        ArrayList<Subscription> subscriptions = dao.readAllSubscriptions();
        int newId = createFakeSubscriptionTest();
        ArrayList<Subscription> newSubscription = dao.readAllSubscriptions();
        dao.deleteGroup(id);
        dao.deleteGroup(newId);
        Assert.assertNotSame(newSubscription, subscriptions);
    }

    @Test
    public void readFakeSubscription() {
        int id = createFakeSubscriptionTest();
        Subscription subscription = dao.readSubscription(id);
        dao.deleteSubscription(id);
        Assert.assertNotNull(subscription);
    }

    @Test
    public void updateFakeSubscription() {
        int id = createFakeSubscriptionTest();
        Subscription subscription = dao.readSubscription(id);
        Subscription newSubscription = dao.readSubscription(id);
        newSubscription.setDuration(99);
        dao.updateSubscription(id, newSubscription);
        newSubscription = dao.readSubscription(id);
        dao.deleteUser(id);
        Assert.assertFalse(newSubscription.equals(subscription));
    }

    @Test
    public void deleteFakeSubscription() {
        int id = createFakeSubscriptionTest();
        Assert.assertTrue(dao.deleteSubscription(id));
    }
    // </editor-fold>

    // <editor-fold desc="/* Заглушка для Пользователя */">
    private int createDummyUser() {
        return dao.createUser(getDummyUser(), "piupiu0203");
    }

    private User getDummyUser() {
        User user = new User();
        user.setFullName("Харитонова Марта Леонидовна");
        user.setDateOfBirth(getDate("1995-03-19"));
        user.setEmail("hariton@mail.ru");
        user.setTelephone(12345);
        user.setLogin("Martha");
        user.setFrozen(true);
        user.setNameRole("User");
        //user.setIdSubscription(65);
        return user;
    }
    // </editor-fold>    

    // <editor-fold desc="/* Заглушка для Группы */">
    public int createDummyGroup() {
        return dao.createGroup(getDummyGroup());
    }

    private Group getDummyGroup() {
        Visit visit = new Visit();
        visit.setVisited(true);
        visit.setDateOfVisit(getDate("2015-11-10"));

        User user = getDummyUser();

        UserVisits userVisits = new UserVisits();
        userVisits.setUser(user);
        ArrayList<Visit> visits = new ArrayList<>();
        visits.add(visit);
        userVisits.setVisits(visits);

        Group group = new Group();
        group.setNameGroup("Для набора массы");
        ArrayList<UserVisits> usersVisits = new ArrayList<>();
        usersVisits.add(userVisits);
        group.setUsersVisits(usersVisits);
        return group;
    }
    // </editor-fold>

    // <editor-fold desc="/* Заглушка для Абонемента */">
    private int createDummySubscription() {
        return dao.createSubscription(getDummySubscription());
    }

    private Subscription getDummySubscription() {
        Subscription subscription = new Subscription();
        subscription.setDuration(4); //Срок действия абонемента в месяцах
        subscription.setDateOfPurchase(getDate("2015-11-10"));
        subscription.setKindTraining("Групповой");
        subscription.setTypeTraining("Похудение");
        subscription.setPrice(1100);
        return subscription;
    }
    // </editor-fold>

    // <editor-fold desc="/* Заглушка для фейкового Пользователя */">
    private int createFakeUserTest() {
        return dao.createUser(getFakeUser(), "piupiu0203");
    }

    private User getFakeUser() {
        User user = new User();
        user.setIdUser(1);
        user.setFullName("Харитонова Марта Леонидовна");
        user.setDateOfBirth(getDate("1995-03-19"));
        user.setEmail("hariton@mail.ru");
        user.setTelephone(12345);
        user.setLogin("Martha");
        user.setFrozen(false);
        user.setNameRole("User");
        return user;
    }
    // </editor-fold>

    // <editor-fold desc="/* Заглушка для фейковой Группы */">
    public int createFakeGroupTest() {
        return dao.createGroup(getDummyGroup());
    }

    private Group getFakeGroup() {
        Visit visit = new Visit();
        visit.setVisited(true);
        visit.setDateOfVisit(getDate("2015-11-10"));

        User user = getDummyUser();

        UserVisits userVisits = new UserVisits();
        userVisits.setUser(user);
        ArrayList<Visit> visits = new ArrayList<>();
        visits.add(visit);
        userVisits.setVisits(visits);

        Group group = new Group();
        group.setIdGroup(1);
        group.setNameGroup("Для набора массы");
        ArrayList<UserVisits> usersVisits = new ArrayList<>();
        usersVisits.add(userVisits);
        group.setUsersVisits(usersVisits);
        return group;
    }
    // </editor-fold>

    // <editor-fold desc="/* Заглушка для фейкового Абонемента */">
    private int createFakeSubscriptionTest() {
        return dao.createSubscription(getDummySubscription());
    }

    private Subscription getFakeSubscription() {
        Subscription subscription = new Subscription();
        subscription.setIdSubscription(1);
        subscription.setDuration(4); //Срок действия абонемента в месяцах
        subscription.setDateOfPurchase(getDate("2015-11-10"));
        subscription.setKindTraining("Групповой");
        subscription.setTypeTraining("Похудение");
        subscription.setPrice(1100);
        return subscription;
    }
    // </editor-fold>

    // <editor-fold desc="/* Преобразование даты */">
    private Date getDate(String date) {
        try {
            return new Date(format.parse(date).getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    // </editor-fold>
}
