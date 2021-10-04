package com.example.tavern.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeList {
    private List<RoomType> data;
    private Integer code;
    private String msg;
    private long count;
}

