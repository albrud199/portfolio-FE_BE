package llm.iteration3.thingsboard.service;

import llm.iteration3.thingsboard.dto.SubscriptionRequest;
import llm.iteration3.thingsboard.model.TelemetrySubscription;

public interface SubscriptionService {
    TelemetrySubscription subscribe(String sessionId, SubscriptionRequest request);

    void unsubscribe(String sessionId, String entityId);
}
