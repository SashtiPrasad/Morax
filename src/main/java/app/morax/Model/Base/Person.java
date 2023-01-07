
package app.morax.Model.Base;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;

public class Person implements Serializable {

    /** Person's name */
    private String name;
    /** list of tasks person is involved with */
    private LinkedList<Task> taskList ;

    /**
     * Initialize a Person
     *
     * @param personName the person's name
     */
    public Person(String personName) {
        this.name = personName;
        this.taskList = new LinkedList<>();
    }

    /**
     * Return person's name variable
     *
     * @return person name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Edit the name of the person
     *
     * @param newName the name to be set
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Add a task for this person
     *
     * @param tas the new task
     */
    public void addTask(Task tas){
        this.taskList.addLast(tas);
    }

    /**
     * Remove task for this person
     *
     * @param taskName name of task to remove
     */
    public void removeTask(String taskName){
        boolean done = false;
        ListIterator<Task> iter= this.taskList.listIterator();
        while (iter.hasNext() && !done){
            if (iter.next().getName().equals(taskName)){
                iter.remove();
                done = true;
            }
        }
    }

    /**
     * Return a string of the person's information
     *
     * @return string - person info
     */
    public String toString() {
        return (this.name);
    }
}
