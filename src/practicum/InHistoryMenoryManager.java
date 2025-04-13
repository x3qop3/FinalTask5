package practicum;

import java.util.ArrayList;
import java.util.List;

public class InHistoryMenoryManager implements HistoryManager {
    private final List<Task> history = new ArrayList<>(10);

    @Override
    public void add(Task task) {
        if(history.size()==10){
            history.remove(0);
        }
        Task copy = new Task(task.getTitle(), task.getDescription(), task.getStatus());
        copy.setId(task.getId());
        history.addLast(copy);
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }

}
