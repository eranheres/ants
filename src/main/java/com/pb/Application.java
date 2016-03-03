package com.pb;

import com.pb.ai.GA;
import com.pb.ant.Game;
import com.pb.ant.WorldFrame;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        GA ga = (GA)ctx.getBean("GA");
        Game game = (Game)ctx.getBean("game");

        game.init();
        EventQueue.invokeLater(() -> {
            JFrame ex = new WorldFrame(game);
            ex.setVisible(true);
        });

        ga.run(game);
    }

    @Bean
    Random random() {
        return new Random();
    }

    @Bean
    Game game() {
        return new Game();
    }
}