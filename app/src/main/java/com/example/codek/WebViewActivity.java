package com.example.codek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {
WebView webView;
    ProgressBar progressBar;
    Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView=findViewById(R.id.webview);
        progressBar=findViewById(R.id.progresss);
        it=getIntent();
        Bundle b=it.getExtras();
        if(b!=null) {
            String url = b.getString("name");
            if(url.equals("programiz"))
                url="https://www.programiz.com/cpp-programming/online-compiler/";
            else if(url.equals("codechef"))
                url="https://www.codechef.com/ide";
             else if(url.equals("onlingedgb"))
                url="https://www.onlinegdb.com/online_c++_compiler";
             else if(url.equals("joodle"))
                url="https://www.jdoodle.com/online-compiler-c++/";
             else if(url.equals("onecompiler"))
                url="https://onecompiler.com/cpp";
            else if(url.equals("w3"))
                url="https://www.w3schools.com/tryit/";
            else if(url.equals("interview"))
                url="https://www.interviewbit.com/online-cpp-compiler/";
            else if(url.equals("tutorial"))
                url="https://www.tutorialspoint.com/compile_cpp11_online.php";
            else if(url.equals("gfg"))
                url="https://ide.geeksforgeeks.org/";
            if(!url.isEmpty()){
               // Toast.makeText(this,url,Toast.LENGTH_SHORT).show();
                webView.loadUrl(url);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());

            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i=new Intent(WebViewActivity.this,
                            MainActivity.class);
                    progressBar.setVisibility(View.INVISIBLE);
                    webView.setVisibility(View.VISIBLE);
                }
            }, 2000);
        }
    }
}