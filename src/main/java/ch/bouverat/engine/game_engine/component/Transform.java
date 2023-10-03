package ch.bouverat.engine.game_engine.component;

import ch.bouverat.engine.game_engine.core.BehaviourManager;
import ch.bouverat.engine.game_engine.core.GameBehaviour;
import ch.bouverat.engine.game_engine.core.enums.Axis;
import ch.bouverat.engine.game_engine.utils.Vector2;

public class Transform extends Component {

    public Vector2 position;

    public Transform(GameBehaviour parent, Vector2 position) {
        super(parent);
        this.position = position;
    }

    private boolean isCollidingWith(Collider other, Vector2 proposedPosition) {
        return proposedPosition.x < other.colliderEnd.x &&
                proposedPosition.x + parent.getSizeX() > other.colliderOrigin.x &&
                proposedPosition.y < other.colliderEnd.y &&
                proposedPosition.y + parent.getSizeY() > other.colliderOrigin.y;
    }

    public void slide(Axis axis, double value) {
        Vector2 proposedPosition = new Vector2(position.x, position.y);

        if(axis == Axis.X) {
            proposedPosition.x += value;

            boolean collisionDetectedX = false;
            for (Collider collider : BehaviourManager.getColliderList()) {
                if(isCollidingWith(collider, proposedPosition)) {
                    collisionDetectedX = true;
                    break;
                }
            }

            if(!collisionDetectedX) {
                position.x = proposedPosition.x;
            }

        } else {
            proposedPosition.y += value;

            boolean collisionDetectedY = false;
            for (Collider collider : BehaviourManager.getColliderList()) {
                if(isCollidingWith(collider, proposedPosition)) {
                    collisionDetectedY = true;
                    break;
                }
            }

            if(!collisionDetectedY) {
                position.y = proposedPosition.y;
            }
        }
    }
}
