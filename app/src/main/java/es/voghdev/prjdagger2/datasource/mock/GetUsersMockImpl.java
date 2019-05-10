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
package es.voghdev.prjdagger2.datasource.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.usecase.GetUsers;

public class GetUsersMockImpl implements GetUsers {
    private User generateMockUser(String id, String name, String addr) {
        User u = new User();
        u.setId(id);
        u.setName(name);
        u.setAddress(addr);
        return u;
    }

    @Override
    public List<User> get() {
        return generateMockedUsers();
    }

    @Override
    public void getAsync(Listener listener) {
        List<User> users = generateMockedUsers();

        int random = new Random().nextInt(10);

        if (random < 8) {
            listener.onUsersReceived(users, true);
        } else if (random > 8 && random <= 9) {
            listener.onNoInternetAvailable();
        } else {
            listener.onError(new Exception("Unparseable response"));
        }
    }

    private List<User> generateMockedUsers() {
        List<User> users = new ArrayList<>();
        users.add(generateMockUser("1", "Antonio", "I.E.S. Zaidin-Vergeles, 5"));
        users.add(generateMockUser("2", "Juan", "I.E.S. Zaidin-Vergeles, 6"));
        users.add(generateMockUser("3", "Ana", "I.E.S. Zaidin-Vergeles, 7"));
        users.add(generateMockUser("4", "Isabel", "I.E.S. Zaidin-Vergeles, 8"));
        return users;
    }
}
