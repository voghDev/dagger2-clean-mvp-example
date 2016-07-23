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
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pedrogomez.renderers.Renderer;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import es.voghdev.prjdagger2.R;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.ui.picasso.RoundedTransformation;

public class UserRenderer extends Renderer<User> {
    protected Context mContext;
    protected OnUserClicked listener = new EmptyOnUserClicked();

    public UserRenderer(Context ctx, OnUserClicked onUserClicked) {
        mContext = ctx.getApplicationContext();
        setListener(onUserClicked);
    }

    @InjectView(R.id.user_container)
    RelativeLayout root;
    @InjectView(R.id.user_iv_thumbnail)
    ImageView ivThumbnail;
    @InjectView(R.id.user_tv_title)
    TextView tvTitle;
    @InjectView(R.id.user_tv_description)
    TextView tvDescription;
    @InjectView(R.id.user_tv_label)
    TextView tvClicks;

    public interface OnUserClicked{
        public void onPictureClicked(User user);
        public void onBackgroundClicked(User user);
    }

    protected void setListener(OnUserClicked listener) {
        if(listener != null)
            this.listener = listener;
    }

    @Override
    protected void setUpView(View rootView) {
        ButterKnife.inject(this, rootView);
    }

    @Override
    protected void hookListeners(View rootView) {

    }

    @OnClick(R.id.user_container)
    public void onClickRow(RelativeLayout root){
        listener.onBackgroundClicked(getContent());
    }
    @OnClick(R.id.user_iv_thumbnail)
    public void onClickThumbnail(ImageView imageView){
        listener.onPictureClicked(getContent());
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.row_user, parent, false);
    }

    @Override
    public void render() {
        User u = getContent();
        renderTitle(u);
        renderDescription(u);
        renderThumbnail(u);
        renderClicks(u);
    }

    private void renderThumbnail(User user) {
        if(!user.hasThumbnail())
            return;

        Picasso.with(mContext).load(user.getThumbnail()).transform(new RoundedTransformation()).placeholder(R.drawable.ic_launcher).into(ivThumbnail);
        Drawable background = mContext.getResources().getDrawable(R.drawable.rounded_edges);
        background.setColorFilter(mContext.getResources().getColor(R.color.light_gray), PorterDuff.Mode.SRC_OVER);
        ivThumbnail.setBackgroundDrawable(background); // setBackground requires minSdkVersion >= 16!!
    }

    private void renderDescription(User user) {
        tvDescription.setText(user.getId());
    }

    private void renderTitle(User user) {
        tvTitle.setText(user.getName());
    }

    private void renderClicks(User user) {
        tvClicks.setText(Integer.toString(0));
    }

    private class EmptyOnUserClicked implements OnUserClicked{
        public void onPictureClicked(User user) { }
        public void onBackgroundClicked(User user) { }
    }
}