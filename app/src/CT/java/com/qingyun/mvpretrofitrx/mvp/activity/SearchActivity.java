package com.qingyun.mvpretrofitrx.mvp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.FatherAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.SuperViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.DApp;
import com.qingyun.mvpretrofitrx.mvp.entity.SearchData;
import com.qingyun.mvpretrofitrx.mvp.net.HttpParamsUtils;
import com.qingyun.mvpretrofitrx.mvp.net.XCallBack;
import com.qingyun.mvpretrofitrx.mvp.utils.ZLog;
import com.senon.mvpretrofitrx.R;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Date 2019/11/17.
 * Created by Sam
 * ClassExplain：搜索
 */
public class SearchActivity extends Activity implements View.OnClickListener {

    private SearchGridViewAdapter mGradViewAdapter;
    private SearchAdapter mAdapter;
    private TextView tv_app_name;
    private TextView tv_link;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        initView();
        request();
    }

    private void initView() {
        GridView gridView = findViewById(R.id.gridView);
        mGradViewAdapter = new SearchGridViewAdapter(this);
        gridView.setAdapter(mGradViewAdapter);
        final ListView listView = findViewById(R.id.listView);
        mAdapter = new SearchAdapter(this);
        listView.setAdapter(mAdapter);
        tv_app_name = findViewById(R.id.tv_app_name);
        tv_link = findViewById(R.id.tv_link);
        EditText et_input = findViewById(R.id.et_input);
        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    listView.setVisibility(View.VISIBLE);
                    findViewById(R.id.ll).setVisibility(View.GONE);
                    tv_app_name.setText(s.toString());
                    tv_link.setText("http://" + s.toString());
                    requestSearchList(s.toString());
                } else {
                    listView.setVisibility(View.GONE);
                    findViewById(R.id.ll).setVisibility(View.VISIBLE);
                    tv_app_name.setText("");
                    tv_link.setText("http://");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        findViewById(R.id.tv_finish).setOnClickListener(this);
        findViewById(R.id.ll_link).setOnClickListener(this);
        findViewById(R.id.ll_dapp).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_finish:
                finish();
                break;
            case R.id.ll_dapp:
                startActivity(tv_app_name.getText().toString());
                break;
            case R.id.ll_link:
                startActivity(tv_link.getText().toString());
                break;
        }
    }

    private void startActivity(String url) {
        Intent intent = new Intent(SearchActivity.this, WebActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    private void requestSearchList(String SearchData) {
        RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl() + "et_selapp");
        params.addBodyParameter("appname", SearchData);
        x.http().post(params, new XCallBack() {
            @Override
            public void onAfterFinished() {

            }

            @Override
            public void onAfterSuccessOk(JSONObject object) {
                JSONArray data = object.getJSONArray("data");
                List<SearchData> list = JSON.parseArray(data.toJSONString(), SearchData.class);
                if (list.size() > 0) {
                    mAdapter.setDatas(list);
                }
            }

            @Override
            public void onAfterSuccessErr(JSONObject object, String msg) {
                if (mAdapter != null && mAdapter.getData() != null && mAdapter.getData().size() > 0) {
                    mAdapter.clear();
                }
            }
        });
    }

    private void request() {
        RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl() + "et_apphot");
        params.addBodyParameter("name", "");
        x.http().post(params, new XCallBack() {
            @Override
            public void onAfterFinished() {

            }

            @Override
            public void onAfterSuccessOk(JSONObject object) {
                JSONArray data = object.getJSONArray("data");
                List<DApp> list = JSON.parseArray(data.toJSONString(), DApp.class);
                if (list.size() > 0) {
                    mGradViewAdapter.setDatas(list);
                }
            }

            @Override
            public void onAfterSuccessErr(JSONObject object, String msg) {

            }
        });
    }


    public class SearchGridViewAdapter extends FatherAdapter<DApp> {

        public SearchGridViewAdapter(Context ctx) {
            super(ctx);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_gridview_search, parent, false);
                new ViewHolder(convertView);
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            final DApp item = getItem(position);
            setUi(viewHolder, item);
            return convertView;
        }

        private void setUi(final ViewHolder viewHolder, final DApp item) {
            viewHolder.tv_name.setText(item.name);
        }

        public class ViewHolder extends SuperViewHolder {
            TextView tv_name;

            public ViewHolder(View view) {
                super(view);
                tv_name = view.findViewById(R.id.tv_name);
            }
        }
    }

    public class SearchAdapter extends FatherAdapter<SearchData> {

        public SearchAdapter(Context ctx) {
            super(ctx);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_search, parent, false);
                new ViewHolder(convertView);
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            final SearchData item = getItem(position);
            setUi(viewHolder, item);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(item.url);
                }
            });
            return convertView;
        }

        private void setUi(final ViewHolder viewHolder, final SearchData item) {
            viewHolder.tv_name.setText(item.name);
            viewHolder.tv_introduce.setText(item.text);
            RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.mipmap.app_icon);
            requestOptions.circleCropTransform();
            requestOptions.transforms( new RoundedCorners(20));
            Glide.with(mContext).load(item.img).apply(requestOptions).into(viewHolder.iv_image);
        }

        public class ViewHolder extends SuperViewHolder {
            TextView tv_name;//名称
            TextView tv_introduce;//简介
            ImageView iv_image;//图片

            public ViewHolder(View view) {
                super(view);
                iv_image = view.findViewById(R.id.iv_image);
                tv_name = view.findViewById(R.id.tv_name);
                tv_introduce = view.findViewById(R.id.tv_introduce);
            }
        }
    }


}
