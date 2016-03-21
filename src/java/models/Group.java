package models;

import java.util.ArrayList;
import java.util.Objects;

public class Group {

    private int idGroup;
    private String nameGroup;
    private ArrayList<UserVisits> usersVisits;
    private String typeTraining;

    public Group() {
        usersVisits = new ArrayList<>();
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }
    
    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public ArrayList<UserVisits> getUsersVisits() {
        return usersVisits;
    }

    public void setUsersVisits(ArrayList<UserVisits> usersVisits) {
        this.usersVisits = usersVisits;
    }
    
    
    public String getTypeTraining() {
        return typeTraining;
    }

    public void setTypeTraining(String typeTraining) {
        this.typeTraining = typeTraining;
    }

    @Override
    public String toString() {
        String str = "Group{" + "idGroup=" + idGroup + ", nameGroup=" + nameGroup + '}';
        for (UserVisits userVisits : usersVisits) {
            str += userVisits.toString();
        }
        return str;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.idGroup;
        hash = 97 * hash + Objects.hashCode(this.nameGroup);
        hash = 97 * hash + Objects.hashCode(this.usersVisits);
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
        final Group other = (Group) obj;
        if (this.idGroup != other.idGroup) {
            return false;
        }
        if (!Objects.equals(this.nameGroup, other.nameGroup)) {
            return false;
        }
        if (!Objects.equals(this.usersVisits, other.usersVisits)) {
            return false;
        }
        return true;
    }
    
    
}
