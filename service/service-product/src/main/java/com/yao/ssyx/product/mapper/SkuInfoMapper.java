package com.yao.ssyx.product.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yao.ssyx.model.product.SkuInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * sku信息 Mapper 接口
 * </p>
 *
 * @author long
 * @since 2023-10-18
 */
@Repository
public interface SkuInfoMapper extends BaseMapper<SkuInfo> {

    SkuInfo checkStock(@Param("skuId") Long skuId, @Param("skuNum") Integer skuNum);

    Integer lockStock(@Param("skuId")Long skuId, @Param("skuNum")Integer skuNum);

    Integer unlockStock(@Param("skuId")Long skuId, @Param("skuNum")Integer skuNum);

}
