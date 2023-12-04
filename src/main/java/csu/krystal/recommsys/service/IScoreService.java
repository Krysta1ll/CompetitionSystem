package csu.krystal.recommsys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import csu.krystal.recommsys.entity.Record;
import csu.krystal.recommsys.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IScoreService extends IService<Score> {

    boolean addScore(Score score);

    IPage<Score> getScorePage(Integer current, Integer size);

}
