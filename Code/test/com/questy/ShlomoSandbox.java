package com.questy;

import com.questy.admin.AdminServices;
import com.questy.admin.MIT;
import com.questy.admin.dao.GeneralEmailDao;
import com.questy.admin.domain.GeneralEmail;
import com.questy.admin.scrapper.GeneralEmailSender;
import com.questy.dao.AppResourceDao;
import com.questy.domain.AppResource;
import com.questy.enums.AppEnum;
import com.questy.enums.AppResourceTypeEnum;
import com.questy.enums.EmailNotificationRateEnum;
import com.questy.services.AppResourceServices;
import com.questy.services.cron.CronServices;

import java.lang.System;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShlomoSandbox {

    private static Random randomGenerator = new Random();

    public static void main(String[] args) throws Exception {

        //GeneralEmail c = new GeneralEmail("John","John@john.com","Microsoft","John.com");
        //GeneralEmailDao.insert(null,c);
        GeneralEmailSender.sendEmailsTo("C:\\Users\\Shlomo\\Desktop\\test.csv");

    }

}