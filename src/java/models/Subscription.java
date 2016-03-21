package models;

import java.sql.Date;
import java.util.Objects;

public class Subscription {

    private int idSubscription;
    private int duration;
    private Date dateOfPurchase;
    private String status;

    private String typeTraining;
    private String kindTraining;
    private int price;

    public int getIdSubscription() {
        return idSubscription;
    }

    public void setIdSubscription(int idSubscription) {
        this.idSubscription = idSubscription;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeTraining() {
        return typeTraining;
    }

    public void setTypeTraining(String typeTraining) {
        this.typeTraining = typeTraining;
    }

    public String getKindTraining() {
        return kindTraining;
    }

    public void setKindTraining(String kindTraining) {
        this.kindTraining = kindTraining;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.idSubscription;
        hash = 53 * hash + this.duration;
        hash = 53 * hash + Objects.hashCode(this.dateOfPurchase);
        hash = 53 * hash + Objects.hashCode(this.typeTraining);
        hash = 53 * hash + Objects.hashCode(this.kindTraining);
        hash = 53 * hash + this.price;
        hash = 53 * hash + Objects.hashCode(this.status);
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
        final Subscription other = (Subscription) obj;
        if (this.idSubscription != other.idSubscription) {
            return false;
        }
        if (this.duration != other.duration) {
            return false;
        }
        if (!Objects.equals(this.dateOfPurchase, other.dateOfPurchase)) {
            return false;
        }
        if (!Objects.equals(this.typeTraining, other.typeTraining)) {
            return false;
        }
        if (!Objects.equals(this.kindTraining, other.kindTraining)) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }

    
}
