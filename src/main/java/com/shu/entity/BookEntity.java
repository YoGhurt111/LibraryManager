package com.shu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Dell on 2017/2/27.
 */
@Entity
@Table(name = "book")
public class BookEntity {
    private String isbn;
    private String typeid;
    private String bookname;
    private String author;
    private String publish;
    private Date publishdate;
    private Integer printtime;
    private Double unitprice;

    @Id
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public Integer getPrinttime() {
        return printtime;
    }

    public void setPrinttime(Integer printtime) {
        this.printtime = printtime;
    }

    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (typeid != null ? !typeid.equals(that.typeid) : that.typeid != null) return false;
        if (bookname != null ? !bookname.equals(that.bookname) : that.bookname != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (publish != null ? !publish.equals(that.publish) : that.publish != null) return false;
        if (publishdate != null ? !publishdate.equals(that.publishdate) : that.publishdate != null) return false;
        if (printtime != null ? !printtime.equals(that.printtime) : that.printtime != null) return false;
        if (unitprice != null ? !unitprice.equals(that.unitprice) : that.unitprice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = isbn != null ? isbn.hashCode() : 0;
        result = 31 * result + (typeid != null ? typeid.hashCode() : 0);
        result = 31 * result + (bookname != null ? bookname.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (publish != null ? publish.hashCode() : 0);
        result = 31 * result + (publishdate != null ? publishdate.hashCode() : 0);
        result = 31 * result + (printtime != null ? printtime.hashCode() : 0);
        result = 31 * result + (unitprice != null ? unitprice.hashCode() : 0);
        return result;
    }
}
