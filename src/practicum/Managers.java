package practicum;

public class Managers {
    public static HistoryManager getDefaultHistory() {
        return new InHistoryMenoryManager();
    }
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }
}
