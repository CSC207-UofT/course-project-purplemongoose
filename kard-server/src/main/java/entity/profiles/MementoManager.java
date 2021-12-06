package entity.profiles;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class MementoManager implements Serializable {
    @Serial
    private static final long serialVersionUID = 7388475794005749038L;

    private LinkedHashMap<String, Memento> mementoHistory;

    public MementoManager(){
        this.mementoHistory = new LinkedHashMap<>();
    }

    public void addPersonalMemento(Name name, Phone phone, Email email, String username){
        Memento newMem = new PersonMemento(name, phone, email, username);
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        this.mementoHistory.put(ft.format(dNow), newMem);
    }

    public PersonMemento getPersonalMemento(int index){
        List<String> keys = List.copyOf(this.mementoHistory.keySet());
        String key = keys.get(index);
        return (PersonMemento) this.mementoHistory.get(key);
    }

    public Memento[] getHistory(){
        ArrayList<Memento> history = new ArrayList<>();
        for (String key : this.mementoHistory.keySet()) {
            history.add(this.mementoHistory.get(key));
        }
        return history.toArray(new Memento[0]);
    }
}
