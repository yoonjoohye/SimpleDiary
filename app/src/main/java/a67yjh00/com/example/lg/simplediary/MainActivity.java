package a67yjh00.com.example.lg.simplediary;

import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DatePicker date;
    EditText edit;
    Button but;
    String fileName;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = (DatePicker)findViewById(R.id.date);
        edit = (EditText)findViewById(R.id.edit);
        but = (Button)findViewById(R.id.but);

        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);//=DATE

        date.init(year, month, day, new DatePicker.OnDateChangedListener() {//handler
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                fileName=year+"_"+(month+1)+"_"+day+".txt";
                String rdata=readDiary(fileName);
                edit.setText(rdata);
                but.setEnabled(true);//활성화 시키기
            }
        });
    }
    public String readDiary(String fileName){
        return null;
    }
}
