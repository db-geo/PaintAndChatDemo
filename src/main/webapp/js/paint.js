/** A class for a 2D point */
class Point {
    /** x-axis coordinate*/
    #x;
    /** y-axis coordinate*/
    #y;

    /** Constructor
     *
     * @param x x-axis coordinate
     * @param y y axis-coordinate
     */
    constructor(x, y) {
        this.#x = x;
        this.#y = y;
    }

    /**
     * Getter for x-axis coordinate
     * @returns {*}
     */
    get x() {
        return this.#x;
    }

    /**
     * Getter for y-axis coordinates
     * @returns {*}
     */
    get y() {
        return this.#y;
    }
}

/** A class for drawing in a canvas : draw a line on mouse dragged */
class Paint {
    /** the reference of the canvas */
    #canvas;
    /** last dragging location */
    #lastLocation = null;

    /** Constructor
     *
     * @param canvas the canvas
     */
    constructor(canvas) {
        /** save the canvas reference */
        this.#canvas = canvas;
        /** Use that because this in event listeners refers to the listener itself */
        let that = this;
        /** start dragging */
        canvas.addEventListener("mousedown", function (e) {
            that.#lastLocation = new Point(
                e.offsetX == undefined ? e.layerX : e.offsetX,
                e.offsetY == undefined ? e.layerY : e.offsetY);
        });
        /** draw the event and stop dragging */
        canvas.addEventListener("mouseup", function (e) {
            that.#draw(e);
            that.stopDrag();
        });
        /** draw the event and stop dragging */
        canvas.addEventListener("mouseout", function (e) {
            that.#draw(e);
            that.stopDrag();
        });
        /** draw the event  */
        canvas.addEventListener("mousemove", function (e) {
            that.#draw(e);
        });
    }

    /**
     * Paint a line between the last location and the location denoted by e
     * @param e the event containing the coordinates
     */
    #draw(e) {
        if (!e) e = window.event;
        if (this.#lastLocation == null) return;
        var x = e.offsetX == undefined ? e.layerX : e.offsetX;
        var y = e.offsetY == undefined ? e.layerY : e.offsetY;
        var ctx = this.#canvas.getContext("2d");
        ctx.beginPath();
        ctx.moveTo(this.#lastLocation.x, this.#lastLocation.y);
        ctx.lineTo(x, y);
        ctx.lineWidth = 2;
        ctx.strokeStyle = "#FF0000";
        ctx.stroke();
        this.#lastLocation = new Point(x, y);

    }

    /** Indicates the end of the dragging gesture by setting the last location to null */
    stopDrag() {
        this.#lastLocation = null;
    }
}