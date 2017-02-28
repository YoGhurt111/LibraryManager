package com.shu.entity;

import javax.persistence.Entity;
import java.sql.Date;

/**
 * Created by Dell on 2017/2/27.
 */
@Entity
public class BorrowbookEntity {
    private String readerid;
    private String isbn;
    private Date borrowdate;
    private Date returndate;
    private Double fine;

    public String getReaderid() {
        return readerid;
    }

    public void setReaderid(String readerid) {
        this.readerid = readerid;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(Date borrowdate) {
        this.borrowdate = borrowdate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BorrowbookEntity that = (BorrowbookEntity) o;

        if (readerid != null ? !readerid.equals(that.readerid) : that.readerid != null) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (borrowdate != null ? !borrowdate.equals(that.borrowdate) : that.borrowdate != null) return false;
        if (returndate != null ? !returndate.equals(that.returndate) : that.returndate != null) return false;
        if (fine != null ? !fine.equals(that.fine) : that.fine != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = readerid != null ? readerid.hashCode() : 0;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (borrowdate != null ? borrowdate.hashCode() : 0);
        result = 31 * result + (returndate != null ? returndate.hashCode() : 0);
        result = 31 * result + (fine != null ? fine.hashCode() : 0);
        return result;
    }
}
