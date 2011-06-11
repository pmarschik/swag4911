package swag49.model.listener;


import swag49.model.Action;

public interface ActionPersistenceEventListener<T extends Action> {
    void postPersist(T action);
    void postRemove(T action);
}
