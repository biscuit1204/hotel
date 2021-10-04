package com.example.tavern.service.impl;

import com.example.tavern.pojo.Note;
import com.example.tavern.mapper.NoteMapper;
import com.example.tavern.service.NoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zcb
 * @since 2021-08-29
 */
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {

}
