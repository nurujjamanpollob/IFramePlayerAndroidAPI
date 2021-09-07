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

import org.junit.Test;

import dev.nurujjamanpollob.iframeplayer.backgroundhandler.IFrameBuildException;
import dev.nurujjamanpollob.iframeplayer.backgroundhandler.NJPollobIFrameUtility;

import static org.junit.Assert.assertEquals;


public class NJPollobIFrameUtilityTest {

    @Test
    public void iframeBuilderIsgivingRightOutput() throws IFrameBuildException {

        NJPollobIFrameUtility utility = new NJPollobIFrameUtility();
        // Write expected result.
      String expected = "<iframe width=\"100%\" height=\"200\" src=\"https://www.youtube.com/embed/nCgQDjiotG0\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen=\"true\"></iframe>";

        String[] keys = {"width", "height", "src", "title", "frameborder", "allow", "allowfullscreen"};
        String[] values = {"100%", "200", "https://www.youtube.com/embed/nCgQDjiotG0", "YouTube video player", "0", "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture", "true" };
        assertEquals(expected, utility.buidIframeByKeyValue(keys, values));
    }
}
