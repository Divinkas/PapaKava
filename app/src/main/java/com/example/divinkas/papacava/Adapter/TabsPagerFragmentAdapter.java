package com.example.divinkas.papacava.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.example.divinkas.papacava.Fragment.AbstractTabFragment;
import com.example.divinkas.papacava.Fragment.BasketFragment;
import com.example.divinkas.papacava.Fragment.CavaFragment;
import com.example.divinkas.papacava.Fragment.ProfileFragment;
import com.example.divinkas.papacava.R;

import java.util.HashMap;
import java.util.Map;

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {
    private Map<Integer, AbstractTabFragment> tabs;
    private Context ctx;

    public TabsPagerFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        ctx = context;
        initFragments(ctx);
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        int[] imageResId = {
                R.drawable.selector_cava_list,
                R.drawable.selector_icon_account,
                R.drawable.selector_basket_icon};

        Drawable image = ctx.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString("   " + tabs.get(position).getTITLE());
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;

        //return tabs.get(position).getTITLE();
    }


    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initFragments(Context context){
        tabs = new HashMap<>();

        tabs.put(0, CavaFragment.getInstance(context));
        tabs.put(1, ProfileFragment.getInstance(context));
        tabs.put(2, BasketFragment.getInstance(context));
    }
}
