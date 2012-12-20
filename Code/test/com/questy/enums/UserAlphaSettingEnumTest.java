package com.questy.enums;

import com.questy.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class UserAlphaSettingEnumTest {


    @Test
    public void getSetUpdateDelete() throws SQLException {

        Integer userId = UserDao.insert(null, "test@email.com", "password", "Test", "Tester");

        // Testing default value of get
        Assert.assertEquals(UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.getIfNull(), UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.getValueByUserId(userId));

        // Testing initial set
        UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.setValueByUserId(userId, "abc");
        Assert.assertEquals("abc", UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.getValueByUserId(userId));

        // Testing updated value
        UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.setValueByUserId(userId, "def");
        Assert.assertEquals("def", UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.getValueByUserId(userId));

        // Testing delete
        UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.deleteByUserId(userId);
        Assert.assertEquals(UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.getIfNull(), UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.getValueByUserId(userId));

    }
}

