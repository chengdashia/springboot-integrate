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

    ///**
    // * ip 位置 搜索
    // *
    // * @param ip ip
    // * @return 位置
    // */
    //@Nullable
    //IpInfo memorySearch(long ip);
    //
    ///**
    // * ip 位置 搜索
    // *
    // * @param ip ip
    // * @return 位置
    // */
    //@Nullable
    //IpInfo memorySearch(String ip);
    //
    ///**
    // * ip 位置 搜索
    // *
    // * @param ptr ptr
    // * @return 位置
    // */
    //@Nullable
    //IpInfo getByIndexPtr(long ptr);
    //
    ///**
    // * ip 位置 搜索
    // *
    // * @param ip ip
    // * @return 位置
    // */
    //@Nullable
    //IpInfo btreeSearch(long ip);
    //
    ///**
    // * ip 位置 搜索
    // *
    // * @param ip ip
    // * @return 位置
    // */
    //@Nullable
    //IpInfo btreeSearch(String ip);
    //
    ///**
    // * ip 位置 搜索
    // *
    // * @param ip ip
    // * @return 位置
    // */
    //@Nullable
    //IpInfo binarySearch(long ip);
    //
    ///**
    // * ip 位置 搜索
    // *
    // * @param ip ip
    // * @return 位置
    // */
    //@Nullable
    //IpInfo binarySearch(String ip);
    //
    //
    ///**
    // * 直接获取地址信息，完整的地址，例如：北京朝阳区xxx
    // *
    // * @param ip ip
    // * @return 地址
    // */
    //@Nullable
    //default String getAddress(long ip) {
    //    return getInfo(ip, IpInfo::getAddress);
    //}
    //
    ///**
    // * 直接获取地址信息，完整的地址，例如：北京朝阳区xxx
    // *
    // * @param ip ip
    // * @return 地址
    // */
    //@Nullable
    //default String getAddress(String ip) {
    //    return getInfo(ip, IpInfo::getAddress);
    //}
    //
    ///**
    // * 获取地址信息包含 isp
    // *
    // * @param ip ip
    // * @return 地址
    // */
    //@Nullable
    //default String getAddressAndIsp(long ip) {
    //    return getInfo(ip, IpInfo::getAddressAndIsp);
    //}
    //
    ///**
    // * 获取地址信息包含 isp
    // *
    // * @param ip ip
    // * @return 地址
    // */
    //@Nullable
    //default String getAddressAndIsp(String ip) {
    //    return getInfo(ip, IpInfo::getAddressAndIsp);
    //}

    /**
     * 获取地理位置信息
     * @param ip  ip地址
     * @return ip 所在地
     */
    @GetMapping("test")
    public String test(String ip) {
        log.info(""+ip2regionSearcher.getAddressAndIsp(ip));
        return ip2regionSearcher.getAddress(ip);
    }
}
