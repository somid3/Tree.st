package com.questy.services;

import com.questy.dao.NetworkAlphaSettingDao;
import com.questy.enums.NetworkAlphaSettingEnum;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NetworkServicesTest {

    @Test
    public void createSimpleNetwork() throws SQLException {


        List<String> qualities = new ArrayList<String>();
        String path = "create-simple";

        NetworkAlphaSettingDao.deleteByValue(null, NetworkAlphaSettingEnum.URL_PATH, path);

        qualities.add(
            "My gender is:\n" +
            "Male\n" +
            "Female\n" +
            "Decline to provide");

        qualities.add(
            "To what have extent have you supported the American Red Cross?\n" +
            "Volunteered\n" +
            "Donated money\n" +
            "Donated blood\n" +
            "Donated supplies (books, blankets, clothes, etc.)\n" +
            "Donated other services (consulting, nursing, etc.)\n" +
            "Took a class (i.e.: lifeguarding, EMT, etc.)");

        qualities.add(
            "I have been with the American Red Cross for the following number of years:\n" +
            "Less than 1 year\n" +
            "1 to 2 years\n" +
            "2 to 3 years\n" +
            "3 to 4 years\n" +
            "4 to 5 years\n" +
            "5 to 10 years\n" +
            "More than 10 years\n");

        qualities.add(
            "I have the following hobbies and interests?\n" +
            "Aerobics\n" +
            "Acting\n" +
            "Amateur Radio\n" +
            "Antiques\n" +
            "Aquariums\n" +
            "Archery\n" +
            "Architecture\n" +
            "Archaeology\n" +
            "Winthrop\n" +
            "Woburn\n" +
            "Worcester\n" +
            "Worthington\n" +
            "Wrentham\n" +
            "Yarmouth\n");

        qualities.add(
            "I am in the following age range:\n" +
            "5 to 10 years\n" +
            "10 to 12 years\n" +
            "13 to 17 years\n" +
            "18 to 20 years\n" +
            "21 to 29 years\n" +
            "30 to 30 years\n" +
            "40 to 49 years\n" +
            "50 to 59 years\n" +
            "60 or more years\n" +
            "Decline to provide");

        qualities.add(
            "My blood type is (note, you can answer this privately)\n" +
            "A+\n" +
            "A-\n" +
            "B+\n" +
            "B-\n" +
            "AB+\n" +
            "AB-\n" +
            "O+\n" +
            "O-\n" +
            "Decline to provide");

        Integer newNetworkId = NetworkServices.createSimpleNetwork(
            path,
            "American Red Cross of Eastern Massachusetts",
            "Red Cross volunteers, find one another and collaborate on improving your goals and careers!",
            qualities);


        System.out.println(
            newNetworkId
        );




    }
}
