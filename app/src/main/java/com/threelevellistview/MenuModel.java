package com.threelevellistview;

import android.graphics.drawable.Drawable;

public class MenuModel {

    public String menuName;
    public Drawable expendableIcon;
    public boolean hasChildren;

    public MenuModel(String menuName,  boolean hasChildren, Drawable expendableIcon) {
        this.menuName = menuName;
        this.hasChildren = hasChildren;
        this.expendableIcon = expendableIcon;
    }
}
