package io.github.mortenjenne;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskList<T extends Task> {
    private List<T> tasks;

    public TaskList(){
        tasks = new ArrayList<>();
    }

    public void addTask(T addTask) {
        tasks.add(addTask);
    }

    public List<T> getTasks() {
        return tasks;
    }

    public List<T> filterTaskByTitle(String title) {
        return tasks.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
}
