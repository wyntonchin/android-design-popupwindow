package com.example.popupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String[] names = new String[]{"鼠", "牛", "虎", "兔", "龙"};
    private int[] imag = new int[]{R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    PopupWindow popup;
    SimpleAdapter sAdapter;
    ListView list_pop;
    ArrayList list;
    ImageView btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.img);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!popup.isShowing()){
                showPopupWindows();
                //}
            }
        });
    }

    void showPopupWindows() {
        // 装载R.layout.popup对应的界面布局
        View contentView = this.getLayoutInflater().inflate(R.layout.list_popup_more_menu, null);

        list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("names", names[i]);
            map.put("imag", imag[i]);
            list.add(map);
        }
        sAdapter = new SimpleAdapter(this, list,
                R.layout.item_pop_more_menu, new String[]{"names", "imag"},
                new int[]{R.id.tv_item, R.id.iv_item});
        list_pop = contentView.findViewById(R.id.list_pop);
        //从不同于主xml(setContentView(R.layout.main);)中使用其他xml布局中的控件时，需声明其所在的VIew布局。
        list_pop.setAdapter(sAdapter);

        list_pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.i("TAG", "你点击了：" + names[position]);
            }
        });
        // 创建PopupWindow对象
        popup = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //popup = new PopupWindow(contentView);
        //popup.setContentView();

/*        DisplayMetrics metrics = new DisplayMetrics();
        final int sWidth = metrics.widthPixels;
        final int sHeight = metrics.heightPixels;*/
        //popup.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        popup.setOutsideTouchable(true);
/*        popup.showAtLocation(btn, Gravity.TOP|Gravity.RIGHT
                , sWidth, btn.getHeight());*/
        popup.showAsDropDown(btn);
        //以某个控件的x和y的偏移量位置开始显示窗口
        //popup.showAsDropDown(btn, 0, 0);
        //如果窗口存在，则更新
        //popup.update();
    }
}
