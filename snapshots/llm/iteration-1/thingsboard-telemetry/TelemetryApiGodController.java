package llm.iteration1.thingsboard;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plugins/telemetry")
public class TelemetryApiGodController {
    private final Map<String, List<Map<String, Object>>> timeseries = new HashMap<>();
    private final Map<String, Map<String, Object>> attributes = new HashMap<>();
    private final Map<String, List<String>> webSocketSubscriptions = new HashMap<>();

    @PostMapping("/{entityType}/{entityId}/timeseries")
    public Map<String, Object> saveTimeseries(@PathVariable String entityType,
                                              @PathVariable String entityId,
                                              @RequestBody Map<String, Object> payload) {
        String key = entityType + ":" + entityId;
        List<Map<String, Object>> values = timeseries.computeIfAbsent(key, ignored -> new ArrayList<>());
        for (Map.Entry<String, Object> entry : payload.entrySet()) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("ts", Instant.now().toEpochMilli());
            row.put("key", entry.getKey());
            row.put("value", entry.getValue());
            values.add(row);
        }
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("saved", payload.size());
        response.put("entity", key);
        return response;
    }

    @GetMapping("/{entityType}/{entityId}/values/timeseries")
    public List<Map<String, Object>> readTimeseries(@PathVariable String entityType,
                                                    @PathVariable String entityId) {
        return timeseries.getOrDefault(entityType + ":" + entityId, new ArrayList<>());
    }

    @PostMapping("/{entityType}/{entityId}/attributes")
    public Map<String, Object> saveAttributes(@PathVariable String entityType,
                                              @PathVariable String entityId,
                                              @RequestBody Map<String, Object> payload) {
        String key = entityType + ":" + entityId;
        Map<String, Object> current = attributes.computeIfAbsent(key, ignored -> new LinkedHashMap<>());
        current.putAll(payload);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("saved", payload.size());
        response.put("entity", key);
        return response;
    }

    @GetMapping("/{entityType}/{entityId}/values/attributes")
    public Map<String, Object> readAttributes(@PathVariable String entityType,
                                              @PathVariable String entityId) {
        return attributes.getOrDefault(entityType + ":" + entityId, new LinkedHashMap<>());
    }

    @PostMapping("/ws/{sessionId}/subscribe")
    public Map<String, Object> subscribe(@PathVariable String sessionId,
                                         @RequestBody Map<String, Object> body) {
        String entityId = String.valueOf(body.get("entityId"));
        webSocketSubscriptions.computeIfAbsent(sessionId, ignored -> new ArrayList<>()).add(entityId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("sessionId", sessionId);
        response.put("subscriptions", webSocketSubscriptions.get(sessionId));
        return response;
    }

    @PostMapping("/ws/{sessionId}/unsubscribe")
    public Map<String, Object> unsubscribe(@PathVariable String sessionId,
                                           @RequestBody Map<String, Object> body) {
        List<String> subscriptions = webSocketSubscriptions.getOrDefault(sessionId, new ArrayList<>());
        subscriptions.remove(String.valueOf(body.get("entityId")));
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("sessionId", sessionId);
        response.put("subscriptions", subscriptions);
        return response;
    }
}
