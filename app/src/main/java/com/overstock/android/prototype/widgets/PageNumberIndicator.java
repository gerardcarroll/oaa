package com.overstock.android.prototype.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author lmeehan
 * @since Created on 20-Apr-16.
 */
public class PageNumberIndicator extends TextView {

  private int currentPageNumber;

  private int totalNumberOfPages;

  public PageNumberIndicator(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setTotalNumberOfPages(final int totalNumberOfPages) {
    if (totalNumberOfPages < 1) {
      throw new IllegalArgumentException("totalNumberOfPages(" + totalNumberOfPages + ") cannot be lesser than 1");
    }
    this.totalNumberOfPages = totalNumberOfPages;
    this.currentPageNumber = 1;

    updatePageNumberContent();
  }

  public void setCurrentPageNumber(final int currentPageNumber) {
    this.currentPageNumber = currentPageNumber;
    updatePageNumberContent();
  }

  private void updatePageNumberContent() {
    setText(String.format("%d of %d", currentPageNumber, totalNumberOfPages));
  }

}
