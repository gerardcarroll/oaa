package com.overstock.android.prototype.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.ProductDetailActivity;
import com.overstock.android.prototype.model.Product;
import com.squareup.picasso.Callback;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

/**
 * @author LeeMeehan Created on 08-Mar-16.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

  private ArrayList<Product> products;

  private Context context;

  private Activity activity;

  private static final String BASE_IMAGE_URL = "http://ak1.ostkcdn.com/images/products/";

  public ProductAdapter(final Activity activity, final Context context, final ArrayList<Product> products) {
    this.activity = activity;
    this.context = context;
    this.products = products;
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

    final int id = product.getId();

    holder.productNameTxt.setText(product.getName());
    final String currencyCode = Currency.getInstance(Locale.US).getSymbol();
    holder.productPriceTxt.setText(context.getString(R.string.product_price_fmt, currencyCode ,product.getMemberPrice().toString()));

    //TODO inject picasso.
    final int maxMemory = (int) (Runtime.getRuntime().maxMemory()/ 1024);
    final int cacheSize = maxMemory /8;
    final Picasso picasso = new Picasso.Builder(context).memoryCache(new LruCache(cacheSize)).build();
    holder.progressBar.setVisibility(View.VISIBLE);
    picasso.with(context).load(BASE_IMAGE_URL + product.getImageMedium1()).resize(300, 300)
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

    // OnClickListener on images to show Shared Element Transition example
    holder.imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(final View v) {

        final ImageView imageView = (ImageView) v;
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        final byte[] b = baos.toByteArray();


        String name = holder.productNameTxt.getText().toString();
        String price = holder.productPriceTxt.getText().toString();

        final Intent intent = new Intent(activity, ProductDetailActivity.class);
        intent.putExtra("image", b);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("price", price);
        final ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, v, context.getString(R.string.shared_element_transition));
        ActivityCompat.startActivity(activity, intent, transitionActivityOptions.toBundle());
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
