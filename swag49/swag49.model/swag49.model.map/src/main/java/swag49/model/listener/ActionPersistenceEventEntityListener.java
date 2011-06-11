package swag49.model.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import swag49.model.Action;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PrePersist;

public class ActionPersistenceEventEntityListener {

    protected static final Logger logger = LoggerFactory.getLogger(Action.class);

    @PostPersist
    public void onPostPersist(Action action) {
        logger.info("Action of type {} with id {} persisted.", action.getClass().getSimpleName(), action.getId());
        ActionPersistenceEventRegistry registry = ActionPersistenceEventRegistry.getInstance(action.getClass());
        registry.firePostPersist(action);
    }

    @PostRemove
    public void onPostRemove(Action action) {
        logger.info("Action of type {} with id {} removed.", action.getClass().getSimpleName(), action.getId());
        ActionPersistenceEventRegistry registry = ActionPersistenceEventRegistry.getInstance(action.getClass());
        registry.firePostRemove(action);
    }
}

