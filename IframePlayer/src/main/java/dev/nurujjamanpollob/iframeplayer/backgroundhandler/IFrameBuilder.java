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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IFrameBuilder {

    /* This class create iframe from key / value pair */


    private static  String HTML_INCLUDE_IFRAME;
    static   String TMPIFrame = "";

    public void putKeyValuePair(List<HashMap<String, String>> IFrameDataToBuild) throws IFrameBuildException {

        String startingIframe = "<iframe ", endIFrame = "></iframe>";


        // RUN a simple loop and get all entry
        for(int i = 0; i < IFrameDataToBuild.size(); i++) {

            HashMap<String, String> map = IFrameDataToBuild.get(i);

            for(Map.Entry<String, String> mapEntry: map.entrySet()) {

                String key = "";
                String value = "";
                if(mapEntry.getKey() != null){

                    if (mapEntry.getValue() != null){
                        key = mapEntry.getKey()+"=\"";
                        value = mapEntry.getValue()+"\"";
                    }else {

                        key = mapEntry.getKey();
                    }

                }else {

                    throw new IFrameBuildException("No key has been assigned for a value");
                }


                String properties ="";

                if (i >= IFrameDataToBuild.size() -1) {

                    properties =  key +  value;


                }else {

                    properties = key + value + " ";
                }



                TMPIFrame = String.format("%s%s", TMPIFrame, properties);



            }



        }


        // the work is simply success, assign builded IFrame to HTML

        HTML_INCLUDE_IFRAME = startingIframe + TMPIFrame + endIFrame;


    }


    public  String getCompiledIFrame(){

        return HTML_INCLUDE_IFRAME;
    }

}
