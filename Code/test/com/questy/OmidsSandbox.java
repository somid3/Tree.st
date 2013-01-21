package com.questy;

import com.questy.admin.AdminServices;
import com.questy.enums.NetworkAlphaSettingEnum;
import com.questy.services.cron.CronServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OmidsSandbox {

    private static Random randomGenerator = new Random();

    public static void main(String[] args) throws Exception {


        System.out.println(NetworkAlphaSettingEnum.UI_HEADER_BACKGROUND_COLOR.getValueByNetworkId(2000));

//
//        List<String> qualities = new ArrayList<String>();
//        String path = "arcem";
//
//        NetworkAlphaSettingDao.deleteByValue(null, NetworkAlphaSettingEnum.URL_PATH, path);
//
//        qualities.add(
//            "My gender is:\n" +
//            "Male\n" +
//            "Female\n" +
//            "Decline to provide");
//
//        qualities.add(
//            "To what have extent have you supported the American Red Cross?\n" +
//            "Volunteered\n" +
//            "Donated money\n" +
//            "Donated blood\n" +
//            "Donated supplies (books, blankets, clothes, etc.)\n" +
//            "Donated other services (consulting, nursing, etc.)\n" +
//            "Took a class (i.e.: lifeguarding, EMT, etc.)");
//
//        qualities.add(
//            "I have been with the American Red Cross for the following number of years:\n" +
//            "Less than 1 year\n" +
//            "1 to 2 years\n" +
//            "2 to 3 years\n" +
//            "3 to 4 years\n" +
//            "4 to 5 years\n" +
//            "5 to 10 years\n" +
//            "More than 10 years\n");
//
//        qualities.add(
//            "I have the following hobbies and interests?\n" +
//            "Aerobics\n" +
//            "Acting\n" +
//            "Amateur Radio\n" +
//            "Antiques\n" +
//            "Aquariums\n" +
//            "Archery\n" +
//            "Architecture\n" +
//            "Archaeology\n" +
//            "Aromatherapy\n" +
//            "Astrology\n" +
//            "Astronomy\n" +
//            "Autographs\n" +
//            "Badminton\n" +
//            "Badge Collecting\n" +
//            "Baking\n" +
//            "Ballet\n" +
//            "Baseball\n" +
//            "Base Jumping\n" +
//            "Basketball\n" +
//            "Bee Keeping\n" +
//            "Bird Keeping\n" +
//            "Body Building\n" +
//            "Books\n" +
//            "Bottle Tops Collecting\n" +
//            "Bowls\n" +
//            "Bridge\n" +
//            "Bungee Jumping\n" +
//            "Butterflies\n" +
//            "Camping\n" +
//            "Canoeing\n" +
//            "Caravaning\n" +
//            "Cats\n" +
//            "Caving\n" +
//            "Ceramics\n" +
//            "Chess\n" +
//            "Classic Cars\n" +
//            "Climbing\n" +
//            "Cooking\n" +
//            "Coin Collecting\n" +
//            "Comics\n" +
//            "Computers\n" +
//            "Country Music\n" +
//            "Crochet\n" +
//            "Croquet\n" +
//            "Crosswords\n" +
//            "Cryptography\n" +
//            "Curling\n" +
//            "Cycling\n" +
//            "Dancing\n" +
//            "Digital Art\n" +
//            "Dogs\n" +
//            "Dominoes\n" +
//            "Drawing\n" +
//            "Driving\n" +
//            "Electronics\n" +
//            "Embroidery\n" +
//            "Fashion\n" +
//            "Fencing\n" +
//            "Films\n" +
//            "Fishing\n" +
//            "Fitness\n" +
//            "Fish Keeping\n" +
//            "Flower Arranging\n" +
//            "Football\n" +
//            "Fossils\n" +
//            "Frisbee\n" +
//            "Gardening\n" +
//            "Genealogy\n" +
//            "Golf\n" +
//            "Greyhound Racing\n" +
//            "Greeting Card Collecting\n" +
//            "Handball\n" +
//            "Handwriting\n" +
//            "History\n" +
//            "Hockey\n" +
//            "Home Brewing\n" +
//            "Horse Racing\n" +
//            "Horse Riding\n" +
//            "Hot Air Balooning\n" +
//            "Hot Rods\n" +
//            "House Plants\n" +
//            "Ice Skating\n" +
//            "Interior Design\n" +
//            "Jazz\n" +
//            "Jetski\n" +
//            "Jewellery\n" +
//            "Jogging\n" +
//            "Karaoke\n" +
//            "Kayaking\n" +
//            "Kit Cars\n" +
//            "Kites\n" +
//            "Knitting\n" +
//            "Koi\n" +
//            "Lacemaking\n" +
//            "Lacross\n" +
//            "Languages\n" +
//            "Magic\n" +
//            "Mah Jongg\n" +
//            "Marbles\n" +
//            "Marquetry\n" +
//            "Martial Arts\n" +
//            "Metal Detection\n" +
//            "Model Railways\n" +
//            "Model Trains\n" +
//            "Monopoly\n" +
//            "Motorbikes\n" +
//            "Mountain Biking\n" +
//            "Music-Guitar\n" +
//            "Music-Keyboard\n" +
//            "Music-Drums\n" +
//            "Netball\n" +
//            "Origami\n" +
//            "Ornithology\n" +
//            "Paintball\n" +
//            "Painting\n" +
//            "Papercraft\n" +
//            "Photography\n" +
//            "Poker\n" +
//            "Polo\n" +
//            "Poole Pottery\n" +
//            "Post Cards\n" +
//            "Pottery\n" +
//            "Psychology\n" +
//            "Quilting\n" +
//            "RC Model Boats\n" +
//            "RC Model Cars\n" +
//            "RC Model Aircrafts\n" +
//            "Reflexology\n" +
//            "Riddles\n" +
//            "Robotics\n" +
//            "Rock Music\n" +
//            "Roller Skating\n" +
//            "Rowing\n" +
//            "Russian Dolls Collecting\n" +
//            "Sailing\n" +
//            "Scrabble\n" +
//            "Sculpting\n" +
//            "Skate Boarding\n" +
//            "Skiing\n" +
//            "Skydiving\n" +
//            "Snooker\n" +
//            "Snow Biking\n" +
//            "Snowboarding\n" +
//            "Snowmobile Riding\n" +
//            "Snowshoeing\n" +
//            "Squash\n" +
//            "Stamp Collecting\n" +
//            "Surfing\n" +
//            "Swimming\n" +
//            "Tarot\n" +
//            "Telescopes\n" +
//            "Tennis\n" +
//            "Ten Pin Bowling\n" +
//            "Table Tennis\n" +
//            "Theatre\n" +
//            "Train Spotting\n" +
//            "Volleyball\n" +
//            "Walking and Hiking\n" +
//            "Wargames\n" +
//            "Warships\n" +
//            "Weather Forecasting\n" +
//            "Wind Surfing\n" +
//            "Wine Making\n" +
//            "Wall Art\n" +
//            "Water Skiing\n" +
//            "Wood Working\n" +
//            "YoYo\n" +
//            "Yoga");
//
//        qualities.add(
//            " I live in the following town in Massachusetts:\n" +
//            "Abington\n" +
//            "Acton\n" +
//            "Acushnet\n" +
//            "Adams\n" +
//            "Agawam\n" +
//            "Alford\n" +
//            "Amesbury\n" +
//            "Amherst\n" +
//            "Andover\n" +
//            "Aquinnah\n" +
//            "Arlington\n" +
//            "Ashburnham\n" +
//            "Ashby\n" +
//            "Ashfield\n" +
//            "Ashland\n" +
//            "Athol\n" +
//            "Attleboro\n" +
//            "Auburn\n" +
//            "Avon\n" +
//            "Ayer\n" +
//            "Barnstable\n" +
//            "Barre\n" +
//            "Becket\n" +
//            "Bedford\n" +
//            "Belchertown\n" +
//            "Bellingham\n" +
//            "Belmont\n" +
//            "Berkley\n" +
//            "Berlin\n" +
//            "Bernardston\n" +
//            "Beverly\n" +
//            "Billerica\n" +
//            "Blackstone\n" +
//            "Blandford\n" +
//            "Bolton\n" +
//            "Boston\n" +
//            "Bourne\n" +
//            "Boxborough\n" +
//            "Boxford\n" +
//            "Boylston\n" +
//            "Bradford\n" +
//            "Braintree\n" +
//            "Brewster\n" +
//            "Bridgewater\n" +
//            "Brimfield\n" +
//            "Brockton\n" +
//            "Brookfield\n" +
//            "Brookline\n" +
//            "Buckland\n" +
//            "Burlington\n" +
//            "Cambridge\n" +
//            "Canton\n" +
//            "Carlisle\n" +
//            "Carver\n" +
//            "Charlemont\n" +
//            "Charlton\n" +
//            "Chatham\n" +
//            "Chelmsford\n" +
//            "Chelsea\n" +
//            "Cheshire\n" +
//            "Chester\n" +
//            "Chesterfield\n" +
//            "Chicopee\n" +
//            "Chilmark\n" +
//            "Clarksburg\n" +
//            "Clinton\n" +
//            "Cohasset\n" +
//            "Colrain\n" +
//            "Concord\n" +
//            "Conway\n" +
//            "Cummington\n" +
//            "Dalton\n" +
//            "Danvers\n" +
//            "Dartmouth\n" +
//            "Dedham\n" +
//            "Deerfield\n" +
//            "Dennis\n" +
//            "Dighton\n" +
//            "Douglas\n" +
//            "Dover\n" +
//            "Dracut\n" +
//            "Dudley\n" +
//            "Dunstable\n" +
//            "Duxbury\n" +
//            "East Bridgewater\n" +
//            "East Brookfield\n" +
//            "East Longmeadow\n" +
//            "Eastham\n" +
//            "Easthampton\n" +
//            "Easton\n" +
//            "Edgartown\n" +
//            "Egremont\n" +
//            "Erving\n" +
//            "Essex\n" +
//            "Everett\n" +
//            "Fairhaven\n" +
//            "Fall River\n" +
//            "Falmouth\n" +
//            "Fitchburg\n" +
//            "Florida\n" +
//            "Foxborough\n" +
//            "Framingham\n" +
//            "Franklin\n" +
//            "Freetown\n" +
//            "Gardner\n" +
//            "Georgetown\n" +
//            "Gill\n" +
//            "Gloucester\n" +
//            "Goshen\n" +
//            "Gosnold\n" +
//            "Grafton\n" +
//            "Granby\n" +
//            "Granville\n" +
//            "Great Barrington\n" +
//            "Greenfield\n" +
//            "Groton\n" +
//            "Groveland\n" +
//            "Hadley\n" +
//            "Halifax\n" +
//            "Hamilton\n" +
//            "Hampden\n" +
//            "Hancock\n" +
//            "Hanover\n" +
//            "Hanson\n" +
//            "Hardwick\n" +
//            "Harvard\n" +
//            "Harwich\n" +
//            "Hatfield\n" +
//            "Haverhill\n" +
//            "Hawley\n" +
//            "Heath\n" +
//            "Hingham\n" +
//            "Hinsdale\n" +
//            "Holbrook\n" +
//            "Holden\n" +
//            "Holland\n" +
//            "Holliston\n" +
//            "Holyoke\n" +
//            "Hopedale\n" +
//            "Hopkinton\n" +
//            "Hubbardston\n" +
//            "Hudson\n" +
//            "Hull\n" +
//            "Huntington\n" +
//            "Hyannis\n" +
//            "Ipswich\n" +
//            "Kingston\n" +
//            "Lakeville\n" +
//            "Lancaster\n" +
//            "Lanesborough\n" +
//            "Lawrence\n" +
//            "Lee\n" +
//            "Leicester\n" +
//            "Lenox\n" +
//            "Leominster\n" +
//            "Leverett\n" +
//            "Lexington\n" +
//            "Leyden\n" +
//            "Lincoln\n" +
//            "Littleton\n" +
//            "Longmeadow\n" +
//            "Lowell\n" +
//            "Ludlow\n" +
//            "Lunenburg\n" +
//            "Lynn\n" +
//            "Lynnfield\n" +
//            "Malden\n" +
//            "Manchester By The Sea\n" +
//            "Mansfield\n" +
//            "Marblehead\n" +
//            "Marion\n" +
//            "Marlborough\n" +
//            "Marshfield\n" +
//            "Mashpee\n" +
//            "Mattapoisett\n" +
//            "Maynard\n" +
//            "Medfield\n" +
//            "Medford\n" +
//            "Medway\n" +
//            "Melrose\n" +
//            "Mendon\n" +
//            "Merrimac\n" +
//            "Methuen\n" +
//            "Middleborough\n" +
//            "Middlefield\n" +
//            "Middleton\n" +
//            "Milford\n" +
//            "Millbury\n" +
//            "Millis\n" +
//            "Millville\n" +
//            "Milton\n" +
//            "Monroe\n" +
//            "Monson\n" +
//            "Montague\n" +
//            "Monterey\n" +
//            "Montgomery\n" +
//            "Mount Washington\n" +
//            "Nahant\n" +
//            "Nantucket\n" +
//            "Natick\n" +
//            "Needham\n" +
//            "New Ashford\n" +
//            "New Bedford\n" +
//            "New Braintree\n" +
//            "New Marlborough\n" +
//            "New Salem\n" +
//            "Newbury\n" +
//            "Newburyport\n" +
//            "Newton\n" +
//            "Norfolk\n" +
//            "North Adams\n" +
//            "North Andover\n" +
//            "North Attleborough\n" +
//            "North Brookfield\n" +
//            "North Reading\n" +
//            "Northampton\n" +
//            "Northborough\n" +
//            "Northbridge\n" +
//            "Northfield\n" +
//            "Norton\n" +
//            "Norwell\n" +
//            "Norwood\n" +
//            "Oak Bluffs\n" +
//            "Oakham\n" +
//            "Orange\n" +
//            "Orleans\n" +
//            "Otis\n" +
//            "Oxford\n" +
//            "Palmer\n" +
//            "Paxton\n" +
//            "Peabody\n" +
//            "Pelham\n" +
//            "Pembroke\n" +
//            "Pepperell\n" +
//            "Peru\n" +
//            "Petersham\n" +
//            "Phillipston\n" +
//            "Pittsfield\n" +
//            "Plainfield\n" +
//            "Plainville\n" +
//            "Plymouth\n" +
//            "Plympton\n" +
//            "Princeton\n" +
//            "Provincetown\n" +
//            "Quincy\n" +
//            "Randolph\n" +
//            "Raynham\n" +
//            "Reading\n" +
//            "Rehoboth\n" +
//            "Revere\n" +
//            "Richmond\n" +
//            "Rochester\n" +
//            "Rockland\n" +
//            "Rockport\n" +
//            "Rowe\n" +
//            "Rowley\n" +
//            "Royalston\n" +
//            "Russell\n" +
//            "Rutland\n" +
//            "Salem\n" +
//            "Salisbury\n" +
//            "Sandisfield\n" +
//            "Sandwich\n" +
//            "Saugus\n" +
//            "Savoy\n" +
//            "Scituate\n" +
//            "Seekonk\n" +
//            "Sharon\n" +
//            "Sheffield\n" +
//            "Shelburne\n" +
//            "Sherborn\n" +
//            "Shirley\n" +
//            "Shrewsbury\n" +
//            "Shutesbury\n" +
//            "Somerset\n" +
//            "Somerville\n" +
//            "South Hadley\n" +
//            "Southampton\n" +
//            "Southborough\n" +
//            "Southbridge\n" +
//            "Southwick\n" +
//            "Spencer\n" +
//            "Springfield\n" +
//            "Sterling\n" +
//            "Stockbridge\n" +
//            "Stoneham\n" +
//            "Stoughton\n" +
//            "Stow\n" +
//            "Sturbridge\n" +
//            "Sudbury\n" +
//            "Sunderland\n" +
//            "Sutton\n" +
//            "Swampscott\n" +
//            "Swansea\n" +
//            "Taunton\n" +
//            "Templeton\n" +
//            "Tewksbury\n" +
//            "Tisbury\n" +
//            "Tolland\n" +
//            "Topsfield\n" +
//            "Townsend\n" +
//            "Truro\n" +
//            "Tyngsborough\n" +
//            "Tyringham\n" +
//            "Upton\n" +
//            "Uxbridge\n" +
//            "Wakefield\n" +
//            "Wales\n" +
//            "Walpole\n" +
//            "Waltham\n" +
//            "Ware\n" +
//            "Wareham\n" +
//            "Warren\n" +
//            "Warwick\n" +
//            "Washington\n" +
//            "Watertown\n" +
//            "Wayland\n" +
//            "Webster\n" +
//            "Wellesley\n" +
//            "Wellfleet\n" +
//            "Wendell\n" +
//            "Wenham\n" +
//            "West Boylston\n" +
//            "West Bridgewater\n" +
//            "West Brookfield\n" +
//            "West Newbury\n" +
//            "West Springfield\n" +
//            "West Stockbridge\n" +
//            "West Tisbury\n" +
//            "Westborough\n" +
//            "Westfield\n" +
//            "Westford\n" +
//            "Westhampton\n" +
//            "Westminster\n" +
//            "Weston\n" +
//            "Westport\n" +
//            "Westwood\n" +
//            "Weymouth\n" +
//            "Whately\n" +
//            "Whitman\n" +
//            "Wilbraham\n" +
//            "Williamsburg\n" +
//            "Williamstown\n" +
//            "Wilmington\n" +
//            "Winchendon\n" +
//            "Winchester\n" +
//            "Windsor\n" +
//            "Winthrop\n" +
//            "Woburn\n" +
//            "Worcester\n" +
//            "Worthington\n" +
//            "Wrentham\n" +
//            "Yarmouth\n");
//
//        qualities.add(
//            "I am in the following age range:\n" +
//            "5 to 10 years\n" +
//            "10 to 12 years\n" +
//            "13 to 17 years\n" +
//            "18 to 20 years\n" +
//            "21 to 29 years\n" +
//            "30 to 30 years\n" +
//            "40 to 49 years\n" +
//            "50 to 59 years\n" +
//            "60 or more years\n" +
//            "Decline to provide");
//
//        qualities.add(
//            "My blood type is (note, you can answer this privately)\n" +
//            "A+\n" +
//            "A-\n" +
//            "B+\n" +
//            "B-\n" +
//            "AB+\n" +
//            "AB-\n" +
//            "O+\n" +
//            "O-\n" +
//            "Decline to provide");
//
//        Integer newNetworkId = NetworkServices.createSimpleNetwork(
//            path,
//            "American Red Cross of Eastern Massachusetts",
//            "Red Cross volunteers, find one another and collaborate on improving your goals and careers!",
//            qualities);
//
//
//        System.out.println(
//            newNetworkId
//        );


//        Integer userId = 3;
//          Integer networkId = 2000;
//          Integer smartGroupRef = 113;
//          Integer sharedItemRef = 4;
//          Integer sharedCommentRef = SharedComment.ANY_SHARED_COMMENT_REF;
//          SharedVoteEnum vote = SharedVoteEnum.UP;
//          SharedVote sharedVote = null;
//          SharedVotable sharedVotable = null;
//
//          // Deleting all inactive votes
//          SharedVoteDao.deleteInactive();
//
//          // Changing vote of shared votable
//          SharedVoteServices.changeVote(
//                  userId,
//                  networkId,
//                  smartGroupRef,
//                  sharedItemRef,
//                  sharedCommentRef,
//                  vote);


//        System.out.println(
//            UserToSmartGroupDao.deleteInactiveByNetworkId(null, 2003)
//        );


//        Foods.create(3);




//        GeneralEmailSender.CVSToDatabase("/Users/omid/Desktop/scrapedaddresses/Universities.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/6600-assoc.csv");

//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/11500-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/12000-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/12500-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/13000-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/13500-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/14000-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/14500-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/15000-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/15500-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/16000-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/16500-assoc.csv");
//




    }




    public static void populate() throws Exception {

        // Total number of users and actions
        Integer totalUsers = 100;
        Integer thisPerUsers = 10;

        // List of networks to sign up users to
        List<Integer> networkIds = new ArrayList<Integer>();
        networkIds.add(1);


        // Create users
        AdminServices.populateUsers(totalUsers, networkIds);

        // Answer randomly
        AdminServices.randomAnswers(totalUsers, thisPerUsers);

        // Refresh all smart groups
        CronServices.calledHourlyPopulateSmartGroups();

        // Write shared items and comments
        AdminServices.randomSharedItemsAndComments(totalUsers, thisPerUsers, 3);

        // Adding connections
        AdminServices.randomConnections(totalUsers, thisPerUsers);

    }


}
