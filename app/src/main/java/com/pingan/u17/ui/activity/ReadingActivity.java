package com.pingan.u17.ui.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pingan.u17.R;
import com.pingan.u17.base.BaseActivity;

import java.util.Calendar;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/9
 */

public class ReadingActivity extends BaseActivity {


    private Button btnSetClock;
    private Button btnbtnCloseClock;
    private AlarmManager alarmManager;
    private PendingIntent pi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentLayout(R.layout.alarm_layout);

        btnSetClock = (Button) findViewById(R.id.btnSetClock);
        btnbtnCloseClock = (Button) findViewById(R.id.btnCloseClock);

        // ①获取AlarmManager对象:
        /*alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // 指定要启动的是Activity组件,通过PendingIntent调用getActivity来设置
        Intent intent = new Intent(ReadingActivity.this, ClockActivity.class);
        pi = PendingIntent.getActivity(ReadingActivity.this, 0, intent, 0);
       btnSetClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentTime = Calendar.getInstance();
                // 弹出一个时间设置的对话框,供用户选择时间
                new TimePickerDialog(ReadingActivity.this, 0,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //设置当前时间
                                Calendar c = Calendar.getInstance();
                                c.setTimeInMillis(System.currentTimeMillis());
                                // 根据用户选择的时间来设置Calendar对象
                                c.set(Calendar.HOUR, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                // ②设置AlarmManager在Calendar对应的时间启动Activity
                                alarmManager.set(AlarmManager.RTC_WAKEUP,
                                        c.getTimeInMillis(), pi);
                                // 提示闹钟设置完毕:
                                Toast.makeText(ReadingActivity.this, "闹钟设置完毕", Toast.LENGTH_SHORT).show();
                            }
                        }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime
                        .get(Calendar.MINUTE), false).show();
                btnbtnCloseClock.setVisibility(View.VISIBLE);
            }
        });*/
        /*btnSetClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(System.currentTimeMillis());
                // 根据用户选择的时间来设置Calendar对象
                c.set(Calendar.HOUR, hourOfDay);
                c.set(Calendar.MINUTE, minute);
                c.set(Calendar.SECOND,minute);
                // ②设置AlarmManager在Calendar对应的时间启动Activity
                alarmManager.set(AlarmManager.RTC_WAKEUP,
                        c.getTimeInMillis(), pi);
            }
        });*/

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // 指定要启动的是Activity组件,通过PendingIntent调用getActivity来设置
        Intent intent = new Intent(ReadingActivity.this, ClockActivity.class);
        pi = PendingIntent.getActivity(ReadingActivity.this, 0, intent, 0);

        /*btnSetClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentTime = Calendar.getInstance();
                // 弹出一个时间设置的对话框,供用户选择时间
                new TimePickerDialog(ReadingActivity.this, 0,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view,
                                                  int hourOfDay, int minute) {
                                //设置当前时间
                                Calendar c = Calendar.getInstance();
                                c.setTimeInMillis(System.currentTimeMillis());
                                // 根据用户选择的时间来设置Calendar对象
                                c.set(Calendar.HOUR, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.set(Calendar.SECOND, 0);
                                // ②设置AlarmManager在Calendar对应的时间启动Activity
                                alarmManager.set(AlarmManager.RTC_WAKEUP,
                                        c.getTimeInMillis(), pi);
                                // 提示闹钟设置完毕:
                                Toast.makeText(ReadingActivity.this, "闹钟设置完毕",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }, currentTime.get(Calendar.HOUR), currentTime.get(Calendar.MINUTE), false).show();
                btnbtnCloseClock.setVisibility(View.VISIBLE);
            }
        });*/

      //  Calendar currentTime = Calendar.getInstance();
        //currentTime.get(Calendar.HOUR) currentTime.get(Calendar.MINUTE);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.HOUR, 5);
        c.set(Calendar.MINUTE, 45);
        c.set(Calendar.SECOND, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                c.getTimeInMillis(), pi);

        btnbtnCloseClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManager.cancel(pi);
                btnbtnCloseClock.setVisibility(View.GONE);
                Toast.makeText(ReadingActivity.this, "闹钟已取消", Toast.LENGTH_SHORT)
                        .show();
            }
        });


        btnbtnCloseClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManager.cancel(pi);
                btnbtnCloseClock.setVisibility(View.GONE);
                Toast.makeText(ReadingActivity.this, "闹钟已取消", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private CollapsingToolbarLayoutState state;
    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }
}
