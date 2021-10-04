package top.lumos04.hotel.service.impl;

import top.lumos04.hotel.mapper.NoteMapper;
import top.lumos04.hotel.service.NoteService;
import top.lumos04.hotel.pojo.Note;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zcb
 * @since 2021-08-28
 */
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {

}
