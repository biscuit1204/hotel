package top.lumos04.hotel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminList {
    private List<Admin> data;
    private Integer code;
    private String msg;
    private long count;
}

