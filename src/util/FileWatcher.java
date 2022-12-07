    package util;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FileWatcher implements Runnable{
    private String directory = "";

    public FileWatcher() {

    }

    //Constructor por si se desea monitorear algún archivo en un directorio distinto al establecido por defecto
    public FileWatcher(String directory) {
        this.directory = directory;
    }

    @Override
    public void run() {
        try (
        WatchService watchService = FileSystems.getDefault().newWatchService()) {
            // Obtiene la ruta del directorio a monitorear
            Path directoryPath = Paths.get(directory);

            // Registra el directorio para monitorear cambios de archivo
            try {
                directoryPath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            while (true) {
                // Obtiene la siguiente notificación de cambio de archivo
                WatchKey key;
                try {
                    key = watchService.take();
                    // Itera sobre los cambios de archivo notificados
                    for (WatchEvent<?> event : key.pollEvents()) {
                        // Obtiene la ruta del archivo que cambió
                        Path file = (Path) event.context();
                        System.out.println("El archivo " + file + " cambió.");

                    // Aquí se puede manejar el cambio en el archivo
                    }

                    // Es importante reiniciar la WatchKey después de procesar los cambios, de lo contrario, sale un error
                    key.reset();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileWatcher fileWatcher = new FileWatcher();

        Thread t = new Thread(fileWatcher);
        t.start();
    }
}