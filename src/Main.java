public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Создаем задачи
        Task task1 = new Task("Помыть посуду", "Помыть всю посуду вечером", Status.NEW);
        manager.createTask(task1);

        // Создаем эпик с подзадачами
        Epic epic1 = new Epic("Переезд", "Организация переезда в новый офис");
        manager.createEpic(epic1);

        Subtask subtask1 = new Subtask("Собрать коробки", "Купить и собрать коробки", Status.NEW, epic1.getId());
        manager.createSubtask(subtask1);

        // Выводим информацию
        System.out.println("Все задачи:");
        manager.getAllTasks().forEach(System.out::println);

        System.out.println("\nВсе эпики:");
        manager.getAllEpics().forEach(System.out::println);

        System.out.println("\nВсе подзадачи:");
        manager.getAllSubtasks().forEach(System.out::println);

        // Меняем статусы
        subtask1.setStatus(Status.DONE);
        manager.updateSubtask(subtask1);
        System.out.println("\nПосле изменения статуса подзадачи:");
        System.out.println(manager.getEpic(epic1.getId()));
    }
}