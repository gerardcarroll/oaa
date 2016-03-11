package com.overstock.android.prototype.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.models.Product;
import com.squareup.picasso.Callback;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

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
  public void onBindViewHolder(final ProductViewHolder holder, int position) {
    final Product product = products.get(position);

    String imageUrl = String.format(baseImageUrl + product.getImageMedium1());

    holder.productNameTxt.setText(product.getName());
    String currencyCode = Currency.getInstance(Locale.US).getSymbol();
    holder.productPriceTxt.setText(currencyCode + product.getMemberPrice().toString());
    Picasso picasso = new Picasso.Builder(context).memoryCache(new LruCache(45000)).build();
    holder.progressBar.setVisibility(View.VISIBLE);
    picasso.with(context).load(imageUrl).resize(500, 500)
        .error(R.drawable.product_placeholder).into(holder.imageView, new Callback() {
          @Override
          public void onSuccess() {
            holder.progressBar.setVisibility(View.GONE);
          }

          @Override
          public void onError() {
            holder.progressBar.setVisibility(View.GONE);
          }
        });
  }

  @Override
  public int getItemCount() {
    return (null != products
        ? products.size()
        : 0);
  }
}
