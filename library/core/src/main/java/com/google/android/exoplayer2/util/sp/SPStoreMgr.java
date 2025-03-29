/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2016-2019. All rights reserved.
 */

package com.google.android.exoplayer2.util.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

/**
 * SharedPreference存储管理类<BR>
 */
public class SPStoreMgr {
    private static final SPStoreMgr INSTANCE = new SPStoreMgr();

    private static final String SP_NAME_DEFAULT = "def_sp_name";

    private static Context mContext;

    private SPStoreMgr() {
    }

    public static void init(Context context) {
        mContext = context;
    }

    static SPStoreMgr getInstance() {
        return INSTANCE;
    }

    void put(String spName, String key, String value) {
        SharedPreferences sp = getSP(spName);
        if (null == sp) {
            return;
        }

        Editor spEditor = sp.edit();
        spEditor.putString(key, value);
        spEditor.apply();
    }

    String getString(String spName, String key, String defValue) {
        SharedPreferences sp = getSP(spName);
        if (null == sp) {
            return null;
        }

        return sp.getString(key, defValue);
    }

    private SharedPreferences getSP(String spName) {
        Context appContext = getContext();
        if (null == appContext) {
            return null;
        }

        if (TextUtils.isEmpty(spName)) {
            return appContext.getSharedPreferences(SP_NAME_DEFAULT, Context.MODE_PRIVATE);
        }
        return appContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    private static Context getContext() {
        return mContext;
    }
}
