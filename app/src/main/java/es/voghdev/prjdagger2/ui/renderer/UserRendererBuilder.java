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

import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import es.voghdev.prjdagger2.global.model.User;

public class UserRendererBuilder extends RendererBuilder<User> {

    public UserRendererBuilder(Context context, UserRenderer.OnUserClicked onUserClicked) {
        Collection<Renderer<User>> prototypes = getPrototypes(context, onUserClicked);
        setPrototypes(prototypes);
    }

    @Override
    protected Class getPrototypeClass(User content) {
        return UserRenderer.class;
    }

    private List<Renderer<User>> getPrototypes(Context context, UserRenderer.OnUserClicked onUserClicked) {
        List<Renderer<User>> prototypes = new LinkedList<Renderer<User>>();

        UserRenderer userRenderer = new UserRenderer(context, onUserClicked);
        prototypes.add(userRenderer);

        return prototypes;
    }
}