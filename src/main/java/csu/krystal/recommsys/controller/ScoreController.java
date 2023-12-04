package csu.krystal.recommsys.controller;


import csu.krystal.recommsys.common.util.ResponseVo;
import csu.krystal.recommsys.dto.LoginRequest;
import csu.krystal.recommsys.dto.RegisterRequest;
import csu.krystal.recommsys.dto.TokenPassJson;
import csu.krystal.recommsys.entity.Score;
import csu.krystal.recommsys.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoreController {
    @GetMapping
    public ResponseVo<List<User>> getScoreList() {
        return null;
    }

}
