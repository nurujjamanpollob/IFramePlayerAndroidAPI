/*
 *  Copyright 2021 Nurujjaman Pollob.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package dev.nurujjamanpollob.iframeplayer;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

public class NJPollobWebChromeClient extends WebChromeClient {


    private Context context;

    private View mCustomView;
    private int mOriginalSystemUiVisibility;
    private int mOriginalOrientation;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;

    // Requires context, it is must
    public NJPollobWebChromeClient(Context context){

        this.context = context;

    }


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
    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
        return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
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



}
