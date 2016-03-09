package com.overstock.android.prototype.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.models.Product;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @author LeeMeehan Created on 08-Mar-16.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

  private ArrayList<Product> products;

  private Context context;

  private String baseImageUrl = "http://ak1.ostkcdn.com/images/products/";

  public ProductAdapter(final Context context, final ArrayList<Product> products) {
    this.context = context;
    this.products = products;
  }

  @Override
  public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    ViewGroup mainGroup = (ViewGroup) inflater.inflate(R.layout.product_card, parent, false);
    ProductViewHolder listHolder = new ProductViewHolder(mainGroup);
    return listHolder;
  }

  @Override
  public void onBindViewHolder(ProductViewHolder holder, int position) {
    final Product product = products.get(position);
    String imageUrl = String.format(baseImageUrl + product.getImageMedium1());
    // holder.imageView.setBackground(Drawable.createFromPath("/drawable/superbowl_nails.jpg"));
    Picasso picasso = new Picasso.Builder(context).memoryCache(new LruCache(30000)).build();
    picasso.with(context).load(imageUrl).resize(500, 500)
            .placeholder(R.drawable.superbowl_nails).into(holder.imageView);

  }

  @Override
  public int getItemCount() {
    return (null != products
        ? products.size()
        : 0);
  }
}
