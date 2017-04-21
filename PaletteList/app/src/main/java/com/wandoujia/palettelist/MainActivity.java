package com.wandoujia.palettelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wandoujia.palettelist.model.ImageUtils;
import com.wandoujia.palettelist.model.MyRecycleViewAdapter;

public class MainActivity extends AppCompatActivity {
    //数据
    private String[] s;
    //RecycleView
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        //初始化数据
       s= ImageUtils.s;
        //瀑布流布局管理器

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        //适配器
        recyclerView.setAdapter(new MyRecycleViewAdapter(s, this));

    }


}
