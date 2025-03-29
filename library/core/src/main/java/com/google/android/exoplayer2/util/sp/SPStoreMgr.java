/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2016-2019. All rights reserved.
 */

package com.google.android.exoplayer2.util.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import java.util.Map;

/**
 * SharedPreference存储管理类<BR>
 */
public class SPStoreMgr {
    private static final SPStoreMgr INSTANCE = new SPStoreMgr();

    private static final String SP_NAME_DEFAULT = "default_sp";

    private static Context mContext;

    private SPStoreMgr() {
    }

    static SPStoreMgr getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化上下文
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        mContext = context;
    }

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    private static Context getContext() {
        return mContext;
    }

    boolean contains(String key) {
        return contains(null, key);
    }

    boolean contains(String spName, String key) {
        SharedPreferences sp = getSP(spName);
        if (null == sp) {
            return false;
        }

        return sp.contains(key);
    }

    void put(String key, String value) {
        put(null, key, value);
    }

    void put(String spName, String key, String value) {
        SharedPreferences sp = getSP(spName);
        if (null == sp) {
            return;
        }

        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    void commit(String spName, String key, String value) {
        SharedPreferences sp = getSP(spName);
        if (null == sp) {
            return;
        }

        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    String getString(String spName, String key, String defValue) {
        SharedPreferences sp = getSP(spName);
        if (null == sp) {
            return null;
        }

        return sp.getString(key, defValue);
    }

    boolean getBoolean(String spName, String key, boolean defValue) {
        SharedPreferences sp = getSP(spName);
        if (null == sp) {
            return defValue;
        }

        return sp.getBoolean(key, defValue);
    }

    long getLong(String spName, String key, long defValue) {
        SharedPreferences sp = getSP(spName);
        if (null == sp) {
            return defValue;
        }

        return sp.getLong(key, defValue);
    }

    int getInt(String spName, String key, int defValue) {
        SharedPreferences sp = getSP(spName);
        if (null == sp) {
            return defValue;
        }

        return sp.getInt(key, defValue);
    }

    float getFloat(String spName, String key, float defValue) {
        SharedPreferences sp = getSP(spName);
        if (null == sp) {
            return defValue;
        }

        return sp.getFloat(key, defValue);
    }

    void remove(String key) {
        remove(null, key);
    }

    void remove(String spName, String key) {
        SharedPreferences sp = getSP(spName);
        if (null == sp) {
            return;
        }

        Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    void clear() {
        clear(null);
    }

    void clear(String spName) {
        SharedPreferences sp = getSP(spName);
        if (null == sp) {
            return;
        }

        Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    Map<String, ?> getAll() {
        return getAll(null);
    }

    Map<String, ?> getAll(String spName) {
        SharedPreferences sp = getSP(spName);
        if (null == sp) {
            return null;
        }
        return sp.getAll();
    }

    private SharedPreferences getSP(String spName) {
        Context context = getContext();
        if (null == context) {
            return null;
        }

        if (TextUtils.isEmpty(spName)) {
            return context.getSharedPreferences(SP_NAME_DEFAULT, Context.MODE_PRIVATE);
        }
        return context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }
}
