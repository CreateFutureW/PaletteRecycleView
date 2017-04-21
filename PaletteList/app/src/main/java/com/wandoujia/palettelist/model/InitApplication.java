package com.wandoujia.palettelist.model;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.wandoujia.palettelist.R;


/**
 * Created by GuoBin on 2017/4/16.
 */

public class InitApplication extends Application {
    /**
     * 进行ImageLoad相关的初始化不然会报错
     * java.lang.IllegalStateException:
     * ImageLoader must be init with configuration before using
     * 字面意思是在使用前要初始化
     */
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化，不然会报错     .displayer(new CircleBitmapDisplayer）圆形图片

        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
              .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .displayer((new RoundedBitmapDisplayer(10)))//是否设置为圆角，弧度为多少
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
              .defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
.diskCacheSize(2*1024*1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .threadPoolSize(2)
                .writeDebugLogs()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);

    }
}
