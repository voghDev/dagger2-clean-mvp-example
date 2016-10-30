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
package es.voghdev.prjdagger2.datasource.api.model;

import es.voghdev.prjdagger2.global.model.User;

public class UserApiEntry {
    private static final String SPACE = " ";

    IdApiEntry id;
    UserNameApiEntry name = new UserNameApiEntry();
    String email = "";
    String gender = "";
    UserPictureApiEntry picture = new UserPictureApiEntry();
    UserLocationApiEntry location = new UserLocationApiEntry();
    String md5 = "";
    String dob = "";

    public UserNameApiEntry getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public UserPictureApiEntry getPicture() {
        return picture;
    }

    public UserLocationApiEntry getLocation() {
        return location;
    }

    public String getMd5() {
        return md5;
    }

    public String getId() {
        return id != null ? id.parseId() : "";
    }

    public User parseUser() {

        User u = new User();
        u.setId(getId());
        u.setEmail(getEmail());
        u.setAddress(getLocation().getStreet());

        StringBuilder strb = new StringBuilder()
                .append(getName().getTitle()).append(SPACE)
                .append(getName().getFirst()).append(SPACE)
                .append(getName().getLast());
        u.setName(strb.toString());
        u.setThumbnail(getPicture().getThumbnail());

        return u;
    }
}
