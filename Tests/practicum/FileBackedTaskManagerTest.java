package practicum;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class FileBackedTaskManagerTest {
    private FileBackedTaskManager taskManager;
    private Path tempFile;
    private Task task;
    private Epic epic;
    private Subtask subtask;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("tasks", ".csv");
        taskManager = new FileBackedTaskManager(tempFile);
        task = new Task("Test1 Task1", "Test1 Description1", Status.NEW);
        epic = new Epic("Test1 Epic1", "Test1 Epic1 Description1");
        subtask = new Subtask("Test1 Subtask1", "Test1 Subtask1 Description1", Status.NEW, 1);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void shouldSaveAndLoadEmptyManager() throws IOException {
        try {
            taskManager.save();

            FileBackedTaskManager loadedManager = FileBackedTaskManager.loadFromFile(tempFile);
            assertTrue(loadedManager.getAllTasks().isEmpty(), "Список задач должен быть пустым");
            assertTrue(loadedManager.getAllEpics().isEmpty(), "Список эпиков должен быть пустым");
            assertTrue(loadedManager.getAllSubtasks().isEmpty(), "Список подзадач должен быть пустым");
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void shouldSaveTaskToFile() throws IOException {
        Path tempFile = Files.createTempFile("tasks", ".csv");
        try {
            FileBackedTaskManager manager = new FileBackedTaskManager(tempFile);
            Task task = new Task("Test title", "Test description", Status.NEW);
            manager.createTask(task);
            String fileContent = Files.readString(tempFile);
            assertTrue(fileContent.contains("Test description"), "Файл должен содержать текст задачи");
            assertTrue(fileContent.contains("Test title"), "Файл должен содержать название задачи");
            assertTrue(fileContent.contains("NEW"), "Файл должен содержать статус задачи");
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void shouldSaveEpicToFile() throws IOException {
        Path tempFile = Files.createTempFile("tasks", ".csv");
        try {
            FileBackedTaskManager manager = new FileBackedTaskManager(tempFile);
            Epic epic = new Epic("Test Epic", "Test description EPIC" );
            manager.createEpic(epic);
            String fileContent = Files.readString(tempFile);
            assertTrue(fileContent.contains("Test description EPIC"), "Файл должен содержать текст эпика");
            assertTrue(fileContent.contains("Test description EPIC"), "Файл должен содержать название эпика");
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }


}