package edu.cerp.checkin.persistencia;

import edu.cerp.checkin.model.Inscripcion;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ArchivoManager {

    private static final String RUTA = "data/inscripciones.csv";
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public ArchivoManager() {
        try {
            Files.createDirectories(Path.of("data"));
            if (!Files.exists(Path.of(RUTA))) Files.createFile(Path.of(RUTA));
        } catch (IOException e) {
            System.err.println("⚠ No se pudo crear la carpeta data/: " + e.getMessage());
        }
    }

    public void guardar(Inscripcion i) {
        try (FileWriter fw = new FileWriter(RUTA, true)) {
            fw.write(i.getNombre() + ";" + i.getDocumento() + ";" + i.getCurso() + ";" +
                    i.getFechaHora().format(FMT) + "\n");
        } catch (IOException e) {
            System.err.println("⚠ Error al guardar inscripción: " + e.getMessage());
        }
    }

    public List<Inscripcion> cargar() {
        List<Inscripcion> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";");
                if (p.length == 4) {
                    try {
                        lista.add(new Inscripcion(p[0], p[1], p[2], LocalDateTime.parse(p[3], FMT)));
                    } catch (Exception e) {
                        System.err.println("⚠ Línea inválida: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("⚠ No se pudo leer el archivo: " + e.getMessage());
        }
        return lista;
    }
}
