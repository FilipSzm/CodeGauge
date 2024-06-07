package com.filipszm.codegauge.config;

import com.filipszm.codegauge.config.model.Arguments;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ArgumentReader {
    private static final String JAVA_FILE_EXTENSION = ".java";
    private static final String CONFIG_OPTION_SHORT = "c";
    private static final String CONFIG_OPTION_LONG = "config";

    public static Arguments readArguments(String[] args) {
        CommandLine cmd = parseArgs(args, createOptions());
        return new Arguments(getFilesToAnalyze(cmd), getConfigPath(cmd));
    }

    private static Optional<String> getConfigPath(CommandLine cmd) {
        return cmd.hasOption(CONFIG_OPTION_SHORT) ?
                Optional.of(cmd.getOptionValue(CONFIG_OPTION_SHORT)) :
                Optional.empty();
    }

    private static List<Path> getFilesToAnalyze(CommandLine cmd) {
        return Arrays.stream(cmd.getArgs()).map(Paths::get).map(path -> {
            if (Files.isRegularFile(path)) {
                return validateIfFileIsJavaFile(path);
            }
            return findAllJavaFilesInDir(path);
        }).flatMap(List::stream).distinct().toList();
    }

    private static Options createOptions() {
        return new Options().addOption(
                CONFIG_OPTION_SHORT,
                CONFIG_OPTION_LONG,
                true,
                "Specify the path to the configuration file"
        );
    }

    private static CommandLine parseArgs(String[] args, Options options) {
        var parser = new DefaultParser();
        try {
            return parser.parse(options, args, true);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Path> validateIfFileIsJavaFile(Path path) {
        if (!path.toFile().getName().endsWith(JAVA_FILE_EXTENSION)) {
            throw new IllegalArgumentException("The file isn't recognized as a Java file: " + path);
        }
        return List.of(path);
    }

    private static List<Path> findAllJavaFilesInDir(Path dir) {
        try (Stream<Path> stream = Files.walk(dir)) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toFile().getName().endsWith(JAVA_FILE_EXTENSION))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
