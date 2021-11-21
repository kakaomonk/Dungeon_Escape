package elements;

import utils.Point2D;

public class AlligatorDen extends Generator{

    /**
     * A constructor for the AlligatorDen class, inherited from its parent the Generator class.
     * @param sprite the element's representation
     * @param pos the element's initial position
     * @param direction the direction in the newly generated objects move
     * @param max_tick the number of frame ticks it takes before the next movement
     * @param bound the movement boundary the objects generated by this element
     */
    public AlligatorDen(String sprite, Point2D pos, Point2D direction, int max_tick, int bound) {
        super(sprite, pos, direction, max_tick, bound);
    }

    /**
     * Generates new objects.
     * @param direction the direction in which the newly generated object move
     * @param bound the movement boundary of the newly generated object
     * @return a newly generated object
     */
    @Override
    MovableElement generateElement(Point2D direction, int bound) {
        return new Alligator("alligator",
                super.getPos(), super.bound, 60, this.direction);
    }
}


