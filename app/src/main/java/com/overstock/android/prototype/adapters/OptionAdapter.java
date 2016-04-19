package com.overstock.android.prototype.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.model.Options;

/**
 * Adapter class that extends BaseAdapter class to allow the binding of underlying data to a such UI element class.
 *
 * @author LeeMeehan.
 * @since Created on 12-Apr-16.
 * @see android.widget.BaseAdapter
 */
public class OptionAdapter extends BaseAdapter {

  private ArrayList<Options> options;

  private Activity activity;

  public OptionAdapter(final ArrayList<Options> options, final Activity activity) {
    this.options = options;
    this.activity = activity;
  }

  @Override
  public int getCount() {
    return options.size();
  }

  @Override
  public Object getItem(int position) {
    return options.get(position);
  }

  @Override
  public long getItemId(int position) {
    return options.get(position).getOptionId();
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    final Options option = options.get(position);
    LayoutInflater inflater = activity.getLayoutInflater();
    View row = inflater.inflate(R.layout.support_simple_spinner_dropdown_item, parent, false);
    ((TextView) row).setText(option.getDescription());
    if (position == 0) {
      ((TextView) row).setTypeface(Typeface.DEFAULT_BOLD);
    }
    return row;
  }
}
