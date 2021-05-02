/*
 * Copyright 2021 Nurujjaman Pollob.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package dev.nurujjamanpollob.iframeplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import dev.nurujjamanpollob.iframeplayer.backgroundhandler.NJPollobIFrameUtility;

public class NJPollobIframePlayer extends RelativeLayout {

    WebView webview;
    View view;
    Context context;

    private View mCustomView;
    private int mOriginalSystemUiVisibility;
    private int mOriginalOrientation;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private OnEventPerformed eventListener;



    public NJPollobIframePlayer(Context context) {
        super(context);

        this.context = context;

        init();
    }

   public NJPollobIframePlayer(Context cntx, AttributeSet set){

        super(cntx, set);
       this.context = cntx;

       init();

    }

    public NJPollobIframePlayer(Context cntx, AttributeSet set, int defStyle){

        super(cntx, set, defStyle);

        this.context = cntx;

        init();


    }

    @SuppressLint({"InflateParams", "SetJavaScriptEnabled"})
    private void init() {


            // Creating an instance for View Object
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.iframe_player_layout, this, true);
            webview = view.findViewById(R.id.iframe_player_webview);

            webview.setOnClickListener(v -> {
                if (eventListener != null) {

                    eventListener.onSoftClicked(v);
                }
            });

            webview.setOnLongClickListener(v -> {

                if (eventListener != null) {

                    eventListener.onLongClicked(v);
                }
                return false;
            });


            webview.setWebChromeClient(new WebChromeClient() {

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onShowCustomView(View view, CustomViewCallback callback) {

                    // if a view already exists then immediately terminate the new one

                    if (mCustomView != null) {
                        onHideCustomView();
                        return;
                    }

                    // 1. Stash the current state
                    mCustomView = view;


                    mOriginalSystemUiVisibility = getActivity(context).getWindow().getDecorView().getSystemUiVisibility();
                    mOriginalOrientation = getActivity(context).getRequestedOrientation();

                    // 2. Stash the custom view callback
                    mCustomViewCallback = callback;

                    // 3. Add the custom view to the view hierarchy
                    FrameLayout decor = (FrameLayout) getActivity(context).getWindow().getDecorView();
                    decor.addView(mCustomView, new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));


                    // 4. Change the state of the window
                    getActivity(context).getWindow().getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                    View.SYSTEM_UI_FLAG_FULLSCREEN |
                                    View.SYSTEM_UI_FLAG_IMMERSIVE);
                    getActivity(context).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                    super.onShowCustomView(view, callback);
                }

                @Override
                public void onHideCustomView() {
                    // 1. Remove the custom view
                    FrameLayout decor = (FrameLayout) getActivity(context).getWindow().getDecorView();
                    decor.removeView(mCustomView);
                    mCustomView = null;

                    // 2. Restore the state to it's original form
                    getActivity(context).getWindow().getDecorView()
                            .setSystemUiVisibility(mOriginalSystemUiVisibility);
                    getActivity(context).setRequestedOrientation(mOriginalOrientation);

                    // 3. Call the custom view callback
                    mCustomViewCallback.onCustomViewHidden();
                    mCustomViewCallback = null;

                    super.onHideCustomView();
                }

                @Override
                public void onProgressChanged(WebView view, int newProgress) {


                    super.onProgressChanged(view, newProgress);
                }
            });


            webview.setWebViewClient(new WebViewClient() {


                @Override
                public void onPageFinished(WebView view, String url) {

                    super.onPageFinished(view, url);
                }

                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

                    Toast.makeText(context, "Network Connection Error Code: " + error.toString(), Toast.LENGTH_LONG).show();
                    super.onReceivedError(view, request, error);
                }
            });


            WebSettings settings = webview.getSettings();
            settings.setJavaScriptEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                WebView.enableSlowWholeDocumentDraw();
            }






    }

    public void loadIFrameByIFrameUtility(NJPollobIFrameUtility utilityObjectInstance){

      System.out.println(utilityObjectInstance.toString());

       // So we are using Utility object to get compiled HTML
       webview.loadData(utilityObjectInstance.toString(), "text/html", "UTF8");

    }


    /**
     * Get activity instance from desired context.
     */
    public static Activity getActivity(Context context) {
        if (context == null) return null;
        if (context instanceof Activity) return (Activity) context;
        if (context instanceof ContextWrapper) return getActivity(((ContextWrapper)context).getBaseContext());
        return null;
    }




    // add event listener interface for various event
    public interface OnEventPerformed{

        void onSoftClicked(View view);
        void onLongClicked(View view);

    }



    // event listener assigner
    public void listenOnClickReletedEvent(OnEventPerformed eventListener){

        this.eventListener = eventListener;

    }


}
