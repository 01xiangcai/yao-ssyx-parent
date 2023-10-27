package com.yao.ssyx.acl.controller;

import com.yao.ssyx.common.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: indexController
 * @Description: 登录
 * @author: long
 * @date: 2023-10-17 18:20
 */
@RestController
@RequestMapping("/admin/acl/index")
public class indexController {

    @PostMapping("login")
    public Result login(){
        Map<String,Object> map = new HashMap<>();
        map.put("token","admin-token");
        return Result.ok(map);
    }

    /**
     * 2 获取用户信息
     */
    @GetMapping("info")
    public Result info(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","atguigu");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }

    /**
     * 3 退出
     */
    @PostMapping("logout")
    public Result logout(){
        return Result.ok();
    }

}
