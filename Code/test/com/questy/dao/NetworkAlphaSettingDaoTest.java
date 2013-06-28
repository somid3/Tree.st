package com.questy.dao;

import com.questy.domain.NetworkAlphaSetting;
import com.questy.enums.NetworkAlphaSettingEnum;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class NetworkAlphaSettingDaoTest {

    @Test
    public void getByValueAndSetting() throws SQLException {

        NetworkAlphaSetting setting = NetworkAlphaSettingDao.getByValueAndSettingEnum(null, "mit", NetworkAlphaSettingEnum.URL_PATH);

        Assert.assertEquals(new Integer(2000), setting.getNetworkId());

    }

}
