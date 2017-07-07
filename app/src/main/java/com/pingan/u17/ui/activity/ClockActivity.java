package com.pingan.u17.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.pingan.u17.R;
import com.pingan.u17.base.BaseActivity;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/7/3
 */
public class ClockActivity extends BaseActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        mediaPlayer = MediaPlayer.create(this, R.raw.pig);
        //mediaPlayer.setLooping(true);
        mediaPlayer.start();

        //创建一个闹钟提醒的对话框,点击确定关闭铃声与页面
        new AlertDialog.Builder(ClockActivity.this)
                .setTitle("闹钟")
                .setMessage("时间到了")
                .setPositiveButton("关闭闹铃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mediaPlayer.stop();
                        ClockActivity.this.finish();
                    }
                })
                .show();
    }
}
