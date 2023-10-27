package com.yao.ssyx.client.sys;

import com.yao.ssyx.model.sys.Region;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @className: sysFeignClient
 * @Description: TODO
 * @author: long
 * @date: 2023-10-21 15:11
 */
@FeignClient(value = "service-sys")
public interface SysFeignClient {

    @GetMapping("/api/sys/region/getRegionInfoById")
    public Region getRegionById(Long regionId);

    @GetMapping("/api/sys/region/getWareId")
    public Long getWareIdByRegionId(Long regionId);

}
