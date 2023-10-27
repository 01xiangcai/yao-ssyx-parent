package com.yao.ssyx.order.repository;

import com.yao.ssyx.model.search.SkuEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @className: SkuRepository
 * @Description: TODO
 * @author: long
 * @date: 2023-10-19 15:14
 */
public interface SkuRepository extends ElasticsearchRepository<SkuEs, Long> {

    //获取爆品商品
    Page<SkuEs> findByOrderByHotScoreDesc(Pageable page);

    Page<SkuEs> findByCategoryIdAndWareId(Long categoryId, Long wareId, Pageable page);

    Page<SkuEs> findByKeywordAndWareId(String keyword, Long wareId, Pageable page);

}
