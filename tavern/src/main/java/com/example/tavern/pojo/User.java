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
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "积分")
    private Integer vip;

    @ApiModelProperty(value = "身份证")
    private String card;

    @ApiModelProperty(value = "头像")
    private String img;

    @ApiModelProperty(value = "删除")
    private String isOut;

    @ApiModelProperty(value = "微信登录")
    private String openKey;

    @ApiModelProperty(value = "性别")
    private String sex;

    private String wait1;

    private String wait2;

    private String wait3;


}
