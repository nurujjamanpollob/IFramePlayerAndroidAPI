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

import androidx.annotation.NonNull;

@SuppressWarnings({"UnusedDeclaration"})
public class DataHolder {

    String HTML_START = "<html>\n<head>\n<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n\n";
    private String HTML_ADD_METADATA_FOR_HTML = ""+"\n\n";
    String HTML_HEADPART_TITLE_START = "<title>";
    private  String HTML_TITLE_TEXT = "IFramePlayerAndroidAPI - Developed by Nurujjaman Pollob";
    String HTML_HEADPART_TITLE_END = "</title>";
    public static String HTML_STYLE_INCLUSION = "\n";
    private String HTML_JAVASCRIPT_INCLUSION = "\n";
    String HTML_ADD_CUSTOM_CSS_RULE_START = "\n<style type=\"text/css\">\n";
    private String HTML_ADD_CUSTOM_CSS_RULE_ADD_TEXT_HERE = "";
    String HTML_ADD_CUSTOM_CSS_RULE_END = "\n</style>\n";
    String HTML_HEAD_END = "\n</head>\n";
    String HTML_BODY_START = "\n<body>";

    private static String HTML_INCLUDE_IFRAME = "\n\n";
    private String HTML_INCLUDE_EXTERNAL_JAVASCRIPTCODE = "";
    String HTML_BODY_END = "\n</body>\n\n";
    String HTML_INCLUDE_HTML_END = "\n\n\n</html>\n";


     static volatile String GENERATED_HTML_FROM_INPUT;




    public void setHTMLTitle(String title){

        this.HTML_TITLE_TEXT = title;
    }

    public String getHTMLTitle(){

        return HTML_TITLE_TEXT;

    }

    public String getCompiledHTML(){

        //Generate the HTML for input
        GENERATED_HTML_FROM_INPUT = HTML_START
                + HTML_ADD_METADATA_FOR_HTML
                + HTML_HEADPART_TITLE_START
                + HTML_TITLE_TEXT
                + HTML_HEADPART_TITLE_END
                + HTML_STYLE_INCLUSION
                + HTML_JAVASCRIPT_INCLUSION
                + HTML_ADD_CUSTOM_CSS_RULE_START
                + HTML_ADD_CUSTOM_CSS_RULE_ADD_TEXT_HERE
                + HTML_ADD_CUSTOM_CSS_RULE_END
                + HTML_HEAD_END
                + HTML_BODY_START
                + HTML_INCLUDE_IFRAME
                + HTML_INCLUDE_EXTERNAL_JAVASCRIPTCODE
                + HTML_BODY_END
                + HTML_INCLUDE_HTML_END;


        // Send back compiled HTML
     return GENERATED_HTML_FROM_INPUT;
    }



    // ADD JavaScript by URLS
    public void includeJavaScriptByURLs(@NonNull String[] javascriptURLS, @NonNull Boolean isUseLibraryGeneratedInclusionStyle){

        // We found that the programmer is happy with our library driven result
        if (isUseLibraryGeneratedInclusionStyle){

            // making a simply designed script include
            String scriptIncludeStyle = "<script src=\"",scriptENDTAG = "\"></script>\n\n";

            // get length of array length and store in variable
            int totalJavaScript = javascriptURLS.length;

            // run a loop and include all script by default style fashion
            for (String javascriptURL : javascriptURLS) {

                String finalURL = scriptIncludeStyle + javascriptURL + scriptENDTAG;

                // we are formatting our String to right format for use adding this also :)
                HTML_JAVASCRIPT_INCLUSION = String.format("%s%s", HTML_JAVASCRIPT_INCLUSION, finalURL);

            }


        }

        // Programmer is needed something then our predefined solution, so he went manually inject Javascript source
        // So we will simply run a loop and add them to HTML
        else {

            // RUN the loop

            for (String javaScriptSources: javascriptURLS) {

                // we are formatting our String to right format for use, adding this also :)
                HTML_JAVASCRIPT_INCLUSION = String.format("%s%s", HTML_JAVASCRIPT_INCLUSION, javaScriptSources);

            }
        }

    }


    // Add custom css to your IframePlayer in order to customize player view according to your needs.

    public void includeCSSByURLS(@NonNull String[] stylesheets, @NonNull Boolean isUseLibraryGeneratedInclusionStyle){


        // Well, the developer will use library generated inclusion style
        if(isUseLibraryGeneratedInclusionStyle){

            // generate inclusion style
            String inclusionStart = "<link rel=\"stylesheet\" href=\"",inclusionEND = "\">\n\n";

            for (String cssURL: stylesheets) {

                // simply add to html with inclusion styling



                String generatedStyle = inclusionStart+cssURL+inclusionEND;

                // format and update HTML
                HTML_STYLE_INCLUSION = String.format("%s%s", HTML_STYLE_INCLUSION, generatedStyle);

            }

        }

        // the programmer requirement is other, so we will simply run a loop and add to HTML
        else {

            //run the loop
            for(String cssLIST : stylesheets){



                // format and update HTML
                HTML_STYLE_INCLUSION = String.format("%s%s", HTML_STYLE_INCLUSION, cssLIST);
            }
        }
    }


    public void includeIframe(String iframeCode){

        HTML_INCLUDE_IFRAME += iframeCode+"\n\n";
    }



    public void addJavaScriptCode(String jsCodeToInjectInHTML){

        HTML_INCLUDE_EXTERNAL_JAVASCRIPTCODE += jsCodeToInjectInHTML;

    }


    public void addCSSRulesToHTML(String cssStyles){

        HTML_ADD_CUSTOM_CSS_RULE_ADD_TEXT_HERE += cssStyles;

    }


    public void addMetaData(String[] metadataWithALLPropertyAndValues){

        for (String s : metadataWithALLPropertyAndValues) {

            HTML_ADD_METADATA_FOR_HTML = String.format("%s%s", HTML_ADD_METADATA_FOR_HTML, s+"\n\n");

        }


    }



}
