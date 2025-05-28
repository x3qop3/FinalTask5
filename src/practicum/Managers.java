<<<<<<< HEAD
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
=======
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
>>>>>>> 532a33049913d2bfa7cd8a82e3dc70708a15a2fa
