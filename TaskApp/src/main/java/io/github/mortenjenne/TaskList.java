package io.github.mortenjenne;

import io.github.mortenjenne.util.FileDataStore;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

public class TaskList<T extends Task> implements Serializable {
    private List<T> tasks;
    FileDataStore<List<T>> dataStore;

    public TaskList(){
        tasks = new ArrayList<>();
        dataStore = new FileDataStore();
    }

    public void addTask(T addTask) {
        if(addTask != null){
            tasks.add(addTask);
        }
    }

    public List<T> getTasks() {
        return new ArrayList<>(tasks);
    }

    public List<T> filterTaskByTitle(String title) {
        return tasks.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<T> filterTaskByDescription(String description) {
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(description.toLowerCase()))
                .collect(Collectors.toList());
    }


    public List<T> sortByDueDate() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
    }

    public List<T> sortByDueDateReversed() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate).reversed())
                .collect(Collectors.toList());
    }

    public List<T> filterByDueToday() {
        return tasks.stream()
                .filter(task -> task.getDueDate().isEqual(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public List<T> getTasksThatAreOverdue() {
        return tasks.stream()
                .filter(task -> task.getDueDate().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public void printTask() {
        if (tasks != null && !tasks.isEmpty()) {
            int counter = 1;
            for (T task : tasks) {
                System.out.println(counter + ":\n" + task);
                counter++;
            }
        } else {
            System.out.println("No tasks added");
        }
    }

    public void removeTask(int index){
        tasks.remove(index);
    }

    public void saveTaskList(){
        String filename = dataStore.save(tasks);
    }

    public void loadTaskList(String filename) {
        List<T> loaded = dataStore.load(filename);
        if (loaded != null) {
            tasks = loaded;
        }
    }

    public T getTask(int index) {
        return tasks.get(index);
    }
}
