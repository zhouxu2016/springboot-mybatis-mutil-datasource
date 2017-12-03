package org.spring.springboot.domain;


/**
 * Work工作地点对应的实体类
 */
public class Work {

    private Integer id;
    private String workAddress;
    private String company;
    private String name;

//    {"id": "1","workAddress": "北京","company": "北京","name": "常远"}

    public Work() {
    }

    public Work(Integer id, String workAddress, String company, String name) {
        this.id = id;
        this.workAddress = workAddress;
        this.company = company;
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", workAddress='" + workAddress + '\'' +
                ", company='" + company + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
