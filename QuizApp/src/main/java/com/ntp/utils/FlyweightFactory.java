/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntp.utils;

import com.ntp.services.BaseServices;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Joon
 */
public class FlyweightFactory {

    private static final Map<String, List> cachedData = new HashMap<>();

    public static <E> List<E> getData(BaseServices s, String key) throws SQLException {
        if (cachedData.containsKey(key) == true) {
            return cachedData.get(key);
        } else {
            List ls = s.list();
            cachedData.put(key, ls);
            return ls;
        }
    }

    public static <E> List<E> updateData(BaseServices s, String key) throws SQLException {
        List ls = s.list();
        cachedData.put(key, ls);
        return ls;
    }
}
