package com.example.lab3;

import com.example.lab3.service.impl.DBHandler;
import com.example.lab3.service.impl.JAXBHandler;
import com.example.lab3.service.impl.StAXHandler;
import com.example.lab3.service.impl.lists.GradeList;
import com.example.lab3.service.impl.lists.StudentList;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

/**
 * Server application which contains enter point of program.
 * @author Volha Hrynko
 * @version 0.1.1
 */
public class MainServer {

    /**
     * Unique binding name
     */
    public static final String UNIQUE_BINDING_NAME = "StudentService";

    /**
     * Enter point of program
     * @param args
     * @throws RemoteException
     * @throws AlreadyBoundException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        final Registry registry = LocateRegistry.createRegistry(7788);
        //registry.rebind(UNIQUE_BINDING_NAME, new DBHandler());
        //registry.rebind(UNIQUE_BINDING_NAME, new StAXHandler());
        registry.rebind(UNIQUE_BINDING_NAME, new JAXBHandler());
        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}