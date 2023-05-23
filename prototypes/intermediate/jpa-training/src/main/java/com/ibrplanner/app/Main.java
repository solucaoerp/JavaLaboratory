package com.ibrplanner.app;

import com.ibrplanner.app.domains.Pessoa;

public class Main {

    public static void main(String[] args) {

        Pessoa p1 = new Pessoa(1, "Charles Borges", "charles@gmail.com");
        Pessoa p2 = new Pessoa(2, "Carlos da Silva", "carlos@gmail.com");
        Pessoa p3 = new Pessoa(3, "Ana Maria", "ana@gmail.com");

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

    }

}
