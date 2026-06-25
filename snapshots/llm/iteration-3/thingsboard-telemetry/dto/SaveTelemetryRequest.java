package llm.iteration3.thingsboard.dto;

import java.util.Map;

public class SaveTelemetryRequest {
    private Map<String, String> values;
    private Long timestamp;

    public Map<String, String> getValues() {
        return values;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
