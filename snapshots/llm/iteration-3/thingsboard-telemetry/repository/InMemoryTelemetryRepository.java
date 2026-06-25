package llm.iteration3.thingsboard.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import llm.iteration3.thingsboard.model.TelemetryPoint;

public class InMemoryTelemetryRepository implements TelemetryRepository {
    private final Map<String, List<TelemetryPoint>> store = new HashMap<>();

    @Override
    public void save(String entityKey, List<TelemetryPoint> points) {
        store.computeIfAbsent(entityKey, ignored -> new ArrayList<>()).addAll(points);
    }

    @Override
    public List<TelemetryPoint> findLatest(String entityKey) {
        return new ArrayList<>(store.getOrDefault(entityKey, new ArrayList<>()));
    }
}
