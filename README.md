# CommonDependence

公共依赖库



## Android 分享[原生]

1. 默认分享

2. 使用 BottomSheetsDialog 遵循 Material Design 标准的分享界面:

![对比](raw/device-原生分享ui-1.png)

## Usages

**默认分享**

```
AndroidShareHelper shareDefault = new AndroidShareHelper();
shareDefault.shareDefaultText(this,"默认分享标题","title","content");
```

**使用 BottomSheetsDialog 分享**
```
AndroidShareHelper shareWithBottomSheetsDialog = new AndroidShareHelper();
                shareWithBottomSheetsDialog.shareWithBottomSheets(
                this,
                R.layout.layout_bottomsheetsdialog,
                shareHelper2.getShareTextIntent("默认分享标题", "https://www.google.com.hk/?gws_rd=ssl")
                );
```
## Android toast 主题[遵循原toast尺寸]
![toast的两种样式](raw/device-toast-theme-all.png)

## Usages

**Toast with theme color**
```
// Toast background
<color name="colorPrimaryDark">#993F9F</color>
//next
UIToast.showToast(this, "Toast with theme color");
```

**Toast with custom color**
```
UIToast.showToast(this, "Toast with custom color", true, /*background color*/Color.GREEN, /*toast textColor*/Color.RED);
```