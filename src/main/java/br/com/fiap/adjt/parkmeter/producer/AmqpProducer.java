package br.com.fiap.adjt.parkmeter.producer;

public interface AmqpProducer<T> {
    void producer(T t);
}
