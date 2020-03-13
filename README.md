This small software allows you to retrieve a list of movies matching a list of criteria from imdb tops.

Let's say you want to watch all the comedies in the world, starting with the most popular. You can go to [imdb](https://www.imdb.com/search/title/?genres=comedy) and check the comedy list ordered by popularity.
This program does the same but in an automated way and allows you to add filter to improve your search.

# How to use it
Run `java -jar imdb-1.1-SNAPSHOT.jar` then you'll be prompter to provide a list of filters

# Filters
Here are the list of filters currently implemented
* Number to start from: if you don't want the program to start looking from the number 1 in the list, you can specify an offset from which to start
* Genres to include (*): The genres of the list you want to search
* Genres to exclude: if there are some genres you want to exclude from the list
* Minimum duration
* Maximum duration
* Minimum rating
* Number of movies to retrieve
* Ordering: imdb allows you to order their list based on different criteria (popularity, number of votes, etc.). You can also specify this ordering here
* Minimum number of votes

(Fields with `(*)` are mandatory)

# Example
```
migwel@home:~$ java -jar imdb-1.1-SNAPSHOT.jar 
[Optional] From which number do you want the search to start? (Default: 0)

[Mandatory] Which genre do you want to look for? (If multiple, give a comma-separated list)
comedy
[Optional] Which genres do you want to exclude? (If multiple, give a comma-separated list)
horror
[Optional] Minimum duration (in minutes):
90
[Optional] Maximum duration (in minutes):
180
[Optional] Minimum rating (out of 10)?

How many movies do you want to retrieve? (default is 10, max allowed is 100)
5
[Optional] Which ordering do you want to use?
Possible choices: POPULARITY_ASC, POPULARITY_DESC, ALPHABETICAL_ASC, ALPHABETICAL_DESC, USERRATING_ASC, USERRATING_DESC, NBVOTES_ASC, NBVOTES_DESC, USBOXOFFICE_ASC, USBOXOFFICE_DESC, RUNTIME_ASC, RUNTIME_DESC, YEAR_ASC, YEAR_DESC, RELEASEDATE_ASC, RELEASEDATE_DESC. Default is USERRATING_DESC
POPULARITY_ASC
[Optional] Minimum number of votes: 

Film{name='Knives Out', releaseYear=2019, genres=[comedy,  crime,  drama], rating=8.0, durationInMinutes=131, nbVotes=217133}
Film{name='Gisaengchung', releaseYear=2019, genres=[comedy,  drama,  thriller], rating=8.6, durationInMinutes=132, nbVotes=307391}
Film{name='Spenser Confidential', releaseYear=2020, genres=[action,  comedy,  crime], rating=6.2, durationInMinutes=111, nbVotes=17925}
Film{name='Onward', releaseYear=2020, genres=[animation,  adventure,  comedy], rating=7.7, durationInMinutes=102, nbVotes=9973}
Film{name='Sonic the Hedgehog', releaseYear=2020, genres=[action,  adventure,  comedy], rating=6.7, durationInMinutes=99, nbVotes=30671}
```

#Minimal requirement
To run this program, you need to have Java 8.0 or higher on your system

#Download link
The compiled program can be found [here]()