package practicum;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTaskManagerTest {
    @Test
    void TaskWithIDShouldBeEqual() {   //тест проверки, что экземпляры класса Task равны друг другу, если равен их id;
        Task task1 = new Task("Title1", "Description1", Status.NEW);
        task1.setId(1);
        Task task2 = new Task("Title2", "Description2", Status.IN_PROGRESS);
        task2.setId(1);
        assertEquals(task1, task2);
    }

    @Test
    void EpicNotBeItsOwnSubtask() {
        InMemoryTaskManager manager = new InMemoryTaskManager();  //тест на проверку что Эпик не может быть сам у себя подзадачей

        Epic epic = new Epic("Title1", "Epic1");
        manager.createEpic(epic);

        Subtask subtask = new Subtask("Subtask1", "Subtask1", Status.NEW, epic.getId());
        subtask.setId(epic.getId());

        manager.createSubtask(subtask);

        boolean error = subtask.getId() == subtask.getEpicId();
        assertFalse(error, "Эпик не должен иметь одинаковый ID  с подзадачей");

    }

    @Test
    void SubtaskNotBeItsOwnEpic() {
        InMemoryTaskManager manager = new InMemoryTaskManager();  //тест на проверку что подзадача не может быть сам у себя эпиком

        Epic epic = new Epic("Title1", "Epic1");
        manager.createEpic(epic);

        Subtask subtask = new Subtask("Subtask1", "Subtask1", Status.NEW, 1);
        subtask.setId(epic.getId());

        manager.createSubtask(subtask);

        boolean error = subtask.getId() == subtask.getEpicId();
        assertFalse(error, "Эпик не должен иметь одинаковый ID  с подзадачей");

    }

    @Test
    void addNewTask() {
        InMemoryTaskManager manager = new InMemoryTaskManager();
        Task task = new Task("Title1", "description1", Status.NEW);

        Task createdTask = manager.createTask(task);  // Возвращаем сам объект задачи
        final int taskId = createdTask.getId();

        final Task savedTask = manager.getTask(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");
        final List<Task> tasks = manager.getAllTasks();
        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }
}