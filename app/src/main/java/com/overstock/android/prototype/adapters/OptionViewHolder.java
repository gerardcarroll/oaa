package com.overstock.android.prototype.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.overstock.android.prototype.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author LeeMeehan Created on 12-Apr-16.
 */
public class OptionViewHolder extends RecyclerView.ViewHolder {

  @Bind(R.id.option_name_txt)
  TextView optionName;

  public OptionViewHolder(View view) {
    super(view);
    ButterKnife.bind(this, view);
  }
}
