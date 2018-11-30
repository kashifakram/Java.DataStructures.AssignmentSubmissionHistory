import java.util.*;


public class Assignment implements SubmissionHistory {

    /**
     * Declaring Submit class implementing Submission interface
     */
    private class Submit implements Submission {
        private String uniKey;
        private Date timeStamp;
        private Integer grade;


        /**
         * Submit class constructor
         * @param uniKey
         * @param timeStamp
         * @param grade
         * Assigning passed parameters' uniKey, timeStamp and grade values to private uniKey, timeStamp and grade variables
         */
        public Submit (String uniKey, Date timeStamp, Integer grade){
            this.uniKey = uniKey;
            this.timeStamp = timeStamp;
            this.grade = grade;
        }

        /**
         * Overriding getUnikey() method
         * @return the unikey (a String of the form " abcd1234 ")
         */
        @Override
        public String getUnikey() {
            return this.uniKey;
        }

        /**
         * Overriding getTime() method
         * @return a Date object representing the time the submission was made
         */
        @Override
        public Date getTime() {
            return this.timeStamp;
        }

        /**
         * Overriding getGrade() method
         * @return an integer grade
         */
        @Override
        public Integer getGrade() {
            return this.grade;
        }
    }

    /**
     * Declaring map constructors, one to store uniKey as key and TreeMap as values
     *
     * First Map (hp) used to retrieve last submission and submission before time stamps
     * where Date data type used as key and Integer (to store grade) as value
     * against timestamps to get complexity of O(log n)
     *
     * Second Map (hp1) used to retrieve best grade where Integer (to store grades) used as key
     * and Date (to store time stamp) as value to store grade
     * against timestamps to get complexity of O(log n)
     */
    Map<String, TreeMap<Date, Integer>> hp;
    Map<String, TreeMap<Integer, Set<Date>>> hp1;
    Map<String, Integer> bestGrade;

    /**
     * Default constructor
     * Initializing data structures
     */
    public Assignment() {
// TODO initialise your data structures
        hp = new HashMap<>();
        hp1 = new HashMap<>();
        bestGrade = new HashMap<>();
    }

    /** These comments are modified version of comments provided in SubmissionHistory
     * interface with complexity details
     * Method getBestGrade(String unikey) finds the highest grade of any submission by this student
     *
     * @param unikey for student to filter on
     * @return the best grade by this student , or null if they have made no
     * submissions
     * @throws IllegalArgumentException if the argument is null
     *
     * This method runs in O(logn) time because according to java docs
     * tree map returns the last key in O(logn) time as mentioned above I used hp data structure
     * where grade is key and set of date is value to cover the situation where student
     * can achieve same grades against different submissions and time stamps
     *
     */
    @Override
    public Integer getBestGrade(String unikey) {
// TODO Implement this , ideally in better than O(n)
        if (unikey == null || unikey.equals("")){
            throw new IllegalArgumentException(unikey);
        }
        else{
            TreeMap<Integer, Set<Date>> tm1 = hp1.get(unikey);
            if (tm1 != null && !tm1.isEmpty())
                return tm1.lastKey();
            return null;
        }
    }

    /** These comments are modified version of comments provided in SubmissionHistory interface with complexity details
     * Method getSubmissionFinal(String unikey) returns latest submission for a given student
     *
     * @param unikey for student to filter on
     * @return Submission made most recently by that student , or null if the
     * student has made no submissions
     * @throws IllegalArgumentException if the argument is null
     *
     * This method runs in O(logn) time because according to java docs
     * tree map returns the last key in O(logn) time as mentioned above I used hp data structure
     * where date is key and integer is value
     */
    @Override
    public Submission getSubmissionFinal(String unikey) {
// TODO Implement this , ideally in better than O(n)
        if (unikey == null || unikey.equals("")){
            throw new IllegalArgumentException(unikey);
        }
        else{
                TreeMap<Date, Integer> tm1 = hp.get(unikey);
            if (tm1 != null && !tm1.isEmpty()){
                Map.Entry<Date, Integer> entry = tm1.lastEntry();
                if (entry != null)
                    return new Submit(unikey, entry.getKey(), entry.getValue());
            }
            return null;
        }
    }

    /** These comments are modified version of comments provided in SubmissionHistory interface with complexity details
     * The most recent submission for a given student , prior to a given time
     *
     * @param unikey   The student to filter on
     * @param deadline The deadline after which no submissions are considered
     * @return Submission made most recently by that student , or null if the
     * student has made no submissions
     * @throws IllegalArgumentException if the argument is null
     *
     * This method runs in O(logn) time because of tree map's ability to find and elements in O(logn) time
     * as mentioned above I used hp data structure where date is key and integer is value
     */
    @Override
    public Submission getSubmissionBefore(String unikey, Date deadline) {
// TODO Implement this , ideally in better than O(n)
        if (unikey == null || unikey.equals("")){
            throw new IllegalArgumentException(unikey);
        }
        else{
                TreeMap<Date, Integer> tm1 = hp.get(unikey);
            if (tm1 != null && !tm1.isEmpty()){
                Map.Entry<Date, Integer> entry = tm1.floorEntry(deadline);
                if (entry != null)
                    return new Submit(unikey, entry.getKey(), entry.getValue());
            }
            return null;
        }
    }

    /** These comments are modified version of comments provided in SubmissionHistory interface with complexity details
     * Add a new submission
     *
     * @param uniKey
     * @param timeStamp
     * @param grade
     * @return the Submission object that was created
     * @throws IllegalArgumentException if any argument is null
     *
     * This method runs in O(logn) time because of tree map's ability to put an element in O(logn) time
     * as mentioned above I used hp data structure where date is key and integer is value and hp1 data structure
     * where integer is key and date is value, adding new element in Hashmap is O(1)
     * and adding in Treemap is O(logn) -> O(1) + O(logn) -> O(logn)
     */
    @Override
    public Submission add(String uniKey, Date timeStamp, Integer grade) {
// TODO initialise your data structures
        if (uniKey == null || uniKey.equals("") || grade == null || timeStamp == null){
            throw new IllegalArgumentException();
        }
        else{
            Submission sb = new Submit(uniKey, timeStamp, grade);
            if (hp.get(uniKey) != null){
                if (hp.containsKey(timeStamp)){
                    System.out.println("Duplicate timestamp found for unikey: " + uniKey);
                    return null;
                }
                else{
                    hp.get(uniKey).put(timeStamp, grade);
                }
                if (hp1.get(uniKey).get(grade) != null){
                    hp1.get(uniKey).get(grade).add(timeStamp);
                }
                else {
                    hp1.get(uniKey).put(grade, new HashSet<>());
                    hp1.get(uniKey).get(grade).add(timeStamp);
                }
            }
            else {
                hp.put(uniKey, new TreeMap<>());
                hp.get(uniKey).put(timeStamp, grade);
                hp1.put(uniKey, new TreeMap<>());
                hp1.get(uniKey).put(grade, new HashSet<>());
                hp1.get(uniKey).get(grade).add(timeStamp);
            }
            bestGrade.put(uniKey, getBestGrade(uniKey));
            return sb;
        }
    }


    /** These comments are modified version of comments provided in SubmissionHistory interface with complexity details
     * Remove a submission
     *
     * @param submission The Submission to remove
     * @throws IllegalArgumentException if the argument is null
     *
     * This method runs in O(logn) time because of tree map's ability to find and remove element in O(logn) time
     * and removing  in Hashmap is O(1) (
     * given the fact that hashtable doesn’t contain many collisions) -> O(1) + O(logn) -> O(logn)
     */
    @Override
    public void remove(Submission submission) {
// TODO Implement this , ideally in better than O(n)
        if (submission == null){
            throw new IllegalArgumentException();
        }
        else {
            if (hp.get(submission.getUnikey()) != null){
                TreeMap tm = hp.get(submission.getUnikey());
                tm.remove(submission.getTime());
                if (hp1.get(submission.getUnikey()).get(submission.getGrade()) != null){
                    hp1.get(submission.getUnikey()).get(submission.getGrade()).remove(submission.getTime());
                    if (hp1.get(submission.getUnikey()).get(submission.getGrade()).isEmpty()){
                        hp1.get(submission.getUnikey()).remove(submission.getGrade());
                    }
                }
                bestGrade.put(submission.getUnikey(), getBestGrade(submission.getUnikey()));
                if(tm.isEmpty()){
                    hp.remove(submission.getUnikey());
                    hp1.remove(submission.getUnikey());
                    bestGrade.remove(submission.getUnikey());
                }

            }
        }
    }

    /** These comments are modified version of comments provided in SubmissionHistory interface with complexity details
     * Get all the students who achieved the highest grade (in any of their
     * submissions ).
     *
     * For example , if the highest grade achieved by any student was 93 , then
     * this would return a list of all the students who have made a submission
     * graded at 93.
     *
     * If no submissions have been made , then return an empty list .
     *
     * @return a list of unikeys
     *
     * This method runs in O(n) time because we have to loop through each element in Hashmap which is O(n)
     * and I used another HashMap data structure to store best grade of every student, inside for loop
     * I compared every best grades of every student and maintained in new Hashset topStudents
     * which is O(1), so overall running time will be O(1).O(n) -> O(n)
     */
    @Override
    public List<String> listTopStudents() {
// TODO Implement this , ideally in better than O(n)
// ( you may ignore the length of the list in the analysis )
        Set<String> topStudents = new HashSet<>();
        int maxGrade = -1;
        for (Map.Entry<String, Integer> entry : bestGrade.entrySet()){
            if (maxGrade < 0 || entry.getValue() > maxGrade){
                maxGrade = entry.getValue();
                topStudents.clear();
                topStudents.add(entry.getKey());
            }
            else if(getBestGrade(entry.getKey()) == maxGrade) {
                topStudents.add(entry.getKey());
            }
        }
        return new ArrayList<>(topStudents);
    }

    /**These comments are modified version of comments provided in SubmissionHistory interface with complexity details
     * Get all the students whose most recent submissions have lower grades than
     * their best submissions
     *
     * @return a list of unikeys
     *
     * This method runs in O(nlogn) time because we have to loop through each element in Hashmap which is O(n)
     * and then adding final submissions from students to regressionStudents Hashset by using
     * getSubmissionFinal method which runs in O(logn),
     * so overall running time will be O(n).O(logn) -> O(n log n)
     */
    @Override
    public List<String> listRegressions() {
// TODO Implement this , ideally in better than O(n ^2)
        Set<String> regressionStudents = new HashSet<>();
        for (Map.Entry<String, TreeMap<Date, Integer>> entry : hp.entrySet()){
            if ((getSubmissionFinal(entry.getKey()).getGrade() < getBestGrade(entry.getKey()))){
                regressionStudents.add(entry.getKey());
            }
        }
        return new ArrayList<>(regressionStudents);
    }
}