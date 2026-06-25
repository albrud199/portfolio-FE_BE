package llm.iteration3.thingsboard.model;

public class TelemetryPoint {
    private final String key;
    private final String value;
    private final long timestamp;

    public TelemetryPoint(String key, String value, long timestamp) {
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
