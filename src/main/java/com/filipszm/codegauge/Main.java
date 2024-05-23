package com.filipszm.codegauge;

import com.filipszm.codegauge.metric.loc.HalsteadLOCListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        String javaClassContent = Files.readString(Paths.get("examples/AllInOne8.java"));
        Java20Lexer lexer = new Java20Lexer(CharStreams.fromString(javaClassContent));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        Java20Parser parser = new Java20Parser(tokens);

        ParseTree tree = parser.compilationUnit();

        ParseTreeWalker walker = new ParseTreeWalker();
        var loc = new HalsteadLOCListener();
        walker.walk(loc, tree);

        System.out.println(loc.getMetricValue());

    }
}