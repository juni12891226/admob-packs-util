package com.extreme.code.juni1289.ads.pack.ads.util.util;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

/**
 * @author Junaid Hassan on 25, March, 2022
 * Senior Android Engineer. Islamabad Pakistan
 */
public class AdmobUtil {

    private static AdmobUtil instance = null;

    public static AdmobUtil getInstance() {
        if (instance == null) {
            instance = new AdmobUtil();
        }
        return instance;
    }//getInstance ends

    private boolean isShowNativeAds = true;
    private boolean isShowTestAds = true;
    private String NATIVE_TEST_UNIT_ID = "ca-app-pub-3940256099942544/2247696110";
    private String nativePublishAdId = "";

    private String BANNER_TEST_UNIT_ID = "ca-app-pub-3940256099942544/6300978111";
    private String bannerPublishAdId = "";

    private String INTERSTIAL_TEST_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";
    private String interstitialPublishAdId = "";

    private String REWARDED_TEST_UNIT_ID = "ca-app-pub-3940256099942544/5224354917";
    private String rewardedPublishAdId = "";

    public void initAdmobSDK(Application application,
                             boolean isShowTestAds,
                             boolean isShowNativeAds,
                             String bannerPublishAdId,
                             String nativePublishAdId,
                             String interstitialPublishAdId,
                             String rewardedPublishAdId) {
        MobileAds.initialize(application, initializationStatus -> {
            RequestConfiguration configuration = new RequestConfiguration.Builder()/*.setTestDeviceIds(Collections.singletonList(AdRequest.DEVICE_ID_EMULATOR))*/.build();
            MobileAds.setRequestConfiguration(configuration);
        });
    }

    public boolean isShowNativeAds() {
        return isShowNativeAds;
    }

    public void setShowNativeAds(boolean showNativeAds) {
        this.isShowNativeAds = showNativeAds;
    }

    public boolean isShowTestAds() {
        return isShowTestAds;
    }

    public void setShowTestAds(boolean showTestAds) {
        this.isShowTestAds = showTestAds;
    }

    public String getNativePublishAdId() {
        if (getInstance().isShowTestAds()) {
            return NATIVE_TEST_UNIT_ID;
        }
        return nativePublishAdId;
    }

    public void setNativePublishAdId(String nativePublishAdId) {
        this.nativePublishAdId = nativePublishAdId;
    }

    public String getBannerPublishAdId() {
        if (getInstance().isShowTestAds()) {
            return BANNER_TEST_UNIT_ID;
        }
        return bannerPublishAdId;
    }

    public void setBannerPublishAdId(String bannerPublishAdId) {
        this.bannerPublishAdId = bannerPublishAdId;
    }

    public String getInterstitialPublishAdId() {
        if (getInstance().isShowTestAds()) {
            return INTERSTIAL_TEST_UNIT_ID;
        }
        return interstitialPublishAdId;
    }

    public void setInterstitialPublishAdId(String interstitialPublishAdId) {
        this.interstitialPublishAdId = interstitialPublishAdId;
    }

    public String getRewardedPublishAdId() {
        if (getInstance().isShowTestAds()) {
            return REWARDED_TEST_UNIT_ID;
        }
        return rewardedPublishAdId;
    }

    public void setRewardedPublishAdId(String rewardedPublishAdId) {
        this.rewardedPublishAdId = rewardedPublishAdId;
    }
}
