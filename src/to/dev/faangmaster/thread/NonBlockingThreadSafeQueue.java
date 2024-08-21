package to.dev.faangmaster.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * NonBlockingThreadSafeQueue
 *
 * <p>This Michael & Scott algorithm nonblocking linked-queue algorithm (Michael and Scott, 1996)
 * @param <E>
 *
 * @link <a href="https://dev.to/faangmaster/riealizovat-potokobiezopasnuiu-nieblokiruiushchuiu-ochieried-na-java-38b8">Реализовать потокобезопасную неблокирующую очередь на Java</a>
 */
public class NonBlockingThreadSafeQueue<E> {
    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
        }
    }

    private final Node<E> emptyNode = new Node<>(null, null);
    private final AtomicReference<Node<E>> tail = new AtomicReference<>(emptyNode);

    public boolean put(E item) {
        Node<E> newNode = new Node<>(item, null);
        while(true) {
            Node<E> currentTail = tail.get();
            Node<E> tailNext = currentTail.next.get();
            if (currentTail == tail.get()) {
                if (tailNext != null) {
                    //Очередь в промежуточном состоянии
                    //Хвост еще не обновился,
                    //а next на хвосте уже обновилась
                    //Пробуем помочь другому потоку завершить операцию,
                    //путем обновления ссылки на хвост
                    //Если другой поток был быстрее,
                    //то это обновление не сработает
                    //и мы перейдем на следующую итерацию цикла
                    //для новой попытки
                    //Если сработает, то мы поможем первому потоку и
                    //сами также перейдем к следующей попытки вставить
                    //наш элемент в очередь
                    tail.compareAndSet(currentTail, tailNext);
                } else {
                    if (currentTail.next.compareAndSet(null, newNode)) {
                        tail.compareAndSet(currentTail, newNode);
                        return true;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "NonBlockingThreadSafeQueue{" +
                "tail=" + tail +
                '}';
    }

    public static void main(String[] args) {
        var queue = new NonBlockingThreadSafeQueue<String>();
        queue.put("1");
        queue.put("2");
        System.out.println(queue.toString());
    }
}
