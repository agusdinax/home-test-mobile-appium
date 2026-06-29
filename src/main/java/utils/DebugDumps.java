package utils;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

//clase que se utiliza para obtener la info de la pagina sin usar el inspector
public final class DebugDumps {
    private static final Logger LOG = Logger.getLogger(DebugDumps.class.getName());
    private static final Path DUMPS_DIR = Paths.get(System.getProperty("pageDumps.dir", "target/page-dumps"));
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss_SSS");

    //guarda el pageSource a XML y loguea la preview
    public static Path dumpPageSource(RemoteWebDriver driver, String tag) {
        try {
            Files.createDirectories(DUMPS_DIR);
            String ts = LocalDateTime.now().format(TS);
            String safeTag = (tag == null || tag.isBlank()) ? "screen" : tag.replaceAll("[^a-zA-Z0-9_-]", "_");
            String fileName = ts + "__" + safeTag + ".xml";
            Path out = DUMPS_DIR.resolve(fileName);
            String xml = driver.getPageSource();
            xml = xml.replaceAll("[\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F]", "");
            Files.writeString(out, xml, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            String preview = xml.length() > 8000 ? xml.substring(0, 8000) + "...(truncated)" : xml;
            LOG.info(() -> "[DebugDumps] PageSource guardado: " + out.toAbsolutePath());
            LOG.info(() -> "[DebugDumps] Preview XML (" + safeTag + "):\n" + preview);
            return out;
        } catch (Exception e) {
            LOG.severe(() -> "[DebugDumps] Error guardando pageSource: " + e.getMessage());
            return null;
        }
    }
    
}