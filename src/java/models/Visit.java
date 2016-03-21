package models;

import java.sql.Date;
import java.util.Objects;

public class Visit {

    private Date dateOfVisit;
    private boolean visited;

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Visit{" + "dateOfVisit=" + dateOfVisit + ", visited=" + visited + '}';
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.dateOfVisit);
        hash = 89 * hash + (this.visited ? 1 : 0);
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
        final Visit other = (Visit) obj;
        if (!Objects.equals(this.dateOfVisit, other.dateOfVisit)) {
            return false;
        }
        if (this.visited != other.visited) {
            return false;
        }
        return true;
    }
    
}
