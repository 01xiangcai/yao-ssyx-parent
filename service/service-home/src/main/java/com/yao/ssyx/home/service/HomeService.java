package com.yao.ssyx.home.service;

import java.util.Map;

/**
 * @className: HomeService
 * @Description: TODO
 * @author: long
 * @date: 2023-10-21 18:50
 */
public interface HomeService {

    //首页数据
    Map<String, Object> home(Long userId);

}
