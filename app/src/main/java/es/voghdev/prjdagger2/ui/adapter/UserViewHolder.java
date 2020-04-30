package es.voghdev.prjdagger2.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.voghdev.prjdagger2.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivBackground;
    public ImageView ivThumbnail;
    public TextView tvTitle;
    public TextView tvDescription;
    public TextView tvLabel;

    public UserViewHolder(View rootView) {
        super(rootView);

        ivBackground = rootView.findViewById(R.id.user_iv_background);
        ivThumbnail = rootView.findViewById(R.id.user_iv_thumbnail);
        tvTitle = rootView.findViewById(R.id.user_tv_title);
        tvDescription = rootView.findViewById(R.id.user_tv_description);
        tvLabel = rootView.findViewById(R.id.user_tv_label);
    }
}
