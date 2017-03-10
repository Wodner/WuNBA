package wu.wunba.utils;

import android.widget.ImageView;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import wu.wunba.R;

/**
 * 描述：
 * 作者：Wu on 2017/2/19 11:42
 * 邮箱：wuwende@live.cn
 */

public class Xutils3ImageUtils {


    /**
     * @return
     */
    public static ImageOptions getImageOptionsDefault(){
        ImageOptions imageOptionsDefault = new ImageOptions.Builder()
        .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)//不裁剪 高清
                .setFailureDrawableId(R.mipmap.latest_pic_social_loading150_150px)
                .setLoadingDrawableId(R.mipmap.latest_pic_social_loading150_150px)
                .setUseMemCache(true)
                .build();
        return imageOptionsDefault;
    }



    /**
     * @return
     */
    public static ImageOptions getImageOptionsDefaultNoLoadingFailure(){
        ImageOptions imageOptionsDefault = new ImageOptions.Builder()
                .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)//不裁剪 高清
                .setUseMemCache(true)
                .build();
        return imageOptionsDefault;
    }
    /**
     * @return
     */


    /**
     * @param resLoading
     * @param resFailure
     * @return
     */
    public static ImageOptions getImageOptionsDefault(int resLoading,int resFailure){
        ImageOptions imageOptionsDefault = new ImageOptions.Builder()
                .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setFailureDrawableId(resFailure)
                .setLoadingDrawableId(resLoading)
                .setUseMemCache(true)
                .build();
        return imageOptionsDefault;
    }


    /**
     * @param isCircle 是否圆形
     * @param radius   角半径
     * @param isGif 是否忽略gif 默认true
     * @return
     */
    public static ImageOptions getConfigImageOptions(boolean isCircle,int radius,boolean isGif){
        ImageOptions imageOptionsCircle = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                .setCircular(isCircle)
                .setRadius(DensityUtil.dip2px(radius))
                .setIgnoreGif(isGif)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                .setCrop(true)
                .setFailureDrawableId(R.mipmap.latest_pic_social_loading150_150px)
                .setLoadingDrawableId(R.mipmap.latest_pic_social_loading150_150px)
                .setUseMemCache(true)
                .build();
        return imageOptionsCircle;
    }

    /**
     * @param isCircle
     * @param resLoading
     * @param resFailure
     * @return
     */
    public static ImageOptions getConfigImageOptions(boolean isCircle,int resLoading,int resFailure){
        ImageOptions imageOptionsCircle = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                .setCircular(isCircle)
//                .setRadius(DensityUtil.dip2px(radius))
//                .setIgnoreGif(isGif)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                .setCrop(true)
                .setFailureDrawableId(resFailure)
                .setLoadingDrawableId(resLoading)
                .setUseMemCache(true)
                .build();
        return imageOptionsCircle;
    }






    /**
     * 显示图片（默认情况）
     *
     * @param imageView 图像控件
     * @param iconUrl   图片地址
     */
    public static void display(ImageView imageView, String iconUrl,ImageOptions imageOptions) {
        x.image().bind(imageView, iconUrl,imageOptions);
    }





}
