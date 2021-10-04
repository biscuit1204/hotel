package com.example.tavern.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 
 * </p>
 *
 * @author zcb
 * @since 2021-08-29
 */
@Data
@Component
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Hotel对象", description="")
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "经度")
    private String lat;

    @ApiModelProperty(value = "纬度")
    private String lng;

    @ApiModelProperty(value = "评分")
    private String assignmark;

    @ApiModelProperty(value = "等级")
    private String level;

    @ApiModelProperty(value = "开业时间")
    private Date openDate;

    @ApiModelProperty(value = "删除")
    private String isOut;

    @ApiModelProperty(value = "照片")
    private String img;

    @ApiModelProperty(value = "备注")
    private String note;

    private String wait1;

    private String wait2;

    private String wait3;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAssignmark() {
        return assignmark;
    }

    public void setAssignmark(String assignmark) {
        this.assignmark = assignmark;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public String getIsOut() {
        return isOut;
    }

    public void setIsOut(String isOut) {
        this.isOut = isOut;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getWait1() {
        return wait1;
    }

    public void setWait1(String wait1) {
        this.wait1 = wait1;
    }

    public String getWait2() {
        return wait2;
    }

    public void setWait2(String wait2) {
        this.wait2 = wait2;
    }

    public String getWait3() {
        return wait3;
    }

    public void setWait3(String wait3) {
        this.wait3 = wait3;
    }
}
