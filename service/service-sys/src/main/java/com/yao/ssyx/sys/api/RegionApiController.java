package com.yao.ssyx.sys.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yao.ssyx.common.result.Result;
import com.yao.ssyx.model.sys.Region;
import com.yao.ssyx.model.sys.RegionWare;
import com.yao.ssyx.sys.service.RegionService;
import com.yao.ssyx.sys.service.RegionWareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: RegionApiController
 * @Description: TODO
 * @author: long
 * @date: 2023-10-21 15:12
 */
@RestController
@RequestMapping("/api/sys/region")
public class RegionApiController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionWareService regionWareService;

    @GetMapping("/findAllList")
    public Result findAllList(){
        List<Region> regionList = regionService.list();
        return Result.ok(regionList);
    }

    @GetMapping("getRegionInfoById")
    public Region getRegionById(Long regionId){
        return regionService.getById(regionId);
    }

    @GetMapping("getWareId")
    public Long getWareIdByRegionId(Long regionId){
        RegionWare regionWare = regionWareService.getOne(new LambdaQueryWrapper<RegionWare>().eq(RegionWare::getRegionId, regionId));
        if (regionWare != null){
            return regionWare.getWareId();
        }else {
            return null;
        }

    }


}
