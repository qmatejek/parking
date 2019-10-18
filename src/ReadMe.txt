Instructions:
=============

To run the program you should just be able to open terminal/cmd, cd to the src folder, run "javac *.java", then run "java ParkingMain".
The program will ask you to enter which test case you would like to run. Your choices are Test1.txt, Test2.txt, Test3.txt, Test4.txt, Test5.txt and Test6.txt.
If you make your own test case, just follow the format of my cases. Integers only for the capacity and the Exit's.
The enter commands simulate a car entering the lot and the exit commands simulate a car handing in its ticket.


Assumptions:
============
The assignment said, "Have your project read from a text file in whatever format you consider appropriate" so I decided that it made sense that
when a car enters it goes to the first spot available and that when it exits it provides the spot # it was parked in so that it's ticket could
be looked up.

I modeled the prices off real parking lots. I'm thinking that's fine.

I assumed that it is okay to have more than 5 test cases. (I thought of more things to test after I made the first 5. Input validation stuff.)

Test Cases:
===========
None of my test cases test with a very large capacity lot, but I made them to stress test different parts of the program. Since my program
handles all of them without issue, I am confident it would hold up to a test case with a very large capacity.
Test1 has a capacity of 3, because I was testing basic functions as I added them.
Test2 and Test3 are just scaled up versions of Test1 to make sure that my program could handle that without problems.
Test4 is smaller than Test2 and Test3 because I wanted to test and see what happened if cars kept trying to enter a full lot.
Test5 was me trying to make a test case that had cars entering and exitting gradually until the lot emptied instead of everyone leaving at the end all at once.
Test6 tests my input validation for if a car attempts to exit an already empty lot (ghost car) and also what happens when a car gives a ticket
with a negative spot number.
Test 7 tests to see what happens when an invalid capacity (< 1) is given.

Notes:
======
This is the first class I've taken in 3-4 years that used Java and that class 3-4 years ago was an intro class, so I don't know much about the more advanced
OOP principles. I just used what I know. 

I didn't know if I should bother posting something as a version, if it couldn't do what it was supposed to, so I only posted functioning versions.

THANK YOU TO KHALID FOR ANSWERING ALL THE QUESTIONS I HAD ON THIS ASSIGNMENT!