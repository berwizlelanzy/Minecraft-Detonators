package Remote;

import java.util.Vector;

import EventListeners.TntDestroyListener;
import Fuzes.Enums.FuzeType;

public class RemoteBroadcaster {
    private static RemoteBroadcaster instance;
    private Vector<RemoteObserver> observers;

    private RemoteBroadcaster(){
        this.observers = new Vector<RemoteObserver>();
    }

    public static RemoteBroadcaster getInstance(){
        if (instance == null) {
            instance = new RemoteBroadcaster();
        }

        return instance;
    }

    public void addRemote(RemoteObserver observer){
        this.removeRemote(observer);    // Remove dulu biar ga pasti duplikat (kalo belum ada gpp)
        this.observers.add(observer);
    }

    public boolean removeRemote(RemoteObserver observer){
        for (int i = 0; i < this.observers.size(); i++) {
            if (((RemoteProxy) this.observers.get(i)).getObserver().equals(observer)) {
                this.observers.removeElementAt(i);
                return true;
            }
        }

        return false;
    }

    public void fire() {
        this.observers.forEach(RemoteObserver::fire);
        TntDestroyListener.getInstance().clearTnt(FuzeType.FUZE_REMOTE);
        this.observers.clear(); // Hapus lagi remotenya kalo udh
    }
}
