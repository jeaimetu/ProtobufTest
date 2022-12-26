package com.example.android.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.myapplication.proto.UserInfo.Person;


import com.example.android.myapplication.MyProtoBuf.*;
public class MyProtoBuf extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Integer mId = 1;
        String mUserName = "rick";
        String mAddress = "asdf1234@gmail.com";

        Person protoPerson = Person.newBuilder() // 값을 입력할 경우 newBuilder로 열어야 함
                .setId(mId)
                .setName(mUserName)
                .setEmail(mAddress) // 사전에 정의한 값들을 정의된 자료형에 맞게 입력
                .build(); //입력을 끝내면 build로 저장한다.

        Log.e("get protobuf", protoPerson.toString()); // 확인을 위해 에러로그 출력

    }
}
