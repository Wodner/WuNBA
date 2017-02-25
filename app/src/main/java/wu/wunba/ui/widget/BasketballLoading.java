package wu.wunba.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import wu.wunba.R;

/**
 * 描述：篮球加载框显示
 * 作者：Wu on 2017/2/19 23:55
 * 邮箱：wuwende@live.cn
 */

public class BasketballLoading extends Dialog{

    public static int TYPE_QUAN =0;
    public static int TYPE_QUAN_POINT = 1;

    private static BasketballLoading ballLoading;

    public BasketballLoading(Context context) {
        super(context);
    }

    public BasketballLoading(Context context, int themeResId) {
        super(context, themeResId);
    }

    public BasketballLoading(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    /**
     * 设置WLoading的参数
     * @param context
     * @param type Loading 样式  ：type=0 样式是quan_0;type=1 样式是quan_1;
     * @return
     */
    public static BasketballLoading createDialog(Context context, int type){
        ballLoading = new BasketballLoading(context, R.style.loading_dialog);
        if(type == TYPE_QUAN){
            View v_0 = LayoutInflater.from(context).inflate(R.layout.dialog_ball_loading_0, null);// 得到加载view
            RelativeLayout layout_0 = (RelativeLayout) v_0.findViewById(R.id.rl_ball_loading_0);// 加载布局
            ImageView ivBall = (ImageView) v_0.findViewById(R.id.iv_ball_loading);
            ImageView ivQuan = (ImageView) v_0.findViewById(R.id.iv_quan_rotate);
            Animation balllAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_ball_loading_ant_clockwise);
            Animation quanAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_ball_loading_clockwise);
            LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
            balllAnimation.setInterpolator(lin);
            quanAnimation.setInterpolator(lin);
            // 使用ImageView显示动画
            ivBall.startAnimation(balllAnimation);
            ivQuan.startAnimation(quanAnimation);
            ballLoading.setContentView(layout_0);
        }else{
            View v_1 = LayoutInflater.from(context).inflate(R.layout.dialog_ball_loading_1, null);// 得到加载view
            RelativeLayout layout_1 = (RelativeLayout) v_1.findViewById(R.id.rl_ball_loading_1);// 加载布局
            ImageView ivBall = (ImageView) v_1.findViewById(R.id.iv_ball_loading);
            ImageView ivQuan = (ImageView) v_1.findViewById(R.id.iv_quan_rotate);
            Animation balllAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_ball_loading_ant_clockwise);
            Animation quanAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_ball_loading_clockwise);
            // 使用ImageView显示动画
            LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
            balllAnimation.setInterpolator(lin);
            quanAnimation.setInterpolator(lin);
            ivBall.startAnimation(balllAnimation);
            ivQuan.startAnimation(quanAnimation);
            ballLoading.setContentView(layout_1);
        }
        ballLoading.setCancelable(true);
        ballLoading.getWindow().getAttributes().gravity = Gravity.CENTER;
        ballLoading.getWindow().getAttributes().width  =  ViewGroup.LayoutParams.WRAP_CONTENT;
        ballLoading.getWindow().getAttributes().height =  ViewGroup.LayoutParams.WRAP_CONTENT;
        return ballLoading;
    }
}
