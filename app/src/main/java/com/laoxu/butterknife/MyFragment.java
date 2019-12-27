package com.laoxu.butterknife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyFragment extends Fragment {

    @BindView(R.id.btn)
    Button btn;
    Unbinder bind;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.frament_my_layout,container,false);

        bind = ButterKnife.bind(this, view);//比activty多一个参数，view的参数
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.btn)
    public void btnClick(View view){
        //
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();//解绑：释放资源，节省内存
        }
    }
}
