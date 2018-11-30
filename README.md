# Java.DataStructures.AssignmentSubmissionHistory

Info 9105								Assignment 1 Report
Kashif Akram – 460458380

Introduction (Overview)
The project is about to store students’ assignment submissions and keep record of every submission submitted by student which requires to implement several methods to get best grade of any given student or to get final submission submitted by student etc. 
I used following nested data structures for the implementation to get maximum efficiency of running time, nested data structures helped to keep running time as minimum as possible because when I found that one data structure is not efficient for a task I replaced it with different data structure which provided me better performance.

Data Structures
Hash Map:
•	HashMap (hp) to store unikey as key for a student and TreeMap with Date to store time stamp as key and integer to store grade as value to fulfil the requirement to store every grade under different time stamp and then retrieving get final submission in O(1) time by using lastKey() method.
•	HashMap (hp1) to store unikey as key for a student and TreeMap with Integer to store grade as key and set of Date to store time stamp as value to fulfil the requirement to store every grade under different time stamp and then retrieving get best grade in O(1) time by using lastKey() method.
•	HashMap (bestGrade) to store unikey as key for a student and Integer to store best grade as value to keep track of best grades for every student and then using this to list top students.
Tree Map:
•	TreeMap is used as nested data structure in HashMap to store values, one variant is with Date to store time stamp as key and integer to store grade as value and other is with Integer to store grade as key and set of Date store time stamp as values.
Hash Set:
•	HashMap (hp1) to store unikey as key for a student and TreeMap with Integer to store grade as key and HashSet of Date to store time stamp as value.





Methods and their running time in Big-Oh
getBestGrade(uniKey):
•	This method finds the highest grade of any submission by the student with uniKey passed as parameter
•	It returns null if there is no submission submitted by the student
•	Throws IllegalArgumentException if no argument is passed
•	This method runs in O(logn) time because according to java docs tree map returns the last key in O(logn) time, as I am storing grade of every student as key in first hp data structure which will store highest grade in last key.
getFinalSubmission(uniKey):
•	This method returns latest submission for a given student with uniKey passed as argument
•	It returns null if there is no submission submitted by the student
•	Throws IllegalArgumentException if no argument is passed
•	This method runs in O(logn) time because according to java docs tree map returns the last key in O(logn) time, as I am storing time stamp of every student as key in second hp1 data structure which will store highest date in last key.
getSubmissionBefore(String unikey, Date deadline):
•	This method adds a new submission and returns the submission created
•	Throws IllegalArgumentException if no argument is passed
•	This method runs in O(logn) time because of tree map's ability to find and a key before or after other key in O(logn) time, floorEntry() method will return submission which was submitted before the deadline passed in method’s argument.
add(String uniKey, Date timeStamp, Integer grade):
•	This method returns the most recent submission for a given student , prior to a given time and null if there is no submission submitted by the student
•	Throws IllegalArgumentException if any argument is null
•	This method runs in O(logn) time because of tree map's ability to put an element in O(logn) time as mentioned above I used hp data structure where date is key and integer is value and hp1 data structure where integer is key and date is value, adding new element in Hashmap is O(1) and adding in Treemap is O(logn) -> O(1) + O(logn) -> O(logn)
remove(Submission submission):
•	This method removes a submission passed in method’s argument
•	Throws IllegalArgumentException if any argument is null
•	This method runs in O(logn) time because of tree map's ability to find and remove element in O(logn) time and removing  in Hashmap is O(1) (given the fact that hashtable doesn’t contain many collisions) -> O(1) + O(logn) -> O(logn)
listTopStudents():
•	This method returns list of all the students who achieved the highest grade (in any of their submissions)
•	This method runs in O(n) time because we have to loop through each element in Hashmap which is O(n) and I used another HashMap data structure to store best grade of every student, inside for loop I compared every best grades of every student and maintained in new Hashset topStudents which is O(1), so overall running time will be O(1).O(n) -> O(n)
listRegressions ():
•	This method returns list of all the students whose most recent submissions have lower grades than their best submissions
•	This method runs in O(nlogn) time because we have to loop through each element in Hashmap which is O(n) and then adding final submissions from students to regressionStudents Hashset by using getSubmissionFinal method which runs in O(logn), so overall running time will be O(n).O(logn) -> O(n log n)


Discussion
Alternative approach:
The alternative approach could be to use List interface with ArrayList implementation. The code would be simple and less as compare to current design where I used complicated and nested data structures, for example HashMap, TreeMap and HashSet.

Comparison:
But the disadvantage of using simple data structure like ArrayList is the complexity time, most of the methods requires at least O(n) running time, for example to get best grade for a student we need to loop through whole list and created another list of grades for all submissions against student’s uni key, additionally we need to extract the maximum grade from newly created list which is O(n) again giving final running time O(n).
Same is true for all other methods and it becomes worst when it comes to implement regression list as we loop through whole list, it is O(n) and inside loop we need to compare final submission with student’s best grade, both of them are O(n) as well, so final will be O(n).O(n)+O(n) -> n2. 
As briefed in above topic, on the other hand we achieved much better time complexity by using HashMap, TreeMap and HashSet data structures.
There are some disadvantages of using these data structures though, that they take more time in getting elements than ArrayList as ArrayList stores elements based on index and can get an element from specific index takes O(1). While TreeMap takes at least O(logn) minimum time to get an element from tree.

