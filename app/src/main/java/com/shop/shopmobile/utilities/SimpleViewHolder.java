package com.shop.shopmobile.utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SimpleViewHolder<T> extends RecyclerView.ViewHolder {

    private T data;

    public SimpleViewHolder(View itemView) {
        super(itemView);
    }

    public void setData(T data, Context context){
        itemView.setTag(data);
    }

    public T getData() { return data; }

}
