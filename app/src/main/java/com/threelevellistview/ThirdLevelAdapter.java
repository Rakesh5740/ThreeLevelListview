package com.threelevellistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ThirdLevelAdapter extends BaseExpandableListAdapter {

    private Context context;
    ArrayList<MenuModel> headers;
    ArrayList<ArrayList<MenuModel>> childList;
    private ExpandableClick expandableClick;

    public ThirdLevelAdapter(Context context, ArrayList<MenuModel> headers,
                             ArrayList<ArrayList<MenuModel>> data, ExpandableClick expandableClick) {
        this.context = context;
        this.headers = headers;
        this.childList = data;
        this.expandableClick = expandableClick;
    }

    @Override
    public MenuModel getGroup(int groupPosition) {
        return headers.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return headers.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_second, null);
        TextView text = convertView.findViewById(R.id.rowSecondText);
        MenuModel groupText = getGroup(groupPosition);

        text.setText(groupText.menuName);

        ImageView imageview = convertView.findViewById(R.id.imageview);
        imageview.setImageDrawable(groupText.expendableIcon);


        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
//        return childData.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_third, null);

        LinearLayout llThird = convertView.findViewById(R.id.llThird);
        TextView textView = convertView.findViewById(R.id.rowThirdText);
        ImageView imageview = convertView.findViewById(R.id.imageview_);
        ArrayList<MenuModel> childArray = childList.get(groupPosition);
        MenuModel menuModel = childArray.get(childPosition);
        textView.setText(menuModel.menuName);
        imageview.setImageDrawable(menuModel.expendableIcon);

        convertView.setOnClickListener(v -> expandableClick.onClick(menuModel));

        if (groupPosition == 1 && menuModel.menuName.equals("Newwww"))
            return null;
        else
            return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return Integer.parseInt(headers.get(groupPosition).hasChildren ?
                "" + childList.get(groupPosition).size() : "0");
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}