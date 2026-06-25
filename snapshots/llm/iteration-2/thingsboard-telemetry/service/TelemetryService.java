package llm.iteration2.thingsboard.service;

import java.util.LinkedHashMap;
import java.util.Map;
import llm.iteration2.thingsboard.repository.TelemetryRepository;

public class TelemetryService {
    private final TelemetryRepository repository = new TelemetryRepository();

    public Map<String, Object> save(String type, String id, Map<String, Object> body) {
        if (body == null || body.isEmpty()) {
            throw new IllegalArgumentException("Telemetry body is empty");
        }
        repository.insert(type + ":" + id, body);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("entityType", type);
        response.put("entityId", id);
        response.put("saved", body.size());
        return response;
    }
}
