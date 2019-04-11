package com.example.j.circle;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MyPagerAdapter extends PagerAdapter {
    private List<Integer> list;
    private Context context;
    private Handler handler;

    public MyPagerAdapter(List<Integer> list, Context context, Handler handler) {
        this.list = list;
        this.context = context;
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(list.get(position % list.size()));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView);
        //解决滑动冲突
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下--停止
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        //抬起--轮播
                        handler.sendEmptyMessageDelayed(0, 1500);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        //取消--轮播
                        handler.sendEmptyMessageDelayed(0, 1500);
                        break;

                    default:
                        break;
                }
                return true;
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
