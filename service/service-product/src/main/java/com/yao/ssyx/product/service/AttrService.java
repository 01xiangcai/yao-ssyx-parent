package com.yao.ssyx.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.ssyx.model.product.Attr;

import java.util.List;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author long
 * @since 2023-10-18
 */
public interface AttrService extends IService<Attr> {

    List<Attr> findByAttrGroupId(Long attrGroupId);
}
