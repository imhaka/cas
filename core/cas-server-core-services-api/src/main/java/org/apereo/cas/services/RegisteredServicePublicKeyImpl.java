package org.apereo.cas.services;

import org.apereo.cas.util.ResourceUtils;
import org.apereo.cas.util.crypto.PublicKeyFactoryBean;
import org.apereo.cas.util.spring.SpringExpressionLanguageValueResolver;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

import java.security.PublicKey;

/**
 * Represents a public key for a CAS registered service.
 *
 * @author Misagh Moayyed
 * @since 4.1
 */
@Slf4j
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"location", "algorithm"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class RegisteredServicePublicKeyImpl implements RegisteredServicePublicKey {

    private static final long serialVersionUID = -8497658523695695863L;

    private String location;

    private String algorithm = "RSA";

    @SneakyThrows
    @Override
    public PublicKey createInstance() {
        if (StringUtils.isNotBlank(this.location)) {
            LOGGER.trace("Attempting to read public key from [{}]", this.location);
            val factory = PublicKeyFactoryBean.class.getDeclaredConstructor().newInstance();
            val resolved = SpringExpressionLanguageValueResolver.getInstance().resolve(this.location);
            val resource = ResourceUtils.getResourceFrom(resolved);
            factory.setResource(resource);
            factory.setAlgorithm(this.algorithm);
            factory.setSingleton(false);
            return factory.getObject();
        }
        LOGGER.warn("NO public key location is defined");
        return null;
    }

}
