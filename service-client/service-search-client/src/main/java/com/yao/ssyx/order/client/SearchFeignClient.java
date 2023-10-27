package com.yao.ssyx.order.client;

import com.yao.ssyx.model.search.SkuEs;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @className: SearchFeignClient
 * @Description: TODO
 * @author: long
 * @date: 2023-10-21 19:04
 */
@FeignClient(value = "service-search")
public interface SearchFeignClient {

    @GetMapping("api/search/sku/inner/findHotSkuList")
    public List<SkuEs> findHotSkuList();

    @ApiOperation(value = "更新商品incrHotScore")
    @GetMapping("api/search/sku/inner/incrHotScore/{skuId}")
    public Boolean incrHotScore(@PathVariable("skuId") Long skuId);

}
