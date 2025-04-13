package practicum;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Integer> subtaskId;

    public Epic(String title, String description) {
        super(title, description, Status.NEW);
        this.subtaskId = new ArrayList<>();
    }

    public List<Integer> getSubtaskIds() {
        return subtaskId;
    }

    public void addSubtaskId(int id) {
        subtaskId.add(id);
    }
}