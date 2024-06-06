package com.filipszm.codegauge.config;

import com.filipszm.codegauge.config.model.Arguments;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public final class ArgumentReader {

    public static Arguments readArguments(String[] args) {
        var pathsToAnalyze = new ArrayList<Path>();
        Optional<String> configPath = Optional.empty();
        Iterator<String> iterator = Arrays.stream(args).iterator();
        while (iterator.hasNext()) {
            String arg = iterator.next();
            if ("-c".equals(arg)) {
                configPath = Optional.of(iterator.next());
            } else {
                pathsToAnalyze.add(Paths.get(arg));
            }
        }
        return new Arguments(pathsToAnalyze, configPath);
    }
}
