package com.laoxu.butterknife;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    //绑定控件，按钮的点击事件，生成按钮
    @BindView(R.id.btn_sccode)
    Button codeBtn;
    @BindView(R.id.btn_code2)
    Button code2Btn;

    @BindView(R.id.iv_image)
    ImageView codeIv;

    Unbinder bind;

    //相机扫描按钮
    @BindView(R.id.btn_camarascancode)
    Button scanCarmara;

    //选择相册功能
    @BindView(R.id.btn_photocode)
    Button scanPhoto;
    //输入的文字
    @BindView(R.id.et_name)
    EditText nameEt;

//    Button openBtn;
//    Button closeBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       bind = ButterKnife.bind(this);//butterknife绑定当前类，初始化黄油刀的过程


        initData();

//        codeBtn = findViewById(R.id.btn_sccode);
    }

    private void initData() {

//        codeBtn.setText("我是修改的代码");
//        codeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    /**
     * 根据文字生成二维码图片
     * @param view
     */
    @OnClick(R.id.btn_sccode)
    public void code1Click(View view){
//        Toast.makeText(MainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
//        Button button = (Button) view;
//        button.setText("点击后的");

        if (TextUtils.isEmpty(nameEt.getText().toString())){
            Toast.makeText(MainActivity.this, "关键词不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        //第一个参数：字符串，第二个参数：宽，像素，第三个：高度，第四个：是否有logo（中间的小图片）
        Bitmap bitmap = CodeUtils.createImage(nameEt.getText().toString(),400,400,null);
        codeIv.setImageBitmap(bitmap);
    }


    @OnClick(R.id.btn_camarascancode)
    public void scanCamara(View view){

        //实现的第一步
        CodeUtils.analyzeByCamera(this);
    }

    @OnClick(R.id.btn_photocode)
    public void photo(View view){

        CodeUtils.analyzeByPhotos(this);
    }

    @OnClick(R.id.btn_open)
    public void openBtn(View view){
        CodeUtils.isLightEnable(true);
    }

    @OnClick(R.id.btn_close)
    public void closeBtn(View view){
        CodeUtils.isLightEnable(false);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //扫描的第二步，当二维码扫描页面关闭的时候，需要我们拿到二维码扫描到的数据（从第二个页面传递到第一个页面（当前页面））
        CodeUtils.onActivityResult(this, requestCode, resultCode, data, new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {

                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(MainActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();//释放资源
        }
    }
}
