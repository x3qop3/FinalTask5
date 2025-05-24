package practicum;

import java.nio.file.Path;

public class Managers {
    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }
}
