package com.qingyun.mvpretrofitrx.mvp.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.contract.WithdrawAddressContract;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.WithdrawAddress;
import com.qingyun.mvpretrofitrx.mvp.presenter.WithdrawAddressPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class AddWithdrawAddressActivity extends BaseActivity<WithdrawAddressContract.View,WithdrawAddressContract.Presenter> implements WithdrawAddressContract.View {
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.ben_scan)
    ImageView benScan;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;
    private int index;

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
        return getResources().getString(R.string.add_address);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_withdraw_address;
    }

    @Override
    public WithdrawAddressContract.Presenter createPresenter() {
        return new WithdrawAddressPresenter(this);
    }

    @Override
    public WithdrawAddressContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        index = getIntent().getIntExtra(IntentUtils.ADDRESS_INDEX, 0);
        etRemark.setText(getResources().getString(R.string.usdt_address_default_name)+" "+index);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ben_scan, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ben_scan:
                AddWithdrawAddressActivityPermissionsDispatcher.needCameraWithCheck(this);
                break;
            case R.id.btn_submit:
                getPresenter().addWithdrawAddress(etAddress.getText().toString(),etRemark.getText().toString());
                break;
        }
    }

    @Override
    public void getWithdrawAddressListSuccess(List<WithdrawAddress> withdrawAddresses) {

    }

    @Override
    public void addWithdrawAddressSuccess(NormalResponse normalResponse) {
        finish();
    }

    @Override
    public void deleteWithdrawAddressSuccess(NormalResponse normalResponse) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void needCamera() {
//        Intent intent = new Intent(AddWithdrawAddressActivity.this, ScanActivity.class);
//        intent.putExtra(IntentUtils.IS_WITHDRAW,true);
//        startActivityForResult(intent,IntentUtils.SCAN_RESULT_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AddWithdrawAddressActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showCamear(final PermissionRequest request) {
        ToastUtil.showShortToast(R.string.camera_permission_need_open);
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void deniedCamera() {
        ToastUtil.showShortToast(R.string.camera_permission_required);

    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void neverAskCamera() {
        ToastUtil.showShortToast(R.string.camera_permission_need_open);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null)
            return;
        String address = data.getExtras().getString(IntentUtils.SCAN_RESULT);
        if (!TextUtils.isEmpty(address)){
            String[] re = address.split("-");
            if (re.length>1) address = address = re[0];
            if (address.contains(":")){
                int start = address.indexOf(":")+1;
                int end  =  address.indexOf("?");
                address = address.substring(start,end);
            }
            etAddress.setText(address);
            etAddress.setSelection(etAddress.getText().toString().length());
        }
    }
}
