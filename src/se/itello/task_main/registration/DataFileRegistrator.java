/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.itello.task_main.registration;

/**
 *
 * @author Furhoff
 */
import se.itello.task.registration.internal.FileHandler;
import se.itello.task.registration.internal.PaymentFileHandlerInbetalningstjansten;
import se.itello.task.registration.internal.PaymentFileHandlerBetalningsservice;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//API:ets publik interface
public class DataFileRegistrator {
    
    //Definerar vilka/vilken format som stöds
    private enum FileFormat{
        BETALNINGSSERVICE("Betalningsservice"),
        INBETALNINGSTJANSTEN("Inbetalningstjänsten");
        
        private final String nameAsString;
        private FileFormat(String nameAsString) {
            this.nameAsString = nameAsString;
        }
        @Override
        public String toString() {
            return nameAsString;
        }
    }
    
    private final Map<String, FileFormat> extensionToFormat;
    private final Map<FileFormat, FileHandler> formatToHandler;
    
    public DataFileRegistrator() {
        extensionToFormat = new HashMap<>();
        extensionToFormat.put("_betalningsservice.txt", FileFormat.BETALNINGSSERVICE);
        extensionToFormat.put("_inbetalningstjansten.txt", FileFormat.INBETALNINGSTJANSTEN);
        
        formatToHandler = new HashMap<>();
        formatToHandler.put(FileFormat.BETALNINGSSERVICE, new PaymentFileHandlerBetalningsservice());
        formatToHandler.put(FileFormat.INBETALNINGSTJANSTEN, new PaymentFileHandlerInbetalningstjansten());
    }
    public void register(Path dataFilePath) throws FileFormatNotSupportedException {
        FileFormat format = findFormat(dataFilePath);
                    
        if(formatToHandler.containsKey(format)) {
            formatToHandler.get(format).dispatchFileData(dataFilePath);
        }
        else {
            throw new FileFormatNotSupportedException("File format can not be registred. "
                    + "Supported file formats are : " +
                    formatToHandler.keySet()
            );
        }
    }
    public void registerAll(Set<Path> dataFilePaths) throws FileFormatNotSupportedException{
        for(Path p : dataFilePaths) {
            register(p);
        }
    }
    private FileFormat findFormat(Path path) {
        String fileName = path.getFileName().toString();
        int extensionIndex = fileName.lastIndexOf("_");
        String extension = fileName.substring(extensionIndex);
        return extensionToFormat.get(extension);
    }
    
}
