package eu.darkbot.api.objects;

/**
 * Represents location point in-game.
 */
public interface Locatable {
    /**
     * Creates new instance of {@link Locatable} with given parameters.
     * @param x coordinate
     * @param y coordinate
     * @return {@link Locatable} with given coordinates
     */
    static Locatable of(double x, double y) {
        return new Locatable() {
            public double getX() { return x; }
            public double getY() { return y; }
        };
    }

    /**
     * @return y coordinate of the {@link Locatable}
     */
    double getX();

    /**
     * @return y coordinate of the {@link Locatable}
     */
    double getY();
}
