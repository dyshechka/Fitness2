package models;

import java.util.ArrayList;
import java.util.Objects;

public class UserVisits {

    private User user;
    private ArrayList<Visit> visits;

    public UserVisits() {
        visits = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Visit> getVisits() {
        return visits;
    }

    public void setVisits(ArrayList<Visit> visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        return "UserVisits{" + "user=" + user + ", visits=" + visits + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.user);
        hash = 79 * hash + Objects.hashCode(this.visits);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserVisits other = (UserVisits) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.visits, other.visits)) {
            return false;
        }
        return true;
    }

}
