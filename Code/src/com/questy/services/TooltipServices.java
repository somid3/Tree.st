package com.questy.services;

import com.questy.dao.*;
import com.questy.domain.*;
import com.questy.enums.RoleEnum;
import com.questy.enums.TooltipEnum;
import com.questy.enums.UserToNetworkIntegerSettingEnum;
import com.questy.helpers.Tuple;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TooltipServices extends ParentService {

    public static TooltipEnum getNextTooltipByUserId(Integer userId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving user's tooltip object
        Tooltip on = TooltipDao.getByUserId(conn,  userId);

        // If the user has not accepted any tooltips yet
        TooltipEnum out = null;
        if (on == null)
            out = TooltipEnum.START;
        else
            out = on.getOnTooltip();

        return out.getNext();
    }

    public static void updateOnTooltipByUserId(Integer userId, TooltipEnum onTooltip) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Attempting to update on tool tip
        Integer updates = TooltipDao.updateOnTooltipByUserId(conn, userId, onTooltip);

        // Did the user have a tooltip object?
        if (updates < 1) {

            // No, insert new tool tip object;
            TooltipDao.insert(conn, userId, onTooltip);

        }

    }

    public static Boolean displayMinitip (UserToNetworkIntegerSettingEnum minitipSettingEnum, Integer userId, Integer networkId) throws SQLException {

        // Retrieve the minitip setting value
        Integer value = minitipSettingEnum.getValueByUserIdAndNetworkId(userId, networkId);

        return value != 0;
    }


}
