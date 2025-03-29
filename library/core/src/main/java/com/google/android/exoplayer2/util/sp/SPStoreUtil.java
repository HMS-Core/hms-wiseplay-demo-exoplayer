/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2016-2019. All rights reserved.
 */

package com.google.android.exoplayer2.util.sp;

/**
 * SharedPreference存储工具类<BR>
 */
public final class SPStoreUtil {
    private SPStoreUtil() {
    }

    public static void put(String key, boolean value) {
        put(null, key, value);
    }

    public static void put(String spName, String key, boolean value) {
        SPStoreMgr.getInstance().put(spName, key, String.valueOf(value));
    }

    public static boolean getBoolean(String key) {
        return getBoolean(null, key);
    }

    public static boolean getBoolean(String spName, String key) {
        return getBoolean(spName, key, false);
    }

    public static boolean getBoolean(String name, String key, boolean defValue) {
        String value = null;
        try {
            value = SPStoreMgr.getInstance().getString(name, key, String.valueOf(defValue));
            return Boolean.valueOf(value);
        } catch (Exception e) {
            return defValue;
        }
    }
}
