package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetModle;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class AssetModleAdapter extends BaseAdapter<AssetModle, AssetModleAdapter.AssetModleViewHolder> {



    public AssetModleAdapter(Context context, List<AssetModle> list) {
        super(context, list);
    }

    @Override
    protected AssetModleViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_asset_modele, parent, false);
        return new AssetModleViewHolder(view);
    }

    @Override
    protected void onItemReset(AssetModleViewHolder holder) {

    }

    @Override
    protected void onItemSelect(AssetModleViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(AssetModleViewHolder holder, int position) {
        holder.imageView.setImageResource(getList().get(position).getResId());
        holder.textView.setText(getList().get(position).getStrId());

    }

    class AssetModleViewHolder extends BaseViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.textView)
        TextView textView;
        public AssetModleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
