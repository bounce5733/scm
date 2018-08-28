package com.jyh.scm.base;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.jyh.scm.dao.OptLogMapper;
import com.jyh.scm.entity.OptLog;
import com.jyh.scm.util.IDGenUtil;
import com.jyh.scm.util.TimeUtil;

/**
 * Spring MVC 配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private static final Logger log = LoggerFactory.getLogger(WebMvcConfig.class);

	@Autowired
	private OptLogMapper optLogMapper;

	// 解决跨域问题
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
				.allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "OPTIONS", "PATCH")
				.allowedHeaders("X-Requested-With", "accept", "content-type", SessionManager.SESSION_KEY)
				.exposedHeaders(SessionManager.SESSION_KEY, SessionManager.USER_NAME_KEY).allowCredentials(false)
				.maxAge(1800);
	}

	// 添加拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HandlerInterceptorAdapter() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				request.setCharacterEncoding(AppConst.ENCODING);
				response.setCharacterEncoding(AppConst.ENCODING);

				// 验证权限
				if (!AppConst.AUTH_SKIP_URI.contains(request.getServletPath())
						&& !request.getMethod().equalsIgnoreCase("OPTIONS")) {
					String token = request.getHeader("X-Auth-Token");
					if (token == null || !SessionManager.isValid(token)) {
						responseResult(response, HttpStatus.UNAUTHORIZED.value());
						return false;
					}

					SessionManager.setSessionid(token);

					// 记录日志
					String method = request.getMethod();
					String url = request.getServletPath();
					String action = method.toLowerCase() + "_" + url.substring(1);
					if (CacheManager.loadActionMap().get(action) != null) {
						OptLog optlog = new OptLog();
						optlog.setId(IDGenUtil.UUID());
						optlog.setUserName(SessionManager.getUsername());
						optlog.setOptType(CacheManager.loadActionMap().get(action));
						optlog.setCreatedBy(SessionManager.getAccount());
						optlog.setCreatedTime(TimeUtil.getTime());

						optLogMapper.insert(optlog);
					}
				}

				return true;
			}
		});
	}

	// 使用阿里 FastJson 作为JSON MessageConverter
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(SerializerFeature.WriteMapNullValue, // 保留空的字段
				SerializerFeature.WriteNullStringAsEmpty, // String null -> ""
				SerializerFeature.WriteNullNumberAsZero);// Number null -> 0
		converter.setFastJsonConfig(config);
		converter.setDefaultCharset(Charset.forName("UTF-8"));
		converters.add(converter);
	}

	/**
	 * 返回头
	 * 
	 * @param response
	 * @param status
	 */
	private void responseResult(HttpServletResponse response, int status) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "application/json;charset=UTF-8");
		response.setStatus(status);
	}

}
