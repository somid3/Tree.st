package com.questy.enums;

import com.questy.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class UserIntegerSettingEnumTest {


    @Test
    public void getSetUpdateDelete() throws SQLException {

        Integer userId = UserDao.insert(null, "test@email.com", "password", "Test", "Tester");

        // Testing default value of get
        Assert.assertEquals(UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.getIfNull(), UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.getValueByUserId(userId));
        Assert.assertEquals(false, UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.getBooleanByUserId(userId));

        // Testing initial set
        UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.setValueByUserId(userId, 1);
        Assert.assertEquals((Integer) 1, UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.getValueByUserId(userId));
        Assert.assertEquals(true, UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.getBooleanByUserId(userId));

        // Testing updated value
        UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.setValueByUserId(userId, 2);
        Assert.assertEquals((Integer) 2, UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.getValueByUserId(userId));
        Assert.assertEquals(true, UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.getBooleanByUserId(userId));

        // Testing delete
        UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.deleteByUserId(userId);
        Assert.assertEquals(UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.getIfNull(), UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.getValueByUserId(userId));

    }
}

