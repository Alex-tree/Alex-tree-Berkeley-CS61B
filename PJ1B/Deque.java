package PJ1B;

public interface Deque<item> {
    public void addLast(item x);

    public int getsize();

    public void addFirst(item x);

    public void removeFirst();

    public void removeLast();

    public item get(int x);

    public boolean isEmpty(int x);

    public  void printDeque();
}
