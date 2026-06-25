package llm.iteration3.thingsboard.service;

import java.util.List;
import llm.iteration3.thingsboard.dto.SaveTelemetryRequest;
import llm.iteration3.thingsboard.dto.TelemetryResponse;
import llm.iteration3.thingsboard.model.TelemetryPoint;

public interface TelemetryService {
    TelemetryResponse save(String entityType, String entityId, SaveTelemetryRequest request);

    List<TelemetryPoint> latest(String entityType, String entityId);
}
