package com.multithreads.files_manager.management;

import com.multithreads.files_manager.management.constants.*;
import com.multithreads.files_manager.management.exception.InvalidCommandException;
import com.multithreads.files_manager.management.file_workers.FileMerger;
import com.multithreads.files_manager.management.file_workers.FileService;
import com.multithreads.files_manager.management.file_workers.FileSplitter;
import com.multithreads.files_manager.statistics.StatisticService;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * Tool for interaction with user.
 */
public class Communicator {

    /**
     * Root logger.
     */
    private final Logger logger;

    private final FileSplitter fileSplitter;

    private final FileMerger fileMerger;

    private final FileService fileService;

    private final StatisticService statisticService;

    public Communicator(Logger logger){
        this.logger = logger;
        this.fileService = new FileService(logger);
        this.statisticService = new StatisticService(logger);
        this.fileSplitter = new FileSplitter(fileService, statisticService);
        this.fileMerger = new FileMerger(fileService, statisticService);
    }

    /**
     * Interacts with user.
     */
    public void openConsole() {
        Scanner scanner = new Scanner(System.in);
        try {
            Command command = chooseCommand(scanner);
            switch (command) {
                case SPLIT:
                    fileSplitter.split();
                case MERGE:
                    fileMerger.merge();
                case EXIT:
                    System.out.println("good bye");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            openConsole();
        } finally {
            fileService.getFileWorkersPool().shutdown();
            statisticService.getStatisticsPool().shutdown();
        }
    }

    Command chooseCommand(Scanner scanner) throws InvalidCommandException {
        System.out.println("To split file input " + Command.SPLIT.getSymbol()
                + "\n" + "To merge file input " + Command.MERGE.getSymbol()
                + "\n" + "To exit input " + Command.EXIT.getSymbol());
        Command command = Command.getCommand(scanner.nextLine());
        return command;
    }
}
