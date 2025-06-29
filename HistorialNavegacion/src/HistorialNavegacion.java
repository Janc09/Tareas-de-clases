import java.util.Stack;
import java.io.IOException;
import java.io.FileWriter;

public class HistorialNavegacion {
    private Stack<String> backStack;  
    private Stack<String> forwardStack;
    private Stack<String> favoritosStack;
    private String paginaActual;

    public HistorialNavegacion() {
        backStack = new Stack<>();
        forwardStack = new Stack<>();
        favoritosStack = new Stack<>();
        paginaActual = "Inicio";  // Página de inicio predeterminada
    }

    public void visitarPagina(String nuevaPagina) {
        if (paginaActual != null) {
            backStack.push(paginaActual);
        }
        paginaActual = nuevaPagina;
        forwardStack.clear();  // Limpiar páginas adelantadas cuando se visita una nueva
        System.out.println("Visitando: " + paginaActual);
    }

    public void retroceder() {
        if (!backStack.isEmpty()) {
            forwardStack.push(paginaActual);
            paginaActual = backStack.pop();
            System.out.println("Retrocediendo a: " + paginaActual);
        } else {
            System.out.println("No hay más páginas atrás.");
        }
    }

    public void avanzar() {
        if (!forwardStack.isEmpty()) {
            backStack.push(paginaActual);
            paginaActual = forwardStack.pop();
            System.out.println("Avanzando a: " + paginaActual);
        } else {
            System.out.println("No hay más páginas adelante.");
        }
    }
   
    public void agregarAFavoritos(){
        if(paginaActual != null && !favoritosStack.contains(paginaActual)){
            favoritosStack.push(paginaActual);
            System.out.println("Página agregada a Favoritos: " + paginaActual);
        } else if (favoritosStack.contains(paginaActual)) {
            System.out.println("La página ya está en favoritos: " + paginaActual);
        }
    }
    
    public void mostrarHistorial() {
        System.out.println("Historial atrás: " + backStack);
        System.out.println("Página actual: " + paginaActual);
        System.out.println("Historial adelante: " + forwardStack);
    }
    
    public void mostrarFavoritos(){
        System.out.println("Favoritos: " + favoritosStack);
    }
    
    // MÉTODOS NUEVOS NECESARIOS PARA LA GUI
    public String getHistorialComoTexto() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== HISTORIAL DE NAVEGACIÓN ===\n\n");
        sb.append("Páginas Anteriores: ").append(backStack).append("\n");
        sb.append("Página Actual: ").append(paginaActual).append("\n");
        sb.append("Páginas Siguientes: ").append(forwardStack).append("\n\n");
        sb.append("================================");
        return sb.toString();
    }
    
    public String getFavoritosComoTexto() {
        if (favoritosStack.isEmpty()) {
            return "No hay favoritos guardados.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("=== PÁGINAS FAVORITAS ===\n\n");
        for (int i = favoritosStack.size() - 1; i >= 0; i--) {
            sb.append("• ").append(favoritosStack.get(i)).append("\n");
        }
        sb.append("\n=========================");
        return sb.toString();
    }
    
    public String getPaginaActual() {
        return paginaActual;
    }
     
    public void imprimirHistorialEnArchivo() {
        try (FileWriter writer = new FileWriter("historial.txt", true)) {
            writer.write("\n=== HISTORIAL COMPLETO ===\n");
            writer.write("Fecha: " + java.time.LocalDateTime.now() + "\n");
            writer.write("Páginas Anteriores: " + backStack + "\n");
            writer.write("Página Actual: " + paginaActual + "\n");
            writer.write("Páginas Siguientes: " + forwardStack + "\n");
            writer.write("Favoritos: " + favoritosStack + "\n");
            writer.write("=============================\n\n");
            System.out.println("Historial completo guardado en historial.txt");
        } catch (IOException e) {
            System.out.println("Error al guardar historial en archivo: " + e.getMessage());
        }
    }
}