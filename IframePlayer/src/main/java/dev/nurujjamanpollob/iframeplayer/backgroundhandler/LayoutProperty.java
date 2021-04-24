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

package dev.nurujjamanpollob.iframeplayer.backgroundhandler;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

@SuppressWarnings({"UnusedDeclaration"})
public class LayoutProperty {
    
    Context context;
    

    
    public LayoutProperty(Context appCntx){
        
        context = appCntx;
    }

    public int getDisplayWidth() {

        WindowManager wm = null;
        if (context != null) {
            wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        Display display = null;
        if (wm != null) {
            display = wm.getDefaultDisplay();
        }

        Point size = new Point();
        if (display != null) {
            display.getSize(size);
        }


        return size.x;
    }


    public int getDisplayHeight() {

        WindowManager wm = null;
        if (context != null) {
            wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        Display display = null;
        if (wm != null) {
            display = wm.getDefaultDisplay();
        }

        Point size = new Point();
        if (display != null) {
            display.getSize(size);
        }


        return size.y;
    }

    public int getOptimalHeightForPlayer(){

        WindowManager wm = null;
        if (context != null) {
            wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        Display display = null;
        if (wm != null) {
            display = wm.getDefaultDisplay();
        }

        Point size = new Point();
        if (display != null) {
            display.getSize(size);
        }


       return size.y /3;
    }
}