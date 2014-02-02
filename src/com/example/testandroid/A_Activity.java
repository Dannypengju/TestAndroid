package com.example.testandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import com.example.testandroid.sliding.SlidingMenu;
import com.example.testandroid.testWeixin.TestWeiXin;
import com.example.testandroid.xia.datetime.wheelview.WheelView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class A_Activity extends Fragment {

    private MainActivity mMain;
    private Button mShowWin;
    private Button mButtonAction;
    private WheelView mWheelView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mMain = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        if (container == null) {
            return null;
        }
        View view_a = inflater.inflate(R.layout.fragement_a, null);
        TextView tv = (TextView) view_a.findViewById(R.id.sb);
        String time = "1381567908757";
        long timg = Long.parseLong(time);

        tv.setText(parseLongToTime(timg));
        Button btn = (Button) view_a.findViewById(R.id.btn_show);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                SlidingMenu sm = mMain.getSlidingMenu();
                sm.showMenu();
            }
        });
        Button find = (Button) view_a.findViewById(R.id.btn_find);
        find.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(A_Activity.this.getActivity(), TestWeiXin.class);
                getActivity().startActivity(intent);
            }
        });

        mShowWin = (Button) view_a.findViewById(R.id.btn_show_pup_window);
        mShowWin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                interapt = false;
            }
        });
        mButtonAction = (Button)view_a.findViewById(R.id.repeat_action);
        mButtonAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                setTvAnimation();
            }
        });
//        mWheelView = (WheelView)view_a.findViewById(R.id.my_wheel);
//        String[] nimei = new String[]{"你妹啊","你妹妹啊","尼玛啊", "不想桑班～～", "不想写代码了", "就想睡觉!!!", "睡觉！！！！！", "我要睡觉！！！"};
//        ArrayWheelAdapter<String> wheelAdapter = new ArrayWheelAdapter<String>(nimei, nimei.length);
//        mWheelView.setAdapter(wheelAdapter);
//        mWheelView.setCyclic(false);
        mWheelView.setCurrentItem(1);
        mWheelView.TEXT_SIZE =  adjustFontSize(mMain.getWindow().getWindowManager());

        return view_a;
    }

    public static int adjustFontSize(WindowManager windowmanager) {

        int screenWidth = windowmanager.getDefaultDisplay().getWidth();
        int screenHeight = windowmanager.getDefaultDisplay().getHeight();
	     /*  DisplayMetrics dm = new DisplayMetrics();
	      dm = windowmanager.getApplicationContext().getResources().getDisplayMetrics();
	     int widthPixels = dm.widthPixels;
	     int heightPixels = dm.heightPixels;
	     float density = dm.density;
	     fullScreenWidth = (int)(widthPixels * density);
	     fullScreenHeight = (int)(heightPixels * density);*/
        if (screenWidth <= 240) { // 240X320 屏幕
            return 10;
        } else if (screenWidth <= 320) { // 320X480 屏幕
            return 14;
        } else if (screenWidth <= 480) { // 480X800 或 480X854 屏幕
            return 24;
        } else if (screenWidth <= 540) { // 540X960 屏幕
            return 26;
        } else if (screenWidth <= 800) { // 800X1280 屏幕
            return 30;
        } else { // 大于 800X1280
            return 30;
        }
    }

    boolean interapt = false;
    Animation singleShake;
    Animation visiable;
    Animation invisiable;
    Animation zoom;


    int mStatue = 0;


    public static String parseLongToTime(long longTime) {
//		long time1970 = new Date(0).getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		/*TimeZone timeZone = TimeZone.getDefault();
		int i = timeZone.getRawOffset();*/
        Date date = new Date(longTime);
        return format.format(date);
    }

}
