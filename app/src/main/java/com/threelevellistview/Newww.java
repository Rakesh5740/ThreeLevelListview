//package com.threelevellistview;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.ExpandableListView;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class Newww extends BaseExpandableListAdapter {
//
//    ArrayList<MenuModel> parentHeaders;
//    ArrayList<ArrayList<MenuModel>> secondLevel;
//    private Context context;
//    ArrayList<HashMap<MenuModel, ArrayList<MenuModel>>> data;
//
//    private ExpandableClick expandableClick;
//
//    HashMap<MenuModel, List<MenuModel>> childList;
//
//    public Newww(
//            Context context,
//            ArrayList<MenuModel> parentHeader,
//            ArrayList<ArrayList<MenuModel>> secondLevel,
//            ArrayList<HashMap<MenuModel, ArrayList<MenuModel>>> data,
//            ExpandableClick expandableClick) {
//        this.context = context;
//        this.parentHeaders = parentHeader;
//        this.secondLevel = secondLevel;
//        this.data = data;
//        this.expandableClick = expandableClick;
//
////        this.childList = childList;
//    }
//
//    @Override
//    public int getGroupCount() {
//        return parentHeaders.size();
////        return Integer.parseInt(parentHeaders.get(0).hasChildren ?
////                "" + parentHeaders.size() : "0");
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        // no idea why this code is working
//        return 1;
//
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public Object getChild(int group, int child) {
//        return child;
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return true;
//    }
//
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        convertView = inflater.inflate(R.layout.row_first, null);
//
//        TextView text = convertView.findViewById(R.id.rowParentText);
//        text.setText(parentHeaders.get(groupPosition).menuName);
//        ImageView imageview = convertView.findViewById(R.id.imageview);
//        imageview.setImageDrawable(parentHeaders.get(groupPosition).expendableIcon);
//
//        return convertView;
//    }
//
//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//
//        final SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(context);
//        if (data.get(groupPosition) != null) {
//            HashMap<MenuModel, ArrayList<MenuModel>> secondLevelData = data.get(groupPosition);
//
//            ArrayList<ArrayList<MenuModel>> childData = new ArrayList<>();
//            for (MenuModel key : secondLevelData.keySet()) {
//                childData.add(secondLevelData.get(key));
//            }
//
//            secondLevelELV.setAdapter(new ThirdLevelAdapter(context,
//                    secondLevel.get(groupPosition), childData, expandableClick));
//            secondLevelELV.setGroupIndicator(null);
//
//        }
//
//        secondLevelELV.setOnGroupClickListener((parent1, v, pos, id) -> {
//            if (groupPosition == 1) {
//                expandableClick.onClick(secondLevel.get(groupPosition).get(pos));
//            }
//            return false;
//        });
//
//        return secondLevelELV;
//    }
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return true;
//    }
//}
