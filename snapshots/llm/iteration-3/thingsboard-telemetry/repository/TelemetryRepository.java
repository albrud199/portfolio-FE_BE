package llm.iteration3.thingsboard.repository;

import java.util.List;
import llm.iteration3.thingsboard.model.TelemetryPoint;

public interface TelemetryRepository {
    void save(String entityKey, List<TelemetryPoint> points);

    List<TelemetryPoint> findLatest(String entityKey);
}
