package vip.xuejike.ktpl.mvc;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import vip.xuejike.ktpl.JkKtView;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author xuejike
 */
public class JkKtViewMessageConverters extends AbstractHttpMessageConverter<JkKtView> {

    public JkKtViewMessageConverters() {
//        text/html,application/xhtml+xml
        super(new MediaType("text", "html", Charset.forName("UTF-8")));

    }

    @Override
    protected boolean supports(Class<?> clazz) {
        boolean assignableFrom = JkKtView.class.isAssignableFrom(clazz);
        return assignableFrom;
    }

    @Override
    protected JkKtView readInternal(Class<? extends JkKtView> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(JkKtView jkKtView, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        byte[] bytes = jkKtView.toHtml().getBytes("UTF-8");
        outputMessage.getBody().write(bytes,0,bytes.length);
    }
}
