package com.shu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "readertype")
public class ReadertypeEntity {
    private int id;
    private String typename;
    private Integer maxborrownum;
    private Integer limitday;

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

    public Integer getMaxborrownum() {
        return maxborrownum;
    }

    public void setMaxborrownum(Integer maxborrownum) {
        this.maxborrownum = maxborrownum;
    }

    public Integer getLimitday() {
        return limitday;
    }

    public void setLimitday(Integer limitday) {
        this.limitday = limitday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadertypeEntity that = (ReadertypeEntity) o;

        if (id != that.id) return false;
        if (typename != null ? !typename.equals(that.typename) : that.typename != null) return false;
        if (maxborrownum != null ? !maxborrownum.equals(that.maxborrownum) : that.maxborrownum != null) return false;
        if (limitday != null ? !limitday.equals(that.limitday) : that.limitday != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (typename != null ? typename.hashCode() : 0);
        result = 31 * result + (maxborrownum != null ? maxborrownum.hashCode() : 0);
        result = 31 * result + (limitday != null ? limitday.hashCode() : 0);
        return result;
    }
}
