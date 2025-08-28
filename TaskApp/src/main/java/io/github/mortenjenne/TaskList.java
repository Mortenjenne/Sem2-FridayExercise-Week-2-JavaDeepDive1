package io.github.mortenjenne;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskList<T extends Task> implements Iterable<T> {
    private List<T> tasks;

    public TaskList(){
        tasks = new ArrayList<>();
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

    public void printTask(){
        if(tasks != null && !tasks.isEmpty()){
            tasks.forEach(System.out::println);
        } else {
            System.out.println("No tasks added");
        }

    }

    @Override
    public Iterator<T> iterator() {
        return tasks.iterator();
    }
}
