package com.extreme.code.juni1289.ads.pack.ads.util.nativetemplates;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import com.extreme.code.juni1289.ads.pack.ads.util.util.AdmobUtil;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

/**
 * @author Junaid Hassan on 26, March, 2022
 * Senior Android Engineer. Islamabad Pakistan
 */

public class AdmobsHandlerForMemoryGame extends FragmentActivity {
    private InterstitialAd mInterstitialAd = null;
    private AdView adView = null;
    private final String fromClass = "AdMobsHandler";
    private static AdmobsHandlerForMemoryGame adMobsHandlerInstance = null;

    public static AdmobsHandlerForMemoryGame getInstance() {
        if (adMobsHandlerInstance == null) {
            adMobsHandlerInstance = new AdmobsHandlerForMemoryGame();
        }
        return adMobsHandlerInstance;
    }//getInstance ends

    /**
     * constructor
     */
    public AdmobsHandlerForMemoryGame() {
        super();
    }//AdMobsHandler ends

    /**
     * method to create and show admobs ad
     * if isInterstitialAdNeedToShow, show the Interstitial Ad
     * else, only show the banner ad
     */
    public void onShowAdMobs(Activity activity, LinearLayout linearLayoutForAds, boolean isInterstitialAdNeedToShowAuto) {
        if (activity != null && !activity.isFinishing()) {
            //clear to go
            /*create the banner ad*/
            initBannerAd(activity, linearLayoutForAds);//create the Smart Banner Ad
            /*check if Interstitial Ad need to show
              if true, show the Interstitial Ad
              else, do not show the Interstitial Ad*/
            if (isInterstitialAdNeedToShowAuto) {
                onInterstitialAdLoadThenShow(activity);//create the Interstitial Ad
            }
        }//activity no null
    }//onShowAdMob ends

    private void initBannerAd(Activity activity, LinearLayout linearLayoutForAds) {
        if (activity != null && !activity.isFinishing()) {
            adView = new AdView(activity);//create the adView here
            adView.setAdSize(AdSize.BANNER);//set the adSize as Smart Banner
            adView.setAdUnitId(AdmobUtil.getInstance().getBannerPublishAdId());//set the banner ad unit id
            /*add the ad view in the view hierarchy
              create the linear layout params
              add the view with the layout params*/
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);//create the layout params
            linearLayoutForAds.addView(adView, params);//add the ad view with view and layout params
            /*create the banner ads request to load the banner ad here
              add the test device as a default testing device*/
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
            initBannerAdListener(activity, linearLayoutForAds);//listen to the adView listeners
        }//activity not null nor finished
    }//initBannerAd ends

    private void initBannerAdListener(Activity activity, final LinearLayout linearLayoutForAds) {
        if (activity != null && !activity.isFinishing()) {
            if (adView != null && linearLayoutForAds != null) {
                adView.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        //hide the layout for linearLayoutForAds
                        linearLayoutForAds.setVisibility(View.GONE);
                    }//onAdClosed ends

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                        super.onAdFailedToLoad(adError);
                        //hide the layout for linearLayoutForAds
                        linearLayoutForAds.setVisibility(View.GONE);
                    }//onAdFailedToLoad ends

                    @Override
                    public void onAdOpened() {
                        super.onAdOpened();
                        //hide the layout for linearLayoutForAds
                        linearLayoutForAds.setVisibility(View.GONE);
                    }//onAdOpened ends

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        //show the layout for linearLayoutForAds
                        linearLayoutForAds.setVisibility(View.VISIBLE);
                    }//onAdLoaded ends

                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }//onAdClicked ends

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }//onAdImpression ends
                });
            }
        }//activity not null nor activity is finishing neither ad view is null or linearLayoutForAds is null
    }//initBannerAdListener ends

    public void onInterstitialAdLoadThenShow(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            AdRequest adRequest = new AdRequest.Builder().build();

            InterstitialAd.load(this, AdmobUtil.getInstance().getInterstitialPublishAdId(), adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.
                            mInterstitialAd = interstitialAd;
                            mInterstitialAd.show(activity);
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                            mInterstitialAd = null;
                        }
                    });
        }//activity not null nor isFinishing
    }//initInterstitialAd ends

    /**
     * activity life cycle events
     * handle ad view on all these life cycle events
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }//if (adView != null) ends
    }//onResume ends

    @Override
    protected void onPause() {
        super.onPause();
        if (adView != null) {
            adView.pause();
        }//if (adView != null) ends

        mInterstitialAd = null;
    }//onPause ends

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adView != null) {
            adView.destroy();
        }//if (adView != null) ends

        mInterstitialAd = null;
    }//onDestroy ends
}
