package com.example.ip2region.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 成大事
 * @since 2022/7/26 11:03
 */
@Slf4j
@RestController
@AllArgsConstructor
public class IpController {
    private final Ip2regionSearcher ip2regionSearcher;

    /**
     * 获取地理位置信息
     * @param ip  ip地址
     * @return ip 所在地
     */
    @GetMapping("test")
    public String test(String ip) {
        return ip2regionSearcher.getAddress(ip);
    }
}
