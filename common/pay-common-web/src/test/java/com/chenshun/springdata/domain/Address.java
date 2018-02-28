package com.chenshun.springdata.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: chenshun131 <p />
 * Time: 18/2/28 10:12  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Table(name = "JPA_ADDRESSES")
@Entity
public class Address {

    private Integer id;

    private String province;

    private String city;

    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
