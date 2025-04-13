package practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class InHistoryMemoryManagerTest {


    @Test
    void add() {
        Task task = new Task("Title1", "description1", Status.NEW);  // Создаем задачу с нужными параметрами

        InHistoryMenoryManager historyManager = new InHistoryMenoryManager();

        historyManager.add(task);

        final List<Task> history = historyManager.getHistory();

        assertNotNull(history, "После добавления задачи, история не должна быть пустой.");
        assertEquals(1, history.size(), "После добавления задачи, история не должна быть пустой.");

    }


    @Test
    void HistoryWithUpdate(){
        InHistoryMenoryManager historyManager = new InHistoryMenoryManager();

        Task  task1 = new Task("Title1", "description1", Status.NEW);
        historyManager.add(task1);

        task1.setTitle("new Title");
        task1.setDescription("new description");
        task1.setStatus(Status.IN_PROGRESS);

        historyManager.add(task1);

        List<Task> history = historyManager.getHistory();

        assertEquals("Title1", history.get(0).getTitle());
        assertEquals("new Title", history.get(1).getTitle());


    }


}
