package com.example.android.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.android.myapplication.proto.UserInfo
import com.example.android.myapplication.proto.UserInfo.Person
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.OutputStream
import java.util.*
import java.util.zip.GZIPInputStream


class MainActivity : AppCompatActivity() {

    private lateinit var client: OkHttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mId = 1
        val mUserName = "rick"
        val mAddress = "asdf1234@gmail.com"

        val protoPerson = UserInfo.Person.newBuilder() // 값을 입력할 경우 newBuilder로 열어야 함
            .setId(mId)
            .setName(mUserName)
            .setEmail(mAddress) // 사전에 정의한 값들을 정의된 자료형에 맞게 입력
            .build() //입력을 끝내면 build로 저장한다.



        Log.e("encoded", Arrays.toString(protoPerson.toByteArray()))
        Log.e("get protobuf", protoPerson.toString())
        Log.e("test", mId.toString())


        client = OkHttpClient()

        val request: Request = Request.Builder()
            .url("wss://websocketstest.com/")
            .build()
        val listener: WebSocketListener = WebSocketListener()


        client.newWebSocket(request, listener)
        client.dispatcher().executorService().shutdown()

        //gzip testing
        Log.e("ZipTest", "using memory itself")

        val content = "Content Test String" //Data from websocket

        println("size of original: ${content.length}")
        val zipped = GZipStreamHelper.gzip(content)
        println("size zipped: ${zipped.size}")
        val unzipped = GZipStreamHelper.ungzip(zipped)
        println("size unzipped: ${unzipped.length}")
        assert(unzipped == content)
        println("Unzipped contents are $unzipped")



    }
}