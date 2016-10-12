/*
 * Copyright (C) 2016 Olmo Gallegos Hernández
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
package es.voghdev.prjdagger2;

import es.voghdev.prjdagger2.global.model.User;

public class BaseUnitTest {
    protected User createMockUser(
            String id, String name, String address,
            String user, String thumbnail, String facebookId) {
        User u = new User();
        u.setId(id);
        u.setName(name);
        u.setAddress(address);
        u.setUsername(user);
        u.setThumbnail(thumbnail);
        u.setFacebookId(facebookId);
        return u;
    }
}
