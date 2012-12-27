package com.questy.services;

import com.questy.dao.NetworkDao;
import com.questy.dao.UserDao;
import com.questy.domain.Network;
import com.questy.domain.User;
import com.questy.enums.UserAlphaSettingEnum;
import com.questy.enums.UserIntegerSettingEnum;
import com.questy.utils.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class UserWebServicesTest {

   @Test
   public void addUserWithNewEmail() throws SQLException {

       Network network = NetworkDao.getById(null, 1);

       String newEmail = StringUtils.random() + "@" + StringUtils.random() + ".com";

       UserWebServices.addUser(null, network.getId(), network.getChecksum(), newEmail, "password", "Test", "Tester");

       User user = UserDao.getByEmail(null, newEmail);

       // Ensuring user's email is not confirmed
       Assert.assertEquals(false, UserIntegerSettingEnum.IS_ACCOUNT_CONFIRMED.getBooleanByUserId(user.getId()));

       // Ensuring user has been emailed a confirmation
       Assert.assertEquals((Integer) 1, UserIntegerSettingEnum.NUMBER_OF_EMAIL_CONFIRMATION_EMAILS_SENT.getValueByUserId(user.getId()));

       // Ensuring email confirmation checksum is set
       Assert.assertEquals(true, UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.getValueByUserId(user.getId()).length() > 0);

   }

}
