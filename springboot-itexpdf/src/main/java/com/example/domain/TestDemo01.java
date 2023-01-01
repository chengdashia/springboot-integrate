package com.example.domain;

import cn.hutool.core.collection.ListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/8/16 22:45
 */
public class TestDemo01 {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            list.add(user);
        }
        List<List<User>> partition = ListUtil.partition(list, 100);
        System.out.println("partition.size() = " + partition.size());
    }
}
