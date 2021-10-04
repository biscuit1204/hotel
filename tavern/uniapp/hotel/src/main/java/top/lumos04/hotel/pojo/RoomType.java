package top.lumos04.hotel.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel(value="RoomType对象", description="")
public class RoomType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "所属酒店")
    private String hotel;

    @ApiModelProperty(value = "房间名称")
    private String name;

    @ApiModelProperty(value = "图片")
    private String img;

    @ApiModelProperty(value = "是否有窗")
    private String windows ;

    @ApiModelProperty(value = "床型")
    private String bed;

    @ApiModelProperty(value = "人数")
    private String people;

    @ApiModelProperty(value = "等级")
    private String layer;

    @ApiModelProperty(value = "是否有WiFi")
    private String wifi;

    @ApiModelProperty(value = "详情")
    private String note;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    private String wait1;

    private String wait2;

    private String wait3;

    private String isOut;


}
