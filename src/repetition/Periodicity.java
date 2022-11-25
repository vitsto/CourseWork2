package repetition;

public abstract class Periodicity implements Repeatable {
    private final String title;


    protected Periodicity(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
