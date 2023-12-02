package csu.krystal.recommsys.controller;


import csu.krystal.recommsys.common.util.ResponseVo;
import csu.krystal.recommsys.entity.Model;
import csu.krystal.recommsys.entity.User;
import csu.krystal.recommsys.service.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Krystal
 * @since 2023-11-22
 */
@RestController
@RequestMapping("/models")
public class ModelController {
    @Autowired
    private IModelService modelService;

    @GetMapping
    public ResponseVo<List<Model>> getModelList() {
        return null;
    }

    @GetMapping
    public ResponseVo<List<Model>> getModelByKey(@PathVariable String seed){
        return null;
    }
}
