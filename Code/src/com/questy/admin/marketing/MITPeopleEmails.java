package com.questy.admin.marketing;

import com.questy.admin.dao.MITEmailDao;
import com.questy.admin.domain.MITEmail;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MITPeopleEmails {

    public static void main(String[] args) throws IOException, SQLException {


        File dir = new File("/Users/omid/Dropbox/Projects/MIT Directory Files/SINGLE_MATCHES");

        // Reading all files
        String fileContents = null;

        Pattern deptPattern =      Pattern.compile("department: (.*?)\n");
        Pattern emailPattern =     Pattern.compile("     email: <A HREF=\"mailto:(.*?)\">");
        Pattern namePatter =       Pattern.compile("      name: (.*?)\n");
        Pattern schoolPattern =    Pattern.compile("    school: (.*?)\n");
        Pattern titlePattern =     Pattern.compile("     title: (.*?)\n");
        Pattern yearPattern =      Pattern.compile("      year: (.*?)\n");


        String found = null;
        for (String file : dir.list()) {

            // Retrieving file contents
            fileContents = FileUtils.readFileToString(new File(dir.getAbsolutePath() + "/" + file), "UTF-8");

            Matcher deptMatcher = deptPattern.matcher(fileContents);
            Matcher emailMatcher = emailPattern.matcher(fileContents);
            Matcher nameMatcher = namePatter.matcher(fileContents);
            Matcher schoolMatcher = schoolPattern.matcher(fileContents);
            Matcher titleMatcher = titlePattern.matcher(fileContents);
            Matcher yearMatcher = yearPattern.matcher(fileContents);

            MITEmail mitEmail = new MITEmail();

            // Extracting aliases
            while (deptMatcher.find())                 {  mitEmail.setDepartment(deptMatcher.group(1)); }
            while (emailMatcher.find())                {  mitEmail.setEmail(emailMatcher.group(1)); }
            while (nameMatcher.find())                 {  mitEmail.setName(nameMatcher.group(1)); }
            while (schoolMatcher.find())               {  mitEmail.setSchool(schoolMatcher.group(1)); }
            while (titleMatcher.find())                {  mitEmail.setTitle(titleMatcher.group(1)); }
            while (yearMatcher.find())                 {  mitEmail.setYear(yearMatcher.group(1)); }

            // Inserting MIT email
            MITEmailDao.insert(
                null,
                mitEmail.getDepartment(),
                mitEmail.getEmail(),
                mitEmail.getName(),
                mitEmail.getSchool(),
                mitEmail.getTitle(),
                mitEmail.getYear()
            );

            System.out.println(mitEmail.getEmail());
        }
    }
}
