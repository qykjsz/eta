package com.qingyun.mvpretrofitrx.mvp.weight.dialog;

import android.app.Dialog;
import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.romainpiel.titanic.library.Titanic;
import com.romainpiel.titanic.library.TitanicTextView;
import com.senon.mvpretrofitrx.R;

import java.sql.RowId;

public class CommonDialogService extends Service implements CommonDialogListener {

    private static Dialog dialog;
    private static TextView tv_str;
    private static ImageView img_loading;
    private static View view;
    private static AnimationDrawable animationDrawable;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ProgressDialogUtils.getInstances().setListener(this);//绑定

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(dialog!=null&&dialog.isShowing()){
            dialog.cancel();
            dialog=null;
        }
    }

    private void showDialog(){
        if(dialog==null&&CommonData.mNowContext!=null){
            dialog = new Dialog(CommonData.mNowContext,R.style.MyDialogStyle1);
            view = LayoutInflater.from(this).inflate(R.layout.dialog_loading,null,false);
//            TitanicTextView titanicTextView = view.findViewById(R.id.my_text_view);
//            new Titanic().start(titanicTextView);
//            img_loading = (ImageView) view.findViewById(R.id.myloading_image_id);
//            tv_str = (TextView) view.findViewById(R.id.mylaoding_text_id);
//            animationDrawable = (AnimationDrawable) img_loading
//                    .getDrawable();
//            animationDrawable.start();
            dialog.setContentView(view);
            dialog.show();
            WindowManager.LayoutParams lp = dialog.getWindow()
                    .getAttributes();
            if(CommonData.ScreenWidth!=0)
            lp.width =  CommonData.ScreenWidth/ 3;
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(lp);
        }
    }

    @Override
    public void show() {
       showDialog();
    }

    @Override
    public void cancel() {
         if(dialog!=null){
                dialog.dismiss();
                dialog=null;
//             animationDrawable.stop();
         }
    }
}
