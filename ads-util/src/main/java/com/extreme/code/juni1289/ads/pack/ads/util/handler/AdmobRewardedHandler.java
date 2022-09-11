package com.extreme.code.juni1289.ads.pack.ads.util.handler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.extreme.code.juni1289.ads.pack.ads.util.R;
import com.extreme.code.juni1289.ads.pack.ads.util.interfaces.AdmobRewardedEvents;
import com.extreme.code.juni1289.ads.pack.ads.util.util.AdmobUtil;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import androidx.annotation.NonNull;

/**
 * @author Junaid Hassan on 09, July, 2022
 * Senior Android Engineer. Islamabad Pakistan
 */
public class AdmobRewardedHandler {
    public static void showAdmobRewarded(Activity activity, AdmobRewardedEvents admobRewardedEvents) {
        ProgressDialog progressDialog = new ProgressDialog(activity, R.style.AdmobProgressDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(activity, AdmobUtil.getInstance().getRewardedPublishAdId(),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.e("reX", "rewardedAdIsNull:::" + loadAdError);
                        progressDialog.dismiss();
                        admobRewardedEvents.onRewardedAdComplete(false);
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        progressDialog.dismiss();
                        rewardedAd.show(activity, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                admobRewardedEvents.onRewardedAdComplete(true);
                            }
                        });
                    }
                });
    }
}
