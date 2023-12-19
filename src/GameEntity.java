enum EntityType {
    PACMAN, GHOST, WALL, DOT
}

// GameEntity class
public class GameEntity {
    private final EntityType type;

    public GameEntity(EntityType type) {
        this.type = type;
    }


    public EntityType getType() {
        return type;
    }
}