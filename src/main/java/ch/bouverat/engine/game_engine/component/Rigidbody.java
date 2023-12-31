package ch.bouverat.engine.game_engine.component;

import ch.bouverat.engine.game_engine.core.*;
import ch.bouverat.engine.game_engine.core.Error;
import ch.bouverat.engine.game_engine.core.enums.Axis;
import ch.bouverat.engine.game_engine.core.enums.ErrorType;
import ch.bouverat.engine.game_engine.settings.PhysicsSetting;
import ch.bouverat.engine.game_engine.utils.Vector2;

public class Rigidbody extends Component{

    private double forceToAdd = 0.8;
    private double maxDistance;
    private Vector2 init;
    private final Transform transform;

    public Rigidbody(GameBehaviour parent, Transform transform) {
        super(parent);

        init = parent.getComponent(Transform.class).position;
        this.transform = transform;
        if (parent.getSizeX() == 0 || parent.getSizeY() == 0) {
            Error.message(ErrorType.WARNING, parent.getClass().getSimpleName() + ".java", "size values are not initialized");
        }
        ObjectManager.addRigidbody(this);
    }

    public void updateRigidBody () {
        if (init.y > maxDistance && forceToAdd != 0) {
            transform.slide(Axis.Y, forceToAdd);
        } else {
            forceToAdd = 0;
        }
        transform.slide(Axis.Y, PhysicsSetting.gravityForce * Time.DeltaTime);
    }

    public void addForce(double value, double maxDistance) {
        forceToAdd += value;
        init = parent.getComponent(Transform.class).position;
        this.maxDistance = init.y - maxDistance;
    }
}
