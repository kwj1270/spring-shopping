package shopping.customer.application;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shopping.customer.application.command.CustomerSignInCommand;
import shopping.customer.application.command.CustomerSignUpCommand;
import shopping.customer.domain.AccessToken;
import shopping.customer.domain.CustomerSignUpRequest;
import shopping.customer.domain.repository.CustomerRepository;
import shopping.utils.fake.FakeCustomerRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static shopping.utils.fixture.CustomerFixture.*;

@DisplayName("고객 가입 테스트")
public class CustomerSignInUseCaseTest {

    private CustomerRepository customerRepository;
    private CustomerSignInUseCase customerSignInUseCase;

    @BeforeEach
    void setUp() {
        customerRepository = new FakeCustomerRepository();
        customerSignInUseCase = new CustomerSignInService(customerRepository);
    }

    @DisplayName("일반 사용자는 회원 가입이 되어있다면 이메일과 비밀번호로 로그인을 할 수 있다.")
    @Test
    void signIn() {
        customerRepository.save(new CustomerSignUpRequest(EMAIL, NAME, PASSWORD, BIRTH, ADDRESS, PHONE));

        final CustomerSignInCommand customerSignInCommand = new CustomerSignInCommand(EMAIL, PASSWORD);
        final AccessToken accessToken = customerSignInUseCase.signIn(customerSignInCommand);

        assertThat(accessToken.accessToken()).isNotBlank();
    }

    @DisplayName("비유효한 이메일을 입력하면 로그인 할 수 없다")
    @Test
    void doNotSignInInvalidEmail() {
        assertThatThrownBy(() -> new CustomerSignUpCommand("test@", NAME, PASSWORD, BIRTH, ADDRESS, PHONE))
                .isExactlyInstanceOf(ConstraintViolationException.class);
    }

    @DisplayName("비유효한 패스워드를 입력하면 로그인 할 수 없다")
    @Test
    void doNotSignInInvalidPassword() {
        assertThatThrownBy(() -> new CustomerSignUpCommand(EMAIL, NAME, "1234", BIRTH, ADDRESS, PHONE))
                .isExactlyInstanceOf(ConstraintViolationException.class);
    }
}
