package com.shop.shopmobile.utilities;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.shop.shopmobile.annotation.In;
import java.lang.reflect.Field;

public class ViewInjector {

    public static void injectViews(View view) {

        Log.i(view.getClass().getName(), "⭕ injectViews ");

        for (Field field : view.getClass().getDeclaredFields()) {

            final In in = field.getAnnotation(In.class);
            if (in == null) {
                continue;
            }

            try {
                final View value = view.findViewById(in.value());
                field.setAccessible(true);
                field.set(view, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    public static void injectViews(Activity view) {

        Log.i(view.getClass().getName(), "⭕ injectViews Activity");

        for (Field field : view.getClass().getDeclaredFields()) {

            final In in = field.getAnnotation(In.class);
            if (in == null) {
                continue;
            }

            try {
                final View value = view.findViewById(in.value());
                field.setAccessible(true);
                field.set(view, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
