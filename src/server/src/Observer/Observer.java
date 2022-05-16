package Observer;

import java.util.ArrayList;

public class Observer {
    public Observer() {
        subjects = new ArrayList<IUpdatable>();
    }

    private ArrayList<IUpdatable> subjects;
    public void AddUpdatable(IUpdatable notifiable) {
        if (!subjects.contains(notifiable)) {
            subjects.add(notifiable);
        }
    }
    public void RemoveUpdatable(IUpdatable notifiable) {
        if (subjects.contains(notifiable)) {
            subjects.remove(notifiable);
        }
    }
    public void Update(String updateString) {
        for (IUpdatable n: subjects) {
            n.Update(updateString);
        }
    }

}
