package com.yao.ssyx.order.service.impl;

import com.yao.ssyx.activity.client.ActivityFeignClient;
import com.yao.ssyx.client.product.ProductFeignClient;
import com.yao.ssyx.common.security.AuthContextHolder;
import com.yao.ssyx.enums.SkuType;
import com.yao.ssyx.model.product.Category;
import com.yao.ssyx.model.product.SkuInfo;
import com.yao.ssyx.model.search.SkuEs;
import com.yao.ssyx.order.repository.SkuRepository;
import com.yao.ssyx.order.service.SkuService;
import com.yao.ssyx.vo.search.SkuEsQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @className: SkuServiceImpl
 * @Description: TODO
 * @author: long
 * @date: 2023-10-19 15:09
 */
@Service
@Slf4j
public class SkuServiceImpl implements SkuService {

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private ActivityFeignClient activityFeignClient;

    @Autowired
    private SkuRepository skuEsRepository;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 上架商品列表
     * @param skuId
     */
    @Override
    public void upperSku(Long skuId) {
        log.info("upperSku："+skuId);
        SkuEs skuEs = new SkuEs();

        //查询sku信息
        SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);
        if(null == skuInfo) return;

        // 查询分类
        Category category = productFeignClient.getCategory(skuInfo.getCategoryId());
        if (category != null) {
            skuEs.setCategoryId(category.getId());
            skuEs.setCategoryName(category.getName());
        }
        skuEs.setId(skuInfo.getId());
        skuEs.setKeyword(skuInfo.getSkuName()+","+skuEs.getCategoryName());
        skuEs.setWareId(skuInfo.getWareId());
        skuEs.setIsNewPerson(skuInfo.getIsNewPerson());
        skuEs.setImgUrl(skuInfo.getImgUrl());
        skuEs.setTitle(skuInfo.getSkuName());
        if(skuInfo.getSkuType() == SkuType.COMMON.getCode()) {
            skuEs.setSkuType(0);
            skuEs.setPrice(skuInfo.getPrice().doubleValue());
            skuEs.setStock(skuInfo.getStock());
            skuEs.setSale(skuInfo.getSale());
            skuEs.setPerLimit(skuInfo.getPerLimit());
        } else {
            //TODO 待完善-秒杀商品

        }
        SkuEs save = skuEsRepository.save(skuEs);
        log.info("upperSku："+JSON.toJSONString(save));
    }

    /**
     * 下架商品列表
     * @param skuId
     */
    @Override
    public void lowerSku(Long skuId) {
        this.skuEsRepository.deleteById(skuId);
    }

    //获取爆品商品
    @Override
    public List<SkuEs> findHotSkuList() {
        Pageable pageable = PageRequest.of(0, 10);
        return skuEsRepository.findByOrderByHotScoreDesc(pageable).getContent();
    }

    @Override
    public Page<SkuEs> search(Pageable pageable, SkuEsQueryVo skuEsQueryVo) {
        skuEsQueryVo.setWareId(AuthContextHolder.getWareId());
        Page<SkuEs> page = null;
        if(StringUtils.isEmpty(skuEsQueryVo.getKeyword())) {
            page = skuEsRepository.findByCategoryIdAndWareId(skuEsQueryVo.getCategoryId(), skuEsQueryVo.getWareId(), pageable);
        } else {
            page = skuEsRepository.findByKeywordAndWareId(skuEsQueryVo.getKeyword(), skuEsQueryVo.getWareId(), pageable);
        }

        List<SkuEs>  skuEsList =  page.getContent();
        //获取sku对应的促销活动标签
        if(!CollectionUtils.isEmpty(skuEsList)) {
            List<Long> skuIdList = skuEsList.stream().map(sku -> sku.getId()).collect(Collectors.toList());
            Map<Long, List<String>> skuIdToRuleListMap = activityFeignClient.findActivityBySkuIds(skuIdList);
            if(null != skuIdToRuleListMap) {
                skuEsList.forEach(skuEs -> {
                    skuEs.setRuleList(skuIdToRuleListMap.get(skuEs.getId()));
                });
            }
        }
        return page;
    }

    @Override
    public void incrHotScore(Long skuId) {
        // 定义key
        String hotKey = "hotScore";
        // 保存数据
        Double hotScore = redisTemplate.opsForZSet().incrementScore(hotKey, "skuId:" + skuId, 1);
        if (hotScore%10==0){
            // 更新es
            Optional<SkuEs> optional = skuEsRepository.findById(skuId);
            SkuEs skuEs = optional.get();
            skuEs.setHotScore(Math.round(hotScore));
            skuEsRepository.save(skuEs);
        }
    }

}
