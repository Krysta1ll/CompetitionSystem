package csu.krystal.recommsys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import csu.krystal.recommsys.common.util.ResponseVo;
import csu.krystal.recommsys.entity.Record;
import csu.krystal.recommsys.common.service.IRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Krystal
 * @since 2023-11-22
 */
@RestController
@RequestMapping("/records")
@Tag(name = "搜索记录管理",description = "实现查询全部搜索记录功能, 添加记录的功能在搜索功能实现时，会完成添加记录的功能")
public class RecordController {

    @Autowired
    private IRecordService recordService;

    @GetMapping
    @Operation(summary = "分页查询搜索记录", description = "传递参数，第一个参数是当前页， 第二个参数是页的大小")
    public ResponseVo<IPage<Record>> getPageRecord(@RequestParam("current") Integer current,
                                                   @RequestParam("size") Integer size){

        IPage<Record> recordIPage = recordService.getRecordPage(current, size);
        if(recordIPage != null){
            return ResponseVo.success("分页查询搜索记录成功", recordIPage);
        }
        return ResponseVo.error("分页查询搜索记录失败");
    }

}
