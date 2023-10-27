package com.yao.ssyx.product.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.ssyx.model.product.Category;
import com.yao.ssyx.vo.product.CategoryQueryVo;

import java.util.List;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author long
 * @since 2023-10-18
 */
public interface CategoryService extends IService<Category> {

    IPage<Category> selectPage(Page<Category> pageParam, CategoryQueryVo categoryQueryVo);

    List<Category> findAllList();

    List<Category> findCategoryList(List<Long> categoryIdList);
}
