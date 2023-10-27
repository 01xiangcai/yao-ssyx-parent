package com.yao.ssyx.activity.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yao.ssyx.model.activity.ActivityInfo;
import com.yao.ssyx.model.activity.ActivityRule;
import com.yao.ssyx.model.activity.ActivitySku;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 活动表 Mapper 接口
 * </p>
 *
 * @author long
 * @since 2023-10-20
 */
@Repository
public interface ActivityInfoMapper extends BaseMapper<ActivityInfo> {

    List<Long> selectExistSkuIdList(@Param("skuIdList")List<Long> skuIdList);

    List<ActivityRule> selectActivityRuleList(@Param("skuId")Long skuId);

    /**
     * 根据skuIdList查询对应的活动列表
     * @param skuIdList
     * @return
     */
    List<ActivitySku> selectCartActivityList(@Param("skuIdList") List<Long> skuIdList);

}
