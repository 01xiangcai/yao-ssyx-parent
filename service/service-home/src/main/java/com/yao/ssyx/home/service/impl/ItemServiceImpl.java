package com.yao.ssyx.home.service.impl;

import com.yao.ssyx.activity.client.ActivityFeignClient;
import com.yao.ssyx.client.product.ProductFeignClient;
import com.yao.ssyx.home.service.ItemService;
import com.yao.ssyx.order.client.SearchFeignClient;
import com.yao.ssyx.vo.product.SkuInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @className: ItemServiceImpl
 * @Description: TODO
 * @author: long
 * @date: 2023-10-22 17:02
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ProductFeignClient productFeignClient;

    @Resource
    private ActivityFeignClient activityFeignClient;

    @Resource
    private SearchFeignClient searchFeignClient;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public Map<String, Object> item(Long skuId, Long userId) {
        Map<String, Object> result = new HashMap<>();

        // 通过skuId 查询skuInfo
        CompletableFuture<SkuInfoVo> skuInfoCompletableFuture = CompletableFuture.supplyAsync(() -> {
            //sku基本信息
            SkuInfoVo skuInfoVo = productFeignClient.getSkuInfoVo(skuId);
            result.put("skuInfoVo", skuInfoVo);
            return skuInfoVo;
        }, threadPoolExecutor);

        //TODO 如果商品是秒杀商品，获取秒杀信息

        CompletableFuture<Void> activityCompletableFuture = CompletableFuture.runAsync(() -> {
            //sku对应的促销与优惠券信息
            Map<String, Object> activityAndCouponMap = activityFeignClient.findActivityAndCoupon(skuId, userId);
            result.putAll(activityAndCouponMap);
        },threadPoolExecutor);

        CompletableFuture<Void> hotCompletableFuture = CompletableFuture.runAsync(() -> {
            searchFeignClient.incrHotScore(skuId);
        },threadPoolExecutor);

        //  任务组合：
        CompletableFuture.allOf(
                skuInfoCompletableFuture,
//                seckillSkuCompletableFuture,
                activityCompletableFuture,
                hotCompletableFuture
        ).join();
        return result;
    }

}
