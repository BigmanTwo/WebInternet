package com.example.asus.webinternet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private WebView mWebView;
    private ProgressBar mProgress;
    private Button mButton1,mButton2,mButton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton1=(Button)findViewById(R.id.load_urls);
        mButton2=(Button)findViewById(R.id.insert_jsp);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mWebView=(WebView)findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new MyJs(),"my");
        //必须加载setWebChromeClient,否则无法打开myAlert
        mWebView.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.load_urls:
                mWebView.loadUrl("file:///android_asset/index.html");
                break;
            case R.id.insert_jsp:
                mWebView.loadUrl("javascript:myAlert()");
                break;
        }
    }

    class MyJs{
        //通过html中button的onclick的属性调用到toast方法
        @JavascriptInterface
        public void toast(String str){
            Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
        }
    }
}
