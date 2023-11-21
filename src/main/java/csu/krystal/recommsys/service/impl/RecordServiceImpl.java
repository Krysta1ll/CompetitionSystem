package csu.krystal.recommsys.service.impl;

import csu.krystal.recommsys.entity.Record;
import csu.krystal.recommsys.mapper.RecordMapper;
import csu.krystal.recommsys.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Krystal
 * @since 2023-11-22
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

}
