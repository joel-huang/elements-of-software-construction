class Track {
    int id;
    boolean isEmpty;
    boolean isDirectionFlipped;

    boolean entryAllowed(boolean trainDirection) {} // based on isEmpty and isDirectionFlipped, return 1 if allowed or 0 if not.
}

class Junction {
    int id;
    List<Track> adjacents; // node adjacency list
}

class Train {
    boolean onTrack;
    String type;
    Engine engine;

    class Engine {
        String type;
    }

    // methods
    void changeEngine(String newEngineType) {} // swap out the engine
    void changeDirection() {}                  // check if safe to move
    void move() {}                             // move to the next track


}

public static void main(String[] args) {
    // usage
    


}