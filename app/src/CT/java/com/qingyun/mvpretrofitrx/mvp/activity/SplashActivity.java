package com.qingyun.mvpretrofitrx.mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.develop.wallet.eth.Wallet;
import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.fragment.AssetFragment;
import com.qingyun.mvpretrofitrx.mvp.net.HttpParamsUtils;
import com.qingyun.mvpretrofitrx.mvp.net.XCallBack;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.LocalManageUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.SpUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.StatsConfig;
import com.senon.mvpretrofitrx.R;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv)
    ImageView iv;
    private String first;
    private int choose = 1;

    @Override
    protected String getTitleRightText() {
        return null;
    }

    @Override
    protected String getTitleLeftText() {
        return null;
    }

    @Override
    protected String getTitleText() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {

        first = (String) SpUtils.getObjectFromShare(SplashActivity.this, "first");
        if (TextUtils.isEmpty(first))
            SpUtils.setObjectToShare(SplashActivity.this, choose, "language");
        else {
            choose = (int) SpUtils.getObjectFromShare(SplashActivity.this, "language");
        }
        LocalManageUtil.saveLocal(getActivity(), choose);

//        final Wallet wallet =  ApplicationUtil.getCurrentWallet();


//        loadOneTimeGif(getContext(), R.mipmap.et, iv, new GifListener() {
//            @Override
//            public void gifPlayComplete() {
//                if (wallet ==null){
//                    startActivity(ChooseBottomLevelActivity.class);
//                }else {
//                   startActivity(MainActivity.class);
//                }
//            }
//        });
//        Glide.with(this).load(R.mipmap.et).listener(new RequestListener() {
//
//
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
//                if (resource instanceof GifDrawable) {
//                    //加载一次
//                    ((GifDrawable) resource).setLoopCount(1);
//                    startActivity(MainActivity.class);
//
//                }
//
//                return false;
//            }
//
//        }).into(iv);

        if (!this.isTaskRoot()) {
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {finish()
            ;return;}}

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                requestAdvertisement();
//                if (wallet ==null){
//                    Intent intent = new Intent(SplashActivity.this,ChooseBottomLevelActivity.class);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.fade_out,R.anim.fade_in);
//                    finish();
//                }else {
//                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.fade_out,R.anim.fade_in);
//                    finish();
//                }

            }
        },500);
    }


    public static void loadOneTimeGif(Context context, Object model, final ImageView imageView, final GifListener gifListener) {
        Glide.with(context).asGif().load(model).listener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                try {
                    Field gifStateField = GifDrawable.class.getDeclaredField("state");
                    gifStateField.setAccessible(true);
                    Class gifStateClass = Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable$GifState");
                    Field gifFrameLoaderField = gifStateClass.getDeclaredField("frameLoader");
                    gifFrameLoaderField.setAccessible(true);
                    Class gifFrameLoaderClass = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader");
                    Field gifDecoderField = gifFrameLoaderClass.getDeclaredField("gifDecoder");
                    gifDecoderField.setAccessible(true);
                    Class gifDecoderClass = Class.forName("com.bumptech.glide.gifdecoder.GifDecoder");
                    Object gifDecoder = gifDecoderField.get(gifFrameLoaderField.get(gifStateField.get(resource)));
                    Method getDelayMethod = gifDecoderClass.getDeclaredMethod("getDelay", int.class);
                    getDelayMethod.setAccessible(true);
                    //设置只播放一次
                    resource.setLoopCount(1);
                    //获得总帧数
                    int count = resource.getFrameCount();
                    int delay = 0;
                    for (int i = 0; i < count; i++) {
                        //计算每一帧所需要的时间进行累加
                        delay += (int) getDelayMethod.invoke(gifDecoder, i);
                    }
                    imageView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (gifListener != null) {
                                gifListener.gifPlayComplete();
                            }
                        }
                    }, delay);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }).into(imageView);
    }

    /**
     * Gif播放完毕回调
     */
    public interface GifListener {
        void gifPlayComplete();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        StatsConfig.getInstance().onPageStart(this,"com.qingyun.mvpretrofitrx.mvp.activity.SplashActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StatsConfig.getInstance().onPageEnd(this,"com.qingyun.mvpretrofitrx.mvp.activity.SplashActivity");
    }

    private void requestAdvertisement(){
        RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl()+"start_up");
        x.http().post(params, new XCallBack() {
            @Override
            public void onAfterFinished() {

            }

            @Override
            public void onAfterSuccessOk(JSONObject object) {

                JSONObject data = object.getJSONObject("data");
                String photo = data.getString("photo");
                String url = data.getString("url");
                Intent intent = new Intent(SplashActivity.this,AdvertisementActivity.class);
                intent.putExtra("photo",photo);
                intent.putExtra("url",url);
                startActivity(intent);
                finish();

            }

            @Override
            public void onAfterSuccessErr(JSONObject object, String msg) {
                startActivity();
            }
        });
    }

    private void startActivity() {
        final Wallet wallet = ApplicationUtil.getCurrentWallet();
        if (wallet == null) {
            Intent intent = new Intent(SplashActivity.this, ChooseBottomLevelActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            finish();
        } else {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            finish();
        }
    }
}
