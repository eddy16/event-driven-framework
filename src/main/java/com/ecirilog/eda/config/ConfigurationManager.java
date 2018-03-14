package com.ecirilog.eda.config;

import com.ecirilog.eda.config.exception.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.IOException;
import java.io.InputStream;

/**
 * {@link ConfigurationManager} is a central interface where it manages the needed initialization for the framework.
 *
 * @author Edgar Cirilo Gonzalez &lt;proed1612@gmail.com&gt;
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ConfigurationManager {
    /**
     * Constant LOGGER instance for class {@link ConfigurationManager}.
     */
    Logger LOGGER = LogManager.getLogger(ConfigurationManager.class);

    /**
     * Loads {@link Configuration} information from YML file.
     * @param configFile path to .yml configuration file.
     * @throws ConfigurationException if can't read the file or does not exists.
     */
    default Configuration configure(String configFile) throws ConfigurationException {
        final Yaml yaml = new Yaml();
        final Configuration  configuration;
        try {
            final InputStream streamYml = getClass().getClassLoader().getResourceAsStream(configFile);
            LOGGER.debug("configure: trying read to YAML file");
            configuration = yaml.loadAs(streamYml, Configuration.class);
            streamYml.close();
        } catch (final YAMLException ex) {
            LOGGER.error("configure: error while trying to read file", ex);
            throw new ConfigurationException("error while trying to read YML file", ex);
        } catch (final IOException ex) {
            LOGGER.error("configure: error while trying to close stream", ex);
            throw new ConfigurationException("cannot close InputStream", ex);
        }
        return configuration;
    }

    /**
     * Configures the framework.
     * @throws ConfigurationException if something went wrong.
     */
    void configure() throws ConfigurationException;
}
