package com.muyan.pms.web.controller;

import com.muyan.pms.entity.pms.Settle;
import com.muyan.pms.entity.rest.Result;
import com.muyan.pms.service.SettleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: SettleController
 * @description:
 * @author: muyan
 * @date: 2023/04/23 08:34:08
 **/

@RestController
@RequestMapping("/settle")
@Api(value = "结算接口",description = "结算相关的接口")
public class SettleController {

    @Autowired
    private SettleService settleService;

    @PostMapping
    @ApiOperation("创建结算")
    public Result create(@RequestBody Settle settle){
        settleService.create(settle);
        return Result.success();
    }

    @GetMapping("/report")
    public Result report(@RequestParam("userId") String userId){
        return Result.success(settleService.report(userId));
    }

}
