package llm.iteration3.thingsboard.service.impl;

import java.util.HashMap;
import java.util.Map;
import llm.iteration3.thingsboard.dto.SubscriptionRequest;
import llm.iteration3.thingsboard.model.TelemetrySubscription;
import llm.iteration3.thingsboard.service.SubscriptionService;

public class DefaultSubscriptionService implements SubscriptionService {
    private final Map<String, TelemetrySubscription> subscriptions = new HashMap<>();

    @Override
    public TelemetrySubscription subscribe(String sessionId, SubscriptionRequest request) {
        if (request == null || request.getEntityId() == null) {
            throw new IllegalArgumentException("Entity id is required");
        }
        TelemetrySubscription subscription = new TelemetrySubscription(
                sessionId,
                request.getEntityType(),
                request.getEntityId(),
                request.getKeyFilter());
        subscriptions.put(sessionId + ":" + request.getEntityId(), subscription);
        return subscription;
    }

    @Override
    public void unsubscribe(String sessionId, String entityId) {
        subscriptions.remove(sessionId + ":" + entityId);
    }
}
