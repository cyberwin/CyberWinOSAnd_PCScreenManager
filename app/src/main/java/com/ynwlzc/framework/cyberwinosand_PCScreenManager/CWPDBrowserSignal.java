package com.ynwlzc.framework.cyberwinosand_PCScreenManager;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Toast;

import CyberWinPHP.Cyber_CPU.Cyber_Public_Var;

import CyberWinPHP.Cyber_Plus.Cyber_JsPrinterStandard;


public class CWPDBrowserSignal  extends Activity {
    private WebView cwpd_Web;

    final Context cyber_instance = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
        DisplayMetrics dm = getResources().getDisplayMetrics();


        //蓝牙初始化

       // Cyber_Public_Var.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        _init_window();
        setContentView(R.layout.cwpdbrowsersignal);
        _cyber_window_init_size();
        initView();
        initWebView_Plugs();



        String cyber_param_weburl = getIntent().getStringExtra("cyber_param_weburl").toString();
        String cyber_param_webtitle = getIntent().getStringExtra("cyber_param_webtitle").toString();

        try {
            int cyber_param_scrren_orientation = Integer.parseInt(getIntent().getStringExtra("cyber_param_scrren_orientation"));
            switch (cyber_param_scrren_orientation) {
                case 7801: {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                }
                break;
                case 7804: {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                break;
                case 7805: {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                break;
                default: {
                }
                break;
            }
        }catch (Exception ex){

        }



        cyber_loadweb(cyber_param_weburl);
        getWindow().setTitle(cyber_param_webtitle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setIcon(R.drawable.ic_launcher_round);
        }
        }catch (Exception ex){
            showToast("新窗口："+ex.getMessage());
        }
    }

    private  void _cyber_window_init_size(){
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int   cwpd_h = dm.heightPixels;
        int  cwpd_w = dm.widthPixels;

        android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
        p.height = (int) (cwpd_h * 0.96); // 高度设置为屏幕的0.3
        p.width = (int) (cwpd_w * 0.96); // 宽度设置为屏幕的0.7
        getWindow().setAttributes(p);


    }

    private void _init_window(){
        //无title
     //   requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
    //    getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
     //           WindowManager.LayoutParams. FLAG_FULLSCREEN);
    }
    public static void showToast(String str) {
        //2022-7-27 优化子线程UI
        //Looper.prepare();
        Toast.makeText(Cyber_Public_Var.cyber_main_instance, str, Toast.LENGTH_SHORT).show();
        //Looper.loop();// 进入loop中的循环，查看消息队列
    }
    /**
     * 初始化View
     */
    private void initView() {

        cwpd_Web = (WebView) findViewById(R.id.cwpd_webview_cwpdbrowsersignal);

    }

    private void initWebView_Plugs() {
        // 修改ua使得web端正确判断
        String ua = cwpd_Web.getSettings().getUserAgentString();
        cwpd_Web.getSettings().setUserAgentString(ua+";CyberWin WebBrowser/Android3.2017");

     //   cwpd_Web.addJavascriptInterface(new Cyber_JsPrinterStandard(this), "Cyber_JsPrinterStandard"); //在JSHook类里实现javascript想调用的方法，并将其实例化传入webview,
      //  cwpd_Web.addJavascriptInterface(new Cyber_JsPrinterStandard(this), "CyberWin_JsStandardPlug"); //在JSHook类里实现javascript想调用的方法，并将其实例化传入webview,



        cwpd_Web.getSettings().setDomStorageEnabled(true);
        cwpd_Web.getSettings().setDatabaseEnabled(true);
        //cwpd_Web.getSettings().set(true);



    }
    /**
     * 初始化WebView
     */
    private void cyber_loadweb(String url ) {




        cwpd_Web.getSettings().setJavaScriptEnabled(true);
        //禁止图片
        //cwpd_Web.getSettings().setBlockNetworkImage(true);
        cwpd_Web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        cwpd_Web.getSettings().setSupportMultipleWindows(true);



            cwpd_Web.loadUrl(url);



        cwpd_Web.setWebViewClient(new CyberWinPHP.Cyber_Core_Web.Cyber_WebviewClient());
        cwpd_Web.setWebChromeClient(new CyberWinPHP.Cyber_Core_Web.Cyber_ChromeClient());
    }
}
