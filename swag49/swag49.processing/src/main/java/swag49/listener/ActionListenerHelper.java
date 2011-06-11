package swag49.listener;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;
import swag49.model.*;
import swag49.model.listener.ActionPersistenceEventListener;
import swag49.model.listener.ActionPersistenceEventRegistry;

import javax.annotation.PostConstruct;

@Component
@Scope("singleton")
public class ActionListenerHelper implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    @Autowired
    @Qualifier("troopActionListener")
    private ActionPersistenceEventListener troopActionListener;

    @Autowired
    @Qualifier("buildActionListener")
    private ActionPersistenceEventListener buildActionListener;

    @Autowired
    @Qualifier("troopUpgradeActionListener")
    private ActionPersistenceEventListener troopUpgradeActionListener;

    @Autowired
    @Qualifier("troopBuildActionListener")
    private ActionPersistenceEventListener troopBuildActionListener;

    private AbstractApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        applicationContext.addApplicationListener(this);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        registerActionListener(BuildAction.class, buildActionListener);
        registerActionListener(TroopAction.class, troopActionListener);
        registerActionListener(TroopUpgradeAction.class, troopUpgradeActionListener);
        registerActionListener(TroopBuildAction.class, troopBuildActionListener);
    }

    private void registerActionListener(Class<? extends Action> actionClass, ActionPersistenceEventListener actionListener) {
        ActionPersistenceEventRegistry registry = ActionPersistenceEventRegistry.getInstance(actionClass);
        registry.registerListener(actionListener);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (AbstractApplicationContext) applicationContext;
    }
}
