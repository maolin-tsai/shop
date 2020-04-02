package com.shop.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shop.R;

import com.shop.bean.ShoppingCarDataBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingCarAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private final LinearLayout llSelectAll;
    private final ImageView ivSelectAll;
    private final Button btnOrder;
    private final Button btnDelete;
    private final RelativeLayout rlTotalPrice;
    private final TextView tvTotalPrice;
    private List<ShoppingCarDataBean.DatasBean> data;
    private boolean isSelectAll = false;
    private double total_price;

    public ShoppingCarAdapter(Context context, LinearLayout llSelectAll,
                              ImageView ivSelectAll, Button btnOrder, Button btnDelete,
                              RelativeLayout rlTotalPrice, TextView tvTotalPrice) {
        this.context = context;
        this.llSelectAll = llSelectAll;
        this.ivSelectAll = ivSelectAll;
        this.btnOrder = btnOrder;
        this.btnDelete = btnDelete;
        this.rlTotalPrice = rlTotalPrice;
        this.tvTotalPrice = tvTotalPrice;
    }

    /**
     * 自定义设置数据方法；
     * 通过notifyDataSetChanged()刷新数据，可保持当前位置
     *
     * @param data 需要刷新的数据
     */
    public void setData(List<ShoppingCarDataBean.DatasBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        } else {
            return 0;
        }
    }
    @Override
    public Object getGroup(int groupPosition) {
        return  data.get(groupPosition);
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    static class GroupViewHolder {
        // relative shopname component
        @BindView(R.id.iv_select)  ImageView ivSelect;
        @BindView(R.id.tv_store_name) TextView tvStoreName;
        @BindView(R.id.ll) LinearLayout ll;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;

        if(convertView == null){
            convertView = View.inflate(context,R.layout.item_shop_car_group,null);

            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        }
        else{
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }

        return convertView;
    }



    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
