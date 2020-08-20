package src.PJ1A;

public interface ALD<item> {
    public void addLast(item x);

    public int getsize();

    public void addFirst(item x);

    public item removeFirst();

    public item removeLast();

    public item get(int x);

    public  void printDeque();
}
