package project;

import project.data.DataStorage;
import project.data.JDBCDataStorage;
import project.locks.LocksProvider;
import project.models.Model;
import project.models.ModelImpl;
import project.services.ConsoleIOService;
import project.services.IOService;

public class Application {
    public static void main(String[] args) {
        DataStorage dataStorage = new JDBCDataStorage();
        LocksProvider locksProvider = new LocksProvider();
        Model model = new ModelImpl(dataStorage, locksProvider);
        IOService ioService = new ConsoleIOService(model);
        //   IOService ioService2 = new ConsoleIOService(model);

        ioService.start();
        // ioService2.start();

        //contex.getAllClasses(ConsoleIOService.class).start();

    }
}
