# Story reader application
A simple Android application to read stories, tales... or everything like that.
> There is no input data feature in this project
## How to use for the first time
This project uses SQLite as a main stored data, so best practices here, you should build and run this for the first time, that means its SQLite database has been created, to get database file, follow these steps (for Android Studio only):
1. Start your Android Emulator.
2. In the right pane of Android Studio, choose Device File Explorer, then select the current emulator.
3. Now you will see a large directory, do not worry! Scroll to find ***data*** folder, expand it.
4. Continue to scroll and find another ***data*** folder, expand it, too (***data/data***).
5. Here you are watching the place which all applications are stored, seek ***com.example.love***, this is your application data.
6. Inside above directory, you will see ***databases/LoveStory*** file, right click on the ***LoveStory*** file, select **Save As**, and choose the path you want to save.
7. Now you have got the database file, just use some software to edit, [DB Browser for SQLite](https://sqlitebrowser.org) is recommended.
8. After updating data to your database, right click on the root ***databases*** folder, select **Upload...**, then choose your updated file (new ***LoveStory***).
## Data
I have use Python to crawl some data and insert into database, you can skip ***crawl_data.py*** if you do not care.
Some library which I use:
- BeautifulSoup
- Requests
- lxml
