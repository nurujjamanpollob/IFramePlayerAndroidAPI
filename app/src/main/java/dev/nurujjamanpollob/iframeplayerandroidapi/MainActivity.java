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

package dev.nurujjamanpollob.iframeplayerandroidapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dev.nurujjamanpollob.iframeplayer.NJPollobIframePlayer;
import dev.nurujjamanpollob.iframeplayer.backgroundhandler.DataHolder;
import dev.nurujjamanpollob.iframeplayer.backgroundhandler.NJPollobIFrameUtility;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NJPollobIframePlayer wb = findViewById(R.id.wbvw);

        EditText ed = findViewById(R.id.iframe_edittext);
        Button bt = findViewById(R.id.ifrane_button);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed.getText().toString().equals("")){

                    Toast.makeText(MainActivity.this, "Please provide input", Toast.LENGTH_LONG).show();
                }else {

                    // create data for Utility class
                    DataHolder dt = new DataHolder();
                    dt.includeIframe(ed.getText().toString());

                    // create a new instance of Utility class
                    // and load IFrame
                    wb.loadIFrameByIFrameUtility(new NJPollobIFrameUtility(dt).buildIFrame());
                    

                }
            }
        });
        DataHolder utility = new DataHolder();
        utility.includeIframe("<iframe width=\"100%\" height=\"200\" src=\"https://www.youtube.com/embed/nCgQDjiotG0\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen=\"true\"></iframe>");


        NJPollobIFrameUtility utility1 = new NJPollobIFrameUtility(utility);

        wb.loadIFrameByIFrameUtility(utility1.buildIFrame());

        System.out.println(utility1.buildIFrame());

    }
}