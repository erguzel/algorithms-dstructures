package lib.model.abstraction;

public abstract class Result implements IResultAsset {
    private  int id;
    private double value;
    private boolean visited;
    private boolean found;
    private IResultAsset previous;

    @Override
    public void setPrevious(IResultAsset previous) {
        this.previous = previous;
    }

    @Override
    public IResultAsset getPrevious() {
        return previous;
    }

    @Override
    public void setFound(boolean found) {
        this.found = found;
    }
    @Override
    public boolean isFound() {
        return found;
    }

    @Override
    public boolean isVisited() {
        return visited;
    }

    @Override
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public double getValue() {
        return value;
    }
}
