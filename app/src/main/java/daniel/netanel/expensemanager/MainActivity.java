package daniel.netanel.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * MainActivity Class
 */
public class MainActivity extends AppCompatActivity {

    /**
     * onCreate method
     *
     * @param savedInstanceState
     */
    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TransactionModel model = null;
        try {
            model = new TransactionModel(this);
        } catch (TransactionException e) {
            e.printStackTrace();
        }
        TransactionViewModel viewModel = new TransactionViewModel();
        WebView webView = new WebView(this);
        try {
            viewModel.setTxModel(model);
        } catch (TransactionException e) {
            e.printStackTrace();
        }
        try {
            viewModel.setView(webView);
        } catch (TransactionException e) {
            e.printStackTrace();
        }
        webView.loadUrl("file:///android_asset/home.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(viewModel, "vm");
        setContentView(webView);

    }
}