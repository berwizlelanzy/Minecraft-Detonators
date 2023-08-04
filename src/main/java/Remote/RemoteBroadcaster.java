package Remote;

import java.util.Vector;

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

    public void removeRemote(RemoteObserver observer){
        this.observers.remove(observer);
    }

    public void fire() {
        this.observers.forEach(RemoteObserver::fire);
        this.observers.clear(); // Hapus lagi remotenya kalo udh
    }
}
