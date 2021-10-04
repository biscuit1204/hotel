package com.example.tavern.pojo;

import com.example.tavern.pojo.Note;
import com.example.tavern.pojo.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteVo {
    Note note;
    Room room;
}
