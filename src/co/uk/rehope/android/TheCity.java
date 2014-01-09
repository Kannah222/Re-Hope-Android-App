package co.uk.rehope.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TheCity extends Activity {
    /**
     * Called when the activity is first created.
     */
    public ProgressDialog pd;
    private rehope ParentActivity;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.thecity);
        setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        Thread t = new Thread() {
            public void run() {
                loadTheCity();
            }
        };
        t.start();
    }

    private void loadTheCity() {
    	 WebView wv = (WebView) findViewById(R.id.thecity_webview);
         wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
         wv.getSettings().setBuiltInZoomControls(true);
         wv.getSettings().setJavaScriptEnabled(true);
         String theCityURL;

         wv.setWebViewClient(new ReHopeWebViewClient());

         try {
         	theCityURL = ParentActivity.cityURL;
         } catch (java.lang.NullPointerException n) {
             theCityURL = "";
         }
         
         if (theCityURL.equals(""))
     		theCityURL = "https://m.facebook.com/#!/groups/118283294939251";
         
         wv.loadUrl(theCityURL);
    }

    private class ReHopeWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.clearCache(true);
        }


    }

}
