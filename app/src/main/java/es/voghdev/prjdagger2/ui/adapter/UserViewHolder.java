package es.voghdev.prjdagger2.ui.adapter;

import androidx.recyclerview.widget.RecyclerView;

import es.voghdev.prjdagger2.databinding.RowUserBinding;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public RowUserBinding binding;

    public UserViewHolder(RowUserBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }
}
