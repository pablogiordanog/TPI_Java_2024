package ar.com.gastronomiaycocina;

import ar.com.gastronomiaycocina.service.menu.MenuService;
import ar.com.gastronomiaycocina.service.menu.impl.MenuServiceImpl;

import java.util.Scanner;

/**
 *
 *
 */
public class App 
{
    public static void main( String[] args ) {

        Scanner scanner = new Scanner(System.in);

        MenuService menuService = new MenuServiceImpl();
        menuService.mostrarMenu(scanner);

        scanner.close();
    }
}
