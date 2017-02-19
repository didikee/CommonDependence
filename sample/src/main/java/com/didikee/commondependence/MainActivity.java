package com.didikee.commondependence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ScrollView;

import com.didikee.dependence.share.AndroidShareHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.share)
    Button share;
    @BindView(R.id.activity_main)
    ScrollView activityMain;
    @BindView(R.id.about)
    Button about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.share)
    public void share() {
        // 分享
        AndroidShareHelper shareHelper =new AndroidShareHelper();
        shareHelper.shareWithBottomSheets(this,R.layout
                .layout_bottomsheetsdialog,shareHelper.getShareTextIntent("你好","www.baidu.com/"));
//        shareHelper.shareDefaultText(this,"fenx sds ","title","content");

    }



    @OnClick(R.id.about)
    public void about() {
    }
}
