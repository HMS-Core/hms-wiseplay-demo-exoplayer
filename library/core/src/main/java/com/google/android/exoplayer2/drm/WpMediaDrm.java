/*
 * Copyright (c) Hisilicon Technologies Co., Ltd. 2021-2022. All rights reserved.
 * Description: the api of DRM SDK
 * Author: wangjian
 * Create: 2022-08-20
 */

package com.google.android.exoplayer2.drm;

import android.media.MediaCodec;
import android.media.MediaCryptoException;
import android.media.MediaDrm;
import android.media.MediaDrmException;
import android.media.NotProvisionedException;
import android.os.PersistableBundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.huawei.wiseplaydrmsdk.MediaKeySystemAPI;
import com.huawei.wiseplaydrmsdk.common.CryptoException;
import com.huawei.wiseplaydrmsdk.common.DrmCryptoInfo;
import com.huawei.wiseplaydrmsdk.common.MediaKeySystemListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * WpMediaDrm封装drm调用软解sdk相关接口
 *
 * @author wangjian
 * @since 2022-8-26
 */
public class WpMediaDrm implements ExoMediaDrm<FrameworkMediaCrypto> {
    private static final String TAG = "WpMediaDrm";

    private static final int RESULT_SUCCESS = 0;

    private final MediaKeySystemAPI mediaDrm;

    private final UUID uuid;

    private byte[] sessionId;

    /**
     * {@link Provider} that returns a new {@link WpMediaDrm} for the requested UUID.
     * Returns a {@link DummyExoMediaDrm} if the protection scheme identified by the given UUID is not
     * supported by the device.
     */
    public static final Provider<FrameworkMediaCrypto> DEFAULT_PROVIDER = uuid -> {
        try {
            return newInstance(uuid);
        } catch (UnsupportedDrmException e) {
            Log.e(TAG, "Failed to instantiate a WisePlayMediaDrm for uuid: " + uuid + ".");
            return new DummyExoMediaDrm<>();
        }
    };

    /**
     * Creates an instance with an initial reference count of 1.
     *
     * @param uuid The scheme uuid.
     * @return The created instance.
     * @throws UnsupportedDrmException If the DRM scheme is unsupported or cannot be instantiated.
     */
    public static WpMediaDrm newInstance(UUID uuid) throws UnsupportedDrmException {
        try {
            return new WpMediaDrm(uuid);
        } catch (Exception e) {
            throw new UnsupportedDrmException(UnsupportedDrmException.REASON_INSTANTIATION_ERROR, e);
        }
    }

    private WpMediaDrm(UUID uuid) {
        Assertions.checkNotNull(uuid);
        this.uuid = uuid;
        Log.i(TAG, "create WpMediaDrm: " + this.uuid);
        this.mediaDrm = new MediaKeySystemAPI();
        boolean success = this.initWisePlayMediaDrm(MediaKeySystemAPI.DRM_LOG_DEBUG_LEVEL, "huawei", "1.0.0");
        Log.i(TAG, "create WpMediaDrm end uuid: " + this.uuid + ", isSuccess: " + success);
    }

    @Override
    public void setOnEventListener(@Nullable OnEventListener<? super FrameworkMediaCrypto> listener) {
        this.mediaDrm.setOnEventListener(sessionId, new MediaKeySystemListener() {
            @Override
            public void sendEvent(@KeySystemEventType int eventType, long extra, @Nullable byte[] keyId,
                @MediaKeyStatusType int keyStatus) {
                Log.i(TAG, "mediaDrm.sendEvent eventType: " + eventType + ", extra: " + extra + ", keyStatus: "
                    + keyStatus + ", keyId: " + Util.fromUtf8Bytes(keyId));
                if (listener != null) {
                    listener.onEvent(WpMediaDrm.this, sessionId, eventType, (int) extra, keyId);
                }
            }
        });
    }

    @Override
    public void setOnKeyStatusChangeListener(@Nullable OnKeyStatusChangeListener listener) {
    }

    @Override
    public boolean initWisePlayMediaDrm(int logLevel, String appProvider, String appVersion) {
        try {
            int result = this.mediaDrm.initWisePlayMediaDrm(logLevel, appProvider, appVersion);
            Log.i(TAG, "initWisePlayMediaDrm result: " + result);
            return result == RESULT_SUCCESS;
        } catch (RuntimeException e) {
            Log.e(TAG, e.getMessage());
        }
        return false;
    }

    @Override
    public byte[] openSession() throws MediaDrmException {
        sessionId = this.mediaDrm.openSession();
        Log.i(TAG, "openSession sessionId: " + sessionId);
        return sessionId;
    }

    @Override
    public void closeSession(byte[] sessionId) {
        int result = this.mediaDrm.closeSession(sessionId);
        Log.i(TAG, "closeSession sessionId: " + Util.fromUtf8Bytes(sessionId) + ", result: " + result);
    }

    @Override
    public KeyRequest getKeyRequest(byte[] scope, @Nullable List<SchemeData> schemeDatas, int keyType,
        @Nullable HashMap<String, String> optionalParameters) throws NotProvisionedException {
        Log.i(TAG, "getKeyRequest uuid: " + uuid);
        SchemeData schemeData = null;
        byte[] initData = null;
        String mimeType = null;
        if (schemeDatas != null && schemeDatas.size() > 0) {
            schemeData = schemeDatas.get(0);
            initData = schemeData.data;
            mimeType = schemeData.mimeType;
        }

        Log.i(TAG, "get drm key request keyType: " + keyType
                + ", scope: " + Util.fromUtf8Bytes(scope)
                + ", mimeType: " + mimeType
                + ", initData: " + Util.fromUtf8Bytes(initData));

        // 自动license renew时initData需传null
        byte[] requestData = this.mediaDrm.getKeyRequest(scope, initData, keyType);
        Log.i(TAG, "requestData: " + Util.fromUtf8Bytes(requestData));

        String licenseServerUrl = "";
        if (schemeData != null && !TextUtils.isEmpty(schemeData.licenseServerUrl)) {
            licenseServerUrl = schemeData.licenseServerUrl;
        }

        // chinadrm2.0二期renew license的时候的请求地址需要从license的相关信息中去获取
        if (initData == null && keyType != MediaDrm.KEY_TYPE_RELEASE) {
            Map<String, String> keyStatus = queryKeyStatus(scope);
            licenseServerUrl = keyStatus.get(WidevineUtil.PROPERTY_RENEWAL_SERVER_URL);
            Log.i(TAG, "renew getKeyRequest keyStatus licenseServerUrl: " + licenseServerUrl);
        }

        return new KeyRequest(requestData, licenseServerUrl);
    }

    @Nullable
    @Override
    public byte[] provideKeyResponse(int keyType, byte[] scope, byte[] response) throws NotProvisionedException {
        Log.i(TAG, "mediaDrm.provideKeyResponse scope: " + Util.fromUtf8Bytes(scope));
        return this.mediaDrm.provideKeyResponse(keyType, scope, response);
    }

    @Override
    public ProvisionRequest getProvisionRequest() {
        Log.i(TAG, "mediaDrm.getProvisionRequest");
        byte[] requestData = mediaDrm.getProvisionRequest(MediaKeySystemAPI.SM2_ALGO_SUPPORT);
        // 默认provision地址
        Log.i(TAG, "getProvisionRequest: "+Util.fromUtf8Bytes(requestData));
        String provisionUrl = "https://provision.dev.trustdta.com:8217/provision/v1/wiseplay";
        return new ProvisionRequest(requestData, provisionUrl);
    }

    @Override
    public void provideProvisionResponse(byte[] response) {
        int ret = mediaDrm.provideProvisionResponse(response);
        Log.i(TAG, "mediaDrm.provideProvisionResponse ret: " + ret);
    }

    @Override
    public Map<String, String> queryKeyStatus(byte[] sessionId) {
        Log.d(TAG, "mediaDrm.queryKeyStatus");
        return mediaDrm.queryKeyStatus(sessionId);
    }

    @Override
    public void acquire() {
        Log.i(TAG, "acquire");
    }

    @Override
    public void release() {
    }

    @Override
    public void restoreKeys(byte[] sessionId, byte[] keySetId) {
        int ret = mediaDrm.restoreKeys(sessionId, keySetId);
        Log.i(TAG, "mediaDrm.restoreKeys ret: " + ret);
    }

    @Nullable
    @Override
    public PersistableBundle getMetrics() {
        return null;
    }

    @Override
    public String getPropertyString(String propertyName) {
        Log.i(TAG, "mediaDrm.getPropertyString");
        return mediaDrm.getPropertyString(propertyName);
    }

    @Override
    public byte[] getPropertyByteArray(String propertyName) {
        Log.i(TAG, "mediaDrm.getPropertyByteArray");
        return mediaDrm.getPropertyByteArray(propertyName);
    }

    @Override
    public void setPropertyString(String propertyName, String value) {
    }

    @Override
    public void setPropertyByteArray(String propertyName, byte[] value) {
    }

    @Override
    public FrameworkMediaCrypto createMediaCrypto(byte[] sessionId) throws MediaCryptoException {
        Log.i(TAG, "createCryptoConfig");
        return new FrameworkMediaCrypto(uuid, sessionId, false);
    }

    @Override
    public Class<FrameworkMediaCrypto> getExoMediaCryptoType() {
        return FrameworkMediaCrypto.class;
    }

    @Override
    public void removeOfflineLicense(byte[] keySetId) {
        int ret = mediaDrm.removeOfflineLicense(keySetId);
        Log.i(TAG, "mediaDrm.removeOfflineLicense ret: " + ret);
    }

    @Override
    public boolean isUseWisePlayDrmSDK() {
        return true;
    }

    @Override
    public byte[] decryptData(DrmCryptoInfo cryptoInfo, byte[] srcPtr) throws MediaCodec.CryptoException {
        try {
            return mediaDrm.decryptData(sessionId, cryptoInfo, srcPtr);
        } catch (CryptoException e) {
            Log.e(TAG, "decryptData error code: " + e.getErrorCode() + ", msg: " + e.getMessage());
            throw new MediaCodec.CryptoException(e.getErrorCode(), e.getMessage());
        }
    }
}