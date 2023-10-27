package com.yao.ssyx.sys.controller;


import com.yao.ssyx.common.result.Result;
import com.yao.ssyx.sys.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 地区表 前端控制器
 * </p>
 *
 * @author long
 * @since 2023-10-18
 */
@Api(tags = "区域接口")
@RestController
@RequestMapping("/admin/sys/region")
public class RegionController {

    @Resource
    private RegionService regionService;

    @ApiOperation(value = "根据关键字获取地区列表")
    @GetMapping("findRegionByKeyword/{keyword}")
    public Result findSkuInfoByKeyword(@PathVariable("keyword") String keyword) {
        return Result.ok(regionService.findRegionByKeyword(keyword));
    }


}

