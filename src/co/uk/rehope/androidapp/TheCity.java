package co.uk.rehope.androidapp;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TheCity extends Activity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.thecity);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadTheCity();
    }

    private void loadTheCity() {
    	 WebView wv = (WebView) findViewById(R.id.thecity_webview);
         wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
         wv.getSettings().setBuiltInZoomControls(true);
         wv.getSettings().setJavaScriptEnabled(true);

         wv.setWebViewClient(new ReHopeWebViewClient());
         wv.loadUrl("https://m.facebook.com/#!/groups/118283294939251");
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
