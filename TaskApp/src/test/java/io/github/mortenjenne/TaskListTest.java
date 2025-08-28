package io.github.mortenjenne;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest<T> {
    TaskList<Task> taskList;
    Task gardenTask;
    Task gardenTask1;

    @BeforeEach
    void setUp() {
        taskList = new TaskList<>();

        gardenTask = Task.builder()
                .title("Water the plants")
                .description("Use the hose to water all garden beds")
                .dueDate(LocalDate.now().plusDays(1))
                .build();

        gardenTask1 = Task.builder()
                .title("Build raised garden bed")
                .description("Construct a new raised bed in the backyard")
                .dueDate(LocalDate.now().plusDays(3))
                .build();
    }

    @Test
    @DisplayName("Add tasks to the list")
    void testAddingTask(){
        int expected = 1;
        taskList.addTask(gardenTask);
        int actual = taskList.getTasks().size();
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Filter tasks based on a keyword in the title or description.")
    void testFilterByTitleOrDescription(){
        taskList.addTask(gardenTask);
        taskList.addTask(gardenTask1);

        String expected = gardenTask1.getTitle();
        List<Task> filteredByTitle = taskList.filterTaskByTitle("Water the plants");
        String actual = filteredByTitle.get(0).getTitle();
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Sort tasks by due date.")
    void testSortingbyDueDate(){

    }

    @Test
    @DisplayName("Get tasks that are due today.")
    void testGetingTaskThatAreDueToday(){

    }

    @Test
    @DisplayName("Get tasks that are overdue..")
    void testGettingTasksThatAreOverDue(){

    }

    @Test
    @DisplayName("Print the list of tasks.")
    void testPrintingTask(){

    }

}