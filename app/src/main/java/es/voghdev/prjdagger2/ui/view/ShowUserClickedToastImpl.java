/*
 * Copyright (C) 2016 Olmo Gallegos Hernández.
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
package es.voghdev.prjdagger2.ui.view;

import android.content.Context;
import android.widget.Toast;

import es.voghdev.prjdagger2.R;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.usecase.ShowUserClicked;

public class ShowUserClickedToastImpl implements ShowUserClicked {
    Context context;

    public ShowUserClickedToastImpl(Context context) {
        this.context = context;
    }

    @Override
    public void show(User user) {
        Toast.makeText(context, context.getString(R.string.user_was_clicked, user.getEmail()), Toast.LENGTH_LONG).show();
    }
}
