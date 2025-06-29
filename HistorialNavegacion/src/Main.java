public class Main {
    public static void main(String[] args) {
        System.out.println("=== NAVEGADOR WEB SIMULADO ===");
        System.out.println("Iniciando aplicación con interfaz gráfica...\n");
        
        // Crear y ejecutar la interfaz gráfica
        VentanaNavegador.main(args);
        
        // CÓDIGO DE PRUEBA EN CONSOLA (opcional - descomentarlo para probar)
        /*
        System.out.println("=== PRUEBA EN CONSOLA ===");
        HistorialNavegacion navegadorConsola = new HistorialNavegacion();
        
        // Probar navegación básica
        navegadorConsola.visitarPagina("Google.com");
        navegadorConsola.visitarPagina("Wikipedia.org");
        navegadorConsola.visitarPagina("YouTube.com");
        navegadorConsola.visitarPagina("ChatGPT.com");
        
        System.out.println("\n--- Historial después de visitar páginas ---");
        navegadorConsola.mostrarHistorial();
        
        // Probar funcionalidad de favoritos
        System.out.println("\n--- Agregando páginas a favoritos ---");
        navegadorConsola.agregarAFavoritos(); // Agregar ChatGPT
        navegadorConsola.retroceder(); // Ir a YouTube
        navegadorConsola.agregarAFavoritos(); // Agregar YouTube
        navegadorConsola.retroceder(); // Ir a Wikipedia
        navegadorConsola.agregarAFavoritos(); // Agregar Wikipedia
        
        System.out.println("\n--- Favoritos ---");
        navegadorConsola.mostrarFavoritos();
        
        // Probar navegación
        System.out.println("\n--- Probando navegación ---");
        navegadorConsola.retroceder();
        navegadorConsola.mostrarHistorial();
        
        navegadorConsola.avanzar();
        navegadorConsola.mostrarHistorial();
        
        // Guardar historial en archivo
        System.out.println("\n--- Guardando historial ---");
        navegadorConsola.imprimirHistorialEnArchivo();
        */
    }
}