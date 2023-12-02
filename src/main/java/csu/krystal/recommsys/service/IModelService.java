package csu.krystal.recommsys.service;

import csu.krystal.recommsys.entity.Model;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Krystal
 * @since 2023-11-22
 */
public interface IModelService extends IService<Model> {
    List<Model> selectBySeed(String seed);
}
