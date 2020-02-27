package lotto.domain;

import java.util.Objects;

public class PaidPrice {
    private static final String EMPTY_INPUT_MSG = "가격을 입력하지 않았습니다.";
    private static final String PRICE_PER_LOTTO_ERROR_MSG = "가격은 1000원 단위로 입력해야 합니다.";
    private static final String UNDER_LOTTO_PRICE_MSG = "1000원 이상으로 입력해야 합니다.";
    private static final String NOT_NUMBER_MSG = "정수로 입력하셔야 합니다.";

    private int payment;

    public PaidPrice(String inputMoney) {
        validateNotNull(inputMoney);
        validateNumber(inputMoney);

        int money = Integer.parseInt(inputMoney);
        validateUnderLottoPrice(money);
        validatePricePerLotto(money);
        this.payment = money;
    }

    private void validateNotNull(String inputMoney) {
        Objects.requireNonNull(inputMoney, EMPTY_INPUT_MSG);
    }

    private void validateNumber(String inputMoney) {
        try {
            Integer.parseInt(inputMoney);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_MSG);
        }
    }

    private void validatePricePerLotto(int inputMoney) {
        if (inputMoney % Lottos.PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException(PRICE_PER_LOTTO_ERROR_MSG);
        }
    }

    private void validateUnderLottoPrice(int inputMoney) {
        if (inputMoney < Lottos.PRICE_PER_LOTTO) {
            throw new IllegalArgumentException(UNDER_LOTTO_PRICE_MSG);
        }
    }

    public int getTotalLottoSize() {
        return payment / Lottos.PRICE_PER_LOTTO;
    }
}
