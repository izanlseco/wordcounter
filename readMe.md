## -- INSTRUCTIONS to use the command-line application --

The 'single commas' are just for syntax purposes, DO NOT type them in.

1. Open the CMD from inside the src path of this folder.
2. Type in the command-line: 'cd com/adaptavist/wordcount'.
3. Type in the command-line: 'javac WordCount.java' to compile the class file.
4. Type in the command-line: 'cd ../../..' to return to the src folder.
5. Type in the command-line: 'java com.adaptavist.wordcount.WordCount "C:\file\path\example\text.txt"'.
6. It should have worked by now, in case of any error please don't doubt to contact me.

## -- further INFO --

As the task that I was asked for was to "implement a command-line application that takes a path to a file as an argument and prints a word count of its contents" so I have tried to make it simple. 

I have used three private sub-methods in order to decouple the activity from the main class one and keep it tidy, clean and easy to maintain. I could have used three classes instead but I have prefered to use sub-methods in this case. I have also added some error management and some guidance during the process of count the words. 

I've assumed that you wanted it to be case-insensitive as it didn't make much sense to me to differentiate by groups of words like 'The' and 'the'.

It was a really fun thing to do. I've added a resources folder with a txt that I've used to do some testing that you can use or load a .txt of your preference from wherever you prefer.
