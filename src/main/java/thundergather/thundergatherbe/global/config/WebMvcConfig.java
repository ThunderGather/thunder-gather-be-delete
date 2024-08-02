package thundergather.thundergatherbe.global.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import thundergather.thundergatherbe.auth.config.LoginUserArgumentResolver;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

      private final LoginUserArgumentResolver loginUserArgumentResolver;

      @Override
      public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
            // loginUserArgumentResolver 를 Argument Resolver 목록에 추가
            resolvers.add(loginUserArgumentResolver);
      }
}
