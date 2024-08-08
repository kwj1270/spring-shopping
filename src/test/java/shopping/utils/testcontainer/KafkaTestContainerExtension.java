package shopping.utils.testcontainer;


import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

public class KafkaTestContainerExtension implements Extension, BeforeAllCallback {

    private static final String DOCKER_IMAGE = "confluentinc/cp-kafka:6.2.1";
    private static final KafkaContainer KAFKA_CONTAINER = new KafkaContainer(DockerImageName.parse(DOCKER_IMAGE));

    @Override
    public void beforeAll(final ExtensionContext extensionContext) throws Exception {
        if (KAFKA_CONTAINER.isRunning()) {
            return;
        }
        KAFKA_CONTAINER.start();
        System.setProperty("spring.kafka.bootstrap-servers", KAFKA_CONTAINER.getBootstrapServers());
        System.setProperty("spring.kafka.consumer.auto-offset-reset", "earliest");
        System.setProperty("spring.kafka.consumer.group-id", "test-group");
    }
}
