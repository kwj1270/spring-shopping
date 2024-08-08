package shopping.common.event;

public class EventQueueFixture {
    private EventQueueFixture() {}

    public static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    public static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    public static class CustomersFixture {
        public static final String CUSTOMERS_LEAVED_OUTBOX_QUEUE = "customers.leaved.outbox";
        public static final String CUSTOMERS_LEAVED_EXTERNAL_QUEUE = "customers.leaved.external";
        public static final String CUSTOMERS_EXCHANGE_FANOUT = "customers.exchange.fanout";
        public static final String CUSTOMERS_EXCHANGE_DLX = "customers.exchange.DLX";
        public static final String CUSTOMERS_QUEUE_DLT = "customers.queue.DLT";
    }
}
