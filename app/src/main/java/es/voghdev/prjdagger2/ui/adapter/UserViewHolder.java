package es.voghdev.prjdagger2.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.voghdev.prjdagger2.databinding.RowUserBinding;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivBackground;
    public ImageView ivThumbnail;
    public TextView tvTitle;
    public TextView tvDescription;
    public TextView tvLabel;


    public UserViewHolder(RowUserBinding binding) {
        super(binding.getRoot());

        ivBackground = binding.userIvBackground;
        ivThumbnail = binding.userIvThumbnail;
        tvTitle = binding.userTvTitle;
        tvDescription = binding.userTvDescription;
        tvLabel = binding.userTvLabel;
    }
}
