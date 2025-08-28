package io.github.mortenjenne;
import io.github.mortenjenne.util.FileDataStore;

    public class TaskManager {

        private TaskList toDoList;
        private UserInterface ui;
        private boolean appRunning;
        private final String path = "data/todo.csv";
        private final String header = "Description, Done";

        public TaskManager() {
            this.toDoList = new TaskList();
            this.ui = new UserInterface();
            this.appRunning = true;
        }

        public void start(){

            while (appRunning){
                ui.showMenu();
                String input = ui.readInput("Awaiting command...");

                switch (input){
                    case "1":
                        showToDoList();
                        break;
                    case "2":
                        addToDo();
                        break;
                    case "3":
                        markToDoAsDone();
                        break;
                    case "4":
                        removeToDo();
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

        private void showToDoList() {
            toDoList.printTask();
        }

        private void addToDo() {
            String input = ui.readInput("Add a task, by writing a description.");
            if (input == null || input.trim().isEmpty()) {
                ui.printMessage("Task description cannot be empty. Try again.");
            } else {
                toDoList.addTask(input);
                ui.printMessage("Task added.");
            }
        }

        private void markToDoAsDone() {
            toDoList.displayItems();
            int index = ui.promptNumeric("Which task should be marked as done (give the number)");
            index --;
            if(isValidTaskNumber(index)){
                toDoList.markTaskAsDone(index);
            }
        }

        private void removeToDo(){
            toDoList.displayItems();
            int index = ui.promptNumeric("Which task should be removed (give the number)");
            index --;
            if(isValidTaskNumber(index)){
                ui.printMessage("Removed task: " + toDoList.getItem(index).getDescription());
                toDoList.remove(index);
            }
        }

        private void quit() {
            ui.printMessage("Thank you for using ToDoApp");
            itemLoader.saveData(toDoList.getItems(),this.path,this.header);
            appRunning = false;
        }

        private boolean isValidTaskNumber(int index){
            if(index >= 0 && index < toDoList.size()){
                return true;
            } else {
                ui.printMessage("Invalid task number. Please enter a number between 1 and " + toDoList.size());
                return false;
            }
        }
    }


