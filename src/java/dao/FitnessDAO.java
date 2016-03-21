package dao;

// <editor-fold desc="/* Импорт */">
import com.sun.org.apache.xpath.internal.axes.SubContextList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import models.Group;
import models.Subscription;
import models.User;
import models.UserVisits;
import models.Visit;
// </editor-fold>

public class FitnessDAO {

    private Connection conn;
    private boolean isTested;

    public FitnessDAO() {
        this(false);
    }

    public FitnessDAO(boolean isTested) {
        this.isTested = isTested;
    }

    // <editor-fold desc="/* Подключение к БД */">
    private void connect() {
        if (!isTested) {
            InitialContext context;
            try {
                context = new InitialContext();
                DataSource dataSource = (DataSource) context.lookup("jdbc/fitness");
                conn = dataSource.getConnection();
            } catch (NamingException | SQLException ex) {
                Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitness", "root", "root");
            } catch (SQLException ex) {
                Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void close() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // </editor-fold>

    // <editor-fold desc="/* CRUD для пользователя */">
    public ArrayList<User> readAllUsers() {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("SELECT idUser, fullName, "
                    + "dateOfBirth, email, telephone, login, nameRole, frozen, idSubscription "
                    + "FROM user JOIN role USING (idRole)");
            ResultSet res = statement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (res.next()) {
                User user = new User();
                user.setIdUser(res.getInt(1));
                user.setFullName(res.getString(2));
                user.setDateOfBirth(res.getDate(3));
                user.setEmail(res.getString(4));
                user.setTelephone(res.getInt(5));
                user.setLogin(res.getString(6));
                user.setNameRole(res.getString(7));
                user.setFrozen(res.getBoolean(8));
                user.setIdSubscription(res.getInt(9));
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return new ArrayList<>();
    }

    public ArrayList<User> listRoles() {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("SELECT nameRole "
                    + "FROM role");
            ResultSet res = statement.executeQuery();
            ArrayList<User> roles = new ArrayList<>();
            while (res.next()) {
                User role = new User();
                role.setNameRole(res.getString(1));
                roles.add(role);
            }
            return roles;
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return new ArrayList<>();
    }

    public User readUser(int idUser) {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("SELECT fullName, "
                    + "dateOfBirth, email, telephone, login, nameRole, frozen, idSubscription "
                    + "FROM user JOIN role USING (idRole)"
                    + "WHERE idUser=?");
            statement.setInt(1, idUser);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                User user = new User();
                user.setIdUser(idUser);
                user.setFullName(res.getString(1));
                user.setDateOfBirth(res.getDate(2));
                user.setEmail(res.getString(3));
                user.setTelephone(res.getInt(4));
                user.setLogin(res.getString(5));
                user.setNameRole(res.getString(6));
                user.setFrozen(res.getBoolean(7));
                user.setIdSubscription(res.getInt(8));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return null;
    }

    public ArrayList<User> readTrainers() {
        try {
            int idRole = getIdRoleByName("Trainer");
            if (idRole != -1) {
                connect();
                PreparedStatement statement = conn.prepareStatement("SELECT idUser, fullName, idRole "
                        + "FROM user JOIN role USING (idRole) "
                        + "WHERE idRole=?");
                statement.setInt(1, idRole);
                ResultSet res = statement.executeQuery();
                ArrayList<User> trainers = new ArrayList<>();
                while (res.next()) {
                    User trainer = new User();
                    trainer.setIdUser(res.getInt(1));
                    trainer.setFullName(res.getString(2));
                    trainers.add(trainer);
                }
                return trainers;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return new ArrayList<>();
    }

    public int createUser(User user, String password) {
        try {
            int idRole = getIdRoleByName(user.getNameRole());
            if (idRole != -1) {
                connect();
                PreparedStatement statement = conn.prepareStatement("INSERT INTO user "
                        + "(fullName, dateOfBirth, email, telephone, login, password, idRole, frozen) "
                        + "VALUES(?,?,?,?,?,MD5(?),?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getFullName());
                statement.setDate(2, user.getDateOfBirth());
                statement.setString(3, user.getEmail());
                statement.setInt(4, user.getTelephone());
                statement.setString(5, user.getLogin());
                statement.setString(6, password);
                statement.setInt(7, idRole);
                statement.setBoolean(8, true);
                statement.execute();
                ResultSet key = statement.getGeneratedKeys();
                if (key.next()) {
                    int a = key.getInt(1);
                    return a;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return -1;
    }

    public boolean updateUser(int idUser, User user) {
        try {
            int idRole = getIdRoleByName(user.getNameRole());
            if (idRole != -1) {
                connect();
                PreparedStatement statement = conn.prepareStatement("UPDATE user SET "
                        + "fullName=?, dateOfBirth=?, email=?, telephone=?, login=?, "
                        + "idRole=?, frozen=?, idSubscription=? WHERE idUser=?");
                statement.setString(1, user.getFullName());
                statement.setDate(2, user.getDateOfBirth());
                statement.setString(3, user.getEmail());
                statement.setInt(4, user.getTelephone());
                statement.setString(5, user.getLogin());
                statement.setInt(6, idRole);
                statement.setBoolean(7, false);
                statement.setInt(8, user.getIdSubscription());
                statement.setInt(9, idUser);
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return false;
    }

    public boolean deleteUser(int idUser) {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM user WHERE idUser=?");
            statement.setInt(1, idUser);
            statement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return false;
    }

    public int getIdRoleByName(String nameRole) {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("SELECT  idRole "
                    + "FROM role "
                    + "WHERE nameRole=?");
            statement.setString(1, nameRole);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return res.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            return -1;
        } finally {
            close();
        }
    }

    public int getIdSubscriptionFromSubscription(int idSubscription) {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("SELECT  idSubscription "
                    + "FROM subscription JOIN user "
                    + "USING (?)");
            statement.setInt(1, idSubscription);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return res.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            return -1;
        } finally {
            close();
        }
    }

    // </editor-fold>
    // <editor-fold desc="/* CRUD для абонемента */">
    public ArrayList<Subscription> readAllSubscriptions() {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("SELECT idSubscription, duration, "
                    + "dateOfPurchase, status, typeTraining, kindTraining, price "
                    + "FROM subscription JOIN trainingProgram USING (idTraining)");
            ResultSet res = statement.executeQuery();
            ArrayList<Subscription> subscriptions = new ArrayList<>();
            while (res.next()) {
                Subscription subscription = new Subscription();
                subscription.setIdSubscription(res.getInt(1));
                subscription.setDuration(res.getInt(2));
                subscription.setDateOfPurchase(res.getDate(3));
                subscription.setStatus(res.getString(4));
                subscription.setTypeTraining(res.getString(5));
                subscription.setKindTraining(res.getString(6));
                subscription.setPrice(res.getInt(7));
                subscriptions.add(subscription);
            }
            return subscriptions;
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return new ArrayList<>();
    }

    public Subscription readSubscription(int idSubscription) {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("SELECT duration, "
                    + "dateOfPurchase, status, typeTraining, kindTraining, price "
                    + "FROM subscription JOIN trainingProgram USING (idTraining) "
                    + "WHERE idSubscription=?");
            statement.setInt(1, idSubscription);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                Subscription subscription = new Subscription();
                subscription.setDuration(res.getInt(1));
                subscription.setDateOfPurchase(res.getDate(2));
                subscription.setStatus(res.getString(3));
                subscription.setTypeTraining(res.getString(4));
                subscription.setKindTraining(res.getString(5));
                subscription.setPrice(res.getInt(6));
                subscription.setIdSubscription(idSubscription);
                return subscription;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return null;
    }

    public Subscription readStatusSubscription(int idSubscription) {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("SELECT status "
                    + "FROM subscription "
                    + "WHERE idSubscription=?");
            statement.setInt(1, idSubscription);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                Subscription statusSubscription = new Subscription();
                statusSubscription.setStatus(res.getString(1));
                return statusSubscription;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return null;
    }

    public ArrayList<Subscription> listTraining() {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("SELECT typeTraining, kindTraining "
                    + "FROM trainingProgram");
            ResultSet res = statement.executeQuery();
            ArrayList<Subscription> trainings = new ArrayList<>();
            while (res.next()) {
                Subscription training = new Subscription();
                training.setTypeTraining(res.getString(1));
                training.setKindTraining(res.getString(2));
                trainings.add(training);
            }
            return trainings;        
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return new ArrayList<>();
    }

    public int createSubscription(Subscription subscription) {
        try {
            int idTraining = getIdTrainigByKindType(subscription.getTypeTraining(),
                    subscription.getKindTraining());
            if (idTraining != -1) {
                connect();
                PreparedStatement statement = conn.prepareStatement(
                        "INSERT INTO subscription "
                        + "(duration, dateOfPurchase, idTraining, status) "
                        + "VALUES(?,?,?,?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setInt(1, subscription.getDuration());
                statement.setDate(2, subscription.getDateOfPurchase());
                statement.setInt(3, idTraining);
                statement.setString(4, subscription.getStatus());
                statement.executeUpdate();
                ResultSet key = statement.getGeneratedKeys();
                if (key.next()) {
                    return key.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return -1;
    }

    public boolean updateSubscription(int idSubscription, Subscription subscription) {
        try {
            int idTraining = getIdTrainigByKindType(subscription.getTypeTraining(),
                    subscription.getKindTraining());
            if (idTraining != -1) {
                connect();
                PreparedStatement statement = conn.prepareStatement(
                        "UPDATE subscription SET "
                        + "duration=?, dateOfPurchase=?, idTraining=?, status=? "
                        + "WHERE idSubscription=?");
                statement.setInt(1, subscription.getDuration());
                statement.setDate(2, subscription.getDateOfPurchase());
                statement.setInt(3, idTraining);
                statement.setString(4, subscription.getStatus());
                statement.setInt(5, idSubscription);
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return false;
    }

    public boolean deleteSubscription(int idSubscription) {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM subscription WHERE idSubscription=?");
            statement.setInt(1, idSubscription);
            statement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return false;
    }

    public int getIdTrainigByKindType(String typeTraining, String kindTraining) {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("SELECT  idTraining "
                    + "FROM trainingProgram "
                    + "WHERE typeTraining=? AND kindTraining=?");
            statement.setString(1, typeTraining);
            statement.setString(2, kindTraining);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return res.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            return -1;
        } finally {
            close();
        }
    }
    // </editor-fold>

    // <editor-fold desc="/* CRUD для группы */">
    public ArrayList<Group> readAllGroups() {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("SELECT idGroup "
                    + "FROM `group`");
            ResultSet res = statement.executeQuery();

            ArrayList<Integer> idGroups = new ArrayList<>();
            while (res.next()) {
                idGroups.add(res.getInt(1));
            }

            ArrayList<Group> groups = new ArrayList<>();
            for (int idGroup : idGroups) {
                statement = conn.prepareStatement("SELECT idUser "
                        + "FROM user "
                        + "WHERE idGroup=?");
                statement.setInt(1, idGroup);
                res = statement.executeQuery();
                ArrayList<Integer> idUsers = new ArrayList<>();
                while (res.next()) {
                    idUsers.add(res.getInt(1));
                }

                ArrayList<UserVisits> usersVisits = new ArrayList<>();
                for (int id : idUsers) {
                    UserVisits userVisits = new UserVisits();
                    userVisits.setUser(readUser(id));
                    userVisits.setVisits(getUserVisits(id));
                    usersVisits.add(userVisits);
                }
                connect();
                statement = conn.prepareStatement("SELECT nameGroup, typeTraining "
                        + "FROM `group` JOIN trainingprogram "
                        + "USING (idTraining) "
                        + "WHERE idGroup=?");
                statement.setInt(1, idGroup);
                res = statement.executeQuery();

                if (res.next()) {
                    Group group = new Group();
                    group.setIdGroup(idGroup);
                    group.setNameGroup(res.getString(1));
                    group.setTypeTraining(res.getString(2));
                    group.setUsersVisits(usersVisits);
                    groups.add(group);
                }
            }
            return groups;
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return new ArrayList<>();
    }

    public ArrayList<Group> listNameGroups() {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("SELECT nameGroup "
                    + "FROM `group`");
            ResultSet res = statement.executeQuery();
            ArrayList<Group> namegroups = new ArrayList<>();
            while (res.next()) {
                Group namegroup = new Group();
                namegroup.setNameGroup(res.getString(1));
                namegroups.add(namegroup);
            }
            return namegroups;
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return new ArrayList<>();
    }

    public Group readGroup(int idGroup) {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("SELECT idUser "
                    + "FROM user "
                    + "WHERE idGroup=?");
            statement.setInt(1, idGroup);
            ResultSet res = statement.executeQuery();
            ArrayList<Integer> idUsers = new ArrayList<>();
            while (res.next()) {
                idUsers.add(res.getInt(1));
            }
            ArrayList<UserVisits> usersVisits = new ArrayList<>();
            for (int id : idUsers) {
                UserVisits userVisits = new UserVisits();
                userVisits.setUser(readUser(id));
                userVisits.setVisits(getUserVisits(id));
                usersVisits.add(userVisits);
            }
            connect();
            statement = conn.prepareStatement("SELECT nameGroup, typeTraining "
                    + "FROM `group` JOIN trainingprogram "
                    + "USING (idTraining) "
                    + "WHERE idGroup=?");
            statement.setInt(1, idGroup);
            res = statement.executeQuery();
            if (res.next()) {
                Group group = new Group();
                group.setNameGroup(res.getString(1));
                group.setTypeTraining(res.getString(2));
                group.setUsersVisits(usersVisits);
                group.setIdGroup(idGroup);
                return group;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return null;
    }

    public int createGroup(Group group) {
        try {
            int idTraining = getIdTrainigByKindType(group.getTypeTraining(), "Групповой");
            if (idTraining != -1) {

                connect();
                PreparedStatement statement = conn.prepareStatement("INSERT INTO `group` "
                        + "(nameGroup, idTraining) VALUES (?,?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1, group.getNameGroup());
                statement.setInt(2, idTraining);
                statement.executeUpdate();
                ResultSet key = statement.getGeneratedKeys();
                if (key.next()) {
                    int a = key.getInt(1);
                    return a;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return -1;

    }

    public boolean updateGroup(int idGroup, Group group) {
        try {
            int idTraining = getIdTrainigByKindType(group.getTypeTraining(), "Групповой");
            if (idTraining != -1) {
                connect();
                PreparedStatement statement = conn.prepareStatement("UPDATE `group` SET "
                        + "nameGroup=?, idTraining=? WHERE idGroup=?");
                statement.setString(1, group.getNameGroup());
                statement.setInt(2, idTraining);
                statement.setInt(3, idGroup);
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return false;
    }

    public boolean deleteGroup(int idGroup) {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM `group` WHERE idGroup=?");
            statement.setInt(1, idGroup);
            statement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return false;
    }

    public ArrayList<Visit> getUserVisits(int idUser) {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("SELECT "
                    + "dateOfVisit, visit "
                    + "FROM monitoringvisit "
                    + "WHERE idUser=?");
            statement.setInt(1, idUser);
            ResultSet res = statement.executeQuery();
            ArrayList<Visit> visits = new ArrayList<>();
            while (res.next()) {
                Visit visit = new Visit();
                visit.setDateOfVisit(res.getDate(1));
                visit.setVisited(res.getBoolean(2));
                visits.add(visit);
            }
            return visits;
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return new ArrayList<>();
    }

    public User findUser(String login, String password) {
        try {
            connect();
            PreparedStatement statement = conn.prepareStatement("SELECT "
                    + "fullName, "
                    + "dateOfBirth, email, telephone, login, nameRole, frozen, "
                    + "u.idUser as id, idSubscription "
                    + "FROM user u JOIN role USING (idRole)"
                    + "WHERE login=? AND password=md5(?)");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                User user = new User();
                user.setFullName(res.getString(1));
                user.setDateOfBirth(res.getDate(2));
                user.setEmail(res.getString(3));
                user.setTelephone(res.getInt(4));
                user.setLogin(res.getString(5));
                user.setNameRole(res.getString(6));
                user.setFrozen(res.getBoolean(7));
                user.setIdUser(res.getInt(8));
                user.setIdSubscription(res.getInt(9));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FitnessDAO.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            close();
        }
        return null;
    }
}
// </editor-fold>
