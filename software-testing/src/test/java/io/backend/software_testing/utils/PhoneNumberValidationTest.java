package io.backend.software_testing.utils;

import io.backend.software_testing.utils.PhoneNumberValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PhoneNumberValidatorTest {
    private PhoneNumberValidator underTest;

    PhoneNumberValidatorTest() {
    }

    @BeforeEach
    void setUp() {
        this.underTest = new PhoneNumberValidator();
    }

    @ParameterizedTest
    @CsvSource({"+447000000000,true", "+447000000000000,false", "-447000000000,false"})
    void itShouldValidatePhoneNumber(String phoneNumber, boolean excepted) {
        boolean isValid = this.underTest.test(phoneNumber);
        Assertions.assertThat(isValid).isEqualTo(excepted);
    }
}
