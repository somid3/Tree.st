package com.questy.services;

import com.questy.dao.UserToNetworkDao;
import com.questy.domain.UserToNetwork;

import javax.servlet.jsp.JspException;
import java.sql.SQLException;

public class UserToNetworkServices {

    /**
     * Identifies whether this user is a member of the provided network
     * if not, a JSP exception is thrown
     *
     */
    public static void jspValidateUserInNetwork (Integer userId, Integer networkId) throws SQLException, JspException {

        UserToNetwork utn = UserToNetworkDao.getByUserIdAndNetworkId(null, userId, networkId);
        if (utn == null)
            throw new JspException("User must belong to network");


    }
}
