package com.yao.ssyx.activity.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.ssyx.model.activity.ActivityInfo;
import com.yao.ssyx.model.activity.ActivityRule;
import com.yao.ssyx.model.order.CartInfo;
import com.yao.ssyx.model.product.SkuInfo;
import com.yao.ssyx.vo.activity.ActivityRuleVo;
import com.yao.ssyx.vo.order.CartInfoVo;
import com.yao.ssyx.vo.order.OrderConfirmVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 活动表 服务类
 * </p>
 *
 * @author long
 * @since 2023-10-20
 */
public interface ActivityInfoService extends IService<ActivityInfo> {

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    IPage<ActivityInfo> selectPage(Page<ActivityInfo> pageParam);

    /**
     * 获取活动规则id
     * @param activityId
     * @return
     */
    Map<String, Object> findActivityRuleList(Long activityId);

    //保存活动规则信息
    void saveActivityRule(ActivityRuleVo activityRuleVo);

    //根据关键字获取sku信息列表
    List<SkuInfo> findSkuInfoByKeyword(String keyword);

    /**
     * 根据skuId获取促销规则信息
     * @param skuId
     * @return
     */
    List<ActivityRule> findActivityRule(Long skuId);

    Map<Long, List<String>> findActivityBySkuIds(List<Long> skuIds);

    Map<String, Object> findActivityAndCoupon(Long skuId, Long userId);

    OrderConfirmVo findCartActivityAndCoupon(List<CartInfo> cartInfoList, Long userId);

    public List<CartInfoVo> findCartActivityList(List<CartInfo> cartInfoList);
}
