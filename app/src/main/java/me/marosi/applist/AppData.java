package me.marosi.applist;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

public class AppData {
    private Drawable icon;
    private String name;
    private String packageName;
    private boolean selected;

    public AppData(Drawable icon, String name, String packageName) {
        this.icon = icon;
        this.name = name;
        this.packageName = packageName;
        this.selected = true;
    }

    public AppData(PackageManager pm, ApplicationInfo applicationInfo) {
        this.name = applicationInfo.loadLabel(pm).toString();
        this.packageName = applicationInfo.packageName;
        try {
            this.icon = pm.getApplicationIcon(this.packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        this.selected = true;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
