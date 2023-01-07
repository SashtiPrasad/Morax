package app.morax.Model.Base;

import java.time.LocalDateTime;
import java.util.Scanner;

public class TestSortFunc {
    /**
     * this class is just to test the Task class
     * and the sort functionality of
     * Category
     */
    private Category taskList;

    public TestSortFunc(String listName, int initialCap) {
        this.taskList = new Category(listName, 0 ,0, Integer.MAX_VALUE);
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("List Name: ");
        String listName = in.next();
        String junk = in.nextLine();
        System.out.print("Initial Capacity: ");
        int initC = in.nextInt(); //assuming this is fine because not what this is testing
        junk = in.nextLine();
        TestSortFunc Morax = new TestSortFunc(listName, initC);

        // testing here //

        // test setting just name
        Task a, b, c, d, e;
        LocalDateTime dateA = LocalDateTime.of(2022, 11, 14, 11, 30);
        LocalDateTime dateB = LocalDateTime.of(2022, 11, 10, 7, 30);
        LocalDateTime dateC = LocalDateTime.of(2022, 12, 3, 11, 0);
        LocalDateTime dateD = LocalDateTime.of(2022, 12, 14, 11, 30);
        LocalDateTime dateE = LocalDateTime.of(2022, 11, 10, 6, 30);
        // date order: e b a c d
        // name order: a e c b d
        // location order: e b d a c
        a = new Task("assignment", "419 8th Street", dateA);
        b = new Task("meeting", "13 Wallaby Way", dateB);
        c = new Task("game", "Campus and Campus", dateC);
        d = new Task("study", "16-1260 Corgo Street", dateD);
        e = new Task("dance", "1010 McKercher Drive", dateE);
        Morax.taskList.addTask(a);
        Morax.taskList.addTask(b);
        Morax.taskList.addTask(c);
        Morax.taskList.addTask(d);
        Morax.taskList.addTask(e);
        String testOutName = "assignment dance game meeting study";
        String testOutLocation = "1010 McKercher Drive 13 Wallaby Way 16-1260 Corgo Street"+
                " 419 8th Street Campus and Campus";
        String testOutDate = dateE + " " + dateB + " " + dateA + " " + dateC + " " + dateD;
        Morax.taskList.sortTaskList(1);
        String output = (Morax.taskList.getTasks().get(0)).getName() + " " +
                Morax.taskList.getTasks().get(1).getName() + " " +
                Morax.taskList.getTasks().get(2).getName() + " " +
                Morax.taskList.getTasks().get(3).getName() + " " +
                Morax.taskList.getTasks().get(4).getName();
        if(!testOutName.equals(output)){
            System.out.println("Test Failed when sorting by name.");
            System.out.println("Expected: " + testOutName);
            System.out.println("Got: " + output + "\n\n");
        }
        Morax.taskList.sortTaskList(2);
        output = (Morax.taskList.getTasks().get(0)).getLocation() + " " +
                Morax.taskList.getTasks().get(1).getLocation() + " " +
                Morax.taskList.getTasks().get(2).getLocation() + " " +
                Morax.taskList.getTasks().get(3).getLocation() + " " +
                Morax.taskList.getTasks().get(4).getLocation();
        if(!testOutLocation.equals(output)){
            System.out.println("Test Failed when sorting by location.");
            System.out.println("Expected: " + testOutLocation);
            System.out.println("Got: " + output + "\n\n");
        }
        Morax.taskList.sortTaskList(3);
        output = (Morax.taskList.getTasks().get(0)).getDate() + " " +
                Morax.taskList.getTasks().get(1).getDate() + " " +
                Morax.taskList.getTasks().get(2).getDate() + " " +
                Morax.taskList.getTasks().get(3).getDate() + " " +
                Morax.taskList.getTasks().get(4).getDate();
        if(!testOutDate.equals(output)){
            System.out.println("Test Failed when sorting by date.");
            System.out.println("Expected: " + testOutDate);
            System.out.println("Got: " + output + "\n\n");
        }
    }
}
