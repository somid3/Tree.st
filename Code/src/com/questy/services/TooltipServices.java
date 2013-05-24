package com.questy.services;

import com.questy.enums.UserIntegerSettingEnum;
import java.sql.SQLException;

public class TooltipServices extends ParentService {

    public static Boolean displayMinitip (UserIntegerSettingEnum minitipSettingEnum, Integer userId) throws SQLException {

        // Retrieve the mini-tip setting value
        Integer value = minitipSettingEnum.getValueByUserId(userId);

        return value != 0;
    }


}
