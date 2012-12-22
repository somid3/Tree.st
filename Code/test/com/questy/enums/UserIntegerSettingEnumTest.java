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
        Assert.assertEquals(UserIntegerSettingEnum.TEST.getIfNull(), UserIntegerSettingEnum.TEST.getValueByUserId(userId));
        Assert.assertEquals(false, UserIntegerSettingEnum.TEST.getBooleanByUserId(userId));

        // Testing initial set
        UserIntegerSettingEnum.TEST.setValueByUserId(userId, 1);
        Assert.assertEquals((Integer) 1, UserIntegerSettingEnum.TEST.getValueByUserId(userId));
        Assert.assertEquals(true, UserIntegerSettingEnum.TEST.getBooleanByUserId(userId));

        // Testing updated value
        UserIntegerSettingEnum.TEST.setValueByUserId(userId, 2);
        Assert.assertEquals((Integer) 2, UserIntegerSettingEnum.TEST.getValueByUserId(userId));
        Assert.assertEquals(true, UserIntegerSettingEnum.TEST.getBooleanByUserId(userId));

        // Testing delete
        UserIntegerSettingEnum.TEST.deleteByUserId(userId);
        Assert.assertEquals(UserIntegerSettingEnum.TEST.getIfNull(), UserIntegerSettingEnum.TEST.getValueByUserId(userId));

    }
}

