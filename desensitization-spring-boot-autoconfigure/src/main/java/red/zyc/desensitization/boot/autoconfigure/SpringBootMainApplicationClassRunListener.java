/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package red.zyc.desensitization.boot.autoconfigure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import red.zyc.desensitization.resolver.TypeResolver;
import red.zyc.desensitization.resolver.TypeResolvers;

/**
 * @author zyc
 */
public class SpringBootMainApplicationClassRunListener implements SpringApplicationRunListener {

    private final SpringApplication springApplication;

    private final String[] args;

    public SpringBootMainApplicationClassRunListener(SpringApplication springApplication, String[] args) {
        this.springApplication = springApplication;
        this.args = args;
    }

    @Override
    public void starting() {
        DesensitizationAutoConfiguration.SPRING_APPLICATION_HOLDER.set(springApplication);
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment configurableEnvironment) {

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext configurableApplicationContext) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext configurableApplicationContext) {

    }

    @Override
    public void finished(ConfigurableApplicationContext configurableApplicationContext, Throwable throwable) {
        configurableApplicationContext.getBeansOfType(TypeResolver.class).values().forEach(TypeResolvers::register);
    }

}
