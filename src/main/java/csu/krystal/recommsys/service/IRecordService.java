package csu.krystal.recommsys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import csu.krystal.recommsys.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Krystal
 * @since 2023-11-22
 */
public interface IRecordService extends IService<Record> {

    boolean addRecord(int uid, String content, double runtime);

    IPage<Record> getRecordPage(Integer current, Integer size);
}
