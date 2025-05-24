package practicum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileBackedTaskManager extends InMemoryTaskManager {
    private final Path file;


    public FileBackedTaskManager(Path file) {
        this.file = file;
    }

    private enum TaskType {
        TASK,
        EPIC,
        SUBTASK
    }

    protected void save() {
        try {
            StringBuilder content = new StringBuilder();
            content.append("id,type,name,status,description,epic\n");

            for (Task task : super.getAllTasks()) {
                content.append(taskToString(task)).append("\n");
            }

            for (Epic epic : super.getAllEpics()) {
                content.append(taskToString(epic)).append("\n");
            }

            for (Subtask subtask : super.getAllSubtasks()) {
                content.append(taskToString(subtask)).append("\n");
            }

            Files.writeString(file, content.toString());
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка сохранения в файл", e);
        }
    }

    private String taskToString(Task task) {
        TaskType type = getTaskType(task);
        String epicId = (type == TaskType.SUBTASK) ?
                String.valueOf(((Subtask) task).getEpicId()) : "";

        return String.join(",",
                String.valueOf(task.getId()),
                type.name(),
                task.getTitle(),
                task.getStatus().name(),
                task.getDescription(),
                epicId
        );
    }

    private TaskType getTaskType(Task task) {
        if (task instanceof Epic) return TaskType.EPIC;
        if (task instanceof Subtask) return TaskType.SUBTASK;
        return TaskType.TASK;
    }

    public static FileBackedTaskManager loadFromFile(Path file) {
        FileBackedTaskManager manager = new FileBackedTaskManager(file);

        try {
            String content = Files.readString(file);
            String[] lines = content.split("\n");

            for (int i = 1; i < lines.length; i++) {
                String line = lines[i].trim();
                if (!line.isEmpty()) {
                    Task task = taskFromString(line);
                    if (task != null) {
                        if (task instanceof Epic) {
                            manager.createEpic((Epic) task);
                        } else if (task instanceof Subtask) {
                            manager.createSubtask((Subtask) task);
                        } else {
                            manager.createTask(task);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка загрузки из файла", e);
        }

        return manager;
    }

    private static Task taskFromString(String value) {
        String[] parts = value.split(",");
        if (parts.length < 6) return null;

        int id = Integer.parseInt(parts[0]);
        TaskType type = TaskType.valueOf(parts[1]);
        String name = parts[2];
        Status status = Status.valueOf(parts[3]);
        String description = parts[4];

        switch (type) {
            case TASK:
                Task task = new Task(name, description, status);
                task.setId(id);
                return task;

            case EPIC:
                Epic epic = new Epic(name, description);
                epic.setId(id);
                epic.setStatus(status);
                return epic;

            case SUBTASK:
                int epicId = Integer.parseInt(parts[5]);
                Subtask subtask = new Subtask(name, description, status, epicId);
                subtask.setId(id);
                return subtask;

            default:
                return null;
        }
    }

    @Override
    public Task createTask(Task task) {
        Task created = super.createTask(task);
        save();
        return created;
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void deleteTask(int id) {
        super.deleteTask(id);
        save();
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        save();
    }

    @Override
    public Epic createEpic(Epic epic) {
        Epic created = super.createEpic(epic);
        save();
        return created;
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void deleteEpic(int id) {
        super.deleteEpic(id);
        save();
    }

    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
        save();
    }

    @Override
    public Subtask createSubtask(Subtask subtask) {
        Subtask created = super.createSubtask(subtask);
        save();
        return created;
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
        save();
    }

    @Override
    public void deleteSubtask(int id) {
        super.deleteSubtask(id);
        save();
    }

    @Override
    public void deleteAllSubtasks() {
        super.deleteAllSubtasks();
        save();
    }
}

class ManagerSaveException extends RuntimeException {
    public ManagerSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}