package com.ameya.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LibraryFragment extends Fragment {

    private static final String TAG = LibraryFragment.class.getSimpleName();
    private LinearLayout layoutAllDiscounts;
    private ImageView allDiscImg;
    private TextView allDiscTitle;
    private LinearLayout layoutAllItems;
    private ImageView allItemsImg;
    private TextView allItemsTitle;

    public static LibraryFragment newInstance() {
        return new LibraryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Inside onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Inside onCreateView");
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "Inside onViewCreated");

        layoutAllDiscounts = view.findViewById(R.id.all_discounts);
        allDiscImg = view.findViewById(R.id.all_disc_img);
        allDiscTitle = view.findViewById(R.id.all_disc_title);

        layoutAllItems = view.findViewById(R.id.all_items);
        allItemsImg = view.findViewById(R.id.all_items_img);
        allItemsTitle = view.findViewById(R.id.all_items_title);

        allDiscImg.setOnClickListener(null);
        allDiscImg.setFocusable(false);

        allDiscTitle.setOnClickListener(null);
        allDiscTitle.setFocusable(false);

        allItemsImg.setOnClickListener(null);
        allItemsImg.setFocusable(false);

        allItemsTitle.setOnClickListener(null);
        allItemsTitle.setFocusable(false);

        layoutAllDiscounts.setOnClickListener(allDiscountsClickedListener);
        layoutAllItems.setOnClickListener(allItemsClickedListener);


    }

    private View.OnClickListener allItemsClickedListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().startActivity(new Intent(getActivity(),ItemsActivity.class));
        }
    };

    private View.OnClickListener allDiscountsClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().startActivity(new Intent(getActivity(),DiscountsActivity.class));
        }
    };
}
