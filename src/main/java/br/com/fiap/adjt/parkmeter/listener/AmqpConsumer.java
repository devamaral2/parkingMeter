package br.com.fiap.adjt.parkmeter.listener;

public interface AmqpConsumer<T> {
    void consumer(T t);
}
