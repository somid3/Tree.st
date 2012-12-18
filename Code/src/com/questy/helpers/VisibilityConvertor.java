package com.questy.helpers;

import com.questy.domain.User;
import com.questy.enums.AnswerVisibilityEnum;

public class VisibilityConvertor {

    public static User convert (User user, AnswerVisibilityEnum visibility) {

        User out = user;

        if (AnswerVisibilityEnum.ANONYMOUS == visibility)
            out = new Anonymous(user);
        
        return out;
    }

}
