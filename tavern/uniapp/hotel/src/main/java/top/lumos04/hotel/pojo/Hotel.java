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


}
