package site.zhangsun.security.pojo.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Course extends CourseKey {
    private Integer type;

    private String name;

    private String teacher;

    private String status;

    private Integer year;

    private Date startTime;

    private Date endTime;

    private String orientedPeople;

    private String location;

    private Boolean deleted;

    private Integer creator;

    private Date createTime;

    private Integer updater;

    private Date updateTime;

    private BigDecimal lengthOfHour;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher == null ? null : teacher.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOrientedPeople() {
        return orientedPeople;
    }

    public void setOrientedPeople(String orientedPeople) {
        this.orientedPeople = orientedPeople == null ? null : orientedPeople.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdater() {
        return updater;
    }

    public void setUpdater(Integer updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getLengthOfHour() {
        return lengthOfHour;
    }

    public void setLengthOfHour(BigDecimal lengthOfHour) {
        this.lengthOfHour = lengthOfHour;
    }
}