package csu.krystal.recommsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public IPage<Record> getRecordPage(Integer current, Integer size) {
        LambdaQueryWrapper<Record> recordLambdaQueryWrapper = Wrappers.lambdaQuery();

        //我们并不需要总记录数，查询总记录数就完全没有必要，因为它也需要耗时，设置不查询总记录数 : false
        Page<Record> recordPage = new Page<>(current, size, false);

        return recordMapper.selectPage(recordPage, new LambdaQueryWrapper<>());
    }


}
