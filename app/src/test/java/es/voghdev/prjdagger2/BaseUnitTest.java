package es.voghdev.prjdagger2;

import es.voghdev.prjdagger2.global.model.User;

/**
 * Created by olmo on 19/09/16.
 */
public class BaseUnitTest {
    protected User createMockUser(String id, String name, String address, String user, String thumbnail, String facebookId) {
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
