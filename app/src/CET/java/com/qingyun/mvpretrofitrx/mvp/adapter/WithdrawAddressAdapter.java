package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.WithdrawAddress;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class WithdrawAddressAdapter extends BaseAdapter<WithdrawAddress, WithdrawAddressAdapter.WithdrawAddressViewHolder> {


    private OnItemClickListener deleteClickListener;

    public WithdrawAddressAdapter(Context context, List<WithdrawAddress> list) {
        super(context, list);
    }

    @Override
    protected WithdrawAddressViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_withdraw_address, parent, false);
        return new WithdrawAddressViewHolder(view);
    }

    @Override
    protected void onItemReset(WithdrawAddressViewHolder holder) {

    }

    @Override
    protected void onItemSelect(WithdrawAddressViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(WithdrawAddressViewHolder holder, final int position) {
        holder.tvAddress.setText(getList().get(position).getAddress());
        holder.tvAddressName.setText(getList().get(position).getRemark());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteClickListener!=null){
                    deleteClickListener.onItemClick(getList(),position);
                }
            }
        });


    }

    public void setDeleteListener(OnItemClickListener deleteClickListener){
        this.deleteClickListener = deleteClickListener;
    }

    class WithdrawAddressViewHolder extends BaseViewHolder {
        @BindView(R.id.btn_delete)
        ImageView btnDelete;
        @BindView(R.id.tv_address_name)
        BoldTextView tvAddressName;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        public WithdrawAddressViewHolder(View itemView) {
            super(itemView);
        }
    }
}
