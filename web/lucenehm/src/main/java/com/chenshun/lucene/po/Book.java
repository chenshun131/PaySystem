package com.chenshun.lucene.po;

/**
 * User: chenshun131 <p />
 * Time: 18/2/22 23:03  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Book {

    /** 图书ID */
    private Integer id;

    /** 图书名称 */
    private String name;

    /** 图书价格 */
    private Float price;

    /** 图书图片 */
    private String pic;

    /** 图书描述 */
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
