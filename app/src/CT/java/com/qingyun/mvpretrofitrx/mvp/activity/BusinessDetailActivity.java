package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.contract.BusinessDetailContact;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessDetail;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.presenter.BusinessDetailPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.CopyUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class BusinessDetailActivity extends BaseActivity<BusinessDetailContact.View, BusinessDetailContact.Presenter> implements BusinessDetailContact.View {
    @BindView(R.id.iv_status)
    ImageView ivStatus;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_miner_pay)
    TextView tvMinerPay;
    @BindView(R.id.tv_miner_pay_1)
    TextView tvMinerPay1;
    @BindView(R.id.tv_getMoney_address)
    TextView tvGetMoneyAddress;
    @BindView(R.id.tv_pay_address)
    TextView tvPayAddress;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.tv_business_no)
    TextView tvBusinessNo;
    @BindView(R.id.tv_block)
    TextView tvBlock;
    private TransferLog transferLog;

    @Override
    protected String getTitleRightText() {
        return null;
    }

    @Override
    protected String getTitleLeftText() {
        return null;
    }

    @Override
    public boolean haveHeader() {
        return true;
    }

    @Override
    protected String getTitleText() {
        return getResources().getString(R.string.business_detail);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_business_detail;
    }

    @Override
    public BusinessDetailContact.Presenter createPresenter() {
        return new BusinessDetailPresenter(this);
    }

    @Override
    public BusinessDetailContact.View createView() {
        return this;
    }

    @Override
    public void init() {
        transferLog = (TransferLog) getIntent().getSerializableExtra(IntentUtils.TRANSFER_LOG);
        getPresenter().getBusinessDetail(ApplicationUtil.getCurrentWallet().getAddress(), transferLog.getName(), transferLog.getId());
    }

    @Override
    public void getBusinessDetailSuccess(BusinessDetail businessDetail) {
        if (businessDetail.getStatus().equals("0")){
//            失败
            Glide.with(getContext()).load(R.mipmap.xq_shibai).into(ivStatus);
            tvStatus.setText(R.string.transfer_failure);
        }else {
            Glide.with(getContext()).load(R.mipmap.xq_cg).into(ivStatus);
            tvStatus.setText(R.string.transfer_success);
        }
        tvTime.setText(businessDetail.getTime());
        tvAmount.setText(businessDetail.getAmount()+businessDetail.getName());
        tvMinerPay.setText(businessDetail.getCost());
        tvMinerPay1.setText("=Gas("+businessDetail.getGas()+")*GasPrice("+businessDetail.getGasp()+ "gwei)");
        tvGetMoneyAddress.setText(businessDetail.getOtheraddress());
        tvPayAddress.setText(businessDetail.getAddress());
//        tvRemark.setText(businessDetail.get);
        tvBusinessNo.setText(businessDetail.getHash());
        tvBlock.setText(businessDetail.getBlocknumber());
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_getMoney_address, R.id.btn_pay_address, R.id.btn_business_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getMoney_address:
                CopyUtils.copy(getContext(),tvGetMoneyAddress.getText().toString());
                break;
            case R.id.btn_pay_address:
                CopyUtils.copy(getContext(),tvPayAddress.getText().toString());

                break;
            case R.id.btn_business_no:
                CopyUtils.copy(getContext(),tvBusinessNo.getText().toString());
                break;
        }
    }
}
