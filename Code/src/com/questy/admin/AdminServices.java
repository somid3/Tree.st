package com.questy.admin;

import com.questy.dao.*;
import com.questy.domain.*;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.enums.RoleEnum;
import com.questy.enums.SmartGroupVisibilityEnum;
import com.questy.helpers.SqlLimit;
import com.questy.helpers.Tuple;
import com.questy.helpers.UIException;
import com.questy.web.HtmlUtils;
import com.questy.services.*;
import com.questy.utils.Vars;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class AdminServices {

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


    public static void populateUsers(Integer noOfUsers, List<Integer> networkIds) throws SQLException {

        /**
         * DO NOT SEND EMAILS AS YOU ARE CREATING RANDOM USERS
         * DO NOT SEND EMAILS AS YOU ARE CREATING RANDOM USERS
         * DO NOT SEND EMAILS AS YOU ARE CREATING RANDOM USERS
         */
        Boolean originalSendEmails = Vars.sendEmails;
        Vars.sendEmails = false;

        Integer userId = null;
        Integer randomFirst = null;
        Integer randomLast = null;
        Integer randomPointsPerLink = null;
        String firstName = null;
        String lastName = null;
        String email = null;

        String checksum = null;
        for (int i = 0; i < noOfUsers; i++) {

            checksum = HtmlUtils.getRandomId();
            randomFirst = randomGenerator.nextInt(names.length);
            randomLast = randomGenerator.nextInt(names.length);
            firstName = names[randomFirst].trim();
            lastName = names[randomLast].trim();
            email = firstName.substring(0, 1) + lastName + "@mit.edu";

            userId = UserDao.insert(null, email, "password" + i, firstName, lastName);

            AdminServices.log("Created user id: " + userId);
            for (Integer networkId : networkIds) {

                randomPointsPerLink = randomGenerator.nextInt(10);

                NetworkServices.addUserToNonGlobalNetworkWithDependencies(networkId, userId, RoleEnum.MEMBER);
                UserToNetworkDao.updatePointsPerLink(null, networkId, userId, randomPointsPerLink);

                AdminServices.log("Added user " + userId + " to network: " + networkId);
            }

            AdminServices.log("");
        }


        /**
         * RE-SET SENDING EMAILS
         * RE-SET SENDING EMAILS
         * RE-SET SENDING EMAILS
         */
        Vars.sendEmails = originalSendEmails;


    }


    public static void randomSpecificAnswer(Integer noOfUsers, Integer networkId, Integer questionRef, Map<String, Integer> optionTextAndTimes) throws SQLException {


        // Retrieve question
        Question question = QuestionServices.getByNetworkIdAndRef(networkId, questionRef);
        QuestionOption desiredOption = null;
        List<Integer> answeringOptions = null;
        Integer noOfOptions = null;
        User user = null;

        // Looping over all option texts provided
        for (String optionText : optionTextAndTimes.keySet() ) {

            // Find option
            desiredOption = question.findOptionByText(optionText.trim());

            // Looping the number of times required to answer
            for (int i = 0; i < optionTextAndTimes.get(optionText); i++) {

                // Retrieve random user
                user = getRandomUser(noOfUsers);

                // Retrieving a random subset of options
                answeringOptions = getRandomOptionsRefs(networkId, questionRef);

                // Removing first random option and replacing with prefered option
                answeringOptions.remove(0);
                answeringOptions.remove(desiredOption.getRef());
                answeringOptions.add(desiredOption.getRef());

                // Answer option with random user
                AnswerServices.answer(user.getId(), networkId, questionRef, answeringOptions, AnswerVisibilityEnum.PROTECTED);


            }
        }

    }

    public static void randomAnswers(Integer noOfUsers, Integer answersPerUser) throws SQLException {

        // How many answers should expect
        Integer totalAnswers = noOfUsers * answersPerUser;
        AnswerVisibilityEnum visibility = null;
        Integer maxVisibility = null;
        List<Answer> lastAnswer = null;
        Integer answeringQuestionRef = null;
        Question answeringQuestion = null;
        for (int i = 0; i < totalAnswers; i++) {

            // Select random user
            User user = getRandomUser(noOfUsers);

            // Select random network
            Network network = getRandomNetworkByUserId(null, user.getId());
            if (network == null) continue;

            // Get user's next question
            answeringQuestionRef = FlowRuleServices.getNextQuestionRef(user.getId(), network.getId());

            AdminServices.log("Answering Question id: " + answeringQuestionRef);

            if (answeringQuestionRef == null) {
                AdminServices.log("CONTINUE: No next question" + ".");
                AdminServices.log("");
                continue;
            }

            // Get a random subset of options from the question
            List<Integer> randomOptionRefs = getRandomOptionsRefs(network.getId(), answeringQuestionRef);
            if (randomOptionRefs == null)
                continue;

            // Ensures that 0 is not chosen since lowest visibility for most will become 0
            visibility = null;
            {
                // Ensures lowest visibility is 3
                visibility = AnswerVisibilityEnum.PROTECTED;

                AdminServices.log("Max Visibility: " + maxVisibility);
                AdminServices.log("Visibility: " + visibility);
            }

            // Submit answer
            Tuple<Boolean, Integer> result = AnswerServices.answer(user.getId(), network.getId(), answeringQuestionRef, randomOptionRefs, visibility);

            AdminServices.log("Again?: " + result.getX());
            AdminServices.log("Points added: " + result.getY());
            AdminServices.log("Answer " + i);
            AdminServices.log("");
        }


    }

    public static void randomSharedItemsAndComments(Integer noOfUsers, Integer itemsPerUser, Integer maxCommentsPerItem) throws SQLException {

        String randomString = null;
        SharedItem addedItem = null;

        Integer totalAnswers = noOfUsers * itemsPerUser;
        for (int i = 0; i < totalAnswers; i++) {

            // Select random user
            User user = getRandomUser(noOfUsers);

            // Select random non global network
            Network network = getRandomNonGlobalNetworkByUserId(null, user.getId());
            if (network == null) continue;

            // Select random smart group of network
            SmartGroup group = getRandomSmartGroupByNetworkId(null, network.getId());
            if (group == null) continue;

            // Write shared item
            randomString = getRandomText(3, 100);
            Tuple<Integer, Integer> addedItemResult = SharedItemServices.add(network.getId(), user.getId(), group.getRef(), randomString);

            // Write commends for shared item
            Integer commentsForItem = randomGenerator.nextInt(maxCommentsPerItem);
            for (int u = 0; u < commentsForItem; u++) {

                // Select random user
                user = getRandomUser(noOfUsers);

                // Write shared comment
                randomString = getRandomText(3, 150);
                SharedCommentServices.add(network.getId(), user.getId(), group.getRef(), addedItemResult.getX(), randomString);

            }

            AdminServices.log("");

        }


    }

    public static void randomConnections(Integer noOfUsers, Integer connectionsPerUser) throws Exception {

        Integer totalConnections = noOfUsers * connectionsPerUser;

        for (int i = 0; i < totalConnections; i++) {

            // Getting random user
            User userFrom = getRandomUser(noOfUsers);

            System.out.println(userFrom.getId());

            // Getting random network
            Network network = getRandomNonGlobalNetworkByUserId(null, userFrom.getId());
            if (network == null) continue;

            SmartGroup group = getRandomSmartGroupByNetworkId(null, network.getId());
            if (group == null) continue;

            // Getting random user in such network
            User userTo = UserDao.getById(null, 2);
//            User userTo = getRandomUserByNetworkId(null, network.getId());

            // Connection both users
            try {

                System.out.println( UserLinkServices.linkUsers(network.getId(), userFrom.getId(), userTo.getId()) );

//                UserLinkServices.linkUsers(network.getId(), userFrom.getId(), userTo.getId());

            } catch (UIException uie) {

                uie.printStackTrace();

            }

        }
    }



    private static User getRandomUser(Integer noOfUsers) throws SQLException {

        int randomInt = randomGenerator.nextInt(noOfUsers);
        randomInt = randomInt + 3;
        User user = UserDao.getById(null, randomInt);
        AdminServices.log("User id: " + user.getId() + ".");

        return user;
    };

    private static User getRandomUserByNetworkId(Connection conn, Integer networkId) throws SQLException {

        List<UserToNetwork> utns = UserToNetworkDao.getByNetworkIdOrderedByPoints(
                conn,
                networkId,
                new SqlLimit(0, 10000));

        int randomInt = randomGenerator.nextInt(utns.size());
        User user = UserDao.getById(null, utns.get(randomInt).getUserId());

        return user;
    };

    private static Network getRandomNetworkByUserId(Connection conn, Integer userId) throws SQLException {

        // Retrieving all networks user is registered with
        List<Network> networks = NetworkServices.getByUserId(userId, RoleEnum.MEMBER);

        if (networks.isEmpty()) return null;

        int randomInt = randomGenerator.nextInt(networks.size());
        Network network = networks.get(randomInt);
        AdminServices.log("Network id: " + network.getId() + ".");

        return network;
    };

    private static Network getRandomNonGlobalNetworkByUserId(Connection conn, Integer userId) throws SQLException {

        // Retrieving all networks user is registered with
        List<Network> networks = NetworkServices.getByUserId(userId, RoleEnum.MEMBER);
        List<Network> globals = NetworkDao.getAllByGlobal(conn, true);

        // Removing all global networks
        networks.removeAll(globals);

        if (networks.isEmpty()) return null;

        int randomInt = randomGenerator.nextInt(networks.size());
        Network network = networks.get(randomInt);
        AdminServices.log("Network id: " + network.getId() + ".");

        return network;
    };

    private static SmartGroup getRandomSmartGroupByNetworkId(Connection conn, Integer networkId) throws SQLException {

        List<SmartGroup> groups = SmartGroupDao.getNonHiddenByNetworkIdAndLowestVisibility(null, networkId, SmartGroupVisibilityEnum.SHARED);

        // Selecting a random smart group
        int randomInt = randomGenerator.nextInt(groups.size());
        SmartGroup group = groups.get(randomInt);

        // Ignore if the "search" smart group was selected
        if (group.getName().equals(SmartGroupDao.SEARCH_NAME)) return null;

        AdminServices.log("Smart Group ref: " + group.getRef() + " (" + group.getName() + ").");

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

        if (log)
            System.out.println(string);

    }

}
