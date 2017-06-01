package a67yjh00.com.example.lg.simplediary;

import android.content.Context;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos=openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str=edit.getText().toString();//toString() 문자열 변환 str 출력할때 쓸 데이터
                    fos.write(str.getBytes());//byte형으로 형변환 IOException로 해야함
                    fos.close();
                    Toast.makeText(MainActivity.this,"정상적으로 "+fileName+" 파일이 저장되었습니다.",Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


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
    public String readDiary(String fileName){//문자열을 저장시키는거
        String diaryStr=null;
        FileInputStream fis=null;
        try {
            fis = openFileInput(fileName);//fileName은 위에서 날짜로 지정됨 예외 처리 FileNotfoundExcept
            byte[] buf=new byte[500];//read할때 크기 설정
            fis.read(buf);//예외 처리가 IOException이어야 해서 따로 만들어야함
            diaryStr=new String(buf).trim();//문자열로 바꾸는것 trim()은 앞뒤 공백 제거
            but.setText("수정하기");
        }catch (FileNotFoundException e){
            edit.setText("일기가 존재하지 않습니다.");//파일이 없을때
            but.setText("새로 저장");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            fis.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return diaryStr;
    }
}
