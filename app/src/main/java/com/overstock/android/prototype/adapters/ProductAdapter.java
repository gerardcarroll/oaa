package com.overstock.android.prototype.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.ProductDetailActivity;
import com.overstock.android.prototype.fragment.ProductDetailsFragment;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.model.Product;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import javax.inject.Inject;

/**
 * @author LeeMeehan Created on 08-Mar-16.
 */
@SuppressLint("ParcelCreator")
public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

  @Inject
  Picasso picasso;

  private ArrayList<Product> products;
  private Context context;
  private Activity activity;

  public ProductAdapter(Activity activity, Context context, ArrayList<Product> products) {
    this.activity = activity;
    this.context = context;
    this.products = products;
    ((OAppPrototypeApplication) context.getApplicationContext()).getComponent().inject(this);
  }

  @Override
  public ProductViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    final ViewGroup mainGroup = (ViewGroup) inflater.inflate(R.layout.product_card, parent, false);
    return new ProductViewHolder(mainGroup);
  }

  @Override
  public void onBindViewHolder(final ProductViewHolder holder, final int position) {
    final Product product = products.get(position);

    holder.productNameTxt.setText(product.getName());
    final String currencyCode = Currency.getInstance(Locale.US).getSymbol();
    holder.productPriceTxt
        .setText(context.getString(R.string.product_price_fmt, currencyCode, String.valueOf(product.getMemberPrice())));

    holder.progressBar.setVisibility(View.VISIBLE);
    picasso.load(context.getString(R.string.cdn_img_url) + product.getImageMedium1()).resize(300, 300).error(R.drawable.product_placeholder)
        .into(holder.imageView, new Callback() {
          @Override
          public void onSuccess() {
            holder.progressBar.setVisibility(View.GONE);
          }

          @Override
          public void onError() {
            holder.progressBar.setVisibility(View.GONE);
          }
        });

    holder.imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(final View v) {
        final ImageView imageView = (ImageView) v;
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

        //TODO: investigate cleaner routing methodology
        if(activity instanceof ProductDetailActivity) {
          ((AppCompatActivity) activity).getSupportFragmentManager()
                  .beginTransaction()
                  .replace(
                          R.id.product_detils_activity_frm,
                          ProductDetailsFragment.newInstance(product),
                          product.getName())
                  .addToBackStack(product.getName())
                  .commit();
        } else {
          final Intent intent = new Intent(activity, ProductDetailActivity.class);
          intent.putExtra("image", bitmap);
          intent.putExtra(ProductDetailsFragment.PRODUCT_DETAILS_PARCEL, Parcels.wrap(product));
          activity.startActivity(intent);
        }
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
