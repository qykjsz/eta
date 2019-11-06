package com.qingyun.mvpretrofitrx.mvp.weight.dialog;


public class ProgressDialogUtils {

    private static ProgressDialogUtils instances;
    private static CommonDialogListener mListener;

    private ProgressDialogUtils(){

    }

    public void setListener(CommonDialogListener listener){
        this.mListener = listener;
    }

    public static ProgressDialogUtils getInstances(){
        if (instances == null)
        {
            synchronized (ProgressDialogUtils.class)
            {
                if (instances == null)
                {
                    instances = new ProgressDialogUtils();
                }
            }
        }
        return instances;
    }


    public void showDialog(){
        if(mListener!=null){
            mListener.show();
        }
    }

    public void cancel(){
        if(mListener!=null){
            mListener.cancel();
        }
    }
}
