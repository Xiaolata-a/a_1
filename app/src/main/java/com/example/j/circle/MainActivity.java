package com.example.j.circle;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radio_group;
    private ViewPager view_pager;
    private List<Integer> list;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                //1.获取当前页的下标
                int i = view_pager.getCurrentItem();
                //2.当前页下标++
                i++;
                //3.设置显示的页
                view_pager.setCurrentItem(i);
                //4.重新发送消息
                handler.sendEmptyMessageDelayed(0, 1500);
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        //1.把图片存放到集合中
        list = new ArrayList<Integer>();
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.bb);
        list.add(R.mipmap.ee);
        //2.创建适配器
        MyPagerAdapter adapter = new MyPagerAdapter(list,MainActivity.this,handler);
        //3.设置适配器
        view_pager.setAdapter(adapter);
        //4.可以设置当前显示的页
        view_pager.setCurrentItem(list.size() * 1000);
        //5.切换页面,,,改变小圆点的位置
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radio_group.check(radio_group.getChildAt(i % list.size()).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //自动无限轮播
        handler.sendEmptyMessageDelayed(0, 1500);
    }
}

