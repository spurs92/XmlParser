package com.spurs.xmlresourceparser;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=(TextView)findViewById(R.id.text1);
    }

    public void clickBtn(View v){
        //res폴더 안에 있는 movies.xml파일을 읽어와서 분석(parse)하기..

        //res폴더 창고관리자 객체 소환하기..
        Resources res=getResources();

        //창고 관리자로 부터 파서객체 얻어오기
        XmlResourceParser xrp=res.getXml(R.xml.movies);

        String s="";//텍스트뷰에 보여질 문자열

        //파서의 분석 작업 시작
        try {
            xrp.next();
            int eventType=xrp.getEventType();

            String name;
            String msg;


            while (eventType !=XmlResourceParser.END_DOCUMENT){

                switch (eventType){
                    case XmlResourceParser.START_DOCUMENT:
                        s+="xml파싱을 시작합니다!!\n\n";
                        break;
                    case XmlResourceParser.START_TAG:
                        name=xrp.getName();//태그의 이름을 반환
                        if(name.equals("no")){
                            s+="No.";
                        }else if(name.equals("title")){
                            s+="제목 :";
                        }else if(name.equals("genre")){
                            s+="장르 :";
                        }
                        break;
                    case XmlResourceParser.TEXT:
                        msg=xrp.getText();
                        s+=msg;
                        break;
                    case XmlResourceParser.END_TAG:
                        s+="\n";
                        break;
                    case XmlResourceParser.END_DOCUMENT:
                        break;
                }

                //커서를 다음 이벤트로 이동
                eventType=xrp.next();
            }//while

            //문서 읽기 종료
            s+="\nxml문서 파싱종료......";

            //텍스트뷰에 분석된 글씨 출력
            text.setText(s);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
