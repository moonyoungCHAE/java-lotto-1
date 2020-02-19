package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @Test
    void validateNumberScope_숫자가_범위보다_작을_때() {
        int invalidNumberUnderScope = -11;
        assertThatThrownBy(() -> LottoNumber.validateNumberScope(invalidNumberUnderScope))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호의 범위가 잘못되었습니다.");
    }

    @Test
    void validateNumberScope_숫자가_범위보다_클_때 () {
        int invalidNumberOverScope = 50;
        assertThatThrownBy(() -> LottoNumber.validateNumberScope(invalidNumberOverScope))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호의 범위가 잘못되었습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"천원", "3.2"})
    void validateNumber_정수로_입력하지_않았을_때(String invalidInputMoney) {
        assertThatThrownBy(() -> LottoNumber.validateNumber(invalidInputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정수로 입력하셔야 합니다.");
    }
}
