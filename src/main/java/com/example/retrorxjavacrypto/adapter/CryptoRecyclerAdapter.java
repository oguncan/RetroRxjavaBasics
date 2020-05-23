package com.example.retrorxjavacrypto.adapter;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;
import com.example.retrorxjavacrypto.R;
import com.example.retrorxjavacrypto.model.CryptoModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CryptoRecyclerAdapter extends RecyclerView.Adapter<CryptoRecyclerAdapter.MyViewHolder> {
    ArrayList<CryptoModel> cryptoList;
    Context context;

    public CryptoRecyclerAdapter(Context context, ArrayList<CryptoModel> cryptoModels) {
        this.context = context;
        this.cryptoList = cryptoModels;
    }

    @NonNull
    @Override
    public CryptoRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_crypto_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoRecyclerAdapter.MyViewHolder holder, int position) {

        Glide.with(context)
                .asDrawable()
                .load( cryptoList.get(position).logo_url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .disallowHardwareConfig()
                .apply(new RequestOptions().override(100,100))
                .into(holder.imgViewLogo);
        holder.txtViewName.setText("Name: "+cryptoList.get(position).name);
        holder.txtViewPrice.setText("Price: "+cryptoList.get(position).price);
    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewLogo;
        //TextView txtViewCurrency;
        TextView txtViewName;
        TextView txtViewPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewLogo = itemView.findViewById(R.id.imgViewLogo);
            txtViewName = itemView.findViewById(R.id.txtViewName);
            txtViewPrice = itemView.findViewById(R.id.txtViewPrice);
        }
    }
}
