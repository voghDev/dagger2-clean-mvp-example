/*
 * Copyright (C) 2015 Olmo Gallegos Hernández.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.voghdev.prjdagger2.ui.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrogomez.renderers.Renderer;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import es.voghdev.prjdagger2.R;
import es.voghdev.prjdagger2.databinding.RowUserBinding;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.ui.picasso.RoundedTransformation;

public class UserRenderer extends Renderer<User> {

    private Context mContext;
    private OnUserClicked listener = new EmptyOnUserClicked();
    private RowUserBinding binding;

    UserRenderer(Context ctx, OnUserClicked onUserClicked) {
        mContext = ctx.getApplicationContext();
        setListener(onUserClicked);
    }

    public interface OnUserClicked {
        void onPictureClicked(User user);

        void onBackgroundClicked(User user);
    }

    protected void setListener(OnUserClicked listener) {
        if (listener != null) {
            this.listener = listener;
        }
    }

    @Override
    protected void setUpView(View rootView) {

    }

    @Override
    protected void hookListeners(View rootView) {

    }

    public void onClickRow(View view) {
        listener.onBackgroundClicked(getContent());
    }

    public void onClickThumbnail(View view) {
        listener.onPictureClicked(getContent());
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        binding = RowUserBinding.inflate(inflater);

        return inflater.inflate(R.layout.row_user, parent, false);
    }

    @Override
    public void render() {
        User u = getContent();
        renderTitle(u);
        renderDescription(u);
        renderBackground(u);
        renderThumbnail(u);
        renderClicks(0);
    }

    private void renderBackground(User u) {
        int resId = R.mipmap.background3;
        Picasso.get()
                .load(resId)
                .into(binding.userIvBackground);
    }

    private void renderThumbnail(User user) {
        if (!user.hasThumbnail()) {
            return;
        }

        Picasso.get()
                .load(user.getThumbnail())
                .transform(new RoundedTransformation())
                .resizeDimen(R.dimen.user_thumbnail_w, R.dimen.user_thumbnail_h)
                .placeholder(R.mipmap.background1)
                .into(binding.userIvThumbnail);
    }

    private void renderDescription(User user) {
        binding.userTvDescription.setText(user.getEmail());
    }

    private void renderTitle(User user) {
        binding.userTvTitle.setText(user.getName());
    }

    private void renderClicks(int numberOfClicks) {
        binding.userTvLabel.setText(String.format(Locale.getDefault(), "%d", numberOfClicks));
    }

    private class EmptyOnUserClicked implements OnUserClicked {
        public void onPictureClicked(User user) {
            /* Empty */
        }

        public void onBackgroundClicked(User user) {
            /* Empty */
        }
    }
}