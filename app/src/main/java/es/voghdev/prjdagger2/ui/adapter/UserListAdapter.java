package es.voghdev.prjdagger2.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import es.voghdev.prjdagger2.R;
import es.voghdev.prjdagger2.databinding.RowUserBinding;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.ui.picasso.RoundedTransformation;

public class UserListAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private final Listener listener;
    private List<User> users = new ArrayList();

    public UserListAdapter(Listener listener) {
        this.listener = listener;
    }

    public void addUser(User user) {
        users.add(user);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowUserBinding binding = RowUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        binding.setHandlers(this);

        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);

        renderTitle(user, holder);
        renderDescription(user, holder);
        renderBackground(user, holder);
        renderThumbnail(user, holder);
        renderClicks(0, holder);
    }

    private void renderBackground(final User user, @NonNull UserViewHolder holder) {
        int resId = R.mipmap.background3;
        Picasso.get()
                .load(resId)
                .into(holder.binding.userIvBackground);
    }

    private void renderThumbnail(final User user, @NonNull UserViewHolder holder) {
        if (!user.hasThumbnail()) {
            return;
        }

        Picasso.get()
                .load(user.getThumbnail())
                .transform(new RoundedTransformation())
                .resizeDimen(R.dimen.user_thumbnail_w, R.dimen.user_thumbnail_h)
                .placeholder(R.mipmap.background1)
                .into(holder.binding.userIvThumbnail);
    }

    public void onRowClicked(View view) {
        listener.onBackgroundClicked(null);
    }

    public void onPictureClicked(View view) {
        listener.onPictureClicked(null);
    }

    private void renderDescription(User user, @NonNull UserViewHolder holder) {
        holder.binding.userTvDescription.setText(user.getEmail());
    }

    private void renderTitle(User user, @NonNull UserViewHolder holder) {
        holder.binding.userTvTitle.setText(user.getName());
    }

    private void renderClicks(int numberOfClicks, @NonNull UserViewHolder holder) {
        holder.binding.userTvLabel.setText(String.format(Locale.getDefault(), "%d", numberOfClicks));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public interface Listener {
        void onPictureClicked(User user);

        void onBackgroundClicked(User user);
    }
}
