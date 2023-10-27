package com.yao.ssyx.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yao.ssyx.model.product.Attr;
import com.yao.ssyx.product.mapper.AttrMapper;
import com.yao.ssyx.product.service.AttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author long
 * @since 2023-10-18
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements AttrService {

    //根据属性分组id 获取属性列表
    @Override
    public List<Attr> findByAttrGroupId(Long attrGroupId) {
        LambdaQueryWrapper<Attr> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Attr::getAttrGroupId,attrGroupId);
        List<Attr> attrList = baseMapper.selectList(wrapper);
        return attrList;
    }


}
