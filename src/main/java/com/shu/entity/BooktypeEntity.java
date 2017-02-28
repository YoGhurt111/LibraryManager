package com.shu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Dell on 2017/2/27.
 */
@Entity
@Table(name = "booktype")
public class BooktypeEntity {
    private int id;
    private String typename;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooktypeEntity that = (BooktypeEntity) o;

        if (id != that.id) return false;
        if (typename != null ? !typename.equals(that.typename) : that.typename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (typename != null ? typename.hashCode() : 0);
        return result;
    }
}
