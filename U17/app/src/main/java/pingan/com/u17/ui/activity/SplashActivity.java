package pingan.com.u17.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import pingan.com.u17.R;
import pingan.com.u17.base.BaseActivity;
import pingan.com.u17.util.SpUtils;

public class SplashActivity extends BaseActivity {

    private static final String FIRST_COME = "first_come";

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_splash);
        mUnbinder = ButterKnife.bind(this);
        final boolean first_come = SpUtils.getBoolean(this, FIRST_COME, true);
        if (first_come) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SpUtils.putBoolean(FIRST_COME,false);
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }
            },2000);
        }else{
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();

    }
}
