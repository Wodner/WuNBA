package wu.wunba.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import wu.wunba.BaseActivity;
import wu.wunba.R;

/**
 * 描述：
 * 作者：Wu on 2017/2/19 12:47
 * 邮箱：wuwende@live.cn
 */

public class WelcomeNBAChina extends BaseActivity implements EasyPermissions.PermissionCallbacks{


    private final int RC_EXTERNAL_STORAGE = 123;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViewsAndEvents() {
        externalStorage();
    }

    @AfterPermissionGranted(RC_EXTERNAL_STORAGE)
    public void externalStorage(){
        String perm[] = {android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS};
        if(EasyPermissions.hasPermissions(mContext,perm)){
            splash();
        }else {
            EasyPermissions.requestPermissions(this,"NBA 应用需要使用你设备的存储",
                    RC_EXTERNAL_STORAGE,perm);
        }
    }


    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Logger.d("权限不允许");
        splash();
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Logger.d("权限允许");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    private void splash(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(WelcomeNBAChina.this, MainActivity.class);
                startActivity(i);
                //启动主Activity后销毁自身
                finish();
            }
        }, 1000);
    }


}
