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

import io.netty.channel.ChannelOption;

/**
 * Returns the channel options argument type.
 *
 * @author croudet
 */
public interface ChannelOptions {

    /**
     * Returns the channel options argument type.
     * @param option A channel option.
     * @return A class.
     */
     @SuppressWarnings("rawtypes")
    Optional<Class> channelOptionType(ChannelOption<?> option);
}
