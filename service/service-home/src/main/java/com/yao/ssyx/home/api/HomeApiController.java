package com.yao.ssyx.home.api;

import com.yao.ssyx.common.result.Result;
import com.yao.ssyx.common.security.AuthContextHolder;
import com.yao.ssyx.home.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @className: HomeApiController
 * @Description: TODO
 * @author: long
 * @date: 2023-10-21 18:48
 */

@Api(tags = "首页接口")
@RestController
@RequestMapping("api/home")
public class HomeApiController {

    @Resource
    private HomeService homeService;

    @ApiOperation(value = "获取首页数据")
    @GetMapping("index")
    public Result index(HttpServletRequest request) {
        // 获取用户Id
        Long userId = AuthContextHolder.getUserId();
        return Result.ok(homeService.home(userId));
    }
}