package llm.iteration3.thingsboard.service.impl;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import llm.iteration3.thingsboard.dto.SaveTelemetryRequest;
import llm.iteration3.thingsboard.dto.TelemetryResponse;
import llm.iteration3.thingsboard.model.TelemetryPoint;
import llm.iteration3.thingsboard.repository.TelemetryRepository;
import llm.iteration3.thingsboard.service.TelemetryService;

public class DefaultTelemetryService implements TelemetryService {
    private final TelemetryRepository repository;
    private final Clock clock;

    public DefaultTelemetryService(TelemetryRepository repository, Clock clock) {
        this.repository = repository;
        this.clock = clock;
    }

    @Override
    public TelemetryResponse save(String entityType, String entityId, SaveTelemetryRequest request) {
        validateRequest(request);
        String entityKey = entityType + ":" + entityId;
        repository.save(entityKey, toPoints(request));
        return new TelemetryResponse(entityKey, request.getValues().size());
    }

    @Override
    public List<TelemetryPoint> latest(String entityType, String entityId) {
        return repository.findLatest(entityType + ":" + entityId);
    }

    private void validateRequest(SaveTelemetryRequest request) {
        if (request == null || request.getValues() == null || request.getValues().isEmpty()) {
            throw new IllegalArgumentException("Telemetry values are required");
        }
    }

    private List<TelemetryPoint> toPoints(SaveTelemetryRequest request) {
        long timestamp = request.getTimestamp() == null ? clock.millis() : request.getTimestamp();
        List<TelemetryPoint> points = new ArrayList<>();
        for (Map.Entry<String, String> entry : request.getValues().entrySet()) {
            points.add(new TelemetryPoint(entry.getKey(), entry.getValue(), timestamp));
        }
        return points;
    }
}
