package top.lumos04.hotel.pojo;

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
 * @since 2021-08-28
 */
@Data
@Component
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Estimate对象", description="")
public class Estimate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "投诉内容")
    private String content;

    @ApiModelProperty(value = "创建用户")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "酒店")
    private String hotel;

    @ApiModelProperty(value = "订单")
    private String note;

    @ApiModelProperty(value = "评分")
    private String num;

    @ApiModelProperty(value = "删除")
    private String isOut;

    private String wait1;

    private String wait2;

    private String wait3;


}
