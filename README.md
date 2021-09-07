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
	        implementation 'com.github.nurujjmanpollob:IFramePlayerAndroidAPI:1.1'
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


Anyway, You can direct using a key value pair to build Iframe programmatically. You can refer to this unit test class: <a href="https://github.com/nurujjamanpollob/IFramePlayerAndroidAPI/blob/master/IframePlayer/src/test/java/dev/nurujjamanpollob/iframeplayer/NJPollobIFrameUtilityTest.java"> NJPollobIFrameUtilityTest.Java</a> to understand and run test according to your needs, I will add more unit test when I have free times.

<b> How to programmatically build IFrame? </b>

<br />
<br />

Look at this implementation:

<pre>
<code>


	DataHolder dataholder = new DataHolder();

        NJPollobIFrameUtility utility = new NJPollobIFrameUtility(dataholder);
  
        // keys in String[] Array
        String[] keys = {"width", "height", "src", "title", "frameborder", "allow", "allowfullscreen"};

        // values followed by keys in Array
        String[] values = {"100%", "200", "https://www.youtube.com/embed/nCgQDjiotG0", "YouTube video player", "0", "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture", "true" };


      dataholder.includeIframe(utility.buidIframeByKeyValue(keys, values));

       // Then load it like:
      NJPollobIframePlayer iframe = findViewById(R.id.iframe_player);
      iframe.loadIFrameByIFrameUtility(utility.buildIFrame());


	
</code>
</pre>



        

<br />
<br />



<h2> Newly Added: <a href="https://github.com/nurujjamanpollob/IFramePlayerAndroidAPI/blob/1.1/IframePlayer/src/main/java/dev/nurujjamanpollob/iframeplayer/NJPollobYTPlayer.java"> NJPollobYTPlayer.Java </a></h2>


<br/>

<p> This class is straightforward, Enable you to play YouTube Video, by simply add video token or video page url in xml or in Java Code progrmmatically like this:


<br />

<h5> For XML, you can show a YouTube Video Like this: </h5>

<br />

<b> With Video Page URL </b>


 
 	<dev.nurujjamanpollob.iframeplayer.NJPollobYTPlayer
   
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:youtube_video_url="https://www.youtube.com/watch?v=0eKVizvYSUQ" />


<br />

<b> With Video Token </b>

<br />


        <dev.nurujjamanpollob.iframeplayer.NJPollobYTPlayer
            android:layout_width="match_parent"
            app:youtube_video_token="nCgQDjiotG0"
            android:layout_height="match_parent" />





<br />

<br />

<h5>You can also do this programmatically, put these values by calling:</h5>

<pre>
<code>
NJPollobYTPlayer ytPlayer = findViewById(view id here);

ytPlayer.putYouTubeVideoToken(Video token here);
</code>

</pre>

<br />

<b> Or </b>

<pre> 

<code>

NJPollobYTPlayer ytPlayer = findViewById(view id here);
ytPlayer.putYouTubeVideoURL(Video token here);



</code>

</pre>



<br /> <br />

This class  <a href="https://github.com/nurujjamanpollob/IFramePlayerAndroidAPI/blob/1.1/IframePlayer/src/main/java/dev/nurujjamanpollob/iframeplayer/NJPollobYTPlayer.java">NJPollobYTPlayer.Java </a> Offers support return it's main View and ViewGroup so you can Work with Views easily.


Applying animation, resizing ViewPort, access or re-render View is just simplified. You can call #getView(); to get parent View and getViewGroup(); to get Created ViewGroup programmatically.


<h5> Hey congratulation! You have successfully implemented IFramePlayerAndroidAPI in your project </h5>

<br />


<h6> Added New Module - DraggableView </h6>

I am soon gonna share documentation for this, it will be like PIP in Android OREO but covering Lower SDK.

I am having tight timeline, hope soon gonna do something with this.

<h2> Feel to share bug, contribution is always welcome :) </h2>




<b> Thanks :) </b>



<h3>What will be added in future?</h3>

<ul>
	<li>Add PIP Support - Current Support featuring WebView Support, I went to make it native and cover lower SDK's</li> 
	<li> Facebook IFrame Building by simply put video page URL like youtube sample. </li>
	<li> Vimeo Iframe builder by simply put video page URL </li>
	<li> Direct Video iframed by simply put URL, then The Library generates necessary codes </li>
	<li> More features. </li>
	
</ul>


<br />


<pre>
<code>

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

</code>
</pre>
