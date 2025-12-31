package utils.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {
    /**
     * Logger instance oluştur
     * Her sınıf kendi logger'ını alır
     */
    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    /**
     * Test başlangıcı logu
     */
    public static void logTestStart(Logger logger, String testName) {
        logger.info("========================================");
        logger.info("TEST BAŞLADI: {}", testName);
        logger.info("Thread ID: {}", Thread.currentThread().getId());
        logger.info("========================================");
    }

    /**
     * Test bitiş logu
     */
    public static void logTestEnd(Logger logger, String testName, boolean passed) {
        logger.info("========================================");
        if (passed) {
            logger.info("TEST BAŞARILI: {}", testName);
        } else {
            logger.error("TEST BAŞARISIZ: {}", testName);
        }
        logger.info("========================================");
    }

    /**
     * Step logu (test adımları için)
     */
    public static void logStep(Logger logger, String step) {
        logger.info(">>> ADIM: {}", step);
    }

    /**
     * Element bulma logu
     */
    public static void logElementFound(Logger logger, String elementName) {
        logger.debug("Element bulundu: {}", elementName);
    }

    /**
     * Element bulunamama logu
     */
    public static void logElementNotFound(Logger logger, String elementName) {
        logger.warn("Element bulunamadı: {}", elementName);
    }

    /**
     * Click logu
     */
    public static void logClick(Logger logger, String elementName) {
        logger.info("Click yapıldı: {}", elementName);
    }

    /**
     * Text girişi logu
     */
    public static void logTextInput(Logger logger, String elementName, String text) {
        logger.info("Text girildi: {} -> '{}'", elementName, text);
    }

    /**
     * Exception logu
     */
    public static void logException(Logger logger, String message, Exception e) {
        logger.error("HATA: {} - Exception: {}", message, e.getMessage());
        logger.debug("Stack Trace:", e);
    }

    /**
     * Performance logu
     */
    public static void logPerformance(Logger logger, String action, long durationMs) {
        if (durationMs > 3000) {
            logger.warn("PERFORMANS UYARISI: {} - Süre: {}ms", action, durationMs);
        } else {
            logger.info("Performans: {} - Süre: {}ms", action, durationMs);
        }
    }
}
