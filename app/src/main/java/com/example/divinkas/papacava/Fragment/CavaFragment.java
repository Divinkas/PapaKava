package com.example.divinkas.papacava.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.divinkas.papacava.Adapter.FiltrAdapter;
import com.example.divinkas.papacava.Adapter.TovarListAdapter;
import com.example.divinkas.papacava.Data.CavaObject;
import com.example.divinkas.papacava.Data.Product;
import com.example.divinkas.papacava.R;
import com.example.divinkas.papacava.Retrofit.IMyProducts;
import com.example.divinkas.papacava.Retrofit.RetrofitClient;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class CavaFragment extends AbstractTabFragment implements View.OnClickListener {
    private static final int LAYOUT_FRAGMENT = R.layout.fragment_cava;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private IMyProducts iMyProducts;
    private static CavaObject cava;
    private static List<String> arrayItemFiltr;

    public static BottomSheetBehavior bottomSheetBehavior;
    public static RecyclerView recyclerViewTovars;
    public static TovarListAdapter tovarListAdapter;
    public static Context Scontext;

    public LinearLayout linearLayout;
    private Button btnOk;
    public RecyclerView recyclerViewBehavior;



    public static CavaFragment getInstance(Context ctx){
        Scontext = ctx;
        Bundle bundle = new Bundle();
        CavaFragment cavaFragment = new CavaFragment();
        cavaFragment.setArguments(bundle);
        cavaFragment.setContext(ctx);
        cavaFragment.setTitle(ctx.getString(R.string.navigation_item_cava));
        return cavaFragment;
    }
    public void setContext(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(LAYOUT_FRAGMENT, container, false);
        linearLayout = view.findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setJustifyContent(JustifyContent.CENTER);
        recyclerViewBehavior = view.findViewById(R.id.filtrContainer);
        recyclerViewBehavior.setLayoutManager(flexboxLayoutManager);
        arrayItemFiltr = arrayStr();
        recyclerViewBehavior.setAdapter(new FiltrAdapter(context, arrayItemFiltr));
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        btnOk = view.findViewById(R.id.btnOk_setFiltr);
        btnOk.setOnClickListener(this);

        recyclerViewTovars = view.findViewById(R.id.recycleView);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

        recyclerViewTovars.setLayoutManager(staggeredGridLayoutManager);

        Retrofit retrofit = RetrofitClient.getInstance();
        iMyProducts = retrofit.create(IMyProducts.class);
        compositeDisposable.add(iMyProducts.getAPI_cavaItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CavaObject>() {
                    @Override
                    public void accept(CavaObject cavaObject) throws Exception {
                        cava = cavaObject;
                        tovarListAdapter = new TovarListAdapter(Scontext, cavaObject.getProducts());
                        recyclerViewTovars.setAdapter(tovarListAdapter);
                    }
                }));

        return view;
    }

    public static void show(){
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }
    public List<String> arrayStr(){
        List<String> lst = new ArrayList<>();
        lst.add("1кг");
        lst.add("250гр");
        lst.add("500гр");
        lst.add("Ambassador");
        lst.add("Caffe Poli");
        lst.add("Gimoka");
        lst.add("Lavazza");
        lst.add("PapaKava");
        return lst;
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOk_setFiltr:
                show();
                break;
        }
    }

    public static void filtration(boolean[] show, Context context) {
        List<Product> list = new ArrayList<>();
        Scontext = context;

        for (int j = 0; j < cava.getProducts().size(); j++){
            boolean flag = true;
            for (int i = 0; i < show.length; i++){
            if(i==0 && !show[i] && cava.products.get(j).getWeight_type()==1){ flag = false; }
            if(i==1 && !show[i] && cava.products.get(j).getWeight_type()==2){ flag = false; }
            if(i==2 && !show[i] && cava.products.get(j).getWeight_type()==3){ flag = false; }

            if(i > 2 && !show[i] && arrayItemFiltr.get(i).equals(cava.products.get(j).getBrand_name())){ flag = false; }
            }
            if (flag){list.add(cava.products.get(j)); }
        }
        if(list.size()==0){
            list.add( null);
        }
        else {
            list.add(1, null);
        }
        tovarListAdapter.productList = list;
        recyclerViewTovars.getAdapter().notifyDataSetChanged();
    }
}
