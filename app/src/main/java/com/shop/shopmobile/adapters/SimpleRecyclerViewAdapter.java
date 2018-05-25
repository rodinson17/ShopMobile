package com.shop.shopmobile.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SimpleRecyclerViewAdapter<T, V extends SimpleViewHolder<T>> extends RecyclerView.Adapter<V> implements View.OnClickListener {

   private List<T> source;
   private Listener listener;
   private int resource;
   private Class<V> viewHolderClass;
   private Constructor<V> viewHolderConstructor;
   private Context context;

    public SimpleRecyclerViewAdapter(Class<V> viewClass, @LayoutRes int resource, List<T> dataSource) {

        this.viewHolderClass = viewClass;
        this.resource = resource;
        this.source = dataSource;

        try {
            if (viewClass.isMemberClass()) {
                viewHolderConstructor = viewClass.getConstructor(viewClass.getDeclaringClass(), View.class);
            } else {
                viewHolderConstructor = viewClass.getConstructor(viewClass.getDeclaringClass(), View.class);
            }
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

//    public SimpleRecyclerViewAdapter(Class<V> viewClass, @LayoutRes int resource, List<T> dataSource, T... selectt) {
//        this.(viewClass, resource, dataSource);
//    }

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        itemView.setOnClickListener(this);
        this.context = parent.getContext();

        V viewHolder = null;

        try {

            if (viewHolderClass.isMemberClass()) {
                viewHolder = viewHolderConstructor.newInstance(null, itemView);
            } else {
                viewHolder = viewHolderConstructor.newInstance(itemView);
            }

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {

        T data = source.get(position);
        holder.setData(data, context);
        // holder.setSelected(selected.contains(data));
    }

    @Override
    public int getItemCount() {  return source.size();  }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.simpleRecyclerViewOnCLick((T) view.getTag());
        }
    }

    public List<T> getSource() {  return source;  }

    public void setSource(List<T> source) {  this.source = source;  }

    public void setListener(Listener<T> listener) {  this.listener = listener;  }

    public interface Listener<T> {

       void simpleRecyclerViewOnCLick(T t);

    }
}
