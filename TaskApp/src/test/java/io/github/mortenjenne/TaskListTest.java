package io.github.mortenjenne;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest<T> {
    TaskList<Task> taskList;
    Task gardenTask;
    Task gardenTask1;
    Task gardenTask2;
    Task gardenTask3;
    Task gardenTask4;
    Task gardenTask5;

    @BeforeEach
    void setUp() {
        taskList = new TaskList<>();

        gardenTask = GardenTask.builder()
                .title("Water the plants")
                .description("Use the hose to water all garden beds")
                .dueDate(LocalDate.now().plusDays(1))
                .gardenLocation("Whole garden")
                .build();

        gardenTask1 = GardenTask.builder()
                .title("Build raised garden bed")
                .description("Construct a new raised bed in the backyard")
                .dueDate(LocalDate.now().plusDays(3))
                .gardenLocation("Backyard")
                .build();

        gardenTask2 = GardenTask.builder()
                .title("Plant new herbs")
                .description("Add basil, thyme, and parsley to the herb garden")
                .dueDate(LocalDate.now().plusDays(2))
                .gardenLocation("Herb garden")
                .build();

        gardenTask3 = GardenTask.builder()
                .title("Mow the lawn")
                .description("Cut the grass evenly and edge the borders for a clean look")
                .dueDate(LocalDate.now().plusDays(5))
                .gardenLocation("Front and backyard")
                .build();

        gardenTask4 = GardenTask.builder()
                .title("Trim the hedges")
                .description("Shape and tidy up the hedges along the fence line")
                .dueDate(LocalDate.now())
                .gardenLocation("Side yard")
                .build();

        taskList.addTask(gardenTask1);
        taskList.addTask(gardenTask3);
        taskList.addTask(gardenTask2);
        taskList.addTask(gardenTask4);
        taskList.addTask(gardenTask);
    }

    @Test
    @DisplayName("Add tasks to the list")
    void testAddingTask(){
        int expected = 5;
        int actual = taskList.getTasks().size();
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Filter tasks based on a keyword in the title.")
    void testFilterByTitle(){
        String expected = gardenTask.getTitle();
        List<Task> filteredByTitle = taskList.filterTaskByTitle("Water");
        String actual = filteredByTitle.get(0).getTitle();
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Filter tasks based on a keyword in the description.")
    void testFilterByDescription(){
        String expected = gardenTask1.getDescription();
        List<Task> filteredByDescription = taskList.filterTaskByDescription("raised");
            String actual = filteredByDescription.get(0).getDescription();
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Sort tasks by due date.")
    void testSortingbyDueDate(){
        LocalDate expected = LocalDate.now();
        List<Task> sortedByDueDate = taskList.sortByDueDate();
        LocalDate actual = sortedByDueDate.get(0).getDueDate();
        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Get tasks that are due today.")
    void testGettingTaskThatAreDueToday(){
        LocalDate expected = LocalDate.now();
        List<Task> tasksDueToday = taskList.filterByDueToday();
        LocalDate actual = tasksDueToday.get(0).getDueDate();

        assertFalse(tasksDueToday.isEmpty(), "No tasks due today found");
        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Get tasks that are overdue..")
    void testGettingTasksThatAreOverDue(){
        gardenTask5 = GardenTask.builder()
                .title("Harvest fresh herbs")
                .description("Pick basil, parsley, and thyme for tonight’s dinner")
                .dueDate(LocalDate.now().minusDays(2))
                .gardenLocation("Herb garden")
                .build();

        taskList.addTask(gardenTask5);

        List<Task> taskOverDue = taskList.getTasksThatAreOverdue();
        assertFalse(taskOverDue.isEmpty(), "No overdue tasks found");

        for (Task task : taskOverDue) {
            assertTrue(LocalDate.now().isAfter(task.getDueDate()), "Task is not overdue: " + task.getTitle());
        }
    }

    @Test
    @DisplayName("Print the list of tasks.")
    void testPrintingTask(){
        taskList.printTask();

    }

}