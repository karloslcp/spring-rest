package com.oci.ws.rest.springrest.resource;

import java.util.Locale;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
@AllArgsConstructor
public class HelloResource
{
    private MessageSource messages;

    @GetMapping("v1")
    public String fromHeader(@RequestHeader(name = "Accept-Language", required = false) Locale locale)
    {
        return messages.getMessage("hello.whatyadoing", null, locale);
    }

    @GetMapping("v2")
    public String fromContext()
    {
        return messages.getMessage("hello.whatyadoing", null, LocaleContextHolder.getLocale());
    }
}
