package com.questy.enums;

import com.questy.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class UserToNetworkIntegerSettingEnumTest {


    @Test
    public void getSetUpdateDelete() throws SQLException {

        Integer userId = UserDao.insert(null, "test@email.com", "password", "Test", "Tester");
        Integer networkId = 1;

        // Testing default value of get
        Assert.assertEquals(UserToNetworkIntegerSettingEnum.TEST.getIfNull(), UserToNetworkIntegerSettingEnum.TEST.getValueByUserIdAndNetworkId(userId, networkId));
        Assert.assertEquals(false, UserToNetworkIntegerSettingEnum.TEST.getBooleanByUserIdAndNetworkId(userId, networkId));

        // Testing initial set
        UserToNetworkIntegerSettingEnum.TEST.setValueByUserIdAndNetworkId(userId, networkId, 1);
        Assert.assertEquals((Integer) 1, UserToNetworkIntegerSettingEnum.TEST.getValueByUserIdAndNetworkId(userId, networkId));
        Assert.assertEquals(true, UserToNetworkIntegerSettingEnum.TEST.getBooleanByUserIdAndNetworkId(userId, networkId));

        // Testing updated value
        UserToNetworkIntegerSettingEnum.TEST.setValueByUserIdAndNetworkId(userId, networkId, 2);
        Assert.assertEquals((Integer) 2, UserToNetworkIntegerSettingEnum.TEST.getValueByUserIdAndNetworkId(userId, networkId));
        Assert.assertEquals(true, UserToNetworkIntegerSettingEnum.TEST.getBooleanByUserIdAndNetworkId(userId, networkId));

        // Testing delete
        UserToNetworkIntegerSettingEnum.TEST.deleteByUserIdAndNetworkId(userId, networkId);
        Assert.assertEquals(UserToNetworkIntegerSettingEnum.TEST.getIfNull(), UserToNetworkIntegerSettingEnum.TEST.getValueByUserIdAndNetworkId(userId, networkId));

    }
}

