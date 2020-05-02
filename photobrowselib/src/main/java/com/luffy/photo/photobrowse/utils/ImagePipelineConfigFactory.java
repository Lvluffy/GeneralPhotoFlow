package com.luffy.photo.photobrowse.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name 图像管道-配置工厂
 * @desc
 */
public class ImagePipelineConfigFactory {

    private static final String IMAGE_PIPELINE_CACHE_DIR = "pipe_cache";

    private static final String IMAGE_PIPELINE_SMALL_CACHE_DIR = "pipe_small_cache";

    private static final int MAX_DISK_SMALL_CACHE_SIZE = 10 * ByteConstants.MB;

    private static final int MAX_DISK_SMALL_ONLOWDISKSPACE_CACHE_SIZE = 5 * ByteConstants.MB;

    /**
     * Creates config using android http stack as network backend.
     */
    public static ImagePipelineConfig getImagePipelineConfig(Context mContext) {
        Context context = mContext.getApplicationContext();
        return createConfigBuilder(context)
                .setBitmapsConfig(Bitmap.Config.ARGB_8888) // 若不是要求忒高清显示应用，就用使用RGB_565吧（默认是ARGB_8888)
                .setDownsampleEnabled(true) // 在解码时改变图片的大小，支持PNG、JPG以及WEBP格式的图片，与ResizeOptions配合使用
                .setRequestListeners(getRequestListeners())
                .setMemoryTrimmableRegistry(getMemoryTrimmableRegistry()) // 报内存警告时的监听
                .setBitmapMemoryCacheParamsSupplier(new MemoryCacheSupplier((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE))) // 设置内存配置
                .setMainDiskCacheConfig(getMainDiskCacheConfig(context)) // 设置主磁盘配置
                .setSmallImageDiskCacheConfig(getSmallDiskCacheConfig(context)) // 设置小图的磁盘配置
                .build();
    }

    /**
     * Create ImagePipelineConfig Builder
     */
    private static ImagePipelineConfig.Builder createConfigBuilder(Context context) {
        return ImagePipelineConfig.newBuilder(context);
    }

    /**
     * 当内存紧张时采取的措施
     */
    private static MemoryTrimmableRegistry getMemoryTrimmableRegistry() {
        MemoryTrimmableRegistry memoryTrimmableRegistry = NoOpMemoryTrimmableRegistry.getInstance();
        memoryTrimmableRegistry.registerMemoryTrimmable(new MemoryTrimmable() {
            @Override
            public void trim(MemoryTrimType trimType) {
                final double suggestedTrimRatio = trimType.getSuggestedTrimRatio();
                if (MemoryTrimType.OnCloseToDalvikHeapLimit.getSuggestedTrimRatio() == suggestedTrimRatio
                        || MemoryTrimType.OnSystemLowMemoryWhileAppInBackground.getSuggestedTrimRatio() == suggestedTrimRatio
                        || MemoryTrimType.OnSystemLowMemoryWhileAppInForeground.getSuggestedTrimRatio() == suggestedTrimRatio
                        ) {
                    // 清除内存缓存
                    Fresco.getImagePipeline().clearMemoryCaches();
                }
            }
        });
        return memoryTrimmableRegistry;
    }

    /**
     * 设置网络请求监听
     */
    private static Set<RequestListener> getRequestListeners() {
        return new HashSet<>();
    }

    /**
     * 获取主磁盘配置
     */
    private static DiskCacheConfig getMainDiskCacheConfig(Context context) {
        /**
         * 推荐缓存到应用本身的缓存文件夹，这么做的好处是:
         * 1、当应用被用户卸载后能自动清除缓存，增加用户好感（可能以后用得着时，还会想起我）
         * 2、一些内存清理软件可以扫描出来，进行内存的清理
         */
        File fileCacheDir = context.getCacheDir();
        return DiskCacheConfig.newBuilder(context)
                .setBaseDirectoryName(IMAGE_PIPELINE_CACHE_DIR)
                .setBaseDirectoryPath(fileCacheDir)
                .build();
    }

    /**
     * 获取小图的磁盘配置（辅助）
     *
     * @return
     */
    private static DiskCacheConfig getSmallDiskCacheConfig(Context context) {
        File fileCacheDir = context.getCacheDir();
        return DiskCacheConfig.newBuilder(context)
                .setBaseDirectoryPath(fileCacheDir)
                .setBaseDirectoryName(IMAGE_PIPELINE_SMALL_CACHE_DIR)
                .setMaxCacheSize(MAX_DISK_SMALL_CACHE_SIZE)
                .setMaxCacheSizeOnLowDiskSpace(MAX_DISK_SMALL_ONLOWDISKSPACE_CACHE_SIZE)
                .build();
    }

    private static class MemoryCacheSupplier implements Supplier<MemoryCacheParams> {

        private final ActivityManager mActivityManager;

        private MemoryCacheSupplier(ActivityManager activityManager) {
            mActivityManager = activityManager;
        }

        @Override
        public MemoryCacheParams get() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return new MemoryCacheParams(getMaxCacheSize(), // 内存缓存中总图片的最大大小,以字节为单位。
                        56,                     // 内存缓存中图片的最大数量。
                        Integer.MAX_VALUE,                      // 内存缓存中准备清除但尚未被删除的总图片的最大大小,以字节为单位。
                        Integer.MAX_VALUE,                      // 内存缓存中准备清除的总图片的最大数量。
                        Integer.MAX_VALUE);                     // 内存缓存中单个图片的最大大小。
            } else {
                return new MemoryCacheParams(
                        getMaxCacheSize(),
                        256,
                        Integer.MAX_VALUE,
                        Integer.MAX_VALUE,
                        Integer.MAX_VALUE);
            }
        }

        private int getMaxCacheSize() {
            final int maxMemory = Math.min(mActivityManager.getMemoryClass() * ByteConstants.MB, Integer.MAX_VALUE);
            if (maxMemory < 32 * ByteConstants.MB) {
                return 4 * ByteConstants.MB;
            } else if (maxMemory < 64 * ByteConstants.MB) {
                return 6 * ByteConstants.MB;
            } else {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                    return 8 * ByteConstants.MB;
                } else {
                    return maxMemory / 4;
                }
            }
        }
    }

}
