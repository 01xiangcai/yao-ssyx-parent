package com.yao.ssyx.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.ssyx.model.sys.Region;

/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author long
 * @since 2023-10-18
 */
public interface RegionService extends IService<Region> {

    Object findRegionByKeyword(String keyword);
}
