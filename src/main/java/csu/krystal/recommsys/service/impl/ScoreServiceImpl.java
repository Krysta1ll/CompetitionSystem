package csu.krystal.recommsys.service.impl;

import csu.krystal.recommsys.entity.Score;
import csu.krystal.recommsys.mapper.ScoreMapper;
import csu.krystal.recommsys.service.IScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Krystal
 * @since 2023-11-22
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {
}
