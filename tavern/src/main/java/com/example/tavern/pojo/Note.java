package com.example.tavern.pojo;

import java.math.BigDecimal;
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
@ApiModel(value="Note对象", description="")
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "创建用户")
    private String createUser;

    @ApiModelProperty(value = "创建时间 ")
    private Date createTime;

    @ApiModelProperty(value = "入住人数")
    private String people;

    @ApiModelProperty(value = "预订房间")
    private String room;

    @ApiModelProperty(value = "入住时长")
    private String dayLong;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "订单状态")
    private String flag;

    @ApiModelProperty(value = "总价")
    private String price;

    @ApiModelProperty(value = "删除")
    private String isOut;

    private String wait1;

    private String wait2;

    private String wait3;


}
