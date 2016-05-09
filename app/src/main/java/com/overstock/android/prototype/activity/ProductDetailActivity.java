package com.overstock.android.prototype.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.fragment.ProductDetailsFragment;
import com.overstock.android.prototype.model.Product;

import org.parceler.Parcels;

/**
 * @author RayConnolly, LeeMeehan Created on 21-03-2016
 */
public class ProductDetailActivity extends AppCompatActivity {

  private static final String TAG = ProductDetailActivity.class.getName();

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);
    if (savedInstanceState == null) {

      final Bundle extras = getIntent().getExtras();
      final Product product = Parcels.unwrap(extras.getParcelable(ProductDetailsFragment.PRODUCT_DETAILS_PARCEL));

      getSupportFragmentManager().beginTransaction()
            .add(R.id.product_detils_activity_frm, ProductDetailsFragment.newInstance(product), product.getName())
            .addToBackStack(product.getName())
            .commit();
    }
  }

  @Override
  public void onBackPressed() {

    int currentNumberOfFragments = getSupportFragmentManager().getBackStackEntryCount();
    if (currentNumberOfFragments <= 1) {
      // the fragment container is an empty view, need to simulate an extra backbutton click to get back to previous
      // activity
      super.onBackPressed();
      super.onBackPressed();
    }
    else {
      getSupportFragmentManager().popBackStackImmediate();
      // get the fragment before the current visible fragment
      String fragmentName = getSupportFragmentManager().getBackStackEntryAt(currentNumberOfFragments - 2).getName();
      ProductDetailsFragment previousProductDetailsFragment = (ProductDetailsFragment) getSupportFragmentManager()
          .findFragmentByTag(fragmentName);

      getSupportFragmentManager().beginTransaction().replace(R.id.product_detils_activity_frm,
        previousProductDetailsFragment, previousProductDetailsFragment.getProduct().getName()).commit();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  @Override
  public boolean onCreateOptionsMenu(final Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(final MenuItem item) {
    final int id = item.getItemId();
    if (id == R.id.action_settings || id == R.id.action_refresh || id == R.id.action_logout) {
      Toast.makeText(this, "You clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

}
