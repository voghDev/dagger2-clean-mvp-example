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

import java.util.ArrayList;
import java.util.List;

import es.voghdev.prjdagger2.global.model.User;

public class BaseUnitTest {
    public static final String SAMPLE_AVATAR = "http://ipbmafia.ru/uploads/monthly_2016_08/"
            + "male.png.af5965773671d5b524ec90d4c12b6ecd.png";

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

    protected List<User> generateMockUserList() {
        List<User> list = new ArrayList<User>();
        list.add(createMockUser("A001", "John Smith", "Sunset Blvd. 27", "smithjohn", "", "1248234564"));
        list.add(createMockUser("A002", "Dianne Harris", "Sunset Blvd. 29", "dianne", "", "1212354823"));
        list.add(createMockUser("A003", "Hans Veljden", "Sunset Blvd. 31", "hansv", "", "1248789723"));
        return list;
    }
}
