package com.ameya.shopping;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class ItemsViewAdapter extends ArrayAdapter<Item> {

    private static final String TAG = ItemsViewAdapter.class.getSimpleName();
    private List<Item> itemList;
    private Context mContext;

    public ItemsViewAdapter(List<Item> itemList, Context mContext){
        super(mContext,R.layout.list_item,itemList);
        this.itemList = itemList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View listViewItem = inflater.inflate(R.layout.list_item, null, true);

        TextView itemName = listViewItem.findViewById(R.id.item_title);
        ImageView itemImage = listViewItem.findViewById(R.id.item_img);
        TextView itemPrice = listViewItem.findViewById(R.id.item_price);

        Item item = itemList.get(position);

        itemName.setText(item.getTitle());

        String itemThumbnailUrl = item.getThumbnailUrl();
        Picasso.get().load(itemThumbnailUrl).into(itemImage);

        int itemId = item.getId();
        int random = new Random().nextInt(100) + 1;

        int price = (itemId * random) % 1000;

        itemPrice.setText(Integer.toString(price));

        Log.d(TAG,"Item Id is " + itemId + "random number generated is " + random + "item price is " + price);

        return listViewItem;
    }
}
