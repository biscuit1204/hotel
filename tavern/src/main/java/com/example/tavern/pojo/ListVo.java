package com.example.tavern.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListVo {
    Hotel hotel;
    RoomType roomType;
    Room room;
    Note note;
}
