package main;

import engine.Engine;
import io.LevelReader;

public class Main
{
    private static Engine engine;

    public static void main(String[] args)
    {
        engine = new Engine();
        LevelReader.readScene("Test.level");
    }
}
