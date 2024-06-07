package com.filipszm.codegauge.config.model;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public record Arguments(List<Path> filesToAnalyze, Optional<String> configPath) {}
