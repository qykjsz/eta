package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class WalletAdapter extends BaseAdapter<Wallet, WalletAdapter.WalletViewHolder> {


    private OnItemClickListener addListener;

    public WalletAdapter(Context context, List<Wallet> list) {
        super(context, list);
    }

    @Override
    protected WalletViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_wallet, parent, false);
        return new WalletViewHolder(view);
    }

    @Override
    protected void onItemReset(WalletViewHolder holder) {

    }

    @Override
    protected void onItemSelect(WalletViewHolder holder) {
    }

    @Override
    protected void viewHolderBind(WalletViewHolder holder, final int position) {

        if (getList().get(position).getHave().equals("1")){
            Glide.with(getContext()).load(R.mipmap.ss_jian).into(holder.btnAdd);

        }else {
            Glide.with(getContext()).load(R.mipmap.sousuo_jia).into(holder.btnAdd);

        }
        Glide.with(getContext()).load(getList().get(position).getImg()).into(holder.ivPic);
        holder.tvSubAddress.setText(getList().get(position).getHyaddress());
        holder.tvWalletName.setText(getList().get(position).getName());
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addListener!=null)
                    addListener.onItemClick(getList(),position);
            }
        });
    }

    public void setAddListener(OnItemClickListener addListener){
        this.addListener = addListener;
    }

    class WalletViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_wallet_name)
        BoldTextView tvWalletName;
        @BindView(R.id.tv_sub_address)
        TextView tvSubAddress;
        @BindView(R.id.iv_opra)
        ImageView btnAdd;

        public WalletViewHolder(View itemView) {
            super(itemView);
        }
    }
}
