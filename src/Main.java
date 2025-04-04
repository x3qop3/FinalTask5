public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();


        Task task1 = new Task("11", "asdfg", Status.NEW);
        manager.createTask(task1);


        Epic epic1 = new Epic("222", "qwerty");
        manager.createEpic(epic1);

        Subtask subtask1 = new Subtask("33333", "zxcvbn", Status.NEW, epic1.getId());
        manager.createSubtask(subtask1);


        System.out.println("Все задачи:");
        manager.getAllTasks().forEach(System.out::println);

        System.out.println("\nВсе эпики:");
        manager.getAllEpics().forEach(System.out::println);

        System.out.println("\nВсе подзадачи:");
        manager.getAllSubtasks().forEach(System.out::println);


        subtask1.setStatus(Status.DONE);
        manager.updateSubtask(subtask1);
        System.out.println("\nПосле изменения статуса подзадачи:");
        System.out.println(manager.getEpic(epic1.getId()));
    }
}