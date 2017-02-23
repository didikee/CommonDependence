package com.didikee.commondependence;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.didikee.dependence.share.AndroidShareHelper;
import com.didikee.dependence.toast.UIToast2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main)
    ScrollView activityMain;
    @BindView(R.id.about)
    Button about;
    @BindView(R.id.toast_1)
    Button toast1;
    @BindView(R.id.toast_2)
    Button toast2;
    @BindView(R.id.share_1)
    Button share1;
    @BindView(R.id.share_2)
    Button share2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.about)
    public void about() {
    }

    @OnClick({R.id.toast_1, R.id.toast_2,R.id.share_1,R.id.share_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share_1:
                // 分享
                AndroidShareHelper shareDefault = new AndroidShareHelper();
                shareDefault.shareDefaultText(this,"默认分享标题","title","content");
                break;
            case R.id.share_2:
                AndroidShareHelper shareWithBottomSheetsDialog = new AndroidShareHelper();
                shareWithBottomSheetsDialog.shareWithBottomSheets(this,
                        R.layout.layout_bottomsheetsdialog,
                        shareWithBottomSheetsDialog.getShareTextIntent("默认分享标题", "https://www.google.com.hk/?gws_rd=ssl"));
                break;
            case R.id.toast_1:
                UIToast2.showToast(this, "Toast with theme color");
                break;
            case R.id.toast_2:
                UIToast2.showToast(this, "Toast with custom color", true, /*background color*/Color.GREEN, /*toast textColor*/Color.RED);
                break;
        }
    }
}
