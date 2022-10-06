package com.example.util;

import org.assertj.core.api.Assertions;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */
public class TestUtil {
    /**
     * Verifies the equals/hashcode contract on the domain object.
     */
    public static <T> void equalsVerifier(Class<T> clazz) throws Exception {
        T domainObject1 = clazz.getConstructor().newInstance();
        Assertions.assertThat(domainObject1.toString()).isNotNull();
        Assertions.assertThat(domainObject1).isEqualTo(domainObject1);
        Assertions.assertThat(domainObject1).hasSameHashCodeAs(domainObject1);
        // Test with an instance of another class
        Object testOtherObject = new Object();
        Assertions.assertThat(domainObject1).isNotEqualTo(testOtherObject);
        Assertions.assertThat(domainObject1).isNotEqualTo(null);
        // Test with an instance of the same class
        T domainObject2 = clazz.getConstructor().newInstance();
        Assertions.assertThat(domainObject1).isNotEqualTo(domainObject2);
        // HashCodes are equals because the objects are not persisted yet
        Assertions.assertThat(domainObject1).hasSameHashCodeAs(domainObject2);
    }
}
