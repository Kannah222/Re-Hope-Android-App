package co.uk.rehope.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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
        ParentActivity = (rehope) TheCity.this.getParent();

		/*
		 * wv.setWebChromeClient(new WebChromeClient() { public void
		 * onProgressChanged(WebView view, int progress) {
		 * rehope.this.setTitle("Loading..."); setProgress(progress * 100);
		 * 
		 * if(progress == 100){ setTitle("The City");
		 * 
		 * } } });
		 */
		
		/* try to open the facebook page via the fb app */
        try {
            this.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            ParentActivity.launchActivity((new Intent(Intent.ACTION_VIEW, Uri.parse("fb://group/118283294939251"))));
        }
		/* if the fb app isn't installed, open a web-view instead*/ catch (Exception e) {
            loadWebView();
        }


    }

    /* loads a webview for theCityURL */
    private void loadWebView() {
        WebView wv = (WebView) findViewById(R.id.thecity_webview);
        wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setJavaScriptEnabled(true);
        String theCityURL = ParentActivity.cityURL;

        wv.setWebViewClient(new ReHopeWebViewClient());

        if (theCityURL != "") {
            wv.loadUrl(theCityURL);
            theCityURL = "";
        } else {
            wv.loadUrl("https://m.facebook.com/#!/groups/118283294939251");
        }
    }


    private class ReHopeWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // setProgressBarIndeterminateVisibility(false);
            view.clearCache(true);
        }
		/*
		 * public void onReceivedSslError(WebView view, SslErrorHandler handler,
		 * SslError error){ handler.proceed(); }
		 */

    }

}
