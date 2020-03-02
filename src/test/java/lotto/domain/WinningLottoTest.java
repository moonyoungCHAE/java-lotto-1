package lotto.domain;

import lotto.generator.ManualLottoGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {
    static Lotto lotto;

    @BeforeAll
    static void init() {
        List<String> lottoNumbers = Arrays.asList("1", "2", "3", "4", "5", "6");
        lotto = ManualLottoGenerator.createLotto(lottoNumbers);
    }

    @Test
    void validateBonusNumber_보너스볼이_나오지_않은_숫자일_때() {
        LottoNumber validBonusNumber = new LottoNumber("7");
        new WinningLotto(lotto, validBonusNumber);
    }

    @Test
    void validateBonusNumber_보너스볼이_이미_나온_숫자일_때() {
        LottoNumber invalidBonusNumber = new LottoNumber("6");
        assertThatThrownBy(() -> new WinningLotto(lotto, invalidBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호가 당첨번호와 중복됩니다.");
    }

    @ParameterizedTest
    @NullSource
    void validateNotNull_로또번호_NULL(Lotto nullValue) {
        assertThatThrownBy(() -> new WinningLotto(nullValue, new LottoNumber(1)))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("로또 번호가 입력되지 않았습니다.");
    }

    @ParameterizedTest
    @NullSource
    void validateNotNull_보너스번호_NULL(LottoNumber nullValue) {
        assertThatThrownBy(() -> new WinningLotto(lotto, nullValue))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("보너스 번호가 입력되지 않았습니다.");
    }

    @Test
    void computeMatchCount_두_로또_사이에_6개_로또_번호가_일치할_때() {
        int expected = 6;

        WinningLotto winningLotto = new WinningLotto(lotto, new LottoNumber(7));
        assertThat(winningLotto.computeMatchCount(lotto) == expected);
    }

    @Test
    void computeMatchCount_두_로또_사이에_일치하는_로또번호가_없을_때() {
        int expected = 0;
        List<String> lottoNumbers = Arrays.asList("11", "12", "13", "14", "15", "16");
        Lotto lotto = ManualLottoGenerator.createLotto(lottoNumbers);
        WinningLotto winningLotto = new WinningLotto(lotto, new LottoNumber("45"));

        assertThat(winningLotto.computeMatchCount(lotto) == expected);
    }
}
