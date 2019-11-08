package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.develop.wallet.eth.Wallet;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.utils.CopyUtils;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class MyWalletAdapter extends BaseAdapter<Wallet, MyWalletAdapter.MyWalletViewHolder> {


    public MyWalletAdapter(Context context, List<Wallet> list) {
        super(context, list);
    }

    @Override
    protected MyWalletViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_my_wallet, parent, false);
        return new MyWalletViewHolder(view);
    }

    @Override
    protected void onItemReset(MyWalletViewHolder holder) {

    }

    @Override
    protected void onItemSelect(MyWalletViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(MyWalletViewHolder holder, final int position) {
        holder.btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CopyUtils.copy(getContext(),getList().get(position).getAddress());

            }
        });
        holder.tvWalletAddress.setText(getList().get(position).getAddress());
        holder.btnWalletName.setText(getList().get(position).getWalletName());
        if (getList().get(position).getMakeCopy()){
            holder.tvMakeCopy.setText(getContext().getText(R.string.had_make_copy));
        }else {
            holder.tvMakeCopy.setText(getContext().getText(R.string.not_make_copy));
        }

    }

    class MyWalletViewHolder extends BaseViewHolder {


        @BindView(R.id.current)
        TextView current;
        @BindView(R.id.tv_make_copy)
        TextView tvMakeCopy;
        @BindView(R.id.btn_wallet_name)
        TextView btnWalletName;
        @BindView(R.id.tv_wallet_address)
        TextView tvWalletAddress;
        @BindView(R.id.btn_copy)
        ImageView btnCopy;
        public MyWalletViewHolder(View itemView) {
            super(itemView);
        }
    }
}
