package com.overstock.android.prototype.widgets;

import android.content.Context;
import android.widget.TextView;

/**
 * @author lmeehan
 * @since Created on 20-Apr-16.
 */
public class PagerIndicator extends TextView {

  private int currentPageNumber;

  private int totalNumberOfPages;

  public PagerIndicator(Context context) {
    super(context);
  }

  public void setTotalNumberOfPages(final int totalNumberOfPages) {
    if (totalNumberOfPages < 1) {
      throw new IllegalArgumentException("totalNumberOfPages(" + totalNumberOfPages + ") cannot be lesser than 1");
    }

    this.totalNumberOfPages = totalNumberOfPages;

    if (currentPageNumber > totalNumberOfPages) {
      currentPageNumber = totalNumberOfPages;
    }

    updatePageNumberContent();
  }

  public boolean moveForward() {
    if (currentPageNumber < totalNumberOfPages) {
      setCurrentPageNumber(++currentPageNumber);

      return true;
    }

    return false;
  }

  public boolean moveBackward() {
    if (currentPageNumber > 0) {
      setCurrentPageNumber(--currentPageNumber);

      return true;
    }

    return false;
  }

  public void setCurrentPageNumber(final int currentPageNumber) {
    if (currentPageNumber < 1) {
      throw new IllegalArgumentException("currentPageNumber(" + currentPageNumber + ") cannot be lesser than 1");
    }

    if (currentPageNumber > totalNumberOfPages) {
      throw new IllegalArgumentException("currentPageNumber(" + currentPageNumber
        + ") cannot be greater than totalPageNumber(" + totalNumberOfPages + ")");
    }

    this.currentPageNumber = currentPageNumber;

    updatePageNumberContent();
  }

  private void updatePageNumberContent() {
    setText(currentPageNumber + " of " + totalNumberOfPages);
  }

}
