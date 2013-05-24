package com.questy.admin;

import com.questy.dao.*;
import com.questy.domain.*;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.enums.RoleEnum;
import com.questy.enums.SmartGroupVisibilityEnum;
import com.questy.helpers.Tuple;
import com.questy.helpers.UIException;
import com.questy.services.*;
import com.questy.services.cron.CronServices;
import com.questy.utils.Vars;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DemoServices {

    private static boolean log = true;

    private static Random randomGenerator = new Random();
    private static String randomText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempus elit eu tellus consectetur laoreet. Phasellus urna libero, sollicitudin quis rutrum id, fringilla id elit. Vestibulum bibendum luctus est euismod blandit. Donec non sem eros. Etiam bibendum cursus ligula eget tristique. Phasellus quis malesuada dui. Nunc turpis enim, tristique ut lacinia non, sodales vitae nisl. Donec viverra lacinia tortor, varius placerat odio sollicitudin eleifend. Nunc et adipiscing lacus. Etiam facilisis purus urna, in pretium purus. Nullam accumsan, lorem id pharetra sodales, nibh elit vulputate tellus, consectetur gravida erat massa eget quam. Cras laoreet magna at justo tristique in hendrerit ante interdum. Etiam quis dui ante. Cras et nisi nisl, quis tincidunt mi.";
    private static String[] names = {

        "Aaron         ",
        "Abe           ",
        "Abraham       ",
        "Abram         ",
        "Adam          ",
        "Adolph        ",
        "Adrian        ",
        "Ahmad         ",
        "Al            ",
        "Alan          ",
        "Albert        ",
        "Alberto       ",
        "Alden         ",
        "Aldo          ",
        "Alec          ",
        "Alejandro     ",
        "Alex          ",
        "Alexander     ",
        "Alexis        ",
        "Alfonso       ",
        "Alfonzo       ",
        "Alfred        ",
        "Alfredo       ",
        "Ali           ",
        "Allan         ",
        "Allen         ",
        "Alonso        ",
        "Alonzo        ",
        "Alphonse      ",
        "Alphonso      ",
        "Alton         ",
        "Alva          ",
        "Alvaro        ",
        "Alvin         ",
        "Amado         ",
        "Ambrose       ",
        "Amos          ",
        "Anderson      ",
        "Andre         ",
        "Andrea        ",
        "Andreas       ",
        "Andres        ",
        "Andrew        ",
        "Andy          ",
        "Angel         ",
        "Angelo        ",
        "Anibal        ",
        "Anthony       ",
        "Antione       ",
        "Antoine       ",
        "Anton         ",
        "Antone        ",
        "Antonia       ",
        "Antonio       ",
        "Antony        ",
        "Antwan        ",
        "Archie        ",
        "Arden         ",
        "Ariel         ",
        "Arlen         ",
        "Arlie         ",
        "Armando       ",
        "Arnold        ",
        "Arnoldo       ",
        "Arnulfo       ",
        "Aron          ",
        "Arron         ",
        "Art           ",
        "Arthur        ",
        "Arturo        ",
        "Asa           ",
        "Ashley        ",
        "Aubrey        ",
        "August        ",
        "Augustine     ",
        "Augustus      ",
        "Aurelio       ",
        "Austin        ",
        "Avery         ",
        "Barney        ",
        "Barrett       ",
        "Barry         ",
        "Bart          ",
        "Barton        ",
        "Basil         ",
        "Beau          ",
        "Ben           ",
        "Benedict      ",
        "Benito        ",
        "Benjamin      ",
        "Bennett       ",
        "Bennie        ",
        "Benny         ",
        "Benton        ",
        "Bernard       ",
        "Bernardo      ",
        "Bernie        ",
        "Berry         ",
        "Bert          ",
        "Bertram       ",
        "Bill          ",
        "Billie        ",
        "Billy         ",
        "Blaine        ",
        "Blair         ",
        "Blake         ",
        "Bo            ",
        "Bob           ",
        "Bobbie        ",
        "Bobby         ",
        "Booker        ",
        "Boris         ",
        "Boyce         ",
        "Boyd          ",
        "Brad          ",
        "Bradford      ",
        "Bradley       ",
        "Bradly        ",
        "Brady         ",
        "Brain         ",
        "Branden       ",
        "Brandon       ",
        "Brant         ",
        "Brendan       ",
        "Brendon       ",
        "Brent         ",
        "Brenton       ",
        "Bret          ",
        "Brett         ",
        "Brian         ",
        "Brice         ",
        "Britt         ",
        "Brock         ",
        "Broderick     ",
        "Brooks        ",
        "Bruce         ",
        "Bruno         ",
        "Bryan         ",
        "Bryant        ",
        "Bryce         ",
        "Bryon         ",
        "Buck          ",
        "Bud           ",
        "Buddy         ",
        "Buford        ",
        "Burl          ",
        "Burt          ",
        "Burton        ",
        "Buster        ",
        "Byron         ",
        "Caleb         ",
        "Calvin        ",
        "Cameron       ",
        "Carey         ",
        "Carl          ",
        "Carlo         ",
        "Carlos        ",
        "Carlton       ",
        "Carmelo       ",
        "Carmen        ",
        "Carmine       ",
        "Carol         ",
        "Carrol        ",
        "Carroll       ",
        "Carson        ",
        "Carter        ",
        "Cary          ",
        "Casey         ",
        "Cecil         ",
        "Cedric        ",
        "Cedrick       ",
        "Cesar         ",
        "Chad          ",
        "Chadwick      ",
        "Chance        ",
        "Chang         ",
        "Charles       ",
        "Charley       ",
        "Charlie       ",
        "Chas          ",
        "Chase         ",
        "Chauncey      ",
        "Chester       ",
        "Chet          ",
        "Chi           ",
        "Chong         ",
        "Chris         ",
        "Christian     ",
        "Christoper    ",
        "Christopher   ",
        "Chuck         ",
        "Chung         ",
        "Clair         ",
        "Clarence      ",
        "Clark         ",
        "Claud         ",
        "Claude        ",
        "Claudio       ",
        "Clay          ",
        "Clayton       ",
        "Clement       ",
        "Clemente      ",
        "Cleo          ",
        "Cletus        ",
        "Cleveland     ",
        "Cliff         ",
        "Clifford      ",
        "Clifton       ",
        "Clint         ",
        "Clinton       ",
        "Clyde         ",
        "Cody          ",
        "Colby         ",
        "Cole          ",
        "Coleman       ",
        "Colin         ",
        "Collin        ",
        "Colton        ",
        "Columbus      ",
        "Connie        ",
        "Conrad        ",
        "Cordell       ",
        "Corey         ",
        "Cornelius     ",
        "Cornell       ",
        "Cortez        ",
        "Cory          ",
        "Courtney      ",
        "Coy           ",
        "Craig         ",
        "Cristobal     ",
        "Cristopher    ",
        "Cruz          ",
        "Curt          ",
        "Curtis        ",
        "Cyril         ",
        "Cyrus         ",
        "Dale          ",
        "Dallas        ",
        "Dalton        ",
        "Damian        ",
        "Damien        ",
        "Damion        ",
        "Damon         ",
        "Dan           ",
        "Dana          ",
        "Dane          ",
        "Danial        ",
        "Daniel        ",
        "Danilo        ",
        "Dannie        ",
        "Danny         ",
        "Dante         ",
        "Darell        ",
        "Daren         ",
        "Darin         ",
        "Dario         ",
        "Darius        ",
        "Darnell       ",
        "Daron         ",
        "Darrel        ",
        "Darrell       ",
        "Darren        ",
        "Darrick       ",
        "Darrin        ",
        "Darron        ",
        "Darryl        ",
        "Darwin        ",
        "Daryl         ",
        "Dave          ",
        "David         ",
        "Davis         ",
        "Dean          ",
        "Deandre       ",
        "Deangelo      ",
        "Dee           ",
        "Del           ",
        "Delbert       ",
        "Delmar        ",
        "Delmer        ",
        "Demarcus      ",
        "Demetrius     ",
        "Denis         ",
        "Dennis        ",
        "Denny         ",
        "Denver        ",
        "Deon          ",
        "Derek         ",
        "Derick        ",
        "Derrick       ",
        "Deshawn       ",
        "Desmond       ",
        "Devin         ",
        "Devon         ",
        "Dewayne       ",
        "Dewey         ",
        "Dewitt        ",
        "Dexter        ",
        "Dick          ",
        "Diego         ",
        "Dillon        ",
        "Dino          ",
        "Dion          ",
        "Dirk          ",
        "Domenic       ",
        "Domingo       ",
        "Dominic       ",
        "Dominick      ",
        "Dominique     ",
        "Don           ",
        "Donald        ",
        "Dong          ",
        "Donn          ",
        "Donnell       ",
        "Donnie        ",
        "Donny         ",
        "Donovan       ",
        "Donte         ",
        "Dorian        ",
        "Dorsey        ",
        "Doug          ",
        "Douglas       ",
        "Douglass      ",
        "Doyle         ",
        "Drew          ",
        "Duane         ",
        "Dudley        ",
        "Duncan        ",
        "Dustin        ",
        "Dusty         ",
        "Dwain         ",
        "Dwayne        ",
        "Dwight        ",
        "Dylan         ",
        "Earl          ",
        "Earle         ",
        "Earnest       ",
        "Ed            ",
        "Eddie         ",
        "Eddy          ",
        "Edgar         ",
        "Edgardo       ",
        "Edison        ",
        "Edmond        ",
        "Edmund        ",
        "Edmundo       ",
        "Eduardo       ",
        "Edward        ",
        "Edwardo       ",
        "Edwin         ",
        "Efrain        ",
        "Efren         ",
        "Elbert        ",
        "Elden         ",
        "Eldon         ",
        "Eldridge      ",
        "Eli           ",
        "Elias         ",
        "Elijah        ",
        "Eliseo        ",
        "Elisha        ",
        "Elliot        ",
        "Elliott       ",
        "Ellis         ",
        "Ellsworth     ",
        "Elmer         ",
        "Elmo          ",
        "Eloy          ",
        "Elroy         ",
        "Elton         ",
        "Elvin         ",
        "Elvis         ",
        "Elwood        ",
        "Emanuel       ",
        "Emerson       ",
        "Emery         ",
        "Emil          ",
        "Emile         ",
        "Emilio        ",
        "Emmanuel      ",
        "Emmett        ",
        "Emmitt        ",
        "Emory         ",
        "Enoch         ",
        "Enrique       ",
        "Erasmo        ",
        "Eric          ",
        "Erich         ",
        "Erick         ",
        "Erik          ",
        "Erin          ",
        "Ernest        ",
        "Ernesto       ",
        "Ernie         ",
        "Errol         ",
        "Ervin         ",
        "Erwin         ",
        "Esteban       ",
        "Ethan         ",
        "Eugene        ",
        "Eugenio       ",
        "Eusebio       ",
        "Evan          ",
        "Everett       ",
        "Everette      ",
        "Ezekiel       ",
        "Ezequiel      ",
        "Ezra          ",
        "Fabian        ",
        "Faustino      ",
        "Fausto        ",
        "Federico      ",
        "Felipe        ",
        "Felix         ",
        "Felton        ",
        "Ferdinand     ",
        "Fermin        ",
        "Fernando      ",
        "Fidel         ",
        "Filiberto     ",
        "Fletcher      ",
        "Florencio     ",
        "Florentino    ",
        "Floyd         ",
        "Forest        ",
        "Forrest       ",
        "Foster        ",
        "Frances       ",
        "Francesco     ",
        "Francis       ",
        "Francisco     ",
        "Frank         ",
        "Frankie       ",
        "Franklin      ",
        "Franklyn      ",
        "Fred          ",
        "Freddie       ",
        "Freddy        ",
        "Frederic      ",
        "Frederick     ",
        "Fredric       ",
        "Fredrick      ",
        "Freeman       ",
        "Fritz         ",
        "Gabriel       ",
        "Gail          ",
        "Gale          ",
        "Galen         ",
        "Garfield      ",
        "Garland       ",
        "Garret        ",
        "Garrett       ",
        "Garry         ",
        "Garth         ",
        "Gary          ",
        "Gaston        ",
        "Gavin         ",
        "Gayle         ",
        "Gaylord       ",
        "Genaro        ",
        "Gene          ",
        "Geoffrey      ",
        "George        ",
        "Gerald        ",
        "Geraldo       ",
        "Gerard        ",
        "Gerardo       ",
        "German        ",
        "Gerry         ",
        "Gil           ",
        "Gilbert       ",
        "Gilberto      ",
        "Gino          ",
        "Giovanni      ",
        "Giuseppe      ",
        "Glen          ",
        "Glenn         ",
        "Gonzalo       ",
        "Gordon        ",
        "Grady         ",
        "Graham        ",
        "Graig         ",
        "Grant         ",
        "Granville     ",
        "Greg          ",
        "Gregg         ",
        "Gregorio      ",
        "Gregory       ",
        "Grover        ",
        "Guadalupe     ",
        "Guillermo     ",
        "Gus           ",
        "Gustavo       ",
        "Guy           ",
        "Hai           ",
        "Hal           ",
        "Hank          ",
        "Hans          ",
        "Harlan        ",
        "Harland       ",
        "Harley        ",
        "Harold        ",
        "Harris        ",
        "Harrison      ",
        "Harry         ",
        "Harvey        ",
        "Hassan        ",
        "Hayden        ",
        "Haywood       ",
        "Heath         ",
        "Hector        ",
        "Henry         ",
        "Herb          ",
        "Herbert       ",
        "Heriberto     ",
        "Herman        ",
        "Herschel      ",
        "Hershel       ",
        "Hilario       ",
        "Hilton        ",
        "Hipolito      ",
        "Hiram         ",
        "Hobert        ",
        "Hollis        ",
        "Homer         ",
        "Hong          ",
        "Horace        ",
        "Horacio       ",
        "Hosea         ",
        "Houston       ",
        "Howard        ",
        "Hoyt          ",
        "Hubert        ",
        "Huey          ",
        "Hugh          ",
        "Hugo          ",
        "Humberto      ",
        "Hung          ",
        "Hunter        ",
        "Hyman         ",
        "Ian           ",
        "Ignacio       ",
        "Ike           ",
        "Ira           ",
        "Irvin         ",
        "Irving        ",
        "Irwin         ",
        "Isaac         ",
        "Isaiah        ",
        "Isaias        ",
        "Isiah         ",
        "Isidro        ",
        "Ismael        ",
        "Israel        ",
        "Isreal        ",
        "Issac         ",
        "Ivan          ",
        "Ivory         ",
        "Jacinto       ",
        "Jack          ",
        "Jackie        ",
        "Jackson       ",
        "Jacob         ",
        "Jacques       ",
        "Jae           ",
        "Jaime         ",
        "Jake          ",
        "Jamaal        ",
        "Jamal         ",
        "Jamar         ",
        "Jame          ",
        "Jamel         ",
        "James         ",
        "Jamey         ",
        "Jamie         ",
        "Jamison       ",
        "Jan           ",
        "Jared         ",
        "Jarod         ",
        "Jarred        ",
        "Jarrett       ",
        "Jarrod        ",
        "Jarvis        ",
        "Jason         ",
        "Jasper        ",
        "Javier        ",
        "Jay           ",
        "Jayson        ",
        "Jc            ",
        "Jean          ",
        "Jed           ",
        "Jeff          ",
        "Jefferey      ",
        "Jefferson     ",
        "Jeffery       ",
        "Jeffrey       ",
        "Jeffry        ",
        "Jerald        ",
        "Jeramy        ",
        "Jere          ",
        "Jeremiah      ",
        "Jeremy        ",
        "Jermaine      ",
        "Jerold        ",
        "Jerome        ",
        "Jeromy        ",
        "Jerrell       ",
        "Jerrod        ",
        "Jerrold       ",
        "Jerry         ",
        "Jess          ",
        "Jesse         ",
        "Jessie        ",
        "Jesus         ",
        "Jewel         ",
        "Jewell        ",
        "Jim           ",
        "Jimmie        ",
        "Jimmy         ",
        "Joan          ",
        "Joaquin       ",
        "Jody          ",
        "Joe           ",
        "Joel          ",
        "Joesph        ",
        "Joey          ",
        "John          ",
        "Johnathan     ",
        "Johnathon     ",
        "Johnie        ",
        "Johnnie       ",
        "Johnny        ",
        "Johnson       ",
        "Jon           ",
        "Jonah         ",
        "Jonas         ",
        "Jonathan      ",
        "Jonathon      ",
        "Jordan        ",
        "Jordon        ",
        "Jorge         ",
        "Jose          ",
        "Josef         ",
        "Joseph        ",
        "Josh          ",
        "Joshua        ",
        "Josiah        ",
        "Jospeh        ",
        "Josue         ",
        "Juan          ",
        "Jude          ",
        "Judson        ",
        "Jules         ",
        "Julian        ",
        "Julio         ",
        "Julius        ",
        "Junior        ",
        "Justin        ",
        "Kareem        ",
        "Karl          ",
        "Kasey         ",
        "Keenan        ",
        "Keith         ",
        "Kelley        ",
        "Kelly         ",
        "Kelvin        ",
        "Ken           ",
        "Kendall       ",
        "Kendrick      ",
        "Keneth        ",
        "Kenneth       ",
        "Kennith       ",
        "Kenny         ",
        "Kent          ",
        "Kenton        ",
        "Kermit        ",
        "Kerry         ",
        "Keven         ",
        "Kevin         ",
        "Kieth         ",
        "Kim           ",
        "King          ",
        "Kip           ",
        "Kirby         ",
        "Kirk          ",
        "Korey         ",
        "Kory          ",
        "Kraig         ",
        "Kris          ",
        "Kristofer     ",
        "Kristopher    ",
        "Kurt          ",
        "Kurtis        ",
        "Kyle          ",
        "Lacy          ",
        "Lamar         ",
        "Lamont        ",
        "Lance         ",
        "Landon        ",
        "Lane          ",
        "Lanny         ",
        "Larry         ",
        "Lauren        ",
        "Laurence      ",
        "Lavern        ",
        "Laverne       ",
        "Lawerence     ",
        "Lawrence      ",
        "Lazaro        ",
        "Leandro       ",
        "Lee           ",
        "Leif          ",
        "Leigh         ",
        "Leland        ",
        "Lemuel        ",
        "Len           ",
        "Lenard        ",
        "Lenny         ",
        "Leo           ",
        "Leon          ",
        "Leonard       ",
        "Leonardo      ",
        "Leonel        ",
        "Leopoldo      ",
        "Leroy         ",
        "Les           ",
        "Lesley        ",
        "Leslie        ",
        "Lester        ",
        "Levi          ",
        "Lewis         ",
        "Lincoln       ",
        "Lindsay       ",
        "Lindsey       ",
        "Lino          ",
        "Linwood       ",
        "Lionel        ",
        "Lloyd         ",
        "Logan         ",
        "Lon           ",
        "Long          ",
        "Lonnie        ",
        "Lonny         ",
        "Loren         ",
        "Lorenzo       ",
        "Lou           ",
        "Louie         ",
        "Louis         ",
        "Lowell        ",
        "Loyd          ",
        "Lucas         ",
        "Luciano       ",
        "Lucien        ",
        "Lucio         ",
        "Lucius        ",
        "Luigi         ",
        "Luis          ",
        "Luke          ",
        "Lupe          ",
        "Luther        ",
        "Lyle          ",
        "Lyman         ",
        "Lyndon        ",
        "Lynn          ",
        "Lynwood       ",
        "Mac           ",
        "Mack          ",
        "Major         ",
        "Malcolm       ",
        "Malcom        ",
        "Malik         ",
        "Man           ",
        "Manual        ",
        "Manuel        ",
        "Marc          ",
        "Marcel        ",
        "Marcelino     ",
        "Marcellus     ",
        "Marcelo       ",
        "Marco         ",
        "Marcos        ",
        "Marcus        ",
        "Margarito     ",
        "Maria         ",
        "Mariano       ",
        "Mario         ",
        "Marion        ",
        "Mark          ",
        "Markus        ",
        "Marlin        ",
        "Marlon        ",
        "Marquis       ",
        "Marshall      ",
        "Martin        ",
        "Marty         ",
        "Marvin        ",
        "Mary          ",
        "Mason         ",
        "Mathew        ",
        "Matt          ",
        "Matthew       ",
        "Maurice       ",
        "Mauricio      ",
        "Mauro         ",
        "Max           ",
        "Maximo        ",
        "Maxwell       ",
        "Maynard       ",
        "Mckinley      ",
        "Mel           ",
        "Melvin        ",
        "Merle         ",
        "Merlin        ",
        "Merrill       ",
        "Mervin        ",
        "Micah         ",
        "Michael       ",
        "Michal        ",
        "Michale       ",
        "Micheal       ",
        "Michel        ",
        "Mickey        ",
        "Miguel        ",
        "Mike          ",
        "Mikel         ",
        "Milan         ",
        "Miles         ",
        "Milford       ",
        "Millard       ",
        "Milo          ",
        "Milton        ",
        "Minh          ",
        "Miquel        ",
        "Mitch         ",
        "Mitchel       ",
        "Mitchell      ",
        "Modesto       ",
        "Mohamed       ",
        "Mohammad      ",
        "Mohammed      ",
        "Moises        ",
        "Monroe        ",
        "Monte         ",
        "Monty         ",
        "Morgan        ",
        "Morris        ",
        "Morton        ",
        "Mose          ",
        "Moses         ",
        "Moshe         ",
        "Murray        ",
        "Myles         ",
        "Myron         ",
        "Napoleon      ",
        "Nathan        ",
        "Nathanael     ",
        "Nathanial     ",
        "Nathaniel     ",
        "Neal          ",
        "Ned           ",
        "Neil          ",
        "Nelson        ",
        "Nestor        ",
        "Neville       ",
        "Newton        ",
        "Nicholas      ",
        "Nick          ",
        "Nickolas      ",
        "Nicky         ",
        "Nicolas       ",
        "Nigel         ",
        "Noah          ",
        "Noble         ",
        "Noe           ",
        "Noel          ",
        "Nolan         ",
        "Norbert       ",
        "Norberto      ",
        "Norman        ",
        "Normand       ",
        "Norris        ",
        "Numbers       ",
        "Octavio       ",
        "Odell         ",
        "Odis          ",
        "Olen          ",
        "Olin          ",
        "Oliver        ",
        "Ollie         ",
        "Omar          ",
        "Omer          ",
        "Oren          ",
        "Orlando       ",
        "Orval         ",
        "Orville       ",
        "Oscar         ",
        "Osvaldo       ",
        "Oswaldo       ",
        "Otha          ",
        "Otis          ",
        "Otto          ",
        "Owen          ",
        "Pablo         ",
        "Palmer        ",
        "Paris         ",
        "Parker        ",
        "Pasquale      ",
        "Pat           ",
        "Patricia      ",
        "Patrick       ",
        "Paul          ",
        "Pedro         ",
        "Percy         ",
        "Perry         ",
        "Pete          ",
        "Peter         ",
        "Phil          ",
        "Philip        ",
        "Phillip       ",
        "Pierre        ",
        "Porfirio      ",
        "Porter        ",
        "Preston       ",
        "Prince        ",
        "Quentin       ",
        "Quincy        ",
        "Quinn         ",
        "Quintin       ",
        "Quinton       ",
        "Rafael        ",
        "Raleigh       ",
        "Ralph         ",
        "Ramiro        ",
        "Ramon         ",
        "Randal        ",
        "Randall       ",
        "Randell       ",
        "Randolph      ",
        "Randy         ",
        "Raphael       ",
        "Rashad        ",
        "Raul          ",
        "Ray           ",
        "Rayford       ",
        "Raymon        ",
        "Raymond       ",
        "Raymundo      ",
        "Reed          ",
        "Refugio       ",
        "Reggie        ",
        "Reginald      ",
        "Reid          ",
        "Reinaldo      ",
        "Renaldo       ",
        "Renato        ",
        "Rene          ",
        "Reuben        ",
        "Rex           ",
        "Rey           ",
        "Reyes         ",
        "Reynaldo      ",
        "Rhett         ",
        "Ricardo       ",
        "Rich          ",
        "Richard       ",
        "Richie        ",
        "Rick          ",
        "Rickey        ",
        "Rickie        ",
        "Ricky         ",
        "Rico          ",
        "Rigoberto     ",
        "Riley         ",
        "Rob           ",
        "Robbie        ",
        "Robby         ",
        "Robert        ",
        "Roberto       ",
        "Robin         ",
        "Robt          ",
        "Rocco         ",
        "Rocky         ",
        "Rod           ",
        "Roderick      ",
        "Rodger        ",
        "Rodney        ",
        "Rodolfo       ",
        "Rodrick       ",
        "Rodrigo       ",
        "Rogelio       ",
        "Roger         ",
        "Roland        ",
        "Rolando       ",
        "Rolf          ",
        "Rolland       ",
        "Roman         ",
        "Romeo         ",
        "Ron           ",
        "Ronald        ",
        "Ronnie        ",
        "Ronny         ",
        "Roosevelt     ",
        "Rory          ",
        "Rosario       ",
        "Roscoe        ",
        "Rosendo       ",
        "Ross          ",
        "Roy           ",
        "Royal         ",
        "Royce         ",
        "Ruben         ",
        "Rubin         ",
        "Rudolf        ",
        "Rudolph       ",
        "Rudy          ",
        "Rueben        ",
        "Rufus         ",
        "Rupert        ",
        "Russ          ",
        "Russel        ",
        "Russell       ",
        "Rusty         ",
        "Ryan          ",
        "Sal           ",
        "Salvador      ",
        "Salvatore     ",
        "Sam           ",
        "Sammie        ",
        "Sammy         ",
        "Samual        ",
        "Samuel        ",
        "Sandy         ",
        "Sanford       ",
        "Sang          ",
        "Santiago      ",
        "Santo         ",
        "Santos        ",
        "Saul          ",
        "Scot          ",
        "Scott         ",
        "Scottie       ",
        "Scotty        ",
        "Sean          ",
        "Sebastian     ",
        "Sergio        ",
        "Seth          ",
        "Seymour       ",
        "Shad          ",
        "Shane         ",
        "Shannon       ",
        "Shaun         ",
        "Shawn         ",
        "Shayne        ",
        "Shelby        ",
        "Sheldon       ",
        "Shelton       ",
        "Sherman       ",
        "Sherwood      ",
        "Shirley       ",
        "Shon          ",
        "Sid           ",
        "Sidney        ",
        "Silas         ",
        "Simon         ",
        "Sol           ",
        "Solomon       ",
        "Son           ",
        "Sonny         ",
        "Spencer       ",
        "Stacey        ",
        "Stacy         ",
        "Stan          ",
        "Stanford      ",
        "Stanley       ",
        "Stanton       ",
        "Stefan        ",
        "Stephan       ",
        "Stephen       ",
        "Sterling      ",
        "Steve         ",
        "Steven        ",
        "Stevie        ",
        "Stewart       ",
        "Stuart        ",
        "Sung          ",
        "Sydney        ",
        "Sylvester     ",
        "Tad           ",
        "Tanner        ",
        "Taylor        ",
        "Ted           ",
        "Teddy         ",
        "Teodoro       ",
        "Terence       ",
        "Terrance      ",
        "Terrell       ",
        "Terrence      ",
        "Terry         ",
        "Thad          ",
        "Thaddeus      ",
        "Thanh         ",
        "Theo          ",
        "Theodore      ",
        "Theron        ",
        "Thomas        ",
        "Thurman       ",
        "Tim           ",
        "Timmy         ",
        "Timothy       ",
        "Titus         ",
        "Tobias        ",
        "Toby          ",
        "Tod           ",
        "Todd          ",
        "Tom           ",
        "Tomas         ",
        "Tommie        ",
        "Tommy         ",
        "Toney         ",
        "Tony          ",
        "Tory          ",
        "Tracey        ",
        "Tracy         ",
        "Travis        ",
        "Trent         ",
        "Trenton       ",
        "Trevor        ",
        "Trey          ",
        "Trinidad      ",
        "Tristan       ",
        "Troy          ",
        "Truman        ",
        "Tuan          ",
        "Ty            ",
        "Tyler         ",
        "Tyree         ",
        "Tyrell        ",
        "Tyron         ",
        "Tyrone        ",
        "Tyson         ",
        "Ulysses       ",
        "Val           ",
        "Valentin      ",
        "Valentine     ",
        "Van           ",
        "Vance         ",
        "Vaughn        ",
        "Vern          ",
        "Vernon        ",
        "Vicente       ",
        "Victor        ",
        "Vince         ",
        "Vincent       ",
        "Vincenzo      ",
        "Virgil        ",
        "Virgilio      ",
        "Vito          ",
        "Von           ",
        "Wade          ",
        "Waldo         ",
        "Walker        ",
        "Wallace       ",
        "Wally         ",
        "Walter        ",
        "Walton        ",
        "Ward          ",
        "Warner        ",
        "Warren        ",
        "Waylon        ",
        "Wayne         ",
        "Weldon        ",
        "Wendell       ",
        "Werner        ",
        "Wes           ",
        "Wesley        ",
        "Weston        ",
        "Whitney       ",
        "Wilber        ",
        "Wilbert       ",
        "Wilbur        ",
        "Wilburn       ",
        "Wiley         ",
        "Wilford       ",
        "Wilfred       ",
        "Wilfredo      ",
        "Will          ",
        "Willard       ",
        "William       ",
        "Williams      ",
        "Willian       ",
        "Willie        ",
        "Willis        ",
        "Willy         ",
        "Wilmer        ",
        "Wilson        ",
        "Wilton        ",
        "Winford       ",
        "Winfred       ",
        "Winston       ",
        "Wm            ",
        "Woodrow       ",
        "Wyatt         ",
        "Xavier        ",
        "Yong          ",
        "Young         ",
        "Zachariah     ",
        "Zachary       ",
        "Zachery       ",
        "Zack          ",
        "Zackary       ",
        "Zane          "};

    // List of users to add
    public static List<Integer> demoUsersIds = new ArrayList<Integer>();

    // List of demo users
    static {

        demoUsersIds.add(3144);
        demoUsersIds.add(3145);
        demoUsersIds.add(3146);
        demoUsersIds.add(3147);
        demoUsersIds.add(3148);
        demoUsersIds.add(3149);
        demoUsersIds.add(3150);
        demoUsersIds.add(3151);
        demoUsersIds.add(3152);
        demoUsersIds.add(3153);
        demoUsersIds.add(3154);
        demoUsersIds.add(3155);
        demoUsersIds.add(3156);
        demoUsersIds.add(3157);
        demoUsersIds.add(3158);
        demoUsersIds.add(3159);
        demoUsersIds.add(3160);
        demoUsersIds.add(3161);
        demoUsersIds.add(3162);
        demoUsersIds.add(3163);
    }

    public static void main (String[] args) throws Exception  {

//        createRandomUsers(20);
        demoize(2032);

    }

    public static void demoize (Integer networkId) throws Exception {

        // Change application to receive demo data
        Vars.sendEmails = false;
        Vars.enableTimelocks = false;

        // Demo parameters
        Integer answersPerUser = 50;
        Integer totalItems = 100;
        Integer userLinksPerUser = 20;

        // Inject demo data
        addDemoUsersToNetwork(networkId);
        randomAnswers(networkId, answersPerUser);
        CronServices.calledHourlyPopulateSmartGroups();
        randomSharedItemsAndComments(networkId, totalItems, 5);
        randomConnections(networkId, userLinksPerUser);

        // Reminder to restart server
        log("** Remember to re-start server!");
        log("** Remember to re-start server!");
        log("** Remember to re-start server!");
    }

    public static void createRandomUsers(Integer noOfUsers) throws SQLException {

        Integer userId = null;
        Integer randomFirst = null;
        Integer randomLast = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        Integer faceId = null;

        for (int i = 0; i < noOfUsers; i++) {

            // Inserting user
            randomFirst = randomGenerator.nextInt(names.length);
            randomLast = randomGenerator.nextInt(names.length);
            firstName = names[randomFirst].trim();
            lastName = names[randomLast].trim();
            email = firstName.substring(0, 1) + lastName + "@tree.st";
            userId = UserDao.insert(null, email, "password" + i, firstName, lastName);

            // Updating face
            faceId = (userId % 20) + 20;
            UserDao.updateFaceByUserId(null, userId, 1, "/resources/faces/" + faceId + ".jpg");

            // Documenting action
            DemoServices.log("Created user id: " + userId + " with face id: " + faceId);
            DemoServices.log("");
        }

    }

    /**
     * Populates a network with about 20 members
     *
     * @throws Exception
     */
    public static void addDemoUsersToNetwork(Integer networkId) throws Exception {

        // Adding demo users to network
        for (Integer demoUserId : demoUsersIds)
            NetworkServices.addUserToNetworkWithDependencies(networkId, demoUserId, RoleEnum.MEMBER);

    }








    public static void randomAnswers(Integer networkId, Integer answersPerUser) throws SQLException {

        // How many answers should expect
        AnswerVisibilityEnum visibility = AnswerVisibilityEnum.PROTECTED;
        Integer answeringQuestionRef = null;
        Network network = NetworkDao.getById(null, networkId);

        for (Integer demoUserId : demoUsersIds) {

            // Select random user
            User user = UserDao.getById(null, demoUserId);

            for (int i = 0; i < answersPerUser; i++) {

                // Get user's next question
                answeringQuestionRef = FlowRuleServices.getNextQuestionRef(user.getId(), network.getId());

                DemoServices.log("Answering Question id: " + answeringQuestionRef);

                if (answeringQuestionRef == null) {
                    DemoServices.log("CONTINUE: No next demo user.");
                    DemoServices.log("");
                    continue;
                }

                // Get a random subset of options from the question
                List<Integer> randomOptionRefs = getRandomOptionsRefs(network.getId(), answeringQuestionRef);
                if (randomOptionRefs == null)
                    continue;

                // Submit answer
                Tuple<Boolean, Integer> result = AnswerServices.answer(user.getId(), network.getId(), answeringQuestionRef, randomOptionRefs, visibility);

                DemoServices.log("Again?: " + result.getX());
                DemoServices.log("Points added: " + result.getY());
                DemoServices.log("Answer " + i);
                DemoServices.log("");
            }

        }

    }

    public static void randomSharedItemsAndComments(Integer networkId, Integer totalItems, Integer maxCommentsPerItem) throws SQLException {

        String randomString = null;
        Tuple<Integer, Integer> addedItemResult = null;

        for (int i = 0; i < totalItems; i++) {

            // Select random user
            User user = getRandomDemoUser();

            // Select random smart group of network
            SmartGroup group = getRandomSmartGroupByNetworkId(networkId);
            if (group == null) continue;

            // Write shared item
            randomString = getRandomText(3, 100);
            try { addedItemResult = SharedItemServices.add(networkId, user.getId(), group.getSmartGroupRef(), randomString); }
            catch (UIException uie) { continue; }

            // Write commends for shared item
            Integer totalComments = randomGenerator.nextInt(maxCommentsPerItem);
            for (int u = 0; u < totalComments; u++) {

                // Select random user
                user = getRandomDemoUser();

                // Write shared comment
                randomString = getRandomText(3, 150);

                try { SharedCommentServices.add(networkId, user.getId(), group.getSmartGroupRef(), addedItemResult.getX(), randomString); }
                catch (UIException uie) { continue; }

            }

            DemoServices.log("");

        }

    }

    public static void randomConnections(Integer networkId, Integer userLinksPerUser) throws Exception {

        User fromUser = null;

        for (Integer demoUserId : demoUsersIds) {

            // Setting from user
            fromUser = UserDao.getById(null, demoUserId);

            // Creating a random number of connections for demo user
            for (int i = 0; i < randomGenerator.nextInt(userLinksPerUser); i++) {

                // Getting random user
                User toUser = getRandomDemoUser();

                // Connection both users
                try { UserLinkServices.linkUsers(networkId, fromUser.getId(), toUser.getId()); }
                catch (UIException uie) { continue; }

            }

        }

    }



    private static User getRandomDemoUser() throws SQLException {

        int randomInt = randomGenerator.nextInt(demoUsersIds.size());
        Integer randomDemoUserId = demoUsersIds.get(randomInt);
        User user = UserDao.getById(null, randomDemoUserId);

        return user;
    };


    private static SmartGroup getRandomSmartGroupByNetworkId(Integer networkId) throws SQLException {

        List<SmartGroup> groups = SmartGroupDao.getNonHiddenByNetworkIdAndLowestVisibility(null, networkId, SmartGroupVisibilityEnum.SHARED);

        // Selecting a random smart group
        int randomInt = randomGenerator.nextInt(groups.size());
        SmartGroup group = groups.get(randomInt);

        // Ignore if the "search" smart group was selected
        if (group.getName().equals(SmartGroup.SEARCH_NAME)) return null;

        DemoServices.log("Smart Group ref: " + group.getSmartGroupRef() + " (" + group.getName() + ").");

        return group;
    };

    public static List<Integer> getRandomOptionsRefs(Integer networkId, Integer questionRef) throws SQLException {

        List<QuestionOption> questionOptions = QuestionOptionDao.getByNetworkIdAndQuestionRef(null, networkId, questionRef);

        if (questionOptions.size() == 0)
            return null;

        // Retrieving question to answer
        Question question = QuestionDao.getByNetworkIdAndRef(null, networkId, questionRef);
        int randomNumberOptions = randomGenerator.nextInt(question.getMaxSelectedOptions());
        randomNumberOptions++;

       // Generating random list of options
       List<Integer> randomOptionRefs = new ArrayList<Integer>();
       int randomOptionIndex = 0;
       for (int u = 0; u < randomNumberOptions; u++) {

           randomOptionIndex = randomGenerator.nextInt(questionOptions.size());

           int randomOptionRef = questionOptions.get(randomOptionIndex).getRef();

           if (randomOptionRefs.contains(randomOptionRef)) continue;

           randomOptionRefs.add(randomOptionRef);
       }

       return randomOptionRefs;

    }

    public static String getRandomText(Integer minLength, Integer maxLength) {

        Integer length = randomGenerator.nextInt(maxLength - minLength);
        length = length + minLength;

        Integer randomTextSize = randomText.length();
        Integer maxEnd = randomTextSize - length;

        Integer start = randomGenerator.nextInt(maxEnd);

        return randomText.substring(start, start + length).trim();
    }

    private static void log(String string) {

        if (log) System.out.println(string);
    }

}
