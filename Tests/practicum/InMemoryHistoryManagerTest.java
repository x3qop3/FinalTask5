package practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    private InMemoryHistoryManager historyManager;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
        task1 = new Task("Title1", "Description1", Status.NEW);
        task2 = new Task("Title2", "Description2", Status.IN_PROGRESS);
    }

    @Test
    void addShouldAddTaskToHistory() {
        historyManager.add(task1);
        final List<Task> history = historyManager.getHistory();

        assertNotNull(history, "История не должна быть null");
        assertEquals(1, history.size(), "История должна содержать 1 задачу");
        assertEquals(task1, history.getFirst(), "Задача в истории не совпадает с добавленной");
    }


    @Test
    void removeShouldDeleteTaskFromHistory() {
        historyManager.add(task1);
        historyManager.add(task2);

        historyManager.remove(task1.getId());
        List<Task> history = historyManager.getHistory();

        assertEquals(1, history.size(), "История должна содержать 1 задачу после удаления");
        assertEquals(task2, history.getFirst(), "Оставшаяся задача должна быть task2");
    }

    @Test
    void getHistoryShouldReturnEmptyListForEmptyHistory() {
        List<Task> history = historyManager.getHistory();

        assertTrue(history.isEmpty(), "История должна быть пустой для нового менеджера");
    }

    @Test
    void historyShouldNotContainDuplicates() {
        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);

        List<Task> history = historyManager.getHistory();

        assertEquals(1, history.size(), "История не должна содержать дубликатов");
    }

    @Test
    void removeNonExistentTaskShouldNotChangeHistory() {
        historyManager.add(task1);
        historyManager.remove(999); // Несуществующий ID

        assertEquals(1, historyManager.getHistory().size(),
                "История не должна измениться при удалении несуществующей задачи");
    }
}