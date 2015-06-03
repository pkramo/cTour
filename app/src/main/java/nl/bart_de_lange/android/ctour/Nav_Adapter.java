package nl.bart_de_lange.android.ctour;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import nl.bart_de_lange.android.ctour.DrawerCallbacks;
import nl.bart_de_lange.android.ctour.R;

import java.util.List;

public class Nav_Adapter extends RecyclerView.Adapter<Nav_Adapter.ViewHolder> {

    private List<NavigationItem> mData;
    private DrawerCallbacks mDrawerCallbacks;
    private int mSelectedPosition;
    private int mTouchedPosition = -1;

    public Nav_Adapter(List<NavigationItem> data) {
        mData = data;
    }


    public void setNavigationDrawerCallbacks(DrawerCallbacks drawerCallbacks) {
        mDrawerCallbacks = drawerCallbacks;
    }

    @Override
    public Nav_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drawer_row, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Nav_Adapter.ViewHolder viewHolder, final int i) {
        FrameLayout framelayout = (FrameLayout) viewHolder.textView.getChildAt(0);
        TextView textItem = (TextView) framelayout.getChildAt(0);
        textItem.setText(mData.get(i).getText());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if (mDrawerCallbacks != null)
                mDrawerCallbacks.onNavigationDrawerItemSelected(i);
            }
        });


        if (mSelectedPosition == i || mTouchedPosition == i) {
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.accent));
            textItem.setCompoundDrawablesWithIntrinsicBounds(mData.get(i).getDrawable(), null, null, null);
        } else {
            viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
            textItem.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }


    public void selectPosition(int position) {
        int lastPosition = mSelectedPosition;
        mSelectedPosition = position;
        notifyItemChanged(lastPosition);
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (LinearLayout) itemView.findViewById(R.id.item_name);
        }
    }
}
