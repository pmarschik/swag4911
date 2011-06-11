package swag49.model.listener;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import swag49.model.Action;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: matthias
 * Date: 11.06.11
 * Time: 13:12
 * To change this template use File | Settings | File Templates.
 */
public class ActionPersistenceEventRegistry {

    public static Map<Class<? extends Action>, ActionPersistenceEventRegistry> instances = Maps.newHashMap();
    private List<ActionPersistenceEventListener> listeners = Lists.newArrayList();

    public synchronized static ActionPersistenceEventRegistry getInstance(Class<? extends Action> actionClass) {
        if (!instances.containsKey(actionClass)) {
            instances.put(actionClass, new ActionPersistenceEventRegistry());
        }

        return instances.get(actionClass);
    }

    public void registerListener(ActionPersistenceEventListener listener) {
        listeners.add(listener);
    }

    public void unregisterListener(ActionPersistenceEventListener listener) {
        listeners.remove(listener);
    }

    protected void firePostPersist(Action action) {
        for (ActionPersistenceEventListener listener : listeners)
            listener.postPersist(action);
    }

    protected void firePostRemove(Action action) {
        for (ActionPersistenceEventListener listener : listeners)
            listener.postRemove(action);
    }
}
