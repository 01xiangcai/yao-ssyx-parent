package com.yao.ssyx.order.service;

import com.yao.ssyx.model.search.SkuEs;
import com.yao.ssyx.vo.search.SkuEsQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: SkuService
 * @Description: TODO
 * @author: long
 * @date: 2023-10-19 15:09
 */
@Service
public interface SkuService {
    void upperSku(Long skuId);

    void lowerSku(Long skuId);

    /**
     * 获取爆品商品
     * @return
     */
    List<SkuEs> findHotSkuList();

    /**
     * 搜索列表
     * @param skuEsQueryVo
     * @return
     */
    Page<SkuEs> search(Pageable pageable, SkuEsQueryVo skuEsQueryVo);

    void incrHotScore(Long skuId);
}
