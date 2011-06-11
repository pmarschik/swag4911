package swag49.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import swag49.model.BuildAction;
import swag49.model.listener.ActionPersistenceEventRegistry;

public class ActionListenerHelper implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ActionPersistenceEventRegistry registry = ActionPersistenceEventRegistry.getInstance(BuildAction.class, );
    }
}
