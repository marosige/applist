package me.marosi.applist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.packagename)
    TextView packagename;
    @BindView(R.id.checkbox)
    CheckBox checkbox;

    public AppViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final AppData appData) {
        icon.setImageDrawable(appData.getIcon());
        name.setText(appData.getName());
        packagename.setText(appData.getPackageName());
        checkbox.setChecked(appData.isSelected());

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appData.setSelected(checkbox.isChecked());
            }
        });
    }
}
