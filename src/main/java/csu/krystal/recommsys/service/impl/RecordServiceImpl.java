package csu.krystal.recommsys.service.impl;

import csu.krystal.recommsys.entity.Record;
import csu.krystal.recommsys.mapper.RecordMapper;
import csu.krystal.recommsys.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Krystal
 * @since 2023-11-22
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public boolean addRecord(int uid, String content, double runtime) {
        Record record = new Record();
        record.setUserId(uid);
        record.setContent(content);
        record.setRunTime(runtime);
        record.setCreateTime(new Date());
        return recordMapper.insert(record) != 0;
    }


}
