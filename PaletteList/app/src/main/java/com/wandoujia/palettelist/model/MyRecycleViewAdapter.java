package com.wandoujia.palettelist.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.wandoujia.palettelist.R;

/**
 * Created by GuoBin on 2017/4/16.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
    //数据
    private String[] s;
    //上下文
    private Context context;
    //布局加载器
    private LayoutInflater layoutInflater;



    /**
     *
     * @param s 数组
     * @param mContext 上下文
     */
    public MyRecycleViewAdapter(String[] s, Context mContext) {
        this.s =s ;
        this.context = mContext;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    /**
     * 创建视图
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder=new ViewHolder(layoutInflater.from(context).inflate(R.layout.list_item,parent,false));
        return viewHolder;
    }

    /**
     * 将数据与item视图进行绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MyRecycleViewAdapter.ViewHolder holder, int position) {
holder.tv.setText("我是文本"+position);
        ImageLoader.getInstance().displayImage(s[position], holder.imageView, new ImageLoadingListener() {
         //加载开始
            @Override
            public void onLoadingStarted(String s, View view) {

            }
//加载失败
            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }
//加载完成
            @Override
            public void onLoadingComplete( String s, View view, Bitmap bitmap) {

holder.imageView.setImageBitmap(bitmap);
                if (bitmap!=null){
                    Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            //以获取明亮的色调为例
                            Palette.Swatch vibrant =
                                    palette.getVibrantSwatch();
                            if (vibrant != null) {
                                // If we have a vibrant color
                                // update the title TextView
                                holder.tv.setBackgroundColor(
                                        vibrant.getRgb());
                                holder.tv.setTextColor(
                                        vibrant.getTitleTextColor());
                            }

                        }
                    });
                }
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });


    }

    /**
     *
     * @return 集合大小
     */
    @Override
    public int getItemCount() {
        return s.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            //找到控件
            imageView= (ImageView) itemView.findViewById(R.id.iv);
            tv= (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
