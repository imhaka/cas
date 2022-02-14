package org.apereo.cas.support.saml.web.idp.profile.builders;

import org.apereo.cas.support.saml.services.SamlRegisteredService;
import org.apereo.cas.support.saml.services.idp.metadata.SamlRegisteredServiceServiceProviderMetadataFacade;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.opensaml.messaging.context.MessageContext;
import org.opensaml.saml.common.xml.SAMLConstants;
import org.opensaml.saml.saml2.core.RequestAbstractType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * This is {@link SamlProfileBuilderContext}.
 *
 * @author Misagh Moayyed
 * @since 6.5.0
 */
@Getter
@SuperBuilder
@SuppressWarnings("ObjectToString")
@ToString(of = {"authenticatedAssertion", "registeredService", "binding"})
public class SamlProfileBuilderContext {
    private final RequestAbstractType samlRequest;

    private final HttpServletRequest httpRequest;

    private final HttpServletResponse httpResponse;

    private final AuthenticatedAssertionContext authenticatedAssertion;

    @NotNull
    private final SamlRegisteredService registeredService;

    @NotNull
    private final SamlRegisteredServiceServiceProviderMetadataFacade adaptor;

    @NotNull
    @Builder.Default
    private final String binding = SAMLConstants.SAML2_POST_BINDING_URI;

    @Builder.Default
    private final MessageContext messageContext = new MessageContext();

    /**
     * Transfer to a new context.
     *
     * @param request   the query
     * @param toBinding the binding
     * @return the saml profile builder context
     */
    public SamlProfileBuilderContext transferTo(final RequestAbstractType request, final String toBinding) {
        return SamlProfileBuilderContext.builder()
            .samlRequest(request)
            .httpRequest(httpRequest)
            .httpResponse(httpResponse)
            .authenticatedAssertion(authenticatedAssertion)
            .registeredService(registeredService)
            .adaptor(adaptor)
            .binding(toBinding)
            .build();
    }
}
