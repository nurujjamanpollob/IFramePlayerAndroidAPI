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

package dev.nurujjamanpollob.iframeplayerandroidapi;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import dev.nurujjamanpollob.iframeplayer.NJPollobIframePlayer;
import dev.nurujjamanpollob.iframeplayer.backgroundhandler.DataHolder;
import dev.nurujjamanpollob.iframeplayer.backgroundhandler.NJPollobIFrameUtility;

public class MainActivity extends AppCompatActivity {

    NJPollobIframePlayer iframePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize IFrame Player
iframePlayer         = findViewById(R.id.iframe_player);



        // Create data for Player

        DataHolder utility = new DataHolder();
        utility.includeIframe("<iframe width=\"100%\" height=\"200\" src=\"https://www.youtube.com/watch?v=9No-FiEInLA\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen=\"true\"></iframe>");


        NJPollobIFrameUtility utility1 = new NJPollobIFrameUtility(utility);

        // Pass the data to player
        iframePlayer.loadIFrameByIFrameUtility(utility1.buildIFrame());


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void pipMe(View view) {

        iframePlayer.convertToMiniWindow();
    }
}