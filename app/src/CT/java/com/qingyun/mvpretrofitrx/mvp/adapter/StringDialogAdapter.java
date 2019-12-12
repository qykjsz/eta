package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class StringDialogAdapter extends BaseAdapter<String, StringDialogAdapter.StringDialogViewHolder> {



    public StringDialogAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    protected StringDialogViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_string_dialog, parent, false);
        return new StringDialogViewHolder(view);
    }

    @Override
    protected void onItemReset(StringDialogViewHolder holder) {

    }

    @Override
    protected void onItemSelect(StringDialogViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(StringDialogViewHolder holder, int position) {
        holder.tvString.setText(getList().get(position));
    }

    class StringDialogViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_string)
        TextView tvString;

        public StringDialogViewHolder(View itemView) {
            super(itemView);
        }
    }
}
