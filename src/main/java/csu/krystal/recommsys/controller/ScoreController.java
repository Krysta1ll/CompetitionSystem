package csu.krystal.recommsys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import csu.krystal.recommsys.common.util.ResponseVo;
import csu.krystal.recommsys.entity.Score;
import csu.krystal.recommsys.common.service.IScoreService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class ScoreController {
    @Autowired
    private IScoreService scoreService;

    @GetMapping
    @Operation(summary = "分页查询用户评分", description = "传递参数，第一个参数是当前页， 第二个参数是页的大小")
    public ResponseVo<IPage<Score>> getPageScore(@RequestParam("current") Integer current,
                                                   @RequestParam("size") Integer size){
        IPage<Score> scoreIPage = scoreService.getScorePage(current, size);
        return ResponseVo.success("分页查询搜索记录成功", scoreIPage);
    }

    @PostMapping
    @Operation(summary = "新增用户评分记录")
    public ResponseVo<Score> addNewScore(@RequestBody Score score){
            if(scoreService.addScore(score)){
                return ResponseVo.success("新增用户评分成功");
            }
        return ResponseVo.error("新增用户评分失败");
    }

}
