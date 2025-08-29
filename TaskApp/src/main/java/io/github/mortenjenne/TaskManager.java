package io.github.mortenjenne;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

        private TaskList taskList;
        private UserInterface ui;
        private List<Task> toDoList;
        private boolean appRunning;
        private final String path = "data/todo.csv";
        private final String header = "Description, Done";

        public TaskManager() {
            this.taskList = new TaskList();
            toDoList = new ArrayList<>();
            this.ui = new UserInterface();
            this.appRunning = true;
        }

        public void start(){

            while (appRunning){
                ui.showMenu();
                String input = ui.readInput("Awaiting command...");

                switch (input){
                    case "1":
                        printTasks();
                        break;
                    case "2":
                        addToDo();
                        break;
                    case "3":
                        //markToDoAsDone();
                        break;
                    case "4":
                        removeTask();
                        break;
                    case "x":
                        quit();
                        break;
                    default:
                        ui.printMessage("Didnt recognize this command, try again.");
                        break;
                }
            }
        }

        private void printTasks(){
            taskList.printTask();
        }

    private void addToDo() {
        ui.printMessage("Add task:");

        String title;
        do {
            title = ui.readInput("Title? ");
            if (title == null || title.trim().isEmpty()) {
                ui.printMessage("Task title cannot be empty. Try again.");
            }
        } while (title == null || title.trim().isEmpty());

        String description = ui.readInput("Description? ");
        if (description == null) description = "";

        String gardenLocation = ui.readInput("Location? ");
        if (gardenLocation == null) gardenLocation = "";

        LocalDate parsedDate = null;
        while (parsedDate == null) {
            String dueDateInput = ui.readInput("Due date? Write in YEAR-MM-DD: ");
            try {
                parsedDate = LocalDate.parse(dueDateInput);
            } catch (Exception e) {
                ui.printMessage("Invalid date format. Please use YEAR-MM-DD.");
            }
        }

        taskList.addTask(GardenTask.builder()
                .title(title)
                .description(description)
                .dueDate(parsedDate)
                .gardenLocation(gardenLocation)
                .build()
        );

        ui.printMessage("Task added: " + title + " (Due: " + parsedDate + ")");
    }

        private void removeTask(){
            taskList.printTask();
            int index = ui.promptNumeric("Which task should be removed (give the number)");
            index --;
            if(isValidTaskNumber(index)){
                ui.printMessage("Removed task: " + taskList.getTask(index).getTitle());
                taskList.removeTask(index);
            }
        }

        private void quit() {
            ui.printMessage("Thank you for using TaskApp");
            taskList.saveTaskList();
            appRunning = false;
        }

        private boolean isValidTaskNumber(int index){
            if(index >= 0 && index < taskList.getTasks().size()){
                return true;
            } else {
                ui.printMessage("Invalid task number. Please enter a number between 1 and " + taskList.getTasks().size());
                return false;
            }
        }
    }


