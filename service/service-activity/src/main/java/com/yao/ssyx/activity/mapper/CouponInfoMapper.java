package com.yao.ssyx.activity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yao.ssyx.model.activity.CouponInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 优惠券信息 Mapper 接口
 * </p>
 *
 * @author long
 * @since 2023-10-20
 */
@Repository
public interface CouponInfoMapper extends BaseMapper<CouponInfo> {

    /**
     * sku优惠券
     * @param skuId
     * @param categoryId
     * @param userId
     * @return
     */
    List<CouponInfo> selectCouponInfoList(@Param("skuId") Long skuId, @Param("categoryId") Long categoryId, @Param("userId") Long userId);


    /**
     * 获取用户全部优惠券
     * @param userId
     * @return
     */
    List<CouponInfo> selectCartCouponInfoList(@Param("userId")Long userId);

}
