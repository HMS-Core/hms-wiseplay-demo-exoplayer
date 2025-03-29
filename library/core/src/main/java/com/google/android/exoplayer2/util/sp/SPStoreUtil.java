/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2016-2019. All rights reserved.
 */

package com.google.android.exoplayer2.util.sp;

import android.util.Base64;

import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * SharedPreference存储工具类<BR>
 */
public final class SPStoreUtil {
    private static final String TAG = "SPStoreApi";

    private SPStoreUtil() {

    }

    /**
     * 判断键值对应的存储信息是否存在<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key 存储信息的键值
     * @return true表示存在，否则反之
     */
    public static boolean contains(String key) {
        return SPStoreMgr.getInstance().contains(key);
    }

    /**
     * 判断键值对应的存储信息是否存在<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @return true表示存在，否则反之
     */
    public static boolean contains(String spName, String key) {
        return SPStoreMgr.getInstance().contains(spName, key);
    }

    /**
     * 存储String类型的值<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key   存储信息的键值
     * @param value String类型的值
     */
    public static void put(String key, String value) {
        put(null, key, value);
    }

    /**
     * 存储int类型的值<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key   存储信息的键值
     * @param value int类型的值
     */
    public static void put(String key, int value) {
        put(null, key, value);
    }

    /**
     * 存储long类型的值<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key   存储信息的键值
     * @param value long类型的值
     */
    public static void put(String key, long value) {
        put(null, key, value);
    }

    /**
     * 存储double类型的值<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key   存储信息的键值
     * @param value double类型的值
     */
    public static void put(String key, double value) {
        put(null, key, value);
    }

    /**
     * 存储float类型的值<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key   存储信息的键值
     * @param value float类型的值
     */
    public static void put(String key, float value) {
        put(null, key, value);
    }

    /**
     * 存储boolean类型的值<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key   存储信息的键值
     * @param value boolean类型的值
     */
    public static void put(String key, boolean value) {
        put(null, key, value);
    }

    /**
     * 存储Serializable类型的值<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key   存储信息的键值
     * @param value Serializable类型的值
     */
    public static void put(String key, Serializable value) {
        put(null, key, value);
    }

    /**
     * 存储String类型的值<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @param value  String类型的值
     */
    public static void put(String spName, String key, String value) {
        SPStoreMgr.getInstance().put(spName, key, value);
    }

    /**
     * 存储String类型的值<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @param value  String类型的值
     */
    public static void commit(String spName, String key, String value) {
        SPStoreMgr.getInstance().commit(spName, key, value);
    }

    /**
     * 存储long类型的值<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @param value  long类型的值
     */
    public static void commit(String spName, String key, long value) {
        SPStoreMgr.getInstance().commit(spName, key, String.valueOf(value));
    }

    /**
     * 存储int类型的值<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @param value  int类型的值
     */
    public static void put(String spName, String key, int value) {
        SPStoreMgr.getInstance().put(spName, key, String.valueOf(value));
    }

    /**
     * 存储long类型的值<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @param value  long类型的值
     */
    public static void put(String spName, String key, long value) {
        SPStoreMgr.getInstance().put(spName, key, String.valueOf(value));
    }

    /**
     * 存储double类型的值<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @param value  double类型的值
     */
    public static void put(String spName, String key, double value) {
        SPStoreMgr.getInstance().put(spName, key, String.valueOf(value));
    }

    /**
     * 存储float类型的值<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @param value  float类型的值
     */
    public static void put(String spName, String key, float value) {
        SPStoreMgr.getInstance().put(spName, key, String.valueOf(value));
    }

    /**
     * 存储boolean类型的值<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @param value  boolean类型的值
     */
    public static void put(String spName, String key, boolean value) {
        SPStoreMgr.getInstance().put(spName, key, String.valueOf(value));
    }

    /**
     * 存储Serializable类型的值<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @param value  Serializable类型的值
     */
    public static void put(String spName, String key, Serializable value) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            // 创建对象输出流，并封装字节流
            oos = new ObjectOutputStream(baos);
            // 将对象写入字节流
            oos.writeObject(value);
            // 将字节流编码成base64的字符串
            String valueStr = Util.fromUtf8Bytes(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            SPStoreMgr.getInstance().put(spName, key, valueStr);
        } catch (IOException e) {
            Log.w(TAG, "put serializable value failed. <" + spName + ", " + key + ", " + value + ">");
        } finally {
            Util.close(oos);
            Util.close(baos);
        }
    }

    /**
     * 获取String类型的存储信息<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key 存储信息的键值
     * @return String类型的存储信息
     */
    public static String getString(String key) {
        return getString(null, key);
    }

    /**
     * 获取int类型的存储信息<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key 存储信息的键值
     * @return int类型的存储信息
     */
    public static int getInt(String key) {
        return getInt(null, key);
    }

    /**
     * 获取long类型的存储信息<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key 存储信息的键值
     * @return long类型的存储信息
     */
    public static long getLong(String key) {
        return getLong(null, key);
    }

    /**
     * 获取double类型的存储信息<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key 存储信息的键值
     * @return double类型的存储信息
     */
    public static double getDouble(String key) {
        return getDouble(null, key);
    }

    /**
     * 获取float类型的存储信息<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key 存储信息的键值
     * @return float类型的存储信息
     */
    public static float getFloat(String key) {
        return getFloat(null, key);
    }

    /**
     * 获取boolean类型的存储信息<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key 存储信息的键值
     * @return boolean类型的存储信息
     */
    public static boolean getBoolean(String key) {
        return getBoolean(null, key);
    }

    /**
     * 获取Serializable类型的存储信息<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key 存储信息的键值
     * @return Serializable类型的存储信息
     */
    public static Serializable getSerializable(String key) {
        return getSerializable(null, key);
    }

    /**
     * 获取String类型的存储信息<BR>
     * 采用自定义的SharedPreference文件，为空则返回null<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @return String类型的存储信息
     */
    public static String getString(String spName, String key) {
        return getString(spName, key, null);
    }

    /**
     * 获取int类型的存储信息<BR>
     * 采用自定义的SharedPreference文件，为空则返回Integer.MIN_VALUE<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @return int类型的存储信息
     */
    public static int getInt(String spName, String key) {
        return getInt(spName, key, Integer.MIN_VALUE);
    }

    /**
     * 获取long类型的存储信息<BR>
     * 采用自定义的SharedPreference文件，为空则返回Long.MIN_VALUE<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @return long类型的存储信息
     */
    public static long getLong(String spName, String key) {
        return getLong(spName, key, Long.MIN_VALUE);
    }

    /**
     * 获取double类型的存储信息<BR>
     * 采用自定义的SharedPreference文件，为空则返回Double.MIN_VALUE<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @return double类型的存储信息
     */
    public static double getDouble(String spName, String key) {
        return getDouble(spName, key, Double.MIN_VALUE);
    }

    /**
     * 获取float类型的存储信息<BR>
     * 采用自定义的SharedPreference文件，为空则返回Float.MIN_VALUE<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @return float类型的存储信息
     */
    public static float getFloat(String spName, String key) {
        return getFloat(spName, key, Float.MIN_VALUE);
    }

    /**
     * 获取boolean类型的存储信息<BR>
     * 采用自定义的SharedPreference文件，为空则返回false<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @return boolean类型的存储信息
     */
    public static boolean getBoolean(String spName, String key) {
        return getBoolean(spName, key, false);
    }

    /**
     * 获取Serializable类型的存储信息<BR>
     * 采用自定义的SharedPreference文件，为空则返回null<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     * @return Serializable类型的存储信息
     */
    public static Serializable getSerializable(String spName, String key) {
        String value = SPStoreMgr.getInstance().getString(spName, key, null);
        if (null == value) {
            return null;
        }

        ObjectInputStream bis = null;
        ByteArrayInputStream bais = null;
        try {

            byte[] data = Base64.decode(value, Base64.DEFAULT);
            bais = new ByteArrayInputStream(data);
            bis = new ObjectInputStream(bais);
            return (Serializable) bis.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Log.d(TAG, "getSerializable failed, invalid value. <" + spName + ", " + key + ", " + value + ">");
        } finally {
            Util.close(bis);
            Util.close(bais);
        }
        return null;
    }

    /**
     * 获取String类型的存储信息<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName   自定义的SharedPreference文件名称
     * @param key      存储信息的键值
     * @param defValue 默认值
     * @return String类型的存储信息
     */
    public static String getString(String spName, String key, String defValue) {
        return getStringInSP(spName, key, defValue);
    }

    /**
     * 获取int类型的存储信息<BR>
     * 采用自定义的SharedPreference文件，为空则采用defValue<BR>
     *
     * @param spName   自定义的SharedPreference文件名称
     * @param key      存储信息的键值
     * @param defValue 默认值
     * @return int类型的存储信息
     */
    public static int getInt(String spName, String key, int defValue) {
        String value = null;
        try {
            // 注：此处需要直接调用SPStoreMgr的getString方法，需要捕获其类型转换异常并做getIntInSP操作
            value = SPStoreMgr.getInstance().getString(spName, key, String.valueOf(defValue));
            return Integer.parseInt(value);
        } catch (ClassCastException e) {
            // 这里是为了兼容老的数据通过sp.putBoolean的方式存储，获取时候通过getString的方式会有类型转换异常。
            return getIntInSP(spName, key, defValue);
        } catch (Exception e) {
            Log.d(TAG, "getInt failed, invalid value. <" + spName + ", " + key + ", " + value + ">");
        }
        return defValue;
    }

    /**
     * 获取long类型的存储信息<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName   自定义的SharedPreference文件名称
     * @param key      存储信息的键值
     * @param defValue 默认值
     * @return long类型的存储信息
     */
    public static long getLong(String spName, String key, long defValue) {
        String value = null;
        try {
            value = SPStoreMgr.getInstance().getString(spName, key, String.valueOf(defValue));
            return Long.parseLong(value);
        } catch (ClassCastException e) {
            return getLongInSP(spName, key, defValue);
        } catch (Exception e) {
            Log.d(TAG, "getLong failed, invalid value. <" + spName + ", " + key + ", " + value + ">");
        }
        return defValue;
    }

    /**
     * 获取double类型的存储信息<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName   自定义的SharedPreference文件名称
     * @param key      存储信息的键值
     * @param defValue 默认值
     * @return double类型的存储信息
     */
    public static double getDouble(String spName, String key, double defValue) {
        String value = null;
        try {
            value = SPStoreMgr.getInstance().getString(spName, key, String.valueOf(defValue));
            return Double.valueOf(value);
        } catch (Exception e) {
            Log.d(TAG, "getDouble failed, invalid value. <" + spName + ", " + key + ", " + value + ">");
        }
        return defValue;
    }

    /**
     * 获取float类型的存储信息<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName   自定义的SharedPreference文件名称
     * @param key      存储信息的键值
     * @param defValue 默认值
     * @return float类型的存储信息
     */
    public static float getFloat(String spName, String key, float defValue) {
        String value = null;
        try {
            value = SPStoreMgr.getInstance().getString(spName, key, String.valueOf(defValue));
            return Float.valueOf(value);
        } catch (ClassCastException e) {
            return getFloatInSP(spName, key, defValue);
        } catch (Exception e) {
            Log.d(TAG, "getFloat failed, invalid value. <" + spName + ", " + key + ", " + value + ">");
        }
        return defValue;
    }

    /**
     * 获取boolean类型的存储信息<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName   自定义的SharedPreference文件名称
     * @param key      存储信息的键值
     * @param defValue 默认值
     * @return boolean类型的存储信息
     */
    public static boolean getBoolean(String spName, String key, boolean defValue) {
        String value = null;
        try {
            value = SPStoreMgr.getInstance().getString(spName, key, String.valueOf(defValue));
            return Boolean.valueOf(value);
        } catch (ClassCastException e) {
            return getBooleanInSP(spName, key, defValue);
        } catch (Exception e) {
            Log.d(TAG, "getBoolean failed, invalid value. <" + spName + ", " + key + ", " + value + ">");
        }
        return defValue;
    }

    /**
     * 删除键值对应的存储信息<BR>
     * 采用默认提供的SharedPreference文件<BR>
     *
     * @param key 存储信息的键值
     */
    public static void remove(String key) {
        SPStoreMgr.getInstance().remove(key);
    }

    /**
     * 删除键值对应的存储信息<BR>
     * 采用自定义的SharedPreference文件，为空则采用默认的<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @param key    存储信息的键值
     */
    public static void remove(String spName, String key) {
        SPStoreMgr.getInstance().remove(spName, key);
    }

    /**
     * 清空默认的SharedPreference中的存储信息<BR>
     */
    public static void clear() {
        SPStoreMgr.getInstance().clear();
    }

    /**
     * 清空自定义的SharedPreference文件的存储信息，为空则清空默认的<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     */
    public static void clear(String spName) {
        SPStoreMgr.getInstance().clear(spName);
    }

    /**
     * 获取SharedPreference中所有的存储信息<BR>
     *
     * @return SharedPreference中所有的存储信息
     */
    public static Map<String, ?> getAll() {
        return SPStoreMgr.getInstance().getAll();
    }

    /**
     * SharedPreference中所有的存储信息，为空则获取默认的<BR>
     *
     * @param spName 自定义的SharedPreference文件名称
     * @return SharedPreference中所有的存储信息
     */
    public static Map<String, ?> getAll(String spName) {
        return SPStoreMgr.getInstance().getAll(spName);
    }

    private static int getIntInSP(String spName, String key, int defValue) {
        try {
            return SPStoreMgr.getInstance().getInt(spName, key, defValue);
        } catch (Exception e) {
            Log.d(TAG, "getIntInSp failed, invalid value. <" + spName + ", " + key + ">");
        }
        return defValue;
    }

    private static boolean getBooleanInSP(String spName, String key, boolean defValue) {
        try {
            return SPStoreMgr.getInstance().getBoolean(spName, key, defValue);
        } catch (Exception e) {
            Log.d(TAG, "getBooleanInSP failed, invalid value. <" + spName + ", " + key + ">");
        }
        return defValue;
    }

    private static long getLongInSP(String spName, String key, long defValue) {
        try {
            return SPStoreMgr.getInstance().getLong(spName, key, defValue);
        } catch (Exception e) {
            Log.d(TAG, "getLongInSP failed, invalid value. <" + spName + ", " + key + ">");
        }
        return defValue;
    }

    private static float getFloatInSP(String spName, String key, float defValue) {
        try {
            return SPStoreMgr.getInstance().getFloat(spName, key, defValue);
        } catch (Exception e) {
            Log.d(TAG, "getFloatInSP failed, invalid value. <" + spName + ", " + key + ">");
        }
        return defValue;
    }

    private static String getStringInSP(String spName, String key, String defValue) {
        try {
            return SPStoreMgr.getInstance().getString(spName, key, defValue);
        } catch (Exception e) {
            Log.d(TAG, "getStringInSP failed, invalid value. <" + spName + ", " + key + ">");
        }
        return defValue;
    }
}
