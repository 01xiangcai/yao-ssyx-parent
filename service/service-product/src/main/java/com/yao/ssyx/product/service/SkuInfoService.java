package com.yao.ssyx.product.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.ssyx.model.product.SkuInfo;
import com.yao.ssyx.vo.product.SkuInfoQueryVo;
import com.yao.ssyx.vo.product.SkuInfoVo;
import com.yao.ssyx.vo.product.SkuStockLockVo;

import java.util.List;

/**
 * <p>
 * sku信息 服务类
 * </p>
 *
 * @author long
 * @since 2023-10-18
 */
public interface SkuInfoService extends IService<SkuInfo> {

    IPage<SkuInfo> selectPage(Page<SkuInfo> pageParam, SkuInfoQueryVo skuInfoQueryVo);

    void saveSkuInfo(SkuInfoVo skuInfoVo);

    SkuInfoVo getSkuInfoVo(Long id);

    void updateSkuInfo(SkuInfoVo skuInfoVo);

    void check(Long skuId, Integer status);

    void publish(Long skuId, Integer status);

    void isNewPerson(Long skuId, Integer status);

    //批量获取sku信息
    List<SkuInfo> findSkuInfoList(List<Long> skuIdList);

    //根据关键字获取sku列表
    List<SkuInfo> findSkuInfoByKeyword(String keyword);

    List<SkuInfo> findNewPersonList();

    Boolean checkAndLock(List<SkuStockLockVo> skuStockLockVoList, String orderNo);
}
