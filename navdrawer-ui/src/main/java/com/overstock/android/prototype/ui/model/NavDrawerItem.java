package com.overstock.android.prototype.ui.model;

/**
 * Entity object to hold nav drawer item attributes, Parcel annotation accomidates state persistence
 * using icepick
 *
 * @author itowey
 * @version 1.0
 * @since 06/04/16
 */
@org.parceler.Parcel
public class NavDrawerItem {

    String title;
    String icon;
    boolean enabled;

    public NavDrawerItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(final String icon) {
        this.icon = icon;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
}
