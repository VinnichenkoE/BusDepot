package com.vinnichenko.bdepot.model.creator;

import com.vinnichenko.bdepot.model.entity.User;

import java.util.Map;

import static com.vinnichenko.bdepot.model.ParameterKey.*;

/**
 * The type User creator.
 */
public class UserCreator {

    private UserCreator() {
    }

    /**
     * Create user user.
     *
     * @param parameters the parameters
     * @return the user
     */
    public static User createUser(Map<String, String> parameters) {
        User user = new User();
        User.Role role;
        if (parameters.containsKey(USER_ID)) {
            user.setUserId(Long.parseLong(parameters.get(USER_ID)));
        }
        user.setLogin(parameters.get(USER_LOGIN));
        user.setIsActive((byte)1);
        user.setName(parameters.get(USER_NAME));
        user.setSurname(parameters.get(USER_SURNAME));
        user.setPhoneNumber(parameters.get(USER_PHONE_NUMBER));
        String roleId = parameters.get(USER_ROLE_INDEX);
        if (roleId == null || roleId.equals("")) {
            role = User.Role.CUSTOMER;
        } else {
            role = User.Role.values()[Integer.parseInt(roleId)];
        }
        user.setRole(role);
        return user;
    }
}
