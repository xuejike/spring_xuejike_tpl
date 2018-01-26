package vip.xuejike.jktpl.layui.anno;

import com.bidanet.springmvc.demo.jkbuilder.annotation.core.JkInfoTag;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkInfoTag
public @interface LongDate {
}
