package com.evan.study.oom.reference;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * WeakHashMap会在系统内存紧张时使用弱引用，自动释放掉持有弱引用的内存数据
 *
 * @author Evan
 * @date 2022/4/28
 */
public class WeakHashMapTest {
    public static void main(String[] args) {
//        Map map = new WeakHashMap<String, Object>();
//        for (int i = 0; i < 10000; i++) {
//            map.put("key" + i, new byte[i]);
//        }
        Map map = new HashMap<String, Object>();
        for (int i = 0; i < 10000; i++) {
            map.put("key" + i, new byte[i]);
        }
    }
}
