package com.example.divinkas.papacava.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.divinkas.papacava.R;

public class BasketFragment extends AbstractTabFragment{
        private static final int LAYOUT_FRAGMENT = R.layout.fragment_basket;

        public static BasketFragment getInstance(Context ctx){

            Bundle bundle = new Bundle();
            BasketFragment basketFragment = new BasketFragment();
            basketFragment.setArguments(bundle);
            basketFragment.setContext(ctx);
            basketFragment.setTitle(ctx.getString(R.string.navigation_item_basket));

            return basketFragment;
        }

        public void setContext(Context context){
            this.context = context;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            view = inflater.inflate(LAYOUT_FRAGMENT, container, false);

            return view;
        }
}
