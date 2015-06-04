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
package es.voghdev.prjdagger2.global.datasource.api.rest.model;

public class UserApi {
    private UserApiName name;
    private String email;
    private String gender;
    private UserApiPicture picture;
    private UserApiLocation location;
    private String md5;

    public UserApiName getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() { return gender; }

    public UserApiPicture getPicture() { return picture; }

    public UserApiLocation getLocation() {
        return location;
    }

    public String getMd5() {
        return md5;
    }
}
