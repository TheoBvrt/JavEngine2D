package ch.bouverat.engine.game_engine.core;

import ch.bouverat.engine.game_engine.component.Component;
import ch.bouverat.engine.game_engine.core.enums.ErrorType;
import java.util.ArrayList;
import java.util.List;

public class GameBehaviour {
    private List<Component> components = new ArrayList<>();
    protected double deltaTime;

    public GameBehaviour() {
        BehaviourManager.addBehaviour(this);
        start();
    }

    public void start() {
    }

    public void update() {
    }

    public void setDeltaTime(double deltaTime) {
        this.deltaTime = deltaTime;
    }

    public void addComponent(Component component) {
        components.add(component);
        for (int i = 0; i < components.size(); i++) {
            Class<?> currentComponent = components.get(i).getClass();
            for (int j = 0; j < components.size(); j++) {
                if (components.get(j).getClass() == currentComponent && j != i) {
                    Error.message(ErrorType.WARNING, component.getParent().getClass().getSimpleName() + ".java",
                            "two identical components on the same object.");
                    System.exit(-1);
                }
            }
        }
    }
}
