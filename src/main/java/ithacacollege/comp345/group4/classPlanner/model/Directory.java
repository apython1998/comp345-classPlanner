package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Directory {

    private Map<String, Major> majorDirectory;
    private Map<String, Student> students;

    public Directory() {
        this.students = new HashMap<>();
        this.majorDirectory = new HashMap<>();
        //Preload major JSON:
        uploadMajor("resources/TestMajorReqs.json");
    }

    public Directory(Map<String, Student> users) {
        this.students = users;
        //Preload major JSON:
        uploadMajor("resources/TestMajorReqs.json");
    }

    /**
     * Registers a new user to the directory
     * @param username
     * @param password
     * @return true if registration successful, false if username already exists
     */
    public boolean registerStudent(String username, String password) throws InvalidArgumentException {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidArgumentException("Invalid String Used");
        } else if (students.containsKey(username)) {
            return false;
        } else {
            Student newUser = new Student(username, password, null, null);
            students.put(newUser.getUsername(), newUser);
            return true;
        }
    }

    /**
     * Authenticates a student's attempt to login if username and password exist in Student directory
     * @param username String of student's username
     * @param password String of student's attempted password entry
     * @return Student that is logged in if authenticated, else return null
     */
    public Student loginStudent(String username, String password) {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidArgumentException("Invalid String Used for Username or Password");
        } else {
            Student userAttempt = students.get(username);
            if (userAttempt != null) {
                if (userAttempt.authenticate(password)) {
                    return userAttempt;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    public void uploadMajor(String file){
        JSONParser parser = new JSONParser();
        Major newMajor = new Major();
        try {
            JSONObject majorObject = (JSONObject)parser.parse(new FileReader(file));

            String title = (String) majorObject.get("title");
            newMajor.title = title;

            JSONArray courses = (JSONArray) majorObject.get("courses");
            Iterator<String> courseitr = courses.iterator();
            while (courseitr.hasNext()) {
                String courseTitle = courseitr.next();
                Course newCourse = new Course();
                newCourse.setCourseDiscAndNum(courseTitle);
                newMajor.addCourse(newCourse);
            }

            JSONArray grouping = (JSONArray) majorObject.get("grouping");
            Iterator<JSONArray> groupingItr = grouping.iterator();
            while(groupingItr.hasNext()){
                JSONArray chooseOne = (JSONArray) groupingItr.next();
                Iterator<String> chooseCourseItr = chooseOne.iterator();
                List<Course> chooseCourseList = new ArrayList<>();
                while(chooseCourseItr.hasNext()) {
                    Course c = new Course();
                    c.setCourseDiscAndNum(chooseCourseItr.next());
                    chooseCourseList.add(c);
                }
                newMajor.addChooseOne(chooseCourseList);
            }
        }
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        majorDirectory.put(newMajor.title, newMajor);
    }
    public List<Course> viewCurrentCourses(String name){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).getCurrentCourses();
    }

    public boolean addCurrentCourse(String name, Course course){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).addCurrentCourses(course);
    }

    public boolean addPastCourse(String name, Course course){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).addCoursesTaken(course);
    }

    public boolean addFutureCourse(String name, Course course){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).addCoursesPlanned(course);
    }

    /**************************** GETTERS AND SETTERS     ****************************/
    public Map<String, Student> getStudents() {
        return students;
    }
    public Map<String, Major> getMajorDirectory() { return majorDirectory; }

    public void setStudents(Map<String, Student> users) {
        this.students = users;
    }


}
