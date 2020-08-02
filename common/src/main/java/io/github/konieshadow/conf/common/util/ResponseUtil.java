package io.github.konieshadow.conf.common.util;

import io.github.konieshadow.conf.common.domain.ErrorResultBean;
import io.github.konieshadow.conf.common.domain.OptResultBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResponseUtil {

    public static void jsonResponse(HttpServletResponse response, String json, int status) throws IOException {
        String content = JsonUtil.toString(json);
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        response.setStatus(status);
        response.setContentLength(bytes.length);
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        response.getOutputStream().write(bytes);
        response.flushBuffer();
    }

    public static <T> void errorResponse(HttpServletResponse response, ErrorResultBean<T> errorResult, int status) throws IOException {
        jsonResponse(response, JsonUtil.toString(errorResult), status);
    }

    public static <T> void optResultResponse(HttpServletResponse response, OptResultBean<T> optResult) throws IOException {
        jsonResponse(response, JsonUtil.toString(optResult), HttpStatus.OK.value());
    }

}
