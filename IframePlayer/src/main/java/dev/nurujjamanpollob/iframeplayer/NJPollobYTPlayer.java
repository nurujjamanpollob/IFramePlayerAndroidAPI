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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import dev.nurujjamanpollob.iframeplayer.backgroundhandler.DataHolder;
import dev.nurujjamanpollob.iframeplayer.backgroundhandler.IFrameBuildException;
import dev.nurujjamanpollob.iframeplayer.backgroundhandler.NJPollobIFrameUtility;

public class NJPollobYTPlayer extends RelativeLayout {

    // add Context field
    Context context;

    // add WebView Field
    WebView webView;

    // Add View Field
    View view;

    // Video Token from Method
    String videoToken = null;

    // Video URL from Method
    String ytVideoURL = null;



    /* Add three constructor parameter matching super */

    public NJPollobYTPlayer(Context context) {
        super(context);

        this.context = context;

        try {
            init(context,null, -1);
        } catch (IFrameBuildException e) {
            e.printStackTrace();
        }
    }

    public NJPollobYTPlayer(Context cntx, AttributeSet set){

        super(cntx, set);
        this.context = cntx;

        try {
            init(cntx, set, -1);
        } catch (IFrameBuildException e) {
            e.printStackTrace();
        }

    }

    public NJPollobYTPlayer(Context cntx, AttributeSet set, int defStyle){

        super(cntx, set, defStyle);

        this.context = cntx;

        try {
            init(cntx, set, defStyle);
        } catch (IFrameBuildException e) {
            e.printStackTrace();
        }

    }



    // initiate views
    @SuppressLint("SetJavaScriptEnabled")
    private void init(Context cntx, AttributeSet attributeSet, int defStyle) throws IFrameBuildException {


        String youtubeVideoURL = null;
        String youtubeVideoToken = null;

        // Check if attributeSet is null or not

        if (attributeSet != null){

            @SuppressLint("CustomViewStyleable") TypedArray typedArray = cntx.obtainStyledAttributes(attributeSet, R.styleable.NJPollobYTView, defStyle, 0);

            String url = typedArray.getString(R.styleable.NJPollobYTView_youtube_video_url);

            if(url != null){

                youtubeVideoURL = url;
            }


            String token = typedArray.getString(R.styleable.NJPollobYTView_youtube_video_token);

            if(token != null){

                youtubeVideoToken = token;


                System.out.println("Token Received:" + token);
            }

            typedArray.recycle();


        }

        // Creating an instance for View Object
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.yt_iframe_player, this, true);
        webView = view.findViewById(R.id.yt_iframe_main_view);



        if (youtubeVideoURL != null && youtubeVideoToken == null){


            // RUN Initializer
            buildAndPlayIFrame(returnEmbedURL(youtubeVideoURL));


        }else {

            if (youtubeVideoToken != null && youtubeVideoURL == null) {

                // RUN Initializer
                buildAndPlayIFrame("https://www.youtube.com/embed/" + youtubeVideoToken);

                System.out.println("Token Received:" + youtubeVideoToken);

            }else {

                System.out.println("No Value Passed: token "+youtubeVideoToken+" URL: "+youtubeVideoURL);
            }
        }



          // Set webChromeClient
          webView.setWebChromeClient(new NJPollobWebChromeClient(context));
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw();
        }



    }


    public String returnEmbedURL(String youtubeVideoURL){


        String ytV1 = "https://www.youtube.com/watch?v=";
        String ytV2 = "https://youtu.be/";

        int lengthOfInput = youtubeVideoURL.length();

        try {

            if (youtubeVideoURL.substring(0, ytV1.length()).equals(ytV1)) {

                return "https://www.youtube.com/embed/"+ youtubeVideoURL.substring(ytV1.length(), lengthOfInput);
            }
        }catch (Exception e) {

            try {

                if (youtubeVideoURL.substring(0, ytV2.length()).equals(ytV2)) {
                    return "https://www.youtube.com/embed/"+ youtubeVideoURL.substring(ytV2.length(), lengthOfInput);
                }

            }catch (Exception es) {

                return null;

            }
        }

        return null;

    }



    // We will build and play IFrame

    private void buildAndPlayIFrame(String url) throws IFrameBuildException {


        // We are using reusable code
        DataHolder dataHolder = new DataHolder();

        // Using NJPollobIFrameUtility Class
        NJPollobIFrameUtility iFrameUtility = new NJPollobIFrameUtility(dataHolder);

        // Store keys
        String[] keys = {"width", "height", "src", "title", "frameborder", "allow", "allowfullscreen"};

        // Store Values
        String[] values = {"100%", "100%", url, "YouTube video player", "0", "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture", "true" };


        // Include Builded IFrame
        dataHolder.includeIframe(iFrameUtility.buidIframeByKeyValue(keys,values));

        // load the Data

        System.out.println(iFrameUtility.buildIFrame().toString());
        webView.loadData(iFrameUtility.buildIFrame().toString(), "text/html", "UTF8");


    }



    // put Youtube video token programmatically
    public void putYouTubeVideoToken(String videoToken){

        try {
            buildAndPlayIFrame("https://www.youtube.com/embed/" + videoToken);
        } catch (IFrameBuildException e) {
            e.printStackTrace();
        }
    }


    // put YouTube video URL Programmatically
    public void putYouTubeVideoURL(String videoPageURL){

        // RUN Initializer
        try {
            buildAndPlayIFrame(returnEmbedURL(videoPageURL));
        } catch (IFrameBuildException e) {
            e.printStackTrace();
        }
    }


}
