
package app.morax.Model.Base;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Model for a generic task within the system
 */
public class Task implements Serializable {

    /**
     * Task Name
     */
    private String name;
    private String location;
    private LocalDateTime date;

    //measured in hours
    private Category category;
    private final int priority;
    private int timeToComplete;
    private boolean isMeeting = false;

    /**
     * Initialize a task
     *
     * @param taskName the person's name
     */
    public Task(String taskName) {
        this.name = taskName;
        this.location = null;
        this.date = null;
        this.priority = 0;
        this.timeToComplete = 0;
    }

    public Task(LocalDateTime meetingDate, Person p) {
        this.name = "Meeting with " + p.getName();
        this.location = null;
        this.date = meetingDate;
        this.priority = 0;
        this.timeToComplete = 0;
        this.isMeeting = true;
    }

    public Task(String taskName, String taskLocation) {
        this.name = taskName;
        this.location = taskLocation;
        this.date = null;
        this.priority = 0;
        this.timeToComplete = 0;
    }

    public Task(String taskName, LocalDateTime taskDate) {
        this.name = taskName;
        this.location = null;
        this.date = taskDate;
        this.priority = 0;
        this.timeToComplete = 0;
    }

    public Task(String taskName, String taskLocation, LocalDateTime taskDate) {
        this.name = taskName;
        this.location = taskLocation;
        this.date = taskDate;
        this.priority = 0;
        this.timeToComplete = 0;
    }

    public Task(String taskName, int time) {
        this.name = taskName;
        this.location = null;
        this.date = null;
        this.priority = 0;
        this.timeToComplete = 0;
    }

    public Task(String taskName, String taskLocation, LocalDateTime taskDate, int priority, int timeToComplete) {
        this.name = taskName;
        this.location = taskLocation;
        this.date = taskDate;
        this.priority = priority;
        this.timeToComplete = timeToComplete;
    }

    /**
     * Return task's name variable
     *
     * @return task name
     */
    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public int getPriority() {
        return priority;
    }

    public int getTimeToComplete() {
        return timeToComplete;
    }

    /**
     * Edit the name of the task
     *
     * @param newName the name to be set
     */
    public void setName(String newName) {
        this.name = newName;
    }

    public void setDate(LocalDateTime newDate) {
        this.date = newDate;
    }

    public void setLocation(String newLocation) {
        this.location = newLocation;
    }

    /**
     * Return a string of the task's information
     *
     * @return string - task info
     */
    public String toString() {
        return ("\nTask: " + this.name +
                "\nLocation: " + this.location +
                "\nDate: " + this.date +
                "\nCategory: " + this.category + "\n");
    }

    public void setCategory(Category cat) {
        this.category = cat;
    }

    public Category getCategory() {
        return category;
    }

    public void removeCategory(Category cat) {
        this.category = null;
    }

    public boolean hasCategory() {
        return category != null;
    }

    public boolean isMeeting() {
        return isMeeting;
    }

    public void setTimeToComplete(int t) {this.timeToComplete = t;}
}
