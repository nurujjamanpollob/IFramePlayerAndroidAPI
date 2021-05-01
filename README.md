# IFramePlayerAndroidAPI - A Android API to play HTML IFrame


<h3> The documentation is under development, I will soon update it. </h3>

<br /> <br />

<h4> Basic usages </h4>

<br />
<br />

<h4> Add this code to your project level build.gradle file </h4>

<br />

<pre>
<code>

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	</code>

	</pre>

<br />

<h4> Then in your app level build.gradle file add this line: </h4>

<br />

<pre>

<code>

dependencies {
	        implementation 'com.github.nurujjmanpollob:IFramePlayerAndroidAPI:1.0'
	}

</code>

</pre>

<br />
<br />

<b> add as usual xml layout like this </b>


   
 <dev.nurujjamanpollob.iframeplayer.NJPollobIframePlayer
   android:id="@+id/iframe_player"
   android:layout_width="match_parent"
   android:layout_height="250dp" />



<br />
<br />

<b> Then initialize the view like this: </b>

<br />

<pre>
<code>

  NJPollobIframePlayer iframe = findViewById(R.id.iframe_player);
  DataHolder dataholder = new DataHolder();
  dataholder.includeIframe("<iframe width=\"100%\" height=\"200\" src=\"https://www.youtube.com/embed/nCgQDjiotG0\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen=\"true\"></iframe>");
  iframe.loadIFrameByIFrameUtility( new NJPollobIFrameUtility(dataholder).buildIFrame());

 </code>

</pre>


Anyway, You can direct using a key value pair to build Iframe programmatially. You are refer to this unit test class: <a href="https://github.com/nurujjamanpollob/IFramePlayerAndroidAPI/blob/master/IframePlayer/src/test/java/dev/nurujjamanpollob/iframeplayer/NJPollobIFrameUtilityTest.java"> NJPollobIFrameUtilityTest.Java</a> to understand and run test according to your needs, I will add more unit test when I have free times.

<b> How to programmatically build IFrame? </b>

<br />

Look at this implementation:

<pre>
<code>
        NJPollobIFrameUtility utility = new NJPollobIFrameUtility();
  
  // keys in String[] Array
        String[] keys = {"width", "height", "src", "title", "frameborder", "allow", "allowfullscreen"};

 // values followed by keys in Array
        String[] values = {"100%", "200", "https://www.youtube.com/embed/nCgQDjiotG0", "YouTube video player", "0", "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture", "true" };
	
 // Then load it like:
	NJPollobIframePlayer iframe = findViewById(R.id.iframe_player);
        iframe.loadIFrameByIFrameUtility( utility.buidIframeByKeyValue(keys, values));
	
	
	
</code>
</pre>
        

<br />
<br />

<h5> Hey congratulation! You have successfully implemented IFramePlayerAndroidAPI in your project </h5>

<br />

<h2> Feel to share bug, contribution is always welcome :) </h2>




<b> Thanks :) </b>

<br />
<br />

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

