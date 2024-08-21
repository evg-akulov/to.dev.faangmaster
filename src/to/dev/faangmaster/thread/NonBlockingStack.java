package to.dev.faangmaster.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * NonBlockingStack
 *
 * @param <E>
 * @link <a href="https://dev.to/faangmaster/riealizovat-potokobiezopasnyi-nieblokiruiushchii-stiek-na-java-2bd1">Реализовать потокобезопасный неблокирующий стек на Java</a>
 */
public class NonBlockingStack<E> {
    private final AtomicReference<Node<E>> top = new AtomicReference<>();

    public void push(E item) {
        Node<E> newHead = new Node<>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            //Новая вершина должна ссылаться на старую вершину стека
            newHead.next = oldHead;
            //Если вершина не была изменена кем-то еще (top == oldHead),
            //то подменяем и выходим из цикла (top = newHead)
        } while (!top.compareAndSet(oldHead, newHead));
    }

    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = top.get();
            //Если стек пустой, то возвращаем null
            if (oldHead == null) {
                return null;
            }
            //Если стек не пустой, то новой вершиной стека будет
            //следующий элемент стека
            newHead = oldHead.next;
            //Если вершина не была изменена кем-то еще (top == oldHead),
            //то подменяем и выходим из цикла (top = newHead)
        } while (!top.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }


    private static class Node<E> {
        public final E item;
        public Node<E> next;

        public Node(E item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NonBlockingStack{" +
                "top=" + top +
                '}';
    }

    public static void main(String[] args) {
        var stack = new NonBlockingStack<String>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        System.out.println(stack.toString());
    }
}
