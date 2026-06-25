package llm.iteration2.thingsboard.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelemetryRepository {
    private final Map<String, List<Map<String, Object>>> rows = new HashMap<>();

    public void insert(String entityKey, Map<String, Object> body) {
        rows.computeIfAbsent(entityKey, ignored -> new ArrayList<>()).add(body);
    }
}
