/*
 *
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * More infos available: https://engine.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 *
 */

package be.yildizgames.engine.server;

import be.yildizgames.common.exception.implementation.ImplementationException;
import be.yildizgames.common.model.Version;
import be.yildizgames.engine.server.configuration.ServerConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * @author Grégory Van den Borre
 */
public class GameEngineFactoryTest {

    @Nested
    public class FromConfig {

        @Disabled
        @Test
        public void happyFlow() {
            GameEngine engine = GameEngineFactory.fromConfig(new DummyServerConfig());
            Assertions.assertNotNull(engine);
        }

        @Disabled
        @Test
        public void nullParam() {
            Assertions.assertThrows(ImplementationException.class, () -> GameEngineFactory.fromConfig(null));
        }

    }

    private class DummyServerConfig implements ServerConfiguration {

        @Override
        public int getApplicationPort() {
            return 0;
        }

        @Override
        public Version getVersion() {
            return Version.alpha(1,1,1,1);
        }

        @Override
        public String getAuthenticationMethod() {
            return "none";
        }

        @Override
        public String getBrokerHost() {
            return "localhost";
        }

        @Override
        public int getBrokerPort() {
            return 0;
        }

        @Override
        public String getBrokerDataFolder() {
            return "temp";
        }

        @Override
        public boolean getBrokerInternal() {
            return true;
        }
    }

}
