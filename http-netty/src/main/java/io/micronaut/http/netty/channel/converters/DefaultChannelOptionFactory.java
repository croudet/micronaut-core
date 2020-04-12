/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.http.netty.channel.converters;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.core.annotation.Internal;
import io.netty.channel.ChannelOption;

/**
 * Creates channel options.
 * @author croudet
 */
@Internal
@Requires(missingBeans = { EpollChannelOptionFactory.class, KQueueChannelOptionFactory.class })
@Singleton
public class DefaultChannelOptionFactory implements ChannelOptionFactory {

    private final ChannelOptions channelOptions;

    /**
     * Default constructor.
     *
     * @param channelOptions The ChannelOptions class.
     */
    @Inject
    public DefaultChannelOptionFactory(ChannelOptions channelOptions) {
        this.channelOptions = channelOptions;
    }

    @Override
    public Object convertValue(ChannelOption<?> option, Object value, Environment env) {
        final Optional<Class> cls = channelOptions.channelOptionType(option);
        if (cls.isPresent()) {
            return env.convert(value, cls.get()).orElse(value);
        }
        return value;
    }

    /**
     * Creates a channel options.
     * @param name The name of the option.
     * @param classes The classes to check.
     * @return A channel option.
     */
    static ChannelOption<?> channelOption(String name, Class<?>... classes) {
        for (Class<?> cls: classes) {
            final String composedName = cls.getName() + '#' + name;
            if (ChannelOption.exists(composedName)) {
                return ChannelOption.valueOf(composedName);
            }
        }
        return ChannelOption.valueOf(name);
    }
}
