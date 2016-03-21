package models;

import java.sql.Date;
import java.util.Objects;

public class User {
    private int idUser;
    private String fullName;
    private Date dateOfBirth;
    private String email;
    private int telephone;
    private String login;
    private boolean frozen;     
    private String nameRole;
    private Integer idSubscription;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
  
    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public Integer getIdSubscription() {
        return idSubscription;
    }

    public void setIdSubscription(Integer idSubscription) {
        this.idSubscription = idSubscription;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", telephone=" + telephone + ", login=" + login + ", frozen=" + frozen + ", nameRole=" + nameRole + ", idSubscription=" + idSubscription + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idUser;
        hash = 37 * hash + Objects.hashCode(this.fullName);
        hash = 37 * hash + Objects.hashCode(this.dateOfBirth);
        hash = 37 * hash + Objects.hashCode(this.email);
        hash = 37 * hash + this.telephone;
        hash = 37 * hash + Objects.hashCode(this.login);
        hash = 37 * hash + (this.frozen ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.nameRole);
        hash = 37 * hash + this.idSubscription;
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
        final User other = (User) obj;
        if (this.idUser != other.idUser) {
            return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (this.telephone != other.telephone) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (this.frozen != other.frozen) {
            return false;
        }
        if (!Objects.equals(this.nameRole, other.nameRole)) {
            return false;
        }
        if (this.idSubscription != other.idSubscription) {
            return false;
        }
        return true;
    }
    
    
}
