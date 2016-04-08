package com.overstock.android.prototype.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.overstock.android.prototype.ui.R;
import com.overstock.android.prototype.ui.model.NavDrawerItem;

import java.util.List;

/**
 * The <b>NavDrawerRecyclerViewAdapter</b> implements interface RecyclerView.Adapter, this interface
 * provides data bindings for the recyclerview widget
 * <p/>
 * This implemtatation of RecyclerView.Adapter uses two viewholder types, one is used once for
 * the recyclerview first item see xml {@link R.layout.nav_drawer_header}, the other is for each
 * of the nav drawer options see xml {@link R.layout.nav_drawer_item}
 *
 * @author itowey
 * @version 1.0
 * @since 06/04/16
 */
public class NavDrawerRecyclerViewAdapter extends RecyclerView.Adapter<NavDrawerRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = NavDrawerRecyclerViewAdapter.class.getName();

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private Context context;
    private List<NavDrawerItem> navDrawerItems;


    public NavDrawerRecyclerViewAdapter(Context context, List<NavDrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    /**
     * @param parent
     * @param viewType
     * @return
     * @Inheritdoc Two layouts are used in the recycler view one for first element, the other for the rest of the items
     */
    @Override
    public NavDrawerRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NavDrawerRecyclerViewAdapter.ViewHolder rowView = null;
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_drawer_item, parent, false);
            rowView = new ViewHolder(v, viewType);
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_drawer_header, parent, false);
            rowView = new ViewHolder(v, viewType);
        }
        return rowView;
    }

    /**
     * @param holder
     * @param position
     * @Inheritdoc
     */
    @Override
    public void onBindViewHolder(NavDrawerRecyclerViewAdapter.ViewHolder holder, int position) {
        if (holder.Holderid == TYPE_ITEM) {
            int pos = position - 1;
            holder.textView.setText(navDrawerItems.get(pos).getTitle());
            if (navDrawerItems.get(pos).getIcon() != "") {
                holder.imageView.setImageResource(context.getResources().getIdentifier(navDrawerItems.get(pos).getIcon(), "drawable", context.getPackageName()));
            }
        } else {
            holder.profile.setImageResource(R.drawable.aka);           // Similarly we set the resources for header view
            holder.Name.setText("John Doe");
            holder.email.setText("johndoe@overstock.com");
        }
    }

    /**
     * @Inheritdoc
     */
    @Override
    public int getItemCount() {
        return navDrawerItems.size() + 1;
    }

    /**
     * @Inheritdoc
     */
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    /**
     * ViewHolder that implements {@Link View.OnClickListener}
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        int Holderid;

        //Line Items
        TextView textView;
        ImageView imageView;
        //Header Items
        TextView Name;
        ImageView profile;
        TextView email;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);
            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.navDrawerItemTitle);
                imageView = (ImageView) itemView.findViewById(R.id.navDrawerItemIcon);
                Holderid = TYPE_ITEM;
                itemView.setOnClickListener(this);
            } else {
                //TODO:get user account details from
                Name = (TextView) itemView.findViewById(R.id.name);
                email = (TextView) itemView.findViewById(R.id.email);
                profile = (ImageView) itemView.findViewById(R.id.circleView);
                Holderid = TYPE_HEADER;
            }
        }

        /**
         * @param v
         * @Inheritdoc
         */
        @Override
        public void onClick(View v) {
            String itemDisplayText = ((TextView) ((LinearLayout) v).getChildAt(1)).getText().toString();
            Log.i(TAG, "TODO: provide action for click on item  '" + itemDisplayText + "'");
        }
    }
}
