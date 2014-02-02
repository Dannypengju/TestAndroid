package com.example.testandroid;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

public class TestPupwindow extends Activity{

	private PopupWindow popupWindow;  
	private LayoutInflater mInflater;
	private Button btn;
    private View mMainContain;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_pup_activity);
		mInflater = LayoutInflater.from(this);
		btn = (Button)findViewById(R.id.btn_wowow);
        mMainContain = findViewById(R.id.main_contain);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showPupwindow();
			}
		});
	}
	private void showPupwindow(){
		View view = (LinearLayout) mInflater.inflate(R.layout.item_popup, null);  
        if (popupWindow == null) { 
        	
            popupWindow = new PopupWindow(this);  
            popupWindow.setBackgroundDrawable(new BitmapDrawable());  
//          popupWindow.setFocusable(true); // 设置PopupWindow可获得焦点  
            popupWindow.setTouchable(true); // 设置PopupWindow可触摸  
            popupWindow.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸  
            popupWindow.setContentView(view);  
            popupWindow.setWidth(LayoutParams.MATCH_PARENT);  
            popupWindow.setHeight(LayoutParams.WRAP_CONTENT); 
//            popupWindow.setAnimationStyle(R.style.popuStyle);   //设置 popupWindow 动画样式
        }  
        popupWindow.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
			}
		});
        
        popupWindow.showAtLocation(mMainContain, Gravity.BOTTOM, 0, 0);
        popupWindow.update();  
	}
}
