/*
 * Copyright (c) 2021. Nurujjaman Pollob, All Right Reserved. 
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package dev.nurujjamanpollob.iframeplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PictureInPictureParams;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Rational;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.RequiresApi;

import dev.nurujjamanpollob.iframeplayer.backgroundhandler.NJPollobIFrameUtility;
import dev.nurujjamanpollob.iframeplayer.utility.GeneralCode;

public class NJPollobIframePlayer extends RelativeLayout {

    WebView webview;
    View view;
    Context context;

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


        /*
            // Creating an instance for View Object
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.iframe_player_layout, this, true);
            webview = view.findViewById(R.id.iframe_player_webview);

         */


        // Creating an instance for View Object
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Create a new instance of WebView
        webview = new WebView(context);
        webview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // Attach webview to Layout View(Layout extends ViewGroup)
        addView(webview);



        // Inflate view
        view = inflater.inflate(R.layout.empty_layout, this, true);

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

            // Use preDefined class
            webview.setWebChromeClient(new NJPollobWebChromeClient(context));

            WebSettings settings = webview.getSettings();
            settings.setJavaScriptEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                WebView.enableSlowWholeDocumentDraw();
            }





    }

    public void loadIFrameByIFrameUtility(NJPollobIFrameUtility utilityObjectInstance){


       // So we are using Utility object to get compiled HTML
       webview.loadData(utilityObjectInstance.toString(), "text/html", "UTF8");

    }







    // add event listener interface for various event
    public interface OnEventPerformed{

        void onSoftClicked(View view);
        void onLongClicked(View view);

    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void convertToMiniWindow(){

        Activity activity = GeneralCode.getActivity(context);

        Display d = view.getDisplay();

        Point p = new Point();
        d.getSize(p);
        int width = p.x;
        int height = p.y;

        Rational ratio = new Rational(800, 600);
        PictureInPictureParams.Builder pip_Builder = new PictureInPictureParams.Builder();
        pip_Builder.setAspectRatio(ratio).build();

       activity.enterPictureInPictureMode(pip_Builder.build());

    }



    // event listener assigner
    public void listenOnClickReletedEvent(OnEventPerformed eventListener){

        this.eventListener = eventListener;

    }


}
