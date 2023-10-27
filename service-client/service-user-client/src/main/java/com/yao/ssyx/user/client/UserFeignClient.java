package com.yao.ssyx.user.client;

import com.yao.ssyx.vo.user.LeaderAddressVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @className: userFeignClient
 * @Description: TODO
 * @author: long
 * @date: 2023-10-21 18:58
 */
@FeignClient(value = "service-user")
public interface UserFeignClient {


    @GetMapping("/api/user/leader/inner/getUserAddressByUserId/{userId}")
    public LeaderAddressVo getUserAddressByUserId(@PathVariable(value = "userId") Long userId);


}
