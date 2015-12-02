package Poker;

import java.util.Objects;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class RollingSpliterator<E> implements Spliterator<Stream<E>> {

    private final int grouping ;
    private final Spliterator<E> spliterator ;
    private Object [] buffer ; // we cant create arrays of E
    private AtomicInteger bufferWriteIndex = new AtomicInteger(0) ;
    private AtomicInteger bufferReadIndex = new AtomicInteger(0) ;

    public RollingSpliterator(Spliterator<E> spliterator, int grouping) {
        this.spliterator = spliterator ;
        this.grouping = grouping ;
        this.buffer = new Object[grouping + 1] ;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Stream<E>> action) {
        Objects.requireNonNull(action) ;
        boolean finished = false ;

        if (bufferWriteIndex.get() == bufferReadIndex.get()) {
            for (int i = 0 ; i < grouping ; i++) {
                if (!advanceSpliterator()) {
                    finished = true ;
                }
            }
        }
        if (!advanceSpliterator()) {
            finished = true ;
        }

        Stream<E> subStream = buildSubstream() ;
        action.accept(subStream) ;
        return !finished ;
    }

    private boolean advanceSpliterator() {
        return spliterator.tryAdvance(
                element -> {
                    buffer[bufferWriteIndex.get() % buffer.length] = element ;
                    bufferWriteIndex.incrementAndGet() ;
                });
    }

    @Override
    public Spliterator<Stream<E>> trySplit() {
        return new RollingSpliterator<E>(spliterator.trySplit(), grouping) ;
    }

    @SuppressWarnings("unchecked")
    private Stream<E> buildSubstream() {

        Stream.Builder<E> subBuilder = Stream.builder() ;
        for (int i = 0 ; i < grouping ; i++) {
            subBuilder.add((E)buffer[(i + bufferReadIndex.get()) % buffer.length]) ;
        }
        bufferReadIndex.incrementAndGet() ;
        Stream<E> subStream = subBuilder.build() ;
        return subStream ;
    }

    @Override
    public long estimateSize() {
        return spliterator.estimateSize() - grouping ;
    }

    @Override
    public int characteristics() {
        return spliterator.characteristics() ;
    }
}
