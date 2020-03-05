package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaidPriceTest {
    @Test
    void 로또_가격_정상적으로_입력했을_때_Success() {
        String validMoney = "1000";
        new PaidPrice(validMoney);
    }

    @ParameterizedTest
    @NullSource
    void validateNotNull_NULL일_때(String nullValue) {
        assertThatThrownBy(() -> new PaidPrice(nullValue))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("가격을 입력하지 않았습니다.");
    }

    @Test
    void validatePricePerLotto_로또_가격_단위로_입력하지_않았을_때() {
        String invalidMoney = "12100";
        assertThatThrownBy(() -> new PaidPrice(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가격은 1000원 단위로 입력해야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"900", "-1", "0"})
    void validateUnderLottoPrice_로또_가격_미만으로_입력했을_때(String invalidMoney) {
        assertThatThrownBy(() -> new PaidPrice(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1000원 이상으로 입력해야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"천원", "3000.2", ""})
    void validateNumber_정수로_입력하지_않았을_때(String invalidMoney) {
        assertThatThrownBy(() -> new PaidPrice(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정수로 입력하셔야 합니다.");
    }

    @Test
    void getTotalLottoCountTest() {
        int expected = 5;
        PaidPrice paidPrice = new PaidPrice("5000");
        assertThat(paidPrice.getTotalLottoSize()).isEqualTo(expected);
    }
}
