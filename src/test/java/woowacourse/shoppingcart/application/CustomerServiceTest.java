package woowacourse.shoppingcart.application;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import woowacourse.shoppingcart.dao.CustomerDao;
import woowacourse.shoppingcart.domain.Customer;
import woowacourse.shoppingcart.dto.CustomerSaveRequest;
import woowacourse.shoppingcart.exception.DuplicatedEmailException;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerDao customerDao;

    @Test
    @DisplayName("회원을 등록한다.")
    void save() {
        // given
        CustomerSaveRequest customerSaveRequest = new CustomerSaveRequest("라라", "lala.seeun@gmail.com", "tpdmstpdms11");
        final Customer customer = new Customer(1L, customerSaveRequest.getName(), customerSaveRequest.getEmail(),
                customerSaveRequest.getPassword());
        when(customerDao.save(any(Customer.class)))
                .thenReturn(customer);

        // when, then
        assertThatCode(() -> customerService.save(customerSaveRequest))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("중복된 이메일로 회원 등록 시 예외를 발생한다.")
    void save_duplicatedEmail_throwsException() {
        // given
        CustomerSaveRequest customerSaveRequest = new CustomerSaveRequest("라라", "lala.seeun@gmail.com", "tpdmstpdms11");
        when(customerDao.existsByEmail(any(Customer.class)))
                .thenReturn(true);

        // when, then
        assertThatThrownBy(() -> customerService.save(customerSaveRequest))
                .isInstanceOf(DuplicatedEmailException.class);
    }
}