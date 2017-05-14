package com.first.akashshrivastava.randomactofkindness;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.github.clans.fab.FloatingActionButton;


public class RandomActMainActivity extends ActionBarActivity {

    private FactBook mFactBook = new FactBook();
    private ColorWheel mColorwheel = new ColorWheel();
    boolean flag = false, stopFlag = false;
    RevealLayout primary_layout;
    TextView ActString, ActString2, first_label, first_label2;
    RelativeLayout firstLayout, secondLayout;
    final String toasty = "You have 12hrs to complete this task.";
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    Bitmap image;
    ShareLinkContent linkContent;
    String sPJunk;
    FloatingActionButton shareButton;
    FloatingActionButton shareButtonLast;

    NotificationManager mNotificationManager;
    SharedPreferences mySharedPreferences;
    SharedPreferences.Editor editor;
    long currentTimer;
    String currentString;
    int color;
    CountDownTimer start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_random);

        /**
         * Initialization of controllers
         */

        firstLayout = (RelativeLayout) findViewById(R.id.firstLayout);
        secondLayout = (RelativeLayout) findViewById(R.id.secondLayout);
        ActString = (TextView) findViewById(R.id.Act_string);
        ActString2 = (TextView) findViewById(R.id.Act_string2);
        first_label = (TextView) findViewById(R.id.first_label);
        first_label2 = (TextView) findViewById(R.id.first_label2);
        primary_layout = (RevealLayout) findViewById(R.id.primary_layout);

        shareButton = (FloatingActionButton) findViewById(R.id.shareButton);
        shareButtonLast = (FloatingActionButton) findViewById(R.id.shareButtonLast);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        mySharedPreferences = this.getSharedPreferences("myPref", MODE_PRIVATE);
        editor = mySharedPreferences.edit();
        final SharedPreferences sharedPreferences = this.getSharedPreferences("com.first.akashshrivastava.randomactofkindness", Context.MODE_PRIVATE);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPicture();
            }
        });
        shareButtonLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPicture();
            }
        });


        SharedPreferences settings = getSharedPreferences("com.first.akashshrivastava.randomactofkindness", MODE_PRIVATE);


        // Writing data to SharedPreferences
        sharedPreferences.edit().putString("fact", sPJunk).apply();


        primary_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (flag) {

                } else {
                    if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {

                        shareButtonLast.setVisibility(View.VISIBLE);
                        flag = true;
                        primary_layout.next((int) event.getX(), (int) event.getY(), 2000);

                        String junk = mFactBook.getFact();

                        sharedPreferences.edit().putString("fact", junk).apply();
                        sPJunk = sharedPreferences.getString("fact", "");

                        //This updates the string.......Junk changed to sharedprefJunk
                        color = mColorwheel.getColor();
                        //Changed first_layout to mprimary here...
                        currentString = sPJunk;
                        if (stopFlag) {
                            firstLayout.setBackgroundColor(color);
                            ActString.setText(sPJunk);
                        } else {
                            secondLayout.setBackgroundColor(color);
                            ActString2.setText(sPJunk);
                        }

                        //   mshareButton.setVisibility(View.VISIBLE);

                        final String timerFinished = "Incoming task in 0hr 0m 0s, time to tap again!";
                        //Times function added here,

                        start = new CountDownTimer(43200000, 1000) {        //Times for 12hrs = 43200000 seconds...ticks every 1000 = 1 second


                            @Override
                            public void onTick(long millisUntilFinished) {
                                currentTimer = millisUntilFinished;

                                if (stopFlag) {
                                    first_label.setTypeface(null, Typeface.BOLD);
                                    first_label.setText(String.valueOf("Incoming task in  " + millisUntilFinished / (60 * 60 * 1000) % 24 + "hr " + millisUntilFinished / (60000) % 60 + "m " + (millisUntilFinished / 1000) % 60 + "s"));

                                } else {
                                    first_label2.setTypeface(null, Typeface.BOLD);
                                    first_label2.setText(String.valueOf("Incoming task in  " + millisUntilFinished / (60 * 60 * 1000) % 24 + "hr " + millisUntilFinished / (60000) % 60 + "m " + (millisUntilFinished / 1000) % 60 + "s"));
                                }
                            }

                            @Override
                            public void onFinish() {
                                //Notification starts here....
                                currentTimer = 0;
                                Intent intent = new Intent();
                                PendingIntent pendingIntent = PendingIntent.getActivity(RandomActMainActivity.this, 0, intent, 0);

                                Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_launcher);

                                Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

                                Notification notification = new Notification.Builder(RandomActMainActivity.this)

                                        .setTicker("Your next act is ready!")
                                        .setContentTitle("Random Acts of Kindness")
                                        .setContentText("Your next act of great kindness is ready!")
                                        .setVibrate(new long[]{200,100})
                                        .setContentIntent(pendingIntent)
                                        .setLargeIcon(bitmap)

                                        .setSmallIcon(R.drawable.ic_done_all_black_24dp).build();

                                //disables the notification when clicked upon....
                                notification.flags = Notification.FLAG_AUTO_CANCEL;

                                mNotificationManager = (NotificationManager)
                                        getSystemService(NOTIFICATION_SERVICE);
                                mNotificationManager.notify(0, notification);

                                //Notification ends here....


                                if (stopFlag) {
                                    first_label.setText(timerFinished);
                                    stopFlag = false;
                                } else {
                                    first_label2.setText(timerFinished);
                                    stopFlag = true;
                                }


                                flag = false;
                                //This happens when timer runs out...
                            }
                        }.start();
                        //Timer function stops here....
                        Toast.makeText(getApplicationContext(), toasty, Toast.LENGTH_LONG).show();

                        //   }

                        return true;
                    }
                }

                return false;
            }
        });
        if (mySharedPreferences.getBoolean("isRemaining", false)) {
            editor.putBoolean("isRemaining", false).commit();
            myFunction();
        }
    }

    public void postPicture() {
        Log.d("Test", "if:" + sPJunk);
        linkContent = new ShareLinkContent.Builder()
                .setContentTitle("Random Acts of Kindness")
                .setContentDescription("I just this using this app: " + sPJunk)
                .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.first.akashshrivastava.randomactofkindness"))
                .build();
        shareDialog.show(linkContent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_random_act_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();

            editor.putLong("currentTime", currentTimer);
            editor.putString("currentText", currentString);
            editor.putBoolean("isRemaining", true);
            editor.putLong("currentActualTime",System.currentTimeMillis());
            editor.putBoolean("stopFlag", stopFlag);
            editor.putInt("color", color);
            editor.commit();
//            start.cancel();


        }


    public void myFunction() {
        long time = System.currentTimeMillis()-mySharedPreferences.getLong("currentActualTime",0);
        currentTimer = mySharedPreferences.getLong("currentTime",0)-time;
        shareButtonLast.setVisibility(View.VISIBLE);
        final int childCount = primary_layout.getChildCount();
        if (childCount > 1) {
            for (int i = 0; i < childCount; i++) {
                View child = primary_layout.getChildAt(i);
                if (i == 0) {
                    primary_layout.bringChildToFront(child);
                }
            }
        }
        flag = true;
        sPJunk = mySharedPreferences.getString("currentText", null);
        currentString = sPJunk;
        color = mySharedPreferences.getInt("color", 0);
        if (stopFlag) {
            firstLayout.setBackgroundColor(color);
            ActString.setText(sPJunk);
        } else {
            secondLayout.setBackgroundColor(color);
            ActString2.setText(sPJunk);
        }
        final String timerFinished = "Incoming task in 0hr 0m 0s, time to tap again!";
        first_label.setTypeface(null, Typeface.BOLD);

        //Times function added here,

        start = new CountDownTimer(currentTimer, 1000) {        //Times for 12hrs = 43200000 seconds...ticks every 1000 = 1 second


            @Override
            public void onTick(long millisUntilFinished) {
                currentTimer = millisUntilFinished;

                if (stopFlag) {
                    first_label.setTypeface(null, Typeface.BOLD);
                    first_label.setText(String.valueOf("Incoming task in  " + millisUntilFinished / (60 * 60 * 1000) % 24 + "hr " + millisUntilFinished / (60000) % 60 + "m " + (millisUntilFinished / 1000) % 60 + "s"));

                } else {
                    first_label2.setTypeface(null, Typeface.BOLD);
                    first_label2.setText(String.valueOf("Incoming task in  " + millisUntilFinished / (60 * 60 * 1000) % 24 + "hr " + millisUntilFinished / (60000) % 60 + "m " + (millisUntilFinished / 1000) % 60 + "s"));
                }
            }

            @Override
            public void onFinish() {
                //Notification starts here....
                Intent intent = new Intent();
                PendingIntent pendingIntent = PendingIntent.getActivity(RandomActMainActivity.this, 0, intent, 0);
                currentTimer = 0;
                Notification notification = new Notification.Builder(RandomActMainActivity.this)

                        .setTicker("Your next act is ready!")
                        .setContentTitle("Random Acts of Kindness")
                        .setContentText("Your next act of great kindness is ready!")
                        .setVibrate(new long[]{200,100})
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.drawable.ic_done_all_black_24dp).build();

                //disables the notification when clicked upon....
                notification.flags = Notification.FLAG_AUTO_CANCEL;

                mNotificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
                mNotificationManager.notify(0, notification);

                //Notification ends here....


                if (stopFlag) {
                    first_label.setText(timerFinished);
                    stopFlag = false;
                } else {
                    first_label2.setText(timerFinished);
                    stopFlag = true;
                }


                flag = false;
                //This happens when timer runs out...
            }
        }.start();
        //Timer function stops here....
        Toast.makeText(getApplicationContext(), toasty, Toast.LENGTH_LONG).show();
    }
}
