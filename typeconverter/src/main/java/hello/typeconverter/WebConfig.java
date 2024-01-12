package hello.typeconverter;

import hello.typeconverter.converter.IntegerToStringConverter;
import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIntegerConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        //우선 순위 때문에 주석처리함
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());
       /* registry.addConverter(new StringToIntegerConverter());
        registry.addConverter(new IntegerToStringConverter());
        둘 다 숫자를 문자로 바꾸는데 우리가 만든 포맷터도 숫자를 문자로 바꿈.
        똑같은건데 컨버터의 우선순위가 높기에 주석처리 함.*/

        //새로 추가
        registry.addFormatter(new MyNumberFormatter());
    }
}
