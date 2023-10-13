package Notifier;

import Student.Student;
import java.util.Set;

public class Notifier {
    private Set<? extends Notifiable> notifiables;

    public Notifier(Set<? extends Notifiable> notifiables) {
        this.notifiables = notifiables;
    }

    public void doNotifyAll(String message){
        for(Notifiable notifiable : notifiables){
            notifiable.notify(message);
        }
    }
}
