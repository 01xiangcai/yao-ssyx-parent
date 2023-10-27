package com.yao.ssyx.product.api;

import com.yao.ssyx.model.product.Category;
import com.yao.ssyx.model.product.SkuInfo;
import com.yao.ssyx.product.service.CategoryService;
import com.yao.ssyx.product.service.SkuInfoService;
import com.yao.ssyx.vo.product.SkuInfoVo;
import com.yao.ssyx.vo.product.SkuStockLockVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @className: ProductInnnerController
 * @Description: TODO
 * @author: long
 * @date: 2023-10-19 14:54
 */
@RestController
@RequestMapping("/api/product")
public class ProductInnnerController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SkuInfoService skuInfoService;



    @ApiOperation(value = "根据分类id获取分类信息")
    @GetMapping("inner/getCategory/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) {
        return categoryService.getById(categoryId);
    }

    @ApiOperation(value = "根据skuId获取sku信息")
    @GetMapping("inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable("skuId") Long skuId) {
        return skuInfoService.getById(skuId);
    }

    @ApiOperation(value = "批量获取sku信息")
    @PostMapping("inner/findSkuInfoList")
    public List<SkuInfo> findSkuInfoList(@RequestBody List<Long> skuIdList) {
        return skuInfoService.findSkuInfoList(skuIdList);
    }

    @ApiOperation(value = "根据关键字获取sku列表")
    @GetMapping("inner/findSkuInfoByKeyword/{keyword}")
    public List<SkuInfo> findSkuInfoByKeyword(@PathVariable("keyword") String keyword) {
        return skuInfoService.findSkuInfoByKeyword(keyword);
    }

    @ApiOperation(value = "批量获取分类信息")
    @PostMapping("inner/findCategoryList")
    public List<Category> findCategoryList(@RequestBody List<Long> categoryIdList) {
        return categoryService.findCategoryList(categoryIdList);
    }

    @ApiOperation(value = "获取分类信息")
    @GetMapping("inner/findAllCategoryList")
    public List<Category> findAllCategoryList() {
        return categoryService.findAllList();
    }

    @ApiOperation(value = "获取新人专享")
    @GetMapping("inner/findNewPersonSkuInfoList")
    public List<SkuInfo> findNewPersonSkuInfoList() {
        return skuInfoService.findNewPersonList();
    }

    @ApiOperation(value = "根据skuId获取sku信息")
    @GetMapping("inner/getSkuInfoVo/{skuId}")
    public SkuInfoVo getSkuInfoVo(@PathVariable("skuId") Long skuId) {
        return skuInfoService.getSkuInfoVo(skuId);
    }

    @ApiOperation(value = "锁定库存")
    @PostMapping("inner/checkAndLock/{orderNo}")
    public Boolean checkAndLock(@RequestBody List<SkuStockLockVo> skuStockLockVoList, @PathVariable String orderNo) {
        return skuInfoService.checkAndLock(skuStockLockVoList, orderNo);
    }

}
