package csu.krystal.recommsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import csu.krystal.recommsys.entity.Record;
import csu.krystal.recommsys.entity.Score;
import csu.krystal.recommsys.mapper.ScoreMapper;
import csu.krystal.recommsys.service.IScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public boolean addScore(Score score) {
        return scoreMapper.insert(score) != 0;
    }

    @Override
    public IPage<Score> getScorePage(Integer current, Integer size) {
        //我们并不需要总记录数，查询总记录数就完全没有必要，因为它也需要耗时，设置不查询总记录数 : false
        Page<Score> scorePage = new Page<>(current, size, false);

        return scoreMapper.selectPage(scorePage, new LambdaQueryWrapper<>());
    }
}
