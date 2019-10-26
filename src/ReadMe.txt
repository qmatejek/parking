Instructions:
=============
To run the program I was able to just cd to the target directory where the executable .jar file was and run "java -jar parking.jar"
and it worked fine. I also tested it and I was able to put just the parking.jar and the TestCases folder on my desktop, then
cd to the desktop and do the same thing. (java -jar parking.jar). I believe this program will work on Linux as well. The
only platform dependent part that I know of is the test file finder, and I tested that on both Windows 7 and Ubuntu Linux
and it worked just fine.
If that doesn't work, you cam either try to cd to src\java\main where the parkingMain.java is and try to run it like normal
with cmd/terminal.
If that doesn't work, you may have to import the project into IntelliJ. If you do this you may have to setup a run configuration
that points to the directory where the .jar is (target).

Assumptions:
============
1.) " Each parking lots belongs to a group that sets the price, discounts, and policies in the group."

Since it says "each" I assumed that each group only needs to have one parking lot. I also assumed the discounts and policies
could be done any way I wanted since that wasn't really specified.

2.) "7. Cars can inquire the latest price and availability of parking space.
8. NEW: groups can send the latest prices to interested cars."

I assumed these went together and the groups sending the latest prices was a response to the car inquiring about the prices.

3.) "10. NEW: Cars choose the lowest available parking lot for parking."
I assumed this meant the lowest PRICED available parking lot, because why else would they be checking the prices?

4.) I assumed that it was okay for me to hardcode in the groups' information since it was not stated in the assignment that
I couldn't, and I still fulfill all the requirements with it like that.

5.) A car cannot be parked in more than one parking lot because of the way everything is set up, not because there is actually
anything checking for it. I assume that's okay since it still fulfills the requirement, " Cars can be only at one place at a time".

6.) I'm pretty sure I use all the new required features, but if I missed one then I want to point out that the assignment
says "can" a lot. Like "A Car can..." or "Groups can...". It says they can, not that they do.


Test Cases:
===========
Test01.txt: Basic test to confirm that all the new functions work as intended.
Test02.txt: Tests to see what happens when a car tries to enter and all lots are full.
Test03.txt: Larger scale test to make sure basic enter/exit functions work properly. Also tests what happens when all lots
are full.
Test04.txt: Further testing of discount to make sure that if a discount makes the most expensive group the cheapest, that
that group will be picked as the cheapest.
Test05.txt: This is just testing to make sure the program works with a decent sized input and a large input that alternates
the  enters and exits.

Notes:
======
This is the first class I've taken in 3-4 years that used Java and that class 3-4 years ago was an intro class, so I don't know much about the more advanced
OOP principles. I just used what I know. 

I am very new to Maven, but I'm pretty sure I got it setup correctly, and it has an executable jar, so I think that handles
the build automation.
