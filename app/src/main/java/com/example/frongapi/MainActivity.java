package com.example.frongapi;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TextView fireBaseTextView;
    TextView fireBaseTextView2;
    TextView fireBaseTextView4;
    TextView fireBaseTextView5;
    TextView fireBaseStepX;
    TextView fireBaseStepY;
    TextView fireBaseStepXY3;
    TextView fireBaseStepXY2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference patient = database.getReference("patient/locations");
        DatabaseReference fall = database.getReference("patient/fall");
        DatabaseReference reset = database.getReference("patient/reset");
        DatabaseReference step = database.getReference("patient/step");
        DatabaseReference acc = database.getReference("patient/acc");
        DatabaseReference compass = database.getReference("patient/compass");
        DatabaseReference radius = database.getReference("patient/radius");



        // NOTIFY
        //requestWindowFeature(Window.FEATURE_NO_TITLE); //เด้งเเจ้งเตือนชัดๆ
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//เด้งเเจ้งเตือนชัดๆ

        setContentView(R.layout.activity_main);

        //Display the message from the database
   /*     step.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    final String message = dataSnapshot.getValue(String.class);
                    Log.d("message is ",message);
                    fireBaseTextView2 = findViewById(R.id.fireBaseTextView2);
                    fireBaseTextView2.setText(message);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        // ดูจำนวนก้าว

        // Read from the database
        fall.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Fall is ",value);

                boolean b1 = Boolean.parseBoolean(value); //เปลี่ยน string เป็น boolean b1
                if (b1) {
                    System.out.println("b1 is "+ b1);
                    showNotification();
                }


                Integer number=Integer.valueOf(15);
                System.out.println(value.getClass().getSimpleName()); // เอาไว้ดู DATA TYPE นะ getClass().getSimpleName()

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.",error.toException());
            }
        });

        // Read from the database
        acc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("acc is: ",value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });

        // Read from the database
        reset.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Reset is: ",value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });

        // Read from the database
        step.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d( "step is: ", value);
                fireBaseTextView2 = findViewById(R.id.fireBaseTextView2);
                fireBaseTextView2.setText(value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w( "Failed to read value.", error.toException());
            }
        });

        // Read from the database
        compass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d( "Compass is: ", value);
                fireBaseTextView4 = findViewById(R.id.fireBaseTextView4);
                fireBaseTextView4.setText(value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w( "Failed to read value.", error.toException());
            }
        });//เอาไว้ดูค่า compass จาก firebase ใน command

        // Read from the database
        radius.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d( "Radius is: ", value);
                fireBaseTextView5 = findViewById(R.id.fireBaseTextView5);
                fireBaseTextView5.setText(value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w( "Failed to read value.", error.toException());
            }
        });//เอาไว้ดูค่าองศา จาก firebase ใน command

        //ประกาศตัวแปรวาดกราฟ Creating the View
        LineChart chart = (LineChart) findViewById(R.id.chart);

        // Read from the database รับ x y มาพร้อตจุด
        patient.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                HashMap<String,String> map = (HashMap<String,String>) dataSnapshot.getValue();
                Log.d("map", map.toString());//ปริ้น map.tostring

                //รับพิกัด x y มาเก็บไว้ใน string step...str
                String stepXstr = map.get("stepX");String stepYstr = map.get("stepY");
                Log.d("StepX",stepXstr);Log.d("StepY",stepYstr);

                fireBaseStepX = findViewById(R.id.fireBaseStepX);
                fireBaseStepY = findViewById(R.id.fireBaseStepY);
                fireBaseStepX.setText(stepXstr);fireBaseStepY.setText(stepYstr);


                ArrayList<Entry> entries = new ArrayList<Entry>();

                //Adding data
                entries.add(new Entry(Float.parseFloat(stepXstr), Float.parseFloat(stepYstr))); // add entries to dataset

                LineDataSet dataSet = new LineDataSet(entries, "Locations");
                LineDataSet start = new LineDataSet(entries,"start");


                dataSet.setDrawValues(true);
                dataSet.setColor(Color.RED);
                dataSet.setValueTextColor(Color.BLACK); // styling, ..
                dataSet.setValueTextSize(10);
                dataSet.setDrawValues(false);
                dataSet.setDrawCircleHole(false);
                dataSet.setCircleRadius(7);

                dataSet.setCircleColor(Color.RED);
                chart.setPinchZoom(true);  //: If set to true, pinch-zooming is enabled. If disabled, x- and y-axis can be zoomed separately.
                chart.setDoubleTapToZoomEnabled(true);

                LineData lineData = new LineData(dataSet);

                XAxis xAxis = chart.getXAxis();
                xAxis.setTextSize(15);
                xAxis.setAxisMaximum(44);
                xAxis.setAxisMinimum(-44);

                YAxis left = chart.getAxisLeft();
                left.setDrawLabels(true); // no axis labels
                left.setDrawAxisLine(true); // no axis line
                left.setDrawGridLines(true); // no grid lines
                left.setDrawZeroLine(true); // draw a zero
                left.setTextSize(15);
                left.setAxisMaximum(20);
                left.setAxisMinimum(-20);

                YAxis right = chart.getAxisRight();
                right.setDrawLabels(true); // no axis labels
                right.setDrawAxisLine(true); // no axis line
                right.setDrawGridLines(true); // no grid lines
                right.setDrawZeroLine(true); // draw a zero line
                right.setTextSize(15);
                right.setAxisMaximum(20);
                right.setAxisMinimum(-20);

                chart.setDragEnabled(false);
                chart.setScaleEnabled(false);
                chart.notifyDataSetChanged();

                //chart.getDescription().setText(" ตำแหน่งของคผู้ส่วมใส่ ("+stepXstr+","+stepYstr+")");
                chart.getDescription().setTextSize(1f);

                chart.setData(lineData);
                chart.invalidate();

                chart.setFitsSystemWindows(true);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Info", "Failed to read value.", error.toException());
            }

        });
    }

    public void resetnoti(View view) {
        showNotification();
    }

    //on off noti
    public void resetfall(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reset = database.getReference("patient/reset");
        reset.setValue( "false");
    }

    public void resetfirebase(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reset = database.getReference("patient/reset");
        reset.setValue( "true");
    }

    public class DrawView extends View {
        Paint paint = new Paint();

        private void init() {
            paint.setColor(Color.BLACK);
        }

        public DrawView(Context context) {
            super(context);
            init();
        }

        public DrawView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public DrawView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            init();
        }

        @Override
        public void onDraw(Canvas canvas) {
            canvas.drawLine(0, 0, 20, 20, paint);
            canvas.drawLine(20, 0, 0, 20, paint);
        }}


        //Show N O T I F I C A T I O N F U N C T I O N
    public void showNotification(){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("แจ้งเตือนการล้ม มีคนล้ม");
        alertDialog.setMessage("ล้มแล้ว ล้มเเล้ว รีบเข้าไปช่วยเร็ว ");
        alertDialog.setIcon(R.drawable.common_google_signin_btn_icon_dark_normal_background);
        Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
        alertDialog.show();
        // do about noti.// do about noti.// do about noti.// do about noti.
        Toast.makeText(getApplicationContext(),"ล้มแล้ว ล้มแล้ว ล้มแล้ว ",Toast.LENGTH_SHORT).show();
        // do about noti.// do about noti.// do about noti.// do about noti.
        Context context = MainActivity.this;
        int color = ContextCompat.getColor(context, R.color.black);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.ic_launcher);

        Notification notification =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(bitmap)
                        .setContentTitle("ล้มแล้ว ล้มแล้ว ล้มแล้ว ล้มแล้ว")
                        .setContentText("Alert Alert Alert.")
                        .setAutoCancel(false)
                        .setColor(color)
                        .setVibrate(new long[] { 500, 1000, 500 })
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setContentIntent(getPendingIntent(context , "Hello World!!"))
                        .build();


        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1000, notification);
    }
    private PendingIntent getPendingIntent(Context context, String message) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("MESSAGE", message);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void toast_message(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert Dialog");
        alertDialog.setMessage("Welcome to dear user.");
        alertDialog.setIcon(R.drawable.common_google_signin_btn_icon_dark_normal_background);
        Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
        alertDialog.show();
    }

    public void GoTOpage2(View view) {
        Intent intent = new Intent(this, MainActivity2_Compass2.class);
        startActivity(intent);
    }
}

