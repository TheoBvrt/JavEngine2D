package ch.bouverat.engine.game_engine.component;

import ch.bouverat.engine.game_engine.core.ObjectManager;
import ch.bouverat.engine.game_engine.core.GameBehaviour;
import ch.bouverat.engine.game_engine.core.enums.Axis;
import ch.bouverat.engine.game_engine.utils.Vector2;

public class Transform extends Component {

    public Vector2 position;

    public Transform(GameBehaviour parent, Vector2 position) {
        super(parent);
        this.position = position;
    }

    //public methods
    public void slide(Axis axis, double value) {
        Vector2 proposedPosition = new Vector2(position.x, position.y);
        if(axis == Axis.X) {
            proposedPosition.x += value;
            boolean collisionDetectedX = false;
            for (Collider collider : ObjectManager.getColliderList()) {
                if (collider.getParent() != parent) {
                    if(isCollidingWith(collider, proposedPosition) && !collider.isTrigger) {
                        collisionDetectedX = true;
                        break;
                    }
                }
            }
            if(!collisionDetectedX) {
                position.x = proposedPosition.x;
            }
        } else {
            proposedPosition.y += value;

            boolean collisionDetectedY = false;
            for (Collider collider : ObjectManager.getColliderList()) {
                if (collider.getParent() != parent) {
                    if(isCollidingWith(collider, proposedPosition) && !collider.isTrigger) {
                        collisionDetectedY = true;
                        break;
                    }
                }
            }
            if(!collisionDetectedY) {
                position.y = proposedPosition.y;
            }
        }
        if (parent.hasComponent(Collider.class)) {
            Collider collider = parent.getComponent(Collider.class);

            collider.origin.x = position.x;
            collider.origin.y = position.y;

            collider.end.x = position.x + collider.getColliderSizeX();
            collider.end.y = position.y + collider.getColliderSizeY();
        }
    }

    //private methods
    private boolean isCollidingWith(Collider other, Vector2 proposedPosition) {
        return proposedPosition.x < other.end.x &&
                proposedPosition.x + parent.getSizeX() > other.origin.x &&
                proposedPosition.y < other.end.y &&
                proposedPosition.y + parent.getSizeY() > other.origin.y;
    }
}
