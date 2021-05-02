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

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class NJPollobYTPlayer extends RelativeLayout {

    // add Context field
    Context context;


    /* Add three constructor parameter matching super */

    public NJPollobYTPlayer(Context context) {
        super(context);

        this.context = context;

        init();
    }

    public NJPollobYTPlayer(Context cntx, AttributeSet set){

        super(cntx, set);
        this.context = cntx;

        init();

    }

    public NJPollobYTPlayer(Context cntx, AttributeSet set, int defStyle){

        super(cntx, set, defStyle);

        this.context = cntx;

        init();

    }



    // initiate views
    private void init(){

        // add field for WebView



    }

}
