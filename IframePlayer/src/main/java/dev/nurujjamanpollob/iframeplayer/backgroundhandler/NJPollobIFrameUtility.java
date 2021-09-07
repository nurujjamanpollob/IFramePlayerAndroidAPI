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

package dev.nurujjamanpollob.iframeplayer.backgroundhandler;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SuppressWarnings({"UnusedDeclaration"})

public class NJPollobIFrameUtility {

    // create data holder instance
    DataHolder dataHolder;



    public NJPollobIFrameUtility(DataHolder compiledHTML){

        dataHolder = compiledHTML;

    }

    // Empty constructor is needed
    public NJPollobIFrameUtility(){

    }

    public String buidIframeByKeyValue(String[]keys, String[] values) throws IFrameBuildException {

        List<HashMap<String, String>> buildIFrame = new ArrayList<>();



        // We are generating key-value pair for #IFrameBuilder Class
        // So we are run loop by longest array
        // We are shorting our code by using expression
        int totalCount = (keys.length > values.length) ? keys.length : ((keys.length == values.length ? keys : values).length);

     
       // run a basic check again
        boolean isKeyBIG = totalCount == keys.length;

        if (isKeyBIG){

            for (int i = 0; i < totalCount; i++) {

                HashMap<String, String> map = new HashMap<>();
                map.put(keys[i], values[i]);

                buildIFrame.add(map);

            }
        }else {


            // We found unnecessary value
            throw new IFrameBuildException("There is likely the value is present that longer then key, please check your input");

        }



        // build final IFrame
        IFrameBuilder iFrameBuilder = new IFrameBuilder();
        iFrameBuilder.putKeyValuePair(buildIFrame);
        return iFrameBuilder.getCompiledIFrame();




}




    @NonNull
    @Override
    public String toString() {

      return dataHolder.getCompiledHTML();
    }


    // build the Utility object
    public NJPollobIFrameUtility buildIFrame(){


        return new NJPollobIFrameUtility(dataHolder);
    }



}



