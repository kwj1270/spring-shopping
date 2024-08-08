package shopping.utils.testcontainer;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.RabbitMQContainer;

public class RabbitMQTestContainerExtension implements Extension, BeforeAllCallback {

    private static final String DOCKER_IMAGE = "rabbitmq:3-management";
    private static final RabbitMQContainer RABBIT_MQ_CONTAINER = new RabbitMQContainer(DOCKER_IMAGE)
            .withUser("admin", "admin");

    @Override
    public void beforeAll(final ExtensionContext extensionContext) {
        if (RABBIT_MQ_CONTAINER.isRunning()) {
            return;
        }
        RABBIT_MQ_CONTAINER.start();
        System.setProperty("spring.rabbitmq.host", RABBIT_MQ_CONTAINER.getHost());
        System.setProperty("spring.rabbitmq.port", RABBIT_MQ_CONTAINER.getAmqpPort().toString());
        System.setProperty("spring.rabbitmq.username", RABBIT_MQ_CONTAINER.getAdminUsername());
        System.setProperty("spring.rabbitmq.password", RABBIT_MQ_CONTAINER.getAdminPassword());
    }
}
