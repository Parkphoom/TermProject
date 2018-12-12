package com.example.termproject.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.termproject.R;
import com.example.termproject.Model.Corditem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Cordlistadapter extends ArrayAdapter<Corditem> {


    private Context mContext;
    private int mResource;
    private List<Corditem> mCordItemList;

    public Cordlistadapter(@NonNull Context context,
                            int resource,
                            @NonNull List<Corditem> CordItemList) {
        super(context, resource, CordItemList);
        this.mContext = context;
        this.mResource = resource;
        this.mCordItemList = CordItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResource, parent, false);

        TextView titleTextView = view.findViewById(R.id.title_text_view);
        TextView numberTextView = view.findViewById(R.id.number_text_view);
        ImageView imageView = view.findViewById(R.id.image_view);

        Corditem Corditem = mCordItemList.get(position);
        String title = Corditem.name;
//        String number = Corditem.number;
        String filename = Corditem.image;

        titleTextView.setText(title);
//        numberTextView.setText(number);

//        AssetManager am = mContext.getAssets();
//        try {
//            InputStream is = am.open(filename);//เปิดไฟล์ใน asserts มาอ่าน
//            Drawable drawable = Drawable.createFromStream(is," ");
//            imageView.setImageDrawable(drawable);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return view;
    }
}